package util;

import domain.ContactType;

public final class ContactTypeMapper {
    private final static String JABBER = "jabber";
    private final static String JBR = "jbr";

    public final static ContactType map(String contact) {
        if(contact.contains(JABBER) || contact.contains(JBR)) {
            return ContactType.JABBER;
        } else if (contact.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")) {
            return ContactType.EMAIL;
        } else if (contact.matches("\\d{9}|((\\d{3} ){2}\\d{3})")) {
            return ContactType.PHONE;
        } else {
            return ContactType.UNKNOWN;
        }
    }
}
