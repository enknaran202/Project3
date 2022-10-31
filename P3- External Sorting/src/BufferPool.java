import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author EnkN
 *
 */
public class BufferPool
{
    LinkedList<Buffer> pool;
    int MAX = 0;
    RandomAccessFile fileName;

    @SuppressWarnings(
    { "unchecked", "rawtypes" })
    public BufferPool(int MAX, RandomAccessFile fileName)
    {
        pool = new <Buffer> LinkedList();
        this.MAX = MAX;
        this.fileName = fileName;
    }


    /**
     * Returns the record we are looking for.
     * 
     * @param index
     *            the index of the record we are looking for
     * 
     * @return short[]
     *         the record we are looking for or null if it doesnt exist
     */
    public short[] getIndex(int index)
    {
        Buffer temp = null;
        temp = find(index);

        return temp.get(index);
        // if not in pool
        // and if size of LL == MAX;

    }


    /**
     * Sets changes the record at the index.
     * 
     * @param record
     *            the new record
     * @param index
     *            the index of the record to be changed
     * @return short[]
     *         the record we are looking for or null if it doesnt exist
     */
    public void setIndex(short[] record, int index)
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
     */
    public Buffer find(int index)
    {
        int block = index % 1024;
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
     * 
     */
    private void evict()
    {
        if (pool.checkLRU().checkDirty() == true)
        {
            // write the block to the buffer
            // should call a buffer method
        }
        pool.deleteLRU();
    }


    // validIndex
    // ensures if index is in our pool, OR in the file
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

// JAVA RandomAccessFile- used to read the file a block at a time
// seek- sets the file pointer to a specific part of a file
// read
// write
// length- tells us the number of bytes. Divide that by 4 to get num of records

// JAVA ByteBuffer- used to read bytes into a proper format
