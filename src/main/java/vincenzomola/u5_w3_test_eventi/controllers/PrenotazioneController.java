package vincenzomola.u5_w3_test_eventi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vincenzomola.u5_w3_test_eventi.entities.Prenotazione;
import vincenzomola.u5_w3_test_eventi.entities.Utente;
import vincenzomola.u5_w3_test_eventi.exceptions.ValidationException;
import vincenzomola.u5_w3_test_eventi.payloads.PrenotazioneRequestDTO;
import vincenzomola.u5_w3_test_eventi.payloads.PrenotazioneResponseDTO;
import vincenzomola.u5_w3_test_eventi.services.PrenotazioneService;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;

    public PrenotazioneController(PrenotazioneService prenotazioneService) {
        this.prenotazioneService = prenotazioneService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('USER')")
    public PrenotazioneResponseDTO createEvento(@RequestBody @Validated PrenotazioneRequestDTO body,
                                                BindingResult validatedResult, @AuthenticationPrincipal Utente utente) {
        if (validatedResult.hasErrors()) {
            List<String> errorsList = validatedResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValidationException(errorsList);
        }
        Prenotazione prenotazione = this.prenotazioneService.creaPrenotazione(body, utente);
        return new PrenotazioneResponseDTO(prenotazione.getId(), prenotazione.getPostiRiservati());
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyAuthority('USER')")
    public List<Prenotazione> getMiePrenotazioni(@AuthenticationPrincipal Utente utenteLoggato) {
        return prenotazioneService.getPrenotazioniUtente(utenteLoggato);
    }
}
