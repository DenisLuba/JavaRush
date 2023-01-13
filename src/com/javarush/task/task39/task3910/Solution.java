package com.javarush.task.task39.task3910;

import org.junit.Assert;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;

public class Solution {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree(3));
        System.out.println(isPowerOfThree(9));
        System.out.println(isPowerOfThree(27));
    }

    public static boolean isPowerOfThree(int n) {
        if (n == 0) return false;

        while(n % 3 == 0)
            n /= 3;

        return n == 1;
    }

    @Test
    public void testIsPowerOfThree() {
        Assert.assertFalse(isPowerOfThree(6));
        Assert.assertFalse(isPowerOfThree(20));
        Assert.assertFalse(isPowerOfThree(18));
        Assert.assertFalse(isPowerOfThree(0));

        Assert.assertTrue(isPowerOfThree(9));
        Assert.assertTrue(isPowerOfThree(27));
    }
}
