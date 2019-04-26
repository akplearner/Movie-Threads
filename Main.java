
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;

public class Main extends Thread {
    //variables
        private static long time = System.currentTimeMillis();
        public static int numVisitors = 23 ;//(The numVisitors should be an argument to the program.)
        private static int theaterCapacity = 5;
        private static int party_ticket =3; 
        
        public static int movieNum = 4; // [Throughout the day, they are four movie presentations]
    
    //visitors stuff
    public static Thread[] visitor = new Thread[numVisitors];
    public static volatile Vector<Visitors> visitorVector = new Vector<Visitors>();
    
    public static AtomicBoolean[] visitorOut = new AtomicBoolean[numVisitors];
    public static AtomicBoolean[] visitorSitting = new AtomicBoolean[numVisitors];
    //clock stuff
    public static Thread[] clock = new Thread[movieNum];
    public static AtomicBoolean SessionOn = new AtomicBoolean(false);
    
    //Created Speaker stuff
    public static Thread speaker;
    
    //methods
    public static void main(String args[]) throws InterruptedException{
        //msg("Main Started Running");
        Thread threadMain = Thread.currentThread(); 
        
        //creates number of visitors and Starts visitor threads
        for (int i = 0; i < numVisitors; i++) {
            Visitors visitors = new Visitors (i , visitorVector);
            visitor[i] = new Thread(visitors );   
            visitor[i].setName("visitor"+i);

            visitorOut[i]=new AtomicBoolean();
            visitorOut[i].set(false);

            visitorSitting[i]=new AtomicBoolean();
            visitorSitting[i].set(false);
        }


        //creates the 4 clock times that will be play on the day
        for(int i=0; i<4 ; i++){ 
            Visitors visitors = new Visitors (i , visitorVector);
            clock[i] = new Thread(new Clock( visitorVector, speaker));
            clock[i].setName("Clock-"+i);
        }
        for (int i= 0; i < numVisitors; i++) {
            visitor[i].start();
        }
        for(int i=0; i<4 ; i++){
            clock[i].start();
            clock[i].join();
            
        }
        System.out.println("Main vec size " + Main.visitorVector.size());
    //create speaker
        
    }

    public static void msg(String m){
        System.out.println("["+(System.currentTimeMillis()-time)+"] "+": "+Thread.currentThread().getName()+" - "+m);
    }

}