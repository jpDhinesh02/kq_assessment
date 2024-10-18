package kqAssessment;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import utility.extentReports;

public class launchWeb {
	public String projectType;
	public WebDriver driver;

	public launchWeb(WebDriver driver, String projectType) {
		this.projectType = projectType;
		this.driver = driver;
	}

	public void launchKqWeb() throws Exception {
		extentReports.createTest("Launch_KqAssessment", "Test for launch Application");
		try {
			String[] urlLinks;
			System.err.println("projectType>>>>" + projectType);
			if (projectType.contains("python")) {
				urlLinks = new String[] { "http://172.26.10.5:8000/" };
			} else if (projectType.contains("react")) {
				urlLinks = new String[] { "https://172.26.10.5:3000/" };
			} else {
				throw new Exception("Unsupported project type: " + projectType);
			}

			for (int i = 0; i <= urlLinks.length - 1; i++) {
				driver.get(urlLinks[i]);
				driver.manage().window().maximize();
				try {
					driver.findElement(By.id("details-button")).click();
					driver.findElement(By.id("proceed-link")).click();
				} catch (Exception e) {
				}
				if (i < urlLinks.length - 1) {
					((JavascriptExecutor) driver).executeScript("window.open()");
					ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
					driver.switchTo().window(tabs.get(tabs.size() - 1));
				}
			}
			extentReports.logPass("Successfully launch Kq Assessment");
		} catch (Exception e) {
			e.printStackTrace();
			extentReports.logFail(driver, "Launch_Kq", "Failed to Launch Kq application");
		}

	}
}
