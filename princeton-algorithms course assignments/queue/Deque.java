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

        Item[] newArray = (Item[]) new Object[3 / 2 * size];

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

        int addedSize = size / 2;
        Item[] newArray = (Item[]) new Object[size + addedSize];

        /** copy */
        for (int i = l; i <= r; i++) {
            newArray[addedSize + i] = array[i];
        }

        l += addedSize;
        r += addedSize;

        array = newArray;
    }

    /** remove and return the item from the front */
    public Item removeFirst() {
        if (size == 0) throw new java.util.NoSuchElementException();

        Item item = array[r];

        r++;
        size--;

        int leftSpace = r;
        if (size < leftSpace * 2) shrinkFromLeft();

        return item;
    }

    /** left space gets .5 smaller */
    private void shrinkFromLeft() {
        assert size != 0;

        int removedSpace = r / 2;
        Item[] newArray = (Item[]) new Object[array.length - removedSpace];

        // copy
        for (int i = l; i <= r; i++) {
            newArray[i - removedSpace] = array[i];
        }

        r -= removedSpace;
        l -= removedSpace;
    }

    /** remove and return the item from the end */
    public Item removeLast() {
        if (size == 0) throw new java.util.NoSuchElementException();

        Item item = array[l];

        l--;
        size--;

        int leftSpace = array.length - l;
        if (size < leftSpace * 2) shrinkFromRight();

        return item;
    }

    /** right space gets .5 smaller */
    private void shrinkFromRight() {
        assert size != 0;

        int removedSpace = (array.length - l) / 2;
        Item[] newArray = (Item[]) new Object[array.length - removedSpace];

        // copy
        for (int i = l; i <= r; i++) {
            newArray[i] = array[i];
        }
    }

    /** return an iterator over items in order from front to end */
    public Iterator<Item> iterator() {
        return new DequeIterator<>(this);
    }

    private class DequeIterator<Item> implements Iterator<Item> {
        private int index;
        private Deque<Item> deq;

        public DequeIterator(Deque<Item> d) {
            index = d.r;
            deq = d;

            assert deq != null && index >= 0;
        }

        public boolean hasNext() {
            return deq.l != index + 1;
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();

            return deq.array[index++];
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    /** unit testing */
    public static void main(String[] args) {
        Deque<String> d;
        String[] arr = new String[] {"hello", "hello again", "some string", "some another string",
                "some another string", "some another string", "some another string", "blah blah"};

        // first
        d = new Deque<>();
        d.checkRepresentation();
        assert d.isEmpty();
        d.addFirst(arr[0]);
        d.addLast(arr[1]);
        assert d.size() == arr.length;
        d.checkRepresentation();
        assert d.removeFirst() == arr[0];
        assert d.removeFirst() == arr[1];
        d.checkRepresentation();
        d.addFirst(arr[1]);
        assert d.removeLast() == arr[1];
        d.checkRepresentation();
        assert d.isEmpty();

        // second
        d = new Deque<>();
        for (String s : arr) d.addFirst(s);
        int i = arr.length;
        for (String s : d) {
            i--;
            assert s == arr[i];
        }
        assert d.removeFirst() == arr[0];
        assert d.removeFirst() == arr[1];
        d.checkRepresentation();
        assert d.removeLast() == arr[arr.length - 1];
        assert !d.isEmpty();
        d.checkRepresentation();
        int nowSize = d.size();
        while (!d.isEmpty()) {
            d.removeFirst();
            nowSize--;
        }
        d.checkRepresentation();
        assert d.size() == nowSize && nowSize == 0;

        // third, aggressive
        d = new Deque<>();
        String someString = "habal";
        for (i = 0; i < 50000; i++) {
            d.addFirst(someString);
            d.addLast(someString);
        }
        d.checkRepresentation();
        assert d.size() == 50000 * 2;
        for (i = 0; i < 50000; i++) {
            assert d.removeLast() == d.removeFirst();
        }
        d.checkRepresentation();
    }

    /** couple of assertions */
    private void checkRepresentation() {
        assert size >= 0;
        assert l >= r;
        assert r >= 0;

        int len = array.length;
        increaseToLeft();
        shrinkFromLeft();
        assert array.length == len;

        len = array.length;
        increaseToRight();
        shrinkFromRight();
        assert array.length == len;

        Iterator<Item> itr = iterator();
        assert itr.next() == array[r];
    }
}