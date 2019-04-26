
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;
import java.lang.Thread.*;

public class Clock implements Runnable {
  // instance variables
  private static Thread thread;
  private Thread speaker;
  public static long time = System.currentTimeMillis();
  Vector<Visitors> visitorVector = new Vector<Visitors>();
  
  // Constructor
  public Clock(Vector<Visitors> visitorVector ,Thread sp) {
    this.thread = new Thread(this, "Speaker");
    this.speaker = sp;
  }

    // Methods
    public void start() {
      thread.start();
    }

    public void run() {
    //----Movie entry--------
        msg("telling speaker to guide visitors in");
        
        Main.SessionOn.set(true);

        //time to tell speaker to handle visitors //signal visitors
        Main.speaker = new Thread(new Speaker());
        Main.speaker.start();
        Main.speaker.setName("Speaker");
        try {
            Main.speaker.join(); //to make sure the speaker is inside the clock[i]
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        Main.SessionOn.set(false);
        msg("Ending");
  }
  
  public static void msg(String m){
    System.out.println("["+(System.currentTimeMillis()-time)+"] "+": "+Thread.currentThread().getName()+" - "+m);
  }
}
