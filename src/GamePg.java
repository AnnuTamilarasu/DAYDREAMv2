import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.awt.*;

public class GamePg extends JPanel implements ActionListener {

    JButton[] cardButtons = new JButton[7];
    String CardPath = "1.png";
    ParentRoomPanel parentRoom;
    boolean[] isClicked = new boolean[7];
    boolean[] clueViewed = new boolean[7];

    long gameStartTime;
    long lastKillTime = 0;
    final int PHASE_DURATION = 20000;
    final int TOTAL_GAME_TIME = 120000;
    boolean canKill = false;
    boolean hasKilledThisPhase = false;

    JLabel[] labelButtons = new JLabel[7];
    JLabel background;

    List<String> labelPath = List.of(
            "member1.png",
            "member2.png",
            "member3.png",
            "member4.png",
            "member5.png",
            "member6.png",
            "member7.png"
    );

    String[] clues = {
            "Works in accounting, very quiet",
            "Has access to company files",
            "Often seen arguing with the boss",
            "Enjoys drinking at the bar",
            "New employee, seems nervous",
            "Has been with company for 10 years",
            "Always leaves work early on Fridays"
    };

    JLabel clueDisplay;
    String winpopimg = "winningScreen.png";
    Timer phaseTimer;

    public GamePg() {
        setLayout(null);
        gameStartTime = System.currentTimeMillis();

        background = new JLabel(new ImageIcon("mafiaGame.png"));
        background.setBounds(0, 0, 1500, 800);
        background.setLayout(null);
        add(background);

        int panelWidth = 1500;
        int numCards = cardButtons.length;
        int spacing = 20;
        int cardWidth = (panelWidth - (numCards + 1) * spacing) / numCards;
        int cardHeight = (int) (cardWidth * 450.0 / 300.0);

        int ogWidthl = 1000;
        int ogHeightl = 395;
        int numLabels = cardButtons.length;
        int[] lx = new int[numLabels];
        for (int i = 0; i < numLabels; i++) lx[i] = 10 + i * 200;
        int ly = 600;
        int lwidth = 134;
        int lheight = (int) (ogHeightl * lwidth / ogWidthl);

        for (int i = 0; i < numCards; i++) {
            int x = spacing + i * (cardWidth + spacing);
            int y = 350 - (cardHeight / 2);
            cardButtons[i] = new JButton(new ImageIcon(CardPath));
            setupButton(cardButtons[i], x, y, cardWidth, cardHeight);
            background.add(cardButtons[i]);
        }

        for (int i = 0; i < numLabels; i++) {
            labelButtons[i] = new JLabel(new ImageIcon(labelPath.get(i)));
            labelButtons[i].setBounds(lx[i], ly, lwidth, lheight);
            background.add(labelButtons[i]);
        }

        clueDisplay = new JLabel("Click suspects to read clues", SwingConstants.CENTER);
        clueDisplay.setFont(new Font("Arial", Font.BOLD, 24));
        clueDisplay.setForeground(Color.WHITE);
        clueDisplay.setBounds(450, 150, 700, 50);
        clueDisplay.setOpaque(true);
        clueDisplay.setBackground(Color.BLACK);
        background.add(clueDisplay);

        parentRoom = new ParentRoomPanel();
        parentRoom.setBounds(0, 0, 1500, 800);
        add(parentRoom);

        new Audio("tickingClock.wav");
        startPhaseTimer();
    }

    public void setupButton(JButton button, int xPos, int yPos, int width, int height) {
        button.setBounds(xPos, yPos, width, height);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(this);
    }

    private void startPhaseTimer() {
        phaseTimer = new Timer(1000, e -> updatePhase());
        phaseTimer.start();
    }

    private void updatePhase() {
        long elapsedTime = System.currentTimeMillis() - gameStartTime;
        if (elapsedTime >= TOTAL_GAME_TIME) {
            showGameOverScreen();
            if (phaseTimer != null) phaseTimer.stop();
            return;
        }

        boolean newCanKill = ((int) (elapsedTime / PHASE_DURATION) % 2 == 1);

        if (!canKill && newCanKill) {
            hasKilledThisPhase = false;
            clueDisplay.setText("KILL PHASE: Click to eliminate suspects!");
            clueDisplay.setForeground(Color.RED);
        } else if (canKill && !newCanKill) {
            clueDisplay.setText("READING PHASE: Click to read clues");
            clueDisplay.setForeground(Color.WHITE);
        }

        canKill = newCanKill;
    }

    private void showWinScreen() {
        if (phaseTimer != null) phaseTimer.stop();
        Audio.stopClip();
        background.setIcon(new ImageIcon(winpopimg));
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (parentFrame != null) {
            parentFrame.revalidate();
            parentFrame.repaint();
        }
    }

    private void showGameOverScreen() {
        if (phaseTimer != null) phaseTimer.stop();
        Audio.stopClip();
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (parentFrame != null) {
            JOptionPane.showMessageDialog(parentFrame, "Game Over! Time's up!\nThe Mob Boss escaped!");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        long currentTime = System.currentTimeMillis();
        boolean gameOver = currentTime - gameStartTime >= TOTAL_GAME_TIME;

        for (int i = 0; i < cardButtons.length; i++) {
            if (e.getSource() == cardButtons[i]) {

                // Always show clues in reading phase
                if (!canKill) {
                    clueDisplay.setText(clues[i]);
                    clueDisplay.setForeground(isClicked[i] ? Color.LIGHT_GRAY : Color.YELLOW);
                    clueViewed[i] = true;
                    // Don't return here, let the loop continue to check other conditions
                    continue; // Changed from 'return' to 'continue'
                }

                // Kill phase logic
                if (!gameOver && canKill && !hasKilledThisPhase && !isClicked[i]) {
                    isClicked[i] = true;
                    cardButtons[i].setIcon(new ImageIcon("3.png"));
                    labelButtons[i].setIcon(new ImageIcon("dead.png"));
                    hasKilledThisPhase = true;
                    lastKillTime = currentTime;

                    if (i == 3) {
                        showWinScreen();
                        return;
                    }

                    clueDisplay.setText("Suspect " + (i + 1) + " eliminated!");
                    clueDisplay.setForeground(Color.RED);
                    break;
                }
            }
        }
    }
}
