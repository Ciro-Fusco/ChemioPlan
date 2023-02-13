package com.example.frontend.controller;

import com.example.frontend.model.Lotto;
import com.example.frontend.model.OrdineRequest;
import com.example.frontend.model.SchedaFarmaco;
import com.example.frontend.service.InterfaceFarmaciaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * <p>Classe controller che gestisce tutte le chiamate dal browser riferite alla farmacia.</p>
 *
 * @version 1.0
 */
@Controller
@RequestMapping(value = { "/farmacia" })
public class FarmaciaController {
  /**
   * <p>Riferimento all'interfaccia service di farmacia.</p>
   */
  @Autowired
  private InterfaceFarmaciaService service;

  /**
   * <p>Metodo che mostra la pagina FarmaciaHome.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @return nome della pagina jsp FarmaciaHome
   */
  @GetMapping
  public String showFarmaciaHomePage(ModelMap model) {
    return "FarmaciaHome";
  }

  /**
   * <p>Metodo che mostra la pagina con tutti i farmaci presenti nel magazzino.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @return nome della pagina jsp Magazzino
   */
  @GetMapping(value = { "/magazzino" })
  public String showMagazzinoPage(ModelMap model) {
    model.addAttribute("Farmaci", service.getAllFarmaci());
    return "Magazzino";
  }

  /**
   * <p>Metodo che mostra la pagina con tutti i farmaci disponibili presenti nel magazzino.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @return nome della pagina jsp Magazzino
   */
  @GetMapping(value = { "/disponibili" })
  public String showMagazzinoDisponibiliPage(ModelMap model) {
    model.addAttribute("Farmaci", service.getAllFarmaciDisponibili());
    return "Magazzino";
  }

  /**
   * <p>Mostra il farmaco con uno specifico id.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param id id del farmaco da visualizzare
   * @return nome della pagina jsp Farmaco
   */
  @GetMapping(value = { "/magazzino/{id}" })
  public String showFarmacoPage(ModelMap model, @PathVariable String id) {
    model.addAttribute("Farmaco", service.getFarmaco(id));
    return "Farmaco";
  }

  /**
   * <p>Metodo che inizilizza la pagina di inserimento del farmaco.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @return nome della pagina jsp AggiungiFarmaco
   */
  @GetMapping(value = { "/add-farmaco-page" })
  public String insertFarmacoPage(ModelMap model) {
    model.addAttribute("scheda", new SchedaFarmaco());
    return "AggiungiFarmaco";
  }

  /**
   * <p>Metodo che cattura l'evento di inserimento del farmaco.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param scheda scheda farmaco da inserire
   * @param bindingResult contiene gli errori del form
   * @return redirect alla pagina del farmaco se l'inserimento va a buon fine,
   *      altrimenti il nome della pagina jsp AggiungiFarmaco
   */
  @PostMapping(value = { "/add-farmaco" })
  public String insertFarmaco(ModelMap model, @Valid @ModelAttribute("scheda") SchedaFarmaco scheda,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("scheda", scheda);
      return "AggiungiFarmaco";
    }
    String msg = service.addFarmaco(scheda);
    return "redirect:/farmacia/magazzino/" + scheda.getCodice();
  }

  /**
   * <p>Metodo che inizializza la pagina di inserimento del nuovo lotto.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param codice codice della scheda farmaco a cui aggiungere il lotto
   * @return nome della pagina jsp AggiungiLotto
   */
  @GetMapping(value = { "/add-lotto-page/{codice}" })
  public String insertLottoPage(ModelMap model, @PathVariable String codice) {
    model.addAttribute("codice", codice);
    model.addAttribute("lotto", new Lotto());
    return "AggiungiLotto";
  }

  /**
   * <p>Metodo che cattura l'evento di inserimento del nuovo lotto.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param codice codice della scheda farmaco a cui aggiungere il lotto
   * @param lotto lotto da aggiungere
   * @param bindingResult contiene gli errori del form
   * @return nome della pagina jsp Farmaco se l'inserimento va a buon fine,
   *      altrimenti nome della pagina jsp AggiungiLotto
   */
  @PostMapping(value = { "/add-lotto/{codice}" })
  public String insertLotto(ModelMap model, @PathVariable String codice,
                            @Valid @ModelAttribute("lotto") Lotto lotto,
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
      model.addAttribute("msg",
              msg.substring(msg.indexOf('"')).replace('"', ' ').replace('|', ' '));
      return "AggiungiLotto";
    }

    model.addAttribute("message", msg);
    model.addAttribute("Farmaco", service.getFarmaco(codice));
    return "Farmaco";
  }

  /**
   * <p>Metodo che inizilizza la pagina di modifica del farmaco.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param codice codice del farmaco da modificare
   * @param scheda scheda che viene utilizzata nel form
   * @return nome della pagina ModificaFarmaco
   */
  @GetMapping(value = { "/modifica-farmaco-page/{codice}" })
  public String modificaFarmacoPage(ModelMap model, @PathVariable String codice,
                                    @ModelAttribute SchedaFarmaco scheda) {
    scheda = service.getFarmaco(codice);
    model.addAttribute("codice", codice);
    model.addAttribute("scheda", scheda);
    return "ModificaFarmaco";
  }

  /**
   * <p>Metodo che cattura l'evento di modifica del farmaco.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param codice codice del farmaco da modificare
   * @param scheda scheda che viene utilizzata nel form con le informazioni modificate
   * @param bindingResult contiene gli errori del form
   * @return nome pagina jsp Farmaco se la modifica va a buon fine,
   *      altrimenti il nome della pagina jsp ModificaFarmaco
   */
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

  /**
   * <p>Metodo che inizilizza la pagina di modifica del lotto.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param num numero che identifica il lotto
   * @param codice codice del farmaco a cui è associato il lotto
   * @return nome della pagina jsp ModifcaLotto
   */
  @GetMapping(value = { "/modifica-lotto-page/{codice}/{num}" })
  public String modificaLottoPage(ModelMap model,
                                  @PathVariable Integer num, @PathVariable String codice) {
    Lotto lotto = service.getLotto(codice, num);
    model.addAttribute("codice", codice);
    model.addAttribute("lotto", lotto);
    return "ModificaLotto";
  }

  /**
   * <p>Metodo che cattura l'evento di modifica del lotto.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param codice codice del farmaco a cui è associato il lotto
   * @param lotto lotto con le modifiche
   * @param bindingResult contiene gli errori del form
   * @return nome pagina jsp Farmaco se la modifica va a buon fine,
   *      altrimenti il nome della pagina jsp ModificaLotto
   */
  @PostMapping(value = { "/modifica-lotto/{codice}" })
  public String modificaLotto(ModelMap model, @PathVariable String codice,
                              @Valid @ModelAttribute("lotto") Lotto lotto,
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

  /**
   * <p>Metodo che inizializza la pagina di nuovo ordine.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @return nome della pagina jsp NuovoOrdine
   */
  @GetMapping(value = { "/nuovo-ordine-page" })
  public String nuovoOrdinePage(ModelMap model) {
    model.addAttribute("ordine", new OrdineRequest());
    return "NuovoOrdine";
  }

  /**
   * <p>Metodo che cattura l'evento di creazione di un nuovo ordine.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param ordine nuovo ordine creato da inserire nel db
   * @param bindingResult contiene gli errori del form
   * @return redirect alla pagina jsp degli ordini se la creazione va a buon fine,
   *      altrimenti nome della pagin jsp NuovoOrdine
   */
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
    return "redirect:/farmacia/ordini-page";
  }

  /**
   * <p>Metodo che mostra la pagina di tutti gli ordini.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @return nome della pagina jsp Ordini
   */
  @GetMapping(value = { "/ordini-page" })
  public String ordiniPage(ModelMap model) {
    model.addAttribute("ordini", service.getAllOrdini());
    return "Ordini";
  }

  /**
   * <p>Metodo che cattura l'evento di eliminazione del farmaco.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param codice codice del farmaco da eliminare
   * @return nome della pagina jsp Magazzino
   */
  @GetMapping(value = { "/elimina/{codice}" })
  public String eliminaFarmaco(ModelMap model, @PathVariable String codice) {
    model.addAttribute("message", service.eliminaFarmaco(codice));
    model.addAttribute("Farmaci", service.getAllFarmaci());
    return "Magazzino";
  }

  /**
   * <p>Metodo che cattura l'evento di eliminazione del farmaco con un determinato codice.</p>
   *
   * @param model utilizzato per comunicare con le jsp
   * @param codice codice del farmaco da eliminare
   * @return nome della pagina jsp Magazzino
   */
  @GetMapping(value = { "/elimina-lotto/{codice}/{num}" })
  public String eliminaLotto(ModelMap model, @PathVariable String codice,
                             @PathVariable Integer num) {
    model.addAttribute("message", service.eliminaLotto(codice, service.getLotto(codice, num)));
    model.addAttribute("Farmaco", service.getFarmaco(codice));
    return "Farmaco";
  }
}
