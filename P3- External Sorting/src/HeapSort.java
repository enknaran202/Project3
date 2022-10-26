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
     */
    public static void main(String[] args)
    {
        // HeapSort <data-file-name> <num-buffers> <stat-file-name>
        // remember to keep a copy of the original data file
        String dataFileName = args[0];
        int numBuffers = Integer.parseInt(args[1]);
        String statFileName = args[2];
    }

    private int[] Heap; // Pointer to the heap array
    private int size; // Maximum size of the heap
    private int n; // Number of things now in heap

    // Constructor supporting preloading of heap contents
    public HeapSort(int[] toSort, int num, int max)
    {
        Heap = toSort;
        n = num;
        size = max;
        buildheap();
    }


    public static void heapsort(int[] A)
    {
        // The heap constructor invokes the buildheap method
        HeapSort H = new HeapSort(A, A.length, A.length);
        for (int i = 0; i < A.length; i++)
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


    // Insert val into heap
    private void insert(int key)
    {
        if (n >= size)
        {
            System.out.println("Heap is full");
            return;
        }
        int curr = n++;
        Heap[curr] = key; // Start at end of heap
        // Now sift up until curr's parent's key > curr's key
        while ((curr != 0) && (Heap[curr] > (Heap[parent(curr)])))
        {
            swap(Heap, curr, parent(curr));
            curr = parent(curr);
        }
    }


    private void swap(int[] heap2, int curr, int last)
    {
        int temp = 0;
        temp = heap2[curr];
        heap2[curr] = heap2[last];
        heap2[last] = temp;
    }


    // Heapify contents of Heap
    private void buildheap()
    {
        for (int i = n / 2 - 1; i >= 0; i--)
        {
            siftdown(i);
        }
    }


    // Put element in its correct place
    private void siftdown(int pos)
    {
        if ((pos < 0) || (pos >= n))
        {
            return;
        } // Illegal position
        while (!isLeaf(pos))
        {
            int j = leftchild(pos);
            if ((j < (n - 1)) && (Heap[j] < (Heap[j + 1])))
            {
                j++; // j is now index of child with greater value
            }
            if (Heap[pos] >= (Heap[j]))
            {
                return;
            }
            swap(Heap, pos, j);
            pos = j; // Move down
        }
    }


    // Remove and return maximum value
    private Comparable removemax()
    {
        if (n == 0)
        {
            return -1;
        } // Removing from empty heap
        swap(Heap, 0, --n); // Swap maximum with last value
        siftdown(0); // Put new heap root val in correct place
        return Heap[n];
    }


    // Remove and return element at specified position
    private Comparable remove(int pos)
    {
        if ((pos < 0) || (pos >= n))
        {
            return -1;
        } // Illegal heap position
        if (pos == (n - 1))
        {
            n--;
        } // Last element, no work to be done
        else
        {
            swap(Heap, pos, --n); // Swap with last value
            update(pos);
        }
        return Heap[n];
    }


    // Modify the value at the given position
    private void modify(int pos, int newVal)
    {
        if ((pos < 0) || (pos >= n))
        {
            return;
        } // Illegal heap position
        Heap[pos] = newVal;
        update(pos);
    }


    // The value at pos has been changed, restore the heap property
    private void update(int pos)
    {
        // If it is a big value, push it up
        while ((pos > 0) && (Heap[pos] > (Heap[parent(pos)])))
        {
            swap(Heap, pos, parent(pos));
            pos = parent(pos);
        }
        siftdown(pos); // If it is little, push down
    }
}
