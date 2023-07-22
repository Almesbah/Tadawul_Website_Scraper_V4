object Constants {
    // Base URL for the Tadawul website
    const val BASE_URL = "https://www.saudiexchange.sa/wps/portal/tadawul/home/"
    // Path to the GeckoDriver executable (Firefox WebDriver)
    const val WEB_DRIVER_PATH = "./geckodriver" // "src/main/resources/geckodriver.exe"
}
data class CompanyObject(
    val stockPrice: String,
    val companyName: String,
    val companyIssuedShares: String,
    val paidCapital: String,
    val bSheetList: String,
    val sOIList: String,
    val cFList: String
)
data class CompanyIndexes(
    val PE: Double,
    val BVPS: Double,
    val priceToBVPS: Double,
    val netProfitMargin: Double,
    val grossProfitMargin: Double,
    val ROE: Double,
    val ROA: Double,
    val ROC: Double,
    val EPS: Double,
    val financialLeverage: Double,
    val debtRatio: Double,
    val dToERatio: Double,
    val quickLiquidityRatio: Double,
    val liquidityRatio: Double
)

data class CompanyResults(
    val symbol: String,
    val companyData: CompanyObject,
    val companyIndexes: CompanyIndexes
)

