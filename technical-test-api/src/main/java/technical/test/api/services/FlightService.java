package technical.test.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.dto.output.FlightOut;
import technical.test.api.entity.Airport;
import technical.test.api.mapper.AirportMapper;
import technical.test.api.mapper.FlightMapper;
import technical.test.api.repository.FlightRepository;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final AirportService airportService;
    private final FlightMapper flightMapper;
    private final AirportMapper airportMapper;

    public Flux<FlightOut> getAll() {
        return flightRepository.findAll()
                .flatMap(flightRecord -> airportService.findByIataCode(flightRecord.getOrigin())
                        .zipWith(airportService.findByIataCode(flightRecord.getDestination()))
                        .flatMap(tuple -> {
                            Airport origin = tuple.getT1();
                            Airport destination = tuple.getT2();
                            FlightOut flightOut = this.flightMapper.convert(flightRecord);
                            flightOut.setOrigin(this.airportMapper.convert(origin));
                            flightOut.setDestination(this.airportMapper.convert(destination));
                            return Mono.just(flightOut);
                        }));
    }

}
