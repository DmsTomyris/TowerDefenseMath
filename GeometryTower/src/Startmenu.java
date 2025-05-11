import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Startmenu extends JFrame implements WindowListener {
    
    public static Fenster Tutorial;
    public static Fenster Endlos;
    private JPanel buttonPanel;

    public Startmenu() {
        JFrame frame = new JFrame("Startmenü");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1175, 600);
        frame.setLayout(new BorderLayout());

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton tutButton = new JButton("Tutorial");
        JButton levelButton = new JButton("Level");
        JButton playButton = new JButton("Endlos");

        // Größe festlegen für alle Buttons
        Dimension buttonSize = new Dimension(300, 70);
        JButton[] buttons = {tutButton, levelButton, playButton};
        for (JButton b : buttons) {
            b.setPreferredSize(buttonSize);
            b.setMinimumSize(buttonSize);
            b.setMaximumSize(buttonSize);
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        tutButton.addActionListener(e -> Tutorial = new Fenster("Tutorial", 0));

        levelButton.addActionListener(e -> {
            buttonPanel.removeAll();
            for (int i = 1; i <= 5; i++) {
                final int levelNum = i;
                JButton lvlButton = new JButton("Level " + levelNum);
                lvlButton.setPreferredSize(buttonSize);
                lvlButton.setMinimumSize(buttonSize);
                lvlButton.setMaximumSize(buttonSize);
                lvlButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                lvlButton.addActionListener(ev -> new Fenster("lvl " + levelNum, levelNum));
                buttonPanel.add(lvlButton);
                if (i < 5) {
                    buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                }
            }
            buttonPanel.revalidate();
            buttonPanel.repaint();
        });

        playButton.addActionListener(e -> Endlos = new Fenster("Endlos", 6));

        buttonPanel.add(Box.createVerticalGlue());
        for (int i = 0; i < buttons.length; i++) {
            buttonPanel.add(buttons[i]);
            if (i < buttons.length -1) {
                buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        buttonPanel.add(Box.createVerticalGlue());

        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override public void windowOpened(WindowEvent e) { System.exit(0); }
    @Override public void windowClosing(WindowEvent e) {}
    @Override public void windowClosed(WindowEvent e) {}
    @Override public void windowIconified(WindowEvent e) {}
    @Override public void windowDeiconified(WindowEvent e) {}
    @Override public void windowActivated(WindowEvent e) {}
    @Override public void windowDeactivated(WindowEvent e) {}

    public static void main(String[] args) {
        new Startmenu();
    }
}
