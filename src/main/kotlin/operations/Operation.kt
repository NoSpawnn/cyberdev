package com.nospawnn.operations

import java.awt.Component
import javax.swing.DefaultListCellRenderer
import javax.swing.JLabel
import javax.swing.JList
import javax.swing.ListCellRenderer
import javax.swing.tree.TreeNode
import kotlin.jvm.Throws

abstract class Operation {
    abstract val listText: String

    abstract fun perform(input: String): String

    override fun toString(): String = listText
}