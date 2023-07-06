package solvd.airline.dataaccess.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import solvd.airline.dataaccess.model.AirlineRoute.AirLineRoute;
import solvd.airline.dataaccess.model.AirlineRoute.AirLineRouteMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AirLineRouteMybatisService {
    private final SqlSession sqlSession;
    private final AirLineRouteMapper airlinerouteMapper;

    public AirLineRouteMybatisService()  throws IOException {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sessionFactory.openSession(true);
            airlinerouteMapper = sqlSession.getMapper(AirLineRouteMapper.class);
        } catch (IOException e) {
            throw new RuntimeException("Error initializing MyBatis session factory", e);
        }
    }

    public AirLineRoute getRoute(int id) {
        return this.airlinerouteMapper.getRoute(id);
    }

    public List<AirLineRoute> getAllRoutes() {
        return this.airlinerouteMapper.getAllRoutes();
    }

    public void updateRoute(AirLineRoute route) {
        this.airlinerouteMapper.updateRoute(route);
    }

    public void deleteRoute(int id) {
        this.airlinerouteMapper.deleteRoute(id);
    }

    public void addRoute(AirLineRoute route) {
        this.airlinerouteMapper.addRoute(route);
    }
}