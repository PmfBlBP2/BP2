package vladofilipovic;

import juma.mohammad.Greeting;
import juma.mohammad.service.GreetingImplService;

public class Client 
{

   public static void main(String[] args)
   { 
  	 	GreetingImplService service = new GreetingImplService();
  	 	Greeting greeting = service.getGreetingImplPort();
  	 	System.out.println("------->>  Call Started");
  	 	System.out.println(greeting.sayHello("Ali"));
  	 	System.out.println("------->>  Call Ended");
   }
}