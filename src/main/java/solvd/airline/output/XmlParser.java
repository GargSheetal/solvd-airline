package solvd.airline.output;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class XmlParser {

    private static final String OUTPUT_DIRECTORY = "src/main/resources/output";

    public static <T> T parseXml(File file, Class<T> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return clazz.cast(jaxbUnmarshaller.unmarshal(file));
    }


    public static <T> void writeXml(File file, T object) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
            jaxbMarshaller.marshal(object, file);
            System.out.println("\n\n----- Data saved to Xml file: " + file.getAbsolutePath());
        } catch (JAXBException e) {
            e.printStackTrace();
            System.out.println("\nFailed to save data to Xml file: " + e.getMessage());
        }
    }


    public static <T> void saveListToXml(List<T> objectList, String filename, Class<?> wrapperClass) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(wrapperClass);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Object wrapper = wrapperClass.getDeclaredConstructor().newInstance();
            wrapperClass.getMethod("setDataList", List.class).invoke(wrapper, objectList);

            // Create output directory if it doesn't exist
            File outputDirectory = new File(OUTPUT_DIRECTORY);
            if (!outputDirectory.exists()) {
                outputDirectory.mkdirs();
            }

            // Save the XML file to the output directory
            File outputFile = new File(outputDirectory, filename);
            marshaller.marshal(wrapper, outputFile);

            System.out.println("\n\n----- Data saved to Xml file: " + outputFile.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Failed to save data to XML file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
