import java.util.List;

import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
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
    
    /* List down all the employees */
    ME.listEmployees1();
    
    ME.listEmployees2();
    
    ME.listEmployees3();
    
    ME.listEmployees4();
    
    ME.listEmployees5();
    
    ME.listEmployees6(6);
    
    /* Add few employee records in database */
    Integer empID1 = ME.addEmployee1("Zara", "Ali", 1_000);
    Integer empID2 = ME.addEmployee1("Daisy", "Das", 5_000);
    Integer empID3 = ME.addEmployee1("John", "Paul", 10_000);
    Integer empID4 = ME.addEmployee1("Mickey", "Mouse", 10_000);
    
    /* Update employee's records */
    ME.updateEmployee1(empID1, 5_250);
    ME.updateEmployee1(empID3, 23_000);
    
    /* Delete an employee from the database */
    ME.deleteEmployee1(empID2);
    ME.deleteEmployee2(empID4);
    
  }
  
  public Integer addEmployee1(String fname, String lname, int salary)
  {
    Session session = factory.openSession();
    Transaction tx = null;
    Integer employeeID = null;
    try
    {
      tx = session.beginTransaction();
      Employee employee = new Employee();
      employee.setFirstName(fname);
      employee.setLastName(lname);
      employee.setSalary(salary);
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
  
  public void listEmployees1()
  {
    System.out.println("\nAll employees data:");
    Session session = factory.openSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      String hql = "FROM Employee";
      Query query = session.createQuery(hql);
      List<Employee> employees = query.list();
      for (Iterator<Employee> iterator = employees.iterator(); iterator
          .hasNext();)
      {
        Employee employee = (Employee) iterator.next();
        System.out.print("First Name: " + employee.getFirstName());
        System.out.print("  Last Name: " + employee.getLastName());
        System.out.println("  Salary: " + employee.getSalary());
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
  
  public void listEmployees2()
  {
    System.out.println("\n First name of all employees:");
    Session session = factory.openSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      String hql = "SELECT E.firstName FROM Employee E";
      Query query = session.createQuery(hql);
      List<?> employees = query.list();
      for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();)
      {
        String employeeFirstName = (String) iterator.next();
        System.out.println("First Name: " + employeeFirstName);
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
  
  public void listEmployees3()
  {
    System.out.println("\n Employee with id equal to 10:");
    Session session = factory.openSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      String hql = "FROM Employee E WHERE E.id = 10";
      Query query = session.createQuery(hql);
      List<?> results = query.list();
      for (Iterator<?> iterator = results.iterator(); iterator.hasNext();)
      {
        Employee employee = (Employee) iterator.next();
        System.out.print("First Name: " + employee.getFirstName());
        System.out.print("  Last Name: " + employee.getLastName());
        System.out.println("  Salary: " + employee.getSalary());
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
  
  public void listEmployees4()
  {
    System.out.println("\n Employees with id greather than 10,");
    System.out.println("Order by first name desc and then by salary desc:");
    Session session = factory.openSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      String hql = "FROM Employee E WHERE E.id > 10 "
          + "ORDER BY E.firstName DESC, E.salary DESC ";
      Query query = session.createQuery(hql);
      List<?> results = query.list();
      for (Iterator<?> iterator = results.iterator(); iterator.hasNext();)
      {
        Employee employee = (Employee) iterator.next();
        System.out.print("First Name: " + employee.getFirstName());
        System.out.print("  Last Name: " + employee.getLastName());
        System.out.println("  Salary: " + employee.getSalary());
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
  
  public void listEmployees5()
  {
    System.out.println("\n Sum of employees salary, group by first name:");
    Session session = factory.openSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      String hql = "SELECT SUM(E.salary), E.firstName FROM Employee E "
          + "GROUP BY E.firstName";
      Query query = session.createQuery(hql);
      List<?> results = query.list();
      for (Iterator<?> iterator = results.iterator(); iterator.hasNext();)
      {
        Object[] objs = (Object[]) iterator.next();
        System.out.print("  Sum: " + (Long) objs[0]);
        System.out.println("  First name: " + (String) objs[1]);
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
  
  public void listEmployees6(int employeeId)
  {
    System.out.println(
        "\n Employee with id equal to parameter (" + employeeId + "):");
    Session session = factory.openSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      String hql = "FROM Employee E WHERE E.id = :employee_id";
      Query query = session.createQuery(hql);
      query.setParameter("employee_id", employeeId);
      List<?> results = query.list();
      for (Iterator<?> iterator = results.iterator(); iterator.hasNext();)
      {
        Object[] objs = (Object[]) iterator.next();
        System.out.print("  Sum: " + (Long) objs[0]);
        System.out.println("  First name: " + (String) objs[1]);
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
  
  public void updateEmployee1(Integer EmployeeID, int salary)
  {
    Session session = factory.openSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      Employee employee = (Employee) session.get(Employee.class, EmployeeID);
      employee.setSalary(salary);
      session.update(employee);
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
  
  public void updateEmployee2(Integer employeeID, int salary)
  {
    Session session = factory.openSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      String hql = "UPDATE Employee set salary = :salary "
          + "WHERE id = :employee_id";
      Query query = session.createQuery(hql);
      query.setParameter("salary", salary);
      query.setParameter("employee_id", employeeID);
      int result = query.executeUpdate();
      System.out.println("Rows affected: " + result);
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
  
  public void deleteEmployee1(Integer EmployeeID)
  {
    Session session = factory.openSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      Employee employee = (Employee) session.get(Employee.class, EmployeeID);
      session.delete(employee);
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
  
  public void deleteEmployee2(Integer employeeID)
  {
    Session session = factory.openSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      String hql = "DELETE FROM Employee " + "WHERE id = :employee_id";
      Query query = session.createQuery(hql);
      query.setParameter("employee_id", employeeID);
      int result = query.executeUpdate();
      System.out.println("Rows affected: " + result);
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