
/**
 * Classic generic node
 * 
 * @param <T>
 *            Generic version
 * @author Deep Datta (PID: ddeep21), Enk Naran (PID: enk)
 * @version 10/17/2022
 * 
 */
public class Node<T> {

    private T data;
    private Node<T> next;

    /**
     * Node constructor
     * 
     * @param data
     *            The data to be stored in the node
     * 
     * @param nextNode
     *            The node this one points to
     * 
     * 
     */
    public Node(T data, Node<T> nextNode) {
        // this is what's causing the problems
        this.data = data;
        next = nextNode;

    }


    /**
     * Node Constructor
     * 
     * @param nextNode
     *            The node this new node points to
     * 
     */
    public Node(Node<T> nextNode) {
        // seems unnecessary
        next = nextNode;
    }


    /**
     * What is inside the node
     * 
     * @return T
     *         The data inside the node
     * 
     */
    public T data() {

        return data;

    }


    /**
     * Changes the data in the node
     * 
     * @param newData
     *            The new data
     * 
     */
    public void setData(T newData) {

        data = newData;

    }


    /**
     * The node this node points to
     * 
     * @return Node
     *         The node this node points to
     */
    public Node<T> next() {

        return next;

    }


    /**
     * Changes the node this node points to
     * 
     * @param nextNode
     *            The new node to point to
     * 
     */
    public void setNext(Node<T> nextNode) {

        next = nextNode;

    }

}
