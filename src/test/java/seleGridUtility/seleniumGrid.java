package seleGridUtility;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class seleniumGrid {

    public static WebDriver gridDriver(String browserName) throws MalformedURLException, InterruptedException {
        runBatchFile();
        Thread.sleep(2500);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("platformName", Platform.WIN10);
        WebDriver driver = new RemoteWebDriver(new URL(" http://0.0.0.0:4444/wd/hub"), capabilities);
        return driver;
    }

    private static void runBatchFile() {
        String batPath[] = { System.getProperty("user.dir") + "//src//test//java//Utility//startHub.bat",
                System.getProperty("user.dir") + "//src//test//java//Utility//connectNode.bat" };
        System.out.println("batPath>>>>" + batPath);

        for (int i = 0; i <= batPath.length - 1; i++) {
            try {
                String batchFilePath = batPath[i];
                ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", batchFilePath);
                processBuilder.inheritIO();
                processBuilder.start();
                System.out.println("Batch file executed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}