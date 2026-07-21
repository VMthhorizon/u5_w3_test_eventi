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
    @Column(name = "data_prenotazione")
    private LocalDateTime dataPrenotazione;
    @Column(name = "posti_riservati")
    private long postiRiservati;

    public Prenotazione(Utente utente, Evento evento, LocalDateTime dataPrenotazione, long postiRiservati) {
        this.utente = utente;
        this.evento = evento;
        this.dataPrenotazione = dataPrenotazione;
        this.postiRiservati = postiRiservati;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", utente=" + utente +
                ", evento=" + evento +
                ", dataPrenotazione=" + dataPrenotazione +
                ", postiRiservati=" + postiRiservati +
                '}';
    }
}
