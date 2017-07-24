import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int size = 0;

    /** is the queue empty? */
    public boolean isEmpty() {
        return size == 0;
    }

    /** return the number of items on the queue */
    public int size() {
        return size;
    }

    /** add the item */
    public void enqueue(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException();

        if (size == 0) {
            array = createGenericArray(1);
            array[0] = item;
        } else {
            if (size == array.length) increaseToRight();

            array[size] = item;
            shuffle();
        }

        size++;
    }

    /** remove and return a random item */
    public Item dequeue() {
        if (size == 0) throw new java.util.NoSuchElementException();

        int last = size - 1;
        Item item = array[last];
        array[last] = null;
        size--;

        int leftSpace = array.length - size;
        if (leftSpace > 2 * size) shrinkFromRight();

        return item;
    }

    /** return (but do not remove) a random item */
    public Item sample() {
        if (size == 0) throw new java.util.NoSuchElementException();

        return array[getRandomNumInRange()];
    }

    /** return an independent iterator over items in random order */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<>(this);
    }

    private class RandomizedQueueIterator<Item> implements Iterator<Item> {
        private RandomizedQueue<Item> rq;
        private int index = 0;

        public RandomizedQueueIterator(RandomizedQueue<Item> rq) {
            this.rq = rq;
        }

        public boolean hasNext() {
            return index < rq.size;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();

            return rq.array[index++];
        }
    }

    /** unit testing (optional) */
    public static void main(String[] args) {
        RandomizedQueue<String> rq;
        String[] arr = new String[] {"hello", "hello again", "some string", "some another string",
                "some another string", "some another string", "some another string", "blah blah"};

        // first
        rq = new RandomizedQueue<>();
        Iterator<String> itr = rq.iterator();
        assert !itr.hasNext();
        assert rq.isEmpty() && rq.size() == 0;
        for (String s : arr) rq.enqueue(s);
        assert itr.hasNext();
        int i = 0, cnt = 0;
        for (String s : rq) {
            if (i == arr.length) break;
            if (s == arr[i]) cnt++;
            i++;
        }
        assert cnt != arr.length;
        assert !rq.isEmpty();
        assert rq.size() == arr.length;

        System.out.println("all tests passed");
    }

    private Item[] createGenericArray(int size) {
        return (Item[]) new Object[size];
    }

    private int getRandomNumInRange() {
        return StdRandom.uniform(0, size);
    }

    private void increaseToRight() {
        if (size == 0) return;

        int addedSize = (int) Math.ceil(size / 2.0);
        Item[] newArray = createGenericArray(size + addedSize);

        // copy
        for (int i = 0; i < size; i++) newArray[i] = array[i];

        array = newArray;
    }

    private void shrinkFromRight() {
        if (size == 0) return;

        int removedSpace = (int) Math.ceil(size / 2.0);
        assert size <= array.length - removedSpace;
        Item[] newArray = createGenericArray(array.length - removedSpace);

        // copy
        for (int i = 0; i < size; i++) newArray[i] = array[i];

        array = newArray;
    }

    private void shuffle() {
        // if (size < 2) return;

        // int i1 = getRandomNumInRange(), i2 = getRandomNumInRange();
        // while (i2 == i1) i2 = getRandomNumInRange();

        // Item k = array[i1];
        // array[i1] = array[i2];
        // array[i2] = k;

        if (array == null) return;

        StdRandom.shuffle(array, 0, size);
    }
}