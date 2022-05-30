package com.akagami.micro.integration.service;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.akagami.micro.domain.Customer;

import reactor.core.publisher.Flux;

@Component
public class CustomerDAO {
	private static void sleepExecution(int i)
	{
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Customer> getCustomers (){
		return IntStream.rangeClosed(1, 25)
		.peek(i -> System.out.println("processing - "+i))
		.peek(i -> sleepExecution(500))
		.mapToObj(i -> new Customer(i, "customer-"+i))
		.collect(Collectors.toList());
	}
	public Flux<Customer> getCustomersStream() {
		// TODO Auto-generated method stub
		return Flux.range(1, 25)
				.doOnNext(i -> System.out.println("processing in stream flow - "+i))
				.delayElements(Duration.ofMillis(500))
				.map(i -> new Customer(i, "customer-"+i));
		
	}
}
