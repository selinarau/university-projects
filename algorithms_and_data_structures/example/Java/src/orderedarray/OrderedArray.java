package orderedarray;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 
 * @author pozzato
 * @param <T>: type of the array elements
 */
public class OrderedArray<T>{
    private ArrayList<T> array = null;
    private Comparator<? super T> comparator;

    /**
     * 
     * It creates an empty ordered array and returns the created array
     * @param comparator: a comparator implementing the precedence relation among array elements
     */
    public OrderedArray(Comparator<? super T> comparator){
        this.comparator = comparator;
        this.array = new ArrayList<>();
    } // OrderedArray

    /**
     * 
     * @return: true if and only of this ordered array is empty
     */
    public boolean isEmpty(){
        return (this.array).isEmpty();
    } // isEmpty

    /**
     * 
     * @return: the number of elements currently belonging to the ordered array
     */
    public int size(){
        return (this.array).size();
    } // size

    /**
     * 
     * @param element: the element to be added
     * @throws OrderedArrayException if and only if the element is null
     */
    public void add (T element) throws OrderedArrayException{
        if (element == null)
            throw new OrderedArrayException("-- add: element cannot be null");
        int index = getIndexInsert(element);
        (this.array).add(index,element);
    } // add

    /**
     * 
     * @param i: index of the element that should be returned
     * @return: the element at position i
     * @throws OrderedArrayException if and only if i is out of the array bounds
     */
    public T get(int i) throws OrderedArrayException{
        if ((i<0) || (i>=(this.array).size())){
            throw new OrderedArrayException("get: index "+i+" is out of the array bounds");
        }
        return (this.array).get(i);
    } // get
    

    // It returns the position whene a new element must be added
    private int getIndexInsert (T element){
        int index = 0;
        boolean cont = true;
        T currEl = null;
        while (cont && (index < (this.array).size())){
            currEl = (this.array).get(index);
            if ((this.comparator).compare(element, currEl)<0)
                cont = false;
            else
                index++;
        }
        return index;
    }  // getIndexInsert

}
