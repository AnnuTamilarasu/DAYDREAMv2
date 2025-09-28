import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tutorial extends JPanel implements KeyListener, ActionListener {
    JLabel background;
    Font f = new Font("Arial", Font.BOLD, 30);
    final String TutorialImgPath = "rhgeth";

    // Labels array and current index for navigation
    JLabel[] labels = new JLabel[3];
    int currentLabelIndex = 0;
    Color highlightColor = new Color(255, 0, 0); // Red for highlighted
    Color normalColor = new Color(85, 85, 85);   // Gray for normal

    public Tutorial() {
        this.setLayout(null);
        this.setFocusable(true); // Make panel focusable to receive key events
        this.addKeyListener(this);

        background = Img.bg(this, TutorialImgPath);
        if (background != null) {
            this.add(background);
        }

        JPanel gifPanel = new JPanel(null);
        gifPanel.setBounds(350, 60, 500, 500);
        gifPanel.setOpaque(false);
        this.add(gifPanel);

        // Initialize labels
        initializeLabels();

        // Highlight the first label initially
        highlightCurrentLabel();
    }

    public void initializeLabels() {
        // Create label 1
        labels[0] = new JLabel("TUTORIAL INFO 1");
        labels[0].setFont(f);
        labels[0].setForeground(normalColor);
        labels[0].setBounds(1070, 230, 200, 100);
        labels[0].setVisible(true);

        // Create label 2
        labels[1] = new JLabel("TUTORIAL INFO 2");
        labels[1].setFont(f);
        labels[1].setForeground(normalColor);
        labels[1].setBounds(1070, 345, 300, 100);
        labels[1].setVisible(true);

        // Create label 3
        labels[2] = new JLabel("TUTORIAL INFO 3");
        labels[2].setFont(f);
        labels[2].setForeground(normalColor);
        labels[2].setBounds(320, 590, 900, 100);
        labels[2].setVisible(true);

        // Add all labels to the panel
        for (JLabel label : labels) {
            this.add(label);
            this.setComponentZOrder(label, 0);
        }
    }

    private void highlightCurrentLabel() {
        // Reset all labels to normal color
        for (JLabel label : labels) {
            label.setForeground(normalColor);
        }

        // Highlight current label
        labels[currentLabelIndex].setForeground(highlightColor);
        this.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                currentLabelIndex = (currentLabelIndex - 1 + labels.length) % labels.length;
                highlightCurrentLabel();
                break;

            case KeyEvent.VK_RIGHT:
                currentLabelIndex = (currentLabelIndex + 1) % labels.length;
                highlightCurrentLabel();
                break;

            case KeyEvent.VK_ENTER:
                // Skip tutorial and go directly to GamePg
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                if (parentFrame != null) {
                    GamePg gamePanel = new GamePg();
                    parentFrame.setContentPane(gamePanel);
                    parentFrame.revalidate();
                    parentFrame.repaint();
                }
                break;
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == play) {
            Audio.stopClip();
            GamePg gamePanel = new GamePg();
            setContentPane(gamePanel);
        }
    }

}