package com.koreapolyschool.util.event.service;



import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreapolyschool.util.event.dao.Event_Dao;
import com.koreapolyschool.util.event.vo.EventVO;
import com.koreapolyschool.util.event.vo.ExcelVO;
import com.koreapolyschool.util.event.vo.ProgressDataVO;
import com.koreapolyschool.util.event.vo.StudentVO;

@Service
public class Event_Service {
	
	@Autowired
	private Event_Dao event_dao;
	private StudentVO studentVO;
	private ProgressDataVO progressDataVO;
	private List<ExcelVO> stList;
	private List<ProgressDataVO> pgList;
	
	// 맴버코드 클라이언트코드로 변환
	public StudentVO mem2client(String member_code) throws Exception  {
			studentVO = (StudentVO)event_dao.sel_mem2client(member_code);
		return studentVO;
	}
	
	// 확률 연산 데이터 추출
	public ProgressDataVO progressData(String client_code) throws Exception  {
		
			progressDataVO =  (ProgressDataVO)event_dao.sel_progressData(client_code);
			
		return progressDataVO;
	}
	
	// 상품 리스트 추출
	public List<ProgressDataVO> product_list(HashMap<String, String> params) throws Exception  {
		
		pgList = event_dao.sel_product_list(params);
		
			 return pgList;
	}
	
	// 엑셀 데이터 추출
	public List<ExcelVO> excel_list () throws Exception{
		
		stList = event_dao.sel_excel_list();
		
		return stList;
	}
	
	// 이벤트 응모 데이터 입력
	public void eventMsg(EventVO eventVO) throws Exception{

		event_dao.ins_eventMsg(eventVO);
		
	}
	
	// 상품 차감 캠퍼스 별
	public void product_sub(EventVO eventVO) throws Exception {
			event_dao.ups_product_sub(eventVO);
	}
	// 대상자 차감
	public void targerCnt_sub(EventVO eventVO) throws Exception{
			event_dao.ups_targerCnt_sub(eventVO);
	}
	

}
