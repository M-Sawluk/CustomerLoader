package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class Customer {
    private final Long id;
    private final String name;
    private final String surname;
    private final Integer age;
    private final List<CustomerContact> contacts;

}
