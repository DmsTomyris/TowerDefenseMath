import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static int leben;
    private static int geld;
    private static JLabel lebenLabel;
    private static JLabel geldLabel;

    public GamePanel() {
        leben = 100; // Startleben
        geld = 0;    // Startgeld

        // Setze Layout auf null
        setLayout(null);

        // Labels initialisieren
        lebenLabel = new JLabel("Leben: " + leben);
        geldLabel = new JLabel("Geld: " + geld);

        // Textfarbe für die Labels setzen
        lebenLabel.setForeground(Color.RED); // Helle Schriftfarbe
        geldLabel.setForeground(Color.YELLOW);  // Helle Schriftfarbe

        // Setze die Position und Größe der Labels
        lebenLabel.setBounds(10, 10, 100, 30); // x, y, width, height
        geldLabel.setBounds(10, 50, 100, 30);  // x, y, width, height

        // Hintergrundfarbe des Panels
        setBackground(new Color(30, 30, 30)); // Dunkelgrau für das gesamte Panel

        // Rahmen hinzufügen
        setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.gray));

        // Hinzufügen der Labels zum Panel
        add(lebenLabel);
        add(geldLabel);

        // Setze die Position und Größe des Panels
        this.setBounds(1260, 20, 200, 100); // x, y, width, height
        this.setVisible(true);
    }

    public static void setLeben(int schaden) {
        GamePanel.leben -= schaden;
        lebenLabel.setText("Leben: " + GamePanel.leben);
    }

    public static void setGeld(int geld) {
        GamePanel.geld += geld;
        geldLabel.setText("Geld: " + geld +"$");
    }

    public int getLeben() {
        return leben;
    }

    public int getGeld() {
        return geld;
    }
}