// (C) Uri Wilensky. https://github.com/NetLogo/NetLogo

package org.nlogo.lab

import org.nlogo.api.LabProtocol
import org.nlogo.api.LogoException
import org.nlogo.nvm.{EngineException,LabInterface,Workspace}

// This is used when running headless. - ST 3/3/09

class Lab extends LabInterface {
  def newWorker(protocol: LabProtocol) =
    new Worker(protocol)
  def run(settings: LabInterface.Settings, protocol: LabProtocol, fn: ()=>Workspace) {
    import settings._
    // pool of workspaces, same size as thread pool
    val workspaces = (1 to threads).map(_ => fn.apply).toList
    val queue = new collection.mutable.Queue[Workspace]
    workspaces.foreach(queue.enqueue(_))
    try {
      queue.foreach(w => dims.foreach(w.setDimensions _))
      def modelDims = queue.head.world.getDimensions
      val worker = newWorker(protocol)
      val tableExporter =
        {
          tableWriter match {
            case Some(w) => new TableExporter(modelPath, dims.getOrElse(modelDims), protocol, w)
            case None => None
          }
        }
      val spreadsheetExporter =
        {
          tableWriter match {
            case Some(w) => new SpreadsheetExporter(modelPath, dims.getOrElse(modelDims), protocol, w)
            case None => None
          }
        }
      tableWriter.foreach(
        worker.addTableWriter(modelPath, dims.getOrElse(modelDims), _))
      spreadsheetWriter.foreach(
        worker.addSpreadsheetWriter(modelPath, dims.getOrElse(modelDims), _))
      statsWriter.foreach(
        worker.addStatsWriter(modelPath, dims.getOrElse(modelDims),
          {
            if (tableExporter != None) tableExporter.asInstanceOf[TableExporter]
            else spreadsheetExporter.asInstanceOf[SpreadsheetExporter]
          },
          {
            if (tableExporter != None) tableFileName
            else spreadsheetFileName
          },
          _
        )
      )
      worker.addListener(
        new LabInterface.ProgressListener {
          override def runCompleted(w: Workspace, runNumber: Int, step: Int) {
            queue.synchronized { queue.enqueue(w) }
          }
          override def runtimeError(w: Workspace, runNumber: Int, t: Throwable) {
            if (!suppressErrors)
              t match {
                case ee: EngineException =>
                  val msg = ee.runtimeErrorMessage
                  System.err.println("Run #" + runNumber + ", RUNTIME ERROR: " + msg)
                  ee.printStackTrace(System.err)
                case _: LogoException =>
                  System.err.println("Run #" + runNumber + ", RUNTIME ERROR: " + t.getMessage)
                  t.printStackTrace(System.err)
                case _ =>
                  System.err.println("Run #" + runNumber + ", JAVA EXCEPTION: " + t.getMessage)
                  t.printStackTrace(System.err)
              }
          } } )
      def nextWorkspace = queue.synchronized { queue.dequeue() }
      worker.run(workspaces.head, nextWorkspace _, threads)
    }
    finally { workspaces.foreach(_.dispose()) }
  }
}
