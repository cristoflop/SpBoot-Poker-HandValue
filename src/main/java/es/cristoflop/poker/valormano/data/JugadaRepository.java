package es.cristoflop.poker.valormano.data;

import es.cristoflop.poker.valormano.domain.Jugada;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadaRepository extends CrudRepository<Jugada, Long> {
}
