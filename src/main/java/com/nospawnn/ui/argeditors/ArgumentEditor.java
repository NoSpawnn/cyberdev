package com.nospawnn.ui.argeditors;

import com.nospawnn.ui.listeners.ArgListener;

import javax.swing.*;
import java.awt.*;

abstract public class ArgumentEditor extends JPanel {
    abstract public Object getValue();

    abstract public JLabel getLabel();

    abstract public String getLabelText();

    abstract public Component getEditor();

    abstract public JPanel getContentPane();

    abstract public void addListener(ArgListener al);

    abstract public void notifyListeners();
}
