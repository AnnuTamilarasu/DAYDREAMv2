import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePg extends JFrame implements ActionListener {

    JButton play;
    // JButton tutorial; // optional if you add it later
    JPanel homePanel;
    JLabel background;
    final String HomePath = "mafiaHome.png";
    final String PlayPath = "DODplay.png";

    private static HomePg instance;

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

        setContentPane(homePanel);
        setVisible(true);
    }

    public void elements(JLabel container) {
        play = new JButton(new ImageIcon(PlayPath)); // assign directly to field
        setupButton(play, 570, 570, 350, 170);
        container.add(play);

        /*
        tutorial = new JButton(new ImageIcon("TutorialPath.png"));
        setupButton(tutorial, 560, 400, 280, 95);
        container.add(tutorial);
        */

    }

    // Setup button styling and placement
    public void setupButton(JButton button, int xPos, int yPos, int width, int height) {
        button.setBounds(xPos, yPos, width, height);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(this);
    }

    //WinPopup (Plykl) activated in GamePg
    public static HomePg getInstance() {
        if (instance == null) {
            instance = new HomePg();
        }
        return instance;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play) {
            GamePg gamePanel = new GamePg();
            setContentPane(gamePanel);
        }
        /*
        else if (e.getSource() == tutorial) {
            Tutorial tutorialPanel = new Tutorial();
            setContentPane(tutorialPanel);
        }
        */
        revalidate();
        repaint();
    }
}
