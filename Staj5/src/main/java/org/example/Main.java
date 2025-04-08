import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.*;

public static void main(String[] args)
{
    try
    {
        FileInputStream fis=new FileInputStream("input.xlsx");
        XSSFWorkbook workbook=new XSSFWorkbook(fis);
        XSSFSheet sheet=workbook.getSheetAt(0);

        List<Row> filteredRows=new ArrayList<>();
        double sum=0;
        int count=0;

        for(Row row:sheet)
        {
            if(row.getRowNum()==0) continue;

            Cell priceCell=row.getCell(1);
            if(priceCell!=null&&priceCell.getCellType()==CellType.NUMERIC)
            {
                double price=priceCell.getNumericCellValue();
                if(price>100)
                {
                    filteredRows.add(row);
                    sum+=price;
                    count++;
                }
            }
        }

        double average=count>0?sum/count:0;
        System.out.println("Average price:"+average);

        XSSFWorkbook newWorkbook=new XSSFWorkbook();
        XSSFSheet newSheet=newWorkbook.createSheet("Filtered");

        Row header=sheet.getRow(0);
        Row newHeader=newSheet.createRow(0);
        for(int i=0;i<header.getLastCellNum();i++)
        {
            Cell cell=newHeader.createCell(i);
            cell.setCellValue(header.getCell(i).toString());
        }

        int rowIndex=1;
        for(Row originalRow:filteredRows)
        {
            Row newRow=newSheet.createRow(rowIndex++);
            for(int i=0;i<originalRow.getLastCellNum();i++)
            {
                Cell oldCell=originalRow.getCell(i);
                Cell newCell=newRow.createCell(i);
                if(oldCell!=null)
                {
                    switch(oldCell.getCellType())
                    {
                        case STRING:
                            newCell.setCellValue(oldCell.getStringCellValue());
                            break;
                        case NUMERIC:
                            newCell.setCellValue(oldCell.getNumericCellValue());
                            break;
                    }
                }
            }
        }

        Row avgRow=newSheet.createRow(rowIndex+1);
        avgRow.createCell(0).setCellValue("Average:");
        avgRow.createCell(1).setCellValue(average);

        FileOutputStream fos=new FileOutputStream("filtered_output.xlsx");
        newWorkbook.write(fos);
        fos.close();
        newWorkbook.close();
        workbook.close();
        fis.close();

        System.out.println("Filtered data written to filtered_output.xlsx");
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
}
