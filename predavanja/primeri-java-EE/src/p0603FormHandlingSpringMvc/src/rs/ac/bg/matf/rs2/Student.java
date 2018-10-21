package rs.ac.bg.matf.rs2;

public class Student
{
  private Integer age = 14;
  private String name = "Miki Maus";
  private Integer id;
  
  public void setAge(Integer age)
  {
    this.age = age;
  }
  
  public Integer getAge()
  {
    return age;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getName()
  {
    return name;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public Integer getId()
  {
    return id;
  }
}