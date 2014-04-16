package com.koreapolyschool.util.event.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.koreapolyschool.util.event.vo.ExcelVO;

public class GenericExcelView extends AbstractExcelView {
	private ExcelVO excelVo = null;
	private HSSFRow row = null;


	@Override
	protected void buildExcelDocument(Map<String, Object> modelMap,

			HSSFWorkbook workbook, HttpServletRequest req,

			HttpServletResponse res) throws Exception {


		String excelName = (String) modelMap.get("excelName");


		List<String> colName = (List<String>) modelMap.get("colName");

		List<ExcelVO> colValue = (List<ExcelVO>) modelMap.get("colValue");

		System.out.println("엑셀 그리기 시작" + getNow());

		res.setContentType("application/msexcel");

		res.setHeader("Content-Disposition", "attachment; filename="	+ excelName + ".xls");



		HSSFSheet sheet = workbook.createSheet(excelName);
	
		Font defaultFont = workbook.createFont();        
		defaultFont.setFontHeightInPoints((short) 11); 
		defaultFont.setFontName("맑은 고딕"); 
		
		System.out.println("엑셀 시트 생성" + getNow());
		//제목 스타일 
		CellStyle HeadStyle = workbook.createCellStyle(); 
		HeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
		HeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); 
		HeadStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index); 
		HeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); 
		HeadStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); 
		HeadStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); 
		HeadStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); 
		HeadStyle.setFillPattern(CellStyle.SOLID_FOREGROUND); 
		HeadStyle.setFont(defaultFont); 

		//본문 스타일 
		CellStyle BodyStyle = workbook.createCellStyle(); 
		BodyStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); 
		BodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); 
		BodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); 
		BodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); 
		BodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); 
		BodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); 
		BodyStyle.setFont(defaultFont);    



	
		// 상단 메뉴명 생성

		HSSFRow menuRow = sheet.createRow(0);

		for (int i = 0; i < colName.size(); i++) {

			HSSFCell cell = menuRow.createCell(i);

			cell.setCellStyle(HeadStyle); 
			cell.setCellValue(new HSSFRichTextString(colName.get(i)));

		}

		System.out.println("엑셀 메뉴 생성" + getNow());
		// 내용 생성

		for (int i = 0; i < colValue.size(); i++) {
			
			excelVo = colValue.get(i);		
			row = sheet.createRow(i+1);

				
			row.createCell(0).setCellValue(excelVo.getMember_code());
			row.createCell(1).setCellValue(excelVo.getName());
			row.createCell(2).setCellValue(excelVo.getEdu_state());
			row.createCell(3).setCellValue(excelVo.getCampus_name());
			row.createCell(4).setCellValue(excelVo.getAffiliate());
			row.createCell(5).setCellValue(excelVo.getProduct());
			row.createCell(6).setCellValue(excelVo.getLogin_date());
			row.createCell(7).setCellValue(excelVo.getParticipation());
			row.createCell(8).setCellValue(excelVo.getEvent_msg());
			row.createCell(9).setCellValue(excelVo.getEnter_yn());
			row.createCell(10).setCellValue(excelVo.getAddress());
			row.createCell(11).setCellValue(excelVo.getPhone());
			
				
		}
		System.out.println("엑셀 내용 생성" + getNow());
		
		for (int i = 0; i < colName.size(); i++){ 
		    sheet.autoSizeColumn(i); 
		    sheet.setColumnWidth(i, (sheet.getColumnWidth(i)) + 1000); 
		}
	

			
		System.out.println("엑셀 완료" + getNow());

	}

	
	public String getNow() {
		Date now = new Date(System.currentTimeMillis());

		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		return simpledateformat.format(now);
	}
}