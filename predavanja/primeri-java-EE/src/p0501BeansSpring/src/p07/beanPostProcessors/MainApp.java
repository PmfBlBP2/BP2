package p07.beanPostProcessors;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp
{
  public static void main(String[] args)
  {
    AbstractApplicationContext context = new ClassPathXmlApplicationContext(
        "p07/beanPostProcessors/Beans.xml");
        
    Poruka objA = (Poruka) context.getBean("objekat");
    
    objA.getMessage();
    
    context.registerShutdownHook();
  }
}