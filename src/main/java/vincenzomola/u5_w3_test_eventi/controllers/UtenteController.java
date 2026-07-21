package vincenzomola.u5_w3_test_eventi.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vincenzomola.u5_w3_test_eventi.entities.Utente;
import vincenzomola.u5_w3_test_eventi.payloads.UtenteProfileDTO;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @GetMapping("/me")
    public UtenteProfileDTO myProfile(@AuthenticationPrincipal Utente utente) {
        return new UtenteProfileDTO(utente.getNome(), utente.getCognome(), utente.getUsername());
    }
}
