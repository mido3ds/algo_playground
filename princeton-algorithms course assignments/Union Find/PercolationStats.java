import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private final int n;
    private double mean;
    private double stddev;
    private double confHi;
    private double confLow;
    private double[] experiment;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new java.lang.IllegalArgumentException();

        this.n = n;
        this.experiment = new double[trials];

        makeExperiment(trials);
        compute(trials);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return confHi;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confLow;
    }

    // test client (described below)
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new java.lang.IllegalArgumentException();
        }

        int n = Integer.parseInt(args[0]), t = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, t);

        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [ " + stats.confidenceLo() + ", "
                + stats.confidenceHi() + " ]");
    }

    private void compute(int trials) {
        // compute mean
        for (int i = 0; i < experiment.length; i++) {
            this.mean += experiment[i];
        }
        this.mean /= trials;

        // compute stddev
        for (int i = 0; i < experiment.length; i++) {
            this.stddev += Math.pow(experiment[i] - this.mean, 2);
        }
        this.stddev /= trials - 1;
        this.stddev = Math.sqrt(this.stddev);

        // compute confidence
        this.confHi = this.mean + (1.96 * this.stddev) / Math.sqrt(trials);
        this.confLow = this.mean - (1.96 * this.stddev) / Math.sqrt(trials);
    }

    private void makeExperiment(int trials) {
        // for try in trials:
        //    make model
        //    while not open:
        //        generate random number among blocked sites
        //        open site
        //    get num of open sites, num of all sites
        //    fraction = open sites / all sites
        //    save fraction in x[try]
        Percolation model;

        for (int i = 0; i < trials; i++) {
            model = new Percolation(n);

            while (!model.percolates()) {
                int[] random = getRandomClosedBlock(model);
                model.open(random[0], random[1]);
            }

            double fraction = (double) model.numberOfOpenSites() / this.n * this.n;
            experiment[i] = fraction;
        }
    }

    // return random col and row where there is an closed block
    private int[] getRandomClosedBlock(Percolation model) {
        int[] arr = new int[2];
        do {
            arr[0] = StdRandom.uniform(1, n + 1);
            arr[1] = StdRandom.uniform(1, n + 1);
        } while (model.isOpen(arr[0], arr[1]));

        return arr;
    }
}