package com.company;

public class Monomial implements Comparable {
    public int power;
    public double coeff;

    // this constructor is used for initialization
    //
    public Monomial(){
        this.power = 0;
        this.coeff = 0;
    }

    // this constructor is used for setting the power and coeff
    //
    public Monomial(int power, double coeff) {
        this.power = power;
        this.coeff = coeff;
    }

    // this function gives me the power of the monomial
    //
    public int getPower() {
        return power;
    }

    // the function toString() converts the monom in a string
    // for the integration I consider it if the coeff is a
    // rational number to be represented as double if not as an integer
    @Override
    public String toString() {
        if((int)coeff == coeff)
        {
            if (coeff < 0)
                return (int) coeff + "x^" + power;
            else if (coeff > 0)
                return "+" + (int) (coeff) + "x^" + power;
            else
                return "";
        }
        else {
            if (coeff < 0)
                return coeff + "x^" + power;
            else if (coeff > 0)
                return "+" + coeff + "x^" + power;
            else
                return "";
        }
    }

    // the function compareTo is used for sorting in polynomial the monomials
    // so that it gives the polynomial sorted
    @Override
    public int compareTo(Object o) {
        return ((Monomial)o).getPower()-getPower();
    }
}
