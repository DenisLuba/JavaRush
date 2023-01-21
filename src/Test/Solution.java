package Test;

import org.junit.Test;

import java.io.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String A = reader.readLine();
            String B = reader.readLine();

            String string = Stream.of(A, B)
                    .map(s -> Pattern.compile("^.")
                            .matcher(s)
                            .replaceFirst(m -> m.group()
                                    .toUpperCase()))
                    .collect(Collectors.joining(" "));

            System.out.printf("%d\n%s\n%s",
                    A.length() + B.length(), A.compareTo(B) > 0 ? "Yes" : "No",
                    string);
        } catch (IOException ignore) {}
    }

    public static String getSmallestAndLargest(String s, int k) {
        String smallest = "";
        String largest = "";

        // Complete the function
        // 'smallest' must be lexicographically the smallest substring of length 'k'
        // 'largest' must be lexicographically the largest substring of length 'k'

        return smallest + "\n" + largest;
    }

    @Test
    public void testGetSmallestAndLargest() {

    }
}
