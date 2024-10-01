package com.nospawnn.ui.argumenteditors

import java.awt.Component
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JLabel
import javax.swing.JPanel

abstract class ArgumentEditor(argName: String) : JPanel() {
    private val label = JLabel(argName)
    abstract val default: Any
    abstract val editor: Component

    private val gbc = GridBagConstraints().apply {
        fill = GridBagConstraints.CENTER
        gridx = GridBagConstraints.RELATIVE
        anchor = GridBagConstraints.WEST
    }

    init {
        layout = GridBagLayout()
        gbc.gridy = 0
        add(label, gbc)
    }

    fun addEditor(editor: Component, newLine: Boolean = false) {
        if (newLine) gbc.gridy = 1
        add(editor, gbc)
    }

    abstract fun getValue(): Any
}