package baza.proba;

import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestProizvodInsert
{
  
  public static void kreirajProizvod(Session session)
  {
    Transaction tx = null;
    try 
    {
      tx = session.beginTransaction();
      
      Proizvod proizvod = new Proizvod();
      
      Scanner ulaz = new Scanner(System.in);
      System.out.println("Naziv proizvoda");
      String naziv = ulaz.next();
      proizvod.setNaziv(naziv);
      
      System.out.println("Kolicina");
      int kolicina = ulaz.nextInt();
      proizvod.setKolicina(kolicina);
      
      System.out.println("Jedinica mjere");
      String jedinicaMjere = ulaz.next();
      proizvod.setJedinicaMere(jedinicaMjere);
      ulaz.close();
      
      session.save(proizvod);
      
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
  
  public static void main(String[] args)
  {
    SessionFactory factory = new Configuration().configure() // configures
                                                             // settings from
                                                             // hibernate.cfg.xml
        .buildSessionFactory();
        
    Session session = factory.openSession();
    
    kreirajProizvod(session);
    
    System.out.println("done");
  }
  
}
