package solvd.airline.dataaccess.model.AirlineRoute;

import org.apache.ibatis.annotations.*;
import java.util.List;

public interface AirLineRouteMapper {

    AirLineRoute getRoute(int id);

    List<AirLineRoute> getAllRoutes();

    void updateRoute(AirLineRoute route);

    void deleteRoute(int id);

    void addRoute(AirLineRoute route);
}
