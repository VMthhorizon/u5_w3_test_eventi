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
        System.out.println("================ DEBUG UTENTE ================");
        System.out.println("Email utente: " + utente.getId());
        System.out.println("Ruolo Enum nell'entità: " + utente.getRuoloUtente());
        System.out.println("Authorities di Spring Security: " + utente.getAuthorities());
        System.out.println("==============================================");
        Evento evento = this.eventoService.saveEvento(body, utente);
        return new EventoResponseDTO(evento.getId(), evento.getTitolo());
    }
}
