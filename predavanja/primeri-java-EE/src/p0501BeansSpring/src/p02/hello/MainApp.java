package p02.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp
{
  public static void main(String[] args)
  {
    ApplicationContext context = new ClassPathXmlApplicationContext(
        "p02/hello/Beans.xml");
        
    Poruka obj = (Poruka) context.getBean("zdravoSvete");
    
    obj.getMessage();
  }
}