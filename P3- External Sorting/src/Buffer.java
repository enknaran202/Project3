import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Buffer Class
 * 
 * @author William Lummus (williamnl)
 * @version 2022.10.23
 */
public class Buffer
{

    private byte[] data;
    private Boolean dirty;
    private int num; // block number
    private RandomAccessFile disk;
    private StatsOutput stats;

    /**
     * Constructor
     * 
     * @param file
     *            file input
     * @param index
     *            index
     */
    public Buffer(RandomAccessFile file, int index, StatsOutput stts)
    {
        stats = stts;
        dirty = false;
        num = index / 1024; // double check number
        this.disk = file;
        this.data = this.read2(num);
    }


    /**
     * 
     * @param d
     * @return
     */
    public byte[] read2(int blockNumber)
    {
        int startingByteIndex = 4096 * blockNumber;

        data = new byte[4096];
        try
        {
            disk.seek(startingByteIndex);
            disk.read(data);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        dirty = false;

        stats.incReads();
        return data;
    }


    /**
     * Writes buffer back into file
     * 
     * @throws IOException
     */
    public void writeToFile() throws IOException
    {
        if (dirty)
        {
// System.out.println("Index 1 of buffer " + this.data[1]);
// System.out.println("Block of buffer " + this.num);
// System.out.println("Size of disk" + this.disk.length());
            disk.seek(num * 4096);
            disk.write(data);
            stats.incWrites();
            dirty = false;
        }
    }


    /**
     * Returns num
     * 
     * @return num
     */
    public int num()
    {
        return num;
    }


    /**
     * Returns if dirty or not
     * 
     * @return dirty
     */
    public boolean checkDirty()
    {
        return dirty;
    }


    /**
     * Makes a buffer dirty.
     */
    public void makeDirty()
    {
        dirty = true;
    }


    /**
     * Gets the byte at a certain index
     * 
     * @param index
     *            index of byte
     * @return byte
     */
    public short[] get(int index)
    { // check in range

        // !Changed! localIndex is required to be in range
        int localIndex = index % 1024;
        // use java bytebuffer to convert to short

        ByteBuffer toReturn = ByteBuffer.allocate(4);
        toReturn.order(ByteOrder.BIG_ENDIAN);
        toReturn.put(data[(localIndex * 4)]);
        toReturn.put(data[(localIndex * 4) + 1]);
        toReturn.put(data[(localIndex * 4) + 2]);
        toReturn.put(data[(localIndex * 4) + 3]);

        short[] s1 = new short[2];

        toReturn.position(0);
        s1[0] = toReturn.getShort();
        s1[1] = toReturn.getShort();

        return s1;
    }


    /**
     * Sets a byte at a certain index
     * 
     * @param index
     *            index of setting
     * @param shorts
     *            short array
     */
    public void set(int index, short[] shorts)
    {
        // !Changed! index and if to local index
        int localIndex = index % 1024;
        if (!dirty)
        {
            dirty = true;
        }

        // reverse bytebuffer to make an array of bytes of size 4
        byte[] toSet = new byte[4];

        ByteBuffer myBuffer = ByteBuffer.allocate(4);
        myBuffer.putShort(shorts[0]);
        myBuffer.putShort(shorts[1]);

        toSet = myBuffer.array();

        // replace the record with new record we made here^
        for (int i = 0; i < 4; i++)
        {
            data[localIndex * 4 + i] = toSet[i];
        }

    }

}
