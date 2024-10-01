package com.nospawnn.ui.argumenteditors

import com.nospawnn.ui.MainView
import java.awt.event.ItemEvent
import java.awt.event.ItemListener
import javax.swing.DefaultComboBoxModel
import javax.swing.JComboBox

class BoolEditor(argName: String, override val default: Boolean) : ArgumentEditor(argName) {
    override val editor = JComboBox<Boolean>().apply {
        addItem(true)
        addItem(false)
        selectedItem = if (default == true) getItemAt(0) else getItemAt(1)
    }

    init {
        editor.addItemListener(object : ItemListener {
            override fun itemStateChanged(e: ItemEvent?) {
                (topLevelAncestor as MainView).updateOutput()
            }
        })
        addEditor(editor)
    }


    override fun getValue(): Boolean = (editor.model as DefaultComboBoxModel<Boolean>).selectedItem == true
}