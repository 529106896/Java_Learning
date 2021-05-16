package lab11_3;
import java.io.*;
import java.util.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil {

    public static List<List<String>> readExcel(String filePath) 
    {
        List<List<String>> dataLst = null;
        InputStream is = null;
        try {
            //验证文件是否合法
            if (!validateExcel(filePath)) {
                return null;
            }
            // 判断文件的类型，是2003还是2007
            boolean isExcel2003 = true;
            if (isExcel2007(filePath)) {
                isExcel2003 = false;
            }
            // 调用本类提供的根据流读取的方法
            File file = new File(filePath);
            is = new FileInputStream(file);
            dataLst = read(is, isExcel2003);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    is = null;
                    e.printStackTrace();
                }
            }
        }

        return dataLst;
    }

    private static List<List<String>> read(InputStream inputStream, boolean isExcel2003) {
        List<List<String>> dataLst = null;
        try {
            //根据版本选择创建Workbook的方式
            Workbook wb = null;
            if (isExcel2003) {
                wb = new HSSFWorkbook(inputStream);
            } else {
                wb = new XSSFWorkbook(inputStream);
            }
            dataLst = read(wb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataLst;
    }

	private static List<List<String>> read(Workbook wb) {
        int totalRows = 0;
        int totalCells = 0;
        List<List<String>> dataLst = new ArrayList<List<String>>();
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数
        if (totalRows >= 1 && sheet.getRow(0) != null) {
            totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        //遍历行
        for (int r = 0; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            List<String> rowLst = new ArrayList<String>();
            //遍历列
            for (int c = 0; c < totalCells; c++) {
                Cell cell = row.getCell(c);
                String cellValue = "";
                if (null != cell) 
                {
                	cell.setCellType(CellType.STRING);
                	cellValue = cell.getStringCellValue();
                    // 以下是判断数据的类型
//                    switch (cell.getCellType()) {
//                        case NUMERIC: // 数字
//                            cellValue = cell.getNumericCellValue() + "";
//                            break;
//                        case STRING: // 字符串
//                            cellValue = cell.getStringCellValue();
//                            break;
//                        case BOOLEAN: // Boolean
//                            cellValue = cell.getBooleanCellValue() + "";
//                            break;
//                        case FORMULA: // 公式
//                            cellValue = cell.getCellFormula() + "";
//                            break;
//                        case BLANK: // 空值
//                            cellValue = "";
//                            break;
//                        case ERROR: // 故障
//                            cellValue = "非法字符";
//                            break;
//                        default:
//                            cellValue = "未知类型";
//                            break;
//                    }
                }
                rowLst.add(cellValue);
            }
            // 保存第r行的第c列
            dataLst.add(rowLst);
        }
        return dataLst;
    }

    private static boolean validateExcel(String filePath) {
        // 检查文件名是否为空或者是否是Excel格式的文件
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            return false;
        }
        // 检查文件是否存在
        File file = new File(filePath);
        if (file == null || !file.exists()) {
            return false;
        }
        return true;
    }

    private static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    private static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }


    public static void writeExcel(List<List<String>> dataList, String finalXlsxPath)
    {
        OutputStream out = null;
        try {

            // 读取Excel文档
            File finalXlsxFile = new File(finalXlsxPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet 对应一个工作页
            Sheet sheet = workBook.getSheetAt(0);

            //删除原有数据，除了属性列
            int rowNumber = sheet.getLastRowNum();
            for (int i = 1; i <= rowNumber; i++) {
                Row row = sheet.getRow(i);
                sheet.removeRow(row);
            }

            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);

            //写一行
            for (int j = 0; j < dataList.size(); j++) {
                Row row = sheet.createRow(j);

                //写一列
                List<String> datas = dataList.get(j);
                for (int k=0; k<datas.size(); k++) {
                    row.createCell(k).setCellValue(datas.get(k));
                }
            }

            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            //System.out.println("数据导出成功");
        } catch (Exception e) {
            System.out.println("请创建一个空文件");
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    private static Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){     //Excel 2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

}