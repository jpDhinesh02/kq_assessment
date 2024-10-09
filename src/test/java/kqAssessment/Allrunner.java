package kqAssessment;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import mailer.sendMail;
import utility.Components;
import utility.extentReports;

public class Allrunner {
    static login login = new login();
    static launchWeb launch = new launchWeb();
    static WebDriver driver;

    public static void main(String[] args) throws Exception {
        driver = Components.startChrome();
        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = caps.getBrowserName();
        extentReports.initalizeExtent("kqAssessment " + browserName);
        launch.launchKqWeb(driver);
        login.loginWeb(driver);
        login.logout(driver);
        login.forgetPassword(driver);
        login.loginWeb(driver);
        extentReports.flush();
        sendMail.sendMailToUser("dhinesh.p@knowledgeq.com");
    }

}
