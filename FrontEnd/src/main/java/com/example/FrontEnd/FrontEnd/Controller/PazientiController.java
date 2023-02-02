package com.example.FrontEnd.FrontEnd.Controller;

import com.example.FrontEnd.FrontEnd.model.*;
import com.example.FrontEnd.FrontEnd.service.IFarmaciaService;
import com.example.FrontEnd.FrontEnd.service.IMalattiaStub;
import com.example.FrontEnd.FrontEnd.service.IPazienteService;
import com.example.FrontEnd.FrontEnd.service.IPazienteStub;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping(value = { "/pazienti" })
public class PazientiController {

  @Autowired
  private IPazienteService pazienteService;
  @Autowired
  private IFarmaciaService farmaciaService;

  @Autowired
  private IMalattiaStub malattiaStub;

  @Autowired
  private IPazienteStub pazienteStub;

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

  @RequestMapping(value = { "/all" }, method = RequestMethod.GET)
  public String showPazientiPage(ModelMap model) {
    model.addAttribute("Pazienti", pazienteService.getPazienti());
    return "Pazienti";
  }

  @RequestMapping(value = { "/{cf}" }, method = RequestMethod.GET)
  public String showUtentiPage(ModelMap model, @PathVariable String cf) {
    model.addAttribute("Paziente", pazienteService.getPaziente(cf));
    return "Paziente";
  }

  @RequestMapping(value = { "/add-paziente-page/{cf}" }, method = RequestMethod.GET)
  public String insertPazientePage(ModelMap model, @PathVariable String cf) {
    model.addAttribute("farmaci", farmaciaService.getAllFarmaci());
    model.addAttribute("scheda", new SchedaPazienteForm());
    model.addAttribute("cf", cf);
    model.addAttribute("Malattie", malattiaStub.getAll());
    return "AggiungiPaziente";
  }

  @RequestMapping(value = { "/add-paziente" }, method = RequestMethod.POST)
  public String insertPaziente(ModelMap model, @ModelAttribute SchedaPazienteForm scheda) {
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

  @RequestMapping(value = "/ricerca-paziente-page", method = RequestMethod.GET)
  public String ricercaPazientePage(ModelMap model) {
    model.addAttribute("paziente", new Paziente());
    return "Ricerca";
  }

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
      if (p.getDataNascita() == "")
        p.setDataNascita(null);
      if (p.getLuogoNascita() == "")
        p.setLuogoNascita(null);

      System.out.println(p);

      model.addAttribute("pazienti", pazienteStub.findPazienti(p));
      return "PazientiTrovati";
    }
    if ((p.getNome() != "" || p.getCognome() != "" || p.getDataNascita() != "" || p.getLuogoNascita() != "")
        && (p.getNome() == "" || p.getCognome() == "")) {
      model.addAttribute("pazienti", p);
      if (p.getNome() == "")
        model.addAttribute("nome_error", "Campo Nome Obbligatorio");
      if (p.getCognome() == "")
        model.addAttribute("cognome_error", "Campo Cognome Obbligatorio");
      return "Ricerca";
    }

    model.addAttribute("pazienti", pazienteStub.findAllPazienti());
    return "PazientiTrovati";
  }

  @RequestMapping(value = { "/cerca-paziente-page" }, method = RequestMethod.GET)
  public String cercaPazientePage(ModelMap model) {
    model.addAttribute("scheda", new SchedaPaziente());
    return "CercaPaziente";
  }

  @RequestMapping(value = { "/cerca-paziente" }, method = RequestMethod.POST)
  public String cercaPaziente(ModelMap model, @Valid @ModelAttribute("scheda") SchedaPaziente scheda,
      BindingResult bindingResult) {
    SchedaPaziente s = pazienteService.getPaziente(scheda.getCodiceFiscale());
    if (bindingResult.hasErrors()) {
      model.addAttribute("scheda", scheda);
      return "CercaPaziente";
    }
    if (s == null) {
      model.addAttribute("scheda", new SchedaPaziente());
      model.addAttribute("message", "Codice fiscale " + scheda.getCodiceFiscale() + " errato");
      return "CercaPaziente";
    }
    model.addAttribute("Paziente", s);
    return "Paziente";
  }

  @RequestMapping(value = { "/modifica-paziente-page/{cf}" }, method = RequestMethod.GET)
  public String modificaPazientePage(ModelMap model, @PathVariable String cf) {
    model.addAttribute("farmaci", farmaciaService.getAllFarmaci());
    SchedaPazienteForm scheda = SchedaPazienteForm.mapToSchedaPazienteForm(pazienteService.getPaziente(cf));

    scheda.setFarmaci(new ArrayList<>());
    model.addAttribute("schedaMap", pazienteService.getPaziente(cf));
    model.addAttribute("scheda", scheda);
    model.addAttribute("Malattie", malattiaStub.getAll());
    model.addAttribute("cf", cf);
    return "ModificaPaziente";
  }

  @RequestMapping(value = { "/modifica-paziente" }, method = RequestMethod.POST)
  public String modificaPaziente(ModelMap model, @Valid @ModelAttribute("scheda") SchedaPazienteForm scheda,
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

  @GetMapping(value = { "/elimina/{cf}" })
  public String eliminaFarmaco(ModelMap model, @PathVariable String cf) {
    model.addAttribute("message", pazienteService.eliminaPaziente(cf));
    model.addAttribute("Pazienti", pazienteService.getPazienti());
    return "Pazienti";
  }
}
