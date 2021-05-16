package lab11_3;

import java.util.List;
import java.util.Arrays;

public class Test 
{
	public static void main(String[] args) 
	{
        List<List<String>> resultList = ExcelUtil.readExcel("src/lab11_3/studentInformation.xlsx");
        if (resultList != null) 
        {
            for (int i = 0; i < resultList.size(); i++) 
            {
                List<String> cellList = resultList.get(i);
                //String str = cellList.get(0) + " " + cellList.get(1) + " " + cellList.get(2) + " " + cellList.get(3);
                System.out.println(cellList);
            }
            List<String> addList = Arrays.asList("a","b","c","d");
            resultList.add(addList);
            List<String> addList1 = Arrays.asList("1","2","3","4");
            resultList.add(addList1);
        }

        //把读取的结果在写回去
        ExcelUtil.writeExcel(resultList, "src/lab11_3/studentInformation.xlsx");
    }
}
