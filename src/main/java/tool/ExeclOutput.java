package tool;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
*@Author by wanghaopeng on 2019/11/8 16:49
*@Comment 输出execl
**/
@Component
public class ExeclOutput {


    public void execlInit() throws IOException {

        FileOutputStream ex = new FileOutputStream("C:\\Users\\姚丽霞\\Desktop\\学习代码\\java学习\\mysql-swagger-springboot\\test.xlsx");
        Workbook wb = new XSSFWorkbook();
        Sheet sheet1 = wb.createSheet("new sheet");
        wb.write(ex);

    }
}
