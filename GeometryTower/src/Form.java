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
    private int g=10;
    private int h=10;
    private int x = 100;
    private int y = 50;
    private JButton Be;
    private JTextField Tg;
	
	public Form() {
		System.out.println("Alge");
		
        this.setBackground(Color.BLUE);
        this.setSize(g*20, h*20);
        this.setLocation(x, y);
        //this.add(panel);
        this.setVisible(true);
        movePanelSmoothThread(300, 50, 10);
        
        Tg=new JTextField();
		Tg.setText("500");
		Tg.setLocation(10, 10);
		Tg.setSize(300, 20);
		Tg.addActionListener(e -> {
			System.out.println(Tg.getText()+" "+(g*h));
			this.remove(Tg); // Entfernen
			this.remove(Be);
			this.remove(this); // Enter gedrückt → "Hallo"
			this.revalidate(); // Layout aktualisieren
			this.repaint();}); // Fenster neu zeichnen
		
		
		Be = new JButton("Berechne");
        Be.setSize(g*20, h*20);
    	Be.setLocation(100, 50);
    	this.add(Be);
    	Be.addActionListener(this);
    	repaint();
    	this.setVisible(true);
	}

	private void movePanelSmoothThread(int targetX, int targetY, int speed) {
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
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==Be) {
			this.add(Tg); // add tf
			this.revalidate();
			this.repaint();}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
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
