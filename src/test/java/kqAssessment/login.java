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
import utility.getPasswordDijango;
import utility.jsonUtilites;

public class login {
    public String projectType;
    public WebDriver driver;
    static loginElements loginEle;
    String jsonPath = System.getProperty("user.dir") + "/data/loginCredentials.json";

    //! Constructor
    public login(WebDriver driver, String projectType) {
        this.projectType = projectType;
        this.driver = driver;
    }

    public void loginWeb() throws Exception {

        extentReports.createTest("Login_KqAssessment", "Test for login kq Assessment");
        try {
            Components.implicitlyWait(driver, 20);
            loginEle = new loginElements(driver, projectType);
            String userName = jsonUtilites.getJsonValue(0, jsonPath, "users", "username");
            String password;
            if (projectType.contains("react")) {
                password = getPassword.getUserPassword(userName);
            } else {
                password = getPasswordDijango.getUserPassword(userName);
            }
            loginEle.email().sendKeys(userName);
            loginEle.password().sendKeys(password);
            String captchaValue;
            captchaValue = loginEle.captchaValue().getText();
            loginEle.captcha().sendKeys(captchaValue);
            loginEle.loginBtn().click();
            Thread.sleep(2000);
            if (projectType.contains("react")) {
                otpReact(driver, userName, "login");
            } else {
                otpDijango(driver, userName, "login");
            }
            loginEle.verifyBtn().click();
            extentReports.logPass("Successfully login with Kq Assessment");
        } catch (Exception e) {
            extentReports.logFail(driver, "Kq_Login", "Failed to login with Kq assessment");
        }
    }

    public void forgetPassword() throws Exception {

        extentReports.createTest("ForgetPassword_KqAssessment", "Test for kq Assessment forget Password ");
        try {
            Components.implicitlyWait(driver, 20);
            loginElements loginEle = new loginElements(driver, projectType);
            String userName = jsonUtilites.getJsonValue(0, jsonPath, "users", "username");
            loginEle.email().sendKeys(userName);
            loginEle.forgetPass().click();
            Thread.sleep(2000);
            if (projectType.contains("react")) {
                otpReact(driver, userName, "forget password");
            } else {
                otpDijango(driver, userName, "forget password");
            }
            loginEle.verifyBtn().click();
            String newPassword = Components.shuffleString(jsonUtilites.getJsonValue(jsonPath, "newpass"));
            String confirmPassword = newPassword;
            loginEle.newPass().sendKeys(newPassword);
            loginEle.cnfrmPass().sendKeys(confirmPassword);
            jsonUtilites.updateJsonValue(jsonPath, "newpass", newPassword);
            jsonUtilites.updateJsonValue(jsonPath, "confrmpass", confirmPassword);
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
        if (projectType.contains("react")) {
            driver.findElement(By.xpath("//button[text()='Logout']")).click();
        } else {
            driver.navigate().refresh();
        }
    }

    //? Helper function to get the current OTP
    private static void otpReact(WebDriver driver, String username, String page)
            throws IOException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        String otp;
        System.out.println("page.toLowerCase().replaceAll(\" \", \"\").equals(\"forgetpassword\")>>>>"
                + page.toLowerCase().replaceAll(" ", "").equals("forgetpassword"));
        if (page.toLowerCase().replaceAll(" ", "").equals("login")) {
            otp = getPassword.getLoginEmailOtp(username);
        } else if (page.toLowerCase().replaceAll(" ", "").equals("forgetpassword")) {
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

    //? Helper function to get the current OTP
    private static void otpDijango(WebDriver driver, String username, String page)
            throws IOException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        String otp;
        if (page.toLowerCase().replaceAll(" ", "").equals("login")) {
            otp = getPasswordDijango.getLoginEmailOtp(username);
        } else if (page.toLowerCase().replaceAll(" ", "").equals("forgetpassword")) {
            otp = getPasswordDijango.getForgotPasswordMobileOtp(username);
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
