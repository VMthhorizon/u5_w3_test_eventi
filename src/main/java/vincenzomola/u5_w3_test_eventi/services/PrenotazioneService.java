package vincenzomola.u5_w3_test_eventi.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import vincenzomola.u5_w3_test_eventi.entities.Evento;
import vincenzomola.u5_w3_test_eventi.entities.Prenotazione;
import vincenzomola.u5_w3_test_eventi.entities.Utente;
import vincenzomola.u5_w3_test_eventi.exceptions.BadRequestException;
import vincenzomola.u5_w3_test_eventi.exceptions.NotFoundException;
import vincenzomola.u5_w3_test_eventi.payloads.PrenotazioneRequestDTO;
import vincenzomola.u5_w3_test_eventi.repositories.EventoRepository;
import vincenzomola.u5_w3_test_eventi.repositories.PrenotazioneRepository;

import java.util.List;

@Service
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final EventoRepository eventoRepository;

    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository, EventoRepository eventoRepository) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.eventoRepository = eventoRepository;
    }

    @Transactional
    public Prenotazione creaPrenotazione(PrenotazioneRequestDTO body, Utente utenteLoggato) {

        Evento evento = eventoRepository.findById(body.eventoId())
                .orElseThrow(() -> new NotFoundException("Evento con ID " + body.eventoId() + " non trovato!"));

        if (evento.getPostiDisponibili() <= 0) {
            throw new BadRequestException("Spiacenti, i posti per questo evento sono esauriti!");
        }

        if (evento.getPostiDisponibili() < body.postiRiservati()) {
            throw new BadRequestException("I posti diponibili rimasti sono " + evento.getPostiDisponibili());
        }

        if (prenotazioneRepository.existsByUtenteAndEvento(utenteLoggato, evento)) {
            throw new BadRequestException("Hai già effettuato una prenotazione per questo evento!");
        }

        evento.setPostiDisponibili(evento.getPostiDisponibili() - body.postiRiservati());
        eventoRepository.save(evento);

        Prenotazione nuovaPrenotazione = new Prenotazione(utenteLoggato, evento, body.postiRiservati());
        return prenotazioneRepository.save(nuovaPrenotazione);
    }

    public List<Prenotazione> getPrenotazioniUtente(Utente utenteLoggato) {
        return prenotazioneRepository.findByUtente(utenteLoggato);
    }

}
