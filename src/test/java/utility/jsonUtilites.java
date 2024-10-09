package utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class jsonUtilites {
    public static String getJsonValue(String jsonPath, String keyValue) throws IOException {
        String jsonContent = new String(Files.readAllBytes(Paths.get(jsonPath)));
        ObjectMapper mapper = new ObjectMapper();
        JsonNode data = mapper.readTree(jsonContent);
        return data.get(keyValue).asText();
    }

    public static String getJsonValue(int index, String jsonPath, String arrayName, String keyValue) throws Exception {
        String jsonContent = new String(Files.readAllBytes(Paths.get(jsonPath)));
        ObjectMapper mapper = new ObjectMapper();
        JsonNode data = mapper.readTree(jsonContent);
        JsonNode users = data.get(arrayName);
        JsonNode user = users.get(index);
        return user.get(keyValue).asText();
    }
}
