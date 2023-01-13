import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.util.concurrent.*;
import java.util.concurrent.TimeUnit;

class ThreadNumOfLines extends Thread {
    private String fileName;
    private int num_lines;

    public ThreadNumOfLines(String fileName) {
        this.fileName = fileName;
        this.num_lines = 0;
    }

    public int get_num_lines() {
        return num_lines;
    }

    @Override
    public void run() {
        // create a new BufferedReader to read from the file
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            // read each line and increment the line count
            while (br.readLine() != null) {
                num_lines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// A helper class that implements the Callable interface and counts the number of lines in a file
class LineNumCallable implements Callable<Integer> {
    private String fileName;

    public LineNumCallable(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Integer call() throws Exception {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}

public class Ex2_1 {
    /**
     * @param n amount of text files
     * @return array of text names
     */
    public static String[] createTextFiles(int n, int seed, int bound) {
        String[] fileNames = new String[n];
        Random rand = new Random(seed);
        for (int i = 0; i < n; i++) {
            fileNames[i] = "file_" + (i + 1) + ".txt";
            try {
                File file = new File(fileNames[i]);
                FileWriter writer = new FileWriter(file);
                int numLines = rand.nextInt(bound) + 1;
                for (int j = 0; j < numLines; j++) {
                    writer.write("Hello World\n");
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileNames;
    }
    /*
    get array of file names
     */
    public static int getNumOfLines(String[] fileNames) {
        int num_lines = 0;
        for (String fileName : fileNames) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                while (reader.readLine() != null) {
                    num_lines++;
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return num_lines;
    }

    public static int getNumOfLinesThreads(String[] fileNames) {
        int sumOfTextLines = 0;
        for (String fileName : fileNames) {
            ThreadNumOfLines fileTread = new ThreadNumOfLines(fileName);
            fileTread.run();
            sumOfTextLines = sumOfTextLines + fileTread.get_num_lines();
        }
        return sumOfTextLines;
    }

    public static int getNumOfLinesThreadPool(String[] fileNames) {
        // Create a thread pool with a fixed number of threads equal to the number of files
        ExecutorService threadPool = Executors.newFixedThreadPool(fileNames.length);

        // Create a list to store the Future objects representing the results of the tasks
        List<Future<Integer>> list = new ArrayList<>();

        // Submit the tasks to the thread pool
        for (String fileName : fileNames) {
            Callable<Integer> task = new LineNumCallable(fileName);
            Future<Integer> future = threadPool.submit(task);
            list.add(future);
        }

        // Initialize a counter to keep track of the total number of lines
        int totalLines = 0;

        // Iterate through the list of Future objects and retrieve the result of each task
        for (Future<Integer> future : list) {
            try {
                totalLines += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        // Shut down the thread pool
        threadPool.shutdown();
        return totalLines;
    }

    /*
    Only for cleanup all the text file that we created before
     */
    public static void clean(String [] fileNames) {
        for (String fileName : fileNames) {
            File file = new File(fileName);
            file.delete();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        long startTime, endTime, sum;
        int num;
        String[] file;
        file = createTextFiles(300, 100, 100000);
        System.out.println("files created");

        startTime = System.currentTimeMillis();
        num = getNumOfLines(file);   //q2
        endTime = System.currentTimeMillis();
        sum = endTime - startTime;
        System.out.println("func getNumOfLines = " + num);
        System.out.println("Execution time: " + sum + " milliseconds");

        startTime = System.currentTimeMillis();
        num = getNumOfLinesThreads(file);   //q2u
        endTime = System.currentTimeMillis();
        sum = endTime - startTime;
        System.out.println("func getNumOfLinesThreads = " + num);
        System.out.println("Execution time: " + sum + " milliseconds");

        startTime = System.currentTimeMillis();
        num = getNumOfLinesThreadPool(file);   //q2
        endTime = System.currentTimeMillis();
        sum = endTime - startTime;
        System.out.println("func getNumOfLinesThreadPool = " + num);
        System.out.println("Execution time: " + sum + " milliseconds");

        TimeUnit.SECONDS.sleep(1);
        clean(file);
    }
}

