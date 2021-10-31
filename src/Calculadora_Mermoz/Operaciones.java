package Calculadora_Mermoz;

// Clase del algoritmo original, no se usa en la calculadora final
class Operaciones {

    public static final String opLog[] = {"<", ">", ">=", "<=", "==", "!=", "|", "&", "||", "&&"}; // array de validacion de operaciones logicas
    public static final String opMat[] = {"*", "/", "-", "+", "%"}; //array de validacion de operaciones mat
    public static final String op[][] = {opMat, opLog};

    /*
Recibe una cadena que contiene una expresion compleja
Recibe Strings de la forma: (3+2)+(2*5)+4+(4*2)+(2+1)
Devuelve float de la forma: 20
     */
    public static float resolverExpresion(String cadena) {
        int indexI, indexF;
        while (cadena.contains("(")) {
            String aux, aux2 = new String();
            boolean flag = false;
            indexI = cadena.indexOf('(');
            indexF = cadena.indexOf(')');
            aux = cadena.substring(indexI + 1, indexF);
            System.out.println("Cadena que se envia: " + aux);//prueba se puede eliminar una vez que funcione
            aux = convertirString(aux);
            System.out.println("Cadena que se recibe: " + aux);//prueba se puede eliminar una vez que funcione");
            for (int i = 0; i < cadena.length(); i++) {
                if (i < indexI || i > indexF) {
                    aux2 += cadena.charAt(i);
                } else if (!flag) {
                    aux2 += aux;
                    flag = true;
                }

            }
            cadena = aux2;
            System.out.println("Cadena despues de analizar: " + cadena);
        }

        for (String[] x : op) { //va recorriendo todas las operaciones posibles, empezando por las matematicas
            for (int j = 0; j < x.length; j++) {    //recorre todas las operaciones del array 
                while (cadena.contains(x[j])) {    //mientras la cadena contenga el simbolo del operando x[j]
                    int indiceOperando, indicePrimerDigito = 0, indiceUltimoDigito = 0;
                    String aux, aux2, resultado;
                    aux2 = new String();
                    boolean flag = false;
                    indiceOperando = cadena.indexOf(x[j]);  // guardo la posicion del operando 32+323.52*786.36
                    for (int i = indiceOperando - 1; i >= 0; i--) {  //identifica la posicion en la cadena completa del primer digito del numero
                        if ((!(Character.isDigit(cadena.charAt(i)) || cadena.charAt(i) == '.'))) {
                            indicePrimerDigito = i + 1;
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) { // Si no encuentra otro operador antes se le pone el 0 como posicion del primer digito
                        indicePrimerDigito = 0;
                    } else {
                        flag = false;
                    }
                    for (int i = indiceOperando + 1; i < cadena.length(); i++) { //identifica la posicion en la cadena completa del ultimo ddigito del numero
                        if ((!(Character.isDigit(cadena.charAt(i)) || cadena.charAt(i) == '.' || i == indiceOperando + 1))) { // la ultima condicion es para que no tome como flaso verdadero a los operandos dobles como el &&
                            indiceUltimoDigito = i;
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        indiceUltimoDigito = cadena.length(); // si no encontro otro operador despues se le pone el ultimo caracter de la cadena como posicion del ultimo digito
                    }
                    /// Inicio de Codigo de prueba
                    System.out.println("Indice Operando: " + indiceOperando);
                    System.out.println("Indice Primer Digito: " + indicePrimerDigito);
                    System.out.println("Indice Ultimo Digito: " + indiceUltimoDigito);
                    /// Fin de codigo de prueba
                    aux = cadena.substring(indicePrimerDigito, indiceUltimoDigito);
                    /// Inicio de Codigo de Prueba
                    System.out.println("Subcadena a procesar: " + aux);
                    /// Fin de Codigo de Prueba
                    resultado = convertirString(aux);
                    /// Inicio de codigo de prueba
                    System.out.println("El resultado devuelto es: " + resultado);
                    /// Fin de Codigo de Prueba
                    for (int i = 0; i < cadena.length(); i++) { /// Iteracion para reemplazar el String resultado en la cadena
                        if (i == indicePrimerDigito) {
                            aux2 += resultado;
                            i = indiceUltimoDigito - 1;
                        } else {
                            aux2 += cadena.charAt(i);
                        }
                    }
                    cadena = aux2;
                    // Inicio de Codigo de Prueba
                    System.out.println("La cadena terminada queda: " + cadena);
                    // Fin de codigo de prueba

                }
            }
        }
        System.out.println(cadena);
        return (Float.valueOf(cadena));
    }

    /*
Recibe una cadena que contiene una operacion aritmetico/logica simple sin parentesis y identifica
Recibe Strings de la forma: 4*5 
Devuelve String de la forma: 20
     */
    public static String convertirString(String cadena) {
        String signo = null;
        String[] ret = {"", "", ""};
        float a = 0, b = 0;
        for (int i = 0; i < cadena.length(); i++) { //for que busca el signo de la expresion simple
            /// if el caracter no es digito o no es '.' y ademas no se guardo ningun signo
            if (!(Character.isDigit(cadena.charAt(i)) || (cadena.charAt(i) == '.')) && signo == null) {
                ///if el siguiente caracter es digito  3>=5

                if (Character.isDigit(cadena.charAt(i + 1))) {
                    signo = String.valueOf(cadena.charAt(i));
                } else { ///o si el siguiente caracter forma parte del operando
                    signo = cadena.substring(i, i + 2);
                }

            }
        }
        if (signo == null) {    //si se da el caso de que se ingresa (3) esto devuelve la cadena sin operar
            return (cadena);
        }
        ret[0] = cadena.substring(0, cadena.indexOf(signo.charAt(0))); // grabo en ret(0) el primer valor
        if (signo.length() % 2 == 0) {  // discrimino si el operando tiene 1 o 2 caracteres
            ret[1] = cadena.substring(cadena.indexOf(signo.charAt(0)) + 2, cadena.length()); //grabo en ret(1) el segundo valor 
        } else {
            ret[1] = cadena.substring(cadena.indexOf(signo.charAt(0)) + 1, cadena.length()); //grabo en ret(1) el segundo valor 
        }
        ret[2] = signo; //guardo el signo

        // inicio codigo de prueba
        System.out.printf("SIGNO: " + signo + "\n");
        System.out.println("RET");
        for (int i = 0; i < 3; i++) {
            System.out.printf(i + " " + ret[i] + "\n");
        }
        // fin codigo de prueba
        for (String x : opLog) {
            if (signo.equals(x)) {
                boolean res = operacionesLog(ret);
                if (res) {
                    return ("1");
                }
                return ("0");
            }
        }
        return (String.valueOf(operacionesMat(ret)));

    }

    /*
Resuelve una operacion matematica 
Recibe String[] con el siguiente formato
String[0] = 4
String[1] = 5
String[2] = *
Devuelve float de la forma: 20
     */
    public static float operacionesMat(String[] cadenaDesarmada) {
        float a = Float.parseFloat(cadenaDesarmada[0]); //a = primer valor
        float b = Float.parseFloat(cadenaDesarmada[1]); //b = segundo valor
        String signo = cadenaDesarmada[2];
        switch (signo.charAt(0)) { //operaciones
            case '+':
                return (a + b);
            case '-':
                return (a - b);
            case '/':
                return (a / b);
            case '*':
                return (a * b);
            case '%':
                return (a % b);
            default:
                System.out.println("error!");
                return (123);
        }

    }

    /*
Resuelve una operacion logica
Recibe String[] con el siguiente formato
String[0] = !1
String[1] = 5
String[2] = <
Devuelve boolean de la forma: true
     */
    public static boolean operacionesLog(String[] cadenaDesarmada) {
        float a = Float.parseFloat(cadenaDesarmada[0]);
        float b = Float.parseFloat(cadenaDesarmada[1]);
        boolean boola = false;
        boolean boolb = false;
        if (a >= 1) {
            boola = true;
        }
        if (b >= 1) {
            boolb = true;
        }
        String signo = cadenaDesarmada[2];
        switch (signo.charAt(0)) { //operaciones
            case '<':
                if (signo.contains("=")) {
                    return (a <= b);
                } else {
                    return (a < b);
                }
            case '>':
                if (signo.contains("=")) {
                    return (a >= b);
                } else {
                    return (a > b);
                }
            case '=':
                return (a == b);
            case '!':
                return (a != b);
            case '|':
                return (boola || boolb);
            case '&':
                return (boola && boolb);
            default:
                System.out.println("error!");
                return (false);
        }
    }

    /*
    recibe un valor y lo niega aritmetica o logicamente
    recibe: -4 o !1
    devuelve 4 o 0
     */
    public String negacionMat(String valor) {
        if (valor.charAt(0) == '-') {
            return (valor.substring(1, valor.length()));
        } else {
            return ("-" + valor);
        }
    }

    public String negacionLog(String valor) {
        if (valor.charAt(0) == '!') {
            if (valor.charAt(1) == 0) {
                return ("1");
            } else {
                return ("0");
            }
        } else {
            return ("!" + valor);
        }
    }

}
