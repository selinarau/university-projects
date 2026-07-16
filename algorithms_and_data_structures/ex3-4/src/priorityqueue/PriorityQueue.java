package priorityqueue;

import java.util.Comparator;
import java.util.Vector;
import java.util.Collections; 

/**
 * 
 * @author rautakoski
 * @param <E>: comparator
 */
public class PriorityQueue<E> implements AbstractQueue<E> {
    private Vector<E> heap = null;
    private Comparator<E> comparator;

    /**
     * 
     * It creates an empty priority queue and returns the created heap
     * @param comparator: a comparator implementing the precedence relation among elements
     */
    public PriorityQueue(Comparator<E> comparator){
        this.comparator = comparator;
        this.heap = new Vector<>();
    } // PriorityQueue

    /**
     * 
     * @return: true if and only of this ordered array is empty
     */
    @Override
    public boolean empty(){
        return this.heap.isEmpty();
    } // empty

    public int size(){
        return (this.heap).size();
    }

    /**
     * 
     * @param element: the element to be pushed in the end of the queue
     */
    @Override
    public boolean push(E e) {
        if(e==null){
            return false;
        }
        this.heap.insertElementAt(e, size());
        fixHeap(size()-1);
        return contains(e);
    } // push

    /**
     * 
     * @param e: element that should be in heap
     * @return: true if element in heap, false otherwise
     */
    @Override
    public boolean contains(E e){
        if(e==null){
            return false;
        }
        return this.heap.contains(e);
    } // contains

        /**
     * 
     * @param e: element that should be removed
     * @return: true if element removed, false otherwise
     */
    @Override
    public boolean remove(E e) {
        if(e==null){
            return false;
        }
        boolean removed = this.heap.remove(e);
        if(removed){
            fixHeap(size()-1);
        }
        return removed;
    } // remove

    /**
     * 
     * @return element at the index i
     */
    public E get(int i) {
        if (i >= size()){
            return null;
        }
        return this.heap.get(i);
    }

    /**
     * 
     * @return: the first element of the heap (smallest element)
     */
    @Override
    public E top(){
        if(empty()){
            return null;
        } 
        return this.heap.get(0);
    } // top

    /**
     * 
     * Removes the first element of the heap (smallest element)
     */
    @Override
    public void pop() {
        if(!empty()){
            this.heap.remove(top());
        }
    } // pop

    // Order the heap from the index i.
    private void fixHeap(int i) {
        while(i > 0){
            int parent; // index to the parent element 
            if(i%2==0){
                parent = i/2-1;
            } else {
                parent = i/2;
            }

            if((this.comparator).compare(this.heap.get(i), this.heap.get(parent))<0){
                Collections.swap(this.heap, i, parent);
            }
            i = parent;
        }
    }// fixHeap
} // class