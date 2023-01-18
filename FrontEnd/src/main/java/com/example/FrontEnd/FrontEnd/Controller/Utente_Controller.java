package com.example.FrontEnd.FrontEnd.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("name")
public class Utente_Controller {

    @RequestMapping(value= {"/index","/"}, method = RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        model.addAttribute("Name","FRANCESCO");
        return "Home";
    }

}
