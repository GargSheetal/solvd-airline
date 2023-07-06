package solvd.airline.dataaccess.service;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import solvd.airline.dataaccess.model.location.Location;
import solvd.airline.dataaccess.model.location.LocationMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class LocationMyBatisService {
    private final SqlSession sqlSession;
    private final LocationMapper locationMapper;

    public LocationMyBatisService() {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sessionFactory.openSession(true);
            locationMapper = sqlSession.getMapper(LocationMapper.class);
        } catch (IOException e) {
            throw new RuntimeException("Error initializing MyBatis session factory", e);
        }
    }

    public Location getLocationById(int id) {
        return locationMapper.getLocationById(id);
    }

    public List<Location> getAllLocations() {
        return locationMapper.getAllLocations();
    }

    public void updateLocation(Location location) {
        locationMapper.updateLocation(location);
    }

    public void deleteLocation(int id) {
        locationMapper.deleteLocation(id);
    }

    public void addLocation(Location location) {
        locationMapper.addLocation(location);
    }

    public void closeSession() {
        sqlSession.close();
    }
}