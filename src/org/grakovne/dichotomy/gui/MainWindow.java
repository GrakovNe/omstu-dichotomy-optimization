package org.grakovne.dichotomy.gui;

import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow(){
        super("DichotomyMethod");
        setBounds(200, 200, 350, 350);
        setVisible(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex){
            System.err.println("cant't apply natime look!");
        }
    }
}