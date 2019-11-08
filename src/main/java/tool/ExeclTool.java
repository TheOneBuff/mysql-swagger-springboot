package tool;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
*@Author by wanghaopeng on 2019/11/8 16:08
*@Comment EXECL工具类
**/
@Component
public class ExeclTool {
    private XSSFSheet ExcelWSheet;
    private XSSFWorkbook ExcelWBook;
    private XSSFCell Cell;
    private XSSFRow row;
    private String filePath;
    private FileInputStream ExcelFile;

//    设定要操作的Excel 的文件路径和Excel 文件中的sheet名称
//    在读写excel的时候，均需要先调用此方法，设定要操作的excel 文件路径和要操作的sheet名称
//    构造函数初始化
//    public ExeclTool(String Path, String SheetName) throws IOException {
//        System.out.print("构造函数初始化");
//        FileInputStream ExcelFile;
//        try{
//            //实例化excel 文件的FileInputStream 对象
//            ExcelFile = new FileInputStream(Path);
//            //实例化excel 文件的XSSFWorkbook 对象
//            ExcelWBook = new XSSFWorkbook(ExcelFile);
//            //实例化ExcelWSheet 对象，指定excel 文件中的sheet 名称，后续用于sheet 中行、列和单元格的操作
//            ExcelWSheet = ExcelWBook.getSheet(SheetName);
//        }catch (Exception e){
//            throw (e);
//        }
//        filePath = Path;
//    }

    //execl初始化
    public void execlInit(String path,String sheetName) throws IOException {
        try{
            //实例化excel 文件的FileInputStream 对象
            ExcelFile = new FileInputStream("C:\\Users\\姚丽霞\\Desktop\\学习代码\\java学习\\mysql-swagger-springboot\\test.xlsx");
            //实例化excel 文件的XSSFWorkbook 对象
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            //实例化ExcelWSheet 对象，指定excel 文件中的sheet 名称，后续用于sheet 中行、列和单元格的操作
            ExcelWSheet = ExcelWBook.getSheet(sheetName);
        }catch (Exception e){
            throw (e);
        }
    }
    
    //读取指定单元格的数据。需要传入行数、列数
    public String getCellData(int RowNum,int ColNum) throws Exception{
        try{
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            String CellData = "";
            if(Cell.getCellType() == CellType.STRING) {
                CellData = Cell.getStringCellValue();
            }else if(Cell.getCellType() == CellType.NUMERIC){
                DecimalFormat df = new DecimalFormat("0");
                CellData=df.format(Cell.getNumericCellValue());
            }
            return CellData;
        }catch (Exception e){
            e.printStackTrace();
            return  "";
        }
    }
    //读取指定单元格的数据且值为字符串。需要传入行数、列数
    public String getCellStrData_value(int RowNum, int ColNum) throws Exception{
        try{
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            if (Cell!=null){
                String CellData = Cell.getStringCellValue();
                return CellData;
            }
            else {
                return"";
            }
        }catch (Exception e){
            return"";
        }
    }
    //读取指定单元格的数据且值为数字。需要传入行数、列数
    public String getCellNumData_value(int RowNum, int ColNum) throws Exception{
        String cellValue = "";
        DecimalFormat df = new DecimalFormat("#");
        try{
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            if (Cell!=null){
                //有错误
                if (Cell.getCellType()== CellType.NUMERIC ){
                    cellValue= Cell.getStringCellValue();
                }else {
                    cellValue=  String.valueOf( Cell.getNumericCellValue());
                    if(cellValue.endsWith(".0")){
                        cellValue = cellValue.substring(0, cellValue.length() - 2);
                    }
                }
            }
            return cellValue;
        }catch (Exception e){
            return"";
        }
    }

    /**
     * aaa
     *
    * */
    // 在excel 文件的执行单元格中写入数据，此函数只支持后缀为xlsx的excel 文件写入
    public void setCellData(int RowNum,int ColNum,String Result) throws Exception{
        try{
            // 获取excel文件中的行对象
            row = ExcelWSheet.getRow(RowNum);
            // 如果单元格为空，则返回Null
            Cell = row.getCell(ColNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

            if(Cell == null){
                // 当单元格对象是null 的时候，则创建单元格
                // 如果单元格为空，无法直接调用单元格对象的setCellValue 方法设定单元格的值
                Cell = row.createCell(ColNum);
                // 创建单元格后可以调用单元格对象的setCellValue 方法设定单元格的值
                Cell.setCellValue(Result);
            }else{
                // 单元格中有内容，则可以直接调用单元格对象的s方法设定单元格的值etCellValue
                Cell.setCellValue(Result);
                System.out.println("执行完成");
            }
            // 实例化写入 excel 文件的文件输出流对象
            FileOutputStream fileOut = new FileOutputStream(filePath);
            // 将内容写入excel 文件中
            ExcelWBook.write(fileOut);
            // 调用flush方法强制刷新写入文件
            fileOut.flush();
            // 关闭文件输出流对象
            fileOut.close();
        }catch (Exception e){
            e.printStackTrace();
            throw (e);
        }
    }
    // 从excel 文件获取测试数据的静态方法
    public static Object[][] getTestData(String execlFilePath,String sheetName) throws IOException {
        // 根据参数传入的数据文件路径和文件名称，组合出excel 数据文件的绝对路径
        // 声明一个file 文件对象
        File file = new File(execlFilePath);

        // 创建FileInputStream 对象用于读取excel 文件
        FileInputStream inputStream = new FileInputStream(file);


        // 声明Workbook 对象
        Workbook Workbook = null;

        // 获取文件名参数的后缀名，判断xlsx文件还是xls文件
        String fileExtensionName = execlFilePath.substring(execlFilePath.indexOf("."));

        // 判断文件类型如果是xlsx，则使用XSSFWorkbook 对象进行实例化
        // 判断文件类型如果是xls，则使用HSSFWorkbook 对象进行实例化
        if(fileExtensionName.equals(".xlsx")){
            //如果是2007的，也就是.xlsx， 让Workbook = new XSSFWorkbook(inputStream);
            Workbook = new XSSFWorkbook(inputStream);
        }else if (fileExtensionName.equals(".xls")){
            //如果是2003的，也就是.xls， 让Workbook = new HSSFWorkbook(inputStream);
            Workbook = new HSSFWorkbook(inputStream);
        }

        // 通过sheetName参数，生成sheet 对象
        Sheet Sheet = Workbook.getSheet(sheetName);

        // 获取excel 数据文件中sheet1中数据的行数，getLastRowNum 方法获取数据的最后行号
        // getFirstRowNum 方法获取数据的第一行行号，相减之后算出数据的行数
        // 注意：excel 文件的行号和列号都是从0开始
        int rowCount = Sheet.getLastRowNum() - Sheet.getFirstRowNum();
        // 创建名为records 的list 对象来存储从excel数据文件读取的数据
        List<Object[]> records = new ArrayList<Object[]>();
        // 使用2个for 循环遍历excel 数据文件的所有数据（除了第一行，第一行是数据列名称）
        // 所以i 从1开始，而不是从0
        for (int i=1;i<rowCount + 1;i++){
            // 使用getRow 方法获取行对象
            Row row = Sheet.getRow(i);

            String fields[] = new String[row.getLastCellNum() - 1];

            if(row.getCell(row.getLastCellNum()-1).getStringCellValue().equals("y")){
                for(int j=0;j<row.getLastCellNum()-1;j++){
                    //判断excel 的单元格字段是数字还是字符，字符串格式调用getStringCellValue 方法获取
                    // 数字格式调用getNumericCellValue 方法获取
                    // fields[j-1]=(String) row.getCell(j).getCellType()==;
                    try {
                        if (row.getCell(j).getCellType() == CellType.STRING) {
                            fields[j] = row.getCell(j).getStringCellValue();
                        } else if (row.getCell(j).getCellType() == CellType.NUMERIC) {
                            DecimalFormat df = new DecimalFormat("0");
                            fields[j] = df.format(row.getCell(j).getNumericCellValue());
                        } else {
                            System.out.println("格式错误");
                        }
                    }catch (Exception e){
                        fields[j] ="";
                    }

                }
                // fields 的数据对象存储到records的list中
                records.add(fields);
            }
        }
        // 定义函数返回值，即Object[][]
        // 将存储测试数据的list 转换为一个Object 的二维数组
        Object[][] results = new Object[records.size()][];
        // 设置二维数组每行的值，每行是个object对象
        for(int i=0;i<records.size();i++){
            results[i] = records.get(i);
            //LogUtil.logInfo(results[i]);
        }
        // LogUtil.logInfo(results);
        // 关闭excel 文件
        return results;
    }

    public int getLastCellNum(){
        // 返回数据文件的最后一列的列号，如果有12列，则结果返回11
        return ExcelWSheet.getRow(0).getLastCellNum()-1;
    }

}
