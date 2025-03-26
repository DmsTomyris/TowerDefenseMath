import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;

public class Form extends JPanel implements WindowListener, ActionListener{
    public int g=15; //public weil brauchen es in Fenster
    public int h=10; //public weil brauchen es in Fenster.; Mindestens 25 sonst zu klein
    private int x = 100;
    private int y = 50;
    public JButton Be;
    private JTextField Tg;
    private JLabel En1;
    private JLabel En2;
    
    private int shapeType = 0; // 0 für Rechteck, 1 für Dreieck
    
    
    private PanelListener listener; // Interface für die Kommunikation


	
	public Form(int w, int was) {
		if (was == 0) {
			Rechteck();
		}
		else if (was==1) {
			Dreieck();
		}
		
		shapeType=was; // Setze den Typ (0 = Rechteck, 1 = Dreieck)
		
        //this.add(panel);
        movePanelSmoothThread(1000, 200, 20);
    	repaint();
    	this.setVisible(true);
    	addButton(w);
    	
    	En1 = new JLabel(String.valueOf(h));
    	En1.setLocation(0, h*10 /2-10);
		En1.setSize(40, 20);
		En1.setForeground(Color.WHITE);
		En1.setOpaque(false);
		this.add(En1);
		
		En2 = new JLabel(String.valueOf(g));
		En2.setLocation((g*10-20)/2, h*10-20);
		En2.setSize(40, 20);
		En2.setForeground(Color.WHITE);
		En2.setOpaque(false);
		this.add(En2);
		repaint();
	}
	
	public void addButton(int w) {
	System.out.println(w);
	Be = new JButton();
	Be.setLayout(null);
    Be.setSize(g*10, h*10);
	Be.setLocation(0, 0);
	//Button unsichtbar
	Be.setOpaque(false);
	Be.setContentAreaFilled(false);
	Be.setBorderPainted(false);
	//Button unsichtbar
	this.add(Be);
	Be.addActionListener(e -> {
        if (listener != null) {
            listener.onButtonClicked(w); // Event auslösen
            
        }
        Be.setVisible(false);
		this.revalidate();
		this.repaint();
    });
	}
	
	public void Rechteck() {
	    this.setLayout(null);
	    this.setBackground(Color.BLACK); // Das schwarze Hintergrund muss hier nicht mehr sein
	    this.setOpaque(false); // Hiermit machen wir das Panel transparent
	    this.setSize(g * 10, h * 10);
	    this.setLocation(x, y);
	}
	
	public void Dreieck() {
	    this.setLayout(null);
	    this.setBackground(Color.BLACK); // Das schwarze Hintergrund muss hier nicht mehr sein
	    this.setOpaque(false); // Hiermit machen wir das Panel transparent
	    this.setSize(g * 10, h * 10);
	    this.setLocation(x, y);
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Ruft paintComponent von JPanel auf, um das Panel zu initialisieren

        if (shapeType == 0) {
            // Rechteck zeichnen, wenn shapeType == 0
            g.setColor(Color.RED); // Setze die Farbe des Rechtecks auf rot
            g.fillRect(0, 0, this.g* 10, this.h* 10); // Rechteck an (50, 50) mit der Breite g und der Höhe h zeichnen
        } 
        else if (shapeType == 1) {
            // Dreieck zeichnen, wenn shapeType == 1
            int[] xPoints = {0, 0 + this.g / 2* 10, 0 + this.g* 10}; // Koordinaten der Ecken des Dreiecks
            int[] yPoints = {0 + this.h* 10, 0, 0 + this.h* 10};  // Höhe und Basis des Dreiecks
            g.setColor(Color.GREEN); // Setze die Farbe des Dreiecks auf grün
            g.fillPolygon(xPoints, yPoints, 3); // Gefülltes Dreieck zeichnen
        }
    }
	public void defeated() {
		
		
		//was passiert wenn gelöscht
	
	}
	
	private void movePanelSmoothThread(int targetX, int targetY, int speed) { //bewegung - Todo -> Route
	    new Thread(() -> {
	        while (x != targetX || y != targetY) {
	            if (x < targetX) x++;
	            if (x > targetX) x--;
	            if (y < targetY) y++;
	            if (y > targetY) y--;

	            this.setLocation(x, y);
	            try {
	                Thread.sleep(speed); // Wartezeit zwischen Bewegungen
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }).start();
	}
	
	
	
	public void setPanelListener(PanelListener listener) {
        this.listener = listener;
    }

    // Interface für das Event
    public interface PanelListener {
        void onButtonClicked(int w);
    }
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
			
			
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
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