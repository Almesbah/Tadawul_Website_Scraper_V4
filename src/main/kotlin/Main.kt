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

    //add companies symbols to get their info
    val companySymbolList = arrayListOf("2330","2120","3020")

    try {
        // Navigate to the Tadawul website
        driver.get(Constants.BASE_URL)

        // Wait for the page to load
        WebDriverWait(driver, 20).until(
            ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".pageLoader")))
        )

        for (i in 0 until  companySymbolList.size){

            // Wait for a while for the header ticker bar to be visible
            Thread.sleep(1000)

            // Perform search
            performSearch(driver, companySymbolList[i])

            // Wait for a while before closing the browser (to see the result)
            Thread.sleep(1000)

            val stockPrice = driver.findElement(By.cssSelector("div.main_trade_box:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2)")).text
            val companyName = driver.findElement(By.cssSelector(".saudiPage > h3:nth-child(1)")).text
            val companyIssuedShares = driver.findElement(By.cssSelector(".inspectionBox > ul:nth-child(1) > li:nth-child(2) > strong:nth-child(2)")).text
            val paidCapital = driver.findElement(By.cssSelector(".inspectionBox > ul:nth-child(1) > li:nth-child(3) > strong:nth-child(2)")).text

            // The Quarterly Balance Sheet
            val balanceSheetTable = getFinancialStatement(driver,"#balancesheet","1")
            // The Quarterly Statement of Income
            val statementOfIncomeTable = getFinancialStatement(driver,"#statementofincome","2")
            // The Quarterly Cash Flow
            val cashFlowTable = getFinancialStatement(driver,"#cashflow","3")

            val bST = balanceSheetTable.text
            val sOI = statementOfIncomeTable.text
            val cF = cashFlowTable.text

            println("Stock Price: $stockPrice")
            println("Company Name: $companyName")
            println("Company Issued Shares: $companyIssuedShares")
            println("Paid Capital: $paidCapital")

            println(bST)
            println(sOI)
            println(cF)

            println(companySymbolList[i]+" Finished Successfully")

            // Scroll to the top of the page to make the header and ticker bar visible
            (driver as JavascriptExecutor).executeScript("window.scrollTo(0, 0);")
        }

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

fun getFinancialStatement(driver: WebDriver, listName: String, listNumber: String):WebElement {
    if(listName == "#balancesheet"){
        // Wait for the page to load
        WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".pageLoader"))))
    } else {
        //Click on the list tab
        val listTab: WebElement = driver.findElement(By.cssSelector(listName))
        WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(listTab))
        listTab.click()
    }
    // Click on the Quarterly tab
    val quarterlyTab: WebElement = driver.findElement(By.cssSelector("div.inner_tab_DtlBox:nth-child($listNumber) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(1) > li:nth-child(2)"))
    WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(quarterlyTab))
    quarterlyTab.click()
    // Return the Cash Flow table Web Element
    return driver.findElement(By.cssSelector("div.inner_tab_DtlBox:nth-child($listNumber) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > table:nth-child(1)"))
}

fun tableData(balanceSheetTable: WebElement): List<List<String>> {
    val tableData = mutableListOf<List<String>>()

    // Find all the rows in the table
    val rows = balanceSheetTable.findElements(By.tagName("tr"))

    // Iterate through the rows
    for (row in rows) {
        val rowData = mutableListOf<String>()

        // Find all the cells (both 'th' and 'td') in the row
        val headerCells = row.findElements(By.tagName("th"))
        val dataCells = row.findElements(By.tagName("td"))
        val allCells = headerCells + dataCells

        // Iterate through the cells and extract the text content
        for (cell in allCells) {
            rowData.add(cell.text)
        }

        // Add the row data to the table data list
        tableData.add(rowData)
    }

    return tableData
}

//val companySymbols = listOf("1010", "1020", "1030")
//driver.manage()?.window()?.maximize()

// Scroll to an element below the button to avoid the cookies banner
/*val element: WebElement = driver.findElement(By.cssSelector("div.inner_tab_sub:nth-child(1) > div:nth-child(1) > table:nth-child(1) > thead:nth-child(1) > tr:nth-child(1) > th:nth-child(1)"))
(driver as JavascriptExecutor).executeScript("arguments[0].scrollIntoView(true);", element)*/




