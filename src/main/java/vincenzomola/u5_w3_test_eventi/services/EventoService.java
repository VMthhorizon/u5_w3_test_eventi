package vincenzomola.u5_w3_test_eventi.services;

import org.springframework.stereotype.Service;
import vincenzomola.u5_w3_test_eventi.entities.Evento;
import vincenzomola.u5_w3_test_eventi.entities.Utente;
import vincenzomola.u5_w3_test_eventi.payloads.EventoRequestDTO;
import vincenzomola.u5_w3_test_eventi.repositories.EventoRepository;

import java.time.LocalDateTime;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public Evento saveEvento(EventoRequestDTO body, Utente organizzatore) {
        Evento evento = new Evento(body.titolo(), body.descrizione(), LocalDateTime.now(), organizzatore,
                body.postiDisponibili(),
                body.luogo());
        return this.eventoRepository.save(evento);
    }
}
