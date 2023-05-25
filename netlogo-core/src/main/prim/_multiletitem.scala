// // (C) Uri Wilensky. https://github.com/NetLogo/NetLogo

package org.nlogo.prim

import org.nlogo.nvm.{ Context, Reporter }

class _multiletitem(private[this] val index: Int) extends Reporter {
  override def report(context: Context): AnyRef = {
    report_1(context)
  }

  def report_1(context: Context): AnyRef = {
    MultiLet.get(index)
  }

}
