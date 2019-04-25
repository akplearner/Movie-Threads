
import java.util.concurrent.atomic.AtomicBoolean;

public class Visitors implements Runnable {
//Instance variable
  private String num;
  private Thread thread;
  public static long time = System.currentTimeMillis();

//Constructor  
  public Visitors(String num) {
    this.num = "visitor_" + num;
    this.thread = new Thread(this, num);
  }

//Methods
  public void start() {
    thread.start();
  }

  public void run() {
    Main main = new Main();
    //check if movie session is On
    while(main.SessionOn.get()==true){
      msg("Other session in process... visitors to busywait in loby");
      if(main.SessionOn.get()==false){
        main.SessionOn.set(false); //session is tunr on on clock
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
