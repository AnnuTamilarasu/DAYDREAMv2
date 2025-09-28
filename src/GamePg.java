import javax.swing.*;
import java.awt.*;

public class GamePg extends JPanel {
    JButton[] nums = new JButton[9];
    JPanel numPanel;
    JLabel background;
    String bgImgPath = "hrdhrh";
    int selectedNumber = -1;
    Font f = new Font("Arial", Font.BOLD, 50);
    /* Skibidi */
    JPanel guiPanel;

    public GamePg() {
        this.setLayout(null);

        JPanel bgPanel = new JPanel(null);
        bgPanel.setBounds(0, 0, 1500, 750);
        this.add(bgPanel);

        guiPanel = new JPanel(null);
        guiPanel.setBounds(950, 170, 300, 300);
        guiPanel.setOpaque(false);
        this.add(guiPanel);

        this.setComponentZOrder(bgPanel, this.getComponentCount() - 1);
        this.setVisible(true);
    }
}
