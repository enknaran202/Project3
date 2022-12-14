//Test main?
// only do it to get full coverage. Use junit webcat page
//https://web-cat.org/junit-quickstart/
//Test Heapsort methods?
// Only if the main test doesnt cover

import java.io.IOException;
import student.TestCase;

public class HeapSortTest extends TestCase
{
    private HeapSort test;
    private int[] toSort;
    private int[] sorted;

    public void setUp()
    {
        
    }
    public void testMain() throws Exception
    {
        new ByteFileGenerator().generate(1024 * 30);
        HeapSort.main(new String[] {"p3_input_sample.txt", "7", "stats.txt"});
        
        assertEquals(true, new CheckFile().checkFile("p3_input_sample.txt"));
    }
    
}