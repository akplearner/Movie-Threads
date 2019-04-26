
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
        Main main = new Main();
        msg("Started");
        msg("let visitors come in");
        ////////main.visitorVector.get(0).;;
        int full=0;
        while(full<5){ //while full<5 then continue sitting visitors
            int num =(int)((Math.random()*100)%22);
            if(main.visitorOut[num].get()==false ){ //if visitor have not leave yet then change flag
                main.visitorSitting[num].set(true);; //change flag
                msg(main.visitor[num].getName()+" sitting");
                //msg(" queue num="+main.visitorNumQueue.get(num) );
                main.visitorOut[num].set(true); //visitors left
                full++;
            }

        }
        // for(int i=0; i<main.numVisitors ; i++){
        //     if(main.visitorSitting[i].get()){
        //         msg(main.visitor[i].getName()); // ================Make it number random
        //     }
        // }
        msg("Ending");
    }

    public static void msg(String m){
        System.out.println("["+(System.currentTimeMillis()-time)+"] "+": "+Thread.currentThread().getName()+" - "+m);
    }

}