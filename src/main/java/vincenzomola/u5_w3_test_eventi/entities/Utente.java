package vincenzomola.u5_w3_test_eventi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vincenzomola.u5_w3_test_eventi.enums.RuoloUtente;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Utenti")
public class Utente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utente")
    private long id;
    private String nome;
    private String cognome;
    private String email;
    @JsonIgnore
    @Column(length = 255)
    private String password;
    @Column(name = "ruolo_utente")
    @Enumerated(EnumType.STRING)
    private RuoloUtente ruoloUtente;

    protected Utente() {
    }

    public Utente(String nome, String cognome, String email, String password, RuoloUtente ruoloUtente) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.ruoloUtente = ruoloUtente;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public RuoloUtente getRuoloUtente() {
        return ruoloUtente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.ruoloUtente.name()));
    }

    @Override
    public @Nullable String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", Password='" + password + '\'' +
                ", ruoloUtente=" + ruoloUtente +
                '}';
    }
}
