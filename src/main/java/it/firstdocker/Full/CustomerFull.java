package it.firstdocker.Full;


import it.firstdocker.Entity.User;

import java.util.List;
import java.util.Optional;

public interface CustomerFull {
    List<User> selectAllCustomers();
    Optional<User> selectCustomerById(Long id);
    void insertCustomer(User customer);
    boolean existsCustomerWithEmail(String email);
    boolean existsCustomerById(Long id);
    void deleteCustomerById(Long customerId);
    void updateCustomer(User update);
    Optional<User> selectUserByEmail(String email);
}
