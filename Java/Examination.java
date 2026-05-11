import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class Examination {
    protected boolean isValidPath(String pathStr) {
        try {
            Paths.get(pathStr);
        } catch (InvalidPathException | NullPointerException e) {
            return false;
        }
        return true;
    }
}
