package com.cft.gui;

import com.cft.model.ModelOperations;
import com.cft.model.Parser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;

public class MainForm extends JFrame{

    private JPanel panel;
    private JButton calculateButton;
    private JTextField inputText;
    private JTextArea answer;
    private JButton clearButton;

    public MainForm(){
        this.setTitle("Calculator");
        this.getContentPane().add(panel);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.calculateButton.addActionListener(new CalculateButtonListener());
        this.clearButton.addActionListener(new ClearButtonListener());

    }

    public class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double result;
            try {
                result = ModelOperations.calculate(Parser.toPostfixForm(inputText.getText()));
                answer.setText(String.valueOf(result));
            } catch (NumberFormatException ex){
                answer.setText("Oops, it's a parse error =(\n" + ex.getMessage());
            } catch (EmptyStackException ex){
                answer.setText("Oops, it's an error in the expression's structure =(\n");
            } catch (Exception ex){
                answer.setText("Oops, error =(\n");
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
