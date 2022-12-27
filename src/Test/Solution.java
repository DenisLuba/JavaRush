package Test;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Result {
    public static String timeConversion(String s) {
        return LocalTime.parse(s,
                DateTimeFormatter.ofPattern("hh:mm:ssa",
                        Locale.US))
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")))) {

            String s = bufferedReader.readLine();

            String result = Result.timeConversion(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }
    }
}
