package com.nospawnn.ui

import com.nospawnn.operations.Operation
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.DefaultListModel
import javax.swing.JLabel
import javax.swing.JList
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener

class StepsListPane(private val stepsList: JList<Operation>, private val configPane: ArgumentEditorPane) : JPanel() {
    private val stepsListScroll = JScrollPane(stepsList)
    private val stepsListLabel = JLabel("Steps")

    init {
        initUI()
    }

    private fun initUI() {
        layout = GridBagLayout()

        val gbc = GridBagConstraints().apply {
            gridy = GridBagConstraints.RELATIVE
            gridx = 0
            weightx = 1.0
        }

        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.insets = Insets(0, 0, 10, 0)
        this.add(stepsListLabel, gbc)

        stepsList.addMouseListener(object : MouseListener {
            override fun mouseClicked(e: MouseEvent) {
                if (e.clickCount != 2 || stepsList.selectedValue == null) return
                configPane.removeArgsFor(stepsList.selectedValue.toString())
                configPane.setDisplayedArgs("") // Clear displayed args
                (stepsList.model as DefaultListModel<Operation>).remove(stepsList.selectedIndex)
                (topLevelAncestor as MainView).updateOutput()
            }

            override fun mousePressed(e: MouseEvent?) {}
            override fun mouseReleased(e: MouseEvent?) {}
            override fun mouseEntered(e: MouseEvent?) {}
            override fun mouseExited(e: MouseEvent?) {}
        })
        stepsList.addListSelectionListener(object : ListSelectionListener {
            override fun valueChanged(e: ListSelectionEvent?) {
                if (stepsList.selectedValue == null) return
                configPane.setDisplayedArgs(stepsList.selectedValue.toString())
            }
        })

        gbc.weighty = 1.0
        gbc.fill = GridBagConstraints.BOTH
        gbc.insets = Insets(0, 0, 0, 0)
        this.add(stepsListScroll, gbc)
    }

}