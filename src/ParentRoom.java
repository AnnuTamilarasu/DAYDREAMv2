import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ParentRoom extends JFrame {
    public static void main(String[] args) {
        new ParentRoom();
    }
    //instance variables
//  JFrame tef = new JFrame("temporary frame");
    JPanel tefp;


    //Clock
    JLabel time;
    private static ParentRoom instance;

    //Constructor
    public ParentRoom() {

        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //remove this for different frame
//    tef.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the layout of the content pane to null for absolute positioning
//    tef.setBounds(0,0,400,400);
        this.setLayout(null);   //tef.getContentPane().setLayout(null);

        tefp = new JPanel();


        //Clock
        time = new JLabel("00:00");
//    time.setFont(new Font("Ariel", Font.PLAIN, 16));
        tefp.add(time);





        //create label
        ImageIcon img = new ImageIcon("downloads.packman_from_internet.webp");

//    JLabel plc = new JLabel(img);  //image (as a label)
        JLabel plc = new JLabel("ooo");
        int plcsx = 100; //starting x-var, change as needed
        int plcsy = 50; //starting y-var, change as needed



        plc.setBounds(520, 200, 300, 50);

        //place character on frame
        tefp.add(plc);


        //add test button
        JButton myButton = new JButton("JButton");
        tefp.add(myButton);

//        //add key listeners
//        plykl mk = new plykl();
//        plc.addKeyListener(mk);

        //add movement to plc
        // Set the bounds (x, y, width, height) of the label
        // Here, x=100, y=50, and width/height are determined by preferred size
        plc.setBounds(plcsx, plcsy, 400, 400);

        this.setContentPane(tefp);
        tefp.setVisible(true);
        this.setVisible(true);



        //Clock
        //Run thread
        clockTest obj = new clockTest();
        Thread thread = new Thread(obj);
        thread.start();
        System.out.println("This code is outside of the thread");


        //stops clock if JFrame has been closed
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // This method is called after the window has been closed and disposed of.
                System.out.println("JFrame has been closed and disposed.");
                //stop clock
                obj.setRunning(false);
            }
        });

    }


    //Clock
    public static ParentRoom getInstance() {
        if (instance == null) {
            instance = new ParentRoom();
        }
        return instance;
    }

    public void setTimeLabel(String timeSeconds) {
        time.setText(timeSeconds);
    }

}

