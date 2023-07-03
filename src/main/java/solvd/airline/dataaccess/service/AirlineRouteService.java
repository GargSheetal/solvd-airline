package solvd.airline.dataaccess.service;
import solvd.airline.dataaccess.model.AirlineRoute.AirLineRoute;
import solvd.airline.dataaccess.model.AirlineRoute.AirLineRouteDao;
import java.util.List;

public class AirlineRouteService {
    private AirLineRouteDao AirlinerouteDao;

    // Constructor for dependency injection
    public AirlineRouteService(AirLineRouteDao AirlinerouteDao) {
        this.AirlinerouteDao = AirlinerouteDao;
    }

    public AirLineRoute getRoute(int id) {
        return AirlinerouteDao.getRoute(id);
    }

    public List<AirLineRoute> getAllRoutes() {
        return AirlinerouteDao.getAllRoutes();
    }

    public void updateRoute(AirLineRoute route) {
        AirlinerouteDao.updateRoute(route);
    }

    public void deleteRoute(int id) {
        AirlinerouteDao.deleteRoute(id);
    }

    public void addRoute(AirLineRoute route) {
        AirlinerouteDao.addRoute(route);
    }
}
