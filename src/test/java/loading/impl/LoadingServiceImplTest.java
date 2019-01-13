package loading.impl;

import configuration.DataSource;
import loading.ChunkLoadingService;
import org.junit.Assert;
import org.junit.Test;
import repository.CustomerRepository;

import java.net.URISyntaxException;

import static org.mockito.Mockito.mock;

public class LoadingServiceImplTest {
    private CustomerRepository customerRepository = mock(CustomerRepository.class);
    private ChunkLoadingService chunkLoadingService = new LoadingServiceImpl(customerRepository);

    @Test
    public void loadingXmlWithTwoCustomersTest() throws URISyntaxException {
        String path = this.getClass().getResource("/xmls/FullXml.xml").toURI().getPath().substring(1);
        long insertedCustomers = chunkLoadingService.executeChunkLoading(path, 10);

        Assert.assertEquals(insertedCustomers, 2);
    }

    @Test(expected = IllegalStateException.class)
    public void throwsExceptionWhenFileExtensionIsNotSupported() {
        long insertedCustomers = chunkLoadingService.executeChunkLoading("customers.pdf", 10);

        Assert.assertEquals(insertedCustomers, 2);
    }
}