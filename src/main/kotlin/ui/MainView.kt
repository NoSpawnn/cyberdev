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
    private val argumentsEditorPane = ArgumentEditorPane()
    private val stepsPane = StepsListPane(stepsList, argumentsEditorPane)
    private val operationsPane = OperationsPane(stepsList, argumentsEditorPane)

    private var inputText = ""

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
            gridx = GridBagConstraints.RELATIVE
        }

        gbc.gridy = 0
        gbc.weightx = 0.25
        gbc.gridwidth = 3
        gbc.weighty = 1.0
        gbc.insets = Insets(10, 10, 10, 10)
        contentPane.add(operationsPane, gbc)

        gbc.weightx = 0.1
        gbc.gridwidth = 1
        gbc.insets = Insets(0, 0, 0, 0)
        val stepsAndArgsPane = JPanel().apply {
            layout = GridBagLayout()
            add(stepsPane, gbc)
            gbc.gridy = 1
            add(argumentsEditorPane, gbc)
        }
        gbc.gridy = 0
        gbc.insets = Insets(10, 10, 10, 10)
        contentPane.add(stepsAndArgsPane, gbc)

        gbc.weighty = 1.0
        gbc.weightx = 0.65
        gbc.gridwidth = 3
        contentPane.add(textPane, gbc)

        pack()
        setLocationRelativeTo(null)
    }

    fun updateOutput() {
        inputText = textPane.getInput()

        var output = inputText
        for (op in (stepsList.model as DefaultListModel<Operation>).elements()) {
            try {
                output = op.perform(output, argumentsEditorPane.getArgsFor(op.toString()))
            } catch (e: Exception) {
                println(e)
                textPane.displayOutputText("Invalid input for step \"${op}\"")
                return
            }
        }

        textPane.displayOutputText(output)
    }
}