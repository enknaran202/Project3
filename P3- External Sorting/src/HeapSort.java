import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

// On my honor:
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project
// with anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * A memory manager package for variable length
 * records, and a spatial data structure to support
 * geographical queries.
 * 
 * @author enk (Enk Naran)
 */
public class HeapSort
{
    /**
     * This is the entry point of the application
     * 
     * @param args
     *            Command line arguments
     * @throws IOException 
     */
    // !question! do i need to test the main?
    // !question! is it ok to just throw a ton of exceptions?
    public static void main(String[] args) throws IOException
    {
        // HeapSort <data-file-name> <num-buffers> <stat-file-name>
        // remember to keep a copy of the original data file
        String dataFileName = args[0];
        int numBuffers = Integer.parseInt(args[1]);
        String statFileName = args[2];
        RandomAccessFile file =  new RandomAccessFile(dataFileName, "rw");
        HeapSort heap = new HeapSort(file, numBuffers);
        HeapSort.heapSort(file, numBuffers);
    }

    private RandomAccessFile Heap; // Pointer to the heap array
    private int size; // Maximum size of the heap
    private int n; // Number of things now in heap
    private BufferPool pool;

    // Constructor supporting preloading of heap contents
    public HeapSort(RandomAccessFile file, int max) throws IOException
    {
        // how do i use RAF constructor? It requires a file object

        Heap = file;
        n = (int)Heap.length() / 4; // !Change! to records
        size = (int)Heap.length() / 4;
        pool = new BufferPool(max, Heap);
        buildheap();
    }


    public static void heapSort(RandomAccessFile A, int max) throws IOException
    {
        // The heap constructor invokes the buildheap method
        HeapSort H = new HeapSort(A, max);
        for (int i = 0; i < A.length() / 4; i++)
        { // Now sort
            H.removemax(); // Removemax places max at end of heap
        }
    }


    // Return current size of the heap
    private int heapsize()
    {
        return n;
    }


    // Return true if pos a leaf position, false otherwise
    private boolean isLeaf(int pos)
    {
        return (pos >= n / 2) && (pos < n);
    }


    // Return position for left child of pos
    private int leftchild(int pos)
    {
        if (pos >= n / 2)
        {
            return -1;
        }
        return 2 * pos + 1;
    }


    // Return position for right child of pos
    private int rightchild(int pos)
    {
        if (pos >= (n - 1) / 2)
        {
            return -1;
        }
        return 2 * pos + 2;
    }


    // Return position for parent
    private int parent(int pos)
    {
        if (pos <= 0)
        {
            return -1;
        }
        return (pos - 1) / 2;
    }


//    // Insert val into heap
//    private void insert(int key)
//    {
//        if (n >= size)
//        {
//            System.out.println("Heap is full");
//            return;
//        }
//        int curr = n++;
//        Heap[curr] = key; // Start at end of heap
//        // Now sift up until curr's parent's key > curr's key
//        while ((curr != 0) && (Heap[curr] > (Heap[parent(curr)])))
//        {
//            swap(Heap, curr, parent(curr));
//            curr = parent(curr);
//        }
//    }


    private void swap(BufferPool pool2, int curr, int last) throws IOException
    {
        short[] temp;
        temp = pool2.getIndex(curr);
        //pool2[curr] = pool2[last];
        pool2.setIndex(pool2.getIndex(last), curr);
        //pool2[last] = temp;
        pool2.setIndex(temp, last);
    }


    // Heapify contents of Heap
    private void buildheap() throws IOException
    {
        for (int i = n / 2 - 1; i >= 0; i--)
        {
            siftdown(i);
        }
    }


    // Put element in its correct place
    private void siftdown(int pos) throws IOException
    {
        if ((pos < 0) || (pos >= n))
        {
            return;
        } // Illegal position
        while (!isLeaf(pos))
        {
            int j = leftchild(pos);
            if ((j < (n - 1)) && (pool.getIndex(j)[0] < pool.getIndex(j + 1)[0]))
            {
                j++; // j is now index of child with greater value
            }
            if (pool.getIndex(pos)[0] >= (pool.getIndex(j)[0]))
            {
                return;
            }
            swap(pool, pos, j);
            pos = j; // Move down
        }
    }


    // Remove and return maximum value
    private short[] removemax() throws IOException
    {
        if (n == 0)
        {
            return new short[] {-1, 0};
        } // Removing from empty heap
        swap(pool, 0, --n); // Swap maximum with last value
        siftdown(0); // Put new heap root val in correct place
        return pool.getIndex(n);
    }


    // Remove and return element at specified position
    private short[] remove(int pos) throws IOException
    {
        if ((pos < 0) || (pos >= n))
        {
            return new short[] {-1, 0};
        } // Illegal heap position
        if (pos == (n - 1))
        {
            n--;
        } // Last element, no work to be done
        else
        {
            swap(pool, pos, --n); // Swap with last value
            update(pos);
        }
        return pool.getIndex(n);
    }


    // Modify the value at the given position
    private void modify(int pos, int newVal) throws IOException
    {
        if ((pos < 0) || (pos >= n))
        {
            return;
        } // Illegal heap position
        // Heap[pos] = newVal;
        update(pos);
    }


    // The value at pos has been changed, restore the heap property
    private void update(int pos) throws IOException
    {
        // If it is a big value, push it up
        // (Heap[parent(pos)])
        while ((pos > 0) && (pool.getIndex(pos)[0] > (pool.getIndex(parent(pos))[0])))
        {
            swap(pool, pos, parent(pos));
            pos = parent(pos);
        }
        siftdown(pos); // If it is little, push down
    }
}
