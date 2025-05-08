import javax.swing.*;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.*;
import java.util.Random;

class Fenster extends JFrame implements WindowListener, ActionListener, Form.PanelListener {
private Form panel[];

private int framewidth = 1500;
private int frameheight = 800;

public JTextField Tg;
public int w = 0; // while counter
public int a = 5; // Anzahl der Panels

public int defeat = 0;

private Random random;

private GamePanel gamePanel; // Reference to GamePanel

public static int[][] towerpos=new int[12][2];
public static int towerzahl =0;

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
            tower.posx=(e.getX())-6;
            tower.posy=(e.getY())-29;
            towerpos[towerzahl][0]=tower.posx;
            towerpos[towerzahl][1]=tower.posy;
            tower.setBounds(tower.posx-(tower.radius/2), tower.posy-(tower.radius/2), tower.radius, tower.radius); // Set tower position to mouse position
            //System.out.println(e.getX() + "xx");
            //System.out.println(e.getY()+ "yy");
            //tower.posx=e.getX()-30;
            //tower.posy=e.getY()-50;
            
            System.out.println(tower.posx+ "towerX");
            System.out.println(tower.posy+ "towerY");
            
            TowerRange range = new TowerRange(TowerRange.range, tower.posx, tower.posy);
            range.setBounds(tower.posx-(TowerRange.range/2), tower.posy-(TowerRange.range/2), TowerRange.range, TowerRange.range); // Set tower position to mouse position
            
            
            addTower(tower); // Add tower to the window
            addTowerRange(range);
            
            gamePanel.resetTowerFlag(); // Reset the flag
            towerzahl+=1;
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
eulePanel.setBounds(1100, 300, framewidth, frameheight);
eulePanel.setOpaque(false); // macht das Panel durchsichtig
eulePanel.add(euleLabel);



JLabel bubbleLabel = new JLabel(new ImageIcon("images/sprechblase1.png"));
bubbleLabel.setBounds(0, 0, framewidth, frameheight); // gleiche Größe, gleiche Position
bubbleLabel.setHorizontalAlignment(SwingConstants.LEFT);
bubbleLabel.setVerticalAlignment(SwingConstants.TOP);

JPanel bubblePanel = new JPanel(null); // Absolutes Layout
bubblePanel.setBounds(700, 100, framewidth, frameheight);
bubblePanel.setOpaque(false); // macht das Panel durchsichtig
bubblePanel.add(bubbleLabel);



JLabel warnLabel = new JLabel("Achtung!! Wir werden überfallen!!!");
warnLabel.setBounds(800, 230, 400, 120); // Position und Größe
warnLabel.setFont(new Font("Arial", Font.BOLD, 24));
warnLabel.setOpaque(true);             // Hintergrund sichtbar machen (optional)
warnLabel.setBackground(java.awt.Color.WHITE); // z. B. für Sichtbarkeit

// Auf Layer 3 hinzufügen

GenerateWave(1, 3); // Gegnerzahl, Gegnerarten
//Die erste Zeile ausführen
GenerateWave(3, 1);

//Kurze Pause nach der ersten Zeile (z.B. 1000 ms)
Timer firstPause = new Timer(1000, new ActionListener() {
 public void actionPerformed(ActionEvent e) {
     // Die restlichen Zeilen nach der Pause ausführen
     layeredPane.add(eulePanel, Integer.valueOf(1));
     layeredPane.add(bubblePanel, Integer.valueOf(2));
     layeredPane.add(warnLabel, Integer.valueOf(3));

     // Zweite "Pause" – warte auf defeat < 3
     Timer waitForDefeat = new Timer(2000, null); // prüft alle 500 ms
     waitForDefeat.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
        	 if (defeat == 0) {
        		 warnLabel.setText("<html>Schnell! Drücke auf ein Viereck!<br>Du besiegst es, indem du den Flächeninhalt ausrechnest!<br>Multipliziere die beiden Zahlen!</html>");

        	 }
        		 
        	 
             if (defeat > 3) {
                 waitForDefeat.stop(); // Stoppe den Timer, wenn Bedingung erfüllt
                 GenerateWave(10, 1); // Gegnerzahl, Gegnerarten
             }
         }
     });
     waitForDefeat.start(); // Starte die Überwachung
 }
});
firstPause.setRepeats(false); // Nur einmal ausführen
firstPause.start(); // Starte die erste Pause


}

// Methode zum Hinzufügen eines Towers
public void addTower(Tower tower) {
    layeredPane.add(tower, Integer.valueOf(3)); // Tower in Ebene 3 hinzufügen
    layeredPane.revalidate(); // Layout aktualisieren
    layeredPane.repaint(); // Panel neu zeichnen
    System.out.println("Tower wurde zum Fenster hinzugefügt.");
}

public void addTowerRange(TowerRange range) {
    layeredPane.add(range, Integer.valueOf(3)); // Tower in Ebene 3 hinzufügen
    layeredPane.revalidate(); // Layout aktualisieren
    layeredPane.repaint(); // Panel neu zeichnen
    System.out.println("Range wurde zum Fenster hinzugefügt.");
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