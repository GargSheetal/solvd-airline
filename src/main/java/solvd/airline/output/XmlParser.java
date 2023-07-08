package solvd.airline.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlParser {

	public static <T> T parseXml(File file, Class<T> clazz) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return clazz.cast(jaxbUnmarshaller.unmarshal(file));
	}

	public static <T> void writeXml(File file, T object) {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(object.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
			jaxbMarshaller.marshal(object, file);
			System.out.println("\n\n----- Data saved to Xml file: " + file.getAbsolutePath());
		} catch (JAXBException e) {
			e.printStackTrace();
			System.out.println("\nFailed to save data to Xml file: " + e.getMessage());
		}
	}
	
//	public static <T> void saveListToXml(List<T> dataList, String filename) {
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(dataList.get(0).getClass());
//            Marshaller marshaller = jaxbContext.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//            File outputFile = new File(filename);
//            FileWriter fileWriter = new FileWriter(outputFile);
//
//            marshaller.marshal(dataList, fileWriter);
//
//            System.out.println("\n\n----- Data saved to Xml file: " + outputFile.getAbsolutePath());
//        } catch (JAXBException | IOException e) {
//            System.out.println("\nFailed to save data to XML file: " + e.getMessage());
//        }
//    }
	
	public static <T> void saveListToXml(List<T> objectList, String filename) {
        try {
            Object wrapper = createWrapper(objectList);

            File outputFile = new File(filename);

            JAXBContext jaxbContext = JAXBContext.newInstance(wrapper.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(wrapper, outputFile);

            System.out.println("\n\n----- Data saved to Xml file: " + outputFile.getAbsolutePath());
        } catch (JAXBException e) {
            System.out.println("Failed to save data to XML file: " + e.getMessage());
        }
    }

    private static <T> Object createWrapper(List<T> objectList) {
        try {
            Class<?> wrapperClass = Class.forName(objectList.get(0).getClass().getPackage().getName() + ".Wrapper");
            Object wrapper = wrapperClass.getDeclaredConstructor().newInstance();
            wrapperClass.getMethod("setDataList", List.class).invoke(wrapper, objectList);
            return wrapper;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid object list or wrapper class: " + e.getMessage());
        }
    }

}
