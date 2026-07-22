package vincenzomola.u5_w3_test_eventi.services;

import org.springframework.stereotype.Service;
import vincenzomola.u5_w3_test_eventi.entities.Evento;
import vincenzomola.u5_w3_test_eventi.entities.Utente;
import vincenzomola.u5_w3_test_eventi.exceptions.NotFoundException;
import vincenzomola.u5_w3_test_eventi.exceptions.UnauthorizedException;
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

    public Evento findById(long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evento con ID " + id + " non trovato!"));
    }

    public Evento updateEvento(long id, EventoRequestDTO body, Utente utenteLoggato) {
        Evento evento = this.findById(id);

        if (evento.getUtente()
                .getId() != utenteLoggato.getId()) {
            throw new UnauthorizedException(
                    "Non hai i permessi per modificare questo evento! Non sei l'organizzatore proprietario.");
        }

        evento.setTitolo(body.titolo());
        evento.setDescrizione(body.descrizione());
        evento.setPostiDisponibili(body.postiDisponibili());
        evento.setLuogo(body.luogo());

        return eventoRepository.save(evento);
    }

    public void deleteEvento(long id, Utente utenteLoggato) {
        Evento evento = this.findById(id);

        if (evento.getUtente()
                .getId() != utenteLoggato.getId()) {
            throw new UnauthorizedException(
                    "Non hai i permessi per eliminare questo evento! Non sei l'organizzatore proprietario.");
        }

        eventoRepository.delete(evento);
    }

}
