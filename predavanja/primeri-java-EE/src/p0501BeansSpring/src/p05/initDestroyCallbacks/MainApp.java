package p05.initDestroyCallbacks;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp
{
  public static void main(String[] args)
  {
    AbstractApplicationContext context = new ClassPathXmlApplicationContext(
        "p05/initDestroyCallbacks/Beans.xml");
        
    Poruka objA = (Poruka) context.getBean("objekat");
    
    objA.getMessage();
    
    context.registerShutdownHook();
  }
}