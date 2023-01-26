package com.example.FrontEnd.FrontEnd.Controller;

import com.example.FrontEnd.FrontEnd.model.SchedaFarmaco;
import com.example.FrontEnd.FrontEnd.model.SchedaPaziente;
import com.example.FrontEnd.FrontEnd.model.Utente;
import com.example.FrontEnd.FrontEnd.service.IFarmacia_Service;
import com.example.FrontEnd.FrontEnd.service.IPaziente_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = { "/pazienti" }, method = RequestMethod.GET)
public class Pazienti_Controller {

  @Autowired
  private IPaziente_Service pazienteService;

  @Autowired
  private IFarmacia_Service farmaciaService;

  @RequestMapping(value= {""}, method = RequestMethod.GET)
  public String showPazientiHomePage(ModelMap model){
    return "Pazienti_Home";
  }

  @RequestMapping(value= {"/all"}, method = RequestMethod.GET)
  public String showPazientiPage(ModelMap model){
    SchedaPaziente[] schede = pazienteService.getPazienti();
    model.addAttribute("Pazienti", schede);
    return "Pazienti";
  }

  @RequestMapping(value= {"/{cf}"}, method = RequestMethod.GET)
  public String showUtentiPage(ModelMap model, @PathVariable String cf){
    SchedaPaziente scheda = pazienteService.getPaziente(cf);
    model.addAttribute("Paziente", scheda);
    return "Paziente";
  }

  @RequestMapping(value = {"/add-paziente-page"}, method = RequestMethod.GET)
  public String insertFarmacoPage(@ModelAttribute SchedaPaziente scheda, ModelMap model) {
    model.addAttribute("farmaci", farmaciaService.getAllFarmaci());
    model.addAttribute("scheda", scheda);
    return "AggiungiPaziente";
  }

  @RequestMapping(value = {"/add-paziente"}, method = RequestMethod.POST)
  public String insertFarmaco(@ModelAttribute SchedaPaziente scheda, ModelMap model) {
    System.out.println(scheda);
    model.addAttribute("farmaci", farmaciaService.getAllFarmaci());
    model.addAttribute("message", pazienteService.addPaziente(scheda));
    model.addAttribute("scheda", scheda);
    return "AggiungiPaziente";
  }

}
