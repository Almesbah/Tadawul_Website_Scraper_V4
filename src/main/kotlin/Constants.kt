object Constants {
    // Base URL for the Tadawul website
    const val BASE_URL = "https://www.saudiexchange.sa/wps/portal/tadawul/home/"
    // Path to the GeckoDriver executable (Firefox WebDriver)
    const val WEB_DRIVER_PATH = "./geckodriver" // "src/main/resources/geckodriver.exe"
}
data class CompanyInfo(
    val stockPrice: Double,
    val companyName: String,
    val companyIssuedShares: Double,
    val paidCapital: Double,
    val bSheetList: Any,
    val sOIList: Any,
    val cFList: Any
)

