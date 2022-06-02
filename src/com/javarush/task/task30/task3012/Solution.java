package com.javarush.task.task30.task3012;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }
    public void createExpression(int number) {
        System.out.print(number + " = ");
        StringBuilder ternaryNumber = new StringBuilder();
        while(number > 0) {
            ternaryNumber.append(number % 3);
            if(number % 3 == 2) number = (number / 3) + 1;
            else number /= 3;
        }
        StringBuilder result = new StringBuilder();
        int i = 0;
        for(char ch : ternaryNumber.toString().toCharArray()) {
            switch (ch) {
                case '1' -> result.append(String.format("+ %d ", (int) Math.pow(3, i)));
                case '2' -> result.append(String.format("- %d ", (int) Math.pow(3, i)));
            }
            i++;
        }
        System.out.println(result.toString().trim());
    }
}
