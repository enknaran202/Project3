import student.TestCase;

public class HeapSortTest extends TestCase
{
    private HeapSort test;
    private int[] toSort;
    private int[] sorted;

    public void setUp()
    {
        toSort = new int[]
        { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
        sorted = new int[]
        { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        test = new HeapSort(toSort, toSort.length, toSort.length);
    }


    public void testHeapsort()
    {
        HeapSort.heapsort(toSort);
        assertEquals(sorted, toSort);
    }

// void testHeapsize()
// {
// fail("Not yet implemented");
// }
//
//
//
// void testIsLeaf()
// {
// fail("Not yet implemented");
// }
//
//
//
// void testLeftchild()
// {
// fail("Not yet implemented");
// }
//
//
//
// void testRightchild()
// {
// fail("Not yet implemented");
// }
//
//
//
// void testParent()
// {
// fail("Not yet implemented");
// }
//
//
//
// void testInsert()
// {
// fail("Not yet implemented");
// }

}
