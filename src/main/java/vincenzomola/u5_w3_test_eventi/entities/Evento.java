package vincenzomola.u5_w3_test_eventi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Eventi")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private long id;
    private String titolo;
    private String descrizione;
    private LocalDateTime data;
    private String luogo;
    @Column(name = "posti_disponibili")
    private long postiDisponibili;
    @ManyToOne
    @JoinColumn(name = "id_organizzatore")
    private Utente utente;

    protected Evento() {
    }

    public Evento(String titolo, String descrizione, LocalDateTime data, Utente utente, long postiDisponibili,
                  String luogo) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data = data;
        this.utente = utente;
        this.postiDisponibili = postiDisponibili;
        this.luogo = luogo;
    }

    public long getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public long getPostiDisponibili() {
        return postiDisponibili;
    }

    public void setPostiDisponibili(long postiDisponibili) {
        this.postiDisponibili = postiDisponibili;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", data=" + data +
                ", luogo='" + luogo + '\'' +
                ", postiDisponibili=" + postiDisponibili +
                ", utente=" + utente +
                '}';
    }
}
