import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait

object DataCalculations {

    fun companyData(dataList:List<Any>) {

        val stockPrice = dataList[0] as String
        val companyName = dataList[1] as String
        val companyIssuedShares = dataList[2] as String
        val paidCapital = dataList[3] as String

        val balanceSheetTable = dataList[4] as WebElement
        val statementOfIncomeTable = dataList[5] as WebElement
        val cashFlowTable = dataList[6] as WebElement

        println(stockPrice)
        println(companyName)
        println(companyIssuedShares)
        println(paidCapital)

        println(balanceSheetTable.text)
        println(statementOfIncomeTable.text)
        println(cashFlowTable.text)

        // Extract the data of the webElement Table to list using a tab character as the delimiter (change the delimiter if needed)
        //val extractedBSTable = extractTableData(balanceSheetTable.text)
        // Print the extracted data
        /*for (rowData in extractedBSTable) {
            println(rowData)
        }*/
        //val extractedSOITable = extractTableData(statementOfIncomeTable.text)
        //val extractedCFTable = extractTableData(cashFlowTable.text)

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
