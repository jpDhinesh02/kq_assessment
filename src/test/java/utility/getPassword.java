package utility;

import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

public class getPassword {
 
   

    //! Common method to retrieve OTPs and password
    private static JsonNode getApiResponse(String email) throws IOException, InterruptedException {
        String BASE_URL = "https://172.26.10.5:3001";
        Thread.sleep(1000);
        RestAssured.config = RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation());
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\"email\": \"" + email + "\"}")
                .when()
                .post(BASE_URL + "/v1/user/login/getOtpForUser")
                .then()
                .extract()
                .response();

        String responseBody = response.asString();
        int statusCode = response.getStatusCode();

        if (statusCode != 200) {
            Components.printColorful("API call failed with status code: " + statusCode, "black_bold","white_Background");
            return null; // or throw an exception
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(responseBody).path("results");
    }

    public static String getLoginMobileOtp(String email) throws IOException, InterruptedException {
        JsonNode results = getApiResponse(email);
        if (results == null || results.isMissingNode()) {
            Components.printColorful("No results found in response.", "black_bold","white_Background");
            return null;
        }
        return results.path("loginMobileOtp").asText().trim();
    }

    public static String getLoginEmailOtp(String email) throws IOException, InterruptedException {
        JsonNode results = getApiResponse(email);

        if (results == null || results.isMissingNode()) {
            Components.printColorful("No results found in response.", "black_bold","white_Background");
            return null;
        }
        return results.path("loginEmailOtp").asText().trim();
    }

    public static String getUserPassword(String email) throws IOException, InterruptedException {
        JsonNode results = getApiResponse(email);
        if (results == null || results.isMissingNode()) {
            Components.printColorful("No results found in response.", "black_bold","white_Background");
            return null;
        }
        return results.path("password").asText().trim();
    }

    public static String getForgotPasswordMobileOtp(String email) throws IOException, InterruptedException {
        JsonNode results = getApiResponse(email);
        if (results == null || results.isMissingNode()) {
            Components.printColorful("No results found in response.", "black_bold","white_Background");
            return null;
        }
        return results.path("forgotPasswordMobileOtp").asText().trim();
    }

    public static String getForgotPasswordEmailOtp(String email) throws IOException, InterruptedException {
        JsonNode results = getApiResponse(email);
        if (results == null || results.isMissingNode()) {
            Components.printColorful("No results found in response.", "black_bold","white_Background");
            return null;
        }
        return results.path("forgotPasswordEmailOtp").asText().trim();
    }
}
