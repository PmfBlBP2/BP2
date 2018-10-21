package p01.hello;

public class HelloWorld
{
  private String message;
  
  public void setMessage(String message)
  {
    this.message = message;
  }
  
  public void getMessage()
  {
    System.out.println("Vasa poruka je : " + message);
  }
}
