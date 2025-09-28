import javax.swing.*;

public class WinCondition extends JFrame{
    public static void main(String[] args) {
        new WinCondition();
    }

    public WinCondition() {
        JOptionPane.showMessageDialog(null, "You won! Sacrifices must be made", "Win",
                JOptionPane.INFORMATION_MESSAGE);

        //end java program
        System.exit(0);

    }
}