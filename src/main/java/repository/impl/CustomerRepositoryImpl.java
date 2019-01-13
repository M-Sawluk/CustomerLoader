package repository.impl;

import configuration.DataSource;
import domain.Customer;
import domain.CustomerContact;
import repository.CustomerRepository;

import java.sql.*;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    private final static String CUSTOMER_SQL = "insert into customers_db.customers(id, name, surname, age) values (?,?,?,?)";
    private final static String CONTACTS_SQL = "insert into customers_db.contacts(ID_CUSTOMER, TYPE, CONTACT) values (?,?,?)";
    private final static String USER_COUNT = "select count(*) as count from customers_db.customers";


    @Override
    public void save(long idOffset, List<Customer> customers) {
        Connection connection = null;
        PreparedStatement customerInsertStatement = null;
        PreparedStatement contactsInsertStatement = null;
        try {
            connection = DataSource.getConnection();
            customerInsertStatement = connection.prepareStatement(CUSTOMER_SQL);
            connection.setAutoCommit(false);
            for (Customer customer : customers) {
                customerInsertStatement.setString(1, String.valueOf(customer.getId() + idOffset));
                customerInsertStatement.setString(2, customer.getName());
                customerInsertStatement.setString(3, customer.getSurname());
                customerInsertStatement.setString(4, customer.getAge() == null ? null: customer.getAge().toString());
                customerInsertStatement.addBatch();
            }
            customerInsertStatement.executeBatch();
            customerInsertStatement.close();

            contactsInsertStatement = connection.prepareStatement(CONTACTS_SQL);

            for (Customer customer : customers) {
                contactsInsertStatement.setString(1, customer.getId().toString());
                for (CustomerContact customerContact : customer.getContacts()) {
                    contactsInsertStatement.setString(2, String.valueOf(customerContact.getContactType().getType()));
                    contactsInsertStatement.setString(3, customerContact.getContact());
                    contactsInsertStatement.addBatch();
                }
            }
            contactsInsertStatement.executeBatch();
            contactsInsertStatement.close();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if(connection != null) connection.close();
                if(customerInsertStatement != null) customerInsertStatement.close();
                if(contactsInsertStatement != null) contactsInsertStatement.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public long getUserCount() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DataSource.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(USER_COUNT);
            resultSet.next();
            long count = resultSet.getLong("count");
            connection.close();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(connection != null) connection.close();
                if(statement != null) statement.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return 0;
    }


}
