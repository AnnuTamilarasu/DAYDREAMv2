//non-thread

import java.time.Clock;
import javax.swing.*;

//has to be implements runnable because can only do one extend

//RUNS JFRAME IN DIFFERENT FILE, TO BE CALLED UPON

public class clockTest implements Runnable{
    public static void main(String[] args) {
        //Run JFrame
//        new parentroom();

//        //Run thread
//        clockTest obj = new clockTest();
//        Thread thread = new Thread(obj);
//        thread.start();
//        System.out.println("This code is outside of the thread");
    }

    //JFrame
    JPanel tefp;

    //Clock
    Clock clock = Clock.systemDefaultZone();
    long initialTime = clock.millis();
    long lastValuePrinted = -1;
    boolean running = true;
    String timeSeconds;
    long currentTimeSeconds;
    long currentTime;
    long addedTime;


    public void run() {
        System.out.println("This code is running in a thread");
        //Clock
        Clock clock = Clock.systemDefaultZone();
        long initialTime = clock.millis();
        long lastValuePrinted = -1;
        boolean running = true;
        String timeSeconds;
        long currentTimeSeconds;
        long currentTime;
        long addedTime;
//        print

        //Clock
        clock();

    }


    //Make clock actually in clock format
    public String toClockFormat(long currentTimeSeconds) {
        String extraZero = "0";
        String extraZeroSeconds = "0";
        int hours;
        int minutes = 0;
        long seconds = currentTimeSeconds;
        //make seconds not go over 60
        while (seconds >= 60) {
            minutes++;
            seconds -= 60;
        }
        //add extra zero for minutes
        if (minutes >= 10) {
            extraZero = "";
        }
        //add extra zero to seconds
        if (seconds >= 10) {
            extraZeroSeconds = "";
        }
        return extraZero + minutes + ":" + extraZeroSeconds + seconds;
    }


    //method for clock timer
    public void clock() {
        while (running) {
            currentTime = clock.millis() - initialTime - addedTime;
            currentTimeSeconds = currentTime / 1000;
            //update clock in banner every second
            if (lastValuePrinted != currentTimeSeconds) {
//                    System.out.println(currentTimeSeconds);
                timeSeconds = toClockFormat(currentTimeSeconds);
                //SET TIME HERE
                ParentRoom pr = ParentRoom.getInstance();
                pr.setTimeLabel(timeSeconds);
                System.out.println(timeSeconds);
//                    time.setText(timeSeconds);

                lastValuePrinted = currentTimeSeconds;

            }
        }
    }


    public void setRunning(boolean run) {
        running = run;
    }


}
