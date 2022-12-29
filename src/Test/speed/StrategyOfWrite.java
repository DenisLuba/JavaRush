package Test.speed;

import java.io.File;
import java.io.IOException;

public interface StrategyOfWrite {
    void getTimeOfWrite(File file) throws IOException;
}
