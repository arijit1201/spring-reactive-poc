package com.akagami.micro;



import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {
	
	@Test
	public void testMono()
	{
		//Mono is a Publisher which emits at most one item
		Mono<String> monoString = Mono.just("Arijit").log();
		
		//Subscriber needs to subscribe to a publisher first
		monoString.subscribe(System.out::println);
		
		//Steps :
		//1. Subscribe Method call to Publisher from Subscriber
		//2. Subscription Event to Subscriber from Publisher
		//3. Request(n) method call from Subscriber to Publisher asking for n data
		//4. n number of onNext events from Publisher to Subscriber
		//5. Ends with the onComplete Event or onError event
	}
	
	//@Test
	public void testMono_withError()
	{
		
		Mono<?> monoString = Mono.just("Arijit")
				.then(Mono.error(new RuntimeException("Exception has occured")))
				.log();
		monoString.subscribe(System.out::println, (e) -> {
			System.out.println(e.getMessage());
		});
		
		
	}
	
	@Test
	public void testFlux()
	{
		
		Flux<String> fluxString = Flux.just("Arijit", "Luffy", "Zorro", "Chopper", "Nami", "Robin")
				//.map(object -> object.concat("-modified"))
				.concatWithValues("Azure")
				.log();
		fluxString.subscribe(System.out::println, (e) -> {
			System.out.println(e.getMessage());
		});
		
		
	}
	
	@Test
	public void testFlux_withError()
	{
		
		Flux<?> fluxString = Flux.just("Arijit", "Luffy", "Zorro", "Chopper", "Nami", "Robin")
				//.map(object -> object.concat("-modified"))
				.concatWith(Flux.error(new RuntimeException("Flux threw error")))
				.concatWithValues("Event after onError") //won't be printed since onError already emitted
				.log();
		fluxString.subscribe(System.out::println, (e) -> {
			System.out.println(e.getMessage());
		});
		
		
	}
}
