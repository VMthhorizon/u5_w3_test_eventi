package vincenzomola.u5_w3_test_eventi.payloads;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PrenotazioneRequestDTO(@NotNull(message = "L'ID dell'evento è obbligatorio")
                                     @Min(value = 1, message = "L'ID dell'evento non è valido")
                                     Long eventoId,
                                     @NotNull(message = "Posti riservqati obbligatorio")
                                     @Min(value = 1, message = "Selezionare almeno un posto")
                                     @Max(value = 10, message = "Selezionare massimo 10 posti")
                                     Long postiRiservati) {
}
