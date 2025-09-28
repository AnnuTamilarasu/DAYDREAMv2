import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tutorial extends JPanel implements KeyListener, ActionListener {

    JButton play;
    JLabel[] labels = new JLabel[5];
    int currentLabelIndex = 0;
    Color highlightColor = new Color(255, 214, 214);
    Color normalColor = new Color(85, 85, 85);

    Font labelFont = new Font("Arial", Font.BOLD, 30);
    Font timeFont = new Font("Arial", Font.BOLD, 80);

    Image backgroundImage;
    JLabel time;
    String info0 = "Use arrow keys (<,>) to navigate through the rules.";
    String info1 = "You have 2 minutes!!! Time is ticking...";
    String info2 = "For the first 20 seconds, you can only read the clues.";
    String info3 = "In the next 20 seconds you can kill a suspect.";
    String info4 = "This repeats until you run out of time.";
    String info5 = "Press enter to go to the Game";

    public Tutorial() {
        setLayout(null);
        setFocusable(true);
        addKeyListener(this);

        backgroundImage = new ImageIcon("mafiaGame.png").getImage();

        time = new JLabel("02:00", SwingConstants.CENTER);
        time.setFont(timeFont);
        time.setForeground(new Color(110, 0, 0));
        add(time);

        initializeLabels();
        createPlayButton();
        highlightCurrentLabel();
    }

    private void initializeLabels() {
        labels[0] = createLabel(info0);
        labels[1] = createLabel(info1);
        labels[2] = createLabel(info2);
        labels[3] = createLabel(info3);
        labels[4] = createLabel(info4);
        labels[4] = createLabel(info5);

        for (JLabel label : labels) add(label);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(labelFont);
        label.setForeground(normalColor);
        return label;
    }

    private void createPlayButton() {
        play = new JButton("PLAY");
        play.setFont(new Font("Arial", Font.BOLD, 40));
        play.setBorderPainted(false);
        play.setFocusPainted(false);
        play.setContentAreaFilled(false);
        play.addActionListener(this);
        add(play);
    }

    private void highlightCurrentLabel() {
        for (JLabel label : labels) label.setForeground(normalColor);
        labels[currentLabelIndex].setForeground(highlightColor);
        repaint();
    }

    private void goToGame() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (parentFrame != null) {
            Audio.stopClip();
            parentFrame.setContentPane(new GamePg());
            parentFrame.revalidate();
            parentFrame.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                currentLabelIndex = (currentLabelIndex - 1 + labels.length) % labels.length;
                highlightCurrentLabel();
                break;
            case KeyEvent.VK_RIGHT:
                currentLabelIndex = (currentLabelIndex + 1) % labels.length;
                highlightCurrentLabel();
                break;
            case KeyEvent.VK_ENTER:
                goToGame();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play) goToGame();
    }

    @Override
    public void doLayout() {
        super.doLayout();
        int width = getWidth();

        time.setBounds(width / 2 - 150, 50, 300, 100);

        int y = 200;
        for (JLabel label : labels) {
            label.setBounds(width / 2 - 450, y, 900, 50);
            y += 120;
        }

        play.setBounds(width / 2 - 175, y, 350, 170);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
