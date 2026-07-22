package vincenzomola.u5_w3_test_eventi.entities;

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
