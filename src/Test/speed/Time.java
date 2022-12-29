package Test.speed;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class Time {
    public static void main(String[] args) throws IOException {
        String test = "test".repeat(10000000);
        String test2 = "test".repeat(100);


        System.out.println("Big text: ");
        test(test);

        System.out.println();
        System.out.println("Small text: ");
        test(test2);
    }

    public static void getTime(File file, StrategyOfWrite strategy) throws IOException {
        Date start = new Date();
        strategy.getTimeOfWrite(file);
        Date finish = new Date();
        System.out.println(finish.getTime() - start.getTime() + " ms");
    }

    public static void test(String test) throws IOException {
        String doc1 = "C:\\Users\\support\\Desktop\\Solution\\doc1.txt";
        String doc2 = "C:\\Users\\support\\Desktop\\Solution\\doc2.txt";
        String doc3 = "C:\\Users\\support\\Desktop\\Solution\\doc3.txt";
        String doc4 = "C:\\Users\\support\\Desktop\\Solution\\doc4.txt";
        String doc5 = "C:\\Users\\support\\Desktop\\Solution\\doc5.txt";
        String doc6 = "C:\\Users\\support\\Desktop\\Solution\\doc6.txt";
        String doc7 = "C:\\Users\\support\\Desktop\\Solution\\doc7.txt";

        System.out.print("BufferedWriter = ");
        getTime(new File(doc1), file -> {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(test);
            } catch (IOException ignore) {}
        });

        System.out.print("PrintWriter = ");
        getTime(new File(doc2), file -> {
            try(PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                writer.print(test);
            }
        });

        System.out.print("FileOutputStream = ");
        getTime(new File(doc3), file -> {
            try(FileOutputStream writer = new FileOutputStream(file)) {
                writer.write(test.getBytes());
            }
        });

        System.out.print("DataOutputStream = ");
        getTime(new File(doc4), file -> {
            try(DataOutputStream writer = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
                writer.write(test.getBytes());
            }
        });

        System.out.print("RandomAccessFile = ");
        getTime(new File(doc5), file -> {
            try(RandomAccessFile writer = new RandomAccessFile(file, "rw")) {
                writer.write(test.getBytes());
            }
        });

        System.out.print("FileChannel = ");
        getTime(new File(doc6), file -> {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            FileChannel writer = randomAccessFile.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(test.getBytes().length);
            buffer.put(test.getBytes());
            buffer.flip();
            writer.write(buffer);
            randomAccessFile.close();
            writer.close();
        });

        System.out.print("Files = ");
        getTime(new File(doc7), file -> {
            Files.write(Paths.get(file.toURI()), test.getBytes());
        });
    }
}
