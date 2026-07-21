package vincenzomola.u5_w3_test_eventi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vincenzomola.u5_w3_test_eventi.exceptions.ValidationException;
import vincenzomola.u5_w3_test_eventi.payloads.EventoRequestDTO;
import vincenzomola.u5_w3_test_eventi.payloads.EventoResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    private fina

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventoResponseDTO saveEvento(@RequestBody @Validated EventoRequestDTO body, BindingResult validaredResult) {
        if (validaredResult.hasErrors()) {
            List<String> errorsList = validaredResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();

            throw new ValidationException(errorsList);
        }
        return new EventoResponseDTO(this.)
    }
}
