package org.nlogo.extensions.bspace

import org.nlogo.api.{ Argument, Command, Context, LabProtocol }
import org.nlogo.core.I18N
import org.nlogo.core.Syntax._
import org.nlogo.window.GUIWorkspace

object SetPreExperimentCommands extends Command {
  override def getSyntax = {
    commandSyntax(right = List(StringType, StringType))
  }

  def perform(args: Array[Argument], context: Context) {
    if (!BehaviorSpaceExtension.validateForEditing(args(0).getString, context)) return

    BehaviorSpaceExtension.experiments(args(0).getString).preExperimentCommands = args(1).getString
  }
}

object SetSetupCommands extends Command {
  override def getSyntax = {
    commandSyntax(right = List(StringType, StringType))
  }

  def perform(args: Array[Argument], context: Context) {
    if (!BehaviorSpaceExtension.validateForEditing(args(0).getString, context)) return

    BehaviorSpaceExtension.experiments(args(0).getString).setupCommands = args(1).getString
  }
}

object SetGoCommands extends Command {
  override def getSyntax = {
    commandSyntax(right = List(StringType, StringType))
  }

  def perform(args: Array[Argument], context: Context) {
    if (!BehaviorSpaceExtension.validateForEditing(args(0).getString, context)) return

    BehaviorSpaceExtension.experiments(args(0).getString).goCommands = args(1).getString
  }
}

object SetPostRunCommands extends Command {
  override def getSyntax = {
    commandSyntax(right = List(StringType, StringType))
  }

  def perform(args: Array[Argument], context: Context) {
    if (!BehaviorSpaceExtension.validateForEditing(args(0).getString, context)) return

    BehaviorSpaceExtension.experiments(args(0).getString).postRunCommands = args(1).getString
  }
}

object SetPostExperimentCommands extends Command {
  override def getSyntax = {
    commandSyntax(right = List(StringType, StringType))
  }

  def perform(args: Array[Argument], context: Context) {
    if (!BehaviorSpaceExtension.validateForEditing(args(0).getString, context)) return

    BehaviorSpaceExtension.experiments(args(0).getString).postExperimentCommands = args(1).getString
  }
}

object SetRepetitions extends Command {
  override def getSyntax = {
    commandSyntax(right = List(StringType, NumberType))
  }

  def perform(args: Array[Argument], context: Context) {
    if (!BehaviorSpaceExtension.validateForEditing(args(0).getString, context)) return

    BehaviorSpaceExtension.experiments(args(0).getString).repetitions = args(1).getIntValue
  }
}

object SetSequentialRunOrder extends Command {
  override def getSyntax = {
    commandSyntax(right = List(StringType, BooleanType))
  }

  def perform(args: Array[Argument], context: Context) {
    if (!BehaviorSpaceExtension.validateForEditing(args(0).getString, context)) return

    BehaviorSpaceExtension.experiments(args(0).getString).sequentialRunOrder = args(1).getBooleanValue
  }
}

object SetRunMetricsEveryStep extends Command {
  override def getSyntax = {
    commandSyntax(right = List(StringType, BooleanType))
  }

  def perform(args: Array[Argument], context: Context) {
    if (!BehaviorSpaceExtension.validateForEditing(args(0).getString, context)) return

    BehaviorSpaceExtension.experiments(args(0).getString).runMetricsEveryStep = args(1).getBooleanValue
  }
}

object SetRunMetricsCondition extends Command {
  override def getSyntax = {
    commandSyntax(right = List(StringType, StringType))
  }

  def perform(args: Array[Argument], context: Context) {
    if (!BehaviorSpaceExtension.validateForEditing(args(0).getString, context)) return

    BehaviorSpaceExtension.experiments(args(0).getString).runMetricsCondition = args(1).getString
  }
}

object SetTimeLimit extends Command {
  override def getSyntax = {
    commandSyntax(right = List(StringType, NumberType))
  }

  def perform(args: Array[Argument], context: Context) {
    if (!BehaviorSpaceExtension.validateForEditing(args(0).getString, context)) return

    BehaviorSpaceExtension.experiments(args(0).getString).timeLimit = args(1).getIntValue
  }
}

object SetStopCondition extends Command {
  override def getSyntax = {
    commandSyntax(right = List(StringType, StringType))
  }

  def perform(args: Array[Argument], context: Context) {
    if (!BehaviorSpaceExtension.validateForEditing(args(0).getString, context)) return

    BehaviorSpaceExtension.experiments(args(0).getString).exitCondition = args(1).getString
  }
}

object SetMetrics extends Command {
  override def getSyntax = {
    commandSyntax(right = List(StringType, ListType | StringType))
  }

  def perform(args: Array[Argument], context: Context) {
    if (!BehaviorSpaceExtension.validateForEditing(args(0).getString, context)) return

    BehaviorSpaceExtension.experiments(args(0).getString).metrics = args(1).getList.toList.map(_.toString)
  }
}

object SetVariables extends Command {
  override def getSyntax = {
    commandSyntax(right = List(StringType, StringType))
  }

  def perform(args: Array[Argument], context: Context) {
    if (!BehaviorSpaceExtension.validateForEditing(args(0).getString, context)) return

    val parsed = LabProtocol.parseVariables(args(1).getString, context.workspace.world,
                                                context.workspace.asInstanceOf[GUIWorkspace], message => {
      BehaviorSpaceExtension.nameError(I18N.gui.getN("edit.behaviorSpace.invalidVarySpec", message), context)
    })

    BehaviorSpaceExtension.experiments(args(0).getString).constants = parsed.get._1
    BehaviorSpaceExtension.experiments(args(0).getString).subExperiments = parsed.get._2
  }
}

object SetParallelRuns extends Command {
  override def getSyntax = {
    commandSyntax(right = List(StringType, NumberType))
  }

  def perform(args: Array[Argument], context: Context) {
    if (!BehaviorSpaceExtension.validateForEditing(args(0).getString, context)) return

    BehaviorSpaceExtension.experiments(args(0).getString).threadCount = args(1).getIntValue
  }
}

object SetTable extends Command {
  override def getSyntax = {
    commandSyntax(right = List(StringType, StringType))
  }

  def perform(args: Array[Argument], context: Context) {
    if (!BehaviorSpaceExtension.validateForEditing(args(0).getString, context)) return

    BehaviorSpaceExtension.experiments(args(0).getString).table = args(1).getString
  }
}

object SetSpreadsheet extends Command {
  override def getSyntax = {
    commandSyntax(right = List(StringType, StringType))
  }

  def perform(args: Array[Argument], context: Context) {
    if (!BehaviorSpaceExtension.validateForEditing(args(0).getString, context)) return

    BehaviorSpaceExtension.experiments(args(0).getString).spreadsheet = args(1).getString
  }
}

object SetStats extends Command {
  override def getSyntax = {
    commandSyntax(right = List(StringType, StringType))
  }

  def perform(args: Array[Argument], context: Context) {
    if (!BehaviorSpaceExtension.validateForEditing(args(0).getString, context)) return

    BehaviorSpaceExtension.experiments(args(0).getString).stats = args(1).getString
  }
}

object SetLists extends Command {
  override def getSyntax = {
    commandSyntax(right = List(StringType, StringType))
  }

  def perform(args: Array[Argument], context: Context) {
    if (!BehaviorSpaceExtension.validateForEditing(args(0).getString, context)) return

    BehaviorSpaceExtension.experiments(args(0).getString).lists = args(1).getString
  }
}

object SetUpdateView extends Command {
  override def getSyntax = {
    commandSyntax(right = List(StringType, BooleanType))
  }

  def perform(args: Array[Argument], context: Context) {
    if (!BehaviorSpaceExtension.validateForEditing(args(0).getString, context)) return

    BehaviorSpaceExtension.experiments(args(0).getString).updateView = args(1).getBooleanValue
  }
}

object SetUpdatePlots extends Command {
  override def getSyntax = {
    commandSyntax(right = List(StringType, BooleanType))
  }

  def perform(args: Array[Argument], context: Context) {
    if (!BehaviorSpaceExtension.validateForEditing(args(0).getString, context)) return

    BehaviorSpaceExtension.experiments(args(0).getString).updatePlotsAndMonitors = args(1).getBooleanValue
  }
}