package com.example.FrontEnd.FrontEnd.Controller;

import com.example.FrontEnd.FrontEnd.model.Lotto;
import com.example.FrontEnd.FrontEnd.model.OrdineRequest;
import com.example.FrontEnd.FrontEnd.model.SchedaFarmaco;
import com.example.FrontEnd.FrontEnd.service.IFarmaciaService;
import java.util.List;
import java.util.regex.Pattern;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.BindingResult;

@Controller
@RequestMapping(value = { "/farmacia" })
public class FarmaciaController {
  @Autowired
  private IFarmaciaService service;

  @GetMapping
  public String showFarmaciaHomePage(ModelMap model) {
    return "FarmaciaHome";
  }

  @GetMapping(value = { "/magazzino" })
  public String showMagazzinoPage(ModelMap model) {
    model.addAttribute("Farmaci", service.getAllFarmaci());
    return "Magazzino";
  }

  @GetMapping(value = { "/magazzino/{id}" })
  public String showFarmacoPage(ModelMap model, @PathVariable String id) {
    model.addAttribute("Farmaco", service.getFarmaco(id));
    return "Farmaco";
  }

  @GetMapping(value = { "/add-farmaco-page" })
  public String insertFarmacoPage(ModelMap model) {
    model.addAttribute("scheda", new SchedaFarmaco());
    return "AggiungiFarmaco";
  }

  @PostMapping(value = { "/add-farmaco" })
  public String insertFarmaco(ModelMap model, @Valid @ModelAttribute("scheda") SchedaFarmaco scheda,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("scheda", scheda);
      return "AggiungiFarmaco";
    }

    String msg = service.addFarmaco(scheda);
    // model.addAttribute("message", msg);
    // model.addAttribute("Farmaco", service.getFarmaco(scheda.getCodice()));

    // return "Farmaco";
    return "redirect:/farmacia/magazzino/" + scheda.getCodice();
  }

  @GetMapping(value = { "/add-lotto-page/{codice}" })
  public String insertLottoPage(ModelMap model, @PathVariable String codice) {
    model.addAttribute("codice", codice);
    model.addAttribute("lotto", new Lotto());
    return "AggiungiLotto";
  }

  @PostMapping(value = { "/add-lotto/{codice}" })
  public String insertLotto(ModelMap model, @PathVariable String codice, @Valid @ModelAttribute("lotto") Lotto lotto,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("codice", codice);
      model.addAttribute("lotto", lotto);
      return "AggiungiLotto";
    }
    String msg = service.nuovoLotto(codice, lotto);
    if (msg.indexOf('"') != -1) {
      model.addAttribute("codice", codice);
      model.addAttribute("lotto", lotto);
      model.addAttribute("msg", msg.substring(msg.indexOf('"')).replace('"', ' ').replace('|', ' '));
      return "AggiungiLotto";
    }

    model.addAttribute("message", msg);
    model.addAttribute("Farmaco", service.getFarmaco(codice));
    return "Farmaco";
  }

  @GetMapping(value = { "/modifica-farmaco-page/{codice}" })
  public String modificaFarmacoPage(ModelMap model, @PathVariable String codice, @ModelAttribute SchedaFarmaco scheda) {
    scheda = service.getFarmaco(codice);
    model.addAttribute("codice", codice);
    model.addAttribute("scheda", scheda);
    return "ModificaFarmaco";
  }

  @PostMapping(value = { "/modifica-farmaco/{codice}" })
  public String modificaFarmaco(ModelMap model, @PathVariable String codice,
      @Valid @ModelAttribute("scheda") SchedaFarmaco scheda, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("codice", codice);
      return "ModificaFarmaco";
    }

    scheda.setLotti((List<Lotto>) model.getAttribute("lotti"));
    model.addAttribute("message", service.modificaFarmaco(codice, scheda));
    model.addAttribute("Farmaco", service.getFarmaco(codice));
    return "Farmaco";
  }

  @GetMapping(value = { "/modifica-lotto-page/{codice}/{num}" })
  public String modificaLottoPage(ModelMap model, @PathVariable Integer num, @PathVariable String codice) {
    Lotto lotto = service.getLotto(codice, num);
    model.addAttribute("codice", codice);
    model.addAttribute("lotto", lotto);
    return "ModificaLotto";
  }

  @PostMapping(value = { "/modifica-lotto/{codice}" })
  public String modificaLotto(ModelMap model, @PathVariable String codice, @Valid @ModelAttribute("lotto") Lotto lotto,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("codice", codice);
      model.addAttribute("lotto", lotto);
      return "ModificaLotto";
    }
    model.addAttribute("message", service.modificaLotto(codice, lotto));
    model.addAttribute("Farmaco", service.getFarmaco(codice));
    return "Farmaco";
  }

  @GetMapping(value = { "/nuovo-ordine-page" })
  public String nuovoOrdinePage(ModelMap model) {
    model.addAttribute("ordine", new OrdineRequest());
    return "NuovoOrdine";
  }

  @PostMapping(value = { "/nuovo-ordine" })
  public String nuovoOrdine(ModelMap model, @Valid @ModelAttribute("ordine") OrdineRequest ordine,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("ordine", ordine);
      return "NuovoOrdine";
    }
    String msg = service.nuovoOrdine(ordine);
    if (msg.indexOf('"') != -1) {
      model.addAttribute("msg", msg.substring(msg.indexOf('"')).replace('"', ' '));
      return "NuovoOrdine";

    }
    // model.addAttribute("message", service.nuovoOrdine(ordine));
    // model.addAttribute("ordine", ordine);
    // return "NuovoOrdine";
    return "redirect:/farmacia/ordini-page";
  }

  @GetMapping(value = { "/ordini-page" })
  public String ordiniPage(ModelMap model) {
    model.addAttribute("ordini", service.getAllOrdini());
    return "Ordini";
  }

  @GetMapping(value = { "/elimina/{codice}" })
  public String eliminaFarmaco(ModelMap model, @PathVariable String codice) {
    model.addAttribute("message", service.eliminaFarmaco(codice));
    model.addAttribute("Farmaci", service.getAllFarmaci());
    return "Magazzino";
  }
}
