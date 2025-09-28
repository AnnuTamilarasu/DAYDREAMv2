import java.awt.event.*;
import javax.swing.*;
import java.util.List;

public class GamePg extends JPanel implements ActionListener {

    JButton[] cardButtons = new JButton[7];
    String CardPath = "1.png";
    ParentRoomPanel parentRoom;
    boolean[] isClicked= new boolean[7];

    //Label
    String currLabelPath = new String();
    JLabel[] labelButtons = new JLabel[7];
    //label path
    String lp1 = "tbd";
    String lp2 = "tbd";
    String lp3 = "tbd";
    String lp4 = "tbd";
    String lp5 = "tbd";
    String lp6 = "tbd";
    String lp7 = "tbd";
    // Create an immutable List of Strings
    List<String> labelPath = List.of(lp1, lp2, lp3, lp4, lp5, lp6, lp7);


    public GamePg() {
        setLayout(null);

        JLabel background = new JLabel(new ImageIcon("mafiaGame.png"));
        background.setBounds(0, 0, 1500, 800);
        background.setLayout(null);
        add(background);

        int panelWidth = 1500;
        int numCards = cardButtons.length;

        int spacing = 20;
        int totalSpacing = (numCards + 1) * spacing;
        int availableWidth = panelWidth - totalSpacing;
        int cardWidth = availableWidth / numCards;

        int originalWidth = 300;
        int originalHeight = 450;
        double aspectRatio = (double) originalHeight / originalWidth;
        int cardHeight = (int) (cardWidth * aspectRatio);

        //Labels
        int space = 20;
        int panWidth = 1500;
        int ogWidthl = 1000;
        int ogHeightl = 395;
        int numLabels = cardButtons.length;
//        int widthVal =
//        double aspectRatiol = (double) ogWidthl / ogHeightl;
//        int totSpacel = (labelButtons.length + 1) * spacing;
//        int avaWidthl = panelWidth - totSpacel;
//        int labelWidth = availableWidth / numLabels;
//        int labelHeight = (int) (avaWidthl * aspectRatiol); //aspectRatiol adjusts height
        int[] lx = new int[numLabels];
        for (int i = 0; i < numLabels; i++) {
            lx[i] = 10 + i;
        }
        int ly = 600; //screen is 800
        int lwidth = 134;
        double lheight = (double) (ogWidthl * ogHeightl) / (double) lwidth;
        System.out.println("lheight:" + lheight);



        //Buttons
        for (int i = 0; i < numCards; i++) {
            int x = spacing + i * (cardWidth + spacing);
            int y = 350 - (cardHeight / 2); // moved further up
            cardButtons[i] = new JButton(new ImageIcon(CardPath));
            setupButton(cardButtons[i], x, y, cardWidth, cardHeight);
            background.add(cardButtons[i]);
        }

        //Labels
        for (int i = 0; i < numLabels; i++) {
//            int lx = spacing + i * (labelWidth + spacing);
//            int ly = 350 - (labelHeight / 2); // moved further up
            currLabelPath = labelPath.get(i);
            System.out.println("currLabelPath:" + currLabelPath);
            labelButtons[i] = new JLabel(new ImageIcon(currLabelPath));
            labelButtons[i].setBounds(lx[i], ly, lwidth, (int) lheight);
            background.add(labelButtons[i]);
        }


        parentRoom = new ParentRoomPanel();
        parentRoom.setBounds(0, 0, 1500, 800);
        add(parentRoom);

        new Audio("tickingClock.wav");
    }

    public void setupButton(JButton button, int xPos, int yPos, int width, int height) {
        button.setBounds(xPos, yPos, width, height);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < cardButtons.length; i++) {
            if (e.getSource() == cardButtons[i]) {
                isClicked[i] = !isClicked[i]; // toggle state

                if (isClicked[i]) {
                    cardButtons[i].setIcon(new ImageIcon("3.png")); // card selected
                    System.out.println("Card " + (i + 1) + " selected!"); //comment cegemm

                    //WIN CONDITION GOES HERE
                    //change screen to different panel
                    //if i = 3 (character #4 (the Mob Boss))
                    if (i == 3) {
                        //replace content in JFrame with WinPopup
                        //Here:
                        HomePg hp = HomePg.getInstance();
                        Plykl WinPopup = new Plykl();
//                        hp.setContentPane(WinPopup);






                    }
                }
            }
        }
    }

}
