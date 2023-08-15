package it.firstdocker.AccessService;

import it.firstdocker.Entity.User;
import it.firstdocker.Full.CustomerFull;
import it.firstdocker.Repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
public class CustomerJPADataAccessService implements CustomerFull {

    private final CustomerRepository customerRepository;

    public CustomerJPADataAccessService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<User> selectAllCustomers() {
        Page<User> page = customerRepository.findAll(Pageable.ofSize(1000));
        return page.getContent();
    }

    @Override
    public Optional<User> selectCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void insertCustomer(User customer) {
        customerRepository.save(customer);
    }

    @Override
    public boolean existsCustomerWithEmail(String email) {
        return customerRepository.existsUserByEmail(email);
    }

    @Override
    public boolean existsCustomerById(Long id) {
        return customerRepository.existsUserById(id);
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public void updateCustomer(User update) {
        customerRepository.save(update);
    }

    @Override
    public Optional<User> selectUserByEmail(String email) {
        return customerRepository.findUserByEmail(email);
    }

}
