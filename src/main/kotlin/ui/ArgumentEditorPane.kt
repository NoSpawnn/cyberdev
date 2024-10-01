package com.nospawnn.ui

import com.nospawnn.ui.argumenteditors.ArgumentEditor
import com.nospawnn.ui.argumenteditors.BoolEditor
import com.nospawnn.ui.argumenteditors.IntEditor
import com.nospawnn.ui.argumenteditors.StringEditor
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JScrollPane
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.set

class ArgumentEditorPane : JPanel() {
    private val argsDisplayList = JPanel().apply {
        layout = GridBagLayout()
    }
    private val label = JLabel("Configure")
    private val storedArgs: MutableMap<String, MutableMap<String, ArgumentEditor>> = mutableMapOf()

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

        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.insets = Insets(10, 0, 10, 0)
        this.add(label, gbc)

        gbc.fill = GridBagConstraints.BOTH
        gbc.weighty = 1.0
        gbc.insets = Insets(0, 0, 0, 0)
        this.add(JScrollPane(argsDisplayList), gbc)
    }

    fun setDisplayedArgs(step: String) {
        argsDisplayList.removeAll()
        val gbc = GridBagConstraints().apply {
            gridy = GridBagConstraints.RELATIVE
            gridx = 0
            insets = Insets(0, 0, 10, 0)
            anchor = GridBagConstraints.NORTHWEST
        }

        storedArgs[step]?.forEach { (_, v) ->
            argsDisplayList.add(v, gbc)
        }

        argsDisplayList.revalidate()
        argsDisplayList.repaint()
    }

    fun addArgsFor(step: String, args: Map<String, Any>?) {
        val newStoredArgs = mutableMapOf<String, ArgumentEditor>()

        args?.forEach { (name, initial) ->
            newStoredArgs[name] = when (initial) {
                is Int -> IntEditor(name, initial)
                is Boolean -> BoolEditor(name, initial)
                else -> StringEditor(name, initial.toString())
            }

            storedArgs.put(step, newStoredArgs)
        }
    }

    fun removeArgsFor(name: String) {
        storedArgs.remove(name)
    }

    fun getArgsFor(step: String): Map<String, Any> =
        storedArgs[step]?.map { (arg, editor) -> arg to editor.getValue() }?.toMap() ?: mapOf()
}