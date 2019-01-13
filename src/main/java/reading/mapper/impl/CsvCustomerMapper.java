package reading.mapper.impl;

import domain.Customer;
import domain.CustomerContact;
import reading.CustomerMapper;
import util.ContactTypeMapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvCustomerMapper implements CustomerMapper {
    private long customerId = 0;
    private static final String SEPARATOR = ",";
    
    @Override
    public Customer map(String customerData) {
        String[] splitedCustomerData = customerData.split(SEPARATOR);

        List<CustomerContact> contacts = Stream
                .of(Arrays.copyOfRange(splitedCustomerData, 4, splitedCustomerData.length))
                .map(contact -> CustomerContact
                        .builder()
                        .contact(contact)
                        .contactType(ContactTypeMapper.map(contact))
                        .customer_id(customerId)
                        .build())
                .collect(Collectors.toList());

        Integer age = null;
        String personAge = splitedCustomerData[2];
        if(!personAge.isEmpty()) {
            age = Integer.valueOf(personAge);
        }

        customerId++;

        return Customer
                .builder()
                .id(customerId)
                .name(splitedCustomerData[0])
                .surname(splitedCustomerData[1])
                .age(age)
                .contacts(contacts)
                .build();
    }
}
