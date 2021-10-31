package Calculadora_Mermoz;

import javax.swing.JTextField;

public class CampoDeTxt extends JTextField {

    CampoDeTxt(String texto, int posicionCursor, int x, int y, int ancho, int alto) {
        super(texto);
        setHorizontalAlignment(JTextField.RIGHT);
        setFont(Fuente.Digital());
        setBounds(x,y,ancho,alto);
        switch (texto) {
            case "Welcome":
                setCaretPosition(7);
                break;
            case "0":
                setEditable(false);
        }

    }

}
