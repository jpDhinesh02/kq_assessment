package kqAssessment;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import mailer.sendMail;
import utility.Components;
import utility.extentReports;

public class Allrunner {
    String projectType;
    WebDriver driver;
    login login;
    launchWeb launch;

    @BeforeTest
    public void setup() {
        projectType = Components.getOption("Python", "React").toLowerCase();
        driver = Components.startChrome();
        login = new login(driver, projectType);
        launch = new launchWeb(driver, projectType);
        extentReports.initalizeExtent("kqAssessment_" + projectType);
    }

    @Test
    public void Runner() throws Exception {
        launch.launchKqWeb();
        login.loginWeb();
        login.logout();
        login.forgetPassword();
        login.loginWeb();
    }

    @AfterTest
    public void tearDown() throws Exception {
        extentReports.flush();
        // sendMail.sendMailToUser("dhinesh.p@knowledgeq.com", driver);
        driver.quit();
    }

}