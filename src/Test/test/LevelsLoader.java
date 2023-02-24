package Test.test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class LevelsLoader {

    Path path = Paths.get("res.levels.txt");

    void getLevel(int level) {

        String p = this.getClass().getPackageName();

        Path path1 = Path.of(p);

        System.out.println(path1.getParent() + ".res");
        String s = Thread
                .currentThread()
                .getContextClassLoader()
                .getResource(p + "src/Test/res.levels.txt")
                .getPath();
    }

    public static void main(String[] args) {
        new LevelsLoader().getLevel(2);
    }
}
