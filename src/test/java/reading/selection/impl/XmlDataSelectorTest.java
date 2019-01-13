package reading.selection.impl;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class XmlDataSelectorTest {
    private XmlDataSelector xmlDataSelector;

    @Before
    public void setUp() {
        xmlDataSelector = new XmlDataSelector();
    }

    @Test
    public void xmlWithPrologTest() throws IOException {
        InputStream resource = this.getClass().getResourceAsStream("/xmls/XmlProlog.xml");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource));
        Optional<String> customerData = xmlDataSelector.select(bufferedReader);
        bufferedReader.close();
        assertEquals(customerData, Optional.empty());
    }

    @Test
    public void xmlWithPrologAndSinglePersonTest() throws IOException {
        InputStream resource = this.getClass().getResourceAsStream("/xmls/XmlWithPrologAndSinglePerson.xml");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource));
        Optional<String> customerData = xmlDataSelector.select(bufferedReader);

        assertTrue(customerData.isPresent());

        assertTrue(customerData.get().contains("<surname>Kowalski</surname>"));
    }

}