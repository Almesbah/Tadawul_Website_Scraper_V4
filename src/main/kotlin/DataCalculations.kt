
object DataCalculations {

    fun companyData(dataList:ArrayList<String>) {

        val stockPrice = dataList[0].toBigDecimal().toDouble()
        val companyName = dataList[1]
        val companyIssuedShares = dataList[2]
        val paidCapital = dataList[3]

        val balanceSheetTable = dataList[4]
        val statementOfIncomeTable = dataList[5]
        val cashFlowTable = dataList[6]

        val extractedBSTable = extractTableData(balanceSheetTable)
        // Print the extracted data
        for (rowData in extractedBSTable) {
            println(rowData)
        }
        val extractedSOITable = extractTableData(statementOfIncomeTable)
        val extractedCFTable = extractTableData(cashFlowTable)

    }
}

fun extractTableData(input: String): List<String> {
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

