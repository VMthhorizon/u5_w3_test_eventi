package vincenzomola.u5_w3_test_eventi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vincenzomola.u5_w3_test_eventi.entities.Evento;
import vincenzomola.u5_w3_test_eventi.entities.Utente;
import vincenzomola.u5_w3_test_eventi.exceptions.ValidationException;
import vincenzomola.u5_w3_test_eventi.payloads.EventoRequestDTO;
import vincenzomola.u5_w3_test_eventi.payloads.EventoResponseDTO;
import vincenzomola.u5_w3_test_eventi.services.EventoService;

import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public EventoResponseDTO saveEvento(@RequestBody @Validated EventoRequestDTO body,
                                        BindingResult validaredResult, @AuthenticationPrincipal Utente utente) {
        if (validaredResult.hasErrors()) {
            List<String> errorsList = validaredResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();

            throw new ValidationException(errorsList);
        }

        Evento evento = this.eventoService.saveEvento(body, utente);
        return new EventoResponseDTO(evento.getId(), evento.getTitolo());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Evento updateEvento(
            @PathVariable long id,
            @RequestBody @Validated EventoRequestDTO body,
            BindingResult validationResult,
            @AuthenticationPrincipal Utente utenteLoggato
    ) {
        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValidationException(errorsList);
        }

        return this.eventoService.updateEvento(id, body, utenteLoggato);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public void deleteEvento(
            @PathVariable long id,
            @AuthenticationPrincipal Utente utenteLoggato
    ) {
        this.eventoService.deleteEvento(id, utenteLoggato);
    }

}
