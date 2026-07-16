package priorityqueue;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Unit tests runner of priority queue
 * @author rautakoski
 */

 public class PriorityQueue_TestsRunner {
    /*
     * @param args: the command line arguments
     */
    public static void main (String [] args){
        Result res = JUnitCore.runClasses(PriorityQueueTests.class);
        for (Failure fail : res.getFailures()){
            System.out.println(fail.toString());
        } // for
        System.out.println("Esito test: "+res.wasSuccessful());
    } // main
}  //class