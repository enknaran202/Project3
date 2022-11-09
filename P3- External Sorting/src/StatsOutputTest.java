import student.TestCase;

/**
 * Tests StatsOutput
 * 
 * @author William Lummus (williamnl)
 * @version 2022.10.19
 */
public class StatsOutputTest extends TestCase {

    private StatsOutput out;

    /**
     * Sets up test cases
     */
    public void setUp() {
        out = new StatsOutput();
    }

    /**
     * Tests setFileName
     */
    public void testSetFileName() {
        assertEquals("", out.name());
        out.setFileName("Test");
        assertEquals("Test", out.name());
    }
    
    /**
     * Tests incHits
     */
    public void testIncHits() {
        assertEquals(0, out.hits());
        out.incHits();
        assertEquals(1, out.hits());
    }


    /**
     * Tests incMisses
     */
    public void testIncMisses() {
        assertEquals(0, out.misses());
        out.incMisses();
        assertEquals(1, out.misses());
    }


    /**
     * Tests incReads
     */
    public void testIncReads() {
        assertEquals(0, out.reads());
        out.incReads();
        assertEquals(1, out.reads());
    }


    /**
     * Tests incWrites
     */
    public void testIncWrites() {
        assertEquals(0, out.writes());
        out.incWrites();
        assertEquals(1, out.writes());
    }
    
    
    /**
     * Tests time setter
     */
    public void testSetTime() {
        assertEquals(0, out.time());
        out.setTime(774);
        assertEquals(774, out.time());
    }
    
    
    /**
     * Tests output()
     */
    public void testOutput() {
        assertEquals("------  STATS ------\n" + 
            "File name: \n" + 
            "Cache Hits: 0\n" + 
            "Cache Misses: 0\n" + 
            "Disk Reads: 0\n" + 
            "Disk Writes: 0\n" + 
            "Time to Sort: 0", out.output());
    }

}