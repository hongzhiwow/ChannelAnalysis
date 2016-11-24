package ReadSource;

import Utils.Common;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ouhiroshi on 2016/11/24.
 */
public class ReadSourceFile {

    private static final Sheet sheet;

    static {
        InputStream stream = null;
        try {
            stream = new FileInputStream(Common.SOURCE_FILE_PATCH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = workbook.getSheet(Common.SOURCE_FILE_SHEET_NAME);
    }

    /**
     * Gets source url.
     *
     * @param lineNumber the line number
     * @return the source url
     */
    public static String getSourceURL(int lineNumber) {
        Row row = sheet.getRow(lineNumber);
        Cell cell = row.getCell(0);
        return cell.getStringCellValue();
    }
}
