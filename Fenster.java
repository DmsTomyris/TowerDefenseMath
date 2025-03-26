import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.Timer;

class Fenster extends JFrame implements WindowListener, ActionListener,  Form.PanelListener{
	private Form panel;
    private JButton Be;
    
    private int framewidth = 700;
    private int frameheight = 500;
    
    //für bewegung
    private int x = 100;
    private int y = 50;
    private Timer timer;
    //für bewegung
    
	public JTextField Tg;
	
	
	

	public Fenster(String titel) {
		super(titel);
		this.setLayout(null); // keine referenz auf einen Layout Manager gut so weil wir machen kein HTML in der Grundschule mit blinken und so
		this.setSize(framewidth, frameheight);
        panel = new Form();
        panel.setPanelListener(this); //interface auf Fenster aktivieren
        this.add(panel); // JPanel zum JFrame hinzufügen
		this.setVisible(true);
 
		// Hintergrundbild einfügen
		//String bildPfad = "C:\\Users\\Teo Helmer\\eclipse-workspace\\Fensterprogramm\\src\\ressources\\png-clipart-dream-river-dream-river-thumbnail.png"; 
		//ImageIcon icon = new ImageIcon(bildPfad);
		//JLabel label = new JLabel(icon);  // Bild setzen
		//label.setText("Mulm"); // Optional: Text hinzufügen
		//label.setSize(500, 500); // Größe setzen
		//label.setLocation(0, 0); // Position setzen
		//this.add(label);
		//Hintergrundbild einfügen
		
		
	
	}
	
	
	public void onButtonClicked() {// Erstellt Eingabefeld wenn Button gedrückt wird
        	Tg=new JTextField();
    		Tg.setText("500");
    		Tg.setLocation(10, 10);
    		Tg.setSize(300, 20);
    		this.add(Tg);
    		Tg.addActionListener(e -> {
    			System.out.println(Tg.getText()+" "+(panel.g*panel.h));
    			this.remove(Tg); // Entfernen
    			panel.addButton();
    			panel.defeated();
    			if(Integer.parseInt(Tg.getText())==panel.g*panel.h) {
    				this.remove(panel);
    			}
    			this.revalidate(); // Layout aktualisieren
    			this.repaint();
    			
    			});   
    

	
    		
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
		
		
		

		}
		
		
	}
//Mulm