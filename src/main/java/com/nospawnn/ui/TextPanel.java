package com.nospawnn.ui;

import com.nospawnn.operations.Operation;
import com.nospawnn.ui.listeners.InputTextListener;
import com.nospawnn.ui.listeners.RecipeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.*;

public class TextPanel implements RecipeListener {
    private JPanel contentPane;
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JLabel inputLabel;
    private JLabel outputLabel;

    private final List<InputTextListener> inputTextListeners = new ArrayList<>();

    public void updateOutput(LinkedHashMap<Operation, Map<String, Object>> steps) {
        var t = inputTextArea.getText();

        for (var op : steps.keySet())
            try {
                t = op.perform(t, steps.get(op));
            } catch (Exception e) {
                outputTextArea.setText("Invalid input for step \"" + op.toString() + "\"");
                return;
            }

        outputTextArea.setText(t);
    }

    @Override
    public void stepAdded(LinkedHashMap<Operation, Map<String, Object>> steps) {
        updateOutput(steps);
    }

    @Override
    public void stepRemoved(LinkedHashMap<Operation, Map<String, Object>> steps) {
        stepAdded(steps);
    }

    public void addListener(InputTextListener l) {
        inputTextListeners.add(l);
    }

    private void notifyListeners() {
        for (var l : inputTextListeners) l.onInputChanged();
    }

    private void createUIComponents() {
        inputTextArea = new JTextArea();
        inputTextArea.getDocument().addDocumentListener(new DocumentListener() {
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

}
