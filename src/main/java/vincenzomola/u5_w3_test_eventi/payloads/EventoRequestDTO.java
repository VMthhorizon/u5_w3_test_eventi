package vincenzomola.u5_w3_test_eventi.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EventoRequestDTO(
        @NotBlank(message = "Il titolo dell'evento è obbligatorio")
        @Size(min = 3, max = 20, message = "Il titolo deve contenere tra 3 e 20 caratteri")
        String titolo,
        @NotBlank(message = "La descrizione è obbligatoria")
        @Size(min = 10, max = 500, message = "La descrizione deve contenere tra 10 e 500 caratteri")
        String descrizione,
        @NotNull(message = "Il numero di posti disponibili è obbligatorio")
        @Min(value = 1, message = "I posti disponibili devono essere almeno 1")
        long postiDisponibili,
        @NotBlank(message = "Il luogo è obbligatorio")
        @Size(min = 2, max = 100, message = "Il luogo deve contenere tra 2 e 100 caratteri")
        String luogo
) {
}
