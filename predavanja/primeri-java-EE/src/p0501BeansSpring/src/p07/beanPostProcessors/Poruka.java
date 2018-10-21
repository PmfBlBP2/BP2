package p07.beanPostProcessors;

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
  
  public void init()
  {
    System.out.println("Prolazi se kroz inicijalizaciju.");
  }
  
  public void destroy()
  {
    System.out.println("Sada ce objekat da bude unisten.");
  }
}
