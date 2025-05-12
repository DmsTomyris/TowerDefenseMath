import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel  {
private static int leben;
private static int geld;
private static int towercost=-100;
private static JLabel lebenLabel;
private static JLabel geldLabel;
public JButton kmin;
public Tower eistower;
private boolean canAddTower = false; // Flag to check if we can add a tower

public GamePanel(Fenster fenster) {
leben = 100; // Startleben
geld = 0; // Startgeld

// Setze Layout auf null
setLayout(null);

// Labels initialisieren
lebenLabel = new JLabel("Leben: " + leben);
geldLabel = new JLabel("Geld: " + geld);

// Textfarbe für die Labels setzen
lebenLabel.setForeground(Color.RED); // Helle Schriftfarbe
geldLabel.setForeground(Color.YELLOW); // Helle Schriftfarbe

// Setze die Position und Größe der Labels
lebenLabel.setBounds(10, 10, 100, 30); // x, y, width, height
geldLabel.setBounds(10, 50, 100, 30); // x, y, width, height

// Hintergrundfarbe des Panels
setBackground(new Color(30, 30, 30)); // Dunkelgrau für das gesamte Panel

// Rahmen hinzufügen
setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.gray));

// Hinzufügen der Labels zum Panel
add(lebenLabel);
add(geldLabel);

// Setze die Position und Größe des Panels
this.setBounds(1260, 20, 200, 150); // x, y, width, height
this.setVisible(true);


//Button
kmin = new JButton("Minus-Turm 100$");
kmin.setBounds(10, 90, 150, 30);
kmin.addActionListener(e -> {
	if (getGeld() >= 100) {
		setGeld(towercost);
		canAddTower = true; // Set flag to true when button is pressed
}});
add(kmin);
repaint();
this.setVisible(true);


}

protected void paintComponent(Graphics g) {
	super.paintComponent(g); // Ruft paintComponent von JPanel auf, um das Panel zu initialisieren
	
	    // Kreis zeichnen, wenn shapeType == 2
	    g.setColor(Color.BLUE); // Setze die Farbe des Kreises auf türkis
	    g.fillOval(162, 90, 30, 30); // Kreis an (posx, posy) mit dem Durchmesser h zeichnen
	
	}

public boolean canAddTower() {
    return canAddTower;
}

public void resetTowerFlag() {
    canAddTower = false; // Reset the flag after adding a tower
}


public static void setLeben(int schaden) {
GamePanel.leben -= schaden;
if (GamePanel.leben <= 0) verloren();
lebenLabel.setText("Leben: " + GamePanel.leben);
}

public static void setGeld(int geld) {
GamePanel.geld += geld;
geldLabel.setText("Geld: " + GamePanel.geld +"$");
}

public int getLeben() {
return leben;
}

public int getGeld() {
return geld;
}

public static void verloren() {
	Fenster.defeat_screen();
}

}
