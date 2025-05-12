import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class TowerRange extends JPanel{ //Ist ein Kreis der die Range des Towers festlegt
	public static int range=500; //reichweite vom tower


	public TowerRange(int range, int posx, int posy) { //Konstruktor
		this.setLayout(null);
		this.setBackground(Color.BLACK); // Das schwarze Hintergrund muss hier nicht mehr sein
		this.setOpaque(false);// Hiermit machen wir das Panel transparent
		this.setSize(range + 10, range + 10); // Panelgröße etwas größer als der Kreis
		this.setLocation(posx, posy);
		repaint();
		this.setVisible(true);
	}
	
	protected void paintComponent(Graphics g) {//Zeichnet einen Kreis
		 super.paintComponent(g); // Basisimplementierung aufrufen

	        Graphics2D g2d = (Graphics2D) g;// Graphics in Graphics2D umwandeln
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d.setColor(Color.WHITE);
	        g2d.setStroke(new BasicStroke(3)); // Randbreite auf 3 Pixel setzen
	        g2d.drawOval(0, 0, TowerRange.range, TowerRange.range); // Kreis: x, y, Breite, Höhe
    }}
	