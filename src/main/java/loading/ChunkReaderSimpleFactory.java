package loading;

import reading.ChunkReader;
import reading.mapper.impl.CsvCustomerMapper;
import reading.mapper.impl.XmlCustomerMapper;
import reading.impl.FileChunkReader;
import reading.selection.impl.CsvDataSelection;
import reading.selection.impl.XmlDataSelector;
import util.FileSuffixes;

import java.io.IOException;

public final class ChunkReaderSimpleFactory {
    public static ChunkReader create(int chunkSize, String sourceFile) throws IOException {
        if(sourceFile.endsWith(FileSuffixes.CSV_SUFFIX)) {
            return new FileChunkReader(
                    new CsvDataSelection(),
                    new CsvCustomerMapper(),
                    sourceFile,
                    chunkSize);
        } else if (sourceFile.endsWith(FileSuffixes.XML_SUFFIX)){
            return new FileChunkReader(
                    new XmlDataSelector(),
                    new XmlCustomerMapper(),
                    sourceFile,
                    chunkSize);
        } else {
            throw new IllegalStateException("No chunk reader for given file extension: " + sourceFile);
        }
    }

}
