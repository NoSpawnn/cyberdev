package com.nospawnn.ui.argeditors;

import com.nospawnn.ui.listeners.ArgListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class BoolEditor extends ArgumentEditor {
    private JPanel contentPane;
    private JLabel label;
    private JComboBox<Boolean> comboBox;

    private final List<ArgListener> argListeners = new ArrayList<>();

    public BoolEditor(String labelText, Boolean initialValue) {
        this.label.setText(labelText);
        this.comboBox.setSelectedIndex(initialValue ? 0 : 1);
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
    public Boolean getValue() {
        return Objects.equals(comboBox.getSelectedItem(), true);
    }

    @Override
    public JLabel getLabel() {
        return this.label;
    }

    @Override
    public String getLabelText() {
        return this.label.getText();
    }

    @Override
    public Component getEditor() {
        return this.comboBox;
    }

    void createUIComponents() {
        this.label = new JLabel();
        this.comboBox = new JComboBox<>();

        comboBox.addItem(true);
        comboBox.addItem(false);
        this.comboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.DESELECTED) return;
            notifyListeners();
        });
    }

}
