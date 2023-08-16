// (C) Uri Wilensky. https://github.com/NetLogo/NetLogo

package org.nlogo.lab

// moved out of netlogo-gui into netlogo-core to fix headless compiler error - IOB 8/16/23
case class RunOptions(threadCount: Int, table: String, spreadsheet: String, updateView: Boolean, updatePlotsAndMonitors: Boolean)
