import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait

object DataCalculations {

    fun companyData(dataList:ArrayList<String>) {

        val stockPrice = dataList[0]
        val companyName = dataList[1]
        val companyIssuedShares = dataList[2]
        val paidCapital = dataList[3]

        val balanceSheetTable = dataList[4]
        val statementOfIncomeTable = dataList[5]
        val cashFlowTable = dataList[6]

        println(stockPrice)
        println(companyName)
        println(companyIssuedShares)
        println(paidCapital)

        // Extract the data of the webElement Table to list using a tab character as the delimiter (change the delimiter if needed)
        val extractedBSTable = extractTableData(balanceSheetTable)
        // Print the extracted data
        for (rowData in extractedBSTable) {
            println(rowData)
        }
        val extractedSOITable = extractTableData(statementOfIncomeTable)
        for (rowData in extractedSOITable) {
            println(rowData)
        }
        val extractedCFTable = extractTableData(cashFlowTable)
        for (rowData in extractedCFTable) {
            println(rowData)
        }
    }
}
fun extractTableData(tableText: String, delimiter: String = "\t"): List<List<String>> {
    val rows = tableText.split("\n")
    val tableData = mutableListOf<List<String>>()

    for (row in rows) {
        val rowData = row.split(delimiter)
        tableData.add(rowData)
    }
    return tableData
}
