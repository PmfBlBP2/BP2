package v01.ch04.p06PackageTest;
/**
   @version 1.11 2004-02-19
   @author Cay Horstmann
*/

import java.util.Date;
import java.util.GregorianCalendar;

import v01.com.horstmann.*;

   // the Employee class is defined in that package

import static java.lang.System.*;

public class PackageTest
{  
   public static void main(String[] args)
   {  
      // because of the import statement, we don't have to
      // use com.horstmann.corejava.Employee here
      Employee harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);

      // raise salary by 5%
      harry.raiseSalary(5);

      // print out information about harry
      // use java.lang.System.out here
      out.println("name=" + harry.getName() + ",salary=" + harry.getSalary());
   }
}

class Employee
{  
   public Employee(String n, double s, int year, int month, int day)
   {  
      name = n;
      salary = s;
      GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
         // GregorianCalendar uses 0 for January
      hireDay = calendar.getTime();
   }

   public String getName()
   {  
      return name;
   }

   public double getSalary()
   {  
      return salary;
   }

   public Date getHireDay()
   {  
      return hireDay;
   }

   public void raiseSalary(double byPercent)
   {  
      double raise = salary * byPercent / 100;
      salary += raise;
   }

   private String name;
   private double salary;
   private Date hireDay;
}

