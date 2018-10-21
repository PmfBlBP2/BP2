package p07.beanPostProcessors;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.BeansException;

public class InitPoruka implements BeanPostProcessor
{
  
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException
  {
    System.out.println("Pre inicijalizacije : " + beanName);
    return bean; // you can return any other object as well
  }
  
  public Object postProcessAfterInitialization(Object bean, String beanName)
      throws BeansException
  {
    System.out.println("Posle inicijalizacije : " + beanName);
    return bean; // you can return any other object as well
  }
  
}
