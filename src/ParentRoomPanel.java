import javax.swing.*;
import java.awt.*;

public class ParentRoomPanel extends JPanel {

    JLabel time;
    Font f = new Font("Arial", Font.BOLD, 80);

    public ParentRoomPanel() {
        setLayout(null);
        setBounds(0, 0, 1500, 800);

        JLabel background = new JLabel(new ImageIcon("mafiaGame.png"));
        background.setBounds(0, 0, 1500, 800);
        background.setLayout(null);
        add(background);

        time = new JLabel("06:00");
        time.setFont(f);
        time.setForeground(new Color(110, 0, 0));
        time.setBounds(650, 60, 300, 100);
        time.setOpaque(false);
        background.add(time);

        ClockThread clock = new ClockThread(this, 6 * 60);
        Thread thread = new Thread(clock);
        thread.start();
    }

    public void setTimeLabel(String timeText) {
        time.setText(timeText);
    }

    private static class ClockThread implements Runnable {
        private boolean running = true;
        private ParentRoomPanel panel;
        private int seconds;

        public ClockThread(ParentRoomPanel panel, int startSeconds) {
            this.panel = panel;
            this.seconds = startSeconds;
        }

        public void setRunning(boolean running) { this.running = running; }

        @Override
        public void run() {
            while (running && seconds >= 0) {
                int mins = seconds / 60;
                int secs = seconds % 60;
                panel.setTimeLabel(String.format("%02d:%02d", mins, secs));

                if (seconds % 10 == 0 && seconds != 0) {
                    SwingUtilities.invokeLater(() -> {
                        panel.time.setFont(new Font("Arial", Font.BOLD, 87));
                    });
                    try { Thread.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
                    SwingUtilities.invokeLater(() -> {
                        panel.time.setFont(new Font("Arial", Font.BOLD, 80));
                    });
                }

                if (seconds == 0) {
                    gameOver();
                    running = false;
                }

                try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
                seconds--;
            }
        }

        private void gameOver() {
            System.out.println("Game Over!");
            JOptionPane.showMessageDialog(panel, "Time's up! Game Over!");
            Audio.clip.stop();
        }
    }
}
