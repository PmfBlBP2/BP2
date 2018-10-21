package p02.hello;

public class Poruka
{
  private String poruka;
  
  public void setMessage(String message)
  {
    this.poruka = message;
  }
  
  public void getMessage()
  {
    System.out.println("Poruka je : " + poruka);
  }
}
