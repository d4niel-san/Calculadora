package Calculadora_Mermoz;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Fuente {

    public static Font Digital() {
        String filename = "C:/Users/Daniel-San/Documents/NetBeansProjects/Swing/external_data/DSDIGI.TTF";
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
            font = font.deriveFont(Font.PLAIN, 20);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            return (font);

        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(Fuente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (Font.decode("PLAIN"));

    }

}
