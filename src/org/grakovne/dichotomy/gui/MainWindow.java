package org.grakovne.dichotomy.gui;

import org.grakovne.dichotomy.executor.MathFunction;
import org.grakovne.dichotomy.executor.ScriptFunction;
import org.grakovne.dichotomy.optimizer.DichotomyMinimizer;
import org.grakovne.dichotomy.swann.SwannOptimizer;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Random;

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

        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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

        closeBtn.addActionListener((listener) -> System.exit(0));
        minimizeBtn.addActionListener((listener) -> {
                try{
                    createResultDialog(makeMinimization());
                } catch (Exception ex){
                    createErrDialog();
                }});

        setResizable(false);
        setVisible(true);


        functionField.setText("pow(x, 2)");
        x0Field.setText("4");
        accuracyField.setText("0.001");
    }

    private String getFunctionValue(){
        try{
            return functionField.getText();
        } catch (Exception ex){
            throw new GuiException("incorrect function");
        }
    }


    private double getX0Value(){
        try{
            return Double.parseDouble(x0Field.getText());
        }
        catch (Exception ex){
            Random randomX0 = new Random();
            System.out.println("x0 value calculated automatically");
            return randomX0.nextDouble();
        }
    }

    private double getAccuracyValue(){
        if (accuracyField.getText().length() == 0){
            throw new GuiException("invalid accuracy");
        }

        return Double.parseDouble(accuracyField.getText());

    }

    private Map<String, Double> makeMinimization(){

        MathFunction mathFunction = new ScriptFunction(getFunctionValue());
        SwannOptimizer optimizer = new SwannOptimizer(mathFunction, getX0Value(), getAccuracyValue());
        DichotomyMinimizer minimizer = new DichotomyMinimizer(mathFunction, optimizer.optimize(), getAccuracyValue());

        return minimizer.minimize();
    }

    private void createResultDialog(Map<String, Double> results){
        int digNumber = String.valueOf(getAccuracyValue()).length() -1;
        String minRange = String.format("%-35s %s", "Минимум лежит в диапазоне:", "["+ String.format("%."+digNumber+"f", results.get("left-limit")) + "; "+ String.format("%."+digNumber+"f", results.get("right-limit")) +"]") + "\n";
        String minApprox = String.format("%-38s %s", "Приближенное значение:", String.format("%."+digNumber+"f", results.get("averange-x"))) + "\n";
        String minFunction = String.format("%-34s %s", "Значение функции в этой точке:", String.format("%."+digNumber+"f", results.get("averange-y"))) + "\n";
        JOptionPane.showMessageDialog(null,minRange + minApprox + minFunction, "Минимум функции найден", JOptionPane.INFORMATION_MESSAGE);
    }

    private void createErrDialog(){
        JOptionPane.showMessageDialog(null,"Введите корректные данные", "Ошибка", JOptionPane.ERROR_MESSAGE);

    }
}