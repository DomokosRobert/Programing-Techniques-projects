package com.company;

import javax.swing.*;

public class Domy {
    private JButton additionButton;
    private JButton divisionButton;
    private JButton substractionButton;
    private JButton multiplicationButton;
    private JButton differentiationButton;
    private JButton integrationButton;
    private JTextField pol1;
    private JTextField pol2;
    private JPanel Gui;
    private JTextField result;

    public JButton getAdditionButton() {
        return additionButton;
    }
    public JButton getDivisionButton() {
        return divisionButton;
    }
    public JButton getSubstractionButton() {
        return substractionButton;
    }
    public JButton getMultiplicationButton() {
        return multiplicationButton;
    }
    public JButton getDifferentiationButton() {
        return differentiationButton;
    }
    public JButton getIntegrationButton() {
        return integrationButton;
    }
    public JTextField getPol1() {
        return pol1;
    }
    public JTextField getPol2() {
        return pol2;
    }
    public JTextField getResult() {
        return result;
    }
    private void createUIComponents() { }

    public Domy(){
        Controller cont= new Controller(this);
        cont.addActionEvent();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Polynomial Calculator");
        frame.setContentPane(new Domy().Gui);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
