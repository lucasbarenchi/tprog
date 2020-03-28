package com.main.gui;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class ShowCommentsJInternalFrame extends JInternalFrame {

    public ShowCommentsJInternalFrame() {
        setBounds(100, 100, 450, 300);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);

    }

}
