package tool;


import bean.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
*@Author by wanghaopeng on 2019/11/19 8:37
*@Comment 读取Execl数据插入到数据库
**/
@Component
public class ExeclInput {

    public void execlInit() throws IOException {
        String path = "C:\\Users\\姚丽霞\\Desktop\\学习代码\\java学习\\mysql-swagger-springboot\\test.xlsx";
        //声明一个文件
        File file = new File(path);
        //创建一个字节流读取Execl
        FileInputStream fileinput = new FileInputStream(file);
        //声明一个workbook对象
        Workbook workbook = null;
        String filePostfix = path.substring(path.indexOf('.'));
        //判断后缀是.xlsx和.xls
        if (".xlsx".equals(filePostfix))
        {
            workbook = new XSSFWorkbook(fileinput);
        }
        else{
            workbook = new HSSFWorkbook(fileinput);
        }
        //生成sheet对象
        Sheet sheet = workbook.getSheet("test");
        //获取sheet多少行
        int row = sheet.getLastRowNum() - sheet.getFirstRowNum();
        List<User> userlist = null;
        //从1开始是除去title开始，读取body
        for(int i = 1; i < row; i++)
        {
            Row sheetrow = sheet.getRow(i);
            int cellnum = sheetrow.getLastCellNum();
            for(int j = 0; j < cellnum - 1; j++)
            {

            }
        }


    }

}
