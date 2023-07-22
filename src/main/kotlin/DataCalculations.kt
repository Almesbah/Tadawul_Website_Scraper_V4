
object DataCalculations {

    fun companyDataCalculations(companyData: CompanyObject): CompanyIndexes {

        val stockPrice = companyData.stockPrice.toDouble() //dataList[0].toDouble()
        val companyName = companyData.companyName //dataList[1]
        val companyIssuedShares = companyData.companyIssuedShares.replace(",", "").toDouble() //dataList[2].replace(",", "").toDouble()
        val paidCapital = companyData.paidCapital.replace(",", "").toDouble() //dataList[3].replace(",", "").toDouble()

        println(companyName)
        println(stockPrice)
        println(companyIssuedShares)
        println(paidCapital)

        val balanceSheetTable = companyData.bSheetList //dataList[4]
        val statementOfIncomeTable = companyData.sOIList //dataList[5]
        val cashFlowTable = companyData.cFList //dataList[6]


        //Split String to String List
        val extractedBSTable = splitTableDataString(balanceSheetTable)
        //Convert StringList to Double value
        val bSLDouble = stringNumberListToDouble(extractedBSTable)

        val extractedSOITable = splitTableDataString(statementOfIncomeTable)
        val sOIDouble = stringNumberListToDouble(extractedSOITable)

        val extractedCFTable = splitTableDataString(cashFlowTable)
        val cFDouble = stringNumberListToDouble(extractedCFTable)

        //val companyDataList = arrayListOf(stockPrice, companyName, companyIssuedShares, paidCapital, bSLDouble, sOIDouble, cFDouble)

        // val calc1 = (stockPrice*issuedShares)/incomeStatement[11] Net Income
        val PE = stockPrice / (companyIssuedShares / sOIDouble[57] as Double)
        println(PE)

        //val calc2 = balanceSheet[9]/issuedShares
        val BVPS = bSLDouble[47] as Double / companyIssuedShares
        println(BVPS)

        //val calc3 = stockPrice/calc2
        val priceToBVPS = stockPrice / BVPS
        println(priceToBVPS)

        //val calc4 = incomeStatement[11]/incomeStatement[4]
        val netProfitMargin = sOIDouble[57] as Double / sOIDouble[22] as Double
        println(netProfitMargin)

        // val calc5 = incomeStatement[2]/incomeStatement[0]
        val grossProfitMargin = sOIDouble[12] as Double / sOIDouble[2] as Double
        println(grossProfitMargin)

        //val calc6 = incomeStatement[11]/balanceSheet[9]
        val ROE = sOIDouble[57] as Double / bSLDouble[47] as Double
        println(ROE)

        //val calc7 = incomeStatement[11]/balanceSheet[5]
        val ROA = sOIDouble[57] as Double / bSLDouble[27] as Double
        println(ROA)

        //val calc8 = incomeStatement[11]/paidCapital
        val ROC = sOIDouble[57] as Double / paidCapital
        println(ROC)

        //val calc9 = incomeStatement[11]/issuedShares
        val EPS = sOIDouble[57] as Double / companyIssuedShares
        println(EPS)

        //val calc10 = balanceSheet[5]/balanceSheet[9]
        val financialLeverage = bSLDouble[52] as Double / bSLDouble[47] as Double
        println(financialLeverage)

        //val calc11 = (balanceSheet[10] - balanceSheet[9])/balanceSheet[5]
        val debtRatio = (bSLDouble[52] as Double - bSLDouble[47] as Double) / bSLDouble[27] as Double
        println(debtRatio)

        //val calc12 = (balanceSheet[10] - balanceSheet[9])/balanceSheet[9]
        val dToERatio = (bSLDouble[52] as Double - bSLDouble[47] as Double) / bSLDouble[47] as Double
        println(dToERatio)

        //val calc13 = (cashFlow[11]+cashFlow[2])/balanceSheet[6]
        val quickLiquidityRatio = (cFDouble[57] as Double + cFDouble[12] as Double) / bSLDouble[32] as Double
        println(quickLiquidityRatio)

        //val calc14 = balanceSheet[6]/balanceSheet[0]
        val liquidityRatio = bSLDouble[32] as Double / bSLDouble[2] as Double
        println(liquidityRatio)

        return CompanyIndexes(
            PE = PE,
            BVPS = BVPS,
            priceToBVPS = priceToBVPS,
            netProfitMargin = netProfitMargin,
            grossProfitMargin = grossProfitMargin,
            ROE = ROE,
            ROA = ROA,
            ROC = ROC,
            EPS = EPS,
            financialLeverage = financialLeverage,
            debtRatio = debtRatio,
            dToERatio = dToERatio,
            quickLiquidityRatio = quickLiquidityRatio,
            liquidityRatio = liquidityRatio
        )
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
            element.contains(Regex("^-?\\d+(,\\d{3})*(\\.\\d+)?$")) -> element.replace(",", "").toDouble()*1000.0
            else -> element
        }
    }
}

