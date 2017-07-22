import java.util.Iterator;
import java.util.*;

public class Deque<Item> implements Iterable<Item> {
    private Item[] array;
    private int r = 1, l = 1;
    private int size = 0;

    /** construct an empty deque */
    public Deque() {
        array = (Item[]) new Object[3];
    }

    /** is the deque empty? */
    public boolean isEmpty() {
        return size == 0;
    }

    /** return the number of items on the deque */
    public int size() {
        return size;
    }

    /** add the item to the front */
    public void addFirst(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException();

        if (r == array.length - 1) {
            increaseToRight();
        }

        array[++r] = item;
        size++;
    }

    private void increaseToRight() {
        assert size != 0;

        Item[] newArray = (Item[]) new Object[size + (int) (.5 * size)];

        // copy
        for (int i = l; i <= r; i++) {
            newArray[i] = array[i];
        }

        array = newArray;
    }

    /** add the item to the end */
    public void addLast(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException();

        if (l == 0) {
            increaseToLeft();
        }

        array[--l] = item;
        size++;
    }

    private void increaseToLeft() {
        assert size != 0;

        int addedSize = (int) (.5 * size);
        Item[] newArray = (Item[]) new Object[size + addedSize];

        /** copy */
        for (int i = l; i <= r; i++) {
            newArray[addedSize + i] = array[i];
        }

        array = newArray;
    }

    /** remove and return the item from the front */
    public Item removeFirst() {
        if (size == 0) throw new java.util.NoSuchElementException();

        Item item = array[r];

        r++;
        size--;

        int leftSpace = r;
        if ((int) .5 * size < leftSpace) shrinkFromLeft();

        return item;
    }

    /** left space gets .5 smaller */
    private void shrinkFromLeft() {
        assert size != 0;

        
    }

    /** remove and return the item from the end */
    public Item removeLast() {
        if (size == 0) throw new java.util.NoSuchElementException();

        size--;
        return array[l--];
    }
    
    /** right space gets .5 smaller */
    private void shrinkFromRight() {}

    /** return an iterator over items in order from front to end */
    public Iterator<Item> iterator() {
        return new DequeIterator<>(0, this); // TODO
    }

    private class DequeIterator<Item> implements Iterator<Item> {
        private int index;
        private Deque<Item> deq;

        public DequeIterator(int startingIndex, Deque<Item> d) {
            index = startingIndex;
            deq = d;
        }

        public boolean hasNext() {
            return false; // TODO
        }

        public Item next() {
            return deq.array[++index]; // TODO
        }

        public void remove() {}
    }

    /** unit testing */
    public static void main(String[] args) {}
}