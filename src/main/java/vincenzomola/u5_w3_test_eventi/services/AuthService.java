package vincenzomola.u5_w3_test_eventi.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vincenzomola.u5_w3_test_eventi.entities.Utente;
import vincenzomola.u5_w3_test_eventi.exceptions.UnauthorizedException;
import vincenzomola.u5_w3_test_eventi.payloads.LoginRequestDTO;
import vincenzomola.u5_w3_test_eventi.security.JWTTools;

@Service
public class AuthService {

    private final UtenteService utenteService;
    private final JWTTools jwtTools;
    private final PasswordEncoder bcrypt;

    public AuthService(UtenteService utenteService, JWTTools jwtTools, PasswordEncoder bcrypt) {
        this.utenteService = utenteService;
        this.jwtTools = jwtTools;
        this.bcrypt = bcrypt;
    }

    public String checkLogin(LoginRequestDTO body) {

        Utente utente = this.utenteService.findByEmail(body.email());

        if (!bcrypt.matches(body.password(), utente.getPassword())) {
            throw new UnauthorizedException("Email o password errate");
        }
        return this.jwtTools.generateToken(utente);
    }
}
