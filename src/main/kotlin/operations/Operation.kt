package com.nospawnn.operations

import java.awt.Component
import javax.swing.DefaultListCellRenderer
import javax.swing.JList

abstract class Operation : DefaultListCellRenderer() {
    abstract val listText: String

    abstract fun perform(input: String): String
    abstract override fun getListCellRendererComponent(
        list: JList<*>?, value: Any?, index: Int, selected: Boolean, hasFocus: Boolean
    ): Component
}