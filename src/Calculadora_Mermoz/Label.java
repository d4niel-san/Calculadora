
package Calculadora_Mermoz;

import javax.swing.JLabel;

public class Label extends JLabel{
    Label (String texto, int x, int y, int ancho, int alto){
        super(texto);
        setBounds(x,y,ancho,alto);
        setFont(Fuente.Digital());
    }
        
}
