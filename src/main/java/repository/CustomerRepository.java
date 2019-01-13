package repository;

import domain.Customer;

import java.util.List;

public interface CustomerRepository {
    void save(long idOffset, List<Customer> customers);

    long getUserCount();
}
