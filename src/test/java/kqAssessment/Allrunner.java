package kqAssessment;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import mailer.sendMail;
import seleGridUtility.seleniumGrid;
import utility.Components;
import utility.extentReports;

public class Allrunner {
    static login login = new login();
    static launchWeb launch = new launchWeb();
    static WebDriver driver;
    String projectType;

    @BeforeTest
    public void setup() {
        projectType = Components.getOption();
        extentReports.initalizeExtent("kqAssessment ");
    }

    @Test
    public void chromeRunner() throws Exception {
        driver = seleniumGrid.gridDriver("chrome");
        launch.launchKqWeb(driver, projectType);
        login.loginWeb(driver, projectType);
        login.logout(driver);
        login.forgetPassword(driver, projectType);
        login.loginWeb(driver, projectType);
    }

    @Test
    public void firefoxRunner() throws Exception {
        driver = seleniumGrid.gridDriver("firefox");
        launch.launchKqWeb(driver, projectType);
        login.loginWeb(driver, projectType);
        login.logout(driver);
        login.forgetPassword(driver, projectType);
        login.loginWeb(driver, projectType);
    }

    @Test
    public void tearDown() throws Exception {
        extentReports.flush();
        sendMail.sendMailToUser("dhinesh.p@knoawledgeq.com", driver);
    }

}

// public static void main(String[] args) throws Exception {
// String projectType = Components.getOption();
// driver = Components.startChrome();
// extentReports.initalizeExtent("kqAssessment ");
// launch.launchKqWeb(driver, projectType);
// login.loginWeb(driver, projectType);
// login.logout(driver);
// login.forgetPassword(driver, projectType);
// login.loginWeb(driver, projectType);
// extentReports.flush();
// sendMail.sendMailToUser("dhinesh.p@knoawledgeq.com", driver);
// }