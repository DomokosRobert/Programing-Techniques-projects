package com.company;


public class Controller {
    public Domy gui ;

    public Controller(Domy gui) {
        this.gui = gui;
    }

    public void addActionEvent() {

        gui.getAdditionButton().addActionListener(e -> {

            Polynomial A = Polynomial.read(gui.getPol1().getText());
            Polynomial B = Polynomial.read(gui.getPol2().getText());
            gui.getResult().setText(Polynomial.Addition(A,B).toString());
        });
        gui.getSubstractionButton().addActionListener(e -> {
            Polynomial A = Polynomial.read(gui.getPol1().getText());
            Polynomial B = Polynomial.read(gui.getPol2().getText());
            gui.getResult().setText(Polynomial.Substract(A,B).toString());
        });
        gui.getMultiplicationButton().addActionListener(e -> {
            Polynomial A = Polynomial.read(gui.getPol1().getText());
            Polynomial B = Polynomial.read(gui.getPol2().getText());
            gui.getResult().setText(Polynomial.Multiplication(A,B).toString());
        });
        gui.getDivisionButton().addActionListener(e -> {
            gui.getResult().setText("This feature is not available!");
        });
        gui.getIntegrationButton().addActionListener(e -> {
            Polynomial A = Polynomial.read(gui.getPol1().getText());;
            gui.getResult().setText(Polynomial.Integrate(A).toString());
        });
        gui.getDifferentiationButton().addActionListener(e -> {
            Polynomial A = Polynomial.read(gui.getPol1().getText());
            gui.getResult().setText(Polynomial.Derivate(A).toString());
        });
    }
}
