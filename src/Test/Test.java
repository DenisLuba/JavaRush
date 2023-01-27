package Test;

import java.io.File;

public class Test {
    String path = new File(this.getClass().getPackage().toString()).toString();
    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.path);
    }
}