package p06.initDestroyDefaultCallbacks;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import p05.initDestroyCallbacks.Poruka;

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