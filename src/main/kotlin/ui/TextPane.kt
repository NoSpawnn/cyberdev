package com.nospawnn.ui

import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextArea
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class TextPane : JPanel() {
    private val inputLabel = JLabel("Input")
    private val inputTextBox = JTextArea().apply {
        lineWrap = true
        document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent?) = (topLevelAncestor as MainView).updateOutput()
            override fun removeUpdate(e: DocumentEvent?) = this.insertUpdate(e)
            override fun changedUpdate(e: DocumentEvent?) = this.insertUpdate(e)
        })
    }
    private val outputLabel = JLabel("Output")
    private val outputTextBox = JTextArea().apply {
        lineWrap = true
        isEditable = false
    }

    init {
        initUI()
    }

    private fun initUI() {
        this.layout = GridBagLayout()

        val gbc = GridBagConstraints().apply {
            weightx = 1.0
            gridx = 0
            gridy = GridBagConstraints.RELATIVE
        }

        gbc.insets = Insets(0, 0, 10, 0)
        gbc.fill = GridBagConstraints.HORIZONTAL
        this.add(inputLabel, gbc)

        gbc.fill = GridBagConstraints.BOTH
        gbc.weighty = 1.0
        this.add(JScrollPane(inputTextBox), gbc)

        gbc.weighty = 0.0
        gbc.fill = GridBagConstraints.HORIZONTAL
        this.add(outputLabel, gbc)

        gbc.insets = Insets(0, 0, 0, 0)
        gbc.fill = GridBagConstraints.BOTH
        gbc.weighty = 1.0
        this.add(JScrollPane(outputTextBox), gbc)
    }

    fun getInput(): String = inputTextBox.text

    fun displayOutputText(text: String) {
        outputTextBox.text = text
    }
}