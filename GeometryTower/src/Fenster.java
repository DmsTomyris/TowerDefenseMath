import javax.swing.*;

import java.awt.Component;
import java.awt.event.*;
import java.util.Random;

class Fenster extends JFrame implements WindowListener, ActionListener, Form.PanelListener {
private Form panel[];

private int framewidth = 1500;
private int frameheight = 800;

public JTextField Tg;
public int w = 0; // while counter
public int a = 5; // Anzahl der Panels

private Random random;

private GamePanel gamePanel; // Reference to GamePanel

JLayeredPane layeredPane = getLayeredPane();


public Fenster(String titel) {
super(titel);
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.setResizable(false);
this.setUndecorated(false);
this.setLayout(null);
this.setSize(framewidth, frameheight);

GamePanel gamePanel = new GamePanel(this);
layeredPane.add(gamePanel, Integer.valueOf(2));

//Add mouse listener for left-clicks
this.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        if (gamePanel.canAddTower() && SwingUtilities.isLeftMouseButton(e)) {
            Tower tower = new Tower(0); // Create a new Tower
            tower.setBounds(e.getX()-30, e.getY()-50, 50, 50); // Set tower position to mouse position
            tower.posx=e.getX()-30;
            tower.posy=e.getY()-50;
            
            System.out.println(tower.posx);
            System.out.println(tower.posy);
            
            TowerRange range = new TowerRange(tower.range, tower.posx, tower.posy);
            range.setBounds(e.getX()-30, e.getY()-50, 100, 100); // Set tower position to mouse position
            
            
            addTower(tower); // Add tower to the window
            gamePanel.resetTowerFlag(); // Reset the flag
        }
    }
});


random = new Random();

// LayeredPane für Hintergrund und Panels

// Hintergrundbild in JPanel setzen
JPanel backgroundPanel = new JPanel(null); // Absolutes Layout
backgroundPanel.setBounds(0, 0, framewidth, frameheight);

JLabel bildLabel = new JLabel(new ImageIcon("images/Map1.png"));
bildLabel.setBounds(0, 0, framewidth, frameheight); // Sicherstellen, dass es ganz links oben startet
bildLabel.setHorizontalAlignment(SwingConstants.LEFT);
bildLabel.setVerticalAlignment(SwingConstants.TOP);
backgroundPanel.add(bildLabel);
layeredPane.add(backgroundPanel, Integer.valueOf(0)); // Hintergrund in Ebene 0 setzen

// Panels auf Ebene 1 hinzufügen

JLabel euleLabel = new JLabel(new ImageIcon("images/Eule3.png"));
euleLabel.setBounds(0, 0, framewidth, frameheight); // gleiche Größe, gleiche Position
euleLabel.setHorizontalAlignment(SwingConstants.LEFT);
euleLabel.setVerticalAlignment(SwingConstants.TOP);

JPanel eulePanel = new JPanel(null); // Absolutes Layout
eulePanel.setBounds(0, 0, framewidth, frameheight);
eulePanel.setOpaque(false); // macht das Panel durchsichtig
eulePanel.add(euleLabel);

layeredPane.add(eulePanel, Integer.valueOf(1)); // Ebene über dem Hintergrund

GenerateWave(10, 1); // Gegnerzahl, Gegnerarten

}

// Methode zum Hinzufügen eines Towers
public void addTower(Tower tower) {
    layeredPane.add(tower, Integer.valueOf(3)); // Tower in Ebene 3 hinzufügen
    layeredPane.revalidate(); // Layout aktualisieren
    layeredPane.repaint(); // Panel neu zeichnen
    System.out.println("Tower wurde zum Fenster hinzugefügt.");
}

public void GenerateWave(int Gegnerzahl, int Gegnerarten) {
	w = 0; // while counter
	a = Gegnerzahl; // Anzahl der Panels
	panel = new Form[a];
	while (w < a) {
		//System.out.println("Mulm");
		panel[w] = new Form(w, random.nextInt(Gegnerarten)); // WElche Form
		panel[w].setPanelListener(this);
		layeredPane.add(panel[w], Integer.valueOf(1)); // Panels auf höhere Ebene setzen
		this.setVisible(true);

		try {
		Thread.sleep(1000);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}

		w += 1;
		}
}




@Override
public void windowOpened(WindowEvent e) {}

@Override
public void windowClosing(WindowEvent e) {
System.exit(0);
}

@Override
public void windowClosed(WindowEvent e) {}

@Override
public void windowIconified(WindowEvent e) {}

@Override
public void windowDeiconified(WindowEvent e) {}

@Override
public void windowActivated(WindowEvent e) {}

@Override
public void windowDeactivated(WindowEvent e) {}

@Override
public void actionPerformed(ActionEvent e) {}



@Override
public void onButtonClicked(int w) {
	// TODO Auto-generated method stub
	
}
}