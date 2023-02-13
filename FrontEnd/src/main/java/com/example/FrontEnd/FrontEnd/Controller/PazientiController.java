package com.example.FrontEnd.FrontEnd.Controller;

import com.example.FrontEnd.FrontEnd.model.Paziente;
import com.example.FrontEnd.FrontEnd.model.SchedaPaziente;
import com.example.FrontEnd.FrontEnd.model.SchedaPazienteForm;
import com.example.FrontEnd.FrontEnd.service.IFarmaciaService;
import com.example.FrontEnd.FrontEnd.service.IMalattiaStub;
import com.example.FrontEnd.FrontEnd.service.IPazienteService;
import com.example.FrontEnd.FrontEnd.service.IPazienteStub;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/**
 * <p>Classe controller che gestisce tutte le chiamate dal browser riferite ai pazienti.</p>
 *
 * @version 1.0
 */
@Controller
@RequestMapping(value = { "/pazienti" })
public class PazientiController {
  /**
   * <p>Riferimento all'interfaccia paziente service.</p>
   */
  @Autowired
  private IPazienteService pazienteService;

  /**
   * <p>Riferimento all'interfaccia farmacia service.</p>
   */
  @Autowired
  private IFarmaciaService farmaciaService;

  /**
   * <p>Riferimento all'interfaccia malattia stub.</p>
   */
  @Autowired
  private IMalattiaStub malattiaStub;

  /**
   * <p>Riferimento all'interfaccia paziente stub.</p>
   */
  @Autowired
  private IPazienteStub pazienteStub;

  /**
   * <p>Metodo che mostra la pagina home della sezione pazienti.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @return nome della pagina jsp PazientiHome
   */
  @RequestMapping(value = { "" }, method = RequestMethod.GET)
  public String showPazientiHomePage(ModelMap model) {
    return "PazientiHome";
  }

  // prova ricerca
  @RequestMapping(value = { "/ricerca" }, method = RequestMethod.GET)
  public String showRicercaPage(ModelMap model) {
    model.addAttribute("scheda", new Paziente());
    return "Ricerca";
  }

  /**
   * <p>Metodo che inizilizza e mostra tutte le schede dei pazienti.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @return nome della pagina jsp Pazienti
   */
  @RequestMapping(value = { "/all" }, method = RequestMethod.GET)
  public String showPazientiPage(ModelMap model) {
    model.addAttribute("Pazienti", pazienteService.getPazienti());
    return "Pazienti";
  }

  /**
   * <p>Metodo che inizializza e mostra la pagina della scheda paziente.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param cf codice fiscale del paziente da mostrare
   * @return nome della pagina jsp Paziente
   */
  @RequestMapping(value = { "/{cf}" }, method = RequestMethod.GET)
  public String showPazientePage(ModelMap model, @PathVariable String cf) {
    model.addAttribute("Paziente", pazienteService.getPaziente(cf));
    return "Paziente";
  }

  /**
   * <p>Metodo che inizializza la pagina di inserimento della scheda paziente.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param cf codice fiscale del paziente
   * @return nome della pagina jsp AggiungiPaziente
   */
  @RequestMapping(value = { "/add-paziente-page/{cf}" }, method = RequestMethod.GET)
  public String insertPazientePage(ModelMap model, @PathVariable String cf) {
    model.addAttribute("farmaci", farmaciaService.getAllFarmaci());
    model.addAttribute("scheda", new SchedaPazienteForm());
    model.addAttribute("cf", cf);
    model.addAttribute("nome", pazienteStub.findByCf(cf).getNome());
    model.addAttribute("cognome", pazienteStub.findByCf(cf).getCognome());
    model.addAttribute("Malattie", malattiaStub.getAll());
    return "AggiungiPaziente";
  }

  /**
   * <p>Metodo che cattura l'evento di inserimento della scheda paziente.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param scheda scheda del paziente
   * @return nome della pagina jsp Paziente se l'inserimento va a buon fine,
   *      altrimenti il nome della pagina AggiungiPaziente
   */
  @RequestMapping(value = { "/add-paziente" }, method = RequestMethod.POST)
  public String insertPaziente(ModelMap model, @ModelAttribute SchedaPazienteForm scheda) {
    System.out.println(scheda);
    SchedaPaziente s = scheda.mapToSchedaPaziente();
    String msg = pazienteService.addPaziente(s);
    if (msg.contains("400")) {
      model.addAttribute("Malattie", malattiaStub.getAll());
      model.addAttribute("farmaci", farmaciaService.getAllFarmaci());
      scheda.setFarmaci(new ArrayList<>());
      model.addAttribute("scheda", scheda);
      model.addAttribute("message", msg);
      return "AggiungiPaziente";
    }
    model.addAttribute("Paziente", pazienteService.getPaziente(s.getCodiceFiscale()));
    return "Paziente";
  }

  /**
   * <p>Metodo che inizilizza la pagina di ricerca per i pazienti presenti sullo stub.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @return nome della pagina jsp Ricerca
   */
  @RequestMapping(value = "/ricerca-paziente-page", method = RequestMethod.GET)
  public String ricercaPazientePage(ModelMap model) {
    model.addAttribute("paziente", new Paziente());
    return "Ricerca";
  }

  /**
   * <p>Metodo che cattura l'evento di ricerca del paziente.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param p paziente da cercare sullo stub
   * @param bindingResult contiene gli errori del form
   * @return nome della pagina jsp PazientiTrovati se la ricerca va a buon fine,
   *      altrimenti nome della pagina jsp Ricerca
   */
  @RequestMapping(value = "/ricerca-paziente", method = RequestMethod.POST)
  public String ricercaPaziente(ModelMap model, @Valid @ModelAttribute("paziente") Paziente p,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("paziente", p);
      return "Ricerca";
    }

    if (p.getCodiceFiscale() != "") {
      model.addAttribute("paziente", new Paziente());
      model.addAttribute("pazienti", pazienteStub.findPazienti(p));
      return "PazientiTrovati";
    }

    if (p.getNome() != "" && p.getCognome() != "") {
      p.setCodiceFiscale("");
      if (p.getDataNascita() == "") {
        p.setDataNascita(null);
      }
      if (p.getLuogoNascita() == "") {
        p.setLuogoNascita(null);
      }
      System.out.println(p);

      model.addAttribute("pazienti", pazienteStub.findPazienti(p));
      return "PazientiTrovati";
    }
    if ((p.getNome() != "" || p.getCognome() != ""
            || p.getDataNascita() != "" || p.getLuogoNascita() != "")
        && (p.getNome() == "" || p.getCognome() == "")) {
      model.addAttribute("pazienti", p);
      if (p.getNome() == "") {
        model.addAttribute("nome_error", "Campo Nome Obbligatorio");
      }
      if (p.getCognome() == "") {
        model.addAttribute("cognome_error", "Campo Cognome Obbligatorio");
      }
      return "Ricerca";
    }

    model.addAttribute("pazienti", pazienteStub.findAllPazienti());
    return "PazientiTrovati";
  }

  /**
   * <p>Metodo che inizializza la pagina per cercare la scheda paziente all'interno del sistema.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @return nome della pagina jsp CercaPaziente
   */
  @RequestMapping(value = { "/cerca-pazienti-page" }, method = RequestMethod.GET)
  public String cercaPazientePage(ModelMap model) {
    model.addAttribute("farmaci", farmaciaService.getAllFarmaci());
    model.addAttribute("scheda", new SchedaPazienteForm());
    return "CercaSchedaPaziente";
  }

  /**
   * <p>Metodo che cattura l'evento per cercare la scheda paziente.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param scheda scheda del paziente cercato
   * @param bindingResult contiene gli errori del form
   * @return nome della pagina jsp Paziente se la ricerca va a buon fine,
   *      altrimenti nome della pagina jsp CercaPaziente
   */
  @RequestMapping(value = { "/cerca-pazienti" }, method = RequestMethod.POST)
  public String cercaPaziente(ModelMap model,
                              @Valid @ModelAttribute("scheda") SchedaPazienteForm scheda,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      scheda.setFarmaci(new ArrayList<>());
      model.addAttribute("farmaci", farmaciaService.getAllFarmaci());
      model.addAttribute("scheda", scheda);
      return "CercaSchedaPaziente";
    }
    SchedaPaziente filtri = scheda.mapToSchedaPaziente();
    System.out.println(filtri);
    SchedaPaziente[] pazienti = pazienteService.getPazientiByFiltri(filtri);
    /*for (SchedaPaziente p: pazienti) {
      System.out.println(p);
    }*/
    model.addAttribute("Pazienti", pazienti);
    return "Pazienti";
  }

  /**
   * <p>Metodo che inizilizza la pagina di modifica della scheda paziente.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param cf codice fiscale della scheda paziente da modificare
   * @return nome della pagina jsp ModificaPaziente
   */
  @RequestMapping(value = { "/modifica-paziente-page/{cf}" }, method = RequestMethod.GET)
  public String modificaPazientePage(ModelMap model, @PathVariable String cf) {
    model.addAttribute("farmaci", farmaciaService.getAllFarmaci());
    SchedaPazienteForm scheda =
            SchedaPazienteForm.mapToSchedaPazienteForm(pazienteService.getPaziente(cf));

    scheda.setFarmaci(new ArrayList<>());
    model.addAttribute("schedaMap", pazienteService.getPaziente(cf));
    model.addAttribute("scheda", scheda);
    model.addAttribute("Malattie", malattiaStub.getAll());
    model.addAttribute("cf", cf);
    return "ModificaPaziente";
  }

  /**
   * <p>Metodo che cattura l'evento per della modifica della scheda paziente.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param scheda scheda paziete con le modifiche
   * @param bindingResult contiene gli errori del form
   * @return nome della pagina jsp Paziente se le modifiche vanno a buon fine,
   *      altrimenti nome della pagina jsp ModificaPaziente
   */
  @RequestMapping(value = { "/modifica-paziente" }, method = RequestMethod.POST)
  public String modificaPaziente(ModelMap model,
                                 @Valid @ModelAttribute("scheda") SchedaPazienteForm scheda,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("scheda", scheda);
      return "ModificaPaziente";
    }
    SchedaPaziente s;
    try {
      s = scheda.mapToSchedaPaziente();
    } catch (Exception e) {
      model.addAttribute("farmaci", farmaciaService.getAllFarmaci());
      model.addAttribute("schedaMap", pazienteService.getPaziente(scheda.getCodiceFiscale()));
      model.addAttribute("scheda", scheda);
      model.addAttribute("Malattie", malattiaStub.getAll());
      model.addAttribute("cf", scheda.getCodiceFiscale());
      model.addAttribute("error", "Quantita obbligatoria per farmaci selezionati");
      return "ModificaPaziente";
    }

    model.addAttribute("message", pazienteService.modificaPaziente(s));
    model.addAttribute("Paziente", pazienteService.getPaziente(scheda.getCodiceFiscale()));
    return "Paziente";
  }

  /**
   * <p>Metodo che cattura l'evento per eliminare la scheda paziente.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param cf codice fiscale della scheda da eliminare
   * @return nome della pagina jsp Pazienti
   */
  @GetMapping(value = { "/elimina/{cf}" })
  public String eliminaFarmaco(ModelMap model, @PathVariable String cf) {
    model.addAttribute("message", pazienteService.eliminaPaziente(cf));
    model.addAttribute("Pazienti", pazienteService.getPazienti());
    return "Pazienti";
  }

  /**
   * <p>Metodo che mostra la pagina dei farmaci di un paziente.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param cf codice fiscale della scheda paziente
   * @return nome della pagina jsp Farmaci
   */
  @RequestMapping(value = { "/getFarmaci/{cf}" }, method = RequestMethod.GET)
  public String showFarmaciPage(ModelMap model, @PathVariable String cf) {
    model.addAttribute("farmaci", pazienteService.getFarmaci(cf));
    return "Farmaci";
  }
}
