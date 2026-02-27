package technical.test.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import technical.test.api.entity.Flight;
import technical.test.api.repository.FlightRepository;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;

    public Flux<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

}
