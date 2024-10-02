package com.nospawnn.ui;

import com.nospawnn.operations.Operation;
import com.nospawnn.operations.OperationsList;
import com.nospawnn.ui.listeners.OperationsTreeListener;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class OperationsPanel {
    private JTextField searchField;
    private JTree operationsTree;
    private JPanel contentPane;
    private JLabel operationsLabel;

    private final List<OperationsTreeListener> operationsTreeListeners = new ArrayList<>();

    private void createUIComponents() {
        addOperationsToTree();
    }

    private void addOperationsToTree() {
        final var ops = OperationsList.INSTANCE.getOperations();
        var rootNode = new DefaultMutableTreeNode();

        for (var section : ops.keySet()) {
            var sectionNode = new DefaultMutableTreeNode(section);
            for (var op : ops.get(section)) sectionNode.add(new DefaultMutableTreeNode(op));
            rootNode.add(sectionNode);
        }

        operationsTree = new JTree(rootNode);
        operationsTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() != 2
                        || operationsTree.getLastSelectedPathComponent() == null)
                    return;

                var selected =
                        (DefaultMutableTreeNode)
                                operationsTree
                                        .getLastSelectedPathComponent();

                if (!selected.isLeaf()) return;

                var optOp = ops
                        .get(selected.getParent().toString())
                        .stream()
                        .filter(o -> o.toString().equals(selected.toString()))
                        .findFirst();

                if (optOp.isEmpty()) return;

                notifyListeners(optOp.get());
            }
        });
    }

    public void addListener(OperationsTreeListener l) {
        operationsTreeListeners.add(l);
    }

    private void notifyListeners(Operation op) {
        for (var l : operationsTreeListeners) l.operationsTreeActivated(op);
    }

}
