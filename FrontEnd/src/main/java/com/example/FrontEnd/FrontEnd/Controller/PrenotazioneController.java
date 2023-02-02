package com.example.FrontEnd.FrontEnd.Controller;

import com.example.FrontEnd.FrontEnd.model.Prenotazione;
import com.example.FrontEnd.FrontEnd.model.SchedaFarmaco;
import com.example.FrontEnd.FrontEnd.model.SchedaPaziente;
import com.example.FrontEnd.FrontEnd.service.FarmaciaService;
import com.example.FrontEnd.FrontEnd.service.PrenotazioneService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = { "/prenotazioni" })
public class PrenotazioneController {

  /**
   * <p>
   * Riferimento al layer Service.
   * </p>
   */
  @Autowired
  private PrenotazioneService prenotazioneService;
  @Autowired
  private FarmaciaService farmaciService;

  @RequestMapping(value = { "" }, method = RequestMethod.GET)
  public String showPrenotazioniHomePage(ModelMap model) {
    return "PrenotazioniHome";
  }

  @RequestMapping(value = { "/all" }, method = RequestMethod.GET)
  public String showPrenotazioniiPage(ModelMap model) {
    Prenotazione[] prenotazioni = prenotazioneService.getAllPrenotazioni();
    model.addAttribute("Prenotazioni", prenotazioni);
    return "Prenotazioni";
  }

  @RequestMapping(value = { "/{codice}" }, method = RequestMethod.GET)
  public String showPrenotazionePage(ModelMap model, @PathVariable String codice) {
    Prenotazione prenotazione = prenotazioneService.getById(codice);
    model.addAttribute("Prenotazione", prenotazione);
    return "Prenotazione";
  }

  @RequestMapping(value = { "/add-prenotazione-page" }, method = RequestMethod.GET)
  public String insertPrenotazionePage(@ModelAttribute Prenotazione prenotazione, ModelMap model) {
    // model.addAttribute("prenotazioni", prenotazioneService.getAllPrenotazioni());
    // model.addAttribute("prenotazione", prenotazione);
    return "AggiungiPrenotazione";
  }

  @RequestMapping(value = { "/add-prenotazione" }, method = RequestMethod.POST)
  public String insertPrenotazione(ModelMap model, @Valid @ModelAttribute("prenotazione") Prenotazione prenotazione,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("prenotazione", prenotazione);
      return "AggiungiPrenotazione";
    }

    try {
      farmaciService.getFarmaco(prenotazione.getCodiceFarmaci().get(0));
    } catch (Exception e) {
      model.addAttribute("msg_farmaco", "Farmaco Inesistente");
      model.addAttribute("prenotazione", prenotazione);
      return "AggiungiPrenotazione";
    }

    String r = prenotazioneService.addPrenotazione(prenotazione);
    System.out.println(r);
    if (r != null) {
      model.addAttribute("prenotazione", prenotazione);
      model.addAttribute("msg_paziente", "Paziente Inesistente");
      return "AggiungiPrenotazione";
    }
    // if(.contains("non trovato"));
    // System.out.println(farmaciService.getFarmaco());

    Prenotazione[] prenotazioni = prenotazioneService.getAllPrenotazioni();
    return "redirect:/prenotazioni/" + prenotazioni[prenotazioni.length - 1].getCodice();

    // model.addAttribute("message", p);
    // model.addAttribute("Prenotazione", prenotazione);
    // return "Prenotazione";
  }

  @RequestMapping(value = { "/modifica-prenotazione-page/{codice}" }, method = RequestMethod.GET)
  public String modificaPrenotazionePage(ModelMap model, @PathVariable String codice) {
    Prenotazione p = prenotazioneService.getById(codice);
    // model.addAttribute("prenotazioni", prenotazioneService.getAllPrenotazioni());
    model.addAttribute("prenotazione", p);
    model.addAttribute("cf", p.getCodiceFiscale());
    return "ModificaPrenotazione";
  }

  @RequestMapping(value = { "/modifica-prenotazione" }, method = RequestMethod.POST)
  public String modificaPrenotazione(ModelMap model, @Valid @ModelAttribute("prenotazione") Prenotazione prenotazione,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("prenotazione", prenotazione);
      model.addAttribute("cf", prenotazione.getCodiceFiscale());
      return "ModificaPrenotazione";
    }

    model.addAttribute("message", prenotazioneService.updatePrenotazione(prenotazione));
    model.addAttribute("Prenotazione", prenotazioneService.getById(prenotazione.getCodice()));
    return "Prenotazione";
  }

  @RequestMapping(value = { "/elimina/{codice}" }, method = RequestMethod.GET)
  public String eliminaPrenotazione(ModelMap model, @PathVariable String codice) {
    Prenotazione[] prenotazioni = prenotazioneService.getAllPrenotazioni();
    model.addAttribute("message", prenotazioneService.deletePrenotazione(codice));
    return "redirect:/prenotazioni/all";
  }

  @RequestMapping(value = { "/cerca-prenotazioneByData-page" }, method = RequestMethod.GET)
  public String cercaPrenotazioneByDataPage(ModelMap model) {
    model.addAttribute("prenotazione", new Prenotazione());
    return "CercaPrenotazioneByData";
  }

  @RequestMapping(value = { "/cerca-prenotazioneByData" }, method = RequestMethod.POST)
  public String cercaPrenotazioneByData(ModelMap model, @ModelAttribute Prenotazione prenotazione) {
    Prenotazione[] prenotazioni = prenotazioneService.getByData(prenotazione.getData());
    if (prenotazioni == null) {
      model.addAttribute("prenotazione", new Prenotazione());
      model.addAttribute("message", "Data " + prenotazione.getData() + " non trovata");
      return "CercaPrenotazioneByData";
    }
    model.addAttribute("Prenotazioni", prenotazioni);
    return "Prenotazioni";
  }
}
