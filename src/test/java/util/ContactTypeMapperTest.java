package util;

import domain.ContactType;
import org.junit.Assert;
import org.junit.Test;

public class ContactTypeMapperTest {
    public String JABBER_CONTACT_1 = "jbr";
    public String JABBER_CONTACT_2 = "michal@jabber.com";
    public String EMAIL_CONTACT = "michal@gmail.com";
    public String PHONE_CONTACT_1 = "111 111 222";
    public String PHONE_CONTACT_2 = "111111222";
    public String UNKNOWN_FORMAT = "1111";

    @Test
    public void mapperTest() {
        ContactType jabber_1 = ContactTypeMapper.map(JABBER_CONTACT_1);
        ContactType jabber_2 = ContactTypeMapper.map(JABBER_CONTACT_2);
        ContactType email = ContactTypeMapper.map(EMAIL_CONTACT);
        ContactType phone_1 = ContactTypeMapper.map(PHONE_CONTACT_1);
        ContactType phone_2 = ContactTypeMapper.map(PHONE_CONTACT_2);
        ContactType unknown = ContactTypeMapper.map(UNKNOWN_FORMAT);

        Assert.assertEquals(jabber_1, ContactType.JABBER);
        Assert.assertEquals(jabber_2, ContactType.JABBER);
        Assert.assertEquals(email, ContactType.EMAIL);
        Assert.assertEquals(phone_1, ContactType.PHONE);
        Assert.assertEquals(phone_2, ContactType.PHONE);
        Assert.assertEquals(unknown, ContactType.UNKNOWN);
    }

}