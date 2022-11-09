import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author EnkN
 * !QUESTION! Hows this shit looking? Good hopefully 
 * !CHANGE!   Change the methods that take index. Calculate local index
 */
public class BufferPool
{
    LinkedList<Buffer> pool;
    int MAX = 0;
    RandomAccessFile fileName;

    public BufferPool(int MAX, RandomAccessFile fileName)
    {
        pool = new LinkedList<Buffer>();
        this.MAX = MAX;
        this.fileName = fileName;
    }

 
    /**
     * Retrieves index of record from bufferpool if it exists
     * Retrieves from file if it doesnt exist
     * 
     * @param index
     *            the index of the record we are looking for
     * 
     * @return short[]
     *         the record we are looking for or null if it doesn't exist
     * @throws IOException 
     */
    public short[] getIndex(int index) throws IOException
    {
        Buffer temp = null;
        temp = find(index);

        return temp.get(index);
        // if not in pool
        // and if size of LL == MAX;

    }


    /**
     * Sets changes the record at the index. 
     * Looks in pool first.
     * Gets the buffer from the file if it doesnt exist in pool
     * 
     * @param record
     *            the new record
     * @param index
     *            the index of the record to be changed
     * @return short[]
     *         the record we are looking for or null if it doesnt exist
     * @throws IOException 
     */
    public void setIndex(short[] record, int index) throws IOException
    {
        Buffer temp = null;
        temp = find(index);
        temp.set(index, record);
    }


    /**
     * Looks in bufferpool and returns the record at the index
     * Removes the LRU if the list is full;
     * 
     * @param index
     *            the index in question
     * @return Buffer
     *         the record we are looking for or null if it doesnt exist
     * @throws IOException 
     */
    public Buffer find(int index) throws IOException
    {
        // converts index to appropriate block
        int block = index / 1024;
        boolean found = false;
        Buffer temp = null;
        pool.resetCurrent();
        // search through linked list
        while (pool.hasNext() && !found)
        {
            temp = pool.next();
            if (temp.num() == block)
            {
                found = true;
            }
        }
        if (found)
        {
            return temp;
        }
        if (pool.getSize() == this.MAX)
        // remove LRU, or HEAD;
        {
            this.evict();
        }
        // get from file
        // get from buffer
        temp = new Buffer(fileName, index);
        pool.enter(temp);
        // return short
        return temp;
    }


    /**
     * Removes the least recently inputed buffer from the pool
     * @throws IOException 
     * 
     */
    private void evict() throws IOException
    {
        if (pool.checkLRU().checkDirty() == true)
        {
            // write the block to the buffer
            // should call a buffer method
            pool.checkLRU().writeToFile();
        }
        pool.deleteLRU();
    }


    /**
     * Ensures if the index is in the pool or in the file
     * 
     * @param index
     *            the questionable index
     * @param
     * @throws IOException
     *
     */
    public boolean validIndex(int index) throws IOException
    {
        long numRecords = fileName.length() / 4;
        return index <= numRecords && index >= 0;
    }
}

// FOR TESTING


// JAVA RandomAccessFile- used to read the file a block at a time
// seek- sets the file pointer to a specific part of a file
// read
// write
// length- tells us the number of bytes. Divide that by 4 to get num of records

// JAVA ByteBuffer- used to read bytes into a proper format
