package vincenzomola.u5_w3_test_eventi.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prenotazione")
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Utente utente;
    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;
    @Column(name = "posti_riservati")
    private long postiRiservati;

    protected Prenotazione() {
    }

    public Prenotazione(Utente utente, Evento evento, long postiRiservati) {
        this.utente = utente;
        this.evento = evento;
        this.postiRiservati = postiRiservati;
    }

    public long getPostiRiservati() {
        return postiRiservati;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", utente=" + utente +
                ", evento=" + evento +
                ", postiRiservati=" + postiRiservati +
                '}';
    }
}
