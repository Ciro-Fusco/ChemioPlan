package com.example.FrontEnd.FrontEnd.controller;

import com.example.FrontEnd.FrontEnd.model.Utente;
import com.example.FrontEnd.FrontEnd.service.IUtente_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.JstlView;

@Controller
@SessionAttributes("name")
public class Utente_Controller {

    @Autowired
    private IUtente_Service service;

    @RequestMapping(value= {"/index","/"}, method = RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        model.addAttribute("Name","FRANCESCO");
        return "Home";
    }

    @RequestMapping(value= {"/utenti"}, method = RequestMethod.GET)
    public String showUtentiPage(ModelMap model){
        Utente[] u=service.getUtenti();

//        System.out.println(u.length);

        model.addAttribute("Utenti",u);
        return "Utenti";
    }

    @RequestMapping(value= {"/utenti/{id}"}, method = RequestMethod.GET)
    public String showUtentePage(ModelMap model,@PathVariable Integer id){
        Utente u=service.getUtente(id);

        model.addAttribute("Utente",u);
        return "Utente";
    }

}
