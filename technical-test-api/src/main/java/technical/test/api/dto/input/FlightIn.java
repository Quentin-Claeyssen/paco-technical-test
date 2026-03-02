package technical.test.api.dto.input;

import lombok.Data;
import technical.test.api.dto.output.AirportOut;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class FlightIn {
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private double price;
    private String origin;
    private String destination;
    private String image;
}
