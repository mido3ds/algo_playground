import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new java.lang.IllegalArgumentException();
        }

        int k = Integer.parseInt(args[0]);

        String[] allFileStrings = StdIn.readAllStrings();
        StdRandom.shuffle(allFileStrings);
        
        for (int i = 0; i < k; i++) System.out.println(allFileStrings[i]);
    }
}