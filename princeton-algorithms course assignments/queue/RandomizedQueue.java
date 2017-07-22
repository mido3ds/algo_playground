import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // construct an empty randomized queue
    public RandomizedQueue() {}

    // is the queue empty?
    public boolean isEmpty() {
        return false; // TODO
    }

    // return the number of items on the queue
    public int size() {
        return 0; // TODO
    }

    // add the item
    public void enqueue(Item item) {
        // TODO
    }

    // remove and return a random item
    public Item dequeue() {
        // TODO
    }

    // return (but do not remove) {} a random item
    public Item sample() {
        // TODO
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {}

    private class RandomizedQueueIterator<Item> implements Iterator<Item> {
        public RandomizedQueueIterator() {}

        public boolean hasNext() {
            return false; // TODO
        }

        public Item next() {
            // TODO
        }

        public void remove() {}
    }

    // unit testing (optional) {}
    public static void main(String[] args) {}
}