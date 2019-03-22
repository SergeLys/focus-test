package com.cft.gui;

import com.cft.model.ModelOperations;
import com.cft.model.SortingStation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;

/**
 * The class implements an app's main window.
 */
public class MainForm extends JFrame {

    private JButton calculateButton;
    private JTextField inputText;
    private JTextArea answer;
    private JButton clearButton;

    public MainForm() {
        super("Calculator");
        setPreferredSize(new Dimension(600,300));
        setContentPane(initPanel());
        this.calculateButton.addActionListener(new CalculateButtonListener());
        this.clearButton.addActionListener(new ClearButtonListener());
    }

    private JPanel initPanel(){

        calculateButton = new JButton("Calculate");
        clearButton = new JButton("Clear");
        inputText = new JTextField();
        inputText.setMaximumSize(new Dimension(590,30));
        answer = new JTextArea();
        answer.setMinimumSize(new Dimension(590,200));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS ));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new Label("Please, input your expression"));
        panel.add(Box.createVerticalStrut(10));
        panel.add(inputText);
        panel.add(Box.createVerticalStrut(10));
        panel.add(answer);
        panel.add(Box.createVerticalStrut(10));
        Box btnBox = new Box(BoxLayout.X_AXIS);
        btnBox.add(calculateButton);
        btnBox.add(clearButton);
        panel.add(btnBox);

        return panel;
    }

    public class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double result;
            try {
                result = ModelOperations.calculate(SortingStation.toPostfixForm(inputText.getText()));
                answer.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                answer.setText("Oops, it's a parse error =(\n" + ex.getMessage());
            } catch (EmptyStackException ex) {
                answer.setText("Oops, it's an error in the expression's structure =(\n");
            } catch (Exception ex) {
                answer.setText("Oops, error =( \n");
            }

        }
    }

    public class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            inputText.setText("");
            answer.setText("");
        }
    }
}
