import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Tower extends JPanel implements WindowListener, ActionListener{
	public int posx;
	public int posy;
	private int radius=50;
	public int range;
	public int typ=0;
	

	public Tower(int typ) {
		this.typ=typ;
		
		if (typ == 0) {
			Minus();
			}
		
		
	}
	
	public void Minus() {
		this.setLayout(null);
		this.setBackground(Color.BLACK); // Das schwarze Hintergrund muss hier nicht mehr sein
		this.setOpaque(false); // Hiermit machen wir das Panel transparent
		this.setSize(100, 100);
		this.setLocation(posx, posy);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Ruft paintComponent von JPanel auf, um das Panel zu initialisieren
		
		if (typ == 0) {
		    // Kreis zeichnen, wenn shapeType == 2
		    g.setColor(Color.getHSBColor(63, 136, 143)); // Setze die Farbe des Kreises auf türkis
		    g.fillOval(0, 0, this.radius, this.radius); // Kreis an (0, 0) mit dem Durchmesser h zeichnen
		}
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
