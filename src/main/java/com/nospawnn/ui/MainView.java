package com.nospawnn.ui;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private JPanel contentPane;
    private OperationsPanel operationsPanel;
    private StepsPanel stepsPanel;
    private TextPanel textPanel;

    public MainView() {
        setContentPane(contentPane);
        setPreferredSize(new Dimension(1280, 720));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        stepsPanel.addRecipeListener(textPanel);
        operationsPanel.addListener(stepsPanel);
        textPanel.addListener(stepsPanel);

        pack();
        setLocationRelativeTo(null);
    }
}
