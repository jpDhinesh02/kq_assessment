package utility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import javax.swing.JOptionPane;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Components {

	// Basic Set up
	public static WebDriver startChrome() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--ignore-certificate-errors");
		WebDriver chromeDriver = new ChromeDriver(options);
		return chromeDriver;

	}

	public static WebDriver startChromeHeadless() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--remote-allow-origins=*");
		WebDriver chromeDriver = new ChromeDriver(options);
		return chromeDriver;

	}

	public static WebDriver startFirefox() {

		WebDriver firefoxDriver = new FirefoxDriver();
		return firefoxDriver;

	}
	// thread sleep

	public static void waitFor(int timeInSeconds) {
		timeInSeconds = timeInSeconds * 1000;
		try {
			Thread.sleep(timeInSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timeInSeconds = 0;
	}

	// to maximize the window
	public static void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	// to launch the page
	public static void launchPage(WebDriver driver, String link) {
		driver.get(link);
	}

	public static void navigateToPage(WebDriver driver, String url) {
		driver.navigate().to(url);
	}

	// To Navigate BackWard and Forward
	public static void navigateForward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void navigateBackward(WebDriver driver) {
		driver.navigate().back();
	}

	// To Refresh the Page
	public static void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	// to quit all windows
	public static void quitBrowser(WebDriver driver) {
		driver.quit();
	}

	// to close the currently focused window
	public static void closeBrowser(WebDriver driver) {
		driver.close();
	}

	public static String getTextFromElement(WebDriver driver, By locator) {
		WebElement element = driver.findElement(locator);
		return element.getText();
	}

	public static void implicitlyWait(WebDriver driver, int timeInSeconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSeconds));
	}

	public static void waitForElementToBeClickable(WebDriver driver, By by, int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public static void waitForElementToBeVisible(WebDriver driver, By locator, Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static void waitForElementToBeVisible(WebDriver driver, By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static void waitForElementToBeVisible(WebDriver driver, String xpath, int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	public static void waitForElementToBeVisible(WebDriver driver, WebElement element, int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForElementToBePresent(WebDriver driver, WebElement element, int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForElementToBeVisibleinDOM(WebDriver driver, String xpath, int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
	}

	public static void waitForElementToBeInvisible(WebDriver driver, By locator, Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public static void waitForElementToBeInvisible(WebDriver driver, WebElement elem, int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.invisibilityOf(elem));
	}

	public static void waitForElementToBeSelected(WebDriver driver, By locator, Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeSelected(locator));
	}

	public static void waitForTextToBePresentInElement(WebDriver driver, By locator, String text,
			Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
	}

	public static void waitForAttributeToBe(WebDriver driver, By locator, String attribute, String value,
			Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.attributeToBe(locator, attribute, value));
	}

	public static void waitForAttributeContains(WebDriver driver, By locator, String attribute, String value,
			Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
	}

	public static void waitForNumberOfElementsToBeMoreThan(WebDriver driver, By locator, int count,
			Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, count));
	}

	public static void waitForNumberOfElementsToBeLessThan(WebDriver driver, By locator, int count,
			Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.numberOfElementsToBeLessThan(locator, count));
	}

	public static void waitForNumberOfElementsToBe(WebDriver driver, By locator, int count, Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.numberOfElementsToBe(locator, count));
	}
	

	// For checking the data type of the field
	public static String checkDataTypeAccepted(WebDriver driver, String xpath) {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			element.sendKeys("123abc!@#");
			String enteredText = element.getAttribute("value");
			element.clear();
			if (enteredText.matches("^[0-9]+$")) {
				return "NUMBER";
			} else if (enteredText.matches("^[a-zA-Z]+$")) {
				return "STRING";
			} else if (enteredText.matches("^[!@#$%^&*()]+$")) {
				return "SPECIAL_CHARACTERS";
			}
			return "VARCHAR";
		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + xpath);
			return "Element not found";
		}
	}

	// Dropdown
	// to select the dropdown
	public static void selectOptionFromDropdown(WebDriver driver, String xpath, String option) {
		WebElement dropdown = driver.findElement(By.xpath(xpath));
		Select select = new Select(dropdown);
		select.selectByVisibleText(option);
	}

	// to de select the dropdown
	public static void deselectDropdown(WebDriver driver, String xpath) {
		WebElement dropdown = driver.findElement(By.xpath(xpath));
		Select select = new Select(dropdown);
		select.deselectAll();
	}

	// Slider
	public static void moveSlider(WebDriver driver, String xpath, int targetValue) {
		WebElement slider = driver.findElement(By.xpath(xpath));
		int width = slider.getSize().width;
		// int xCoord = slider.getLocation().x;
		Actions moveSlider = new Actions(driver);
		moveSlider.moveToElement(slider, ((targetValue * width) / 100), 0).click().build().perform();

	}

	// popup window

	public static void switchToPopupWindow(WebDriver driver) {
		String mainWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(mainWindowHandle)) {
				driver.switchTo().window(currentWindowHandle);
			}
		}

	}

	// tooltip

	// to check wheather the tooltip is presented or not and what is the text inside
	// the tooltip

	public static void checkAndPrintTooltipText(WebDriver driver, String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
		if (element.getAttribute("title") != null) {
			System.out.println("Tooltip present. Text: " + element.getAttribute("title"));
		} else {
			System.out.println("Tooltip not present");
		}
	}

	// Pagination
	public static void clickPage(WebDriver driver, int pageNumber) {
		WebElement pagination = driver.findElement(By.xpath("//div[@class='pagination']"));
		ArrayList<WebElement> pages = (ArrayList<WebElement>) pagination.findElements(By.xpath(".//a"));
		for (WebElement page : pages) {
			String pageText = page.getText();
			if (pageText.equals(Integer.toString(pageNumber))) {
				page.click();
				break;
			}
		}
	}

	// Alerts

	public static void switchToAlert(WebDriver driver) {
		try {
			driver.switchTo().alert();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert is not present.");
		}
	}

	// To accept the alert
	public static void acceptAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	// to dismiss the alert
	public static void dismisAlert(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert not found: " + e.getMessage());
		}
	}

	// to send the text in the alert box
	public static void sendTextToAlert(WebDriver driver, String text) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(text);
			alert.accept();
		} catch (NoAlertPresentException ex) {
			System.out.println("No alert is present");
		}
	}

	public static String stringDialouge(String string) {

		String a = JOptionPane.showInputDialog(null, string);
		return a;
	}

	// Method for JOption Int
	public static int intDialouge(String string) {
		String a = JOptionPane.showInputDialog(null, string);
		int a1 = Integer.parseInt(a);
		return a1;
	}

	public static String getXclPath(String Defaultpath) {
		String path;
		int choice = JOptionPane.showConfirmDialog(null, "Is the Data path is " + Defaultpath, "Confirmation",
				JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION) {
			path = Defaultpath;
		} else {
			path = JOptionPane.showInputDialog("Enter Data Excel path");
		}
		return path;
	}

	public static String getOption(String... options) {
		String projectType;
		int choice = JOptionPane.showOptionDialog(null, "Python Or React", "Select Project Type",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
		if (choice >= 0 && choice < options.length) {
			projectType = options[choice];
		} else {
			projectType = null;
		}

		return projectType;
	}

	public static void interactWithElement(Runnable elementInteraction) throws Exception {
		int attempts = 0;
		while (attempts < 3) {
			try {
				elementInteraction.run();
				return;
			} catch (StaleElementReferenceException | NoSuchElementException e) {
				attempts++;
				Thread.sleep(3000);
			}
		}
		throw new Exception("Element interaction failed after multiple attempts");
	}

	public static void retryOnStaleElement(Runnable elementInteraction) throws Exception {
		int attempts = 0;
		boolean success = false;
		while (attempts < 3 && !success) {
			try {
				elementInteraction.run();
				success = true;
			} catch (StaleElementReferenceException e) {
				attempts++;
				if (attempts == 3) {
					throw e;
				}
			}
		}
	}

	public static String shuffleString(String input) {
		char[] characters = input.toCharArray();
		Random random = new Random();
		for (int i = 0; i < characters.length; i++) {
			int randomIndex = random.nextInt(characters.length);
			char temp = characters[i];
			characters[i] = characters[randomIndex];
			characters[randomIndex] = temp;
		}

		return new String(characters);
	}
}