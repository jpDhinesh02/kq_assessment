package utility;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
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

	// to take ss
	public static String takeScreenShot(WebDriver driver, String path, String fileName) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File destination = new File(path + "\\" + fileName + ".png");
			FileUtils.copyFile(source, destination);
			return "ScreenShot taken";
		} catch (Exception e) {

			return ("Exception while taking screenshot: " + e.getMessage());
		}
	}

	// short cut of implicitly wait
	@SuppressWarnings("deprecation")
	public static void implicitlyWait(WebDriver driver, int timeInSeconds) {
		driver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
	}

	// all explicitly wait
	// public static void waitForElementToBeClickable(WebDriver driver, By locator,
	// Duration timeOutInSeconds) {
	// WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
	// wait.until(ExpectedConditions.elementToBeClickable(locator));
	// }
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

	// public static void waitForElementToBePresent(WebDriver driver, By locator,
	// int time) {
	// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
	// WebElement element =
	// wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	// wait.until(ExpectedConditions.visibilityOf(element));
	// }
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

	// Text fields
	// For enter text in some field
	public static boolean enterText(WebDriver driver, String xpath, String text) {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			element.sendKeys(text);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// how many character will the field accept

	public static int getMaxLength(WebDriver driver, String xpath) {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			String maxLengthAttribute = element.getAttribute("maxlength");
			if (maxLengthAttribute == null) {
				return -1;
			} else {
				return Integer.parseInt(maxLengthAttribute);
			}
		} catch (NoSuchElementException e) {
			return -1;
		}
	}

	// this will help to clear the text that already presented

	public static boolean clearText(WebDriver driver, String xpath) {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			element.clear();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
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

	// for checking password field

	//
	// String pattern =
	// "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$"; ==This
	// pattern requires the password to contain at least:

	/*
	 * 1 lowercase letter 1 uppercase letter 1 number 1 special character (!@#$%^&*)
	 * No whitespaces A minimum length of 8 characters
	 */

	public static boolean testPasswordField(WebDriver driver, String xpath, String pattern) {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			element.sendKeys("123Abc!@#");
			String enteredText = element.getAttribute("value");
			element.clear();

			if (enteredText.matches(pattern)) {
				return true;
			}
			return false;
		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + xpath);
			return false;
		}
	}

	// for testing the phone number field
	public static String testPhoneNumberField(WebDriver driver, String xpath, String phonenumber) {

		String datatype = checkDataTypeAccepted(driver, xpath);
		int length = getMaxLength(driver, xpath);

		if (length == 10 && datatype.equals("NUMBER")) {
			return "pass";
		} else {
			return "MaxLength=" + length + "and DataType= " + datatype;
		}

	}
	// Radio button
	// to select a radio button

	public static void selectRadioButton(WebDriver driver, String xpath) {
		WebElement radioButton = driver.findElement(By.xpath(xpath));
		if (!radioButton.isSelected()) {
			radioButton.click();
		}
	}
	// to deselect a radio button

	public static void deselectRadioButton(WebDriver driver, String xpath) {
		WebElement radioButton = driver.findElement(By.xpath(xpath));
		if (radioButton.isSelected()) {
			radioButton.click();
		}
	}
	// Checkbox
	// to select a check box

	public static void selectCheckBox(WebDriver driver, String xpath) {
		WebElement checkBox = driver.findElement(By.xpath(xpath));
		if (!checkBox.isSelected()) {
			checkBox.click();
		}
	}

	// to deselect a check box

	public static void deselectCheckBox(WebDriver driver, String xpath) {
		WebElement checkBox = driver.findElement(By.xpath(xpath));
		if (checkBox.isSelected()) {
			checkBox.click();
		}
	}

	// to select a Multiple check box

	public static void selectMultipleCheckBoxes(WebDriver driver, String parentXpath) {
		ArrayList<WebElement> checkBoxes = (ArrayList<WebElement>) driver
				.findElements(By.xpath(parentXpath + "//input[@type='checkbox']"));
		for (WebElement checkBox : checkBoxes) {
			if (!checkBox.isSelected()) {
				checkBox.click();
			}
		}
	}

	// to de select a Multiple check box

	public static void deselectMultipleCheckboxes(WebDriver driver, String parentXpath) {
		ArrayList<WebElement> checkboxes = (ArrayList<WebElement>) driver
				.findElements(By.xpath(parentXpath + "//input[@type='checkbox']"));
		for (WebElement checkbox : checkboxes) {
			if (checkbox.isSelected()) {
				checkbox.click();
			}
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

	// Button

	public static void clickButton(WebDriver driver, String xpath) {
		WebElement button = driver.findElement(By.xpath(xpath));
		button.click();
	}

	public static void clickButton(WebDriver driver, WebElement element) {
		WebElement button = element;
		button.click();
	}
	// Link
	// To click the link

	public static void clickLink(WebDriver driver, String xpath) {
		WebElement link = driver.findElement(By.xpath(xpath));
		link.click();
	}

	// Image

	// to get a alt of a image

	public static String getImageAlt(WebDriver driver, String xpath) {
		WebElement image = driver.findElement(By.xpath(xpath));
		return image.getAttribute("alt");
	}

	// to click the image

	public static void clickImageByXPath(WebDriver driver, String xpath) {
		WebElement image = driver.findElement(By.xpath(xpath));
		image.click();
	}

	// Upload File

	public static void uploadFile(WebDriver driver, String xpath, String filePath) {
		WebElement uploadElement = driver.findElement(By.xpath(xpath));
		uploadElement.sendKeys(filePath);
	}

	// Data Picker

	public static void selectDateFromDatePicker(WebDriver driver, String xpath, String xpathofInnercalender,
			String date) {
		WebElement datePicker = driver.findElement(By.xpath(xpath));
		datePicker.click();

		ArrayList<WebElement> allDates = (ArrayList<WebElement>) driver.findElements(By.xpath(xpathofInnercalender));

		for (WebElement cell : allDates) {
			if (cell.getText().equals(date)) {
				cell.click();
				break;
			}
		}

	}

	// Slider
	public static void moveSlider(WebDriver driver, String xpath, int targetValue) {
		WebElement slider = driver.findElement(By.xpath(xpath));
		int width = slider.getSize().width;
		// int xCoord = slider.getLocation().x;
		Actions moveSlider = new Actions(driver);
		moveSlider.moveToElement(slider, ((targetValue * width) / 100), 0).click().build().perform();

	}

	// Toggle button
	public static void toggleButton(WebDriver driver, String xpath) {
		WebElement toggleButton = driver.findElement(By.xpath(xpath));
		if (toggleButton.isSelected()) {
			toggleButton.click();
		}
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

	// Accordian
	// To click the Accordian
	public static void clickAccordion(WebDriver driver, String xpath) {
		try {
			WebElement accordion = driver.findElement(By.xpath(xpath));
			accordion.click();
		} catch (Exception e) {
			System.out.println("Accordion not found: " + e.getMessage());
		}
	}

	// to check multiple accordian are open

	public static void checkSingleOpenAccordion(WebDriver driver, String accordionXpath, String accordionHeaderXpath) {
		ArrayList<WebElement> accordions = (ArrayList<WebElement>) driver.findElements(By.xpath(accordionXpath));
		for (WebElement accordion : accordions) {
			WebElement accordionHeader = accordion.findElement(By.xpath(accordionHeaderXpath));
			accordionHeader.click();
			for (WebElement otherAccordion : accordions) {
				if (!otherAccordion.equals(accordion)) {
					// WebElement otherAccordionHeader =
					// otherAccordion.findElement(By.xpath(accordionHeaderXpath));
					String otherAccordionState = otherAccordion.getAttribute("aria-expanded");
					if (otherAccordionState.equals("true")) {
						System.out.println("Error: Multiple accordions are open");
					}
				}
			}
		}
	}

	// Carousel
	// To get how many items are there in a corousel

	public static int countItemsInCarousel(WebDriver driver, String carouselXpath) {
		ArrayList<WebElement> carouselItems = (ArrayList<WebElement>) driver.findElements(By.xpath(carouselXpath));
		return carouselItems.size();
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

	// Auto complete
	// to check auto complete is enabled or not

	public static boolean isAutocompleteEnabled(WebDriver driver, String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		return element.getAttribute("autocomplete").equalsIgnoreCase("on");
	}

	// Search
	public static void searchAndPressEnter(WebDriver driver, String xpath, String searchText) {
		WebElement searchBox = driver.findElement(By.xpath(xpath));
		searchBox.clear();
		searchBox.sendKeys(searchText);
		searchBox.sendKeys(Keys.ENTER);
	}

	// Pagination
	public static void clickPage(WebDriver driver, int pageNumber) {
		// Find the xpath of the pagination element containing all the page numbers
		WebElement pagination = driver.findElement(By.xpath("//div[@class='pagination']"));

		// Find all the page numbers in the pagination element
		ArrayList<WebElement> pages = (ArrayList<WebElement>) pagination.findElements(By.xpath(".//a"));

		// Loop through the page numbers to find the desired page
		for (WebElement page : pages) {
			// Get the text of the page number
			String pageText = page.getText();

			// If the page number text matches the user input, click on it
			if (pageText.equals(Integer.toString(pageNumber))) {
				page.click();
				break;
			}
		}
	}

	// Alerts

	public static void switchToAlert(WebDriver driver) {
		try {
			// Alert alert = driver.switchTo().alert();
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

	// Video
	// to check video is play able or not
	public static boolean isVideoPlayable(WebDriver driver, String xpath) {
		WebElement video = driver.findElement(By.xpath(xpath));
		return ((JavascriptExecutor) driver).executeScript("return arguments[0].paused;", video).equals(false);
	}

	// to check video is presented or not

	public static boolean isVideoPresent(WebDriver driver, String xpath) {
		try {
			driver.findElement(By.xpath(xpath));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// switch the focus to the which ever tab you want
	public static void switchToTab(WebDriver driver, int tabIndex) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabIndex));
	}

	// I frame
	// switching the focus to the i frame using index
	public static void switchToiFrame(WebDriver driver, int frameIndex) {
		driver.switchTo().frame(frameIndex);
	}

	// switching to i frame using the xpath
	public static void switchToiFrame(WebDriver driver, String xpath) {
		WebElement iFrameElement = driver.findElement(By.xpath(xpath));
		driver.switchTo().frame(iFrameElement);
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
}