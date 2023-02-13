package com.example.frontend.controller;

import com.example.frontend.model.Prenotazione;
import com.example.frontend.model.SchedaPaziente;
import com.example.frontend.service.InterfacePazienteService;
import com.example.frontend.service.InterfacePrenotazioneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>Classe controller che gestisce tutte le chiamate dal browser riferite alle prenotazioni.</p>
 *
 * @version 1.0
 */
@Controller
@RequestMapping(value = { "/prenotazioni" })
public class PrenotazioneController {
  /**
   * <p>Riferimento all'interfaccia prenotazione service.</p>
   */
  @Autowired
  private InterfacePrenotazioneService prenotazioneService;

  /**
   * <p>Riferimento all'interfaccia paziente service.</p>
   */
  @Autowired
  private InterfacePazienteService pazienteService;

  /**
   * <p>Metodo che inizilizza la pagina home delle prenotazioni.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @return nome della pagina jsp PrenotazioniHome
   */
  @RequestMapping(value = { "" }, method = RequestMethod.GET)
  public String showPrenotazioniHomePage(ModelMap model) {
    return "PrenotazioniHome";
  }

  /**
   * <p>Metodo che inizilizza la pagina di tutte le prenotazioni.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @return nome della pagina jsp Prenotazione
   */
  @RequestMapping(value = { "/all" }, method = RequestMethod.GET)
  public String showPrenotazioniiPage(ModelMap model) {
    Prenotazione[] prenotazioni = prenotazioneService.getAllPrenotazioni();
    model.addAttribute("Prenotazioni", prenotazioni);
    return "Prenotazioni";
  }

  /**
   * <p>Metodo che inizializza la pagina della prenotazione.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param codice codice della prenotazione da visualizzare
   * @return nome della pagina jsp Prenotazione
   */
  @RequestMapping(value = { "/{codice}" }, method = RequestMethod.GET)
  public String showPrenotazionePage(ModelMap model, @PathVariable String codice) {
    Prenotazione prenotazione = prenotazioneService.getById(codice);
    model.addAttribute("Prenotazione", prenotazione);
    return "Prenotazione";
  }

  /**
   * <p>Metodo che inizilizza la pagina di inserimento di una nuova prenotazione.</p>
   *
   * @param prenotazione prenotazione inizializzata
   * @param model utilizzato per comunicare con le jsp
   * @return nome della pagina jsp AggiungiPrenotazione
   */
  @RequestMapping(value = { "/add-prenotazione-page" }, method = RequestMethod.GET)
  public String insertPrenotazionePage(@ModelAttribute Prenotazione prenotazione, ModelMap model) {
    return "AggiungiPrenotazione";
  }

  /**
   * <p>Metodo che cattura l'evento di inserimento della prenotazione.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param prenotazione prenotazione da inserire
   * @param bindingResult contiene gli errori del form
   * @return redirect alla pagina della prenotazione se l'inserimento va a buon fine,
   *      altrimenti nome della pagina jsp AggiungiPrenotazione
   */
  @RequestMapping(value = { "/add-prenotazione" }, method = RequestMethod.POST)
  public String insertPrenotazione(ModelMap model,
                                   @Valid @ModelAttribute("prenotazione") Prenotazione prenotazione,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
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
    Prenotazione[] prenotazioni = prenotazioneService.getAllPrenotazioni();
    return "redirect:/prenotazioni/" + prenotazioni[prenotazioni.length - 1].getCodice();
  }

  /**
   * <p>Metodo che inizializza la pagina di modifica della prenotazione.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param codice codice della prenotazione da modificare
   * @return nome della pagina jsp ModificaPrenotazione
   */
  @RequestMapping(value = { "/modifica-prenotazione-page/{codice}" }, method = RequestMethod.GET)
  public String modificaPrenotazionePage(ModelMap model, @PathVariable String codice) {
    Prenotazione p = prenotazioneService.getById(codice);
    // model.addAttribute("prenotazioni", prenotazioneService.getAllPrenotazioni());
    model.addAttribute("prenotazione", p);
    model.addAttribute("cf", p.getCodiceFiscale());
    return "ModificaPrenotazione";
  }

  /**
   * <p>Metodo che cattura l'evento di modifica della prenotazione.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param prenotazione prenotazione con le modifiche
   * @param bindingResult contiene gli errori del form
   * @return nome della pagina jsp Prenotazione se la modifica va buon fine,
   *      altrimenti nome della pagina jsp ModificaPrenotazione
   */
  @RequestMapping(value = { "/modifica-prenotazione" }, method = RequestMethod.POST)
  public String modificaPrenotazione(
          ModelMap model, @Valid @ModelAttribute("prenotazione") Prenotazione prenotazione,
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

  /**
   * <p>Metodo che cattura l'evento di eliminazione della prenotazione.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param codice codice della prenotazione da eliminare
   * @return redirect alla pagina di tutte le prenotazioni
   */
  @RequestMapping(value = { "/elimina/{codice}" }, method = RequestMethod.GET)
  public String eliminaPrenotazione(ModelMap model, @PathVariable String codice) {
    Prenotazione[] prenotazioni = prenotazioneService.getAllPrenotazioni();
    model.addAttribute("message", prenotazioneService.deletePrenotazione(codice));
    return "redirect:/prenotazioni/all";
  }

  /**
   * <p>Metodo che inizializza la pagina di ricerca di prenotazione per data.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @return nome della pagina jsp CercaPrenotazioneByData
   */
  @RequestMapping(value = { "/cerca-prenotazioneByData-page" }, method = RequestMethod.GET)
  public String cercaPrenotazioneByDataPage(ModelMap model) {
    model.addAttribute("prenotazione", new Prenotazione());
    return "CercaPrenotazioneByData";
  }

  /**
   * <p>Metodo che cattura l'evento di ricerca della prenotazione per data.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param prenotazione prenotazione trovata
   * @return nome della pagina jsp Prenotazioni se la ricerca va a buon fine,
   *      altrimenti nome della pagina jsp CercaPrenotazioneByData
   */
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

  /**
   * <p>Metodo che cattura l'evento di conferma di prenotazione.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param codice codice della prenotazione da confermare
   * @return nome della pagina jsp Prenotazioni
   */
  @RequestMapping(value = {"/conferma-prenotazione/{codice}"}, method = RequestMethod.GET)
  public String confermaPrenotazione(ModelMap model, @PathVariable String codice) {
    Prenotazione p = prenotazioneService.getById(codice);
    SchedaPaziente s = pazienteService.getPaziente(p.getCodiceFiscale());
    if (prenotazioneService.confermaPrenotazione(s)) {
      model.addAttribute("message", "Prenotazione confermata");
      p.setConfermata(true);
      System.out.println(prenotazioneService.updatePrenotazione(p));
    } else {
      model.addAttribute("message",
              "Impossibile confermare la prenotazione. Quantità farmaco non disponibile");
    }
    model.addAttribute("Prenotazioni", prenotazioneService.getAllPrenotazioni());
    return "Prenotazioni";
  }
}
