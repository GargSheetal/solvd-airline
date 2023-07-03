package solvd.airline.dataaccess.model.AirlineRoute;

import org.apache.ibatis.session.SqlSession;
import java.util.List;

public class AirLineRouteMybatis implements AirLineRouteDao {
    private final SqlSession sqlSession;

    public AirLineRouteMybatis(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public AirLineRoute getRoute(int id) {
        return this.sqlSession.selectOne("getRoute", id);
    }

    public List<AirLineRoute> getAllRoutes() {
        return this.sqlSession.selectList("getAllRoutes");
    }

    public void updateRoute(AirLineRoute route) {
        this.sqlSession.update("updateRoute", route);
    }

    public void deleteRoute(int id) {
        this.sqlSession.delete("deleteRoute", id);
    }

    public void addRoute(AirLineRoute route) {
        this.sqlSession.insert("addRoute", route);
    }
}
