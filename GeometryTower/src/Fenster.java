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

public static double defeat = 0;
public static int start = 0;


private Random random;

private GamePanel gamePanel; // Reference to GamePanel

public static int[][] towerpos=new int[12][2];
public static int towerzahl =0;

public static Tower[] tower=new Tower[12];

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
            tower[towerzahl]  = new Tower(0); // Create a new Tower
            tower[towerzahl].posx=(e.getX())-6;
            tower[towerzahl].posy=(e.getY())-29;
            towerpos[towerzahl][0]=tower[towerzahl].posx;
            towerpos[towerzahl][1]=tower[towerzahl].posy;
            tower[towerzahl].setBounds(tower[towerzahl].posx-(tower[towerzahl].radius/2), tower[towerzahl].posy-(tower[towerzahl].radius/2), tower[towerzahl].radius, tower[towerzahl].radius); // Set tower position to mouse position
            //System.out.println(e.getX() + "xx");
            //System.out.println(e.getY()+ "yy");
            //tower.posx=e.getX()-30;
            //tower.posy=e.getY()-50;
            
            System.out.println(tower[towerzahl].posx+ "towerX");
            System.out.println(tower[towerzahl].posy+ "towerY");
            
            TowerRange range = new TowerRange(TowerRange.range, tower[towerzahl].posx, tower[towerzahl].posy);
            range.setBounds(tower[towerzahl].posx-(TowerRange.range/2), tower[towerzahl].posy-(TowerRange.range/2), TowerRange.range, TowerRange.range); // Set tower position to mouse position
            
            
            addTower(tower[towerzahl]); // Add tower to the window
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

layeredPane.add(eulePanel, Integer.valueOf(2));
layeredPane.add(bubblePanel, Integer.valueOf(3));
layeredPane.add(warnLabel, Integer.valueOf(4));

startAutoRefresh();

Timer initWaveTimer = new Timer(500, new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        GenerateWave(3, 1); // Erste Welle starten

        // Einführung + Überwachung von defeat-Werten
        Timer tutorialWatcher = new Timer(500, null);
        tutorialWatcher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (defeat == 0 && start == 2) {
                	defeat += 1;
                    warnLabel.setText("<html>Schnell! Drücke auf ein Viereck!<br>Du besiegst es, indem du den Flächeninhalt ausrechnest!<br>Multipliziere die beiden Zahlen!</html>");
                }
                if (defeat == 4) {
                    defeat += 1;
                    layeredPane.setLayer(eulePanel, Integer.valueOf(0));
                    layeredPane.setLayer(bubblePanel, Integer.valueOf(0));
                    layeredPane.setLayer(warnLabel, Integer.valueOf(0));
                    GenerateWave(8, 1); // Zweite Welle
                }
                if (defeat == 13) {
                	defeat += 1;
                	GenerateWave(10, 2); // Zweite Welle
                	warnLabel.setText("<html>Nimm dich vor den Dreiecken in Acht!<br>Quadriere die angezeigte Zahl</html>");
                	layeredPane.setLayer(eulePanel, Integer.valueOf(2));
                    layeredPane.setLayer(bubblePanel, Integer.valueOf(3));
                    layeredPane.setLayer(warnLabel, Integer.valueOf(4));
                }
                if (defeat == 24) {
                	defeat += 1;
                	GenerateWave(15, 3); // Zweite Welle
                	warnLabel.setText("<html>Du kennst das Spiel schon!<br>Multipliziere die Zahlen in den Kreisen</html>");
                }
                if (defeat == 40) {
                    tutorialWatcher.stop();
                    System.out.println("Tutorial beendet");
                }
            }
        });
        tutorialWatcher.start();
    }
});
initWaveTimer.setRepeats(false);
initWaveTimer.start();
}


private void startAutoRefresh() {
    Timer refreshTimer = new Timer(100, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            layeredPane.revalidate();
            layeredPane.repaint();
        }
    });
    refreshTimer.setRepeats(true);
    refreshTimer.start();
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

public void GenerateWave(int gegnerAnzahl, int gegnerArt) {
    panel = new Form[gegnerAnzahl];
    w = 0;
    Timer spawnTimer = new Timer(1500, null); // Alle 500 ms ein Gegner
    spawnTimer.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (w < gegnerAnzahl) {
            	if (start < 2) {
                	start += 1;
                	System.out.println(start);}
                panel[w] = new Form(w, random.nextInt(gegnerArt));
                panel[w].setPanelListener(Fenster.this);
                layeredPane.add(panel[w], Integer.valueOf(1));
                Fenster.this.setVisible(true);
                w++;
            } else {
                spawnTimer.stop(); // Alle Gegner gespawnt, Timer beenden
            }
        }
    });

    spawnTimer.start();
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