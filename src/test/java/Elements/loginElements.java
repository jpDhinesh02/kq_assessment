package Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class loginElements {
    public WebDriver driver;

    public loginElements(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement email() {
        return driver.findElement(By.xpath("//input[@type=\"email\"]"));
    }

    public WebElement password() {
        return driver.findElement(By.xpath("//input[@type=\"password\"]"));
    }

    public WebElement captchaValue() {
        return driver.findElement(By.xpath("//span[@class=\"captcha-value\"]"));
    }
    public WebElement captchaValue2() {
        return driver.findElement(By.xpath("//div[@id=\"captcha_generation\"]"));
    }

    public WebElement captcha() {
        return driver.findElement(By.xpath("//input[@type=\"text\"]"));
    }

    public WebElement loginBtn() {
        return driver.findElement(By.xpath("//button[text()='Login']"));
    }

    public WebElement forgetPass() {
        return driver.findElement(By.xpath("//p[text()='Forgot Password?']"));
    }

    public WebElement otpInput(int index) {
        return driver.findElement(By.xpath("//input[@type=\"text\"][" + index + "]"));
    }
    // public WebElement otpInput() {
    //     return driver.findElement(By.xpath("//input[@type=\"text\"][1]"));
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
