import student.TestCase;

// ----------------------------------------------------------
/**
 * NodeTest Class
 * Description: Test class for NodeTest object
 * 
 * @version 10/17/2022
 * @author Deep Datta (PID: ddeep21), Enk Naran (PID: enk)
 * 
 */
public class NodeTest extends TestCase {

    private Node<Integer> test1;
    private Node<Integer> test2;
    private Node<Integer> test3;
    private Node<Integer> test4;

    // ----------------------------------------------------------
    /**
     * 
     * Description: Sets up test Node objects to be used
     * 
     */
    public void setUp() {

        test1 = new Node<Integer>(1, null);
        test3 = new Node<Integer>(3, null);
        test2 = new Node<Integer>(2, test3);
        test4 = new Node<Integer>(test1);

    }


    /**
     * Description: Tests getData method
     */
    public void testGetData() {

        assertEquals(1, ((int)test1.data()));
        assertEquals(2, ((int)test2.data()));
        assertEquals(3, ((int)test3.data()));
        test4.setData(4);
        assertEquals(4, ((int)test4.data()));
        Node<Integer> zero = new Node<Integer>(0, null);
        assertEquals(0, ((int)zero.data()));

    }


    /**
     * Description: Tests setData method
     */
    public void testSetData() {

        assertEquals(1, ((int)test1.data()));
        test1.setData(0);
        assertEquals(0, ((int)test1.data()));
        test1.setData(2);
        assertEquals(2, ((int)test1.data()));
        Node<Integer> nope = new Node<Integer>(null, null);
        nope.setData(0);
        assertEquals(0, ((int)nope.data()));

    }


    /**
     * Description: Tests getNext method
     */
    public void testGetNext() {

        assertNull(test1.next());
        assertEquals(test1, test4.next());
        assertEquals(test3, test2.next());

    }


    /**
     * Description: Tests setNext method
     */
    public void testSetNext() {

        assertNull(test1.next());
        test1.setNext(test2);
        assertEquals(test2, test1.next());
        test2.setNext(test4);
        assertEquals(test4, test2.next());
        Node<Integer> neww = new Node<Integer>(7, null);
        test4.setNext(neww);
        assertEquals(neww, test4.next());

    }
}
