package com.nospawnn.ui.argeditors;

import com.nospawnn.ui.listeners.ArgListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class StringEditor extends ArgumentEditor {
    private JPanel contentPane;
    private JLabel label;
    private JTextField editor;

    private final List<ArgListener> argListeners = new ArrayList<>();

    public StringEditor(String labelText, String initialValue) {
        this.label.setText(labelText);
        this.editor.setText(initialValue);
    }

    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

    @Override
    public void addListener(ArgListener al) {
        argListeners.add(al);
    }

    @Override
    public void notifyListeners() {
        for (var l : argListeners) l.argUpdated(this);
    }

    @Override
    public String getValue() {
        return editor.getText();
    }

    @Override
    public JLabel getLabel() {
        return this.label;
    }

    void createUIComponents() {
        this.label = new JLabel();
        this.editor = new JTextField();

        editor.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                notifyListeners();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                insertUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                insertUpdate(e);
            }
        });
    }

    @Override
    public String getLabelText() {
        return this.label.getText();
    }

    @Override
    public Component getEditor() {
        return this.editor;
    }

}
