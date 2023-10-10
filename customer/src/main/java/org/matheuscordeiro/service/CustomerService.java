package org.matheuscordeiro.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.matheuscordeiro.dto.CustomerDto;
import org.matheuscordeiro.entity.Customer;
import org.matheuscordeiro.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CustomerService {

    @Inject
    private CustomerRepository customerRepository;

    public List<CustomerDto> findAllCustomers(){
        final var customerDtoList = new ArrayList<CustomerDto>();
        customerRepository.findAll().stream().forEach(item->{
            customerDtoList.add(mapCustomerEntityToDTO(item));
        });
        return customerDtoList;
    }

    public CustomerDto findCustomerById(Long id) {
        return mapCustomerEntityToDTO(customerRepository.findById(id));
    }

    @Transactional
    public void createNewCustomer(CustomerDto customerDTO){
        customerRepository.persist(mapCustomerDtoToEntity(customerDTO));
    }

    @Transactional
    public void changeCustomer(Long id, CustomerDto customerDTO){
        final var customer = customerRepository.findById(id);
        customer.setName(customerDTO.name());
        customer.setAddress(customerDTO.address());
        customer.setPhone(customerDTO.phone());
        customer.setEmail(customerDTO.email());
        customer.setAge(customerDTO.age());
        customerRepository.persist(customer);
    }

    @Transactional
    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }

    private CustomerDto mapCustomerEntityToDTO(Customer customer){
        return CustomerDto.builder().address(customer.getAddress()).age(customer.getAge()).email(customer.getEmail())
                .name(customer.getName()).phone(customer.getPhone()).build();
    }

    private Customer mapCustomerDtoToEntity(CustomerDto customerDto) {
        final var customer = new Customer();
        customer.setAddress(customerDto.address());
        customer.setAge(customerDto.age());
        customer.setEmail(customerDto.email());
        customer.setName(customerDto.name());
        customer.setPhone(customerDto.phone());
        return customer;
    }

}

