package technical.test.api.dto.output;

import lombok.Data;

@Data
public class AirportOut {
    private String iata;
    private String name;
    private String country;
}
