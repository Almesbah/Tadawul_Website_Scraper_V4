import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

// The SeleniumWebData object contains methods for extracting web data using Selenium.
object SeleniumWebData {
    // This function returns a list of company names from the Saudi Exchange website.
    fun getCompaniesList(driver: WebDriver): List<String> {
        // Find the table rows in the most active stocks table using CSS selectors.
        val tableRows = getTableRows(driver, "#most-active-stocks > table > tbody > tr")
        // Extract the company names from the table rows and return them as a list.
        return tableRows.map { it.findElement(By.cssSelector("td:nth-child(2) > a")).text }
    }

    // This function returns a list of WebElement representing table rows from a table using a CSS selector.
    private fun getTableRows(driver: WebDriver, cssSelector: String): List<WebElement> {
        val wait = WebDriverWait(driver, 20)
        // Wait for the presence of all elements located by the given CSS selector and return them as a list.
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(cssSelector)))
    }
}

/* // Add the CSS selectors in the respective variables below.
    val stockPriceSelector = ""
    val companyNameSelector = ""
    val companyIssuedSharesSelector = ""
    val paidCapitalSelector = ""
    val balanceSheetTableSelector = ""
    val incomeStatementTableSelector = ""
    val cashFlowTableSelector = ""

    // Retrieve elements using CSS selectors
    val stockPrice: WebElement? = driver.findElement(By.cssSelector(stockPriceSelector))
    val companyName: WebElement? = driver.findElement(By.cssSelector(companyNameSelector))
    val companyIssuedShares: WebElement? = driver.findElement(By.cssSelector(companyIssuedSharesSelector))
    val paidCapital: WebElement? = driver.findElement(By.cssSelector(paidCapitalSelector))
    val balanceSheetTable: WebElement? = driver.findElement(By.cssSelector(balanceSheetTableSelector))
    val incomeStatementTable: WebElement? = driver.findElement(By.cssSelector(incomeStatementTableSelector))
    val cashFlowTable: WebElement? = driver.findElement(By.cssSelector(cashFlowTableSelector))*/
