package tool;


import bean.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.crypto.KeyGenerator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
*@Author by wanghaopeng on 2019/11/19 8:37
*@Comment 读取Execl数据插入到数据库
**/
@Component
public class ExeclInput {

    public List<User> execlInit() throws IOException {
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
        List<User> userlist = new ArrayList<User>();
        //从1开始是除去title开始，读取body
        for (int i = 1; i <= row; i++)
        {
            Row sheetrow = sheet.getRow(i);
            int cellnum = sheetrow.getLastCellNum();
            User user = new User();
            for (int j = 0; j < cellnum ; j++)
            {
                Object obj = null;
                //判断格子是数字类型
//                if (sheetrow.getCell(j).getCellType() == CellType.NUMERIC)
//                {
//                    obj = Integer.valueOf(sheetrow.getCell(j).toString());
//                }
//                else
                //判断格子是字符串类型
                if (sheetrow.getCell(j).getCellType() == CellType.STRING){
                    obj = sheetrow.getCell(j).toString();
                }
                //case语句中少写了break，编译不会报错，但是会一直执行之后所有case条件下的语句而不再判断
                switch (j) {
                    case 0 : user.setId(Integer.valueOf(obj.toString()));
                             break;
                    case 1 : user.setName(obj.toString());
                             break;
                    case 2 : user.setAge(Integer.valueOf(obj.toString()));
                             break;
                    default : break;
                }
            }
            userlist.add(user);
        }
        return userlist;
    }

}
