import java.util.*;
import java.io.*;

public class ExternalSort {

    // Estimate the best size of blocks based on the file size and available memory
    public static long estimateBestSizeOfBlocks(File fileToBeSorted) {
        long sizeOfFile = fileToBeSorted.length();
        final int MAX_TEMP_FILES = 1024;
        long blockSize = sizeOfFile / MAX_TEMP_FILES;
        long freeMemory = Runtime.getRuntime().freeMemory();

        if (blockSize < freeMemory / 2) {
            blockSize = freeMemory / 2;
        } else {
            if (blockSize >= freeMemory) {
                System.err.println("Warning: Running out of memory.");
            }
        }
        return blockSize;
    }

    // Sort and save a list of objects to a temporary file
    public static <T> File sortAndSave(List<T> tmpList, Comparator<T> cmp) throws IOException {
        Collections.sort(tmpList, cmp);
        File newTmpFile = File.createTempFile("sortInBatch", "flatfile");
        newTmpFile.deleteOnExit();

        try (BufferedWriter fbw = new BufferedWriter(new FileWriter(newTmpFile))) {
            for (T item : tmpList) {
                fbw.write(item.toString());
                fbw.newLine();
            }
        }

        return newTmpFile;
    }

    // Merge a list of temporary sorted files into a single output file
    public static <T> void mergeSortedFiles(List<File> files, File outputFile, Comparator<T> cmp) throws IOException {
        PriorityQueue<BinaryFileBuffer<T>> priorityQueue = new PriorityQueue<>(11,
                new Comparator<BinaryFileBuffer<T>>() {
                    public int compare(BinaryFileBuffer<T> i, BinaryFileBuffer<T> j) {
                        return cmp.compare(i.peek(), j.peek());
                    }
                });

        for (File file : files) {
            BinaryFileBuffer<T> binaryFileBuffer = new BinaryFileBuffer<>(file);
            priorityQueue.add(binaryFileBuffer);
        }

        try (BufferedWriter fbw = new BufferedWriter(new FileWriter(outputFile))) {
            while (!priorityQueue.isEmpty()) {
                BinaryFileBuffer<T> binaryFileBuffer = priorityQueue.poll();
                T item = binaryFileBuffer.pop();
                fbw.write(item.toString());
                fbw.newLine();

                if (!binaryFileBuffer.isEmpty()) {
                    priorityQueue.add(binaryFileBuffer);
                } else {
                    binaryFileBuffer.close();
                    binaryFileBuffer.getOriginalFile().delete();
                }
            }
        }
    }

    // Entry point: sort the input file and save the sorted output
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Please provide input and output file names");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        Comparator<String> comparator = Comparator.naturalOrder();
        List<File> sortedFiles = sortInBatch(new File(inputFile), comparator);
        mergeSortedFiles(sortedFiles, new File(outputFile), comparator);
    }

    // Sort the input file in batches and return a list of temporary sorted files
    public static List<File> sortInBatch(File file, Comparator<String> cmp) throws IOException {
        List<File> sortedFiles = new ArrayList<>();
        long blockSize = estimateBestSizeOfBlocks(file);

        try (BufferedReader fbr = new BufferedReader(new FileReader(file))) {
            List<String> tmpList = new ArrayList<>();
            String line;

            while ((line = fbr.readLine()) != null) {
                long currentBlockSize = 0;

                while (currentBlockSize < blockSize && line != null) {
                    tmpList.add(line);
                    currentBlockSize += line.length() + 40; // Add an estimated overhead for each line
                    line = fbr.readLine();
                }

                sortedFiles.add(sortAndSave(tmpList, cmp));
                tmpList.clear();
            }
        }

        return sortedFiles;
    }

    // Inner class representing a binary file buffer
    static class BinaryFileBuffer<T> {
        private BufferedReader fbr;
        private File originalFile;
        private String cache;
        private boolean empty;

        public BinaryFileBuffer(File file) throws IOException {
            originalFile = file;
            fbr = new BufferedReader(new FileReader(file));
            reload();
        }

        public boolean isEmpty() {
            return empty;
        }

                private void reload() throws IOException {
            try {
                if ((this.cache = fbr.readLine()) == null) {
                    empty = true;
                    cache = null;
                } else {
                    empty = false;
                }
            } catch (EOFException oef) {
                empty = true;
                cache = null;
            }
        }

        public void close() throws IOException {
            fbr.close();
        }

        public T peek() {
            if (isEmpty()) return null;
            return (T) cache;
        }

        public T pop() throws IOException {
            T answer = peek();
            reload();
            return answer;
        }

        public File getOriginalFile() {
            return originalFile;
        }
    }
}


