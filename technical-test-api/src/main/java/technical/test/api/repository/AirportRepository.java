package technical.test.api.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import technical.test.api.entity.Airport;

@Repository
public interface AirportRepository extends ReactiveMongoRepository<Airport, String> {
    Mono<Airport> findAirportRecordByIata(final String iata);
}
