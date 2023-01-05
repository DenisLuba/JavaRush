package com.javarush.task.task39.task3901;

import org.junit.Test;
import org.junit.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length  is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        return s == null || s.length() == 0 ? 0 : getUniqueCharSequence(s).length();
    }

    private static String getUniqueCharSequence(String line) {
        Map<Character, Integer> chars = new HashMap<>();
        String result = "";
        for (int start = 0, end = 0; end < line.length(); end++) {
            char currentChar = line.charAt(end);
            if (chars.containsKey(currentChar))
                start = Math.max(start, chars.get(currentChar) + 1);
            if (result.length() < (end + 1) - start)
                result = line.substring(start, end + 1);
            chars.put(currentChar, end);
        }
        return result;
    }

    @Test
    public  void testGetUniqueCharSequence() {
        Assert.assertEquals("", getUniqueCharSequence(""));
        Assert.assertEquals("A", getUniqueCharSequence("A"));
        Assert.assertEquals("ABCDEF", getUniqueCharSequence("AABCDEF"));
        Assert.assertEquals("ABCDEF", getUniqueCharSequence("ABCDEFF"));
        Assert.assertEquals("NGISAWE", getUniqueCharSequence("CODINGISAWESOME"));
        Assert.assertEquals("be coding", getUniqueCharSequence("always be coding"));
        Assert.assertEquals("LEHIN", getUniqueCharSequence("HELLEHINNE"));
    }
}
