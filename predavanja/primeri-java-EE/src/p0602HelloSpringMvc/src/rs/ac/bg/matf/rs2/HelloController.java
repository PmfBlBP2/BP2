package rs.ac.bg.matf.rs2;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/hello")
public class HelloController
{
  
  @RequestMapping(method = RequestMethod.GET)
  public String printHello(ModelMap model)
  {
    model.addAttribute("message", "Hello sa casa RS2 " + new Date() + "!");
    model.addAttribute("proba", "Ovo je jedna mala proba");
    return "hello";
  }
  
}
