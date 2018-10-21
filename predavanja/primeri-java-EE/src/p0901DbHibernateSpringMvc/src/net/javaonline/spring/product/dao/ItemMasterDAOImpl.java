package net.javaonline.spring.product.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import net.javaonline.spring.product.dto.ItemMaster;

@Transactional
public class ItemMasterDAOImpl implements ItemMasterDAO
{
  
  private SessionFactory sessionFactory;
  
  public ItemMasterDAOImpl(SessionFactory sessionFactory)
  {
    this.sessionFactory = sessionFactory;
  }
  
  @Override
  public void add(ItemMaster item)
  {
    Session session = sessionFactory.getCurrentSession();
    try
    {
      session.beginTransaction(); 
      session.save(item);
    }
    catch (HibernateException e)
    {
      e.printStackTrace();
      session.getTransaction().rollback();
    }
    session.getTransaction().commit();
  }
  
  @Override
  public void update(ItemMaster item)
  {
    
    Session session = sessionFactory.getCurrentSession();
    try
    {
      System.out.println("IN Update");
      session.beginTransaction();
      session.saveOrUpdate(item);
    }
    catch (HibernateException e)
    {
      e.printStackTrace();
      session.getTransaction().rollback();
    }
    session.getTransaction().commit();
  }
  
  @Override
  public ItemMaster getItem(Long id)
  {
    Session session = sessionFactory.getCurrentSession();
    ItemMaster item = null;
    try
    {
      System.out.println("IN GetIteam");
      session.beginTransaction();
      item = (ItemMaster) session.get(ItemMaster.class, id);
    }
    catch (HibernateException e)
    {
      e.printStackTrace();
      session.getTransaction().rollback();
    }
    session.getTransaction().commit();
    return item;
  }
  
  @Override
  public void delete(Long id)
  {
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    ItemMaster item = (ItemMaster) session.get(ItemMaster.class, id);
    if (null != item)
    {
      session.delete(item);
    }
    session.getTransaction().commit();
    // return item;
  }
  
  @Override
  public List<ItemMaster> list()
  {
    
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    List<ItemMaster> items = null;
    try
    {
      System.out.println("IN LIST");
      items = (List<ItemMaster>) session.createQuery("from ItemMaster").list();
      
    }
    catch (HibernateException e)
    {
      e.printStackTrace();
      session.getTransaction().rollback();
    }
    session.getTransaction().commit();
    return items;
  }
  
}
