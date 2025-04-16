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
public int a = 0; // Anzahl der Panels

private Random random;

JLayeredPane layeredPane = getLayeredPane();


public Fenster(String titel) {
super(titel);
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.setResizable(false);
this.setUndecorated(false);
this.setLayout(null);
this.setSize(framewidth, frameheight);

GamePanel gamePanel = new GamePanel();
layeredPane.add(gamePanel, Integer.valueOf(2));

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


GenerateWave();


}

public void GenerateWave() {
	w = 0; // while counter
	a = 5; // Anzahl der Panels
	panel = new Form[a];
	while (w < a) {
		System.out.println("Mulm");
		panel[w] = new Form(w, random.nextInt(2)); // WElche Form
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