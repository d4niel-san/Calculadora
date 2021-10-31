package Calculadora_Mermoz;

public class Main {

    public static String expresion = new String();
    public static final String op[][] = {Operaciones.opLog, Operaciones.opMat}; // array de validacion de operaciones
    public static Ventana v = new Ventana();

    public static void main(String[] args) {

        expresion = "2+3";
        float resultado = 0;
        boolean resLog = false, tipoRes = false;
///experimentacion ventana
        v.setVisible(true);
///fin experimentacion ventana
        try {
            resultado = Operaciones.resolverExpresion(expresion);
        } catch (Exception e) {
            v.resultado.setText("ERROR");
        }

        for (String x : Operaciones.opLog) {
            if (expresion.contains(x)) {
                tipoRes = true;
            }
        }

        if (tipoRes) {
            if (resultado != 0) {
                System.out.println("VERDADERO");
            } else {
                System.out.println("FALSO");
            }
        } else {
            System.out.println(resultado);
        }
    }
}
