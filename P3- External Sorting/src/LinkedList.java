import java.util.NoSuchElementException;

/**
 * Linked list with access to end.
 * Able to input at the beginning and end of list.
 * start -> node -> node -> node -> null
 * 
 * end points to the last node in the list.
 * 
 * 
 * @author William Lummus (williamnl), Enk Naran (enk)
 * @version 10/1/2022
 * 
 */
public class LinkedList<Buffer>
{
    private Node<Buffer> lru;
    private Node<Buffer> entry;
    private Node<Buffer> cur;
    private int size;

    /**
     * LinkedList constructor
     */
    public LinkedList()
    {
        entry = null;
        lru = null;
        cur = null;
        size = 0;
    }


//    /**
//     * Enter a new number into the beginning of the list
//     * The current pointer is set to the new entry
//     * 
//     * @param number
//     *            The number to be entered into the list
//     */
//    // probably don't need this
//    public void toDelete(Buffer input)
//    {
//        if (isEmpty())
//        {
//            lru = new Node<Buffer>(input, null);
//            cur = lru;
//            entry = lru;
//            size++;
//        }
//
//        else
//        {
//            lru = new Node<Buffer>(input, lru);
//            cur = lru;
//            size++;
//        }
//    }


    /**
     * Enter a new number into the END of the list
     * Note: Current pointer is not reset
     * 
     * @param number
     *            The number to be entered into the list
     */
    public void enter(Buffer input)
    {
        if (isEmpty())
        {
            lru = new Node<Buffer>(input, null);
            entry = lru;
            cur = lru;
            size++;
        }
        else
        {
            entry.setNext(new Node<Buffer>(input, null));
            entry = entry.next();
            size++;
        }
    }

    /**
     * Deletes the node at the end of the list
     */
    public void deleteLRU()
    {
        Node<Buffer> temp = lru.next();
        lru.setNext(null);
        lru = temp;
        cur = temp;
        size--;
    }
    
    /**
     * Peeks at lru
     */
    public Buffer checkLRU()
    {
        return lru.data();
    }
    

    /**
     * The size of the list
     * 
     * @return int
     *         The size of the LinkedList
     * 
     */
    public int getSize()
    {
        return size;
    }


    /**
     * Checks to see if the list is empty
     * 
     * @return boolean
     *         True if empty, False if not
     * 
     */
    public boolean isEmpty()
    {
        return lru == null;
    }


    /**
     * Returns the value in the current node and moves current to the next node
     * 
     * @return boolean
     *         True if empty, False if not
     * 
     */
    @SuppressWarnings("unchecked")
    public Buffer next()
    {
        if (!hasNext())
        {
            throw new NoSuchElementException("No nodes left in the list.");
        }
        Buffer toReturn = cur.data();
        cur = cur.next();
        return toReturn;
    }

    /**
     * Checks to see if the current pointer is at the end of the list
     * 
     * @return boolean
     *         True if not at end. False if at end
     * 
     */
    public boolean hasNext()
    {
        return cur != null;
    }


    /**
     * Puts current to the beginning of the list
     * 
     */
    public void resetCurrent()
    {
        cur = lru;
    }


    /**
     * Clears the list
     * 
     */
    public void clear()
    {
        lru = null;
        entry = null;
    }


    /**
     * Prints the list in the correct order.
     * Note: resets the current pointer to low
     * 
     * @return String
     *         the list in proper string form
     */
    public String toString()
    {
        String toReturn = "";
        resetCurrent();
        while (hasNext())
        {
            toReturn = next().toString() + toReturn;
        }
        return toReturn;
    }

}
