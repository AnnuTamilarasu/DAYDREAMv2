import javax.swing.*;
import java.awt.*;

public class Tutorial extends JPanel {
    JLabel background;
    Font f = new Font("Arial", Font.BOLD, 30);
    final String TutorialImgPath = "rhgeth";
    public Tutorial() {
        this.setLayout(null);

        Img.bg(this,TutorialImgPath);

        JPanel gifPanel = new JPanel(null);
        gifPanel.setBounds(350, 60, 500, 500);
        gifPanel.setOpaque(false);
        this.add(gifPanel);
    }

    public void label() {
        JLabel l1 = new JLabel("TUTORIAL INFO 1");
        l1.setFont(f);
        l1.setForeground(new Color(85, 85, 85));
        l1.setBounds(1070, 230, 200, 100);
        l1.setVisible(true);

        JLabel l2 = new JLabel("TUTORIAL INFO 2");
        l2.setFont(f);
        l2.setForeground(new Color(85, 85, 85));
        l2.setBounds(1070, 345, 300, 100);
        l2.setVisible(true);

        JLabel l3 = new JLabel("TUTORIAL INFO 3");
        l3.setFont(f);
        l3.setForeground(new Color(85, 85, 85));
        l3.setBounds(320, 590, 900, 100);
        l3.setVisible(true);

        this.add(l1);
        this.add(l2);
        this.add(l3);

        this.setComponentZOrder(l1, 0);
        this.setComponentZOrder(l2, 0);
        this.setComponentZOrder(l3, 0);
    }
}
