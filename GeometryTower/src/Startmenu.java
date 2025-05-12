import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Startmenu extends JFrame implements WindowListener{
	
    // Statische Referenzen auf Fenster-Objekte für Tutorial und Endlosmodus
    public static Fenster Tutorial;
    public static Fenster Endlos;
    // Statische Referenzen auf Fenster-Objekte für Tutorial und Endlosmodus

    private JPanel buttonPanel; // Panel für Buttons
    private Image backgroundImage; // Hintergrundbild

    public Startmenu() {//Konstruktor
    	
        // Setze Titel und grundlegende Fenster-Einstellungen
        setTitle("Startmenü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1175, 600); // Fenstergröße
        setLocationRelativeTo(null); // Zentriere Fenster auf dem Bildschirm
        // Setze Titel und grundlegende Fenster-Einstellungen

        // Lade Hintergrundbild
        backgroundImage = new ImageIcon("images/Menubild.png").getImage();
        // Lade Hintergrundbild
        
        // Erstelle ein benutzerdefiniertes Panel, um das Hintergrundbild zu zeichnen
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Zeichne das Hintergrundbild skaliert, damit es das gesamte Panel füllt
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new GridBagLayout()); // Verwende GridBagLayout, um Button-Panel zu zentrieren
        setContentPane(mainPanel); // Setze das Haupt-Panel als Inhalt des Fensters
        // Erstelle ein benutzerdefiniertes Panel, um das Hintergrundbild zu zeichnen
        
        
        // Erstelle den Untergrund für die Buttons
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Mache das Panel transparent, damit der Hintergrund sichtbar bleibt
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Vertikale Anordnung der Buttons
        Dimension buttonSize = new Dimension(300, 70); // Standardgröße der Buttons
        // Erstelle den Untergrund für die Buttons
        
        // Erstelle Buttons für Tutorial, Level-Auswahl und Endlosmodus
        JButton tutButton = new JButton("Tutorial");
        JButton levelButton = new JButton("Level");
        JButton playButton = new JButton("Endlos");
        // Erstelle Buttons für Tutorial, Level-Auswahl und Endlosmodus

        // Füge alle Buttons in ein Array, um später Eigenschaften einheitlich zu setzen
        JButton[] buttons = {tutButton, levelButton, playButton};
        for (JButton b : buttons) {
            b.setPreferredSize(buttonSize); // Setze bevorzugte Größe
            b.setMaximumSize(buttonSize); // Setze maximale Größe
            b.setAlignmentX(Component.CENTER_ALIGNMENT);}// Zentriere Buttons horizontal
        // Füge alle Buttons in ein Array, um später Eigenschaften einheitlich zu setzen

        
        tutButton.addActionListener(e -> Tutorial = new Fenster("Tutorial", 0)); // Füge Aktion für Tutorial-Button hinzu, die ein neues Fenster im Tutorial-Modus öffnet
        
        // Füge die Level hinzu
        levelButton.addActionListener(e -> {		//beim Level button wird das Fenster komplett gereinigt und mit neuen buttons besetzt
            // Entferne alle existierenden Buttons aus dem Button-Panel
            buttonPanel.removeAll();
            // Erstelle Buttons für Levels 1 bis 5
            for (int i = 1; i <= 5; i++) {
                final int levelNum = i; // Speichere die Levelnummer für die Aktion
                JButton lvlButton = new JButton("Level " + levelNum);
                lvlButton.setPreferredSize(buttonSize);
                lvlButton.setMaximumSize(buttonSize);
                lvlButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                // Füge Aktion hinzu, um ein neues Fenster für das gewählte Level zu öffnen
                lvlButton.addActionListener(ev -> new Fenster("lvl " + levelNum, levelNum));
                buttonPanel.add(lvlButton); // Füge Level-Button dem Panel hinzu
                if (i < 5) {
                    // Füge Abstand zwischen den Buttons hinzu
                    buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                }
            }
            buttonPanel.revalidate(); // Aktualisiere das Layout des Panels
            buttonPanel.repaint(); // Zeichne das Panel neu
        });
        // Füge die Level hinzu

        
        playButton.addActionListener(e -> Endlos = new Fenster("Endlos", 6)); // Füge Aktion für Endlos-Button hinzu, die ein neues Fenster im Endlosmodus öffnet

        // Füge Buttons mit vertikalem Abstand und Zentrierung hinzu
        buttonPanel.add(Box.createVerticalGlue()); // Füge vertikalen Platz vor den Buttons hinzu
        for (int i = 0; i < buttons.length; i++) {
            buttonPanel.add(buttons[i]); // Füge den Button hinzu
            if (i < buttons.length - 1) {
                // Füge Abstand zwischen den Buttons hinzu
                buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            }
        }
        buttonPanel.add(Box.createVerticalGlue()); // Füge vertikalen Platz nach den Buttons hinzu

        
        // Zentriere das Button-Panel im Haupt-Panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER; // Zentriere das Panel
        mainPanel.add(buttonPanel, gbc);

        setVisible(true); // Zeige das Fenster an
    }

    public static void main(String[] args) {		//main Methode
        // Starte das Startmenü im Event-Dispatching-Thread
        SwingUtilities.invokeLater(() -> new Startmenu());
    }

    
    
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);}

	//Sinnlose must include funktionen
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
	//Sinnlose must include funktionen
}
