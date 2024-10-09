package kqAssessment;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Elements.loginElements;
import utility.Components;
import utility.extentReports;
import utility.getPassword;
import utility.jsonUtilites;

public class login {
    String jsonPath = System.getProperty("user.dir") + "/data/loginCredentials.json";

    public void loginWeb(WebDriver driver) throws Exception {
        extentReports.createTest("login_kq", "Test for login kq Assessment");
        try {
            Components.implicitlyWait(driver, 60);
            loginElements loginEle = new loginElements(driver);
            String userName = jsonUtilites.getJsonValue(0, jsonPath, "users", "username");
            String password = getPassword.getUserPassword(userName);
            loginEle.email().sendKeys(userName);
            loginEle.password().sendKeys(password);
            String captchaValue;
            captchaValue = loginEle.captchaValue().getText();
            loginEle.captcha().sendKeys(captchaValue);
            loginEle.loginBtn().click();
            otp(driver, userName, "login");
            loginEle.verifyBtn().click();
            extentReports.logPass("Successfully login with Kq Assessment");
        } catch (Exception e) {
            extentReports.logFail(driver, "Kq_Login", "Failed to login with Kq assessment");
        }
    }

    public void forgetPassword(WebDriver driver) throws Exception {
        extentReports.createTest("ForgetPassword_KQ", "Test for kq Assessment forget Password ");
        try {
            Components.implicitlyWait(driver, 60);
            loginElements loginEle = new loginElements(driver);
            String userName = jsonUtilites.getJsonValue(0, jsonPath, "users", "username");
            loginEle.email().sendKeys(userName);
            loginEle.forgetPass().click();
            Thread.sleep(5000);
            otp(driver, userName, "forget password");
            loginEle.verifyBtn().click();
            loginEle.newPass().sendKeys(jsonUtilites.getJsonValue(jsonPath, "newpass"));
            loginEle.cnfrmPass().sendKeys(jsonUtilites.getJsonValue(jsonPath, "confrmpass"));
            loginEle.submitBtn().click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[1]")).click();
            extentReports.logPass("Successfully reset password on forget password flow");
        } catch (Exception e) {
            extentReports.logFail(driver, "Kq_forgot", "Failed to reset password on forget password flow");
        }

    }

    public void logout(WebDriver driver) {
        driver.findElement(By.xpath("//button[text()='Logout']")).click();
    }

    public static void otp(WebDriver driver, String username, String page) throws IOException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        String otp;
        if (page.toLowerCase().trim().equals("login")) {
            otp = getPassword.getLoginEmailOtp(username);
        } else {
            otp = getPassword.getForgotPasswordMobileOtp(username);
        }
        wait.until(
                ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[text()='OTP Verification']"))));
        loginElements loginEle = new loginElements(driver);
        char[] otpArray = otp.toCharArray();
        for (int i = 1; i <= 6; i++) {
            otp = String.valueOf(otpArray[i - 1]);
            loginEle.otpInput(i).sendKeys(otp);
        }
    }
}
