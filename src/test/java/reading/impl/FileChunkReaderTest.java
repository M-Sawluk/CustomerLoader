package reading.impl;


import domain.Customer;
import org.junit.Test;
import reading.mapper.impl.XmlCustomerMapper;
import reading.selection.impl.XmlDataSelector;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileChunkReaderTest {

    private FileChunkReader fileChunkReadingService;

    @Test
    public void xmlWithPrologTest() throws URISyntaxException, IOException {
        String path = this.getClass().getResource("/xmls/XmlProlog.xml").toURI().getPath().substring(1);
        fileChunkReadingService = new FileChunkReader(new XmlDataSelector(), new XmlCustomerMapper(), path, 5);
        List<Customer> chunk = fileChunkReadingService.getChunk();

        assertEquals(chunk, new ArrayList<>());
    }

    @Test
    public void xmlWithPrologAndSinglePersonTest() throws URISyntaxException, IOException {
        String path = this.getClass().getResource("/xmls/XmlWithPrologAndSinglePerson.xml").toURI().getPath().substring(1);
        fileChunkReadingService = new FileChunkReader(new XmlDataSelector(), new XmlCustomerMapper(), path, 5);
        List<Customer> chunk = fileChunkReadingService.getChunk();

        assertEquals(chunk.size(), 1);

        Customer customer = chunk.get(0);

        assertEquals(customer.getSurname(), "Kowalski");
        assertEquals(customer.getName(), "Jan");
    }

    @Test
    public void fullXmlTest() throws URISyntaxException, IOException {
        String path = this.getClass().getResource("/xmls/FullXml.xml").toURI().getPath().substring(1);
        fileChunkReadingService = new FileChunkReader(new XmlDataSelector(), new XmlCustomerMapper(), path, 5);
        List<Customer> chunk = fileChunkReadingService.getChunk();

        assertEquals(chunk.size(), 2);

        Customer customerOne = chunk.get(0);

        assertEquals(customerOne.getSurname(), "Kowalski");
        assertEquals(customerOne.getName(), "Jan");

        Customer customerTwo = chunk.get(1);

        assertEquals(customerTwo.getSurname(), "Nowak");
        assertEquals(customerTwo.getName(), "Adam");

    }

    @Test
    public void chunkSizeOneTest() throws URISyntaxException, IOException {
        String path = this.getClass().getResource("/xmls/FullXml.xml").toURI().getPath().substring(1);
        fileChunkReadingService = new FileChunkReader(new XmlDataSelector(), new XmlCustomerMapper(), path, 1);
        List<Customer> chunkOne = fileChunkReadingService.getChunk();

        assertEquals(chunkOne.size(), 1);

        Customer customerOne = chunkOne.get(0);

        assertEquals(customerOne.getSurname(), "Kowalski");
        assertEquals(customerOne.getName(), "Jan");

        List<Customer> chunkTwo = fileChunkReadingService.getChunk();

        Customer customerTwo = chunkTwo.get(0);

        assertEquals(customerTwo.getSurname(), "Nowak");
        assertEquals(customerTwo.getName(), "Adam");

    }


}