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
    public String projectType;
    public WebDriver driver;

    public login(WebDriver driver, String projectType) {
        this.projectType = projectType;
        this.driver = driver;
    }

    static loginElements loginEle;
    String jsonPath = System.getProperty("user.dir") + "/data/loginCredentials.json";

    public void loginWeb() throws Exception {

        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println("Current method: " + methodName);
        extentReports.createTest("login_kq", "Test for login kq Assessment");
        try {
            Components.implicitlyWait(driver, 20);
            loginEle = new loginElements(driver, projectType);
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

    public void forgetPassword() throws Exception {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println("Current method: " + methodName);
        extentReports.createTest("ForgetPassword_KQ", "Test for kq Assessment forget Password ");
        try {
            Components.implicitlyWait(driver, 20);
            loginElements loginEle = new loginElements(driver, projectType);
            String userName = jsonUtilites.getJsonValue(0, jsonPath, "users", "username");
            loginEle.email().sendKeys(userName);
            loginEle.forgetPass().click();
            otp(driver, userName, "forget password");
            loginEle.verifyBtn().click();
            loginEle.newPass().sendKeys(jsonUtilites.getJsonValue(jsonPath, "newpass"));
            loginEle.cnfrmPass().sendKeys(jsonUtilites.getJsonValue(jsonPath, "confrmpass"));
            loginEle.submitBtn().click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[1]")).click();
            extentReports.logPass("Successfully reset password on forget password flow");
        } catch (Exception e) {
            e.printStackTrace();
            extentReports.logFail(driver, "Kq_forgot", "Failed to reset password on forget password flow");
        }

    }

    public void logout() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println("Current method: " + methodName);
        driver.findElement(By.xpath("//button[text()='Logout']")).click();
    }

    public static void otp(WebDriver driver, String username, String page)
            throws IOException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        String otp;
        if (page.toLowerCase().replaceAll(" ", "").equals("login")) {
            otp = getPassword.getLoginEmailOtp(username);
        } else if (page.toLowerCase().replaceAll(" ", "").equals("forgetpassword")) {
            System.out.println("otp else");
            otp = getPassword.getForgotPasswordMobileOtp(username);
        } else {
            otp = null;
        }
        wait.until(
                ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='OTP Verification']"))));
        char[] otpArray = otp.toCharArray();
        for (int i = 1; i <= 6; i++) {
            otp = String.valueOf(otpArray[i - 1]);
            loginEle.otpInput(i).sendKeys(otp);
        }
    }

}
