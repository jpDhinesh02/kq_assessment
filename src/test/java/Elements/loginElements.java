package Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class loginElements {
    public WebDriver driver;
    String projectType;

    public loginElements(WebDriver driver, String ProjectType) {
        this.driver = driver;
        this.projectType = ProjectType;
    }

    public WebElement email() {
        return driver.findElement(By.xpath("//input[@type=\"email\"]"));
    }

    public WebElement password() {
        return driver.findElement(By.xpath("//input[@type=\"password\"]"));
    }

    public WebElement captchaValue() {
        if (projectType.toLowerCase().contains("python")) {

            return driver.findElement(By.xpath("//div[@id=\"captcha_generation\"]"));
        } else if (projectType.toLowerCase().replaceAll(" ", "").contains("react")) {
            return driver.findElement(By.xpath("//span[@class=\"captcha-value\"]"));
        } else {
            return null;
        }
    }

    public WebElement captcha() {
        return driver.findElement(By.xpath("//input[@type=\"text\"]"));
    }

    public WebElement loginBtn() {
        return driver.findElement(By.xpath("//button[text()='Login']"));
    }

    public WebElement forgetPass() {

        if (projectType.toLowerCase().contains("python")) {
            return driver.findElement(By.xpath("//a[text()='Forgot Password?']"));
        } else if (projectType.toLowerCase().replaceAll(" ", "").contains("react")) {
            return driver.findElement(By.xpath("//p[text()='Forgot Password?']"));
        } else {
            return null;
        }
    }

    public WebElement otpInput(int index) {
        return driver.findElement(By.xpath("(//input[@type=\"text\"])[" + index + "]"));
    }
    // public WebElement otpInput() {
    // return driver.findElement(By.xpath("//input[@type=\"text\"][1]"));
    // }

    public WebElement verifyBtn() {
        return driver.findElement(By.xpath("//button[text()='Verify']"));
    }

    public WebElement newPass() {
        return driver.findElement(By.xpath("//input[@type=\"password\"][1]"));
    }

    public WebElement cnfrmPass() {
        return driver.findElement(By.xpath("(//input[@type=\"password\"])[2]"));
    }

    public WebElement submitBtn() {
        return driver.findElement(By.xpath("//button[text()='Submit']"));
    }
}
