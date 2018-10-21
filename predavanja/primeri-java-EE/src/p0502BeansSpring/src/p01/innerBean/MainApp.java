package p01.innerBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp
{
  public static void main(String[] args)
  {
    ApplicationContext context = new ClassPathXmlApplicationContext(
        "p01/innerBeans/Beans.xml");
        
    TextEditor te = (TextEditor) context.getBean("textEditor");
    
    te.spellCheck();
  }
}