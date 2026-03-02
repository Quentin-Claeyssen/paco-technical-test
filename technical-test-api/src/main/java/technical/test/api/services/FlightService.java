package technical.test.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import technical.test.api.dto.input.FlightIn;
import technical.test.api.dto.output.FlightOut;
import technical.test.api.entity.Airport;
import technical.test.api.entity.Flight;
import technical.test.api.mapper.AirportMapper;
import technical.test.api.mapper.FlightMapper;
import technical.test.api.repository.FlightRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final AirportService airportService;
    private final FlightMapper flightMapper;
    private final AirportMapper airportMapper;

    public Flux<FlightOut> getAll() {
        return flightRepository.findAll()
                .flatMap(this::addAirportOutput);
    }

    public Mono<FlightOut> create(FlightIn flightIn) {
        return Mono.just(flightIn)
                .map(flightMapper::convert)
                .map(record -> { record.setId(UUID.randomUUID()); return record; })
                .flatMap(flightRepository::save)
                .flatMap(this::addAirportOutput);
    }

    private Mono<FlightOut> addAirportOutput(Flight flight) {
        return Mono.zip(
                airportService.findByIataCode(flight.getOrigin()),
                airportService.findByIataCode(flight.getDestination())
                )
                .map(tuple -> buildOutput(flight, tuple));
    }

    private FlightOut buildOutput(Flight flight, Tuple2<Airport, Airport> airports) {
        Airport origin = airports.getT1();
        Airport destination = airports.getT2();

        FlightOut flightOut = flightMapper.convert(flight);
        flightOut.setOrigin(airportMapper.convert(origin));
        flightOut.setDestination(airportMapper.convert(destination));

        return flightOut;
    }

}
