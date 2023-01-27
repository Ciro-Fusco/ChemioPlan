package com.example.FrontEnd.FrontEnd.Controller;


import com.example.FrontEnd.FrontEnd.model.Prenotazione;
import com.example.FrontEnd.FrontEnd.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = { "/prenotazioni" }, method = RequestMethod.GET)
public class PrenotazioneController {

  /**
   * <p>Riferimento al layer Service.</p>
   */
  @Autowired
  private PrenotazioneService prenotazioneService;


  @RequestMapping(value= {""}, method = RequestMethod.GET)
  public String showPrenotazioniHomePage(ModelMap model){
    return "Prenotazioni_Home";
  }

  @RequestMapping(value= {"/all"}, method = RequestMethod.GET)
  public String showPrenotazioniiPage(ModelMap model){
    Prenotazione[] prenotazioni = prenotazioneService.getAllPrenotazioni();
    model.addAttribute("Prenotazioni", prenotazioni);
    return "Prenotazioni";
  }

  @RequestMapping(value= {"/{codice}"}, method = RequestMethod.GET)
  public String showPrenotazionePage(ModelMap model, @PathVariable String codice){
    Prenotazione prenotazione = prenotazioneService.getById(codice);
    model.addAttribute("Prenotazione", prenotazione);
    return "Prenotazione";
  }

  @RequestMapping(value = {"/add-prenotazione-page"}, method = RequestMethod.GET)
  public String insertPrenotazionePage(@ModelAttribute Prenotazione prenotazione, ModelMap model) {
    model.addAttribute("prenotazioni", prenotazioneService.getAllPrenotazioni());
    model.addAttribute("prenotazione", prenotazione);
    return "AggiungiPrenotazione";
  }

  @RequestMapping(value = {"/add-prenotazione"}, method = RequestMethod.POST)
  public String insertPrenotazione(@ModelAttribute Prenotazione prenotazione, ModelMap model) {
    model.addAttribute("prenotazioni", prenotazioneService.getAllPrenotazioni());
    model.addAttribute("message", prenotazioneService.addPrenotazione(prenotazione));
    model.addAttribute("prenotazione", new Prenotazione());
    return "AggiungiPrenotazione";
  }


  @RequestMapping(value = {"/modifica-prenotazione-page/{codice}"}, method = RequestMethod.GET)
  public String modificaPrenotazionePage(ModelMap model, @PathVariable String codice) {
    model.addAttribute("prenotazioni", prenotazioneService.getAllPrenotazioni());
    model.addAttribute("prenotazione", prenotazioneService.getById(codice));
    return "ModificaPrenotazione";
  }

  @RequestMapping(value = {"/modifica-prenotazione"}, method = RequestMethod.POST)
  public String modificaPrenotazione(ModelMap model, @ModelAttribute Prenotazione prenotazione) {
    model.addAttribute("message", prenotazioneService.updatePrenotazione(prenotazione));
    model.addAttribute("Prenotazione", prenotazioneService.getById(prenotazione.getCodice()));
    return "Prenotazione";
  }

  @RequestMapping(value = {"/elimina/{codice}"}, method = RequestMethod.DELETE)
  public String eliminaPrenotazione(ModelMap model, @PathVariable String codice) {
    Prenotazione[] prenotazioni = prenotazioneService.getAllPrenotazioni();
    model.addAttribute("message", prenotazioneService.deletePrenotazione(codice));
    model.addAttribute("Prenotazioni", prenotazioni);
    return "Prenotazioni";
  }
}
