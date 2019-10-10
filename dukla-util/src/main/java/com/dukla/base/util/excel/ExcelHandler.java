package com.dukla.base.util.excel;

import com.dukla.base.util.Kit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Excel处理
 * by Dukla.ou
 */
public class ExcelHandler {
	
	private ExcelConfig config;

	public ExcelHandler (ExcelConfig config){
		this.config = config;
	}

	public void setExcelConfig(ExcelConfig config){
		this.config = config;
	}

	public ExcelConfig getExcelConfig(){
		return config;
	}

	/*
	 * 测试函数
	 */
	public static void main(String[] argus){
		//列定义
		List<ExcelColumn> excelColumnList=new ArrayList<ExcelColumn>();

		ExcelColumn cloumn=new ExcelColumn(0,"testStr", ExcelColumn.TYPE_STRING,20,false,"\\d+|\\d+(\\.\\d+)+","请输入文本型数据,非空,20字内,格式:x.x.x");
		excelColumnList.add(cloumn);

		cloumn=new ExcelColumn(1,"testNum", ExcelColumn.TYPE_NUMBER,0,false,null,"请输入数字型数据,非空");
		excelColumnList.add(cloumn);

		cloumn=new ExcelColumn(2,"testDate", ExcelColumn.TYPE_DATE,0,false,null,"请输入日期型数据,非空");
		excelColumnList.add(cloumn);

		//配置处理器
		ExcelConfig config=new ExcelConfig();
		config.setOfficeVer(ExcelConfig.OFFICE_2003);
		config.setExcelColumnList(excelColumnList);
		config.setStartRowNum(2);
		config.setEndRowNum(100);
		config.setFileSize(2048000L);
		config.setVerifyFilePath("d:\\");
		config.setVerifyFileName("测试验证文件.xls");
		config.setWriteFilePath("d:\\");
		config.setWriteFileName("测试输出文件.xls");

		ExcelHandler handler=new ExcelHandler(config);
		try{
			//输出测试数据
//			List<Map<String,Object>> dataList=new ArrayList<Map<String,Object>>();
//
//			Map<String,Object> data=new HashMap<String,Object>();
//			data.put("testStr", "测试字符传1");
//			data.put("testNum", 1);
//			data.put("testDate", new Date());
//			dataList.add(data);
//
//			data=new HashMap<String,Object>();
//			data.put("testStr", "测试字符传2");
//			data.put("testNum", 100000L);
//			data.put("testDate", new Date());
//			dataList.add(data);
//
//			data=new HashMap<String,Object>();
//			data.put("testStr", "测试字符传3");
//			data.put("testNum", 1.02);
//			data.put("testDate", new Date());
//			dataList.add(data);
//
//			data=new HashMap<String,Object>();
//			data.put("testStr", "测试字符传4");
//			data.put("testNum", new BigDecimal(1));
//			data.put("testDate", new Date());
//			dataList.add(data);
//
//			data=new HashMap<String,Object>();
//			data.put("testStr", "测试字符传5");
//			data.put("testNum", new BigDecimal(1.0101));
//			data.put("testDate", new Date());
//			dataList.add(data);

			//输出测试(无模板)
//			List<ExcelMessage> msgList=handler.writeExcel(dataList);

			//输出测试(有模板)
//			File file=new File("d:\\template.xls");
//			List<ExcelMessage> msgList=handler.writeExcel(file,dataList);

			//验证测试
			File file=new File("d:\\被验证文件.xls");
			List<ExcelMessage> msgList=handler.verifyExcel(file);

			//读取测试
//			File file=new File("d:\\测试输出文件.xls");
//			List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
//			List<ExcelMessage> msgList=handler.readExcel(file, result, true);
//			for(Map<String,Object> d:result){
//				System.out.print("testStr:"+Kit.getObjStr(d.get("testStr"))+",");
//				System.out.print("testNum:"+Kit.getObjDouble(d.get("testNum"))+",");
//				System.out.print("testDate:"+Kit.formatDateTime(Kit.getObjDate(d.get("testDate")),"yyyy-MM-dd")+",");
//				System.out.println();
//			}


			for(ExcelMessage msg:msgList){
				String msgLevel="";
				switch(msg.getLevel()){
				case ExcelMessage.LEVEL_ERROR:
					msgLevel="错误:";
					break;
				case ExcelMessage.LEVEL_WARN:
					msgLevel="警告:";
					break;
				case ExcelMessage.LEVEL_INFO:
					msgLevel="信息:";
					break;
				}
				System.out.println(msgLevel+msg.getMessage());
			}

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}


	/*
	 * 验证Excel数据
	 */
	public List<ExcelMessage> verifyExcel(File file) throws Exception{
		List<ExcelMessage> msgList=new ArrayList<ExcelMessage>();
		if(this.config==null){
			msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"配置对象不存在"));
		}else if(!file.exists()){
			msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"文件不存在"));
		}else{
			String fileExt=file.getName().substring(file.getName().lastIndexOf('.'));
			long fileSize=file.length();
			if(!".xls".equals(fileExt) && !".xlsx".equals(fileExt)){
				msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"非Excel文件(.xls或.xlsx)"));
			}else if(fileSize>this.config.getFileSize()){
				msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"文件超大(小于"+String.valueOf(this.config.getFileSize()/1024)+"K)"));
			}else{
				this.verifyExcel(new FileInputStream(file), fileExt,msgList);
			}
		}
		return msgList;
	}

	/*
	 * 验证Excel数据
	 */
	public List<ExcelMessage> verifyExcel(String fileName,long fileSize,InputStream in) throws Exception{
		List<ExcelMessage> msgList=new ArrayList<>();
		String fileExt=fileName.substring(fileName.lastIndexOf('.'));
		if(this.config==null){
			msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"配置对象不存在"));
		}else if(!".xls".equals(fileExt) && !".xlsx".equals(fileExt)){
			msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"非Excel文件(.xls或.xlsx)"));
		}else if(fileSize>this.config.getFileSize()){
			msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"文件超大(小于"+String.valueOf(this.config.getFileSize()/1024)+"K)"));
		}else{
			this.verifyExcel(in, fileExt,msgList);
		}
		return msgList;
	}

	/*
	 * 验证Excel数据
	 */
	private  void verifyExcel(InputStream in,String fileExt,List<ExcelMessage> msgList) throws Exception{
		Workbook workbook;
		if(".xls".equals(fileExt)){//2003文件
			workbook = new HSSFWorkbook(in);
		}else{//2007文件
			workbook = new XSSFWorkbook(in);
		}
		Sheet sheet=workbook.getSheetAt(0);
		CreationHelper factory = workbook.getCreationHelper();
		Drawing drawing = sheet.createDrawingPatriarch();
		int rows = sheet.getPhysicalNumberOfRows();
		Font font = workbook.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		if(rows<this.config.getStartRowNum()){
			msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"从第"+String.valueOf(this.config.getStartRowNum()+1)+"开始没有数据"));
	    }else{
	    	int endRowNum=(this.config.getEndRowNum()==0 || this.config.getEndRowNum()-this.config.getStartRowNum()>rows) ? rows : this.config.getEndRowNum();
	    	for (int r = this.config.getStartRowNum(); r < endRowNum; r++) {
	    		Row row = sheet.getRow(r);
				if (row == null) {
					endRowNum++;
					continue;
				}
	    		this.verifyRow(row, msgList,factory, drawing);
	    	}
    		msgList.add(new ExcelMessage(ExcelMessage.LEVEL_INFO,"验证了"+String.valueOf(endRowNum-this.config.getStartRowNum())+"条数据"));
	    }
		if(this.config.getVerifyFilePath()!=null && this.config.getVerifyFileName()!=null){
			FileOutputStream fileOut = new FileOutputStream(this.config.getVerifyFilePath()+this.config.getVerifyFileName());
			workbook.write(fileOut);
			fileOut.close();
		}
	}

	/*
	 * 验证行
	 */
	private void verifyRow(Row row,List<ExcelMessage> msgList,CreationHelper factory, Drawing drawing) throws Exception{
		int cells = this.config.getExcelColumnList().size();// 列数
		for (int c = 0; c < cells; c++) {
			ExcelColumn column=this.config.getExcelColumnList().get(c);
			Cell cell = row.getCell(column.getColNum());
			if (cell == null) {
				cell = row.createCell(c, Cell.CELL_TYPE_STRING);
				cell.setCellValue("");
			}
			this.verifyCell(cell,column,msgList,factory, drawing);
		}

	}

	/*
	 * 验证列
	 */
	private void verifyCell(Cell cell,ExcelColumn column,List<ExcelMessage> msgList,CreationHelper factory, Drawing drawing) throws Exception{
		switch (column.getColType()){
		case ExcelColumn.TYPE_STRING :
			if(cell.getCellType()==Cell.CELL_TYPE_STRING){
				String cellValue=cell.getStringCellValue();
				if(!column.isEmpty() && (cellValue==null || cellValue.trim().length()==0)){
					msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"第"+String.valueOf(cell.getRowIndex()+1)+"行,第"+String.valueOf(cell.getColumnIndex()+1)+"列:为空"));
					this.writeCellComment(factory, drawing, cell, column.getVerifyMsg());
				}else if(cellValue.length()>column.getLength()){
					msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"第"+String.valueOf(cell.getRowIndex()+1)+"行,第"+String.valueOf(cell.getColumnIndex()+1)+"列:超长"));
					this.writeCellComment(factory, drawing, cell, column.getVerifyMsg());
				}else if(column.getRegularExp()!=null  && cellValue!=null && cellValue.trim().length()!=0){
					Pattern p=Pattern.compile(column.getRegularExp());
					Matcher m=p.matcher(cellValue);
					if(!m.matches()){
						msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"第"+String.valueOf(cell.getRowIndex()+1)+"行,第"+String.valueOf(cell.getColumnIndex()+1)+"列:格式错误"));
						this.writeCellComment(factory, drawing, cell, column.getVerifyMsg());
					}
				}
			}else{
				msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"第"+String.valueOf(cell.getRowIndex()+1)+"行,第"+String.valueOf(cell.getColumnIndex()+1)+"列:类型错误(文本型)"));
				this.writeCellComment(factory, drawing, cell, column.getVerifyMsg());
			}
			break;
		case ExcelColumn.TYPE_NUMBER :
			if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
				Double cellValue=cell.getNumericCellValue();
				if(!column.isEmpty() && cellValue==null){
					msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"第"+String.valueOf(cell.getRowIndex()+1)+"行,第"+String.valueOf(cell.getColumnIndex()+1)+"列:为空"));
					this.writeCellComment(factory, drawing, cell, column.getVerifyMsg());
				}
			}else{
				msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"第"+String.valueOf(cell.getRowIndex()+1)+"行,第"+String.valueOf(cell.getColumnIndex()+1)+"列:类型错误(数字型)"));
				this.writeCellComment(factory, drawing, cell, column.getVerifyMsg());
			}
			break;
		case ExcelColumn.TYPE_DATE :
			if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
				Double cellValue=cell.getNumericCellValue();
				if(!column.isEmpty() && cellValue==null){
					msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"第"+String.valueOf(cell.getRowIndex()+1)+"行,第"+String.valueOf(cell.getColumnIndex()+1)+"列:为空"));
					this.writeCellComment(factory, drawing, cell, column.getVerifyMsg());
				}else if(cellValue!=null && !DateUtil.isValidExcelDate(cellValue)){
					msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"第"+String.valueOf(cell.getRowIndex()+1)+"行,第"+String.valueOf(cell.getColumnIndex()+1)+"列:非法的日期"));
					this.writeCellComment(factory, drawing, cell, column.getVerifyMsg());
				}
			}else{
				msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"第"+String.valueOf(cell.getRowIndex()+1)+"行,第"+String.valueOf(cell.getColumnIndex()+1)+"列:类型错误(日期型)"));
				this.writeCellComment(factory, drawing, cell, column.getVerifyMsg());
			}
			break;
		default:
			break;
		}
	}

	//写Cell注释
	private void writeCellComment(CreationHelper factory,Drawing drawing,Cell cell,String verifyMsg){
		ClientAnchor anchor = factory.createClientAnchor();
		anchor.setCol1(0);
		anchor.setCol2(2);
		anchor.setRow1(0);
		anchor.setRow2(2);
		Comment comment = drawing.createCellComment(anchor);
		comment.setAuthor("系统提示");
		RichTextString commentStr = factory.createRichTextString(verifyMsg);
		comment.setString(commentStr);
		cell.setCellComment(comment);
	}

	/*
	 * 读取Excel数据
	 */
	public List<ExcelMessage> readExcel(File file,List<Map<String,Object>> result,boolean needVerify) throws Exception{
		List<ExcelMessage> msgList=new ArrayList<ExcelMessage>();
		if(needVerify){
			msgList=this.verifyExcel(file);
		}
		boolean passed=true;
		for(ExcelMessage msg:msgList){
			if(msg.getLevel()== ExcelMessage.LEVEL_ERROR){
				passed=false;
				break;
			}
		}
		if(passed){
			if(this.config==null){
				msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"配置对象不存在"));
			}else if(!file.exists()){
				msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"文件不存在"));
			}else{
				String fileExt=file.getName().substring(file.getName().lastIndexOf('.'));
				long fileSize=file.length();
				if(!".xls".equals(fileExt) && !".xlsx".equals(fileExt)){
					msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"非Excel文件(.xls或.xlsx)"));
				}else if(fileSize>this.config.getFileSize()){
					msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"文件超大(小于"+String.valueOf(this.config.getFileSize()/1024)+"K)"));
				}else{
					this.readExcel(new FileInputStream(file),fileExt,result,msgList);
				}
			}
		}
		return msgList;
	}

	/*
	 * 读取Excel数据
	 */
	public List<ExcelMessage> readExcel(String fileName,long fileSize,InputStream dataFileIn,List<Map<String,Object>> result,boolean needVerify) throws Exception{
		List<ExcelMessage> msgList=new ArrayList<>();
        byte[] b=new byte[new Long(fileSize).intValue()];
        dataFileIn.read(b);
        dataFileIn.close();
        InputStream byteArrayInputStream=new ByteArrayInputStream(b);
		if(needVerify){
			msgList=this.verifyExcel(fileName,fileSize,byteArrayInputStream);
		}
		boolean passed=true;
		for(ExcelMessage msg:msgList){
			if(msg.getLevel()== ExcelMessage.LEVEL_ERROR){
				passed=false;
				break;
			}
		}
		if(passed){
			if(this.config==null){
				msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"配置对象不存在"));
			}else{
				String fileExt=fileName.substring(fileName.lastIndexOf('.'));
				if(!".xls".equals(fileExt) && !".xlsx".equals(fileExt)){
					msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"非Excel文件(.xls或.xlsx)"));
				}else if(fileSize>this.config.getFileSize()){
					msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"文件超大(小于"+String.valueOf(this.config.getFileSize()/1024)+"K)"));
				}else{
                    byteArrayInputStream.reset();
					this.readExcel(byteArrayInputStream,fileExt,result,msgList);
				}
			}
		}
		return msgList;
	}

	/*
	 * 读取Excel数据
	 */
	private void readExcel(InputStream in,String fileExt,List<Map<String,Object>> result,List<ExcelMessage> msgList) throws Exception{
		Workbook workbook;
		if(".xls".equals(fileExt)){//2003文件
			workbook = new HSSFWorkbook(in);
		}else{//2007文件
			workbook = new XSSFWorkbook(in);
		}
		Sheet sheet=workbook.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		if(rows<this.config.getStartRowNum()){
			msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"从第"+String.valueOf(this.config.getStartRowNum()+1)+"开始没有数据"));
	    }else{
	    	int endRowNum=(this.config.getEndRowNum()==0 || this.config.getEndRowNum()-this.config.getStartRowNum()>rows) ? rows : this.config.getEndRowNum();
	    	for (int r = this.config.getStartRowNum(); r < endRowNum; r++) {
	    		Row row = sheet.getRow(r);
				if (row == null) {
					endRowNum++;
					continue;
				}
	    		this.readRow(row,result);
	    	}
			msgList.add(new ExcelMessage(ExcelMessage.LEVEL_INFO,"读取了"+String.valueOf(endRowNum-this.config.getStartRowNum())+"条数据"));
	    }
	}

	/*
	 * 读取行
	 */
	private void readRow(Row row,List<Map<String,Object>> result) throws Exception{
		Map<String,Object> data=new HashMap<String,Object>();
		int cells = this.config.getExcelColumnList().size();// 列数
		for (int c = 0; c < cells; c++) {
			ExcelColumn column=this.config.getExcelColumnList().get(c);
			Cell cell = row.getCell(column.getColNum());
			if (cell == null) {
				data.put(column.getColName(), null);
			}else{
				this.readCell(cell,column,data);
			}
		}
		result.add(data);
	}

	/*
	 * 读取列
	 */
	private void readCell(Cell cell,ExcelColumn column,Map<String,Object> data) throws Exception{
		switch (column.getColType()){
		case ExcelColumn.TYPE_STRING :
			data.put(column.getColName(), cell.getStringCellValue());
			break;
		case ExcelColumn.TYPE_NUMBER :
			data.put(column.getColName(), cell.getNumericCellValue());
			break;
		case ExcelColumn.TYPE_DATE :
			if(DateUtil.isValidExcelDate(cell.getNumericCellValue())){
				data.put(column.getColName(), DateUtil.getJavaDate(cell.getNumericCellValue()));
			}else{
				data.put(column.getColName(),null);
			}
			break;
		default:
			break;
		}
	}


	/*
	 * 写Excel数据
	 */
	public List<ExcelMessage> writeExcel(File template,List<Map<String,Object>> dataList) throws Exception{
		List<ExcelMessage> msgList=new ArrayList<ExcelMessage>();
		if(this.config==null){
			msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"配置对象不存在"));
		}else if(!template.exists()){
			msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"输出模板文件不存在"));
		}else if(this.config.getWriteFilePath()==null || this.config.getWriteFileName()==null){
			msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"未定义输出路径和输出文件名"));
		}else{
			InputStream in=new FileInputStream(template);
			this.writeExcel(in,dataList, msgList);
		}

		return msgList;
	}

	/*
	 * 写Excel数据
	 */
	public List<ExcelMessage> writeExcel(List<Map<String,Object>> dataList) throws Exception{
		List<ExcelMessage> msgList=new ArrayList<ExcelMessage>();
		if(this.config==null){
			msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"配置对象不存在"));
		}else if(this.config.getWriteFilePath()==null || this.config.getWriteFileName()==null){
			msgList.add(new ExcelMessage(ExcelMessage.LEVEL_ERROR,"未定义输出路径和输出文件名"));
		}else{
			this.writeExcel(null,dataList, msgList);
		}
		return msgList;
	}

	/*
	 * 写Excel数据
	 */
	private void writeExcel(InputStream in,List<Map<String,Object>> dataList,List<ExcelMessage> msgList) throws Exception{
		Workbook workbook;
		if(this.config.getOfficeVer()== ExcelConfig.OFFICE_2003){
			if(in!=null){
				workbook = new HSSFWorkbook(in);
			}else{
				workbook = new HSSFWorkbook();
			}
		}else{//2007文件
			if(in!=null){
				workbook = new XSSFWorkbook(in);
			}else{
				workbook = new XSSFWorkbook();
			}
		}
		Sheet sheet;
		if(in==null){
			sheet = workbook.createSheet("数据");
		}else{
			sheet=workbook.getSheetAt(0);
		}
		for(int i=0;i<dataList.size();i++){
			Map<String,Object> data=dataList.get(i);
			Row row=sheet.createRow(this.config.getStartRowNum()+i);
			this.writeRow(row, data, msgList);
		}
		OutputStream out=new FileOutputStream(this.config.getWriteFilePath()+this.config.getWriteFileName());
		workbook.write(out);
		out.close();
		if(msgList.size()==0){
			msgList.add(new ExcelMessage(ExcelMessage.LEVEL_INFO,"成功导出"+String.valueOf(dataList.size())+"条记录到["+this.config.getWriteFilePath()+this.config.getWriteFileName()+"]文件中"));
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
		for(ExcelColumn column:this.config.getExcelColumnList()){
			if(colName.equals(column.getColName())){
				return column;
			}
		}
		return null;
	}

	/*
	 * 写列数据
	 */
	private void writeCell(Cell cell,Object value,ExcelColumn column,List<ExcelMessage> msgList) throws Exception{
		switch (column.getColType()){
		case ExcelColumn.TYPE_STRING :
			cell.setCellValue(Kit.getObjStr(value));
			break;
		case ExcelColumn.TYPE_NUMBER :
			if(value instanceof Double){
				cell.setCellValue((Double)value);
			}else if(value instanceof Long){
				cell.setCellValue((Long)value);
			}else if(value instanceof Integer){
				cell.setCellValue((Integer)value);
			}else if(value instanceof BigDecimal){
				cell.setCellValue(((BigDecimal)value).doubleValue());
			}
			break;
		case ExcelColumn.TYPE_DATE :
			if(value instanceof Date){
				Workbook wb=cell.getRow().getSheet().getWorkbook();
				CreationHelper createHelper = wb.getCreationHelper();
				CellStyle cellStyle = wb.createCellStyle();
				cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd"));
				cell.setCellStyle(cellStyle);
				cell.setCellValue((Date) value);
			}else{
				msgList.add(new ExcelMessage(ExcelMessage.LEVEL_WARN,"第"+String.valueOf(cell.getRowIndex()-this.config.getStartRowNum())+"行,列:["+column.getColName()+"]非时间类型"));
			}
			break;
		default:
			break;
		}
	}
	
}
