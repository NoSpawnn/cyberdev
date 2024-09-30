package com.nospawnn

import com.formdev.flatlaf.FlatDarkLaf
import com.nospawnn.ui.MainView
import javax.swing.SwingUtilities
import javax.swing.UIManager

fun main() {
    SwingUtilities.invokeLater {
        UIManager.setLookAndFeel(FlatDarkLaf())
        var ui = MainView()
        ui.isVisible = true
    }
}