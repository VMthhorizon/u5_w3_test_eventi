package vincenzomola.u5_w3_test_eventi.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vincenzomola.u5_w3_test_eventi.entities.Utente;
import vincenzomola.u5_w3_test_eventi.exceptions.NotFoundException;
import vincenzomola.u5_w3_test_eventi.payloads.UtenteRequestDTO;
import vincenzomola.u5_w3_test_eventi.repositories.UtenteRepository;

import java.util.UUID;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final PasswordEncoder bcrypt;

    public UtenteService(UtenteRepository utenteRepository, PasswordEncoder bcrypt) {
        this.utenteRepository = utenteRepository;
        this.bcrypt = bcrypt;
    }

    public Utente saveUtente(UtenteRequestDTO paylaod) {
        Utente utente = new Utente(paylaod.nome(), paylaod.cognome(), paylaod.email(),
                this.bcrypt.encode(paylaod.password()), paylaod.ruoloUtente());
        return this.utenteRepository.save(utente);
    }

    public Utente findById(long id) {
        return this.utenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utente con id " + id + " non trovato"));
    }

    public Utente findByEmail(String email) {
        return this.utenteRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente con email: " + email + " non trovato"));
    }

}
