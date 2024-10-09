package utility;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class appiumComponents {

	private AppiumDriverLocalService service;
	private AndroidDriver driver;

	public appiumComponents() // constructor
	{
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"C://Users//Dhinesh.P//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
				.withIPAddress("172.25.10.171").usingPort(4723).build();
	}

	public void startAppium() {
		try {
			String batchFilePath = "C:\\Users\\Dhinesh.P\\eclipse\\Eclipse\\Dv_Mobile\\src\\test\\java\\utility\\appium.bat";
			Process process = Runtime.getRuntime().exec(batchFilePath);
			int exitCode = process.waitFor();
			System.out.println("Batch file executed with exit code: " + exitCode);

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void stopAppium() {
		service.stop();
	}

	public AndroidDriver launchDriver(String apkPath) throws MalformedURLException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Android Device");
		options.setApp(apkPath);
		driver = new AndroidDriver(new URL("http://172.25.10.171:4723"), options);
		return driver;
	}

	public void quitDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

	// Working Scroll Method
	public void scrollScreenEnd(AppiumDriver driver) {
		driver.findElement(AppiumBy
				.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000000)"));
	}

	public void scrollScreenBegining(AppiumDriver driver) {

		driver.findElement(AppiumBy
				.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(100000)"));
	}

	public static void scrolltotext(AppiumDriver driver) {
		String scrollElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Date Of Birth\").instance(0))";

		driver.findElement(AppiumBy.androidUIAutomator(scrollElement));
	}

	public void scroll(AppiumDriver driver) {
		boolean canScrollMore = false;
		do {
			canScrollMore = (boolean) driver.executeScript("mobile: scrollGesture", ImmutableMap.of("left", 100, "top",
					100, "width", 200, "height", 200, "direction", "down", "percent", 3.0

			));
		} while (canScrollMore);
	}

	public static void scrollVertical(AppiumDriver driver, int scrollAmount) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, arguments[0]);", scrollAmount);
	}

	public static void scrollIntoView(AppiumDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void DragScreen(AppiumDriver driver, WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "endX", 100, "endY", 100));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Scroll not happening>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
	}

}
