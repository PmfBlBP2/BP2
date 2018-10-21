package juma.mohammad;

import javax.jws.WebService;

@WebService(endpointInterface = "juma.mohammad.Greeting")
public class GreetingImpl implements Greeting
{
	
	@Override
	public String sayHello(String name)
	{
		return "Hello, Welcom to jax-ws " + name;
	}
	
}
