import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.NoSuchElementException;
import student.TestCase;

/**
 * Test for linked list
 * 
 * @author Deep Datta (PID: ddeep21), Enk Naran (PID: enk)
 * @version 10/3/2022
 * 
 */
public class LinkedListTest extends TestCase
{
    private LinkedList<Integer> list;
    private RandomAccessFile file;

    /**
     * Sets up the test
     * The list should be
     * 1,2,3,4,5,6,7,8
     * @throws FileNotFoundException 
     */
    public void setUp() throws FileNotFoundException
    {
        file = new RandomAccessFile("SampleInput.dat", "rw");
        list = new LinkedList<Integer>();
        list.enter(8);
        list.enter(7);
        list.enter(6);
        list.enter(5);
        list.enter(4);
        list.enter(3);
        list.enter(2);
        list.enter(1);
    }


    /**
     * Description: Tests addLow method
     */
    public void testEnter()
    {
        assertEquals("12345678", list.toString());
        assertEquals(8, list.getSize());
        list.enter(0);
        assertEquals(9, list.getSize());
        assertEquals("012345678", list.toString());

        LinkedList<Integer> newList = new LinkedList<Integer>();
        assertEquals(0, newList.getSize());
        newList.enter(0);
        assertEquals(1, newList.getSize());

    }


    /**
     * Description: Tests addHigh method
     */
    public void testDeleteLRU()
    {
        assertEquals("12345678", list.toString());
        assertEquals(8, list.getSize());
        list.deleteLRU();
        assertEquals(7, list.getSize());
        assertEquals("1234567", list.toString());
    }


    /**
     * Description: Test isEmpty method
     */
    public void testIsEmpty()
    {
        assertFalse(list.isEmpty());

        LinkedList<Buffer> emptyList = new LinkedList<Buffer>();
        assertTrue(emptyList.isEmpty());

    }


    /**
     * Description: Test the clear method
     */
    public void testClear()
    {
        list.clear();
        assertTrue(list.isEmpty());
    }
    
    /**
     * Description: Test the clear method
     */
    public void testCheckLRU()
    {
        assertEquals("8",list.checkLRU().toString());
    }
    
    /**
     * Description: Test the clear method
     */
    public void testNext()
    {
        assertEquals("8",list.next().toString());
        assertEquals("7",list.next().toString());
        assertEquals("6",list.next().toString());
        assertEquals("5",list.next().toString());
        assertEquals("4",list.next().toString());
        assertEquals("3",list.next().toString());
        assertEquals("2",list.next().toString());
        assertEquals("1",list.next().toString());
        Exception exception = null;
        try {
            list.next();
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue("No nodes left in the list.",
            exception instanceof NoSuchElementException);
    }


    /**
     * Description: Test toString method
     */
    public void testToString()
    {
        assertEquals("12345678", list.toString());
        list.enter(0);
        assertEquals("012345678", list.toString());
        list.enter(9);
        assertEquals("9012345678", list.toString());
        list.clear();
        assertEquals("", list.toString());
    }
}
