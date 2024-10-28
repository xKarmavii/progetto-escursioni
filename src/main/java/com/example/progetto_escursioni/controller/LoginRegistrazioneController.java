package com.example.progetto_escursioni.controller;

import com.example.progetto_escursioni.model.Utente;
import com.example.progetto_escursioni.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loginregistrazione")
public class LoginRegistrazioneController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String getPage(
            HttpSession session,
            Model model) {

        // se provi a riaccedere alla pagina di login quando sei già registrato (modificando l'URL), vieni reindirizzato all'area riservata
        if (session.getAttribute("utente") != null) {
            return "redirect:/areariservata";
        }

        // crea un nuovo oggetto utente e lo registra nel model perché il form di registrazione ha il binding con esso
        Utente utente = new Utente();
        model.addAttribute("utente", utente);

        return "login-registrazione";
    }

    @PostMapping("/login")
    public String loginManager(HttpSession session,
                               Model model,
                               @ModelAttribute("utente") Utente utente,
                               @RequestParam("login-username") String username,
                               @RequestParam("login-password") String password) {


        if (utenteService.loginUtente(session, username, password)) {
            // se il login ha successo (ovvero se c'è un utente corrispondente sul database) allora reindirizza all'area riservata
            return "redirect:/" + session.getAttribute("paginaPrecedente");
        }
        else {
            // altrimenti registra nel model un messaggio di errore, da mostrare sul form di login
            model.addAttribute("messaggio", "Credenziali errate");
        }
        return "login-registrazione";
    }

    @PostMapping("/registrazione")
    public String registrazioneManager(Model model,
                                       @ModelAttribute("utente") Utente utente) {

        // controllo per vedere se username o password sono già in uso
        if (utenteService.controlloUsernameEmail(utente.getUsername(), utente.getEmail())) {
            utenteService.salvaUtente(utente); // se è tutto ok salva l'utente sul database
            model.addAttribute("messaggio", "Registrazione effettuata"); // questo messaggio deve comparire sul form di login
        }
        else {
            model.addAttribute("messaggioDue", "Nome utente o email già in uso"); // questo messaggio deve comparire sul form di registrazione
        }

        // reinizializza l'oggetto utente e sovrascrive l'oggetto salvato nel model, così i campi del form di registrazione di svuotano (perché c'è il binding tra form e oggetto utente)
        utente = new Utente();
        model.addAttribute("utente", utente);

        return "login-registrazione";
    }

    @GetMapping("/indietro")
    public String tornaIndietro(HttpSession session) {
        return "redirect:/" + session.getAttribute("paginaPrecedente");
    }
}
