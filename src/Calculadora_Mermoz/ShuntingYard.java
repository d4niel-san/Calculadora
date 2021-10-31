package Calculadora_Mermoz;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShuntingYard {

    private String operacion;
    private ArrayList<String> prefija, tokens;

    public ShuntingYard(String operacion) throws Excepciones {
        this.operacion = operacion;
        while (this.operacion.contains("(")) {
            int indexI = 0, indexF = 0;
            String aux2 = "";
            for (int i = 0; i < this.operacion.length(); i++) {
                if (this.operacion.charAt(i) == '(') {
                    indexI = i; //1+(2+(3+3)-3)
                }
            }
            for (int i = this.operacion.length() - 1; i > indexI; i--) {
                if (this.operacion.charAt(i) == ')') {
                    indexF = i; //1+(2+(3+3)-3)
                }
            }
            //System.out.println("PARENTESIS: " + operacion.substring(indexI+1, indexF));
            ShuntingYard aux = new ShuntingYard(this.operacion.substring(indexI + 1, indexF));
            RPN r = new RPN(aux.prefija);
            System.out.println("Resultado paren: " + r.rpn());
            for (int i = 0; i < this.operacion.length(); i++) {
                if (i < indexI) {
                    aux2 += this.operacion.charAt(i);
                } else {
                    aux2 += r.rpn();
                    //aux2 += Math.round(r.rpn());
                    aux2 += this.operacion.substring(indexF + 1);
                    break;
                }
            }
            this.operacion = aux2;
            System.out.println("oper despues paren: " + this.operacion);
            System.out.println("aux2: " + aux2);
        }
        convertirUnarios();
        prefija = new ArrayList<>();
        this.tokens = new ArrayList();
        this.tokenizar();
        this.shuntingYard();
    }
/// (3+56)*2  ( 3 + 56 ) * 2 

    private void tokenizar() {
        String patron = "(?<token>[\\(]|\\d+|[-+\\*/%^]|[\\)]|[~]|[.]|[=!<>][=]|[!]|[<>]|&{2}|\\|{2})";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(this.operacion);

        String token;

        while (matcher.find()) {
            token = matcher.group("token");
            tokens.add(token);
        }

        for (int i = 0; i < tokens.size(); i++) {
            System.out.println("TOKENS: " + tokens.get(i) + " ");
        }

    }

    private void shuntingYard() throws Excepciones {
        String token;
        int contador = 0;
        Stack pila = new Stack();
        for (int i = 0; i < tokens.size(); i++) {
            token = tokens.get(i);
            if (token.matches("\\d+")) {
                if ((i + 2) <= tokens.size()) {
                    if (tokens.get(i + 1).equals(".")) {
                        prefija.add(token + "." + tokens.get(i + 2));
                        i = i + 2;
                    } else {
                        prefija.add(token);
                    }
                } else {
                    prefija.add(token);
                }

            } // new code
            else if (token.matches("~")) {  // -55.22 - 55 . 22
                i = invertirSigno(i);
            } // end new code
            else if (token.equals('(')) {
                pila.push(token);
                contador++;
            } else if (token.matches("[-+\\*/%^]|[=!<>][=]|[<>]|&{2}|\\|{2}|[)]")) {
                if (!pila.empty()) {
                    if (compararPresedencia(token, (String) pila.peek())) {
                        while (!pila.empty()) {
                            prefija.add((String) pila.pop());
                        }
                        pila.push(token);
                    } else {
                        pila.push(token);
                    }
                } else {
                    pila.push(token);
                }
            } else if (token.equals(")")) {
                contador--;
                if (!pila.empty()) {
                    while (true) {
                        if (!pila.empty()) {
                            if (!pila.peek().equals("(")) {
                                prefija.add((String) pila.pop());
                            } else {
                                pila.pop();
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                } else {
                    throw new Excepciones("Parentesis de cierre sobrante");
                }
            } else if (contador < 0) {
                throw new Excepciones("Parentesis de apertura Sobrante");
            } else if (token.matches("!")) {  
                double auxdouble = 0;
                String aux = "";
                if (tokens.get(i + 1).equals('~')) {
                    i = invertirSigno(i);
                }
                if (i + 2 != tokens.size()) {
                    if (tokens.get(i + 2).equals(".")) { // si el subsiguiente al not es un punto
                        aux = tokens.get(i + 1) + "." + tokens.get(i + 3);
                        i = i + 3; // me posisiono en el ultimo digito agregado para continuar el for desde ahi
                    }
                } else {
                    aux = tokens.get(i + 1); //si estoy en el borde solo tengo - digitos
                    i++; //me posiciono en el sig para continuar el for
                }
                auxdouble = Double.parseDouble(aux);
                if (auxdouble >= 1) {
                    prefija.add("0");
                } else {
                    prefija.add("1");
                }
            }
        }

        while (!pila.empty()) {
            if (!pila.peek().equals("(")) {
                prefija.add((String) pila.pop());
            } else {
                throw new Excepciones("Parentesis de apertura Sobrante");
            }
        }
    }

    private boolean compararPresedencia(String opExp, String opPila) {
        return darPresedencia(opExp) < darPresedencia(opPila);
    }

    private int darPresedencia(String op) {
        switch (op) {
            case "*":
            case "/":
            case "%":
                return (6);
            case "+":
            case "-":
                return (5);
            case "<":
            case "<=":
            case ">":
            case ">=":
                return (4);
            case "==":
            case "!=":
                return (3);
            case "&&":
                return (2);
            case "||":
                return (1);
            default:
                return (0);
        }
    }

    public ArrayList<String> getPrefija() {
        return prefija;
    }
/*
    agrega el - al token del numero
*/
    public int invertirSigno(int i) {
        if (i + 2 != tokens.size()) {
            if (tokens.get(i + 2).equals(".")) {
                prefija.add("-" + tokens.get(i + 1) + "." + tokens.get(i + 3));
                i = i + 3;
            } else {
                prefija.add("-" + tokens.get(i + 1));
                i++;
            }
        } else {
            prefija.add("-" + tokens.get(i + 1));
            i++;
        }
        return (i);
    }
/*
    Convierte - -> ~
*/
    public void convertirUnarios() {
        for (int i = 0; i < operacion.length(); i++) {
            if (operacion.charAt(i) == '-') {
                if (i != 0) {
                    if (!Character.isDigit(operacion.charAt(i - 1))) {
                        operacion = operacion.substring(0, i) + "~" + operacion.substring(i + 1, operacion.length());
                    }
                } else {
                    operacion = "~" + operacion.substring(1);
                }
            }
        }
        System.out.println("REGEX CONVERTIDA: " + operacion);
    }

}
