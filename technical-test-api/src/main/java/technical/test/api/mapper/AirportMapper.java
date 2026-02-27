package technical.test.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import technical.test.api.entity.Airport;
import technical.test.api.representation.AirportRepresentation;

import java.util.Collections;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, imports = Collections.class)
public interface AirportMapper {
    AirportRepresentation convert(Airport source);
    Airport convert(AirportRepresentation source);
}
