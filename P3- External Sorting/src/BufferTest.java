import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import student.TestCase;

/**
 * Test cases for Buffer
 * 
 * @author William Lummus (williamnl)
 * @version 2022.10.24
 */
public class BufferTest extends TestCase {

    private Buffer buffer;
    private byte[] bytes;
    private StatsOutput stats;

    /**
     * Sets up test cases
     * 
     * @throws FileNotFoundException
     *             if no file
     */
    public void setUp() throws FileNotFoundException {
        File file = new File("4096Bytes.txt");
        stats = new StatsOutput();
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        buffer = new Buffer(raf, 4096, stats);
        bytes = new byte[4];
        byte zero = 01;
        byte one = 23;
        byte two = 45;
        byte three = 67;
        bytes[0] = zero;
        bytes[1] = one;
        bytes[2] = two;
        bytes[3] = three;
    }


    /**
     * Tests read2
     */
    public void testRead2() {
        byte[] test = buffer.read2(1);
        assertEquals(4096, test.length);
    }


    /**
     * Tests writeToFile
     * 
     * @throws IOException
     */
    public void testWriteToFile() throws IOException {
        // dirty
        buffer.makeDirty();
        buffer.writeToFile();
        assertFalse(buffer.checkDirty());

    }


    /**
     * Tests num()
     */
    public void testNum() {
        assertEquals(4, buffer.num());
    }

 
    /**
     * Tests checkDirty()
     */
    public void testCheckDirty() {
        assertFalse(buffer.checkDirty());
    }


    /**
     * Tests makeDirty()
     */
    public void testMakeDirty() {
        buffer.makeDirty();
        assertTrue(buffer.checkDirty());
    }


    /**
     * Tests get() and set()
     */
    public void testGetAndSet() {
        short[] shorts = { 2, 4 };
        buffer.set(2, shorts);
        //short[] first = buffer.get(2);
        //System.out.println(first[0]);
        //System.out.println(first[1]);
        //System.out.println(Arrays.equals(first, shorts));
        assertTrue(Arrays.equals(shorts, buffer.get(2)));
    }

}