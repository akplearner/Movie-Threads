
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.lang.Thread.*;

public class Clock implements Runnable {
  // instance variables
  private static Thread thread;
  public static long time = System.currentTimeMillis();
  
  // Constructor
  public Clock() {
    this.thread = new Thread(this, "Speaker");
  }

  // Methods
  public void start() {
    thread.start();
  }

    public void run() {
        Main main = new Main();
    //----Movie entry--------
        
        msg("Started");
        msg("Ready to get visitors in. Needs to signal visitors");
        main.SessionOn.set(true);

        //time to tell speaker to handle visitors //signal visitors
        main.speaker = new Thread(new Speaker());
        main.speaker.start();
        main.speaker.setName("Speaker");
        try {
            main.speaker.join(); //to make sure the speaker is inside the clock[i]
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        main.SessionOn.set(false);
        msg("Ending");
  }
  
  public static void msg(String m){
    System.out.println("["+(System.currentTimeMillis()-time)+"] "+": "+Thread.currentThread().getName()+" - "+m);
  }
}
