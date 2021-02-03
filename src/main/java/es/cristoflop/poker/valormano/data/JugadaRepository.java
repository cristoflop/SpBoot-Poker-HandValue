package es.cristoflop.poker.valormano.data;

import es.cristoflop.poker.valormano.domain.Jugada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadaRepository extends JpaRepository<Jugada, Long> {
}
