package solvd.airline.main;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import solvd.airline.dataaccess.model.AirlineRoute.AirLineRoute;
import solvd.airline.dataaccess.model.AirlineRoute.AirLineRouteMapper;

import java.io.InputStream;
import java.util.List;

public class TestMyBatis {
    public static void main(String[] args) throws Exception {
        // Create SqlSessionFactory using the MyBatis config file.
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        // Obtain a SqlSession from the factory.
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // Now we can execute our mapper methods.
            AirLineRouteMapper mapper = session.getMapper(AirLineRouteMapper.class);

            // For example, test the 'getRoute' operation.
            AirLineRoute route = mapper.getRoute(1);
            System.out.println(route);

            List<AirLineRoute> allRoute = mapper.getAllRoutes();
            for (AirLineRoute airLineRoute : allRoute){
                System.out.println(airLineRoute);
            }

        }
    }
}
