import java.util.*;

public class MinPQ {
    private PQElement[] heap;
    private int maxsize;
    private int currentsize;

    // Grundlegendes Verständnis für Implementation von HeapSort: https://www.geeksforgeeks.org/heap-sort/?ref=gcse_outind (08.04.2025)
    // Grundlegendes Verständnis für Min-Heap Datenstruktur: https://www.geeksforgeeks.org/introduction-to-min-heap-data-structure/?ref=gcse_outind (08.04.2025)
    // Konstruktor
    public MinPQ(int maxsize) {
        this.maxsize = maxsize;
        this.currentsize = 0;
        this.heap = new PQElement[maxsize];
    }

    // Idee für minHeapify: https://www.geeksforgeeks.org/introduction-to-min-heap-data-structure/?ref=gcse_outind (08.04.2025)
    public void minHeapify(int i, int n) {
        // Assume the root is the smallest element initially
        int smallest = i;
        // Calculate the indices of the left and right child of the current node
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        // Compare the left child with the current smallest
        if (l < n && this.heap[l].getPrio() < this.heap[smallest].getPrio()) smallest = l;

        // Compare the right child with the current smallest
        if (r < n && this.heap[r].getPrio() < this.heap[smallest].getPrio()) smallest = r;

        // If the current node is not the smallest, swap it with the smallest child
        if (smallest != i) {
            double tmp = this.heap[i].getPrio();
            this.heap[i].setPrio(this.heap[smallest].getPrio());
            this.heap[smallest].setPrio(tmp);
            // Recursively heapify the subtree rooted at the smallest child
            minHeapify(smallest, n);
        }
    }

    public boolean isEmpty() {
        if (this.heap == null) {
            return true;
        }
        return false;
    }

    // Referenz: https://www.geeksforgeeks.org/introduction-to-min-heap-data-structure/?ref=gcse_outind (08.04.2025)
    public boolean insert(String d, double p) {
        PQElement e = new PQElement(d, p);
        if (this.currentsize + 1 < this.maxsize) {
            this.heap[++this.currentsize] = e;
            int index = this.currentsize;

            // Compare the new element with its parent and swap if necessary
            while (index > 0 && this.heap[(index - 1) / 2].getPrio() > heap[index].getPrio()) {
                tauschen(index, (index - 1) / 2);
                // Move up the tree to the parent of the current element
                index = (index - 1) / 2;
            }

            return true;
        }
        return false;
    }

    // Function to delete a node from the min-heap
    public static void deleteMinHeap(List<Integer> heap,
                                     int value)
    {
        // Find the index of the element to be deleted
        int index = -1;
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i) == value) {
                index = i;
                break;
            }
        }
        // If the element is not found, return
        if (index == -1) {
            return;
        }
        // Replace the element to be deleted with the last
        // element
        heap.set(index, heap.get(heap.size() - 1));
        // Remove the last element
        heap.remove(heap.size() - 1);
        // Heapify the tree starting from the element at the
        // deleted index
        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;
            if (leftChild < heap.size()
                    && heap.get(leftChild)
                    < heap.get(smallest)) {
                smallest = leftChild;
            }
            if (rightChild < heap.size()
                    && heap.get(rightChild)
                    < heap.get(smallest)) {
                smallest = rightChild;
            }
            if (smallest != index) {
                Collections.swap(heap, index, smallest);
                index = smallest;
            }
            else {
                break;
            }
        }
    }

    public PQElement extractElement() {
        if (!isEmpty()) {

        }
    }

    public String extractData() {
    }

    public void update(String s, double n) {
    }

    private void tauschen(int i, int j) {
        PQElement tmp = this.heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = tmp;
    }

}
