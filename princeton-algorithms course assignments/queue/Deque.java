import java.util.Iterator;
import java.util.*;

public class Deque<Item> implements Iterable<Item> {
    private Item[] array;
    private int first = -1, last = -1;
    private int size = 0;

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

        // empty
        if (size == 0) {
            array = (Item[]) new Object[2];
            first = 0;
            last = first;
        } else {
            // special case: at end from left
            if (first == 0) {
                increaseToLeft();
            }
            first--;
        }

        array[first] = item;
        size++;
    }

    /** add the item to the end */
    public void addLast(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException();

        // empty
        if (size == 0) {
            array = (Item[]) new Object[2];
            last = 1;
            first = last;
        } else {
            // special case: at end from right
            if (last == array.length - 1) {
                increaseToRight();
            }
            last++;
        }

        array[last] = item;
        size++;
    }

    /** remove and return the item from the front */
    public Item removeFirst() {
        if (size == 0) throw new java.util.NoSuchElementException();

        Item item = array[first];

        first++;
        size--;

        int leftSpace = first;
        if (leftSpace > 2 * size) shrinkFromLeft();

        return item;
    }

    /** remove and return the item from the end */
    public Item removeLast() {
        if (size == 0) throw new java.util.NoSuchElementException();

        Item item = array[last];

        last--;
        size--;

        int leftSpace = array.length - last;
        if (leftSpace > 2 * size) shrinkFromLeft();

        return item;
    }

    private void increaseToRight() {
        if (size == 0) return;

        int addedSize = (int) Math.ceil(size / 2.0);
        Item[] newArray = (Item[]) new Object[array.length + addedSize];

        // copy
        for (int i = first; i <= last; i++) {
            newArray[i] = array[i];
        }

        array = newArray;
    }

    private void increaseToLeft() {
        if (size == 0) return;

        int addedSize = (int) Math.ceil(size / 2f);
        Item[] newArray = (Item[]) new Object[array.length + addedSize];

        /** copy */
        for (int i = first; i <= last; i++) {
            newArray[addedSize + i] = array[i];
        }

        first += addedSize;
        last += addedSize;

        array = newArray;
    }

    /** left space gets .5 smaller */
    private void shrinkFromLeft() {
        assert array.length != 0;
        if (size == 0) return;

        int removedSpace = (int) Math.ceil(size / 2.0);
        assert size <= array.length - removedSpace;
        Item[] newArray = (Item[]) new Object[array.length - removedSpace];

        // copy
        for (int i = first; i <= last; i++) {
            newArray[i - removedSpace] = array[i];
        }

        last -= removedSpace;
        first -= removedSpace;

        array = newArray;
    }

    /** right space gets .5 smaller */
    private void shrinkFromRight() {
        assert array.length != 0;
        if (size == 0) return;

        int removedSpace = (int) Math.ceil(size / 2.0);
        assert size <= array.length - removedSpace;
        Item[] newArray = (Item[]) new Object[array.length - removedSpace];

        // copy
        for (int i = first; i <= last; i++) {
            newArray[i] = array[i];
        }

        array = newArray;
    }

    /** return an iterator over items in order from front to end */
    public Iterator<Item> iterator() {
        return new DequeIterator<>(this);
    }

    private class DequeIterator<Item> implements Iterator<Item> {
        private int index;
        private Deque<Item> deq;

        public DequeIterator(Deque<Item> d) {
            index = d.first;
            deq = d;

            assert deq != null && index >= 0;
        }

        public boolean hasNext() {
            return index + 1 != deq.last;
        }

        public Item next() {
            if (index > deq.last) throw new java.util.NoSuchElementException();

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
        assert d.size() == 2;
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
        assert d.removeLast() == arr[0];
        assert d.removeLast() == arr[1];
        d.checkRepresentation();
        assert d.removeFirst() == arr[arr.length - 1];
        assert !d.isEmpty();
        d.checkRepresentation();
        int nowSize = d.size();
        while (!d.isEmpty()) {
            d.removeFirst();
            nowSize--;
        }
        d.checkRepresentation();
        assert d.size() == nowSize && nowSize == 0;

        // third, stress
        d = new Deque<>();
        String someString = "habal";
        for (i = 0; i < 50000; i++) {
            String someOtherString = someString + "Foo";

            d.addFirst(someOtherString);
            d.addLast(someOtherString);
        }
        d.checkRepresentation();
        assert d.size() == 50000 * 2;
        for (i = 0; i < 50000; i++) {
            if (d.removeLast() == d.removeFirst()) System.out.println("yes #" + i);
        }
        d.checkRepresentation();
    }

    /** couple of assertions */
    private void checkRepresentation() {
        if (size == 0) return;

        assert size > 0;
        assert size <= array.length;
        assert last >= first;
        assert first >= 0;
        assert last < array.length;

        int len = array.length;
        increaseToLeft();
        shrinkFromLeft();
        assert array.length == len;

        len = array.length;
        increaseToRight();
        shrinkFromRight();
        assert array.length == len;

        Iterator<Item> itr = iterator();
        assert itr.next() == array[first];
    }
}