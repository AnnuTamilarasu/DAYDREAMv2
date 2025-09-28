import java.awt.event.*;
import javax.swing.*;

public class GamePg extends JPanel implements ActionListener {

    JButton[] cardButtons = new JButton[7];
    final String CardPath = "1.png";
    ParentRoomPanel parentRoom;

    public GamePg() {
        setLayout(null);

        JLabel background = new JLabel(new ImageIcon("mafiaGame.png"));
        background.setBounds(0, 0, 1500, 800);
        background.setLayout(null);
        add(background);

        int panelWidth = 1500;
        int numCards = cardButtons.length;

        int spacing = 20;
        int totalSpacing = (numCards + 1) * spacing;
        int availableWidth = panelWidth - totalSpacing;
        int cardWidth = availableWidth / numCards;

        int originalWidth = 300;
        int originalHeight = 450;
        double aspectRatio = (double) originalHeight / originalWidth;
        int cardHeight = (int) (cardWidth * aspectRatio);

        for (int i = 0; i < numCards; i++) {
            int x = spacing + i * (cardWidth + spacing);
            int y = 350 - (cardHeight / 2); // moved further up
            cardButtons[i] = new JButton(new ImageIcon(CardPath));
            setupButton(cardButtons[i], x, y, cardWidth, cardHeight);
            background.add(cardButtons[i]);
        }


        parentRoom = new ParentRoomPanel();
        parentRoom.setBounds(0, 0, 1500, 800);
        add(parentRoom);

        new Audio("tickingClock.wav");
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
        for (int i = 0; i < cardButtons.length; i++) {
            if (e.getSource() == cardButtons[i]) {
                System.out.println("Card " + (i + 1) + " clicked!");
            }
        }
    }
}
