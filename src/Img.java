import javax.swing.*;

public class Img {
    static JLabel background;
    static JLabel imgICON;

    public static JLabel img(JPanel b, String path) {
        ImageIcon img = new ImageIcon(path);
        imgICON = new JLabel("", img, JLabel.CENTER);
        imgICON.setBounds(0, 0, 400, 300);
        b.add(imgICON);
        return imgICON;
    }

    public static JLabel bg(JPanel b, String path) {
        ImageIcon bgImg = new ImageIcon(path);
        background = new JLabel("", bgImg, JLabel.CENTER);
        background.setBounds(0, 0, 1500, 800);
        return background;
    }

}