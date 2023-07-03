package solvd.airline.dataaccess.model.AirlineRoute;

import org.apache.ibatis.annotations.*;
import java.util.List;

public interface AirLineRouteMapper {

    @Select("SELECT * FROM `airline_routes` WHERE `airline_route_id` = #{id}")
    AirLineRoute getRoute(int id);

    @Select("SELECT * FROM `airline_routes`")
    List<AirLineRoute> getAllRoutes();

    @Update("UPDATE `airline_routes` SET `origin_location_id` = #{originLocationId}, `destination_location_id` = #{destinationLocationId}, `distance_miles` = #{distanceMiles}, `price` = #{priceDollars} WHERE `airline_route_id` = #{routeId}")
    void updateRoute(AirLineRoute route);

    @Delete("DELETE FROM `airline_routes` WHERE `airline_route_id` = #{id}")
    void deleteRoute(int id);

    @Insert("INSERT INTO `airline_routes`(`origin_location_id`, `destination_location_id`, `distance_miles`, `price`) VALUES (#{originLocationId}, #{destinationLocationId}, #{distanceMiles}, #{priceDollars})")
    @Options(useGeneratedKeys=true, keyProperty="routeId")
    void addRoute(AirLineRoute route);
}
