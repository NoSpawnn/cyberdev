package com.nospawnn.ui.argumenteditors

import com.nospawnn.ui.MainView
import javax.swing.JTextField
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class StringEditor(argName: String, override val default: String) : ArgumentEditor(argName) {
    override val editor = JTextField(default)

    init {
        editor.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent?) {
                (topLevelAncestor as MainView).updateOutput()
            }

            override fun removeUpdate(e: DocumentEvent?) = insertUpdate(e)
            override fun changedUpdate(e: DocumentEvent?) = insertUpdate(e)
        })
        super.addEditor(editor, newLine = true)
    }

    override fun getValue(): String = editor.text.trim()
}