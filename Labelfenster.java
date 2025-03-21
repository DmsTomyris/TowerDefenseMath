import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

class Fenster extends JFrame implements WindowListener, ActionListener{
	private JPanel panel;
    private JButton Be;
    private int g=2;
    private int h=2;
    
    //für bewegung
    private int x = 100;
    private int y = 50;
    private Timer timer;
    //für bewegung

	public JLabel Lg,Lh;
	public JTextField Tg,Th;
	public JButton Sub; //submit
	
	
	//Movement
	private void movePanelSmoothThread(int targetX, int targetY, int speed) {
	    new Thread(() -> {
	        while (x != targetX || y != targetY) {
	            if (x < targetX) x++;
	            if (x > targetX) x--;
	            if (y < targetY) y++;
	            if (y > targetY) y--;

	            panel.setLocation(x, y);
	            try {
	                Thread.sleep(speed); // Wartezeit zwischen Bewegungen
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }).start();
	}
	//Movement
	

	public Fenster(String titel) {
		super(titel);
		this.setLayout(null); // keine referenz auf einen Layout Manager gut so weil wir machen kein HTML in der Grundschule mit blinken und so
		this.setSize(700, 400);
		
		//Lg=new JLabel("Die Linie südlich des großen Flusses");
		//Lg.setLocation(5, 50);
		//Lg.setSize(300, 20);
		//this.add(Lg);
		
		Tg=new JTextField();
		Tg.setText("500");
		Tg.setLocation(10, 10);
		Tg.setSize(300, 20);
		Tg.addActionListener(e -> {
			System.out.println(Tg.getText()+" "+(g*h));
			this.remove(Tg); // Entfernen
			
			this.remove(Be);
			this.remove(panel); // Enter gedrückt → "Hallo"
			this.revalidate(); // Layout aktualisieren
			this.repaint();}); // Fenster neu zeichnen
		
		
		
		// JPanel als Teil der JFrame-Klasse
        panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setSize(g*20, h*20);
        panel.setLocation(x, y);
        panel.setLayout(null);

        this.add(panel); // JPanel zum JFrame hinzufügen
        this.setLayout(null); // Kein Layout-Manager
        this.setVisible(true);
        
        Be = new JButton("Berechne");
        Be.setSize(g*20, h*20);
    	Be.setLocation(100, 50);
    	this.add(Be);
    	Be.addActionListener(this);
    	this.addWindowListener(this);
    	repaint();
    	this.setVisible(true);

		
		
		
		
		
		//nur spaß
		//String bildPfad = "C:\\Users\\Teo Helmer\\eclipse-workspace\\Fensterprogramm\\src\\ressources\\png-clipart-dream-river-dream-river-thumbnail.png"; 
		//ImageIcon icon = new ImageIcon(bildPfad);
		//JLabel label = new JLabel(icon);  // Bild setzen
		//label.setText("Mulm"); // Optional: Text hinzufügen
		//label.setSize(500, 500); // Größe setzen
		//label.setLocation(0, 0); // Position setzen
		//this.add(label);
		//nur spaß
		
		this.addWindowListener(this);
		this.setVisible(true);
		
		
		movePanelSmoothThread(300, 50, 10);
		
		
		
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


	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if (e.getSource()==Be) {
			this.add(Tg); // add tf
			this.revalidate();
			this.repaint();}

		}
		
		
	}
//Mulm
