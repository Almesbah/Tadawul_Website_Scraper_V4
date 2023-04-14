
object DataCalculations {

    fun companyDataCalculations(dataList:ArrayList<String>) {

        val stockPrice = dataList[0].toDouble()
        val companyName = dataList[1]
        val companyIssuedShares = dataList[2].toDouble()
        val paidCapital = dataList[3].toDouble()


        val balanceSheetTable = dataList[4]
        val statementOfIncomeTable = dataList[5]
        val cashFlowTable = dataList[6]
        //Split String to String List
        val extractedBSTable = splitTableDataString(balanceSheetTable)
        //Convert StringList to Double value
        val bSLDouble = stringNumberListToDouble(extractedBSTable)

        val extractedSOITable = splitTableDataString(statementOfIncomeTable)
        val sOIDouble = stringNumberListToDouble(extractedSOITable)

        val extractedCFTable = splitTableDataString(cashFlowTable)
        val cFDouble = stringNumberListToDouble(extractedCFTable)

        val companyDataList = arrayListOf(stockPrice, companyName, companyIssuedShares, paidCapital, bSLDouble, sOIDouble, cFDouble)

        val fAnalysisResultDouble = arrayListOf<Double>()

        // val calc1 = (stockPrice*issuedShares)/incomeStatement[11] Net Income
        val PE = (stockPrice*companyIssuedShares)/sOIDouble[57] as Double
        fAnalysisResultDouble.add(PE)

        //val calc2 = balanceSheet[9]/issuedShares
        val BVPS = bSLDouble[47] as Double/companyIssuedShares
        fAnalysisResultDouble.add(BVPS)

        //val calc3 = stockPrice/calc2
        val priceToBVPS = stockPrice/BVPS
        fAnalysisResultDouble.add(priceToBVPS)

        //val calc4 = incomeStatement[11]/incomeStatement[4]
        val netProfitMargin = sOIDouble[57] as Double/sOIDouble[22] as Double
        fAnalysisResultDouble.add(netProfitMargin)

        // val calc5 = incomeStatement[2]/incomeStatement[0]
        val grossProfitMargin = sOIDouble[12] as Double/sOIDouble[2] as Double
        fAnalysisResultDouble.add(grossProfitMargin)

        //val calc6 = incomeStatement[11]/balanceSheet[9]
        val ROE = sOIDouble[57] as Double/bSLDouble[47] as Double
        fAnalysisResultDouble.add(ROE)

        //val calc7 = incomeStatement[11]/balanceSheet[5]
        val ROA = sOIDouble[57] as Double/bSLDouble[27] as Double
        fAnalysisResultDouble.add(ROA)

        //val calc8 = incomeStatement[11]/paidCapital
        val ROC = sOIDouble[57] as Double/paidCapital
        fAnalysisResultDouble.add(ROC)

        //val calc9 = incomeStatement[11]/issuedShares
        val EPS = sOIDouble[57] as Double/companyIssuedShares
        fAnalysisResultDouble.add(EPS)

        //val calc10 = balanceSheet[5]/balanceSheet[9]
        val financialLeverage = bSLDouble[52] as Double/bSLDouble[47] as Double
        fAnalysisResultDouble.add(financialLeverage)

        //val calc11 = (balanceSheet[10] - balanceSheet[9])/balanceSheet[5]
        val debtRatio = (bSLDouble[52] as Double-bSLDouble[47] as Double)/bSLDouble[27] as Double
        fAnalysisResultDouble.add(debtRatio)

        //val calc12 = (balanceSheet[10] - balanceSheet[9])/balanceSheet[9]
        val dToERatio = (bSLDouble[52] as Double-bSLDouble[47] as Double)/bSLDouble[47] as Double
        fAnalysisResultDouble.add(dToERatio)

        //val calc13 = (cashFlow[11]+cashFlow[2])/balanceSheet[6]
        val quickLiquidityRatio = (cFDouble[57] as Double+cFDouble[12] as Double)/bSLDouble[32] as Double
        fAnalysisResultDouble.add(quickLiquidityRatio)

        //val calc14 = balanceSheet[6]/balanceSheet[0]
        val liquidityRatio = bSLDouble[32] as Double/bSLDouble[2] as Double
        fAnalysisResultDouble.add(liquidityRatio)
    }
}

fun splitTableDataString(input: String): List<String> {
    // Create a mutable list to store the final output strings
    val result = mutableListOf<String>()

    // Iterate through each line in the input, split by the newline character
    input.split("\n").forEach { line ->
        // Split the line using the regex pattern for hyphens surrounded by whitespace or hyphens at the end of the line
        val wordsAndHyphens = line.split(Regex("\\s-\\s|\\s-\$"))

        // Iterate through each substring in wordsAndHyphens
        wordsAndHyphens.forEachIndexed { index, word ->
            if (index == 0) {
                // If it's the first substring, add it directly to the result list
                result.add(word)
            } else {
                // For the remaining substrings, add a hyphen and the word to the result list
                result.add("-")
                if (word.isNotBlank()) {
                    result.add(word)
                }
            }
        }
    }

    // Return the result list after processing all the lines
    return result
}

fun stringNumberListToDouble(input: List<String>): List<Any> {
    return input.map { element ->
        when {
            element == "-" -> 0.0
            element.contains(Regex("^\\d+(,\\d{3})*(\\.\\d+)?$")) -> element.replace(",", "").toDouble()
            else -> element
        }
    }
}

/*fun stringNumberToDouble(input: String): Double {
    if (input == "-") {
        return 0.0
    }
    val stringWithoutCommas = input.replace(",", "")
    return stringWithoutCommas.toDouble()
}*/


// Function that takes an input string split it and returns a list of strings
/*fun extractTableData(input: String): List<String> {
    // Split the input string into lines using the newline character
    val lines = input.split("\n")
    // Create a mutable list to store the final output strings
    val result = mutableListOf<String>()

    // Iterate through each line in the lines list
    for (line in lines) {
        // Check if the line contains a hyphen surrounded by whitespace or a hyphen at the end of the line
        if (line.contains(Regex("\\s-\\s|\\s-\$"))) {
            // Split the line into words and hyphen groups using lookahead regex and trim the substrings
            val wordsAndHyphens = line.split(Regex("(?=\\s-\\s|\\s-\$)")).map { it.trim() }

            // Iterate through each word or hyphen group in the wordsAndHyphens list
            for (word in wordsAndHyphens) {
                // Check if the word or hyphen group contains a hyphen surrounded by whitespace or a hyphen at the end of the line
                if (word.contains(Regex("\\s-\\s|\\s-\$"))) {
                    // Split the word or hyphen group into individual elements using whitespace as a delimiter
                    val splitWord = word.split(Regex("\\s"))

                    // Combine the first two elements of the splitWord list (i.e., the text before the hyphen and the hyphen) and add the resulting string to the result list
                    result.add(splitWord[0] + " " + splitWord[1])

                    // Iterate through the remaining elements of the splitWord list, starting from the third element
                    for (i in 2 until splitWord.size) {
                        // Check if the current element is a hyphen, if it is, add the hyphen to the result list
                        if (splitWord[i] == "-") {
                            result.add(splitWord[i])
                        }
                    }
                } else {
                    // Add the word or hyphen group to the result list as-is
                    result.add(word)
                }
            }
        } else {
            // Add the line to the result list as-is
            result.add(line)
        }
    }
    // Return the result list after processing all the lines
    return result
}*/

