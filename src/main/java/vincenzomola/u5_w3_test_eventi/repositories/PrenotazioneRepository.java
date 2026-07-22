package vincenzomola.u5_w3_test_eventi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vincenzomola.u5_w3_test_eventi.entities.Evento;
import vincenzomola.u5_w3_test_eventi.entities.Prenotazione;
import vincenzomola.u5_w3_test_eventi.entities.Utente;

import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByUtenteAndEvento(Utente utente, Evento evento);

    List<Prenotazione> findByUtente(Utente utente);
}
