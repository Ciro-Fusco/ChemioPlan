package com.example.FrontEnd.FrontEnd.controller;

import com.example.FrontEnd.FrontEnd.model.SchedaFarmaco;
import com.example.FrontEnd.FrontEnd.service.IFarmacia_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

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
        SchedaFarmaco[] s=service.getAllFarmaci();
        model.addAttribute("Farmaci",s);

        return "Magazzino";
    }

    @RequestMapping(value = { "/magazzino/{id}" }, method = RequestMethod.GET)
    public String showFarmacoPage(ModelMap model,@PathVariable String id) {
        SchedaFarmaco s=service.getFarmaco(id);
        model.addAttribute("Farmaco",s);

        return "Farmaco";
    }

}
