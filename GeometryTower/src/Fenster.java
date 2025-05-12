import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.Random;

class Fenster extends JFrame implements WindowListener, ActionListener, Form.PanelListener {
    private static Form panel[];

    private int framewidth = 1500;
    private int frameheight = 800;

    private Timer spawnTimer;
    private Timer tutorialWatcher;
    private Timer refreshTimer;
    private Timer initWaveTimer;

    public static int globallevel;

    private int waveiterator = 0;

    public JTextField Tg;
    public int w = 0; // while counter
    public int a = 5; // Anzahl der Panels

    public static double defeat = 0;
    public static int start = 0;

    private Random random;

    public static int[][] towerpos = new int[1][2];
    public static int towerzahl = 0;
    public static boolean Schliessen = false;

    public static Tower[] tower = new Tower[1];
    
    
    int gegner = 6; // für Endlosmodus, startgegner
	int arten = 1; // für Endlosmodus, startgegnerarten
	int zahlenhöhe = 10; //für Endlosmodus, höhe der Zahlen
	int gegner_insgesamt = 0; //für endlosmodus besiegte gegner

    JLayeredPane layeredPane = getLayeredPane(); // Für mehrere Layers, um sachen ordentlich anzuordnen
    
    
    //Verlorenscreen
    static JLabel defeatLabel = new JLabel("Du hast verloren");
    static JPanel defeatPanel = new JPanel();
    static JPanel defeatPanelBackground = new JPanel();
    static JButton btnStartMenu = new JButton("Menu");
    //Verlorenscreen

    public Fenster(String titel, int level) {//Hauptfenster - hier wird das Spiel gespielt
        super(titel);
        globallevel = level;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setUndecorated(false);
        this.setLayout(null);
        this.setSize(framewidth, frameheight);
        
        random = new Random();

        Schliessen = true;

        //Fügt gamepanel zum Fenster hinzu
        GamePanel gamePanel = new GamePanel(this); 
        layeredPane.add(gamePanel, Integer.valueOf(10));
        //Fügt gamepanel zum Fenster hinzu

        this.addMouseListener(new MouseAdapter() {// Setzt den Tower an die Stelle wo geklickt wurde, geht nur, wenn der Tower vorher gekauft wurde.
            @Override
            public void mousePressed(MouseEvent e) {
                if (gamePanel.canAddTower() && SwingUtilities.isLeftMouseButton(e)) {
                    tower[towerzahl] = new Tower(0,(e.getX())-6, (e.getY())-29); // Create a new Tower
                    tower[towerzahl].posx = (e.getX()) - 6;
                    tower[towerzahl].posy = (e.getY()) - 29;
                    towerpos[towerzahl][0] = tower[towerzahl].posx;
                    towerpos[towerzahl][1] = tower[towerzahl].posy;
                    tower[towerzahl].setBounds(tower[towerzahl].posx - (tower[towerzahl].radius / 2), tower[towerzahl].posy - (tower[towerzahl].radius / 2), tower[towerzahl].radius, tower[towerzahl].radius); // Set tower position to mouse position

                    TowerRange range = new TowerRange(TowerRange.range, tower[towerzahl].posx, tower[towerzahl].posy);
                    range.setBounds(tower[towerzahl].posx - (TowerRange.range / 2), tower[towerzahl].posy - (TowerRange.range / 2), TowerRange.range, TowerRange.range); // Set tower position to mouse position

                    addTower(tower[towerzahl]); // Add tower to the window
                    addTowerRange(range);

                    gamePanel.resetTowerFlag(); // Reset the flag
                }
            }
        });

        this.addMouseListener(new MouseAdapter() {// Wenn geklicht wird, wird refreshed welches Textfeld grad im Fokus ist. Sie refreshTextField()
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshTextField();
            }
        });

        //Menubutton wird erstellt
        btnStartMenu.setBounds(10, 10, 100, 20);
        btnStartMenu.setOpaque(false);
        btnStartMenu.addActionListener(e -> {
            cleanup();
            SwingUtilities.getWindowAncestor(btnStartMenu).dispose();
        });
        layeredPane.add(btnStartMenu, Integer.valueOf(8));
        //Menubutton wird erstellt

        //Alle grafischen Elemente werden vorbereitet
        JPanel backgroundPanel = new JPanel(null);
        backgroundPanel.setBounds(0, 0, framewidth, frameheight);
        JLabel bildLabel = new JLabel(new ImageIcon("images/Map1.png"));
        bildLabel.setBounds(0, 0, framewidth, frameheight);
        bildLabel.setHorizontalAlignment(SwingConstants.LEFT);
        bildLabel.setVerticalAlignment(SwingConstants.TOP);
        backgroundPanel.add(bildLabel);
        layeredPane.add(backgroundPanel, Integer.valueOf(0));
        JLabel euleLabel = new JLabel(new ImageIcon("images/Eule3.png"));
        euleLabel.setBounds(0, 0, framewidth, frameheight);
        euleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        euleLabel.setVerticalAlignment(SwingConstants.TOP);
        JPanel eulePanel = new JPanel(null);
        eulePanel.setBounds(1100, 300, framewidth, frameheight);
        eulePanel.setOpaque(false);
        eulePanel.add(euleLabel);
        JLabel bubbleLabel = new JLabel(new ImageIcon("images/sprechblase1.png"));
        bubbleLabel.setBounds(0, 0, framewidth, frameheight);
        bubbleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        bubbleLabel.setVerticalAlignment(SwingConstants.TOP);
        JPanel bubblePanel = new JPanel(null);
        bubblePanel.setBounds(700, 100, framewidth, frameheight);
        bubblePanel.setOpaque(false);
        bubblePanel.add(bubbleLabel);
        JLabel warnLabel = new JLabel("<html>Schnell! Drücke auf ein Viereck!<br>Du besiegst es, indem du die Aufgabe ausrechnest!<br>Addiere die beiden Zahlen!</html>");
        warnLabel.setBounds(800, 230, 400, 120); 
        warnLabel.setFont(new Font("Arial", Font.BOLD, 24));
        warnLabel.setOpaque(true);
        warnLabel.setBackground(java.awt.Color.WHITE);
    	defeatLabel.setBounds(0, 0, 0, 0);
    	defeatLabel.setFont(new Font("Arial", Font.BOLD, 40));
    	defeatLabel.setHorizontalAlignment(SwingConstants.LEFT);
    	defeatLabel.setVerticalAlignment(SwingConstants.TOP);
    	layeredPane.add(defeatLabel, Integer.valueOf(7));  	
    	defeatPanel.setBounds(0, 0, 0, 0);
    	layeredPane.add(defeatPanel, Integer.valueOf(6));
    	defeatPanelBackground.setBounds(0, 0, 0, 0);
    	defeatPanelBackground.setBackground(Color.BLACK);
    	layeredPane.add(defeatPanelBackground, Integer.valueOf(5));
    	//Alle grafischen Elemente werden vorbereitet

        startAutoRefresh();
        
        //Levelfunktion - Generiert Waves mit Abstand, sorgt auch für flüssiges Tutorial. Waves und Level sind sehr individuel anpassbar, dafür ist aber auch pro Level 30 Zeilen Code Notwendig
        if (level == 0) {
            initWaveTimer = new Timer(500, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    GenerateWave(3, 1, 9); // Erste Welle starten

                    // Einführung + Überwachung von defeat-Werten
                    tutorialWatcher = new Timer(500, null);
                    tutorialWatcher.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (defeat == 0 && start == 2) {
                            	layeredPane.add(eulePanel, Integer.valueOf(2));
                                layeredPane.add(bubblePanel, Integer.valueOf(3));
                                layeredPane.add(warnLabel, Integer.valueOf(4));
                                defeat += 1;
                            }
                            if (defeat == 4) {
                                defeat += 1;
                                warnLabel.setText("<html>Du schaffst das erstmal alleine</html>");
                                new javax.swing.Timer(1500, e -> {
                                    layeredPane.setLayer(eulePanel, Integer.valueOf(0));
                                    layeredPane.setLayer(bubblePanel, Integer.valueOf(0));
                                    layeredPane.setLayer(warnLabel, Integer.valueOf(0));
                                    ((javax.swing.Timer) e.getSource()).stop(); // Timer nach einmaligem Durchlauf stoppen
                                }).start();
                                GenerateWave(8, 1, 9); // Zweite Welle
                            }
                            if (defeat == 13) {
                                defeat += 1;
                                GenerateWave(10, 2, 9); // Zweite Welle
                                warnLabel.setText("<html>Nimm dich vor den Dreiecken in Acht!<br>Quadriere die angezeigte Zahl</html>");
                                layeredPane.setLayer(eulePanel, Integer.valueOf(2));
                                layeredPane.setLayer(bubblePanel, Integer.valueOf(3));
                                layeredPane.setLayer(warnLabel, Integer.valueOf(4));
                                new javax.swing.Timer(4000, e -> {
                                    warnLabel.setText("<html>Plaziere einen Minus-Turm<br>um die Gegner zu verlangsamen</html>");
                                    ((javax.swing.Timer) e.getSource()).stop(); // Timer nach einmaligem Durchlauf stoppen
                                }).start();
                                new javax.swing.Timer(11000, e -> {
                                    layeredPane.setLayer(eulePanel, Integer.valueOf(0));
                                    layeredPane.setLayer(bubblePanel, Integer.valueOf(0));
                                    layeredPane.setLayer(warnLabel, Integer.valueOf(0));
                                    ((javax.swing.Timer) e.getSource()).stop(); // Timer nach einmaligem Durchlauf stoppen
                                }).start();
                            }
                            if (defeat == 24) {
                                defeat += 1;
                                GenerateWave(15, 3, 9); // Zweite Welle
                                warnLabel.setText("<html>Du kennst das Spiel schon!<br>Multipliziere die Zahlen in den Kreisen</html>");
                                layeredPane.setLayer(eulePanel, Integer.valueOf(2));
                                layeredPane.setLayer(bubblePanel, Integer.valueOf(3));
                                layeredPane.setLayer(warnLabel, Integer.valueOf( 4));
                                new javax.swing.Timer(5000, e -> {
                                    layeredPane.setLayer(eulePanel, Integer.valueOf(0));
                                    layeredPane.setLayer(bubblePanel, Integer.valueOf(0));
                                    layeredPane.setLayer(warnLabel, Integer.valueOf(0));
                                    ((javax.swing.Timer) e.getSource()).stop(); // Timer nach einmaligem Durchlauf stoppen
                                }).start();
                            }
                            if (defeat == 40) {
                            	defeat += 1;
                                warnLabel.setText("<html>Du hast das Tutorial geschafft!</html>");
                                layeredPane.setLayer(eulePanel, Integer.valueOf(2));
                                layeredPane.setLayer(bubblePanel, Integer.valueOf(3));
                                layeredPane.setLayer(warnLabel, Integer.valueOf(4));
                                btnStartMenu.setBounds(850, 350, 280, 50);
                                tutorialWatcher.stop();
                            }}});tutorialWatcher.start();}});initWaveTimer.setRepeats(false);initWaveTimer.start();
        } 
        else if (level == 1) {
        	initWaveTimer = new Timer(500, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    GenerateWave(5, 1, 9); // Erste Welle starten
                    tutorialWatcher = new Timer(500, null);
                    tutorialWatcher.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (defeat == 0 && start == 2) {
                            	layeredPane.add(eulePanel, Integer.valueOf(0));
                                layeredPane.add(bubblePanel, Integer.valueOf(0));
                                layeredPane.add(warnLabel, Integer.valueOf(0));
                                defeat += 1;}
                            if (defeat == 6) {
                                defeat += 1;
                                GenerateWave(8, 2, 12);}
                            if (defeat == 15) {
                                defeat += 1;
                                GenerateWave(10, 3, 14);}
                            if (defeat == 26) {
                                defeat += 1;
                                GenerateWave(15, 3, 16);}
                            if (defeat == 42) {
                            	defeat += 1;
                                warnLabel.setText("<html>Du hast Level 1 geschafft!</html>");
                                layeredPane.setLayer(eulePanel, Integer.valueOf(2));
                                layeredPane.setLayer(bubblePanel, Integer.valueOf(3));
                                layeredPane.setLayer(warnLabel, Integer.valueOf(4));
                                btnStartMenu.setBounds(850, 350, 280, 50);
                                tutorialWatcher.stop();
                            }}});tutorialWatcher.start();}});initWaveTimer.setRepeats(false);initWaveTimer.start();
        } else if (level == 2) {
        	initWaveTimer = new Timer(500, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    GenerateWave(7, 1, 12); // Erste Welle starten
                    tutorialWatcher = new Timer(500, null);
                    tutorialWatcher.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (defeat == 0 && start == 2) {
                            	layeredPane.add(eulePanel, Integer.valueOf(0));
                                layeredPane.add(bubblePanel, Integer.valueOf(0));
                                layeredPane.add(warnLabel, Integer.valueOf(0));
                                defeat += 1;}
                            if (defeat == 8) {
                                defeat += 1;
                                GenerateWave(10, 2, 17);}
                            if (defeat == 19) {
                                defeat += 1;
                                GenerateWave(30, 2, 12);}
                            if (defeat == 50) {
                                defeat += 1;
                                GenerateWave(17, 3, 21);}
                            if (defeat == 68) {
                            	defeat += 1;
                                warnLabel.setText("<html>Du hast Level 2 geschafft!</html>");
                                layeredPane.setLayer(eulePanel, Integer.valueOf(2));
                                layeredPane.setLayer(bubblePanel, Integer.valueOf(3));
                                layeredPane.setLayer(warnLabel, Integer.valueOf(4));
                                btnStartMenu.setBounds(850, 350, 280, 50);
                                tutorialWatcher.stop();
                            }}});tutorialWatcher.start();}});initWaveTimer.setRepeats(false);initWaveTimer.start();
        } else if (level == 3) {
        	initWaveTimer = new Timer(500, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    GenerateWave(10, 1, 14); // Erste Welle starten
                    tutorialWatcher = new Timer(500, null);
                    tutorialWatcher.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (defeat == 0 && start == 2) {
                            	layeredPane.add(eulePanel, Integer.valueOf(0));
                                layeredPane.add(bubblePanel, Integer.valueOf(0));
                                layeredPane.add(warnLabel, Integer.valueOf(0));
                                defeat += 1;}
                            if (defeat == 11) {
                                defeat += 1;
                                GenerateWave(14, 2, 17);}
                            if (defeat == 26) {
                                defeat += 1;
                                GenerateWave(15, 2, 24);}
                            if (defeat == 42) {
                                defeat += 1;
                                GenerateWave(17, 3, 19);}
                            if (defeat == 60) {
                            	defeat += 1;
                                warnLabel.setText("<html>Du hast Level 3 geschafft!</html>");
                                layeredPane.setLayer(eulePanel, Integer.valueOf(2));
                                layeredPane.setLayer(bubblePanel, Integer.valueOf(3));
                                layeredPane.setLayer(warnLabel, Integer.valueOf(4));
                                btnStartMenu.setBounds(850, 350, 280, 50);
                                tutorialWatcher.stop();
                            }}});tutorialWatcher.start();}});initWaveTimer.setRepeats(false);initWaveTimer.start();
        } else if (level == 4) {
        	initWaveTimer = new Timer(500, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    GenerateWave(10, 1, 28); // Erste Welle starten
                    tutorialWatcher = new Timer(500, null);
                    tutorialWatcher.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (defeat == 0 && start == 2) {
                            	layeredPane.add(eulePanel, Integer.valueOf(0));
                                layeredPane.add(bubblePanel, Integer.valueOf(0));
                                layeredPane.add(warnLabel, Integer.valueOf(0));
                                defeat += 1;}
                            if (defeat == 11) {
                                defeat += 1;
                                GenerateWave(12, 1, 57);}
                            if (defeat == 24) {
                                defeat += 1;
                                GenerateWave(17, 2, 21);}
                            if (defeat == 32) {
                                defeat += 1;
                                GenerateWave(5, 3, 29);}
                            if (defeat == 38) {
                            	defeat += 1;
                                warnLabel.setText("<html>Du hast Level 4 geschafft!</html>");
                                layeredPane.setLayer(eulePanel, Integer.valueOf(2));
                                layeredPane.setLayer(bubblePanel, Integer.valueOf(3));
                                layeredPane.setLayer(warnLabel, Integer.valueOf(4));
                                btnStartMenu.setBounds(850, 350, 280, 50);
                                tutorialWatcher.stop();
                            }}});tutorialWatcher.start();}});initWaveTimer.setRepeats(false);initWaveTimer.start();
        } else if (level == 5) {
        	initWaveTimer = new Timer(500, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    GenerateWave(12, 3, 10); // Erste Welle starten
                    tutorialWatcher = new Timer(500, null);
                    tutorialWatcher.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (defeat == 0 && start == 2) {
                            	layeredPane.add(eulePanel, Integer.valueOf(0));
                                layeredPane.add(bubblePanel, Integer.valueOf(0));
                                layeredPane.add(warnLabel, Integer.valueOf(0));
                                defeat += 1;}
                            if (defeat == 13) {
                                defeat += 1;
                                GenerateWave(14, 2, 42);}
                            if (defeat == 28) {
                                defeat += 1;
                                GenerateWave(17, 3, 69);}
                            if (defeat == 46) {
                                defeat += 1;
                                GenerateWave(14, 3, 187);}
                            if (defeat == 61) {
                            	defeat += 1;
                                warnLabel.setText("<html>Du hast Level 5 geschafft!</html>");
                                layeredPane.setLayer(eulePanel, Integer.valueOf(2));
                                layeredPane.setLayer(bubblePanel, Integer.valueOf(3));
                                layeredPane.setLayer(warnLabel, Integer.valueOf(4));
                                btnStartMenu.setBounds(850, 350, 280, 50);
                                tutorialWatcher.stop();
                            }}});tutorialWatcher.start();}});initWaveTimer.setRepeats(false);initWaveTimer.start();
        } else if (level == 6) {
        	gegner = 6;
        	arten = 1;
        	zahlenhöhe = 10;
        	gegner_insgesamt = 6;
            initWaveTimer = new Timer(500, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	
                    GenerateWave(gegner, arten, zahlenhöhe); // Erste Welle starten
                    
                    tutorialWatcher = new Timer(500, null);
                    tutorialWatcher.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (defeat == 0 && start == 2) {
                                defeat += 1;
                            }
                            if (defeat == 1+gegner_insgesamt) {
                                defeat += 1;
                                gegner += 3;
                            	arten += 1;
                            	zahlenhöhe += 2;
                            	gegner_insgesamt = gegner_insgesamt + gegner;
                            	GenerateWave(gegner, arten, zahlenhöhe);
                            }
                            if (defeat == 2+gegner_insgesamt) {
                                defeat += 1;
                                gegner += 3;
                            	zahlenhöhe += 2;
                                gegner_insgesamt = gegner_insgesamt + gegner;
                                GenerateWave(gegner, arten, zahlenhöhe);
                            }
                            if (defeat == 3+gegner_insgesamt) {
                                defeat += 1;
                                gegner += 3;
                                if (arten < 3) arten += 1;gegner_insgesamt = gegner_insgesamt + gegner;
                                GenerateWave(gegner, arten, zahlenhöhe);
                            }
                            if (defeat == 4+gegner_insgesamt) {
                                defeat += 1;
                                gegner += 3;
                            	zahlenhöhe += 2;
                                gegner_insgesamt = gegner_insgesamt + gegner;
                                GenerateWave(gegner, arten, zahlenhöhe);
                            }
                            if (defeat == 5+gegner_insgesamt) {
                                defeat += 1;
                                arten -= 1;
                                gegner_insgesamt = gegner_insgesamt + gegner;
                                GenerateWave(gegner, arten, zahlenhöhe);
                            }
                            if (defeat > 50) {
                                defeat = 10;

                                
                            }
                        }
                    });
                    tutorialWatcher.start();
                }
            });
            initWaveTimer.setRepeats(false);
            initWaveTimer.start();
            }}//Levelfunktion - Generiert Waves mit Abstand, sorgt auch für flüssiges Tutorial

    public void cleanup() { // Ist für den Menu Button - Alle prozesse werden abgebrochen, alle Gegner entfernt

        if (spawnTimer != null) spawnTimer.stop();
        if (tutorialWatcher != null) tutorialWatcher.stop();
        if (refreshTimer != null) refreshTimer.stop();
        if (initWaveTimer != null) initWaveTimer.stop();

        // Entfernen der Türme
        for (int i = 0; i < towerzahl; i++) {
            if (tower[i] != null) {
                layeredPane.remove(tower[i]);
                tower[i] = null; // Referenz auf null setzen
            }
        }

        // Entfernen der Gegner (Forms)
        if (panel != null) {
            for (Form f : panel) {
                if (f != null) {
                    layeredPane.remove(f);
                    f = null; // Referenz auf null setzen
                }
            }
        }
        defeat=0;
        layeredPane.revalidate();
        layeredPane.repaint();
    }
    
    public static void defeat_screen() { // Zeigt den defeat Screen an
    	defeatLabel.setBounds(600, 300, 500, 500); // gleiche Größe, gleiche Position    	
    	defeatPanel.setBounds(100, 100, 1300, 600); // gleiche Größe, gleiche Position
    	defeatPanelBackground.setBounds(0, 0, 2000, 1000); // gleiche Größe, gleiche Position
    	btnStartMenu.setBounds(610, 400, 280, 50);
    }

    private void startAutoRefresh() { // Refreshed alle Grafikelemente alle 0,1 Sekunden um die grafische Ausgabe mit den Hintergrundprozessen zu synchronisieren
        refreshTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layeredPane.revalidate();
                layeredPane.repaint();
            }
        });
        refreshTimer.setRepeats(true);
        refreshTimer.start();
    }
    
    public static void refreshTextField() { // Aktualisiert welche Funktion das Textfeld hat, damit nur ein Textfeld gleichzeitg generiert wird
    	for (Form p : Fenster.panel) {
            if (p != null) {
                p.refresh();
                
    }}}

    public void addTower(Tower tower) { //Fügt den Tower visuell hinzu
        layeredPane.add(tower, Integer.valueOf(3)); // Tower in Ebene 3 hinzufügen
        layeredPane.revalidate(); // Layout aktualisieren
        layeredPane.repaint(); // Panel neu zeichnen
    }

    public void addTowerRange(TowerRange range) { // Fügt die Towerrange visuell hinzu
        layeredPane.add(range, Integer.valueOf(3)); // Tower in Ebene 3 hinzufügen
        layeredPane.revalidate(); // Layout aktualisieren
        layeredPane.repaint(); // Panel neu zeichnen
    }

    public void GenerateWave(int gegnerAnzahl, int gegnerArt, int d) { // Generiert eine Wave, es kann festgelegt werden, wie viele Gegner, wie viele Gegner-Arten und wie hoch die Zahlen auf den Gegnern gehen sollen
        waveiterator = 0;
        panel = new Form[gegnerAnzahl + 1];
        w = 0;
        spawnTimer = new Timer(1500, null); // Alle 1500 ms ein Gegner
        spawnTimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (w < gegnerAnzahl) {
                    if (start < 2) {
                        start += 1;
                    }
                    panel[w] = new Form(w, random.nextInt(gegnerArt), d);
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

    public void focusNext() {//Das Textfeld vom nächsten Gegner wird angezeigt siehe onPanelRemoved
        waveiterator += 1;
        if (panel[waveiterator] != null && panel[waveiterator].isVisible()) {
            panel[waveiterator].onButtonClicked(0);
        }
    }

    @Override
    public void onPanelRemoved(Form form) { // focusNext() wurd ausgeführt - siehe Form.onButtonClicked()
        focusNext();
    }

    
    @Override
    public void windowClosing(WindowEvent e) {
    	cleanup(); // Ensure cleanup is called before closing
        System.exit(0);
    }
    
    //Nicht benötigte Funktionen
    @Override
    public void windowOpened(WindowEvent e) {}
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
    //Nicht benötigte Funktionen
}