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
            PQElement tmp = this.heap[i];
            this.heap[i] = this.heap[smallest];
            this.heap[smallest] = tmp;
            // Recursively heapify the subtree rooted at the smallest child
            minHeapify(smallest, n);
        }
    }

    public boolean isEmpty() {
        return this.currentsize == 0;
    }

    // Referenz: https://www.geeksforgeeks.org/introduction-to-min-heap-data-structure/?ref=gcse_outind (08.04.2025)
    public boolean insert(String d, double p) {
        PQElement e = new PQElement(d, p);
        if (this.currentsize < this.maxsize) {
            this.heap[this.currentsize] = e;
            int index = this.currentsize;
            this.currentsize++;

            // Compare the new element with its parent and swap if necessary
            while (index > 0 && this.heap[(index - 1) / 2].getPrio() > heap[index].getPrio()) {
                tauschen(index, (index - 1) / 2);
                // Move up the tree to the parent of the current element
                index = (index - 1) / 2;
            }

            return true;
        }
        System.out.println("Queue ist voll!");
        return false;
    }

    public PQElement extractElement() {
        if (isEmpty()) {
            return null;
        }
        // Wurzel (erstes Element (geringste Prio) aus dem MinHeap) speichern
        PQElement min = this.heap[0];
        this.heap[0] = this.heap[this.currentsize - 1]; // Element mit höchster Prio an Wurzelstelle schieben
        this.heap[this.currentsize - 1] = null;
        currentsize--;

        minHeapify(0, this.currentsize);
        return min;
    }

    public String extractData() {
        if (isEmpty()) {
            return null;
        }
        PQElement data = extractElement();
        return data.getData();
    }

    public void update(String s, double n) {
        for (int i = 0; i < this.currentsize; i++) {
           if (this.heap[i].getData().equals(s)) {
               if (this.heap[i].getPrio() > n) {
                   this.heap[i].setPrio(n);
               }
               // Bubble Up - Element mit neuer Priorität muss Richtung Wurzel wandern
               int index = i;
               while (index > 0 && this.heap[(index - 1) / 2].getPrio() > heap[index].getPrio()) {
                   tauschen(index, (index - 1) / 2);
                   // Move up the tree to the parent of the current element
                   index = (index - 1) / 2;
               }
           }
        }
    }

    private void tauschen(int i, int j) {
        PQElement tmp = this.heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = tmp;
    }

}
