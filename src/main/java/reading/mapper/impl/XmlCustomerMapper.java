package reading.mapper.impl;

import domain.Customer;
import domain.CustomerContact;
import domain.jaxb.model.PersonModel;
import reading.CustomerMapper;
import util.ContactTypeMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

public class XmlCustomerMapper implements CustomerMapper {
    private long customerId = 0;

    public Customer map(String customerData) {
        PersonModel personModel = null;
        List<CustomerContact> contacts = null;
        try {
            JAXBContext jaxbContext  = JAXBContext.newInstance(PersonModel.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            personModel = (PersonModel) unmarshaller.unmarshal(new StringReader(customerData));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        List<String> personContacts = personModel.getContacts();

        if(personContacts != null) {
            contacts = personModel
                    .getContacts()
                    .stream()
                    .map(contact -> CustomerContact
                            .builder()
                            .contact(contact)
                            .contactType(ContactTypeMapper.map(contact))
                            .customer_id(customerId)
                            .build())
                    .collect(Collectors.toList());
        }

        customerId++;

        return Customer
                .builder()
                .id(customerId)
                .age(personModel.getAge())
                .name(personModel.getName())
                .surname(personModel.getSurname())
                .contacts(contacts)
                .build();
    }
}
