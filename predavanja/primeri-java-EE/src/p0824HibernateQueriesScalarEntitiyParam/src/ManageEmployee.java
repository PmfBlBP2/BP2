import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Projections;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class ManageEmployee
{
  private static SessionFactory factory;
  
  public static void main(String[] args)
  {
    try
    {
      factory = new AnnotationConfiguration().configure().
          // addPackage("com.xyz") //add package if used.
          addAnnotatedClass(Employee.class).buildSessionFactory();
    }
    catch (Throwable ex)
    {
      System.err.println("Failed to create sessionFactory object." + ex);
      throw new ExceptionInInitializerError(ex);
    }
    ManageEmployee ME = new ManageEmployee();
    
    /* Add few employee records in database */
    Integer empID1 = ME.addEmployee("Zara", "Ali", 2000);
    Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
    Integer empID3 = ME.addEmployee("John", "Paul", 5000);
    Integer empID4 = ME.addEmployee("Mohd", "Yasee", 3000);
    
    /* List down all the employees */
    ME.listEmployeesScalarQuery();
    
    ME.listEmployeesEntityQuery();
    
    ME.listEmployeesEntityParamQuery(41);
    
  }
  
  /* Method to CREATE an employee in the database */
  public Integer addEmployee(String fname, String lname, int salary)
  {
    Session session = factory.openSession();
    Transaction tx = null;
    Integer employeeID = null;
    try
    {
      tx = session.beginTransaction();
      Employee employee = new Employee(fname, lname, salary);
      employeeID = (Integer) session.save(employee);
      tx.commit();
    }
    catch (HibernateException e)
    {
      if (tx != null)
        tx.rollback();
      e.printStackTrace();
    }
    finally
    {
      session.close();
    }
    return employeeID;
  }
  
  /* Method to READ all the employees having salary more than 2000 */
  public void listEmployeesScalarQuery()
  {
    Session session = factory.openSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      String sql = "SELECT first_name, salary FROM EMPLOYEE";
      SQLQuery query = session.createSQLQuery(sql);
      query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
      List<?> results = query.list();
      for (Iterator<?> iterator = results.iterator(); iterator.hasNext();)
      {
        HashMap<String, Object> result = (HashMap<String, Object>) iterator
            .next();
        System.out.print("First Name: " + result.get("first_name"));
        System.out.println("  Salary: " + result.get("salary"));
      }
      tx.commit();
    }
    catch (HibernateException e)
    {
      if (tx != null)
        tx.rollback();
      e.printStackTrace();
    }
    finally
    {
      session.close();
    }
  }
  
  public void listEmployeesEntityQuery()
  {
    Session session = factory.openSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      String sql = "SELECT * FROM EMPLOYEE";
      SQLQuery query = session.createSQLQuery(sql);
      query.addEntity(Employee.class);
      List results = query.list();
      for (Iterator<?> iterator = results.iterator(); iterator.hasNext();)
      {
        Employee result = (Employee) iterator.next();
        System.out.print("First Name: " + result.getFirstName());
        System.out.print("   Last Name: " + result.getLastName());
        System.out.println("  Salary: " + result.getSalary());
      }
      tx.commit();
    }
    catch (HibernateException e)
    {
      if (tx != null)
        tx.rollback();
      e.printStackTrace();
    }
    finally
    {
      session.close();
    }
  }
  
  public void listEmployeesEntityParamQuery(int employeeId)
  {
    Session session = factory.openSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      String sql = "SELECT * FROM EMPLOYEE WHERE id = :employee_id";
      SQLQuery query = session.createSQLQuery(sql);
      query.addEntity(Employee.class);
      query.setParameter("employee_id", employeeId);
      List results = query.list();
      for (Iterator<?> iterator = results.iterator(); iterator.hasNext();)
      {
        Employee result = (Employee) iterator.next();
        System.out.print("First Name: " + result.getFirstName());
        System.out.print("   Last Name: " + result.getLastName());
        System.out.println("  Salary: " + result.getSalary());
      }
      tx.commit();
    }
    catch (HibernateException e)
    {
      if (tx != null)
        tx.rollback();
      e.printStackTrace();
    }
    finally
    {
      session.close();
    }
  }
}