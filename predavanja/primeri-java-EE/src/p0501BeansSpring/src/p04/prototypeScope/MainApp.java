package p04.prototypeScope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import p02.hello.Poruka;

public class MainApp
{
  public static void main(String[] args)
  {
    ApplicationContext context = new ClassPathXmlApplicationContext(
        "p04/prototypeScope/Beans.xml");
        
    Poruka objA = (Poruka) context.getBean("objekat");
    
    objA.setMessage("I'm object A");
    objA.getMessage();
    
    Poruka objB = (Poruka) context.getBean("objekat");
    objB.getMessage();
  }
}