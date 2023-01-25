package com.example.FrontEnd.FrontEnd.controller;

import com.example.FrontEnd.FrontEnd.model.SchedaFarmaco;
import com.example.FrontEnd.FrontEnd.service.IFarmacia_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("name")
@RequestMapping(value = { "/farmacia" }, method = RequestMethod.GET)
public class Farmacia_Controller {

    @Autowired
    private IFarmacia_Service service;

    @RequestMapping(value = { "" }, method = RequestMethod.GET)
    public String showFarmaciaHomePage(ModelMap model) {
        return "Farmacia_Home";
    }

    @RequestMapping(value = { "/magazzino" }, method = RequestMethod.GET)
    public String showMagazzinoPage(ModelMap model) {
        SchedaFarmaco[] s = service.getAllFarmaci();
        model.addAttribute("Farmaci",s);

        return "Magazzino";
    }

    @RequestMapping(value = { "/magazzino/{id}" }, method = RequestMethod.GET)
    public String showFarmacoPage(ModelMap model,@PathVariable String id) {
        SchedaFarmaco s = service.getFarmaco(id);
        model.addAttribute("Farmaco",s);

        return "Farmaco";
    }

    @RequestMapping(value = {"/add-farmaco-page"}, method = RequestMethod.GET)
    public String insertFarmacoPage(@ModelAttribute SchedaFarmaco scheda, ModelMap model) {
        model.addAttribute("scheda", scheda);
        return "AggiungiFarmaco";
    }

    @RequestMapping(value = {"/add-farmaco"}, method = RequestMethod.POST)
    public String insertFarmaco(@ModelAttribute SchedaFarmaco scheda, ModelMap model){
        System.out.println(scheda);
        model.addAttribute("message", service.addFarmaco(scheda));
        model.addAttribute("scheda", scheda);
        return "AggiungiFarmaco";
    }

}
