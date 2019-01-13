package reading.selection.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

public class CsvDataSelectionTest {

    private CsvDataSelection csvDataSelection;


    @Before
    public void setUp() {
        csvDataSelection = new CsvDataSelection();
    }

    @Test
    public void emptyFileTest() throws IOException {
        InputStream resource = this.getClass().getResourceAsStream("/csvs/Empty.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource));
        Optional<String> customerData = csvDataSelection.select(bufferedReader);
        bufferedReader.close();
        Assert.assertEquals(customerData, Optional.empty());
    }

    @Test
    public void fullFileTest() throws IOException {
        InputStream resource = this.getClass().getResourceAsStream("/csvs/FullCsv.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource));
        Optional<String> customerData = csvDataSelection.select(bufferedReader);
        bufferedReader.close();
        Assert.assertTrue(customerData.isPresent());
        Assert.assertTrue(customerData.get().contains("Kowalski"));
    }


}