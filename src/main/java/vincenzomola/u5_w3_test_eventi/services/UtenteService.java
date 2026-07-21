package vincenzomola.u5_w3_test_eventi.services;

import org.springframework.stereotype.Service;
import vincenzomola.u5_w3_test_eventi.repositories.UtenteRepository;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;

    public UtenteService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }
    

}
