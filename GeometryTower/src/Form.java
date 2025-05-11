import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Form extends JPanel implements WindowListener, ActionListener{
public int g; //public weil brauchen es in Fenster
public int h; //public weil brauchen es in Fenster.; Mindestens 25 sonst zu klein
public int g2; //public weil brauchen es in Fenster
public int h2; //public weil brauchen es in Fenster.; Mindestens 25 sonst zu klein
public int scale=8;
public int x = 0;
public int y = 505;
public JButton Be;
private JTextField Tg;
private JLabel En1;
private JLabel En2;
private Random random;
public int mittelx;
public int mittely;

public int diff = 19;

public boolean defeated = false;

private int s = 10; //speed

public int shapeType = 0; // 0 für Rechteck, 1 für Dreieck


private PanelListener listener; // Interface für die Kommunikation



public Form(int w, int was, int difficutly) {
	diff = difficutly;
	random = new Random();
	g=7;
	random = new Random();
	h=7;
	
	random = new Random();
	g2 = random.nextInt(diff) + 2; // ergibt Werte von 2 bis 20

	h2 = random.nextInt(diff) + 2;
	
	
if (was == 0) {
Rechteck();
}
else if (was==1) {
Dreieck();
}
else if (was==2) {
	g=h;
Kreis();
}

mittelx=(int) (x+(0.5*g*scale));
mittely=(int) (y+(0.5*h*scale));

shapeType=was; // Setze den Typ (0 = Rechteck, 1 = Dreieck)
//this.add(panel);
repaint();
this.setVisible(true);
addButton(w);


java.util.function.Supplier<Integer> speedSupplier = () -> s;

movePanelSmoothThread(205, 505, speedSupplier, () -> {
    movePanelSmoothThread(205, 205, speedSupplier, () -> {
        movePanelSmoothThread(505, 205, speedSupplier, () -> {
            movePanelSmoothThread(505, 605, speedSupplier, () -> {
                movePanelSmoothThread(905, 605, speedSupplier, () -> {
                    movePanelSmoothThread(905, 405, speedSupplier, () -> {
                        movePanelSmoothThread(1600, 405, speedSupplier, null);
                    });
                });
            });
        });
    });
});


if (was == 0) {
	En1 = new JLabel(String.valueOf(g2) + "+" + String.valueOf(h2));
	En1.setLocation(0, h*scale /2-10);
	En1.setSize(40, 20);
	En1.setForeground(Color.WHITE);
	En1.setOpaque(false);
	this.add(En1);
	}
else if (was == 1) {
	En1 = new JLabel(String.valueOf(g2));
	En1.setLocation(0, h*scale /2-10);
	En1.setSize(40, 20);
	En1.setForeground(Color.WHITE);
	En1.setOpaque(false);
	this.add(En1);
	repaint();
}
else if (was == 2) {
	En1 = new JLabel(String.valueOf(g2) + "*" + String.valueOf(h2));
	En1.setLocation(0, h*scale /2-10);
	En1.setSize(40, 20);
	En1.setForeground(Color.WHITE);
	En1.setOpaque(false);
	this.add(En1);
}



}

public void onButtonClicked(int w) {
    Tg = new JTextField();
    
    Tg.setBounds(10, 0, 50, 25);
    this.add(Tg); // <- wichtig: ohne Layer-Index!
    this.revalidate(); // <- neu
    this.repaint();    // <- neu
    

    Tg.addActionListener(e -> {
        //System.out.println(Tg.getText() + " " + (this.g2 * this.h2));
        this.remove(Tg);
        Be.setVisible(true);

        if  (this.shapeType == 0) {
            if (Integer.parseInt(Tg.getText()) == this.g2 + this.h2){
                Container parent = this.getParent(); // <-- wichtiger Fix
                if (parent != null) {
                	if (listener != null) {
                	    listener.onPanelRemoved(this); // << HIER
                	}
                    parent.remove(this); // statt this.remove(this)
                }
                this.defeated = true;
                Fenster.defeat += 1;
                System.out.println(Fenster.defeat);
                GamePanel.setGeld(10);
            }
        }
        else if (this.shapeType == 1){
            if (Integer.parseInt(Tg.getText()) == this.g2 * this.g2){
                Container parent = this.getParent();
                if (parent != null) {
                	if (listener != null) {
                	    listener.onPanelRemoved(this); // << HIER
                	}
                    parent.remove(this); // statt this.remove(this)
                }
                this.defeated = true;
                Fenster.defeat += 1;
                System.out.println(Fenster.defeat);
                GamePanel.setGeld(20);
            }
        }
        else if (this.shapeType == 2) {
            if (Integer.parseInt(Tg.getText()) == this.g2 * this.h2){
                Container parent = this.getParent();
                if (parent != null) {
                	if (listener != null) {
                	    listener.onPanelRemoved(this); // << HIER
                	}
                    parent.remove(this); // statt this.remove(this)
                }
                this.defeated = true;
                GamePanel.setGeld(30);
                Fenster.defeat += 1;
                //System.out.println(Fenster.defeat);
            }
        }
        
        this.revalidate();
        this.repaint();
    });
    Tg.requestFocus();
}


public void addButton(int w) {
Be = new JButton();
Be.setLayout(null);
Be.setSize(g*scale, h*scale);
Be.setLocation(0, 0);
//Button unsichtbar
Be.setOpaque(false);
Be.setContentAreaFilled(false);
Be.setBorderPainted(false);
//Button unsichtbar
this.add(Be);


Be.addActionListener(e -> {
if (listener != null) {
onButtonClicked(w); // Event auslösen
}
Be.setVisible(false);
this.revalidate();
this.repaint();
});

}

public void Rechteck() {
this.setLayout(null);
this.setBackground(Color.BLACK); // Das schwarze Hintergrund muss hier nicht mehr sein
this.setOpaque(false); // Hiermit machen wir das Panel transparent
this.setSize(g * 100, h * 100);
this.setLocation(x, y);
}

public void Dreieck() {
this.setLayout(null);
this.setBackground(Color.BLACK); // Das schwarze Hintergrund muss hier nicht mehr sein
this.setOpaque(false); // Hiermit machen wir das Panel transparent
this.setSize(g * 100, h * 100);
this.setLocation(x, y);
}

public void Kreis() {
	this.setLayout(null);
	this.setBackground(Color.BLACK); // Das schwarze Hintergrund muss hier nicht mehr sein
	this.setOpaque(false); // Hiermit machen wir das Panel transparent
	this.setSize(g * 100, h * 100);
	this.setLocation(x, y);
}

@Override
protected void paintComponent(Graphics g) {
super.paintComponent(g); // Ruft paintComponent von JPanel auf, um das Panel zu initialisieren

if (shapeType == 0) {
// Rechteck zeichnen, wenn shapeType == 0
g.setColor(Color.RED); // Setze die Farbe des Rechtecks auf rot
g.fillRect(0, 0, this.g* scale, this.h* scale); // Rechteck an (50, 50) mit der Breite g und der Höhe h zeichnen
}
else if (shapeType == 1) {
// Dreieck zeichnen, wenn shapeType == 1
int[] xPoints = {0, 0 + this.g / 2* scale, 0 + this.g* scale}; // Koordinaten der Ecken des Dreiecks
int[] yPoints = {0 + this.h* scale, 0, 0 + this.h* scale}; // Höhe und Basis des Dreiecks
g.setColor(Color.GREEN); // Setze die Farbe des Dreiecks auf grün
g.fillPolygon(xPoints, yPoints, 3); // Gefülltes Dreieck zeichnen
}
else if (shapeType == 2) {
    // Kreis zeichnen, wenn shapeType == 2
    g.setColor(Color.BLUE); // Setze die Farbe des Kreises auf blau
    g.fillOval(0, 0, this.h*scale, this.h*scale); // Kreis an (0, 0) mit dem Durchmesser h zeichnen
}
}



public void defeated() {


//was passiert wenn gelöscht

}


private void movePanelSmoothThread(int targetX, int targetY, java.util.function.Supplier<Integer> speedSupplier, Runnable onComplete) {
    new Thread(() -> {
        while (x != targetX || y != targetY) {
//        	System.out.println(s);
        	for (int i=0; i<=Fenster.towerzahl;i++) {
        		mittelx=(int) (x+(0.5*g*scale));
        		mittely=(int) (y+(0.5*h*scale));
        		//System.out.println((Fenster.towerpos[i][0]-mittelx)*(Fenster.towerpos[i][0]-mittelx)+(Fenster.towerpos[i][1]-mittely)*(Fenster.towerpos[i][1]-mittely));
//    			System.out.println(Tower.radius/2);
        		//System.out.println(mittelx);
        		//System.out.println(x);
        		//s-=1;
        		if (Math.sqrt((Fenster.towerpos[i][0]-mittelx)*(Fenster.towerpos[i][0]-mittelx)+(Fenster.towerpos[i][1]-mittely)*(Fenster.towerpos[i][1]-mittely)) < TowerRange.range/2) {
        		    s = 20;
//        		    System.out.println("kach");
        		}
//        		if (Math.sqrt((Fenster.towerpos[i][0]-mittelx)*(Fenster.towerpos[i][0]-mittelx)+(Fenster.towerpos[i][1]-mittely)*(Fenster.towerpos[i][1]-mittely)) < Tower.radius/2) {
//        			Fenster.towerpos[i][0]=0;
//        			Fenster.towerpos[i][1]=0;
//        			
//        			Fenster.tower[i].destroytower();
//        			Fenster.trange[i].destroyRange();
//
//        			System.out.println("kach");
//        			s = 10;
//        		  } 
        		else if (Math.sqrt((Fenster.towerpos[i][0]-mittelx)*(Fenster.towerpos[i][0]-mittelx)+(Fenster.towerpos[i][1]-mittely)*(Fenster.towerpos[i][1]-mittely)) > TowerRange.range/2) {
        			if (Fenster.towerpos[i][0] != 0) {
       
        			s = 10;
        			}
        		}}
        		//System.out.println(Fenster.towerpos[0][0]);
        		//System.out.println(Fenster.towerpos[0][1]);
            if (x < targetX) x++;
            if (x > targetX) x--;
            if (y < targetY) y++;
            if (y > targetY) y--;

            this.setLocation(x, y);

            if (x == 1600 && !defeated) {
            	Fenster.defeat += 1;
                GamePanel.setLeben(10);
            }

            try {
                int speed = speedSupplier.get(); // Dynamische Geschwindigkeit
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (onComplete != null) {
            onComplete.run();
        }
    }).start();
}







public void setPanelListener(PanelListener listener) {
this.listener = listener;
}

// Interface für das Event
public interface PanelListener {
void onButtonClicked(int w);

void onPanelRemoved(Form form);
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