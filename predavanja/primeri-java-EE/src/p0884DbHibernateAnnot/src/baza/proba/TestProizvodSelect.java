package baza.proba;

import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.dom4j.xpp.ProxyXmlStartTag;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestProizvodSelect
{
  private static SessionFactory factory;
  
  private static void upitProizvoda()
  {
    Session session = factory.openSession();
    Transaction tx = null;
    try
    { 
      tx = session.beginTransaction();
      List<Proizvod> proizvodi = session.createQuery("FROM Proizvod").list();
      for (Proizvod proizvod : proizvodi)
      {
        System.out.print("Naziv: " + proizvod.getNaziv());
        System.out.print("  Kolicina: " + proizvod.getKolicina());
        System.out.println("  Jedinica mjere: " + proizvod.getJedinicaMere());
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
  
  public static void main(String[] args)
  {
    try
    {
      factory = new Configuration().configure() // configures settings from
                                                // hibernate.cfg.xml
          .buildSessionFactory();
      upitProizvoda();
    }
    catch (Throwable ex)
    {
      System.err.println("Failed to create sessionFactory object." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }
  
}
