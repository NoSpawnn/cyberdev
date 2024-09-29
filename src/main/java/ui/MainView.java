package ui;

import com.nospawnn.operations.Operation;
import com.nospawnn.operations.OperationsList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainView extends JFrame {
    private JPanel contentPane;
    private JTree sidePanelTree;
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JTextField searchTextField;
    private JList<String> displayedStepsList;
    private JScrollPane stepsListScrollPane;
    private JPanel textPane;
    private JScrollPane operationsScrollPane;
    private JPanel operationsPane;
    private JPanel stepsPane;

    private final List<Operation> steps;

    public MainView() {
        steps = new ArrayList<>();

        setTitle("CyberDev");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);

        pack();
        setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        createStepsList();
        createRecipeTree();
    }

    private void createStepsList() {
        DefaultListModel<String> stepsListModel = new DefaultListModel<>();
        displayedStepsList = new JList<>(stepsListModel);
    }

    private void createRecipeTree() {
        var treeEntries = OperationsList.INSTANCE.getOperations();

        var root = new DefaultMutableTreeNode(null);
        for (var entry : treeEntries.entrySet()) {
            var folderNode = new DefaultMutableTreeNode(entry.getKey());
            for (var op : entry.getValue()) folderNode.add(new DefaultMutableTreeNode(op.getListText()));
            root.add(folderNode);
        }

        sidePanelTree = new JTree(root);
        sidePanelTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() != 2) return;

                var selected = (DefaultMutableTreeNode) sidePanelTree.getLastSelectedPathComponent();

                if (selected == null || !selected.isLeaf()) return;

                var optOp = treeEntries.get(selected.getParent().toString())
                        .stream()
                        .filter(o -> o.getListText().equals(selected.toString()))
                        .findFirst();

                if (optOp.isEmpty()) return;

                var op = optOp.get();
                ((DefaultListModel<String>) displayedStepsList.getModel()).addElement(op.getListText());
                steps.add(op);

                updateOutput();
            }
        });

        displayedStepsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() != 2) return;

                var idxToRemove = displayedStepsList.getSelectedIndex();
                ((DefaultListModel<String>) displayedStepsList.getModel()).remove(idxToRemove);
                steps.remove(idxToRemove);

                updateOutput();
            }
        });

        inputTextArea = new JTextArea();
        inputTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateOutput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateOutput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    private void updateOutput() {
        var text = inputTextArea.getText();

        for (var step : steps)
            text = step.perform(text);

        outputTextArea.setText(text);
    }

}
