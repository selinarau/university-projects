package priorityqueue;

import java.util.Comparator;
import java.util.Vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * unit tests of priorityqueue functions
 * @author rautakoski
 */

public class PriorityQueueTests{

    class IntegerComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer i1, Integer i2){
            return i1.compareTo(i2);
        } // compare
    }  // inner class

    private Integer i1, i2, i3;
    private PriorityQueue<Integer> heap;

    @Before
    public void createPriorityQueue(){
        i1 = 0;
        i2 = 4;
        i3 = 7;
        heap = new PriorityQueue<>(new IntegerComparator());
    }

    @Test
    public void testIsEmpty_zeroEl(){
        assertTrue(heap.empty());
    }

    @Test
    public void testIsEmpty_oneEl() throws Exception{
        heap.push(i1);
        assertFalse(heap.empty());
    }

    @Test
    public void testSize_zeroEl(){
        assertEquals(0,heap.size());
    }

    @Test
    public void testSize_oneEl() throws Exception{
        heap.push(i1);
        assertEquals(1,heap.size());
    }

    @Test
    public void testSize_twoEl() throws Exception{
        heap.push(i1);
        heap.push(i2);
        assertEquals(2,heap.size());
    }

    @Test
    public void testPushTop_oneEl() throws Exception{
        heap.push(i1);
        assertTrue(i1 == heap.top());
    }

    @Test
    public void testContains_zeroEl() throws Exception{
        assertFalse(heap.contains(i1));
    }

    @Test
    public void testContains_oneEL() throws Exception{
        heap.push(i1);
        assertTrue(heap.contains(i1));
    }

    @Test
    public void testRemove_true() throws Exception{
        heap.push(i1);
        assertTrue(heap.remove(i1));
    }

    public void testRemove_false() throws Exception{
        heap.push(i1);
        assertFalse(heap.remove(i2));
    }

    @Test
    public void testHeap_threeEl() throws Exception{
        heap.push(i2);
        heap.push(i1);
        heap.push(i3);

        Vector<Integer> VectorExpected = new Vector<>();
        VectorExpected.add(i1);
        VectorExpected.add(i2);
        VectorExpected.add(i3);

        Vector<Integer> VectorActual = new Vector<>(3);
        for (int i=0; i<3; i++)
           VectorActual.add(heap.get(i));

        assertEquals(VectorExpected,VectorActual);
    }

    @Test
    public void testHeap_afterRemove() throws Exception{
        heap.push(i1);
        heap.push(i3);
        heap.push(i2);

        heap.remove(i1);

        Vector<Integer> VectorExpected = new Vector<>();
        VectorExpected.add(i2);
        VectorExpected.add(i3);

        Vector<Integer> VectorActual = new Vector<>(2);
        for (int i=0; i<2; i++)
           VectorActual.add(heap.get(i));

        assertEquals(VectorExpected,VectorActual);
    }
}