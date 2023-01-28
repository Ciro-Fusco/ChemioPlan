package com.example.FrontEnd.FrontEnd.Controller;

import com.example.FrontEnd.FrontEnd.model.SchedaFarmaco;
import com.example.FrontEnd.FrontEnd.model.SchedaPaziente;
import com.example.FrontEnd.FrontEnd.model.Utente;
import com.example.FrontEnd.FrontEnd.service.IFarmaciaService;
import com.example.FrontEnd.FrontEnd.service.IPazienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = { "/pazienti" })
public class PazientiController {

  @Autowired
  private IPazienteService pazienteService;
  @Autowired
  private IFarmaciaService farmaciaService;


  @RequestMapping(value= {""}, method = RequestMethod.GET)
  public String showPazientiHomePage(ModelMap model){
    return "PazientiHome";
  }

  @RequestMapping(value= {"/all"}, method = RequestMethod.GET)
  public String showPazientiPage(ModelMap model){
    model.addAttribute("Pazienti", pazienteService.getPazienti());
    return "Pazienti";
  }

  @RequestMapping(value= {"/{cf}"}, method = RequestMethod.GET)
  public String showUtentiPage(ModelMap model, @PathVariable String cf){
    model.addAttribute("Paziente", pazienteService.getPaziente(cf));
    return "Paziente";
  }

  @RequestMapping(value = {"/add-paziente-page"}, method = RequestMethod.GET)
  public String insertPazientePage(ModelMap model) {
    model.addAttribute("farmaci", farmaciaService.getAllFarmaci());
    model.addAttribute("scheda", new SchedaPaziente());
    return "AggiungiPaziente";
  }

  @RequestMapping(value = {"/add-paziente"}, method = RequestMethod.POST)
  public String insertPaziente(ModelMap model, @ModelAttribute SchedaPaziente scheda) {
    model.addAttribute("farmaci", farmaciaService.getAllFarmaci());
    model.addAttribute("message", pazienteService.addPaziente(scheda));
    model.addAttribute("scheda", new SchedaPaziente());
    return "AggiungiPaziente";
  }

  @RequestMapping(value = {"/cerca-paziente-page"}, method = RequestMethod.GET)
  public String cercaPazientePage(ModelMap model) {
    model.addAttribute("scheda", new SchedaPaziente());
    return "CercaPaziente";
  }

  @RequestMapping(value = {"/cerca-paziente"}, method = RequestMethod.POST)
  public String cercaPaziente(ModelMap model, @ModelAttribute SchedaPaziente scheda) {
    SchedaPaziente s = pazienteService.getPaziente(scheda.getCodiceFiscale());
    if (s == null) {
      model.addAttribute("scheda", new SchedaPaziente());
      model.addAttribute("message", "Codice fiscale " + scheda.getCodiceFiscale() + " errato");
      return "CercaPaziente";
    }
    model.addAttribute("Paziente", s);
    return "Paziente";
  }

  @RequestMapping(value = {"/modifica-paziente-page/{cf}"}, method = RequestMethod.GET)
  public String modificaPazientePage(ModelMap model, @PathVariable String cf) {
    model.addAttribute("farmaci", farmaciaService.getAllFarmaci());
    model.addAttribute("scheda", pazienteService.getPaziente(cf));
    return "ModificaPaziente";
  }

  @RequestMapping(value = {"/modifica-paziente"}, method = RequestMethod.POST)
  public String modificaPaziente(ModelMap model, @ModelAttribute SchedaPaziente scheda) {
    model.addAttribute("message", pazienteService.modificaPaziente(scheda));
    model.addAttribute("Paziente", pazienteService.getPaziente(scheda.getCodiceFiscale()));
    return "Paziente";
  }
}
