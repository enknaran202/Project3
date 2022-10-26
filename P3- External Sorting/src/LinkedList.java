import java.util.NoSuchElementException;

/**
 * Linked list with access to end.
 * Able to input at the beginning and end of list.
 * Low -> node -> node -> node -> null
 * 
 * High points to the last node in the list.
 * 
 * 
 * @author William Lummus (williamnl), Enk Naran (enk)
 * @version 10/1/2022
 * 
 */
public class LinkedList<T>
{
    private Node<T> low;
    private Node<T> high;
    private Node<T> cur;
    private int size;

    /**
     * LinkedList constructor
     */
    public LinkedList()
    {
        high = null;
        low = null;
        cur = null;
        size = 0;
    }


    /**
     * Enter a new number into the beginning of the list
     * The current pointer is set to the new entry
     * 
     * @param number
     *            The number to be entered into the list
     */
    public void addLow(T input)
    {
        if (isEmpty())
        {

            low = new Node<T>(input, null);
            cur = low;
            high = low;
            size++;

        }

        else
        {
            low = new Node<T>(input, low);
            cur = low;
            size++;
        }
    }


    /**
     * Enter a new number into the END of the list
     * Note: Current pointer is not reset
     * 
     * @param number
     *            The number to be entered into the list
     */
    public void addHigh(T input)
    {
        if (isEmpty())
        {
            low = new Node<T>(input, null);
            high = low;
            cur = low;
            size++;
        }
        else
        {
            high.setNext(new Node<T>(input, null));
            high = high.next();
            size++;
        }
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
        return low == null;
    }


    /**
     * Returns the value in the current node and moves current to the next node
     * 
     * @return boolean
     *         True if empty, False if not
     * 
     */
    @SuppressWarnings("unchecked")
    public T next()
    {
        if (!hasNext())
        {
            throw new NoSuchElementException("No nodes left in the list.");
        }
        T toReturn = cur.data();
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
        cur = low;
    }


    /**
     * Clears the list
     * 
     */
    public void clear()
    {
        low = null;
        high = null;
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
