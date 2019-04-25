
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Visitors implements Runnable {
  // Instance variable
  private String num;
  private Thread thread;
  public static long time = System.currentTimeMillis();


  // Constructor
  public Visitors(String num) {
    //save this num in queueInteger atomic to know priority
    this.num = "visitor_" + num;
    this.thread = new Thread(this, num);
  }

  // Methods
  public void start() {
    thread.start();
  }

  public void run() {
    Main main = new Main();
    //--->>>>main.visitorNumQueue.set( main.Num.getAndIncrement(), this.num);
    // check if movie session is On
    while (main.SessionOn.get() == true) {
      msg("Other session in process... visitors to busywait in loby");
      if (main.SessionOn.get() == false) {
        main.SessionOn.set(false); // session is tunr on on clock
      }

      try {
        Thread.currentThread().sleep(1000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    msg("Started");
    //when awoken need to get into theater
    msg("Ending");
  }

  public static void msg(String m){
    System.out.println("["+(System.currentTimeMillis()-time)+"] "+": "+Thread.currentThread().getName()+" - "+m);
  }
}
