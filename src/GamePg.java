import java.awt.event.*;
import javax.swing.*;

public class GamePg extends JPanel implements ActionListener {

    JButton[] cardButtons = new JButton[7];
    String CardPath = "1.png";
    ParentRoomPanel parentRoom;
    boolean[] isClicked = new boolean[7];

    long lastKillTime = 0;
    final int COOLDOWN = 10000;
    long phaseStartTime;

    public GamePg() {
        setLayout(null);
        phaseStartTime = System.currentTimeMillis();

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
            int y = 350 - (cardHeight / 2);
            cardButtons[i] = new JButton(new ImageIcon(CardPath));
            setupButton(cardButtons[i], x, y, cardWidth, cardHeight);
            background.add(cardButtons[i]);
        }

        parentRoom = new ParentRoomPanel();
        parentRoom.setBounds(0, 0, 1500, 800);
        add(parentRoom);

        new Audio("tickingClock.wav");
    }

    public void setupButton(JButton button, int x, int y, int width, int height) {
        button.setBounds(x, y, width, height);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long now = System.currentTimeMillis();

        if (now - phaseStartTime < 10000) {
            System.out.println("Reading clues phase: wait " + ((10000 - (now - phaseStartTime)) / 1000) + "s");
            return;
        }

        if (now - lastKillTime < COOLDOWN) {
            System.out.println("Cooldown active: wait " + ((COOLDOWN - (now - lastKillTime)) / 1000) + "s");
            return;
        }

        for (int i = 0; i < cardButtons.length; i++) {
            if (e.getSource() == cardButtons[i] && !isClicked[i]) {
                isClicked[i] = true;
                cardButtons[i].setIcon(new ImageIcon("3.png"));
                System.out.println("Card " + (i + 1) + " selected!");
                lastKillTime = now;
                break;
            }
        }
    }

}
