import ScrapeAndCalculate.scrapeAndCalculate
import freemarker.cache.ClassTemplateLoader
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.freemarker.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.*


fun main() {

    // Start an embedded Ktor server with the Netty engine on port 8080
    embeddedServer(Netty, port = 8080) {
        // Install the FreeMarker feature
        install(FreeMarker) {
            // Configure the template loader to load templates from the "templates" directory in resources
            templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
        }

        // Configure routing
            routing {

                // Define a GET route for the root path "/"
                get("/") {
                    call.respond(FreeMarkerContent("results.ftl", mapOf<String, Any>("results" to listOf<CompanyResults>())))
                }
                // Define a POST route for the "/submit" path
                post("/submit") {
                    // Extract the companySymbols parameter from the request and split it into a list
                    val companySymbols = call.receiveParameters()["companySymbols"]?.split(",")

                    if (companySymbols != null) {
                        // Call the scrapeAndCalculate function to get the results
                        val results = scrapeAndCalculate(companySymbols)

                        // Respond with the calculated results rendered using the FreeMarker template
                        //call.respond(FreeMarkerContent("templates/results.ftl", mapOf("results" to results)))
                        call.respond(FreeMarkerContent("results.ftl", mapOf("results" to results)))

                    } else {
                        // Respond with an internal server error if there was a problem processing the request
                        call.respond(InternalServerError, "Error calculating fundamental analysis results.")
                    }
                }

                // Serve static resources from "/icons" resource folder
                staticResources("/icons", "icons")
            }
        }.start(wait = true) // Start the server and wait for incoming requests
    }

    /*// Set the system property for the GeckoDriver (Firefox WebDriver)
    System.setProperty("webdriver.gecko.driver", WEB_DRIVER_PATH)
    // Configure Firefox options for headless mode
    val firefoxOptions = FirefoxOptions().apply {
        addArguments("--headless")
    }
    // Create an instance of FirefoxDriver with the specified options
    val driver: WebDriver = FirefoxDriver(firefoxOptions)

    val companySymbolList = arrayListOf("2330","2120")

    try {
        // Navigate to the Tadawul website
        driver.get(Constants.BASE_URL)

        for (i in 0 until  companySymbolList.size) {
            // Scrape company data
            val companyData = Selenium.getCompanyData(driver, companySymbolList[i])
            // Calculate the fundamental analysis result
            val companyIndexes = DataCalculations.companyDataCalculations(companyData)
            println(companyIndexes.PE)
            // Add the result to the list
            //results.add(CompanyResults(symbol, companyData, companyIndexes))
        }

    } catch (e: Exception) {
        println("An error occurred: ${e.message}")
    } finally {
        // Close the WebDriver
        driver.quit()
    }*/
    /*// Set the system property for the GeckoDriver (Firefox WebDriver)
    System.setProperty("webdriver.gecko.driver", WEB_DRIVER_PATH)
    // Configure Firefox options for headless mode
    val firefoxOptions = FirefoxOptions().apply {
        addArguments("--headless")
    }
    // Create an instance of FirefoxDriver with the specified options
    val driver: WebDriver = FirefoxDriver(firefoxOptions)

    //add companies symbols to get their info
    val companySymbolList = arrayListOf("2330","2120")

    try {
        // Navigate to the Tadawul website
        driver.get(Constants.BASE_URL)

        for (i in 0 until  companySymbolList.size){
           val companyDataList =  Selenium.getCompanyData(driver,companySymbolList[i])

            DataCalculations.companyDataCalculations(companyDataList)
        }

    } catch (e: Exception) {
        println("An error occurred: ${e.message}")
    } finally {
        driver.quit()
    }*/






