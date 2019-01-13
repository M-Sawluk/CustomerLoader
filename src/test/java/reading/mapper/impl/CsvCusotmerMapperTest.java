package reading.mapper.impl;

import domain.ContactType;
import domain.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CsvCusotmerMapperTest {
    private String firstPerson = "Jan,Kowalski,12,Lublin,123123123,654 765 765,kowalski@gmail.com,jan@gmail.com";
    private String secondPerson = "Adam,Nowak,,Lublin,123123123,adam@gmail.com,12321,jbr";
    private CsvCustomerMapper csvCusotmerMapper;

    @Before
    public void setUp() {
        csvCusotmerMapper = new CsvCustomerMapper();
    }

    @Test
    public void testFirstPerson() {
        Customer firstPerson = csvCusotmerMapper.map(this.firstPerson);

        Assert.assertEquals(firstPerson.getId(), Long.valueOf(1L));
        Assert.assertEquals(firstPerson.getAge(), Integer.valueOf(12));
        Assert.assertEquals(firstPerson.getName(), "Jan");
        Assert.assertEquals(firstPerson.getSurname(), "Kowalski");
        Assert.assertEquals(firstPerson.getContacts().size(), 4);
        Assert.assertEquals(firstPerson.getContacts().get(0).getContactType(), ContactType.PHONE);
        Assert.assertEquals(firstPerson.getContacts().get(1).getContactType(), ContactType.PHONE);
        Assert.assertEquals(firstPerson.getContacts().get(2).getContactType(), ContactType.EMAIL);
        Assert.assertEquals(firstPerson.getContacts().get(3).getContactType(), ContactType.EMAIL);
    }

    @Test
    public void testSecondPerson() {

        Customer secondPerson = csvCusotmerMapper.map(this.secondPerson);

        Assert.assertEquals(secondPerson.getId(), Long.valueOf(1L));
        Assert.assertNull(secondPerson.getAge());
        Assert.assertEquals(secondPerson.getName(), "Adam");
        Assert.assertEquals(secondPerson.getSurname(), "Nowak");
        Assert.assertEquals(secondPerson.getContacts().size(), 4);
        Assert.assertEquals(secondPerson.getContacts().get(0).getContactType(), ContactType.PHONE);
        Assert.assertEquals(secondPerson.getContacts().get(1).getContactType(), ContactType.EMAIL);
        Assert.assertEquals(secondPerson.getContacts().get(2).getContactType(), ContactType.UNKNOWN);
        Assert.assertEquals(secondPerson.getContacts().get(3).getContactType(), ContactType.JABBER);
    }
}