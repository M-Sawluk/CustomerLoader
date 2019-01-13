package reading.mapper.impl;

import domain.ContactType;
import domain.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class XmlCustomerMapperTest {
    private String firstPerson =
            "<person>\n" +
            "        <name>Jan</name>\n" +
            "        <surname>Kowalski</surname>\n" +
            "        <age>12</age>\n" +
            "        <city>Lublin</city>\n" +
            "        <contacts>\n" +
            "            <phone>123123123</phone>\n" +
            "            <phone>654 765 765</phone>\n" +
            "            <email>kowalski@gmail.com</email>\n" +
            "            <email>jan@gmail.com</email>\n" +
            "        </contacts>\n" +
            "</person>";
    private String secondPerson =
                    "<person>\n" +
                    "        <name>Adam</name>\n" +
                    "        <surname>Nowak</surname>\n" +
                    "        <city>Lublin</city>\n" +
                    "        <contacts>\n" +
                    "            <phone>123123123</phone>\n" +
                    "            <email>adam@gmail.com</email>\n" +
                    "            <icq>12321</icq>\n" +
                    "            <jabber>jbr</jabber>\n" +
                    "        </contacts>\n" +
                    "</person>";

    private XmlCustomerMapper xmlCustomerMapper;

    @Before
    public void setUp() {
        xmlCustomerMapper = new XmlCustomerMapper();
    }

    @Test
    public void testFirstPerson() {
        Customer customer = xmlCustomerMapper.map(firstPerson);

        Assert.assertEquals(customer.getId(), Long.valueOf(1L));
        Assert.assertEquals(customer.getAge(), Integer.valueOf(12));
        Assert.assertEquals(customer.getName(), "Jan");
        Assert.assertEquals(customer.getSurname(), "Kowalski");
        Assert.assertEquals(customer.getContacts().size(), 4);
        Assert.assertEquals(customer.getContacts().get(0).getContactType(), ContactType.PHONE);
        Assert.assertEquals(customer.getContacts().get(1).getContactType(), ContactType.PHONE);
        Assert.assertEquals(customer.getContacts().get(2).getContactType(), ContactType.EMAIL);
        Assert.assertEquals(customer.getContacts().get(3).getContactType(), ContactType.EMAIL);

    }

    @Test
    public void testSecondPerson() {
        Customer customer = xmlCustomerMapper.map(secondPerson);

        Assert.assertEquals(customer.getId(), Long.valueOf(1L));
        Assert.assertNull(customer.getAge());
        Assert.assertEquals(customer.getName(), "Adam");
        Assert.assertEquals(customer.getSurname(), "Nowak");
        Assert.assertEquals(customer.getContacts().size(), 4);
        Assert.assertEquals(customer.getContacts().get(0).getContactType(), ContactType.PHONE);
        Assert.assertEquals(customer.getContacts().get(1).getContactType(), ContactType.EMAIL);
        Assert.assertEquals(customer.getContacts().get(2).getContactType(), ContactType.UNKNOWN);
        Assert.assertEquals(customer.getContacts().get(3).getContactType(), ContactType.JABBER);

    }

}