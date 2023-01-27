package com.example.FrontEnd.FrontEnd.Controller;

import com.example.FrontEnd.FrontEnd.model.Lotto;
import com.example.FrontEnd.FrontEnd.model.OrdineRequest;
import com.example.FrontEnd.FrontEnd.model.SchedaFarmaco;
import com.example.FrontEnd.FrontEnd.service.IFarmacia_Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/farmacia"}, method = RequestMethod.GET)
public class Farmacia_Controller {

  @Autowired
  private IFarmacia_Service service;

  @RequestMapping(value = {""}, method = RequestMethod.GET)
  public String showFarmaciaHomePage(ModelMap model) {
    return "Farmacia_Home";
  }

  @RequestMapping(value = {"/magazzino"}, method = RequestMethod.GET)
  public String showMagazzinoPage(ModelMap model) {
    SchedaFarmaco[] s = service.getAllFarmaci();
    model.addAttribute("Farmaci", s);

    return "Magazzino";
  }

  @RequestMapping(value = {"/magazzino/{id}"}, method = RequestMethod.GET)
  public String showFarmacoPage(ModelMap model, @PathVariable String id) {
    SchedaFarmaco s = service.getFarmaco(id);
    model.addAttribute("Farmaco", s);

    return "Farmaco";
  }

  @RequestMapping(value = {"/add-farmaco-page"}, method = RequestMethod.GET)
  public String insertFarmacoPage(@ModelAttribute SchedaFarmaco scheda, ModelMap model) {
    model.addAttribute("scheda", scheda);
    return "AggiungiFarmaco";
  }

  @RequestMapping(value = {"/add-farmaco"}, method = RequestMethod.POST)
  public String insertFarmaco(@ModelAttribute SchedaFarmaco scheda, ModelMap model) {
    System.out.println(scheda);
    model.addAttribute("message", service.addFarmaco(scheda));
    model.addAttribute("scheda", new SchedaFarmaco());
    return "AggiungiFarmaco";
  }

  @RequestMapping(value = {"/add-lotto-page/{codice}"}, method = RequestMethod.GET)
  public String insertLottoPage(@ModelAttribute Lotto lotto, ModelMap model, @PathVariable String codice) {
    model.addAttribute("codice", codice);
    model.addAttribute("lotto", lotto);
    return "AggiungiLotto";
  }

  @RequestMapping(value = {"/add-lotto/{codice}"}, method = RequestMethod.POST)
  public String insertLotto(@ModelAttribute Lotto lotto, ModelMap model, @PathVariable String codice) {
    System.out.println(lotto);
    System.out.println(codice);
    model.addAttribute("message", service.nuovoLotto(codice, lotto));
    model.addAttribute("Farmaco", service.getFarmaco(codice));
    return "Farmaco";
  }

  @RequestMapping(value = {"/modifica-farmaco-page/{codice}"}, method = RequestMethod.GET)
  public String modificaFarmacoPage(@ModelAttribute SchedaFarmaco scheda, @PathVariable String codice, ModelMap model) {
    scheda = service.getFarmaco(codice);
    System.out.println(scheda);
    model.addAttribute("codice", codice);
    model.addAttribute("scheda", scheda);
    return "ModificaFarmaco";
  }

  @RequestMapping(value = {"/modifica-farmaco/{codice}"}, method = RequestMethod.POST)
  public String modificaFarmaco(@ModelAttribute SchedaFarmaco scheda, ModelMap model, @PathVariable String codice) {
    System.out.println(scheda);
    scheda.setLotti((List<Lotto>) model.getAttribute("lotti"));
    model.addAttribute("message", service.modificaFarmaco(codice, scheda));
    model.addAttribute("Farmaco", service.getFarmaco(codice));
    return "Farmaco";
  }

  @RequestMapping(value = {"/modifica-lotto-page/{codice}/{num}"}, method = RequestMethod.GET)
  public String modificaLottoPage(@PathVariable Integer num, @PathVariable String codice, ModelMap model) {
    Lotto lotto = service.getLotto(codice, num);
    System.out.println(lotto);
    model.addAttribute("codice", codice);
    model.addAttribute("lotto", lotto);
    return "ModificaLotto";
  }

  @RequestMapping(value = {"/modifica-lotto/{codice}"}, method = RequestMethod.POST)
  public String modificaLotto(@ModelAttribute Lotto lotto, @PathVariable String codice, ModelMap model) {
    System.out.println(lotto);
    model.addAttribute("message", service.modificaLotto(codice, lotto));
    model.addAttribute("Farmaco", service.getFarmaco(codice));
    return "Farmaco";
  }

  @RequestMapping(value = {"/nuovo-ordine-page"}, method = RequestMethod.GET)
  public String nuovoOrdinePage(@ModelAttribute OrdineRequest ordine, ModelMap model) {
    model.addAttribute("ordine", ordine);
    return "NuovoOrdine";
  }

  @RequestMapping(value = {"/nuovo-ordine"}, method = RequestMethod.POST)
  public String nuovoOrdine(@ModelAttribute OrdineRequest ordine, ModelMap model) {
    model.addAttribute("message", service.nuovoOrdine(ordine));
    model.addAttribute("ordine", new OrdineRequest());
    return "NuovoOrdine";
  }

  @RequestMapping(value = {"/ordini-page"}, method = RequestMethod.GET)
  public String ordiniPage(ModelMap model) {
    model.addAttribute("ordini", service.getAllOrdini());
    return "Ordini";
  }

  @RequestMapping(value = {"/elimina/{codice}"}, method = RequestMethod.DELETE)
  public String eliminaFarmaco(ModelMap model, @PathVariable String codice) {
    model.addAttribute("message", service.eliminaFarmaco(codice));
    model.addAttribute("Farmaci", service.getAllFarmaci());
    return "Magazzino";
  }
}
