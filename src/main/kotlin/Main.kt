import org.openqa.selenium.*
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit

fun main() {
    // Set the system property for the GeckoDriver (Firefox WebDriver)
    System.setProperty("webdriver.gecko.driver", "./geckodriver")

    // Configure Firefox options for headless mode
    val firefoxOptions = FirefoxOptions().apply {
        addArguments("--headless")
    }

    // Create an instance of FirefoxDriver with the specified options
    val driver: WebDriver = FirefoxDriver(firefoxOptions)

    try {
        //driver.manage()?.window()?.maximize()

        // Navigate to the Tadawul website
        driver.get(Constants.BASE_URL)

        // Wait for the page to load
        WebDriverWait(driver, 20).until(
            ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".pageLoader")))
        )

        // Perform search
        performSearch(driver, "2330")

        // Wait for a while before closing the browser (to see the result)
        Thread.sleep(1000)

        // Navigate to the Quarterly Balance Sheet
        navigateToQuarterlyBalanceSheet(driver)

        // Get the Balance Sheet table
        val balanceSheetTable: WebElement = driver.findElement(
            By.cssSelector(
                "div.inner_tab_DtlBox:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > table:nth-child(1)"
            )
        )
        println(balanceSheetTable.text)
        println("Finished Successfully")

    } catch (e: Exception) {
        println("An error occurred: ${e.message}")
    } finally {
        driver.quit()
    }
}

fun performSearch(driver: WebDriver, searchTerm: String) {
    val searchIcon: WebElement = driver.findElement(By.cssSelector(".searchtxt"))
    WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(searchIcon))
    searchIcon.click()

    val searchBox = driver.findElement(By.cssSelector("#searchQuery"))
    searchBox.sendKeys(searchTerm, Keys.ENTER)

    println("Search term '$searchTerm' entered and search performed Successfully.")

    // Set implicit wait time
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS)
}

fun navigateToQuarterlyBalanceSheet(driver: WebDriver) {

    // Wait for the page to load
    WebDriverWait(driver, 30).until(
        ExpectedConditions.invisibilityOf(
            driver.findElement(By.cssSelector(".pageLoader"))
        )
    )

    // Scroll to an element below the button to avoid the cookies banner
    val element: WebElement = driver.findElement(By.cssSelector("div.inner_tab_sub:nth-child(1) > div:nth-child(1) > table:nth-child(1) > thead:nth-child(1) > tr:nth-child(1) > th:nth-child(1)"))
    (driver as JavascriptExecutor).executeScript("arguments[0].scrollIntoView(true);", element)

    // Click on the Quarterly tab
    val quarterlyTab: WebElement = driver.findElement(
        By.cssSelector("div.inner_tab_DtlBox:nth-child(1) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(1) > li:nth-child(2)")
    )
    WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(quarterlyTab))
    quarterlyTab.click()
}




/*
fun main() {
    // Set the system property for the GeckoDriver (Firefox WebDriver)
    System.setProperty("web-driver.gecko.driver", "./geckodriver")
   // Initialize the FirefoxDriver to interact with the Tadawul website
    val driver: WebDriver = FirefoxDriver()

    driver.manage()?.window()?.maximize()

   // Navigate to the Tadawul website
    driver.get(Constants.BASE_URL)

    WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".pageLoader"))))

    val searchIcon: WebElement = driver.findElement(By.cssSelector(".searchtxt")) //.searchtxt > strong:nth-child(2) for arabic so add if statement for eng or ara
    WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(searchIcon))
    searchIcon.click()

    val searchBox = driver.findElement(By.cssSelector("#searchQuery"))
    val searchTerm = "2330"
    searchBox.sendKeys(searchTerm, Keys.ENTER)

    println("Search term '$searchTerm' entered and search performed Successfully.")

    //-------------

    // Wait for page to load and for pageLoader to disappear
    WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".breadcrumbs > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1) > span:nth-child(1)"))))

    // Scroll to an element below the button to avoid the cookies banner
    val element: WebElement = driver.findElement(By.cssSelector(".financials > h3:nth-child(1)"))
    (driver as JavascriptExecutor).executeScript("arguments[0].scrollIntoView(true);", element)

    // Click on the Quarterly tab
    val quarterlyTab: WebElement = driver.findElement(By.cssSelector("div.inner_tab_DtlBox:nth-child(1) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(1) > li:nth-child(2)"))
    WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(quarterlyTab))
    quarterlyTab.click()

    // Get the Balance Sheet table
    val balanceSheetTable: WebElement = driver.findElement(By.cssSelector("div.inner_tab_DtlBox:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > table:nth-child(1)"))
    println(balanceSheetTable.text)
    println("Finished Successfully finally")

    driver.close()
}
*/

/*
// Wait for page to load and for pageLoader to disappear
    val wait = WebDriverWait(driver, 40)
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(".pageLoader")))

    // Scroll to an element below the button to avoid the cookies banner
    val element: WebElement = driver.findElement(By.cssSelector(".financials > h3:nth-child(1)"))
    (driver as JavascriptExecutor).executeScript("arguments[0].scrollIntoView(true);", element)

    // Click on the Quarterly tab
    val quarterlyTab: WebElement = driver.findElement(By.cssSelector("div.inner_tab_DtlBox:nth-child(1) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(1) > li:nth-child(2)"))
    WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(quarterlyTab))
    quarterlyTab.click()

    // Get the Balance Sheet table
    val balanceSheetTable: WebElement = driver.findElement(By.cssSelector("div.inner_tab_DtlBox:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > table:nth-child(1)"))
    println(balanceSheetTable.text)
    println("Finished Successfully finally")
    */


//val companySymbols = listOf("1010", "1020", "1030")



