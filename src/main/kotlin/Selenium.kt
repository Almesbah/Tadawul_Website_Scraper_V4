import org.openqa.selenium.*
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit

// The Selenium object contains methods for extracting web data using Selenium.
object Selenium {

    fun getCompanyData(driver: WebDriver, companySymbolList: String): List<Any> {

        // Wait for a while for the header ticker bar to be visible
        Thread.sleep(1000)

        // Perform search
        performSearch(driver, companySymbolList)

        // Wait for a while before closing the browser (to see the result)
        Thread.sleep(1000)

        //WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div.main_trade_box:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2)"))))

        val stockPrice = driver.findElement(By.cssSelector("div.main_trade_box:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2)")).text
        val companyName = driver.findElement(By.cssSelector(".saudiPage > h3:nth-child(1)")).text
        val companyIssuedShares = driver.findElement(By.cssSelector(".inspectionBox > ul:nth-child(1) > li:nth-child(2) > strong:nth-child(2)")).text
        val paidCapital = driver.findElement(By.cssSelector(".inspectionBox > ul:nth-child(1) > li:nth-child(3) > strong:nth-child(2)")).text

        println("Stock Price: $stockPrice")
        println("Company Name: $companyName")
        println("Company Issued Shares: $companyIssuedShares")
        println("Paid Capital: $paidCapital")

        // The Quarterly Balance Sheet WebElement (Still need to be converted to list)
        val balanceSheetTable = getFinancialStatement(driver, "#balancesheet", "1")
        println(balanceSheetTable.text)

        // The Quarterly Statement of Income
        val statementOfIncomeTable = getFinancialStatement(driver, "#statementofincome", "2")
        println(statementOfIncomeTable.text)

        // The Quarterly Cash Flow
        val cashFlowTable = getFinancialStatement(driver, "#cashflow", "3")
        println(cashFlowTable.text)

        println("$companySymbolList Finished Successfully")

        // Scroll to the top of the page to make the header and ticker bar visible
        (driver as JavascriptExecutor).executeScript("window.scrollTo(0, 0);")

        return listOf(
            stockPrice,
            companyName,
            companyIssuedShares,
            paidCapital,
            balanceSheetTable,
            statementOfIncomeTable,
            cashFlowTable
        )
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

    fun getFinancialStatement(driver: WebDriver, tableName: String, tableNumber: String): WebElement {
        if (tableName == "#balancesheet") {
            // Wait for the page to load
            WebDriverWait(
                driver,
                30
            ).until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".pageLoader"))))
        } else {
            //Click on the list tab
            val listTab: WebElement = driver.findElement(By.cssSelector(tableName))
            WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(listTab))
            listTab.click()
        }

        // Click on the Quarterly tab
        val quarterlyTab: WebElement =
            driver.findElement(By.cssSelector("div.inner_tab_DtlBox:nth-child($tableNumber) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(1) > li:nth-child(2)"))

        WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(quarterlyTab))

        quarterlyTab.click()
        // Return the Cash Flow table Web Element
        return driver.findElement(By.cssSelector("div.inner_tab_DtlBox:nth-child($tableNumber) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > table:nth-child(1)"))
    }



