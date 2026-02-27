package technical.test.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import technical.test.api.facade.FlightFacade;
import technical.test.api.dto.output.FlightOut;

@RestController
@RequestMapping("/flight")
@RequiredArgsConstructor
public class FlightController {
    private final FlightFacade flightFacade;

    @GetMapping
    public Flux<FlightOut> getAllFlights() {
        return flightFacade.getAllFlights();
    }
}
