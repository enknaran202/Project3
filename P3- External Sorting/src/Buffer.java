import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

/**
 * Buffer Class
 * 
 * @author William Lummus (williamnl)
 * @version 2022.10.23
 */
public class Buffer {

    public byte[] data;
    public Boolean dirty;
    public int num;

    public Buffer(RandomAccessFile file, int num) {
        dirty = false;
        this.data = data;
        this.num = num;
        // need a way to get the file
    }
    


    /**
     * Returns num
     * 
     * @return num
     */
    public int num() {
        return num;
    }


    /**
     * Returns if dirty or not
     * 
     * @return dirty
     */
    public boolean checkDirty() {
        return dirty;
    }


    /**
     * Makes a buffer dirty.
     */
    public void makeDirty() {
        dirty = true;
    }


    /**
     * Gets the byte at a certain index
     * 
     * @param index
     *            index of byte
     * @return byte
     */
    public short[] get(int index) {
        byte[] toReturn = new byte[4];

        for (int i = index; i < 4; i++) {
            toReturn[i] = data[(index * 4) + i];
        }
        short[] shorts = new short[2];

        // use java bytebuffer to convert to short
        ShortBuffer s1 = ByteBuffer.wrap(toReturn).order(
            ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(shorts);
        return (s1.array());
    }


    /**
     * Sets a byte at a certain index
     * 
     * @param index
     *            index of setting
     * @param byt
     *            byte
     */
    public void set(int index, short[] shorts) {
        if (!dirty) {
            dirty = true;
        }

        // reverse bytebuffer to make an array of bytes of size 4
        byte[] toSet = new byte[4];
        ByteBuffer.wrap(toSet).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer()
            .put(shorts);

        // replace the record with new record we made here^
        for (int i = 0; i < 4; i++) {
            data[index] = toSet[i];
        }

    }

}

