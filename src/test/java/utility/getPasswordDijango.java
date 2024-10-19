package utility;

import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

public class getPasswordDijango {
 
    //! Common method to retrieve OTPs and password
    private static JsonNode getApiResponse(String email) throws IOException, InterruptedException {
        String BASE_URL = "http://172.26.10.5:8000";
        Thread.sleep(1000);
        RestAssured.config = RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation());
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\"email\": \"" + email + "\"}")
                .when()
                .post(BASE_URL + "/v1/user/data")
                .then()
                .extract()
                .response();

        String responseBody = response.asString();
        int statusCode = response.getStatusCode();

        if (statusCode != 200) {
            System.out.println("API call failed with status code: " + statusCode);
            return null; // or throw an exception
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(responseBody).path("results");
    }

    public static String getLoginMobileOtp(String email) throws IOException, InterruptedException {
        JsonNode results = getApiResponse(email);

        if (results == null || results.isMissingNode()) {
            System.out.println("No results found in response.");
            return null;
        }
        return results.path("loginMobileOtp").asText().trim();
    }

    public static String getLoginEmailOtp(String email) throws IOException, InterruptedException {
        JsonNode results = getApiResponse(email);

        if (results == null || results.isMissingNode()) {
            System.out.println("No results found in response.");
            return null;
        }
        return results.path("EmailOTP").asText().trim();
    }

    public static String getUserPassword(String email) throws IOException, InterruptedException {
        JsonNode results = getApiResponse(email);
        if (results == null || results.isMissingNode()) {
            System.out.println("No results found in response.");
            return null;
        }
        return results.path("Password").asText().trim();
    }

    public static String getForgotPasswordMobileOtp(String email) throws IOException, InterruptedException {
        JsonNode results = getApiResponse(email);
        if (results == null || results.isMissingNode()) {
            System.out.println("No results found in response.");
            return null;
        }
        return results.path("forgotOtp").asText().trim();
    }

    public static String getForgotPasswordEmailOtp(String email) throws IOException, InterruptedException {
        JsonNode results = getApiResponse(email);
        if (results == null || results.isMissingNode()) {
            System.out.println("No results found in response.");
            return null;
        }
        return results.path("forgotPasswordEmailOtp").asText().trim();
    }
}
