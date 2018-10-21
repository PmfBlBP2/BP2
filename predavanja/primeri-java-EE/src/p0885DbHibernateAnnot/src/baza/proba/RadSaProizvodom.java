package baza.proba;

import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class RadSaProizvodom
{
  private static SessionFactory factory;
  
  /* Method to CREATE an Proizvod in the database */
  public Long dodajProizvod(String naziv, int kolicina, String jedinicaMere)
  {
    Session session = factory.openSession();
    Transaction tx = null;
    Long proizvodID = null;
    try
    { 
      tx = session.beginTransaction();
      Proizvod proizvod = new Proizvod();
      proizvod.setNaziv(naziv);
      proizvod.setKolicina(kolicina);
      proizvod.setJedinicaMere(jedinicaMere);
      proizvodID = (Long) session.save(proizvod);
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
    return proizvodID;
  }
  
  /* Method to READ all the Proizvods */
  public void izlistajProizvode()
  {
    Session session = factory.openSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      List proizvodi = session.createQuery("FROM Proizvod").list();
      for (Iterator iterator = proizvodi.iterator(); iterator.hasNext();)
      {
        Proizvod proizvod = (Proizvod) iterator.next();
        System.out.print("Id: " + proizvod.getProizvodId());
        System.out.print(" Naziv: " + proizvod.getNaziv());
        System.out.print("  Kolicina: " + proizvod.getKolicina());
        System.out.println("  Jedinica mere: " + proizvod.getJedinicaMere());
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
  
  /* Method to UPDATE salary for an Proizvod */
  public void azurirajKolicinu(Long ProizvodID, int kolicina)
  {
    Session session = factory.openSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      Proizvod proizvod = (Proizvod) session.get(Proizvod.class, ProizvodID);
      proizvod.setKolicina(kolicina);
      session.update(proizvod);
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
  
  /* Method to DELETE an Proizvod from the records */
  public void izbrisiProizvod(Long proizvodID)
  {
    Session session = factory.openSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      Proizvod proizvod = (Proizvod) session.get(Proizvod.class, proizvodID);
      session.delete(proizvod);
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
      factory = new Configuration().configure()
          .buildSessionFactory();
    }
    catch (Throwable ex)
    {
      System.err.println("Failed to create sessionFactory object." + ex);
      throw new ExceptionInInitializerError(ex);
    }
    RadSaProizvodom RSP = new RadSaProizvodom();
    
    /* dodaj proizvode u bazu */
    Long prId1 = RSP.dodajProizvod("Hleb", 1000, "kg");
    Long prId2 = RSP.dodajProizvod("Kisela voda", 500, "litar");
    Long prId3 = RSP.dodajProizvod("Pivo", 1000, "flasa");
    
    /* izlistaj proizvode */
    RSP.izlistajProizvode();
    
    /* azuriraj kolicinu za prvi dodati proizvod */
    RSP.azurirajKolicinu(prId1, 900);
    
    /* ukloni drugi dodati proizvod iz baze */
    RSP.izbrisiProizvod(prId2);
    
    /* izlistaj proizvode */
    RSP.izlistajProizvode();
  }
  
}
