package Calculadora_Mermoz;

import java.util.ArrayList;
import java.util.Stack;

public class RPN {

    private ArrayList<String> prefija;
    private Stack<String> pila;

    public RPN(ArrayList<String> prefija) {
        this.prefija = prefija;
        this.pila = new Stack();
    }

    public double rpn() throws Excepciones {
        String eleDer, eleIzq;
        System.out.print("PREFIJA: ");
        for (String token : prefija) {
            System.out.print(token + " ");
        }
        for (String token : prefija) {
            if (esOperador(token)) {
                eleDer = pila.pop();
                if (pila.isEmpty()) {
                    eleIzq = "0";
                    //throw new Excepciones("Elemento Faltante");
                } else {
                    eleIzq = pila.pop();
                }
                double resultado = operar(eleIzq, token, eleDer);
                pila.push("" + resultado);
            } else {
                pila.push(token);
            }
        }
        return Double.parseDouble(pila.pop());
    }

    private boolean esOperador(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")
                || token.equals("%") || token.equals("<") || token.equals("<=") || token.equals(">")
                || token.equals(">=") || token.equals("==") || token.equals("!=") || token.equals("||") || token.equals("&&");
    }

    private double operar(String eleIzq, String operador, String eleDer) {
        double a = Double.parseDouble(eleIzq);
        double b = Double.parseDouble(eleDer);
        boolean boola = false;
        boolean boolb = false;
        if (a != 0) {
            boola = true;
        }
        if (b != 0) {
            boolb = true;
        }
        switch (operador) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            case "%":
                return a % b;
            case "^":
                return Math.pow(a, b);
            case "<":
                if (a < b) {
                    return (1);
                } else {
                    return (0);
                }
            case "<=":
                if (a <= b) {
                    return (1);
                } else {
                    return (0);
                }
            case ">":
                if (a > b) {
                    return (1);
                } else {
                    return (0);
                }
            case ">=":
                if (a >= b) {
                    return (1);
                } else {
                    return (0);
                }
            case "==":
                if (a == b) {
                    return (1);
                } else {
                    return (0);
                }
            case "!=":
                if (a != b) {
                    return (1);
                } else {
                    return (0);
                }
            case "||":
                if (boola || boolb) {
                    return (1);
                } else {
                    return (0);
                }
            case "&&":
                if (boola && boolb) {
                    return (1);
                } else {
                    return (0);
                }
            default:
                return (-1);
        }
    }
}
