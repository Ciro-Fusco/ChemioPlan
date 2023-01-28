package com.example.FrontEnd.FrontEnd.Controller;

import com.example.FrontEnd.FrontEnd.model.Credenziali;
import com.example.FrontEnd.FrontEnd.model.Utente;
import com.example.FrontEnd.FrontEnd.service.IUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@SessionAttributes("ruolo")
public class UtenteController {

    @Autowired
    private IUtenteService service;

    @RequestMapping(value= {"/index","/"}, method = RequestMethod.GET)
    public String showIndexPage(ModelMap model){
        model.addAttribute("credenziali", new Credenziali());
        return "Login";
    }

    @RequestMapping(value = {"/logo"}, method = RequestMethod.GET)
    public String clickLogo(ModelMap model){
        if(model.getAttribute("ruolo") == "") {
            model.addAttribute("credenziali", new Credenziali());
            return "Login";
        }
        return "Index";
    }

    @RequestMapping(value= {"/utente/login"}, method = RequestMethod.GET)
    public String showLoginPage(ModelMap model, @ModelAttribute Credenziali credenziali){
        model.addAttribute("message", null);
        model.addAttribute("credenziali", credenziali);
        return "Login";
    }

    @RequestMapping(value = {"/utente/verificaCredenziali"}, method = RequestMethod.POST)
    public String login(@ModelAttribute Credenziali credenziali,ModelMap model){
        String response = service.verificaCrendenziali(credenziali);
        if(response.equals("Login effettuato con successo!")){
            model.addAttribute("message" , response);
            model.addAttribute("ruolo", service.getRuoloByUser(credenziali.getUser()));
            System.out.println(service.getRuoloByUser(credenziali.getUser()));
            return "Index";
        }else{
            model.addAttribute("message" , "Credenziali non valide!");
            return "Login";
        }
    }

    @RequestMapping(value="/utente/logout", method = RequestMethod.GET)
    public String logout(ModelMap model){
        model.addAttribute("ruolo", "");
        model.addAttribute("credenziali", new Credenziali());
        return "Login";
    }

    @RequestMapping(value= {"/utenti"}, method = RequestMethod.GET)
    public String showUtentiPage(ModelMap model){
        Utente[] u=service.getUtenti();

//        System.out.println(u.length);

        model.addAttribute("Utenti",u);
        return "Utenti";
    }

    @RequestMapping(value= {"/utenti/{id}"}, method = RequestMethod.GET)
    public String showUtentePage(ModelMap model,@PathVariable Integer id){
        Utente u=service.getUtente(id);

        model.addAttribute("Utente",u);
        return "Utente";
    }
}
