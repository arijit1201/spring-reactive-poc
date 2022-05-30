package com.akagami.micro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akagami.micro.domain.Customer;
import com.akagami.micro.integration.service.CustomerDAO;

import reactor.core.publisher.Flux;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	public List<Customer> loadAllCustomers()
	{
		long start = System.currentTimeMillis();
		List<Customer> customers = customerDAO.getCustomers();
		long end = System.currentTimeMillis();
		System.out.println("Total time taken for execution is "+(end-start)+" ms");
		return customers;
	}
	
	public Flux<Customer> loadAllCustomersStream()
	{
		long start = System.currentTimeMillis();
		Flux<Customer> customers = customerDAO.getCustomersStream();
		long end = System.currentTimeMillis();
		System.out.println("Total time taken for async execution is "+(end-start)+" ms");
		return customers;
	}
}
