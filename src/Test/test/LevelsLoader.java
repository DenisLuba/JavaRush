package Test.test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class LevelsLoader {

    Path path = Paths.get("res.levels.txt");

    void getLevel(int level) {

        String p = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath().toString();

        System.out.println(p);
        String s = Thread
                .currentThread()
                .getContextClassLoader()
                .getResource(p + "res.levels.txt")
                .getPath();
    }

    public static void main(String[] args) {
        new LevelsLoader().getLevel(2);
    }
}
