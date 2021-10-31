package Calculadora_Mermoz;

import static Calculadora_Mermoz.Main.v;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Insets;
/*/ experimentacion con bordes
import javax.swing.border.*;
import javax.swing.BorderFactory;
/*/
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.util.ArrayList;

public class Boton extends JButton implements ActionListener {

    Boton(String string, String color) {
        super(string);
//        Border borde = BorderFactory.createLineBorder(Color.lightGray, 3);
        super.setMargin(new Insets(0, 0, 0, 0));
//        super.setBorder(borde);
        addActionListener(this);
        switch (color) {
            case "yellow":
                super.setBackground(Color.yellow);
                super.setFont(new Font("Times New Roman", Font.BOLD, 15));
                //super.setForeground(new Color(255, 255, 255));
                break;
            case "negro":
                super.setBackground(new Color(0, 0, 0));
                super.setFont(new Font("Times New Roman", Font.BOLD, 15));
                super.setForeground(new Color(255, 255, 255));
                break;
            case "darkgrey":
                super.setBackground(new Color(128, 128, 128));
                super.setFont(new Font("Times New Roman", Font.BOLD, 15));
                break;
            case "red":
                super.setBackground(new Color(218, 64, 64)); //178.34.34
                super.setFont(new Font("Times New Roman", Font.BOLD, 15));
                break;
            case "green":
                super.setBackground(new Color(0, 150, 0));
                super.setFont(new Font("Times New Roman", Font.BOLD, 15));
                break;
            case "blue":
                super.setBackground(new Color(70, 130, 180));
                super.setFont(new Font("Times New Roman", Font.BOLD, 20));
                break;
            default:
                super.setBackground(Color.lightGray);
                super.setFont(new Font("Times New Roman", Font.BOLD, 15));
        }
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Pulsado: " + e.getActionCommand());
        //si el cartel de bienvenida esta activo, lo borro
        if (v.expresion.getText().equals("Welcome")) {
            v.expresion.setText("");
        }
        //de acuerdo al boton que fue presionado actuo
        switch (e.getActionCommand()) {
            case "C":
                v.expresion.setText("");
                v.muestraResultado.setText("");
                break;
            case "=":
                String resultado = "";
                boolean tipoRes = false; // false = OP LOGICA / true = OP ARITMETICA
                if (v.expresion.getText().equals("")){
                    v.muestraResultado.setText("Expresion vacia");
                    break;
                }
                for (String x : Operaciones.opLog) {
                    if (v.expresion.getText().contains(x)) {
                        tipoRes = true;
                    }
                }
                //if (tipoRes) {
                    try {
                        ShuntingYard sy = new ShuntingYard(v.expresion.getText());
                        ArrayList<String> prefija = sy.getPrefija();
                        System.out.print("PREFIJA: ");
                        for (String i : prefija) {
                            System.out.println(i + " ");
                        }

                        RPN r = new RPN(prefija);
                        resultado = String.valueOf(r.rpn());
                        System.out.println("\nResultado: " + r.rpn());
                    } catch (Exception f) {
                        v.muestraResultado.setText("-ERROR-");
                    }
                /*} else {
                    resultado = String.valueOf(Operaciones.resolverExpresion(v.expresion.getText()));
                }*/
                // si es logica muestro su valor de verdad
                if (tipoRes) {
                    if (Float.parseFloat(resultado) == 0) {
                        v.muestraResultado.setText("FALSO");
                    } else {
                        v.muestraResultado.setText("VERDADERO");
                    }

                } else {
                    if (resultado.equals("")) {
                        v.muestraResultado.setText("Error de tipeo");
                    } else {
                        v.muestraResultado.setText(resultado);
                    }
                }
                break;

            case "<-":
                if (v.expresion.getText().length() > 0) {
                    v.expresion.setText(v.expresion.getText().substring(0, v.expresion.getText().length() - 1));
                }
                break;
            case "MS":
                switch (v.muestraResultado.getText()) {
                    case "VERDADERO":
                        v.memoryStorage = "1";
                        break;
                    case "FALSO":
                        v.memoryStorage = "0";
                        break;
                    default:
                        v.memoryStorage = v.muestraResultado.getText();
                }
                v.ms.setBackground(new Color(0, 150, 0));
                break;
            case "MR":
                v.expresion.setText(v.expresion.getText() + v.memoryStorage);
                break;
            case "MC":
                v.memoryStorage = "";
                v.ms.setBackground(new Color(0, 0, 0));
                v.ms.setForeground(new Color(255, 255, 255));
                break;
            case "+/-":
                if (v.muestraResultado.getText().charAt(0) == '-') {
                    v.muestraResultado.setText(v.muestraResultado.getText().substring(1, v.muestraResultado.getText().length()));
                } else {
                    v.muestraResultado.setText("-" + v.muestraResultado.getText());
                }
                break;

            // el default esta para los botones que agregan a la expresion
            default:
                if (v.muestraResultado.getText().contains(".")) {
                    v.expresion.setText(v.muestraResultado.getText() + e.getActionCommand());
                    v.muestraResultado.setText("");
                } else {
                    v.expresion.setText(v.expresion.getText() + e.getActionCommand());
                    v.muestraResultado.setText("");
                }
        }

    }
}
