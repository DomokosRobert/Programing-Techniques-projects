package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {

    ArrayList<Monomial> poly = new ArrayList<>();

    public Polynomial() {
    }

    public ArrayList<Monomial> getPoly() {
        return poly;
    }

    // reading method that uses regex
    public static Polynomial read(String expr) {
        Polynomial pol = new Polynomial();
        Pattern p = Pattern.compile("([-+]?\\d+)x\\^(-?\\d+)");
        Matcher m = p.matcher(expr);
        while (m.find()) {
            Monomial mol = new Monomial();
            mol.coeff = Double.parseDouble(m.group(1));
            mol.power = Integer.parseInt(m.group(2));
            pol.getPoly().add(mol);
        }
        return pol;
    }

    // toString method that helps me print the polynomials
    @Override
    public String toString() {
        if (poly.isEmpty())
            return "";

        String str = "";
        for (Monomial mono : poly) {
            str = str + mono;
        }
        return str;
    }

    // addition function
    public static Polynomial Addition(Polynomial A, Polynomial B) {
        Polynomial S = new Polynomial();
        for (Monomial mono1 : A.getPoly())
            for (Monomial mono2 : B.getPoly())
                if (mono1.power == mono2.power) {
                    S.getPoly().add(new Monomial(mono1.power,mono1.coeff + mono2.coeff));
                }
        for (Monomial mono1 : B.getPoly()) {
            int ok = 0;
            for (Monomial mono2 : A.getPoly())
                if (mono1.power == mono2.power) {
                    ok = 1;
                    break;
                }
            if (ok == 0)
                S.getPoly().add(new Monomial(mono1.power, mono1.coeff));
        }
        for (Monomial mono1 : A.getPoly()) {
            int ok = 0;
            for (Monomial mono2 : B.getPoly())
                if (mono1.power == mono2.power) {
                    ok = 1;
                    break;
                }
            if (ok == 0)
                S.getPoly().add(new Monomial(mono1.power, mono1.coeff));
        }
        Collections.sort(S.getPoly());
        return S;
    }

    // subtraction function
    public static Polynomial Substract(Polynomial A, Polynomial B) {
        Polynomial S = new Polynomial(),B1=B;
        for (Monomial mono1 : B1.getPoly()) {
            mono1.coeff = -mono1.coeff;
        }
        for (Monomial mono1 : A.getPoly())
            for (Monomial mono2 : B1.getPoly())
                if (mono1.power == mono2.power) {
                    S.getPoly().add(new Monomial(mono1.power,mono1.coeff + mono2.coeff));
                }
        for (Monomial mono1 : A.getPoly()) {
            int ok = 0;
            for (Monomial mono2 : B1.getPoly())
                if (mono1.power == mono2.power)
                    ok = 1;
            if (ok == 0)
                S.getPoly().add(new Monomial(mono1.power,mono1.coeff));
        }
        for (Monomial mono1 : B1.getPoly()) {
            int ok = 0;
            for (Monomial mono2 : A.getPoly())
                if (mono1.power == mono2.power)
                    ok = 1;
            if (ok == 0)
                S.getPoly().add(new Monomial(mono1.power, mono1.coeff));
        }
        Collections.sort(S.getPoly());
        return S;
    }

    // Multiplication function
    public static Polynomial Multiplication(Polynomial A, Polynomial B) {
        Polynomial P = new Polynomial();
        for (Monomial mono1 : A.getPoly())
            for (Monomial mono2 : B.getPoly()) {
                Monomial prod = new Monomial();
                prod.coeff = mono1.coeff * mono2.coeff;
                prod.power = mono1.power + mono2.power;
                P.getPoly().add(prod);
            }
        Collections.sort(P.getPoly());
        Polynomial P1 = new Polynomial();
        Monomial mono = new Monomial(-1, -1);
        for (Monomial mono1 : P.getPoly()) {
            if (mono.power == mono1.power) {

                mono.coeff = mono.coeff + mono1.coeff;
            } else {
                mono = mono1;
                P1.getPoly().add(mono);
            }
        }

        return P1;
    }

    // Differentiation function
    public static Polynomial Derivate(Polynomial A) {
        Polynomial A1 = A;
        for(Monomial mono1: A1.getPoly())
        {
            mono1.coeff = mono1.power*mono1.coeff;
            mono1.power = mono1.power - 1;
        }
        return A1;
    }

    // Integration function
    public static Polynomial Integrate(Polynomial A) {
        Polynomial A1 = A;
        for(Monomial mono1: A1.getPoly()){
            mono1.power = mono1.power + 1;
            mono1.coeff = mono1.coeff/mono1.power;

        }
        return A1;
    }

    //Division function that's not yet implemented
    public static Polynomial Division(Polynomial A,Polynomial B){
        Polynomial S=new Polynomial();
        S.getPoly().add(new Monomial(0,0));
        return S;
    }

}
