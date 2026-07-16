package graph;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Unit tests runner to graph
 *@author rautakoski
 */
public class Graph_TestsRunner{
    /*
     * @param args: the command line arguments
     */
    public static void main (String [] args){
        Result res = JUnitCore.runClasses(GrapTests.class);
        for (Failure fail : res.getFailures()){
            System.out.println(fail.toString());
        } // for
        System.out.println("Test result: "+res.wasSuccessful());
    } // main
}