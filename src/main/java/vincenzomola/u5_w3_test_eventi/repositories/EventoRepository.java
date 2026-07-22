package vincenzomola.u5_w3_test_eventi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vincenzomola.u5_w3_test_eventi.entities.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
}
