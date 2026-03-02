package technical.test.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.dto.input.FlightIn;
import technical.test.api.dto.output.FlightOut;
import technical.test.api.services.FlightService;

@RestController
@RequestMapping("/flight")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @GetMapping
    public Flux<FlightOut> getAll() {
        return flightService.getAll();
    }

    @PostMapping
    public Mono<FlightOut> create(@RequestBody FlightIn flight) {
        return flightService.create(flight);
    }
}
