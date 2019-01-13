package reading;

import domain.Customer;

import java.io.IOException;
import java.util.List;

public interface ChunkReader {
    List<Customer> getChunk() throws IOException;
}
