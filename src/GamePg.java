import java.awt.event.*;
import javax.swing.*;

public class GamePg extends JPanel implements ActionListener {

    JButton cardButton;
    final String CardPath = "DODplay.png";

    public GamePg() {
        setLayout(null);

        // Background
        JLabel background = new JLabel(new ImageIcon("mafiaGame.png"));
        background.setBounds(0, 0, 1500, 800);
        background.setLayout(null);
        add(background);

        // Card Button
        cardButton = new JButton(new ImageIcon(CardPath));
        setupButton(cardButton, 570, 770, 350, 170);
        background.add(cardButton);
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
        if (e.getSource() == cardButton) {
            // For now, you can print something or call a method to advance the game
            System.out.println("Card clicked!");
            // Example: swap to a new panel or update game state
        }
    }
}
