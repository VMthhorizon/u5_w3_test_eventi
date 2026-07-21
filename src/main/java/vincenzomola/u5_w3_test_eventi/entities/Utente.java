package vincenzomola.u5_w3_test_eventi.entities;

import jakarta.persistence.*;
import vincenzomola.u5_w3_test_eventi.enums.RuoloUtente;

@Entity
@Table(name = "Utenti")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utente")
    private long id;
    private String nome;
    private String cognome;
    private String email;
    private String Password;
    @Column(name = "ruolo_utente")
    @Enumerated(EnumType.STRING)
    private RuoloUtente ruoloUtente;

    protected Utente() {
    }

    public Utente(String nome, String cognome, String email, String password, RuoloUtente ruoloUtente) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        Password = password;
        this.ruoloUtente = ruoloUtente;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", Password='" + Password + '\'' +
                ", ruoloUtente=" + ruoloUtente +
                '}';
    }
}
