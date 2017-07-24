import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new java.lang.IllegalArgumentException();
        }

        int k = Integer.parseInt(args[0]);
        final RandomizedQueue<String> q = new RandomizedQueue<>();
        
        for (String s : StdIn.readAllStrings()) q.enqueue(s);
        for (int i = 0; i < k; i++) System.out.println(q.dequeue());
    }
}