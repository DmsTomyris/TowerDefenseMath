import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class TowerRange extends JPanel implements WindowListener, ActionListener{

	public TowerRange(int range, int posx, int posy) {
		this.setLayout(null);
		this.setBackground(Color.BLACK); // Das schwarze Hintergrund muss hier nicht mehr sein
		this.setOpaque(true); // Hiermit machen wir das Panel transparent
		this.setSize(range + 10, range + 10); // Panelgröße etwas größer als der Kreis
		this.setLocation(posx, posy);
		System.out.println(posy);
		
		repaint();
		this.setVisible(true);
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Basisimplementierung aufrufen

        // Graphics in Graphics2D umwandeln, um erweiterte Features zu nutzen
        Graphics2D g2d = (Graphics2D) g;

        // Rendering-Hints aktivieren (z. B. Kantenglättung)
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Hintergrund zeichnen
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Transparenz setzen: AlphaComposite mit 50% Deckkraft (0.5f)
        float alpha = 0.5f; // Wertebereich: 0.0f (vollständig transparent) bis 1.0f (vollständig undurchsichtig)
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(ac);

        // Halbtransparentes Rechteck zeichnen
        g2d.setColor(Color.BLUE);
        g2d.fill(new Rectangle2D.Double(50, 50, 200, 100));
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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
