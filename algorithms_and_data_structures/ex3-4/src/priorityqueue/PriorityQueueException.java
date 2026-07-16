package priorityqueue;

/**
 * @author rautakoski
 */

 public class PriorityQueueException extends Exception{
    /**
     * @param message: the message displayed when the exception is thorwn
     */
    public PriorityQueueException (String message){
        super("-- ERROR PriorityQueue: "+message);
    } // PriorityQueueException
    
} // class