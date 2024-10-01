package com.nospawnn.ui.argumenteditors

import com.nospawnn.ui.MainView
import javax.swing.JSpinner
import javax.swing.event.ChangeEvent
import javax.swing.event.ChangeListener

class IntEditor(argName: String, override val default: Int) : ArgumentEditor(argName) {
    override val editor = JSpinner().apply {
        value = default
    }

    init {
        editor.addChangeListener(object : ChangeListener {
            override fun stateChanged(e: ChangeEvent?) {
                (topLevelAncestor as MainView).updateOutput()
            }
        })
        addEditor(editor)
    }

    override fun getValue(): Int = editor.value as Int
}