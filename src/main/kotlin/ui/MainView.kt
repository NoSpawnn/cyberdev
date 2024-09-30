package com.nospawnn.ui

import com.nospawnn.operations.Operation
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.DefaultListModel
import javax.swing.JFrame
import javax.swing.JList
import javax.swing.JPanel

class MainView : JFrame() {
    private val textPane = TextPane()
    private val stepsList = JList<Operation>(DefaultListModel<Operation>())
    private val stepsPane = StepsListPane(stepsList)
    private val operationsPane = OperationsPane(stepsList)

    init {
        createUI()
    }

    private fun createUI() {
        this.apply {
            title = "CyberDev"
            defaultCloseOperation = EXIT_ON_CLOSE
            minimumSize = Dimension(1280, 720)
            contentPane = JPanel().apply {
                layout = GridBagLayout()
            }
        }

        val gbc = GridBagConstraints().apply {
            fill = GridBagConstraints.BOTH
            weighty = 1.0
            gridy = 0
            gridx = GridBagConstraints.RELATIVE
        }

        gbc.weightx = 0.25
        gbc.gridwidth = 2
        gbc.insets = Insets(10, 10, 10, 10)
        contentPane.add(operationsPane, gbc)

        gbc.weightx = 0.1
        gbc.gridwidth = 1
        contentPane.add(stepsPane, gbc)

        gbc.weightx = 0.65
        gbc.gridwidth = 3
        contentPane.add(textPane, gbc)

        pack()
        setLocationRelativeTo(null)
    }

    fun updateOutput() {
        var text = textPane.getInput()

        for (op in (stepsList.model as DefaultListModel<Operation>).elements()) text = op.perform(text)

        textPane.setOutput(text)
    }
}