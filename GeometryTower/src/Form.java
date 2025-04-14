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
public int scale=8;
public int x = 0;
public int y = 505;
public JButton Be;
private JTextField Tg;
private JLabel En1;
private JLabel En2;
private Random random;

public boolean defeated = false;


private int s = 10; //speed

public int shapeType = 0; // 0 für Rechteck, 1 für Dreieck


private PanelListener listener; // Interface für die Kommunikation



public Form(int w, int was) {
	
	random = new Random();
	g=random.nextInt(5) + 5;
	if (!(g % 2 == 0)) {
        g+=1;
    } 
	random = new Random();
	h=random.nextInt(5) + 5;
	
	
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


shapeType=was; // Setze den Typ (0 = Rechteck, 1 = Dreieck)
//this.add(panel);
repaint();
this.setVisible(true);
addButton(w);


movePanelSmoothThread(205, 505, s, () -> {
movePanelSmoothThread(205, 205, s, () -> {
movePanelSmoothThread(505, 205, s, () -> {
movePanelSmoothThread(505, 605, s, () -> {
movePanelSmoothThread(905, 605, s, () -> {
movePanelSmoothThread(905, 405, s, () ->{
movePanelSmoothThread(1600, 405, s, null);
});
});
});
});
}); // Beispiel für die nächste Bewegung
});

if (was <2) {
	En1 = new JLabel(String.valueOf(h));
	En1.setLocation(0, h*scale /2-10);
	En1.setSize(40, 20);
	En1.setForeground(Color.WHITE);
	En1.setOpaque(false);
	this.add(En1);

	En2 = new JLabel(String.valueOf(g));
	En2.setLocation((g*scale-20)/2, h*scale-20);
	En2.setSize(40, 20);
	En2.setForeground(Color.WHITE);
	En2.setOpaque(false);
	this.add(En2);
	repaint();
}
else if (was == 2) {
	En1 = new JLabel(String.valueOf(h));
	En1.setLocation(0, h*scale /2-10);
	En1.setSize(40, 20);
	En1.setForeground(Color.WHITE);
	En1.setOpaque(false);
	this.add(En1);
}


}

public void onButtonClicked(int w) {
    Tg = new JTextField();
    Tg.setText("625");
    Tg.setBounds(10, 10, 300, 20);
    this.add(Tg); // <- wichtig: ohne Layer-Index!
    this.revalidate(); // <- neu
    this.repaint();    // <- neu

    Tg.addActionListener(e -> {
        System.out.println(Tg.getText() + " " + (this.g * this.h));
        this.remove(Tg);
        Be.setVisible(true);

        if  (this.shapeType == 0) {
            if (Integer.parseInt(Tg.getText()) == this.g * this.h){
                Container parent = this.getParent(); // <-- wichtiger Fix
                if (parent != null) {
                    parent.remove(this); // statt this.remove(this)
                }
                this.defeated = true;
                GamePanel.setGeld(100);
            }
        }
        else if (this.shapeType == 1){
            if (Integer.parseInt(Tg.getText()) == this.g * this.h * 0.5){
                Container parent = this.getParent();
                if (parent != null) {
                    parent.remove(this);
                }
                this.defeated = true;
                GamePanel.setGeld(100);
            }
        }
        else if (this.shapeType == 2) {
            if (Integer.parseInt(Tg.getText()) == this.h * this.h * 3){
                Container parent = this.getParent();
                if (parent != null) {
                    parent.remove(this);
                }
                this.defeated = true;
                GamePanel.setGeld(100);
            }
        }

        this.revalidate();
        this.repaint();
    });
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
System.out.println("Test");

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

private void movePanelSmoothThread(int targetX, int targetY, int speed, Runnable onComplete) {
new Thread(() -> {
while (x != targetX || y != targetY) {
if (x < targetX) x++;
if (x > targetX) x--;
if (y < targetY) y++;
if (y > targetY) y--;

this.setLocation(x, y);
if (x==1600 && defeated == false) {
System.out.println("you lost");
GamePanel.setLeben(10);
}
try {
Thread.sleep(speed); // Wartezeit zwischen Bewegungen
} catch (InterruptedException e) {
e.printStackTrace();
}
}
// Callback aufrufen, wenn die Bewegung abgeschlossen ist
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