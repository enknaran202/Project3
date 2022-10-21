/**
 * 
 */

/**
 * @author EnkN
 *
 */
public class BufferPool
{
    //Constructor(int numBuffers)
        //Basically an array of buffers
        //Will load the MAX number of buffers
    
    //readRecord(int indexLookingFor)
        //read a block of data that contains the index
        // 4096 bytes - 1024 records
    
    //writeRecord(int theBufferToReturn, value)
        //checks for valid index
        //
    
    //evict
        //Least Recently Used Buffer
    
    //validIndex
        //ensures if index is in our pool, OR in the file
    
    
}

// JAVA RandomAccessFile- used to read the file a block at a time
    //seek- sets the file pointer to a specific part of a file
    //read
    //write
    //length- tells us the number of bytes. Dividte that by 4 to get num of records

// JAVA ByteBuffer- used to read bytes into a proper format