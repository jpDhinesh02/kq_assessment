package kqAssessment;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import utility.extentReports;

public class launchWeb {

	public void launchKqWeb(WebDriver driver, String projectType) throws Exception {
		extentReports.createTest("Launch_Kq_Assessment", "Test for launch Application");
		try {
			String[] urlLinks;
			System.err.println("projectType>>>>"+projectType);
			System.err.println(projectType.toLowerCase().contains("python"));
			if (projectType.toLowerCase().contains("python")) {
				urlLinks = new String[] { "https://172.26.10.5:3001/", "http://172.26.10.5:8000/" };
			} else if (projectType.toLowerCase().contains("react")) {
				urlLinks = new String[] { "https://172.26.10.5:3001/", "https://172.26.10.5:3000/" };
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
