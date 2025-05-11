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
        frame.setLayout(null); // For absolute layout

        // Create a panel for the background with absolute layout
        JPanel backgroundPanel = new JPanel(null);
        backgroundPanel.setBounds(0, 0, 1175, 600);

        // Load and scale the image
        ImageIcon originalIcon = new ImageIcon("images/Menubild.png");
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(1175, 600, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel bildLabel = new JLabel(scaledIcon);
        bildLabel.setBounds(0, 0, 1175, 600);
        backgroundPanel.add(bildLabel);

        // Create button panel with vertical BoxLayout
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        // Set size and location of buttonPanel explicitly (centered)
        int panelWidth = 350;
        int panelHeight = 300;
        int panelX = (1175 - panelWidth) / 2;
        int panelY = (600 - panelHeight) / 2;
        buttonPanel.setBounds(panelX, panelY, panelWidth, panelHeight);

        JButton tutButton = new JButton("Tutorial");
        JButton levelButton = new JButton("Level");
        JButton playButton = new JButton("Endlos");

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
            // Also revalidate and repaint the parent container to force refresh
            Container parent = buttonPanel.getParent();
            if (parent != null) {
                parent.revalidate();
                parent.repaint();
            }
        });

        playButton.addActionListener(e -> Endlos = new Fenster("Endlos", 6));

        buttonPanel.add(Box.createVerticalGlue());
        for (int i = 0; i < buttons.length; i++) {
            buttonPanel.add(buttons[i]);
            if (i < buttons.length - 1) {
                buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            }
        }
        buttonPanel.add(Box.createVerticalGlue());

        backgroundPanel.add(buttonPanel);

        frame.add(backgroundPanel);

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

