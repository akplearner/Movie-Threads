
import java.util.concurrent.atomic.AtomicBoolean;

public class Speaker implements Runnable {
    // Initial values:
    private Thread thread;
    public static long time = System.currentTimeMillis();

    // Constructor
    public Speaker() {
        this.thread = new Thread(this);
    }

    // Methods
    public void start() {
        thread.start();
    }

    public void run() {
        msg("Started");
        msg("Ending");
    }

    public static void msg(String m){
        System.out.println("["+(System.currentTimeMillis()-time)+"] "+": "+Thread.currentThread().getName()+" - "+m);
    }
}
