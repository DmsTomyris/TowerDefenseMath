import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Form extends JPanel implements WindowListener, ActionListener{
    public int g=25; //public weil brauchen es in Fenster
    public int h=25; //public weil brauchen es in Fenster.; Mindestens 25 sonst zu klein
    private int x = 100;
    private int y = 50;
    public JButton Be;
    private JTextField Tg;
    
    private PanelListener listener; // Interface für die Kommunikation


	
	public Form(int w) {
		this.setLayout(null);
        this.setBackground(Color.BLUE);
        this.setSize(g, h);
        this.setLocation(x, y);
        //this.add(panel);
        movePanelSmoothThread(1000, 200, 20);
    	repaint();
    	this.setVisible(true);
    	addButton(w);
	}
	
	public void addButton(int w) {
	System.out.println(w);
	Be = new JButton();
	Be.setLayout(null);
    Be.setSize(g, h);
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
