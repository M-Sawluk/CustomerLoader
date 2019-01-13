package reading.selection.impl;

import reading.DataSelectionStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

public class CsvDataSelection implements DataSelectionStrategy {
    @Override
    public Optional<String> select(BufferedReader bufferedReader) throws IOException {
        String line = null;
        if((line = bufferedReader.readLine()) != null) {
            return Optional.of(line);
        }
        return Optional.empty();
    }
}
