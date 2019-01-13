package reading.impl;

import domain.Customer;
import reading.ChunkReader;
import reading.CustomerMapper;
import reading.DataSelectionStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileChunkReader implements ChunkReader {
    private final DataSelectionStrategy dataSelectionStrategy;
    private final CustomerMapper customerMapper;
    private final BufferedReader bufferedReader;
    private final int chunkSize;

    public FileChunkReader(DataSelectionStrategy dataSelectionStrategy, CustomerMapper customerMapper, String fileSource, int chunkSize) throws IOException {
        this.dataSelectionStrategy = dataSelectionStrategy;
        this.customerMapper = customerMapper;
        this.bufferedReader = Files.newBufferedReader(Paths.get(fileSource));
        this.chunkSize = chunkSize;
    }

    public List<Customer> getChunk() throws IOException {
        List<Customer> customers = new ArrayList<>();
        while (customers.size() < chunkSize) {
            Optional<String> selectedData = dataSelectionStrategy.select(bufferedReader);
            if (selectedData.isPresent()) {
                Customer customer = customerMapper.map(selectedData.get());
                customers.add(customer);
            } else {
                bufferedReader.close();
                break;
            }
        }
        return customers;
    }


}
