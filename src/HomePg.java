import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePg extends JFrame implements ActionListener {

    JButton play;
    JPanel homePanel;
    JLabel background;
    final String HomePath = "mafiaHome.png";
    final String PlayPath = "DODplay.png";

    public HomePg() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1500, 800);
        setResizable(false);

        homePanel = new JPanel(null);

        background = Img.bg(homePanel, HomePath);
        background.setBounds(0, 0, 1500, 800);
        background.setLayout(null);
        homePanel.add(background);

        elements(background);

        homePanel.setComponentZOrder(background, homePanel.getComponentCount() - 1);
        new Audio("homeMusic.wav",true);
        setContentPane(homePanel);
        setVisible(true);
    }

    public void elements(JLabel container) {
        play = new JButton(new ImageIcon(PlayPath));
        setupButton(play, 570, 570, 350, 170);
        container.add(play);
    }

    public void setupButton(JButton button, int xPos, int yPos, int width, int height) {
        button.setBounds(xPos, yPos, width, height);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play) {
            Tutorial tutorialPanel = new Tutorial();
            setContentPane(tutorialPanel);
            tutorialPanel.requestFocusInWindow(); // Important for key listeners
        }
        revalidate();
        repaint();
    }
}