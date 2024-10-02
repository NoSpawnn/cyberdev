package com.nospawnn.ui;

import com.nospawnn.operations.Operation;
import com.nospawnn.ui.argeditors.ArgumentEditor;
import com.nospawnn.ui.argeditors.BoolEditor;
import com.nospawnn.ui.argeditors.IntEditor;
import com.nospawnn.ui.argeditors.StringEditor;
import com.nospawnn.ui.listeners.ArgListener;
import com.nospawnn.ui.listeners.InputTextListener;
import com.nospawnn.ui.listeners.OperationsTreeListener;
import com.nospawnn.ui.listeners.RecipeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class StepsPanel implements OperationsTreeListener, InputTextListener, ArgListener {
    private enum Action {
        ADDED,
        REMOVED
    }

    private JPanel contentPane;
    private JList<Operation> stepsList;
    private JLabel stepsLabel;
    private JLabel argumentsLabel;
    private JScrollPane configureScrollPane;
    private JPanel configurePane;

    private final List<RecipeListener> recipeListeners = new ArrayList<>();
    private final Map<Operation, Map<String, ArgumentEditor>> storedArgs = new HashMap<>();

    private void createUIComponents() {
        stepsList = new JList<>(new DefaultListModel<>());

        stepsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() != 2
                        || stepsList.getSelectedValue() == null) return;
                removeStep(stepsList.getSelectedValue());
            }
        });
        stepsList.addListSelectionListener(e -> {
            if (stepsList.getSelectedValue() == null) return;
            setDisplayedArgs(stepsList.getSelectedValue());
        });

        configurePane = new JPanel();
    }

    public void addStep(Operation op) {
        ((DefaultListModel<Operation>) stepsList.getModel()).addElement(op);
        addArgs(op, op.getRequiredArgs());
        stepsList.setSelectedValue(op, true);
        setDisplayedArgs(op);
        notifyRecipeListeners(Action.ADDED);
    }

    public void removeStep(Operation op) {
        ((DefaultListModel<Operation>) stepsList.getModel()).removeElement(op);
        removeArgsFor(op);
        notifyRecipeListeners(Action.REMOVED);
    }

    private void addArgs(Operation step, Map<String, Object> args) {
        Map<String, ArgumentEditor> newStoredArgs = new HashMap<>();

        args.forEach((arg, initial) ->
                newStoredArgs.put(arg,
                        switch (initial) {
                            case String s -> new StringEditor(arg, s);
                            case Boolean b -> new BoolEditor(arg, b);
                            case Integer i -> new IntEditor(arg, i);
                            default -> new StringEditor(arg, initial.toString());
                        }));

        storedArgs.put(step, newStoredArgs);
    }

    private void removeArgsFor(Operation step) {
        storedArgs.remove(step);
    }

    public Map<String, Object> getArgsFor(Operation step) {
        var argsMap = new HashMap<String, Object>();

        storedArgs
                .getOrDefault(step, new HashMap<>())
                .forEach((arg, editor) ->
                        argsMap.put(arg, editor.getValue()));

        return argsMap;
    }

    private void setDisplayedArgs(Operation step) {
        configurePane.removeAll();
        configurePane.setLayout(new GridLayout(storedArgs.get(step).size(), 1));

        for (var v : storedArgs.get(step).values()) {
            v.addListener(this);
            configurePane.add(v.getContentPane());
        }

        configureScrollPane.revalidate();
        configureScrollPane.repaint();
    }

    public void addRecipeListener(RecipeListener l) {
        recipeListeners.add(l);
    }

    private void notifyRecipeListeners(Action action) {
        final var steps = new LinkedHashMap<Operation, Map<String, Object>>();
        for (var o : ((DefaultListModel<Operation>) stepsList.getModel())
                .toArray())
            steps.put((Operation) o, getArgsFor((Operation) o));

        switch (action) {
            case ADDED -> {
                for (var l : recipeListeners) l.stepAdded(steps);
            }
            case REMOVED -> {
                for (var l : recipeListeners) l.stepRemoved(steps);
            }
        }
    }

    @Override
    public void operationsTreeActivated(Operation op) {
        addStep(op);
        addArgs(op, op.getRequiredArgs());
    }

    @Override
    public void onInputChanged() {
        notifyRecipeListeners(Action.ADDED);
    }

    @Override
    public void argUpdated(ArgumentEditor ae) {
        storedArgs
                .get(stepsList.getSelectedValue())
                .put(ae.getLabelText(), ae);
        notifyRecipeListeners(Action.ADDED);
    }
}
