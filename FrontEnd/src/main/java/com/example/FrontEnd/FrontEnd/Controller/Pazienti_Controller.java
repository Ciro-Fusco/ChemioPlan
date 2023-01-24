package com.example.FrontEnd.FrontEnd.Controller;

import com.example.FrontEnd.FrontEnd.model.SchedaPaziente;
import com.example.FrontEnd.FrontEnd.model.Utente;
import com.example.FrontEnd.FrontEnd.service.IPaziente_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Pazienti_Controller {

  @Autowired
  private IPaziente_Service service;

  @RequestMapping(value= {"/pazienti"}, method = RequestMethod.GET)
  public String showUtentiPage(ModelMap model){
    SchedaPaziente[] schede = service.getPazienti();

//        System.out.println(u.length);

    model.addAttribute("Pazienti", schede);
    return "Pazienti";
  }

  @RequestMapping(value= {"/pazienti/{cf}"}, method = RequestMethod.GET)
  public String showUtentiPage(ModelMap model, @PathVariable String cf){
    SchedaPaziente scheda = service.getPazienteByCF(cf);

//        System.out.println(u.length);

    model.addAttribute("Paziente", scheda);
    return "Paziente";
  }
}
