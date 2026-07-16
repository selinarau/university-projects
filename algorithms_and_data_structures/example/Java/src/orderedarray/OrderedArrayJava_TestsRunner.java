package orderedarray;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * 
 * @author pozzato
 */
public class OrderedArrayJava_TestsRunner {
    /*
     * @param args: the command line arguments
     */
    public static void main (String [] args){
        Result res = JUnitCore.runClasses(OrderedArrayTests.class);
        for (Failure fail : res.getFailures()){
            System.out.println(fail.toString());
        } // for
        System.out.println("Esito test: "+res.wasSuccessful());
    } // main

}  //class

