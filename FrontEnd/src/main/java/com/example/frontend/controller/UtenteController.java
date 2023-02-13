package com.example.frontend.controller;

import com.example.frontend.model.Credenziali;
import com.example.frontend.model.Utente;
import com.example.frontend.service.InterfaceUtenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


/**
 * <p>Classe controller che gestisce tutte le chiamate dal browser riferite alle prenotazioni.</p>
 *
 * @version 1.0
 */
@Controller
@SessionAttributes("ruolo")
public class UtenteController {
  /**
   * <p>Riferimento all'interfaccia utente service.</p>
   */
  @Autowired
  private InterfaceUtenteService service;

  /**
   * <p>Metodo che inizializza le credenziali.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @return nome della pagina jsp Login se non è stato effettuato il login,
   *      altrimenti nome della pagina jsp Index
   */
  @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
  public String showIndexPage(ModelMap model) {
    if (model.getAttribute("ruolo") == null) {
      model.addAttribute("credenziali", new Credenziali());
      return "Login";
    }
    return "Index";
  }

  /**
   * <p>Metodo che cattura l'evento di click del logo.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @return se l'attributo di sessione ruolo è vuoto restituisce il nome della pagina jsp Login,
   *        altrimenti nome della pagina jsp Index
   */
  @RequestMapping(value = {"/logo"}, method = RequestMethod.GET)
  public String clickLogo(ModelMap model) {
    if (model.getAttribute("ruolo") == "") {
      model.addAttribute("credenziali", new Credenziali());
      return "Login";
    }
    return "Index";
  }

  /**
   * <p>Metodo che inizializza la pagina di login.</p>
   *
   * @param model       utilizzato per comunicare con le jsp
   * @param credenziali credenziali dell'utente
   * @return nome della pagina jsp Login
   */
  @RequestMapping(value = {"/utente/login"}, method = RequestMethod.GET)
  public String showLoginPage(ModelMap model, @ModelAttribute Credenziali credenziali) {
    model.addAttribute("message", null);
    model.addAttribute("credenziali", credenziali);
    return "Login";
  }

  /**
   * <p>Metodo che cattura l'evento di login.</p>
   *
   * @param credenziali credenziali dell'utente
   * @param model       utilizzato per comunicare con le jsp
   * @return nome della pagin jsp Index, altrimenti nome della pagina Login
   */
  @RequestMapping(value = {"/utente/verificaCredenziali"}, method = RequestMethod.POST)
  public String login(@ModelAttribute Credenziali credenziali, ModelMap model) {
    String response = service.verificaCrendenziali(credenziali);
    if (response.equals("Login effettuato con successo!")) {
      model.addAttribute("message", response);
      model.addAttribute("ruolo", service.getRuoloByUser(credenziali.getUser()));
      return "Index";
    } else {
      model.addAttribute("message", "Credenziali non valide!");
      return "Login";
    }
  }

  /**
   * <p>Metodo che cattura l'evento di logout.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @return nome della pagina jsp Login
   */
  @RequestMapping(value = "/utente/logout", method = RequestMethod.GET)
  public String logout(ModelMap model) {
    model.addAttribute("ruolo", "");
    model.addAttribute("credenziali", new Credenziali());
    return "Login";
  }

  /**
   * <p>Metodo che inizializza la pagina di tutti gli utenti.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @return nome della pagina jsp Utenti
   */
  @RequestMapping(value = {"/utenti"}, method = RequestMethod.GET)
  public String showUtentiPage(ModelMap model) {
    Utente[] u = service.getUtenti();
    model.addAttribute("Utenti", u);
    return "Utenti";
  }

  /**
   * <p>Metodo che inizializza la pagina di un utente.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param id id dell'utente
   * @return nome della pagina jsp Utente
   */
  @RequestMapping(value = {"/utenti/{id}"}, method = RequestMethod.GET)
  public String showUtentePage(ModelMap model, @PathVariable Integer id) {
    Utente u = service.getUtente(id);
    model.addAttribute("Utente", u);
    return "Utente";
  }

  /**
   * <p>Metodo che inizializza la pagina di registrazione di un nuovo utente.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @return nome della pagina jsp AggiungiUtente
   */
  @RequestMapping(value = {"/utenti/add-utente-page"}, method = RequestMethod.GET)
  public String nuovoUtentePage(ModelMap model) {
    model.addAttribute("utente", new Utente());
    return "AggiungiUtente";
  }

  /**
   * <p>Metodo che catture l'evento di aggiungi paziente.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param utente utente da inserire
   */
  @RequestMapping(value = {"/utenti/add-utente"}, method = RequestMethod.POST)
  public String nuovoUtente(
          ModelMap model, @Valid @ModelAttribute Utente utente, BindingResult result) {
    if (result.hasErrors()) {
      model.addAttribute("utennte", utente);
      return "AggiungiUtente";
    }
    String hash = BCrypt.hashpw(utente.getCredenziali().getPass(), BCrypt.gensalt());
    utente.getCredenziali().setPass(hash);
    String response = service.aggiungiUtente(utente);
    if (!response.contains("Utente salvato")) {
      model.addAttribute("message",
              response.substring(response.indexOf('"') + 1, response.length() - 1));
      model.addAttribute("utennte", utente);
      return "AggiungiUtente";
    }
    return "redirect:/utenti/" + utente.getId();
  }

  /**
   * <p>Metodo che inizializza l'evento di modifica utente.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   */
  @RequestMapping(value = {"/utenti/modifica-utente-page/{id}"}, method = RequestMethod.GET)
  public String modificaUtentePage(ModelMap model, @PathVariable Integer id) {
    Utente u = service.getUtente(id);
    model.addAttribute("utente", u);
    return "ModificaUtente";
  }

  /**
   * <p>Metodo che cattura l'evento di modifica dell'utente.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param utente utente da modificare
   */
  @RequestMapping(value = {"/utenti/modifica-utente"}, method = RequestMethod.POST)
  public String modificaUtente(
          ModelMap model, @Valid @ModelAttribute Utente utente, BindingResult result) {
    if (result.hasErrors()) {
      model.addAttribute("utente", utente);
      return "ModificaUtente";
    }
    String response = service.modificaUtente(utente.getId(), utente);
    if (!response.contains("modificato correttamente")) {
      model.addAttribute("utente", utente);
      return "ModificaUtente";
    }
    return "redirect:/utenti/" + utente.getId();
  }

  /**
   * <p>Metodo che inizializza la l'evento di eliminzione utente.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param id id utente da eliminare
   */
  @RequestMapping(value = {"/utenti/elimina/{id}"}, method = RequestMethod.GET)
  public String eliminaUtente(ModelMap model, @PathVariable Integer id) {
    model.addAttribute("message", service.elimina(id));
    model.addAttribute("Utenti", service.getUtenti());
    return "Utenti";
  }
}
