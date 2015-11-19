package org.grakovne.dichotomy.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    JLabel enterFunctionHint;
    JLabel enterX0Hint;
    JTextField functionField;
    JTextField accuracyField;
    JTextField x0Field;
    JLabel enterAccuracyHint;
    JButton minimizeBtn;
    JButton closeBtn;


    public MainWindow(){
        super("DichotomyMethod");
        setBounds(300, 100, 290, 340);
        setLayout(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex){
            System.err.println("cant't apply natime look!");
        }

        enterFunctionHint = new JLabel("Укажите функцию для минимизации");
        enterFunctionHint.setFont(new Font("Arial", Font.PLAIN, 16));
        enterFunctionHint.setBounds(11, 15, 300, 30);
        add(enterFunctionHint);

        functionField = new JTextField();
        functionField.setBounds(11, 50, 260, 25);
        add(functionField);

        enterAccuracyHint = new JLabel("Укажите точность вычислений");
        enterAccuracyHint.setFont(new Font("Arial", Font.PLAIN, 16));
        enterAccuracyHint.setBounds(10, 175, 255, 30);
        enterAccuracyHint.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterAccuracyHint);

        accuracyField = new JTextField();
        accuracyField.setBounds(11, 210, 260, 25);
        add(accuracyField);

        enterX0Hint = new JLabel("Укажите начальную точку");
        enterX0Hint.setFont(new Font("Arial", Font.PLAIN, 16));
        enterX0Hint.setBounds(10, 95, 255, 30);
        enterX0Hint.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterX0Hint);


        x0Field = new JTextField();
        x0Field.setBounds(11, 130, 260, 25);
        add(x0Field);


        minimizeBtn = new JButton("Минимизировать");
        minimizeBtn.setBounds(15, 270, 120, 25);
        add(minimizeBtn);

        closeBtn = new JButton("Закрыть");
        closeBtn.setBounds(140, 270, 125, 25);
        add(closeBtn);

        closeBtn.addActionListener((listener)->System.exit(0));

        setResizable(false);
        setVisible(true);
    }
}