import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

class Fenster extends JFrame implements WindowListener, ActionListener,  Form.PanelListener{
	private Form panel[];
    
    
    private int framewidth = 700;
    private int frameheight = 500;
    
    //für bewegung
    private int x = 100;
    private int y = 50;
    private Timer timer;
    //für bewegung
    
	public JTextField Tg;
	
	
	public int w = 0; //while counter
	public int a = 100; //anzahl panel

	public Fenster(String titel) {
		super(titel);
		//quality of life
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setResizable(false);
		this.setUndecorated(true); // Entfernt die Titelleiste
		this.setLayout(null); // keine referenz auf einen Layout Manager gut so weil wir machen kein HTML in der Grundschule mit blinken und so
		this.setSize(framewidth, frameheight);
        panel = new Form[a];
        
        System.out.println(Main.class.getResource("/images/Map1.png"));
        ImageIcon icon = new ImageIcon("Map1.png");
		JLabel label = new JLabel(icon);  // Bild setzen
		label.setText("Mulm"); // Optional: Text hinzufügen
		label.setSize(this.getWidth(), this.getHeight()); // Größe setzen
		label.setLocation(0, 0); // Position setzen
		this.add(label);
		label.setVisible(true);
		
        while (w<a)   {
            panel[w] = new Form(w);
            panel[w].setPanelListener(this); //interface auf Fenster aktivieren
            this.add(panel[w]); // JPanel zum JFrame hinzufügen
    		this.setVisible(true);
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		w+=1;
    		
            }
		
        
		
		
	
	}
	
	
	public void onButtonClicked(int w) {// Erstellt Eingabefeld wenn Button gedrückt wird
        	Tg=new JTextField();
    		Tg.setText("625");
    		Tg.setLocation(10, 10);
    		Tg.setSize(300, 20);
    		this.add(Tg);
    		Tg.addActionListener(e -> {
    			System.out.println(Tg.getText()+" "+(panel[w].g*panel[w].h));
    			this.remove(Tg); // Entfernen
    			panel[w].Be.setVisible(true);
    			
    			if(Integer.parseInt(Tg.getText())==panel[w].g*panel[w].h) {
    				this.remove(panel[w]);
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
