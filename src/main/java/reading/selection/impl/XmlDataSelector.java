package reading.selection.impl;

import reading.DataSelectionStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

public class XmlDataSelector implements DataSelectionStrategy {
    private static final String CLOSING_ELEMENT = "</person>";
    private static final String XML_PROLOG = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
    private static final String LIST_OPEN_TAG = "<persons>";
    private static final String LIST_CLOSING_TAG = "</persons>";

    @Override
    public Optional<String> select(BufferedReader bufferedReader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (String line = null; (line = bufferedReader.readLine()) != null;) {
            if(!line.contains(XML_PROLOG) && !line.contains(LIST_OPEN_TAG) && !line.contains(LIST_CLOSING_TAG)) {
                stringBuilder.append(line);
            }
            if(line.contains(CLOSING_ELEMENT)) {
                return Optional.of(stringBuilder.toString());
            }
        }
        return Optional.empty();
    }
}
