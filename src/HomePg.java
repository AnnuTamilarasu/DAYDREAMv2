import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePg extends JFrame implements ActionListener {

    JButton play;
    JButton tutorial;
    JPanel homePanel;
    JLabel background;
    final String HomePath = "Untitled_design-removebg-preview.png"; // Replace with actual image path

    public HomePg() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0, 0, 1500, 800);
        this.setResizable(false);
        homePanel = new JPanel(null);
        elements(homePanel);
        this.setContentPane(homePanel);
        this.setVisible(true);
    }

    public void elements(JPanel panel) {
        play = new JButton("Start Game");
        play.setBounds(560, 300, 200, 60);
        play.setFont(new Font("Arial", Font.BOLD, 18));
        play.addActionListener(this);
        play.setFocusPainted(false);
        panel.add(play);

        tutorial = new JButton("Tutorial");
        setupButton(tutorial, 560, 400);
        panel.add(tutorial);

        JLabel label = new JLabel("Welcome to the Home Page");
        label.setBounds(520, 200, 300, 50);
        panel.add(label);
    }

    public void setupButton(JButton button, int xPos, int yPos) {
        button.setBounds(xPos, yPos, 200, 60);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(222, 172, 159));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(this);
        button.setContentAreaFilled(true);
        if (background != null) {
            background.setLayout(null);
            background.add(button);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play) {
            GamePg gamePanel = new GamePg();
            this.setContentPane(gamePanel);
            this.revalidate();
            this.repaint();
        } else if (e.getSource() == tutorial) {
            Tutorial tutorialPanel = new Tutorial();
            this.setContentPane(tutorialPanel);
            this.revalidate();
            this.repaint();
        }
    }
}