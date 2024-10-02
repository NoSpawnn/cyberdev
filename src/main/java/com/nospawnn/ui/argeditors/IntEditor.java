package com.nospawnn.ui.argeditors;

import com.nospawnn.ui.listeners.ArgListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class IntEditor extends ArgumentEditor {
    private JPanel contentPane;
    private JLabel label;
    private JSpinner editor;

    private final List<ArgListener> argListeners = new ArrayList<>();

    public IntEditor(String labelText, Integer initialValue) {
        this.label.setText(labelText);
        this.editor.setValue(initialValue);
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
    public Integer getValue() {
        return (Integer) editor.getValue();
    }

    @Override
    public JLabel getLabel() {
        return this.label;
    }

    void createUIComponents() {
        this.label = new JLabel();
        this.editor = new JSpinner();
        this.editor.addChangeListener(e -> notifyListeners());
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
