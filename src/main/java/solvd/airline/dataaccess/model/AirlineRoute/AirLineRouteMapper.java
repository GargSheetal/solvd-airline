package solvd.airline.dataaccess.model.AirlineRoute;

import java.util.List;

public interface AirLineRouteMapper {

    AirLineRoute getRoute(int id);

    List<AirLineRoute> getAllRoutes();

    void updateRoute(AirLineRoute route);

    void deleteRoute(int id);

    void addRoute(AirLineRoute route);
}
