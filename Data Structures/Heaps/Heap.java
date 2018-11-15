package heaps;

public interface Heap {
    
    /**
     * 
     * @return the top element in the heap, the one with lowest key for min-heap or with
     * the highest key for max-heap
     * @throws Exception if heap is empty
     */
    public abstract HeapElement getElement() throws EmptyHeapException;
    /**
     * Inserts an element in the heap. Adds it to then end and toggle it until it finds its
     * right position.
     * 
     * @param element an instance of the HeapElement class.
     */
    public abstract void insertElement(HeapElement element);
    
    /**
     * Delete an element in the heap.
     * 
     * @param elementIndex int containing the position in the heap of the element to be deleted.
     */
    public abstract void deleteElement(int elementIndex);

}
