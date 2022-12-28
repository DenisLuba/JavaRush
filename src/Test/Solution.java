package Test;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; scanner.hasNext(); i++)
                stringBuilder.append(i)
                        .append(" ")
                        .append(scanner.nextLine())
                        .append("\n");

            System.out.println(stringBuilder);
        }
    }
}
