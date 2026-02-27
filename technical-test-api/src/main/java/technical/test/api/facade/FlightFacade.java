package technical.test.api.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.entity.Airport;
import technical.test.api.mapper.AirportMapper;
import technical.test.api.mapper.FlightMapper;
import technical.test.api.dto.output.FlightOut;
import technical.test.api.services.AirportService;
import technical.test.api.services.FlightService;

@Component
@RequiredArgsConstructor
public class FlightFacade {
    private final FlightService flightService;
    private final AirportService airportService;
    private final FlightMapper flightMapper;
    private final AirportMapper airportMapper;

    public Flux<FlightOut> getAllFlights() {
        return flightService.getAllFlights()
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
