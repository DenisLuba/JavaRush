package Test;

import java.util.*;
import java.text.*;

public class Test {
    private static void method(long a) {
        String f = "0".repeat(64);
        StringBuilder fs = new StringBuilder(f);
        for (int i = 32; i >= 1; i /= 2) {

            fs.replace(fs.length() - Long.toBinaryString(a).length(),
                    fs.length(),
                    Long.toBinaryString(a));
            System.out.println(fs);
            fs.replace(0, fs.length(), f);
            fs.replace(fs.length() - Long.toBinaryString(a >>> i).length(), fs.length(), Long.toBinaryString(a >>> i));
            System.out.println(fs);
            a ^= a >>> i;
            System.out.println("_______________________________________________________");
        }
        fs.replace(0, fs.length(), f);
        fs.replace(fs.length() - Long.toBinaryString(a).length(), fs.length(), Long.toBinaryString(a));
        System.out.println(fs + "\n&");
        System.out.println("0".repeat(63) + "1");
        System.out.println("_______________________________________________________");
        fs.replace(0, fs.length(), f);
        fs.replace(fs.length() - Long.toBinaryString(a & 1).length(), fs.length(), Long.toBinaryString(a & 1));
        System.out.println(fs);
    }

    public static void main(String[] args) {
//        method(1236548521);
        int n = 1000;
        System.out.println(Integer.toBinaryString(n));
        n = n >>> 8;
        System.out.println(n);

    }
}