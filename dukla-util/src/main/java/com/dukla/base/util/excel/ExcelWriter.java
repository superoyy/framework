package com.dukla.base.util.excel;

import com.dukla.base.util.Kit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Excel文件写入器
 */
public class ExcelWriter {

    private DataScheme dataScheme;//数据定义

    private FileInputStream templateFileIns;//写入模板文件

    public ExcelWriter(DataScheme dataScheme,FileInputStream templateFileIns){
        this.dataScheme = dataScheme;
        this.templateFileIns = templateFileIns;
    }

    public ExcelWriter(DataScheme dataScheme){
        this.dataScheme = dataScheme;
        this.templateFileIns = null;
    }

    /**
     * 写入Excel数据
     */
    public void write(String targetFileUrl,
                      int sheetIndex,
                      int startRowIndex,
                      DataValues values) throws IOException{
        File targetFile=new File(targetFileUrl);
        if(!targetFile.exists()){
            targetFile.createNewFile();
        }



    }


    /*
     * 写行数据
     */
    private void writeRow(Row row,Map<String,Object> data,List<ExcelMessage> msgList) throws Exception{
        Set<String> keySet=data.keySet();
        for(String key:keySet){
            ExcelColumn column=this.getColumn(key);
            if(column!=null){
                Cell cell=row.createCell(column.getColNum());
                Object value=data.get(key);
                if(value!=null){
                    this.writeCell(cell, value,column, msgList);
                }
            }else{
                msgList.add(new ExcelMessage(ExcelMessage.LEVEL_WARN,"列:["+key+"]未定义"));
            }
        }
    }

    /*
     * 得到列定义
     */
    private ExcelColumn getColumn(String colName){
//        for(ExcelColumn column:this.config.getExcelColumnList()){
//            if(colName.equals(column.getColName())){
//                return column;
//            }
//        }
        return null;
    }

    /*
     * 写列数据
     */
    private void writeCell(Cell cell,Object value,ExcelColumn column,List<ExcelMessage> msgList) throws Exception{
//        switch (column.getColType()){
//            case ExcelColumn.TYPE_STRING :
//                cell.setCellValue(Kit.getObjStr(value));
//                break;
//            case ExcelColumn.TYPE_NUMBER :
//                if(value instanceof Double){
//                    cell.setCellValue((Double)value);
//                }else if(value instanceof Long){
//                    cell.setCellValue((Long)value);
//                }else if(value instanceof Integer){
//                    cell.setCellValue((Integer)value);
//                }else if(value instanceof BigDecimal){
//                    cell.setCellValue(((BigDecimal)value).doubleValue());
//                }
//                break;
//            case ExcelColumn.TYPE_DATE :
//                if(value instanceof Date){
//                    Workbook wb=cell.getRow().getSheet().getWorkbook();
//                    CreationHelper createHelper = wb.getCreationHelper();
//                    CellStyle cellStyle = wb.createCellStyle();
//                    cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd"));
//                    cell.setCellStyle(cellStyle);
//                    cell.setCellValue((Date) value);
//                }else{
//                    msgList.add(new ExcelMessage(ExcelMessage.LEVEL_WARN,"第"+String.valueOf(cell.getRowIndex()-this.config.getStartRowNum())+"行,列:["+column.getColName()+"]非时间类型"));
//                }
//                break;
//            default:
//                break;
//        }
    }

}
