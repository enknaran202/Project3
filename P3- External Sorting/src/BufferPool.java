/**
 * @author EnkN
 *
 */
public class BufferPool
{
    LinkedList<Buffer> pool;
    int MAX = 0;

    // Constructor(int numBuffers)
    // Basically an array of buffers
    // Will load the MAX number of buffers

    @SuppressWarnings(
    { "unchecked", "rawtypes" })
    public BufferPool(int MAX)
    {
        pool = new <Buffer> LinkedList();
        this.MAX = MAX;
    }


    public short[] get(int index)
    {
        // should return the block the index belongs to
        int block = index % 1024;
        boolean found = false;
        Buffer temp = null;
        pool.resetCurrent();
        // search through linked list
        while(pool.hasNext() && !found)
        {
            temp = pool.next();
            if (temp.num() == block)
            {
                found = true;
            }
        }
        if(found)
        {
            return temp.get(index);
        }
        // if not in pool
        // and if size of LL == MAX;
        if(pool.getSize() == this.MAX)
        // remove LRU, or HEAD;
        {
            evict();
        }
        // get from file
        // get from buffer
        // return short
        return null;

    }
    // readRecord(int indexLookingFor)
    // read a block of data that contains the index
    // 4096 bytes - 1024 records


    private void evict()
    {
        if(pool.checkLRU().checkDirty() == true)
        {
            //write the block to the buffer
        }
        
    }

    // writeRecord(int theBufferToReturn, value)
    // checks for valid index
    //

    // evict
    // Least Recently Used Buffer

    // validIndex
    // ensures if index is in our pool, OR in the file

}

// JAVA RandomAccessFile- used to read the file a block at a time
// seek- sets the file pointer to a specific part of a file
// read
// write
// length- tells us the number of bytes. Divide that by 4 to get num of records

// JAVA ByteBuffer- used to read bytes into a proper format
