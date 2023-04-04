import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream
import java.io.IOException

object CreateExcelFile {
    fun createWorkbook(data: List<String>, sheetName: String, fileName: String) {
        try {
            XSSFWorkbook().use { workbook ->
                val sheet = workbook.createSheet(sheetName)

                for ((index, rowData) in data.withIndex()) {
                    val row = sheet.createRow(index)
                    rowData.split(" ").forEachIndexed { cellIndex, cellData ->
                        row.createCell(cellIndex).setCellValue(cellData)
                    }
                }

                FileOutputStream(fileName).use { fileOutputStream ->
                    workbook.write(fileOutputStream)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
