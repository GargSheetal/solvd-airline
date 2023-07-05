package solvd.airline.dataaccess.model.AirlineRoute;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement(name = "airline_routes")
@XmlAccessorType(XmlAccessType.FIELD)
public class AirLineRoutes {
    @XmlElement(name = "airline_route")
    private List<AirLineRoute> routes;

    public List<AirLineRoute> getRoutes() {
        return routes;
    }

    @Override
    public String toString() {
        return "AirLineRoutes{" +
                "routes=" + routes.stream().map(AirLineRoute::toString).collect(Collectors.joining(", ")) +
                "}";
    }

}
