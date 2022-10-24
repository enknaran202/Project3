import java.util.NoSuchElementException;

/**
 * Linked list with access to end.
 * Able to input at the beginning and end of list.
 * Low -> node -> node -> node -> null
 * 
 * High points to the last node in the list.
 * 
 * 
 * @author Deep Datta (PID: ddeep21), Enk Naran (PID: enk)
 * @version 10/1/2022
 * 
 */
public class LinkedList<T> {
    private Node<T> low;
    private Node<T> high;
    private Node<T> cur;
    private int size;

    /**
     * LinkedList constructor
     * 
     */
    public LinkedList() {
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
    public void addLow(T input) {
        if (isEmpty()) {

            low = new Node<T>(input, null);
            cur = low;
            high = low;
            size++;

        }

        else {
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
    public void addHigh(T input) {
        if (isEmpty()) {
            low = new Node<T>(input, null);
            high = low;
            cur = low;
            size++;
        }
        else {
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
    public int getSize() {
        return size;
    }

    /**
     * Checks to see if the list is empty
     * 
     * @return boolean
     *         True if empty, False if not
     * 
     */
    public boolean isEmpty() {
        return low == null;
    }


    /**
     * Clears the list
     * 
     */
    public void clear() {
        low = null;
        high = null;
    }
}