
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Thread {
    //variables
        private static long time = System.currentTimeMillis();
        private static int numVisitors = 23 ;//(The numVisitors should be an argument to the program.)
        private static int theaterCapacity = 5;
        private static int party_ticket =3;
        
        
        public static int movieNum = 4; // [Throughout the day, they are four movie presentations]
    
    //visitors stuff
    public static Thread[] visitor = new Thread[numVisitors];
    //clock stuff
    public static Thread[] clock = new Thread[movieNum];
    public static AtomicBoolean SessionOn = new AtomicBoolean(false);
    //speaker stuff
    public static Thread speaker;
    
    //methods
    public static void main(String args[]) throws InterruptedException{
            
        Thread threadMain = Thread.currentThread(); 
        msg("Started running ");
        
    //creates number of visitors and Starts visitor threads
        for (int i = 0; i < numVisitors; i++) {
            visitor[i] = new Thread(new Visitors(Integer.toString(i)));   
            visitor[i].setName("visitor"+i);  
        }
        for (int i= 0; i < numVisitors; i++) {
            visitor[i].start();
        }

    //creates the clock
        for(int i=0; i<4 ; i++){ 
            clock[i] = new Thread(new Clock());
            clock[i].setName("Clock-"+i);
        }
        for(int i=0; i<4 ; i++){
            clock[i].start();
            clock[i].join();
        }
        //SessionOn.set(true);

    //create speaker
        //speaker.start();
        //speaker.setName("Speaker");
    }

    /*
    public RandomThread(int id) {
        setName("RandomThread-" + id);
    }
    */

    public static void msg(String m){
        System.out.println("["+(System.currentTimeMillis()-time)+"] "+": "+Thread.currentThread().getName()+" - "+m);
    }

}