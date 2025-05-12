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

public GamePanel(Fenster fenster) { //Panel um Leben und Geld anzuzeigen, und wo man den Tower kaufen kann
leben = 100; // Startleben
geld = 0; // Startgeld


//Setzt die grafischen Element und fügt die beschriftung hinzu
setLayout(null);
lebenLabel = new JLabel("Leben: " + leben);
geldLabel = new JLabel("Geld: " + geld);
lebenLabel.setForeground(Color.RED);
geldLabel.setForeground(Color.YELLOW);
lebenLabel.setBounds(10, 10, 100, 30);
geldLabel.setBounds(10, 50, 100, 30);
setBackground(new Color(30, 30, 30));
setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.gray));
add(lebenLabel);
add(geldLabel);
//Setzt die grafischen Element und fügt die beschriftung hinzu

// Setze die Position und Größe des Panels
this.setBounds(1260, 20, 200, 150); // x, y, width, height
this.setVisible(true);
//Setze die Position und Größe des Panels


//Button um den Tower hinzuzufügen
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
//Button um den Tower hinzuzufügen
}

protected void paintComponent(Graphics g) {// Malt den Miniaturkreis, um anzuzeigen wie der Eistower den man Kauft aussieht
	super.paintComponent(g); // Ruft paintComponent von JPanel auf, um das Panel zu initialisieren
	    // Kreis zeichnen, wenn shapeType == 2
	    g.setColor(Color.BLUE); // Setze die Farbe des Kreises auf türkis
	    g.fillOval(162, 90, 30, 30); // Kreis an (posx, posy) mit dem Durchmesser h zeichnen
	}


public boolean canAddTower() {//returnt ob grade erlaubt ist einen Tower hinzuzufügen
    return canAddTower;
}

public void resetTowerFlag() {//Toggeld Towerplacing Modus off
    canAddTower = false; // Reset the flag after adding a tower
}


public static void setLeben(int schaden) {//setter für Leben, überprüft auch ob der Spieler Tod ist, falls Lebel auf 0 geht
GamePanel.leben -= schaden;
if (GamePanel.leben <= 0) verloren();
lebenLabel.setText("Leben: " + GamePanel.leben);
}

public static void setGeld(int geld) {// setter fürs Geld
GamePanel.geld += geld;
geldLabel.setText("Geld: " + GamePanel.geld +"$");
}

public int getLeben() {// getter für Leben
return leben;
}

public int getGeld() {// getter für Geld
return geld;
}

public static void verloren() {//Ruft defeat_screen auf, siehe defeat_screen()
	Fenster.defeat_screen();
}

}
