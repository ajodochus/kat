import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.firefox.FirefoxDriver as FirefoxDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import org.openqa.selenium.ie.InternetExplorerDriver as InternetExplorerDriver
import org.testng.Assert as Assert
import org.openqa.selenium.Cookie as Cookie
import org.junit.After as After
import org.openqa.selenium.By as By
import org.openqa.selenium.Capabilities as Capabilities
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl('http://demo.guru99.com/selenium/cookie/selenium_aut.php')

WebDriver driver = DriverFactory.getWebDriver()

Capabilities cap = ((driver) as RemoteWebDriver).getCapabilities()

String browserName = cap.getBrowserName()

//driver.findElement(By.name('username')).sendKeys('abc123')
WebUI.setText(findTestObject('Object Repository/Page_Guru99 Selenium Cookie/input_username'), "abc123")

driver.findElement(By.name('password')).sendKeys('123xyz')

driver.findElement(By.name('submit')).click()

WebUI.delay(2)

// store the current session
Set<Cookie> cookies1 = driver.manage().getCookies()

System.out.println('Coockies = ' + cookies1)

// close the web driver instance
driver.close()

// Reinitiate web driver and go to website
WebDriver driver2

if (browserName == 'firefox') {
    System.setProperty('webdriver.gecko.driver', 'c:\\browser_driver\\geckodriver.exe')

    driver2 = new FirefoxDriver()

    driver2.get('http://demo.guru99.com')
} else {
    WebUI.openBrowser('')

    WebUI.navigateToUrl('http://demo.guru99.com')

    driver2 = DriverFactory.getWebDriver()
}

// add the stored session in the bew web driver instance
for (Cookie cookie : cookies1) {
    driver2.manage().addCookie(cookie)
}

// re-visit the page, login information should be placed
driver2.get('http://demo.guru99.com/selenium/cookie/selenium_aut.php')

// get the current session of new web driver instance
Set<Cookie> cookiesInstance2 = driver2.manage().getCookies()

System.out.println('Coockies = ' + cookiesInstance2)

// notice that session of previous web driver instanse is achieved
Assert.assertEquals(cookies1, cookiesInstance2)

WebUI.openBrowser('')

WebUI.setText(findTestObject('Page_Guru99 Selenium Cookie/input_username'), 'abc123')

WebUI.setText(findTestObject('Page_Guru99 Selenium Cookie/input_password'), '123xyz')

WebUI.click(findTestObject('Page_Guru99 Selenium Cookie/button_Login'))

WebUI.closeBrowser()

