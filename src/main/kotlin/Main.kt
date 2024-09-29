package com.nospawnn

import com.formdev.flatlaf.FlatDarkLaf
import javax.swing.SwingUtilities
import javax.swing.UIManager

fun main() {
    SwingUtilities.invokeLater {
        UIManager.setLookAndFeel(FlatDarkLaf())
        var ui = ui.MainView()
        ui.isVisible = true
    }
}