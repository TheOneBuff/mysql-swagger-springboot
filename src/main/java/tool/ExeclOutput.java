package tool;

import globalvariables.GlobalVariables;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

/**
*@Author by wanghaopeng on 2019/11/8 16:49
*@Comment 输出execl
**/
@Component
public class ExeclOutput<T> {

    //创建工作薄
    Workbook wb = new XSSFWorkbook();


    public void execlInit(List<T> dataList, String [] title, String[] propertyNames, String execlPath, String sheetName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {

        //创建EXECL"C:\\Users\\姚丽霞\\Desktop\\学习代码\\java学习\\mysql-swagger-springboot\\test.xlsx"
        FileOutputStream ex = new FileOutputStream(execlPath + "/"+ GlobalVariables.ExeclName);
        //创建一个工作表
        Sheet sheet1 = wb.createSheet(sheetName);
        //创建一行
        Row row = sheet1.createRow(0);
        for (int i = 0; i < title.length; i++)
        {
            Cell cell = row.createCell(i);
            cell.setCellStyle(this.titlestyle());
            cell.setCellValue(title[i]);
        }
        int rowindex = 0;
        Iterator<T> it = dataList.iterator();
        while(it.hasNext())
        {
            rowindex++;
            row = sheet1.createRow(rowindex);
            T t = it.next();
            for (int i = 0; i < propertyNames.length; i++)
            {
                Cell cell = row.createCell(i);
                Object obj = ExeclOutput.getpropervalue(t, propertyNames[i]);
                cell.setCellStyle(this.bodyStyle());
                cell.setCellValue(obj.toString());
            }
        }
        //row = sheet1.createRow(rowindex + 1);
        //合并单元格
        //参数说明：1：开始行 2：结束行  3：开始列 4：结束列
        //sheet1.addMergedRegion(new CellRangeAddress(0,rowindex,1,1));

        wb.write(ex);
    }

    /**
     * @Comment 获取实体中得值
     * @param [o, propertyName]o：实体类 ，propertyName：实体类中的字段
     * @return V
     **/
    private static <V> V getpropervalue(Object o, String propertyName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        //o.getClass().getDeclaredFields()[0].name.toString()获取实体类的属性
        String getproperty = "get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
        Class cls = o.getClass();
        V value = (V) o.getClass().getMethod(getproperty).invoke(o);
        return value;
    }


    /**
     * @Comment 获取实体中得值
     * @param [o, propertyName]
     * @return V
     **/
/*    private static <V> V getPropertyValue(Object o, String propertyName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String decapitalized = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
        Class tCls = o.getClass();
        Method method = Stream.of(tCls.getMethods())
                .filter(m -> ImmutableSet.of("is" + decapitalized, "get" + decapitalized).contains(m.getName()) && m.getParameterCount() == 0)
                .findAny()
                .orElseThrow(() -> new NoSuchMethodException(decapitalized));
        V value = (V) method.invoke(o, new Object[]{});
        return value;
    }*/


    /**
     * @Comment 对标题样式进行设置
     * @param []
     * @return org.apache.poi.ss.usermodel.CellStyle
     **/
    public CellStyle titlestyle(){
        //创建格子样式
        CellStyle cellstyle = wb.createCellStyle();
        //指定单元格居中对齐
        cellstyle.setAlignment(HorizontalAlignment.CENTER);
        //设置单元格边框（底部）
        cellstyle.setBorderBottom(BorderStyle.THIN);
        //左边
        cellstyle.setBorderLeft(BorderStyle.THIN);
        //右边
        cellstyle.setBorderRight(BorderStyle.THIN);
        //顶部
        cellstyle.setBorderTop(BorderStyle.THIN);
        //设置前景色
        cellstyle.setFillForegroundColor(IndexedColors.RED1.getIndex());
        cellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //设置单元格字体
        Font font = wb.createFont();
        font.setFontName("宋体");
        //粗体
        font.setBold(true);
        cellstyle.setFont(font);
        return cellstyle;
    }


    /**
     * @Comment body的样式
     * @param []
     * @return org.apache.poi.ss.usermodel.CellStyle
     **/
    private CellStyle bodyStyle()
    {
        //创建格子样式
        CellStyle cellstyle = wb.createCellStyle();
        //设置单元格边框（底部）
        cellstyle.setBorderBottom(BorderStyle.THIN);
        //左边
        cellstyle.setBorderLeft(BorderStyle.THIN);
        //右边
        cellstyle.setBorderRight(BorderStyle.THIN);
        //顶部
        cellstyle.setBorderTop(BorderStyle.THIN);
        //设置单元格字体
        Font font = wb.createFont();
        font.setFontName("宋体");
        cellstyle.setFont(font);
        return cellstyle;
    }

}
