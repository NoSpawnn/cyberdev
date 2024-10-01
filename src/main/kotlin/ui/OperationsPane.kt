package com.nospawnn.ui

import com.nospawnn.operations.Operation
import com.nospawnn.operations.OperationsList
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
import javax.swing.JTextField
import javax.swing.JTree
import javax.swing.tree.DefaultMutableTreeNode

class OperationsPane(private val stepsList: JList<Operation>, private val configPane: ArgumentEditorPane) : JPanel() {
    private val searchBox = JTextField()
    private val operationsLabel = JLabel("Operations")

    private lateinit var sidePanelScrollPane: JScrollPane
    lateinit var sidePanelTree: JTree

    init {
        initUI()
    }

    private fun initUI() {
        this.layout = GridBagLayout()
        val gbc = GridBagConstraints().apply {
            gridx = 0
            gridy = GridBagConstraints.RELATIVE
        }

        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.insets = Insets(0, 0, 10, 0)
        this.add(operationsLabel, gbc)

        gbc.weightx = 1.0
        gbc.insets = Insets(0, 0, 10, 0)
        this.add(searchBox, gbc)

        gbc.insets = Insets(0, 0, 0, 0)
        gbc.fill = GridBagConstraints.BOTH
        gbc.weighty = 1.0
        initTree()
        sidePanelScrollPane = JScrollPane(sidePanelTree)
        this.add(sidePanelScrollPane, gbc)
    }

    private fun initTree() {
        val root = DefaultMutableTreeNode()

        for ((key, opList) in OperationsList.operations) {
            val sectionNode = DefaultMutableTreeNode(key)
            for (op in opList) sectionNode.add(DefaultMutableTreeNode(op.toString()))
            root.add(sectionNode)
        }

        sidePanelTree = JTree(root).apply {
            showsRootHandles = true
            isRootVisible = false

            addMouseListener(object : MouseListener {
                override fun mouseClicked(e: MouseEvent) {
                    if (e.clickCount != 2) return

                    val selected =
                        if (lastSelectedPathComponent == null) return
                        else lastSelectedPathComponent as DefaultMutableTreeNode

                    if (!selected.isLeaf) return

                    val op = OperationsList
                        .operations[selected.parent.toString()]
                        ?.find { it.toString() == selected.toString() }!!

                    (stepsList.model as DefaultListModel<Operation>).addElement(op)
                    configPane.addArgsFor(op.toString(), op.requiredArgs)
                    configPane.setDisplayedArgs(op.toString())
                    (topLevelAncestor as MainView).updateOutput()
                }

                override fun mousePressed(e: MouseEvent?) {}
                override fun mouseReleased(e: MouseEvent?) {}
                override fun mouseEntered(e: MouseEvent?) {}
                override fun mouseExited(e: MouseEvent?) {}
            })

        }
    }

}