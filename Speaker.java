
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;
import java.lang.Math;

public class Speaker implements Runnable {
    // Initial values:
    private Thread thread;
    public static long time = System.currentTimeMillis();

    public Vector<Thread> visitorQueue;

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
        msg(Main.visitor[ (int) ( (Math.random()*100 )%23) ].getName()); // ================Make it number random
        msg("Ending");
    }

    public static void msg(String m){
        System.out.println("["+(System.currentTimeMillis()-time)+"] "+": "+Thread.currentThread().getName()+" - "+m);
    }

}