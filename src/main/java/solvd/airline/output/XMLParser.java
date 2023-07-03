package solvd.airline.output;

import solvd.airline.dataaccess.model.AirlineRoute.AirLineRoutes;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solvd.airline.main.Main;

public class XMLParser {

    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        try {
            File file = new File("src/main/resources/xml/airlineroute.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(AirLineRoutes.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            AirLineRoutes routes = (AirLineRoutes) jaxbUnmarshaller.unmarshal(file);


            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


            jaxbMarshaller.marshal(routes, new File("src/main/resources/output/sampleOutput.xml"));

            logger.info(routes);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
