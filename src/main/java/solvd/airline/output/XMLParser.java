package solvd.airline.output;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solvd.airline.dataaccess.model.AirlineRoute.AirLineRoutes;
import solvd.airline.main.Main;

public class XMLParser {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static <T> T parseXml(File file, Class<T> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return clazz.cast(jaxbUnmarshaller.unmarshal(file));
    }

    public static void main(String[] args) {
        try {
            File airlineRouteFile = new File("src/main/resources/xml/airlineroute.xml");

            // Parse AirLineRoute XML
            AirLineRoutes airlineRoutes = parseXml(airlineRouteFile, AirLineRoutes.class);
            logger.info(airlineRoutes);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
