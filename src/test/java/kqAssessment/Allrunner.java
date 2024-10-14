package kqAssessment;

import org.openqa.selenium.WebDriver;
import mailer.sendMail;
import utility.Components;
import utility.extentReports;

public class Allrunner {
    static login login = new login();
    static launchWeb launch = new launchWeb();
    static WebDriver driver;

    public static void main(String[] args) throws Exception {
        String projectType = Components.getOption();
        driver = Components.startChrome();
        extentReports.initalizeExtent("kqAssessment ");
        launch.launchKqWeb(driver, projectType);
        login.loginWeb(driver, projectType);
        login.logout(driver);
        login.forgetPassword(driver, projectType);
        login.loginWeb(driver, projectType);
        extentReports.flush();
        sendMail.sendMailToUser("dhinesh.p@knoawledgeq.com", driver);
    }

}
