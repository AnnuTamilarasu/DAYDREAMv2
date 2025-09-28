import javax.swing.*;

public class LoseCondition extends JFrame{
    public LoseCondition() {
        JOptionPane.showMessageDialog(null, "You lose! Sacrifices must be made", "Lose",
                JOptionPane.INFORMATION_MESSAGE);
        //Stop java
        System.exit(0);
    }
}