import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Startmenu extends JFrame {
    public static Fenster Tutorial;
    public static Fenster Endlos;

    private JPanel buttonPanel;
    private Image backgroundImage;

    public Startmenu() {
        setTitle("Startmenü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1175, 600);
        setLocationRelativeTo(null);

        // Load background image
        backgroundImage = new ImageIcon("images/Menubild.png").getImage();

        // Create a custom panel to paint background image scaled
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image scaled to fill panel
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new GridBagLayout()); // to center button panel
        setContentPane(mainPanel);

        // Create button panel with BoxLayout vertical
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // transparent, to show background
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        Dimension buttonSize = new Dimension(300, 70);

        JButton tutButton = new JButton("Tutorial");
        JButton levelButton = new JButton("Level");
        JButton playButton = new JButton("Endlos");

        JButton[] buttons = {tutButton, levelButton, playButton};
        for (JButton b : buttons) {
            b.setPreferredSize(buttonSize);
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
            if (i < buttons.length - 1) {
                buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            }
        }
        buttonPanel.add(Box.createVerticalGlue());

        // Add button panel centered using GridBagLayout constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonPanel, gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Startmenu());
    }
}
