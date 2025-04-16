import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GamePanel extends JPanel implements WindowListener, ActionListener{
private static int leben;
private static int geld;
private static JLabel lebenLabel;
private static JLabel geldLabel;
public JButton kmin;
public Tower eistower;
private Fenster fenster; // Referenz auf Fenster

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
this.setBounds(1260, 20, 200, 100); // x, y, width, height
this.setVisible(true);

kmin = new JButton();
kmin.setLayout(null);
kmin.setSize(40, 20);
kmin.setLocation(0, 0);
//Button unsichtbar
kmin.setOpaque(true);
this.add(kmin);

//Button
JButton kmin = new JButton("Add Tower");
kmin.setBounds(10, 90, 100, 30);
kmin.addActionListener(e -> {
    Tower tower = new Tower(0); // Neuen Tower erzeugen
    tower.setBounds(300, 300, 100, 100); // Beispielposition und Größe
    fenster.addTower(tower); // Tower über Fenster hinzufügen
});
add(kmin);
repaint();
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

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowOpened(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowClosing(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowClosed(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowIconified(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowDeiconified(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowActivated(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowDeactivated(WindowEvent e) {
	// TODO Auto-generated method stub
	
}


}
