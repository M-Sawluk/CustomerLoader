package loading.impl;

import domain.Customer;
import loading.ChunkLoadingService;
import loading.ChunkReaderSimpleFactory;
import reading.ChunkReader;
import repository.CustomerRepository;

import java.io.IOException;
import java.util.List;

public class LoadingServiceImpl implements ChunkLoadingService {
    private final CustomerRepository customerRepository;

    public LoadingServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public long executeChunkLoading(String fileSource, int chunkSize) {
        long insertedCustomers = 0;
        long userCount = customerRepository.getUserCount();
        try {
            ChunkReader chunkReadingService = ChunkReaderSimpleFactory.create(chunkSize, fileSource);
            List<Customer> customers;
            do {
                customers = chunkReadingService.getChunk();
                customerRepository.save(userCount, customers);
                insertedCustomers += customers.size();
            } while (customers.size() == chunkSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return insertedCustomers;
    }
}
