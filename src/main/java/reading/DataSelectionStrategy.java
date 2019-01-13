package reading;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

public interface DataSelectionStrategy {
    Optional<String> select(BufferedReader bufferedReader) throws IOException;
}
