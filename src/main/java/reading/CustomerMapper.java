package reading;

import domain.Customer;

public interface CustomerMapper {
    Customer map(String customerData);
}
