package net.javaonline.spring.product.controller;

import java.util.List;

import net.javaonline.spring.product.dao.ItemMasterDAO;
import net.javaonline.spring.product.dto.ItemMaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController
{
  
  @Autowired
  private ItemMasterDAO itemMasterDao;
  
  @RequestMapping(value = "/list")
  public ModelAndView list()
  {
    List<ItemMaster> itemList = itemMasterDao.list();
    ModelAndView model = new ModelAndView("ItemMasterList");
    model.addObject("itemList", itemList);
    return model;
  }
  
  @RequestMapping(value = "/loadAddForm")
  public ModelAndView add()
  {
    ModelAndView model = new ModelAndView("ItemMasterAdd");
    ItemMaster item = new ItemMaster();
    model.addObject("item", item);
    List<ItemMaster> itemList = itemMasterDao.list();
    model.addObject("itemList", itemList);
    return model;
  }
  
  @RequestMapping(value = "/edit")
  public ModelAndView edit(@RequestParam(value = "id", required = true) Long id)
  {
    System.out.println("Id= " + id);
    ModelAndView model = new ModelAndView("ItemMasterAdd");
    ItemMaster item = itemMasterDao.getItem(id);
    model.addObject("item", item);
    List<ItemMaster> itemList = itemMasterDao.list();
    model.addObject("itemList", itemList);
    return model;
  }
  
  @RequestMapping(value = "/delete")
  public ModelAndView delete(
      @RequestParam(value = "id", required = true) Long id)
  {
    ModelAndView model = new ModelAndView("ItemMasterAdd");
    itemMasterDao.delete(id);
    List<ItemMaster> itemList = itemMasterDao.list();
    model.addObject("itemList", itemList);
    return model;
  }
  
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public ModelAndView save(@ModelAttribute("item") ItemMaster item)
  {
    System.out.println(item.getItem_name());
    if (null != item)
      itemMasterDao.add(item);
      
    ModelAndView model = new ModelAndView("ItemMasterAdd");
    item = new ItemMaster();
    model.addObject("item", item);
    List<ItemMaster> itemList = itemMasterDao.list();
    model.addObject("itemList", itemList);
    return model;
  }
  
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ModelAndView update(@ModelAttribute("item") ItemMaster item)
  {
    System.out.println(item.getItem_name());
    if (null != item)
      itemMasterDao.update(item);
      
    ModelAndView model = new ModelAndView("ItemMasterAdd");
    item = new ItemMaster();
    model.addObject("item", item);
    List<ItemMaster> itemList = itemMasterDao.list();
    model.addObject("itemList", itemList);
    return model;
  }
  
}
