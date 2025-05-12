import javax.swing.*;
import java.awt.*;


public class Tower extends JPanel{
	public int posx=0;
	public int posy=0;
	public static int radius=50; //größe vom tower
	private JLabel En1;


	public int typ=0;
	

	public Tower(int typ, int posx, int posy) { //Es wird wenn der Tower gekauft wird mitgegeben, welcher Tower geplaced wird (gibt nur Eistower) und wo der Tower steht
		this.typ=typ;
		
		if (typ == 0) { // Hier wäre erweiterbar, Plustower usw.
			Minus(posx, posy); 
			}
		repaint();
		this.setVisible(true);
		
		
	}
	
	public void Minus(int posx, int posy) {	//Erstellt den Minus Tower
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		this.setOpaque(false);
		this.setSize(radius , radius ); // Kreis, kein Oval
		this.setLocation(posx, posy);
		
		En1 = new JLabel("-"); // Setzt ein - in den Tower
		En1.setLocation(((radius-10)/2),(radius-15)/2);
		En1.setSize(10, 10);
		En1.setForeground(Color.WHITE);
		En1.setFont(En1.getFont().deriveFont(30f));
		En1.setOpaque(false);
		this.add(En1);
		
	}

	protected void paintComponent(Graphics g) {		//Zeichnet den Kreis, das grafische Element zum Tower
		super.paintComponent(g); // Ruft paintComponent von JPanel auf, um das Panel zu initialisieren
		if (typ == 0) {
		    // Kreis zeichnen, wenn shapeType == 2
		    g.setColor(Color.BLUE); // Setze die Farbe des Kreises auf türkis
		    g.fillOval(0, 0, Tower.radius, Tower.radius); // Kreis an (posx, posy) mit dem Durchmesser h zeichnen
		}
		}
}
