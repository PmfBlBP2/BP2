package baza.proba;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestProizvodSelect
{
  private static void upitProizvoda(Session session)
  {
    Transaction tx = null;
    try
    { 
      tx = session.beginTransaction();
      Query query = session.createQuery("from Proizvod");
      List<Proizvod> list = query.list();
      for (Proizvod proizvod : list)
      {
        System.out.println("Proizvod: \"" + proizvod.getNaziv() + "\", "
            + proizvod.getKolicina() + "\", " + proizvod.getJedinicaMere());
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
    SessionFactory factory = new Configuration().configure() // configures
                                                             // settings from
                                                             // hibernate.cfg.xml
        .buildSessionFactory();
    Session session = factory.openSession();
    upitProizvoda(session);
    System.out.println("done");
  }
  
}
