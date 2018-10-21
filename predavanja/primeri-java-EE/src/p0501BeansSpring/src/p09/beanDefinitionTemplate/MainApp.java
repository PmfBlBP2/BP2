package p09.beanDefinitionTemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp
{
  public static void main(String[] args)
  {
    ApplicationContext context = new ClassPathXmlApplicationContext(
        "/p09/beanDefinitionTemplate/Beans.xml");
        
  
    HelloIndia objB = (HelloIndia) context.getBean("helloIndia");
    objB.getMessage1();
    objB.getMessage2();
    objB.getMessage3();
    
  }
}