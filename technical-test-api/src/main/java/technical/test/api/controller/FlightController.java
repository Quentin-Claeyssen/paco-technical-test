package technical.test.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Flux<FlightOut> getAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "price") String sortBy) {
        Pageable pageable = PageRequest.of(page, 6, Sort.by(sortBy).ascending());
        return flightService.getAll(pageable);
    }

    @PostMapping
    public Mono<FlightOut> create(@RequestBody FlightIn flight) {
        return flightService.create(flight);
    }
}
