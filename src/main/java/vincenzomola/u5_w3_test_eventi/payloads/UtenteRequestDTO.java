package vincenzomola.u5_w3_test_eventi.payloads;

import jakarta.validation.constraints.*;
import vincenzomola.u5_w3_test_eventi.enums.RuoloUtente;

public record UtenteRequestDTO(
        @NotBlank(message = "Il campo non può essere vuoto")
        @Size(min = 2, max = 30, message = "Il nome deve avere tra 2 e 30 caratteri")
        @Pattern(regexp = "^[a-zA-Z\\sàèìòùòóÁÉÍÓÚçÇñÑ'-]+$", message = "Il nome non può contenere numeri o " +
                "caratteri speciali")
        String nome,
        @NotBlank(message = "Il campo non può essere vuoto")
        @Size(min = 2, max = 30, message = "Il cognome deve avere tra 2 e 30 caratteri")
        @Pattern(regexp = "^[a-zA-Z\\sàèìòùòóÁÉÍÓÚçÇñÑ'-]+$", message = "Il cognome non può contenere numeri o " +
                "caratteri speciali")
        String cognome,
        @NotBlank(message = "Il campo non può essere vuoto")
        @Size(min = 2, max = 50, message = "La mail deve avere tra 2 e 50 caratteri")
        @Email(message = "Formato per la email non valido")
        String email,
        @NotBlank
        @Size(min = 8, message = "La password deve essere almeno di 8 caratteri")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[^a-zA-Z0-9\\s])[a-zA-Z0-9[^a-zA-Z0-9\\s]]+$",
                message = "Il campo deve contenere caratteri alfanumerici, almeno un numero e almeno un carattere " +
                        "speciale"
        )
        String password,
        @NotNull(message = "Il campo specializzazione non può essere vuoto")
        RuoloUtente ruoloUtente
) {
}
