package com.example.FrontEnd.FrontEnd.Controller;

import com.example.FrontEnd.FrontEnd.model.Lotto;
import com.example.FrontEnd.FrontEnd.model.OrdineRequest;
import com.example.FrontEnd.FrontEnd.model.SchedaFarmaco;
import com.example.FrontEnd.FrontEnd.service.IFarmaciaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/farmacia"})
public class FarmaciaController {
  @Autowired
  private IFarmaciaService service;

  @GetMapping
  public String showFarmaciaHomePage(ModelMap model) {
    return "FarmaciaHome";
  }

  @GetMapping(value = {"/magazzino"})
  public String showMagazzinoPage(ModelMap model) {
    model.addAttribute("Farmaci", service.getAllFarmaci());
    return "Magazzino";
  }

  @GetMapping(value = {"/magazzino/{id}"})
  public String showFarmacoPage(ModelMap model, @PathVariable String id) {
    model.addAttribute("Farmaco", service.getFarmaco(id));
    return "Farmaco";
  }

  @GetMapping(value = {"/add-farmaco-page"})
  public String insertFarmacoPage(ModelMap model) {
    model.addAttribute("scheda", new SchedaFarmaco());
    return "AggiungiFarmaco";
  }

  @PostMapping(value = {"/add-farmaco"})
  public String insertFarmaco(ModelMap model, @ModelAttribute SchedaFarmaco scheda) {
    String msg = service.addFarmaco(scheda);
    if(msg.contains("400")) {
      model.addAttribute("message", msg);
      model.addAttribute("scheda", scheda);
      return "AggiungiFarmaco";
    }
    model.addAttribute("message", msg);
    model.addAttribute("Farmaco", service.getFarmaco(scheda.getCodice()));
    return "Farmaco";
  }

  @GetMapping(value = {"/add-lotto-page/{codice}"})
  public String insertLottoPage(ModelMap model, @PathVariable String codice) {
    model.addAttribute("codice", codice);
    model.addAttribute("lotto", new Lotto());
    return "AggiungiLotto";
  }

  @PostMapping(value = {"/add-lotto/{codice}"})
  public String insertLotto(ModelMap model, @ModelAttribute Lotto lotto, @PathVariable String codice) {
    model.addAttribute("message", service.nuovoLotto(codice, lotto));
    model.addAttribute("Farmaco", service.getFarmaco(codice));
    return "Farmaco";
  }

  @GetMapping(value = {"/modifica-farmaco-page/{codice}"})
  public String modificaFarmacoPage(ModelMap model, @ModelAttribute SchedaFarmaco scheda, @PathVariable String codice) {
    scheda = service.getFarmaco(codice);
    model.addAttribute("codice", codice);
    model.addAttribute("scheda", scheda);
    return "ModificaFarmaco";
  }

  @PostMapping(value = {"/modifica-farmaco/{codice}"})
  public String modificaFarmaco(@ModelAttribute SchedaFarmaco scheda, ModelMap model, @PathVariable String codice) {
    scheda.setLotti((List<Lotto>) model.getAttribute("lotti"));
    model.addAttribute("message", service.modificaFarmaco(codice, scheda));
    model.addAttribute("Farmaco", service.getFarmaco(codice));
    return "Farmaco";
  }

  @GetMapping(value = {"/modifica-lotto-page/{codice}/{num}"})
  public String modificaLottoPage(ModelMap model, @PathVariable Integer num, @PathVariable String codice) {
    Lotto lotto = service.getLotto(codice, num);
    model.addAttribute("codice", codice);
    model.addAttribute("lotto", lotto);
    return "ModificaLotto";
  }

  @PostMapping(value = {"/modifica-lotto/{codice}"})
  public String modificaLotto(ModelMap model, @ModelAttribute Lotto lotto, @PathVariable String codice) {
    model.addAttribute("message", service.modificaLotto(codice, lotto));
    model.addAttribute("Farmaco", service.getFarmaco(codice));
    return "Farmaco";
  }

  @GetMapping(value = {"/nuovo-ordine-page"})
  public String nuovoOrdinePage(ModelMap model) {
    model.addAttribute("ordine", new OrdineRequest());
    return "NuovoOrdine";
  }

  @PostMapping(value = {"/nuovo-ordine"})
  public String nuovoOrdine(@ModelAttribute OrdineRequest ordine, ModelMap model) {
    model.addAttribute("message", service.nuovoOrdine(ordine));
    model.addAttribute("ordine", new OrdineRequest());
    return "NuovoOrdine";
  }

  @GetMapping(value = {"/ordini-page"})
  public String ordiniPage(ModelMap model) {
    model.addAttribute("ordini", service.getAllOrdini());
    return "Ordini";
  }

  @GetMapping(value = {"/elimina/{codice}"})
  public String eliminaFarmaco(ModelMap model, @PathVariable String codice) {
    model.addAttribute("message", service.eliminaFarmaco(codice));
    model.addAttribute("Farmaci", service.getAllFarmaci());
    return "Magazzino";
  }
}
