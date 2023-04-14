import org.openqa.selenium.*
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions



fun main() {
    // Set the system property for the GeckoDriver (Firefox WebDriver)
    System.setProperty("webdriver.gecko.driver", "./geckodriver")
    // Configure Firefox options for headless mode
    val firefoxOptions = FirefoxOptions().apply {
        addArguments("--headless")
    }
    // Create an instance of FirefoxDriver with the specified options
    val driver: WebDriver = FirefoxDriver(firefoxOptions)

    //add companies symbols to get their info
    val companySymbolList = arrayListOf("2330","2120")

    try {
        // Navigate to the Tadawul website
        driver.get(Constants.BASE_URL)

        for (i in 0 until  companySymbolList.size){
           val companyDataList =  Selenium.getCompanyData(driver,companySymbolList[i])

            DataCalculations.companyDataCalculations(companyDataList)
        }

    } catch (e: Exception) {
        println("An error occurred: ${e.message}")
    } finally {
        driver.quit()
    }
}





