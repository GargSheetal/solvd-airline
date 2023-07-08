package solvd.airline.output;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonParser {
    private static final String OUTPUT_DIRECTORY = "src/main/resources/output";
    
    public static void saveDataToJson(Object data, String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File outputDirectory = new File(OUTPUT_DIRECTORY);
            if (!outputDirectory.exists()) {
                outputDirectory.mkdirs();
            }

            File outputFile = new File(outputDirectory, filename);
            objectMapper.writeValue(outputFile, data);

            System.out.println("\n\n... data saved to JSON file: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("\n... failed to save data to JSON file: " + e.getMessage());
        }
    }

    public static <T> List<T> loadDataFromJson(String filename, Class<T> valueType) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File inputFile = new File(OUTPUT_DIRECTORY, filename);
            return objectMapper.readValue(inputFile, objectMapper.getTypeFactory().constructCollectionType(List.class, valueType));
        } catch (IOException e) {
            System.out.println("\n... failed to load data from JSON file: " + e.getMessage());
        }
        return null;
    }
}