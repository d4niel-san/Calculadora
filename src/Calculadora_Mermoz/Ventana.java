package Calculadora_Mermoz;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.*;

public class Ventana extends JFrame {

    CampoDeTxt expresion, muestraResultado;
    JButton mc, mr, ms, and, or, not, noIgual, mayorI, menorI, igual, mayor, menor;
    JButton abreParen, cierraParen, cero, uno, dos, tres, cuatro, cinco, seis, siete, ocho, nueve;
    JButton surp, masMenos, multiplicacion, division, mas, menos, ce, resultado;
    GridLayout layout = new GridLayout(4, 8);
    JPanel cuadricula = new JPanel(layout);
    Label labelExpresion, labelIgual;
    String memoryStorage, memoriaPrincipal;

    public Ventana() {

        super("Calculadora - TP MERMOZ, DANIEL");

//configuracion de ventana
        setLayout(null);
        setFont(Font.decode("BOLDITALIC"));
        ImageIcon img = new ImageIcon("C:/Users/Daniel-San/Documents/NetBeansProjects/Swing/external_data/icocalcu.png");
        setIconImage(img.getImage());
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(10, 10);
// Creacion de los TExtfields*/
// Creacion y configuracion del text field de expresion
        expresion = new CampoDeTxt("Welcome", 7, 90, 10, 250, 40);
        add(expresion);
// Creacion de Textfield de =
        muestraResultado = new CampoDeTxt("0", 0, 190, 60, 150, 30);
        add(muestraResultado);
// Creacion de los Labels
        labelExpresion = new Label("EXPR: ", 20, 10, 80, 40);
        labelIgual = new Label("=", 180, 60, 10, 30);
        add(labelIgual);
        add(labelExpresion);
// creacion del frame de botones
        layout.setHgap(5);
        layout.setVgap(5);
        JPanel cuadricula = new JPanel(layout);
        cuadricula.setBounds(10, 100, 325, 150);
        add(cuadricula);
        cuadricula.add(mc = new Boton("MC", "negro"));
        cuadricula.add(mr = new Boton("MR", "negro"));
        cuadricula.add(ms = new Boton("MS", "negro"));
        cuadricula.add(siete = new Boton("7", "blue"));
        cuadricula.add(ocho = new Boton("8", "blue"));
        cuadricula.add(nueve = new Boton("9", "blue"));
        cuadricula.add(surp = new Boton("<-", "yellow"));
        cuadricula.add(masMenos = new Boton("+/-", ""));
        cuadricula.add(and = new Boton("&", ""));
        cuadricula.add(or = new Boton("|", ""));
        cuadricula.add(not = new Boton("!", ""));
        cuadricula.add(cuatro = new Boton("4", "blue"));
        cuadricula.add(cinco = new Boton("5", "blue"));
        cuadricula.add(seis = new Boton("6", "blue"));
        cuadricula.add(multiplicacion = new Boton("*", ""));
        cuadricula.add(division = new Boton("/", ""));
        cuadricula.add(noIgual = new Boton("!=", ""));
        cuadricula.add(mayorI = new Boton(">=", ""));
        cuadricula.add(menorI = new Boton("<=", ""));
        cuadricula.add(uno = new Boton("1", "blue"));
        cuadricula.add(dos = new Boton("2", "blue"));
        cuadricula.add(tres = new Boton("3", "blue"));
        cuadricula.add(mas = new Boton("+", ""));
        cuadricula.add(menos = new Boton("-", ""));
        cuadricula.add(igual = new Boton("==", ""));
        cuadricula.add(mayor = new Boton(">", ""));
        cuadricula.add(menor = new Boton("<", ""));
        cuadricula.add(abreParen = new Boton("(", "darkgrey"));
        cuadricula.add(cero = new Boton("0", "blue"));
        cuadricula.add(cierraParen = new Boton(")", "darkgrey"));
        cuadricula.add(ce = new Boton("C", "green"));
        cuadricula.add(resultado = new Boton("=", "red"));
        cuadricula.setVisible(true);

    }

}
