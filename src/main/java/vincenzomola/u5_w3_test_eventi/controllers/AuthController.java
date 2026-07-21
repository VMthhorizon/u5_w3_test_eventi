package vincenzomola.u5_w3_test_eventi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vincenzomola.u5_w3_test_eventi.exceptions.ValidationException;
import vincenzomola.u5_w3_test_eventi.payloads.LoginRequestDTO;
import vincenzomola.u5_w3_test_eventi.payloads.LoginResponseDTO;
import vincenzomola.u5_w3_test_eventi.payloads.UtenteRequestDTO;
import vincenzomola.u5_w3_test_eventi.payloads.UtenteResponseDTO;
import vincenzomola.u5_w3_test_eventi.services.AuthService;
import vincenzomola.u5_w3_test_eventi.services.UtenteService;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UtenteService utenteService;
    private final AuthService authService;

    public AuthController(UtenteService utenteService, AuthService authService) {
        this.utenteService = utenteService;
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UtenteResponseDTO createAccount(@RequestBody @Validated UtenteRequestDTO body,
                                           BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValidationException(errorsList);
        }
        return new UtenteResponseDTO(this.utenteService.saveUtente(body)
                .getId());
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Validated LoginRequestDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            validationResult.getFieldErrors()
                    .forEach(fieldError -> System.out.println(fieldError.getDefaultMessage()));

            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValidationException(errorsList);
        }
        return new LoginResponseDTO(this.authService.checkLogin(body));
    }

}
