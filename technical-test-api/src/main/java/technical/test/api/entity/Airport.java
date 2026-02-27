package technical.test.api.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "airport")
public class Airport {
    @Id
    private String iata;
    private String name;
    private String country;
}