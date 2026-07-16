package orderedarray;

/**
 * @author pozzato
 */
public class OrderedArrayException extends Exception{
    /**
     * @param message: the message displayed when the exception is thorwn
     */
    public OrderedArrayException (String message){
        super("-- ERROR OrderedArray: "+message);
    } // OrderedArrayException
    
} // class
