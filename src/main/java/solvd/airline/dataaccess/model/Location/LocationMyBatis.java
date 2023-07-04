package solvd.airline.dataaccess.model.Location;
import org.apache.ibatis.session.SqlSession;
import java.util.List;

public class LocationMyBatis implements LocationMapper {
    private final SqlSession sqlSession;

    public LocationMyBatis(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Location getLocationById(int id) {
        return this.sqlSession.selectOne("getLocationById", id);
    }

    public List<Location> getAllLocations() {
        return this.sqlSession.selectList("getAllLocations");
    }

    public void addLocation(Location location) {
        this.sqlSession.insert("addLocation", location);
    }

    public void updateLocation(Location location) {
        this.sqlSession.update("updateLocation", location);
    }

    public void deleteLocation(int id) {
        this.sqlSession.delete("deleteLocation", id);
    }
}