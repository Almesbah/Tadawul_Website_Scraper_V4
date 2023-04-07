import org.openqa.selenium.WebElement

object DataCalculations {

    fun companyData(dataList:List<Any>) {

        val balanceSheetTable = dataList[4] as WebElement
        //val statementOfIncomeTable = dataList[5] as WebElement
        //val cashFlowTable = dataList[6] as WebElement

        println(balanceSheetTable.text)
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
