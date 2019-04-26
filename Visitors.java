
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Visitors implements Runnable {
  // Instance variable
  private String num;
  private Thread thread;
  public static long time = System.currentTimeMillis();
  private Object lock = new Object();
  Vector<Visitors> visitorVector = new Vector<Visitors>();
  // Constructor
  public Visitors(String num, Vector<Visitors> visitorVector) {
    //save this num in queueInteger atomic to know priority
    this.num = "visitor_" + num;
    this.thread = new Thread(this, num);
    this.visitorVector = visitorVector;
  }

  // Methods
  public void start() {
    thread.start();
  }

  public void run() {

    inTheater();
    System.out.println("visitor vec size " + Main.visitorVector.size());
    //--->>>main.visitorNumQueue.set( main.Num.getAndIncrement(), this.num);
    // check if movie session is On
    while (Main.SessionOn.get() == true) {
      msg("Other session in process... visitors to busywait in loby");
      if (Main.SessionOn.get() == false) {
        Main.SessionOn.set(false); // session is tunr on on clock
      }

      try {
        Thread.currentThread().sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    msg("Started");
    //when awoken need to get into theater
    msg("Ending");
  }

  public void inTheater()
  {
    synchronized(lock)
    {
      Main.visitorVector.addElement(this);
      System.out.println("visitor vec size" + Main.visitorVector.size());
    }
  }
  public static void msg(String m){
    System.out.println("["+(System.currentTimeMillis()-time)+"] "+": "+Thread.currentThread().getName()+" - "+m);
  }
}
