package mailer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import utility.Components;
import utility.extentReports;

public class sendMail {

    static String BASE_URL = "http://localhost:3000/mail";

    public static void sendMailToUser(String toMail, WebDriver driver) throws IOException, InterruptedException {
        runBatchFile();
        isServerRunning(driver);
        RestAssured.config = RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation());
        String reportPath = System.getProperty("user.dir")+"/Reports/"+extentReports.reportFileName+ ".html";

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
        Components.printColorful(responseBody   ,"red_bold","yellow_background");
        killProcessOnPort();
    }

    //? Helper method to run a server
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

    //? Helper method to kill the process
    private static void killProcessOnPort() {
        try {
            int port = 3000;
            String command = "cmd.exe /c netstat -ano | findstr :" + port;
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            if (line != null) {
                String[] tokens = line.trim().split("\\s+");
                String pid = tokens[tokens.length - 1];

                String killCommand = "taskkill /F /PID " + pid;
                Runtime.getRuntime().exec(killCommand);
                Components.printColorful("Killed process using port " + port + " with PID: " + pid, "red_bold","yellow_background");
            } else {
                Components.printColorful("No process is using port " + port, "red_bold","yellow_background");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //? Helper method to check if server is running
    private static void isServerRunning(WebDriver driver) throws InterruptedException {
        if (driver != null) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.open();");
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
        } else {
            driver = Components.startChrome();
            driver.manage().window().maximize();
        }
        driver.get(BASE_URL);
        while (driver.findElement(By.xpath("//pre[text()='Cannot GET /mail']")).isDisplayed()) {
            try {
                break;
            } catch (Exception e) {
                Thread.sleep(2000);
                driver.navigate().refresh();
            }
        }
        driver.quit();
    }
}
