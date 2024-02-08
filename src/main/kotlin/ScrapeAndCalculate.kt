import Constants.WEB_DRIVER_PATH
import kotlinx.coroutines.coroutineScope
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

object ScrapeAndCalculate {
    // Function to scrape and calculate the results based on the given company symbols
    suspend fun scrapeAndCalculate(companySymbols: List<String>): List<CompanyResults> = coroutineScope {
        val results = mutableListOf<CompanyResults>()

        // Set the system property for the GeckoDriver (Firefox WebDriver)
        System.setProperty("webdriver.gecko.driver", WEB_DRIVER_PATH)
        // Configure Firefox options for headless mode
        val firefoxOptions = FirefoxOptions().apply {
            //addArguments("--headless")
        }
        // Create an instance of FirefoxDriver with the specified options
        val driver: WebDriver = FirefoxDriver(firefoxOptions)

        try {
            // Navigate to the Tadawul website
            driver.get(Constants.BASE_URL)

            for (symbol in companySymbols) {
                // Scrape company data
                val companyData = Selenium.getCompanyData(driver, symbol)
                // Calculate the fundamental analysis result
                val companyIndexes = DataCalculations.companyDataCalculations(companyData)
                // Add the result to the list
                results.add(CompanyResults(symbol, companyData, companyIndexes))
            }

        } catch (e: Exception) {
            e.printStackTrace()
            println("An error occurred: ${e.message}")
        } finally {
            // Close the WebDriver
            driver.quit()
        }

        // Return the list of results
        return@coroutineScope results
    }
}