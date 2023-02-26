import java.util.Random;

public class Main {
    public static void main(String[] args){
        test();
        //benchmark();
    }


    // For testing purposes
    public static void test(){

        // Single Linked Queue
        SingleLinkedQueue<Integer> sq = new SingleLinkedQueue<Integer>();
        sq.remove();
        sq.add(1);
        sq.add(2);
        sq.add(3);
        sq.remove();
        sq.add(4);

        // Double Linked Queue
        DoubleLinkedQueue<Integer> dq = new DoubleLinkedQueue<Integer>();
        dq.remove();
        dq.add(1);
        dq.add(2);
        dq.add(3);
        dq.remove();
        dq.add(4);

        // Binary tree with queue (breadth first traversal)
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        tree.add(5,105);
        tree.add(2,102);
        tree.add(7,107);
        tree.add(1,101);
        tree.add(8,108);
        tree.add(6,106);
        tree.add(3,103);
        for (int i : tree)
            System.out.println("next value " + i);

        // Array queue
        ArrayQueue<Integer> aq = new ArrayQueue<Integer>(Integer.class, 4);
        aq.remove();
        aq.add(1);
        aq.add(2);
        aq.remove();
        aq.add(3);
        aq.add(4);
        aq.add(5);
        aq.remove();
        aq.remove();
    }


    // Benchmarks to be executed
    public static void benchmark(){
        int loops = 1000;
        int minLoops = 10;
        int dummyLoops = 10;
        int numberOfSizes = 9;
        int[] sizes = new int[numberOfSizes];
        for(int i = 0; i<numberOfSizes; i++){
            sizes[i] = (i+1)*1000;
        }
        Random rnd = new Random();

        // Text displayed
        System.out.println("\nBenchmarking binary tree lookup vs binary search algorithm");
        System.out.println("Loops for average time: " + loops);
        System.out.println("Loops for minimum time of average: " + minLoops);
        System.out.println("Dummy loops: " + dummyLoops);
        System.out.println("Unit of time: Nanoseconds");  //(*) Unit of time
        System.out.println("\nRunning benchmark...\n");
        System.out.printf("%s%30s%30s", "Size", "Binary tree lookup", "Binary search\n");

        // Benchmark every size
        long t0 = System.nanoTime();  // Start measuring time to complete benchmark
        for(int n : sizes) {

            // Prints out the size and creates the arrays for the benchmark
            System.out.print(n);
            int[] keys = new int[n];
            Benchmark[] benchmarks = {new SingleLinkedQueue<Integer>(), new DoubleLinkedQueue<Integer>()};

            // Benchmark every type of test
            for (Benchmark benchmark : benchmarks) {

                double minAverage = Double.MAX_VALUE;

                for (int minLoop = 0; minLoop < minLoops; minLoop++) {
                    double t_total = 0;  // Init

                    // Dummy
                    for (int d = 0; d < dummyLoops; d++) {
                        benchmark.bench();
                    }

                    // Benchmark
                    for (int loop = 0; loop < loops; loop++) {
                        t_total += benchmark.bench();
                    }

                    // Get average and minimum average
                    double average = t_total / loops;
                    if (average < minAverage) {
                        minAverage = average;
                    }
                }
                // Printing result
                System.out.printf("%25s", (int) minAverage);
            }
            System.out.println();
        }

        // Time
        long t1 = System.nanoTime();  // Stop measuring time to complete benchmark
        double time = t1-t0;
        time  = time/1000000000;
        System.out.println("\nBenchmark took " + time + " seconds");
    }
}
