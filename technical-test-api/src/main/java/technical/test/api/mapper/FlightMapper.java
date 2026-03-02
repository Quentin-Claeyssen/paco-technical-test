package technical.test.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import technical.test.api.dto.input.FlightIn;
import technical.test.api.entity.Flight;
import technical.test.api.dto.output.AirportOut;
import technical.test.api.dto.output.FlightOut;

import java.util.Collections;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, imports = Collections.class)
public interface FlightMapper {
    @Mapping(target = "origin", source = "origin", ignore = true)
    @Mapping(target = "destination", source = "destination", ignore = true)
    FlightOut convert(Flight source);

    Flight convert(FlightIn source);
}
