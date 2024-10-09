package utility;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class extentReports {
	static ExtentTest test1;
	static ExtentReports extent;
	static LocalDateTime currentDateTime = LocalDateTime.now();
	static String formattedDateTime;
	File destination;
	static String dest;
	public static String mailfileName;
	public static String mailformattedDateTime;

	public static void initalizeExtent(String fileName) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy_HHmmss");
		formattedDateTime = currentDateTime.format(formatter);
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter("./Reports/" + fileName + "_" + formattedDateTime);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		mailfileName = fileName;
		mailformattedDateTime = formattedDateTime;
		htmlReporter.config().setTheme(Theme.DARK);
	}

	public static void createTest(String PBname, String testDesription) {
		test1 = extent.createTest(PBname + "_" + formattedDateTime, testDesription);

	}

	public static void logExtent(String infoMessage) {
		test1.log(Status.INFO, infoMessage);
	}

	public static void logPass(String passMessage) {
		test1.pass(passMessage);
	}

	public static void logFail(WebDriver driver, String fileName, String failMessage) throws Exception {
		extentReports.takeScreenShot(driver, fileName);
		test1.fail(failMessage, MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
	}

	public static void takeScreenShot(WebDriver driver, String fileName) throws Exception {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		dest = System.getProperty("user.dir") + "\\Screenshots\\" + fileName + "_" + formattedDateTime + ".png";

		File destination = new File(dest);
		FileUtils.copyFile(source, destination);
	}

	public static void flush() {
		extent.flush();
	}

}