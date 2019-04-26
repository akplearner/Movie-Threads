
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Visitors implements Runnable {
  // Instance variable
  private int num;
  private Thread thread;
  public static long time = System.currentTimeMillis();
  private Object lock = new Object();
  Vector<Visitors> visitorVector = new Vector<Visitors>();

  // Constructor
  public Visitors(int num, Vector<Visitors> visitorVector) {
    // save this num in queueInteger atomic to know priority
    this.num = num;
    this.thread = new Thread(this, "visitor_" + num);
    this.visitorVector = visitorVector;
  }

  // Methods
  public void start() {
    thread.start();
  }

  public void run() {
    msg("Just Came in");
    // Adding visitor to queue
    inTheater();

    // check if movie session is On
    while (Main.SessionOn.get() == true) {
      msg("visitors busywait in lobby");
      try {
        this.thread.sleep(1000);
      } catch (InterruptedException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }

      if (Main.SessionOn.get() == false) {
        Main.SessionOn.set(false); // session is turn on on clock
      }
    }

    while (Main.visitorOut[num].get() == false) {
      try {
        this.thread.sleep(1000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    // when awoken need to get into theater
    Main.visitorOut[this.num].set(true);
    msg("left");

  }

  public void inTheater()
  {
    synchronized(lock)
    {
      Main.visitorVector.addElement(this);
    }
  }
  public static void msg(String m){
    System.out.println("["+(System.currentTimeMillis()-time)+"] "+": "+Thread.currentThread().getName()+" - "+m);
  }
}
