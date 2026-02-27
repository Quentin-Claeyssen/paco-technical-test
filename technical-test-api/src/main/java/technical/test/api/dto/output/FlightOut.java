package technical.test.api.dto.output;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class FlightOut {
    private UUID id;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private double price;
    private AirportOut origin;
    private AirportOut destination;
    private String image;
}
