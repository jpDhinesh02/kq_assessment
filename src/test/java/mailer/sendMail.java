package mailer;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import utility.Components;
import utility.extentReports;

public class sendMail {

    public static void sendMailToUser(String toMail) throws IOException, InterruptedException {
        WebDriver driver = Components.startChromeHeadless();
        runBatchFile();
        String BASE_URL = "http://localhost:3000/mail";
        driver.get(BASE_URL);

        while (driver.findElement(By.xpath("//pre[text()='Cannot GET /mail']")).isDisplayed()) {
            try {
               break;
            } catch (Exception e) {
                Thread.sleep(2000);
            }
        }

        RestAssured.config = RestAssured.config().sslConfig(
                new SSLConfig().relaxedHTTPSValidation());
        String reportPath = System.getProperty("user.dir") + "/Reports/" + extentReports.mailfileName + "_"
                + extentReports.mailformattedDateTime + ".html";

        String jsonBody = "{"
                + "\"to\": \"" + toMail + "\","
                + "\"subject\": \"Selenium Test Complete\","
                + "\"text\": \"The Selenium test has completed successfully.\","
                + "\"attachments\": [{"
                + "\"path\": \"" + reportPath.replace("\\", "\\\\") + "\""
                + "}]"
                + "}";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post(BASE_URL)
                .then()
                .extract()
                .response();
        String responseBody = response.asString();
    }

    private static void runBatchFile() {
        try {
            String batchFilePath = System.getProperty("user.dir") + "\\src\\test\\java\\mailer\\node_runner.bat";
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", batchFilePath);
            processBuilder.inheritIO();
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
