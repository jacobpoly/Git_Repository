package com.koreapolyschool.util.event.dao;



import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreapolyschool.util.event.vo.EventVO;
import com.koreapolyschool.util.event.vo.ExcelVO;
import com.koreapolyschool.util.event.vo.ProgressDataVO;
import com.koreapolyschool.util.event.vo.StudentVO;

@Repository
public class Event_Dao {
	@Autowired
	private SqlSession session;
	private StudentVO studentVO;
	private ProgressDataVO progressDataVO;
	private List<ExcelVO> stList;
	private List<ProgressDataVO> pgList;
	
	public StudentVO sel_mem2client(String member_code) throws Exception{
		
		if (member_code != null || member_code != "" ) {
			
			
			studentVO =	session.selectOne("event_ns.sel_mem2client", member_code);
		}else{
			System.out.println(" member_code is null ");
		}
		
		return studentVO ;
	}
		
	
	public ProgressDataVO sel_progressData (String client_code) throws Exception{
		
		if (client_code != null || client_code != "" ) {
			progressDataVO =	session.selectOne("event_ns.sel_progressData", client_code);
		}else{
			System.out.println(" client_code is null ");
		}
		
		return progressDataVO;
		
	}
		
	public List<ProgressDataVO> sel_product_list (HashMap<String, String> params) throws Exception{
		
		System.out.println( 	params.get("client_code")	);
		System.out.println(	params.get("product_no"));
		
		if (params != null) {
			pgList =	session.selectOne("event_ns.sel_product_list", params);
		}else{
			System.out.println(" client_code is null ");
		}
		
		return pgList;
	}

	public ProgressDataVO sel_product (String client_code) throws Exception{
		
		if (client_code != null || client_code != "" ) {
			progressDataVO =	session.selectOne("event_ns.sel_product_list", client_code);
		}else{
			System.out.println(" client_code is null ");
		}
		
		return progressDataVO;
	}
	
	public List<ExcelVO>  sel_excel_list () throws Exception{
		
		
		stList = session.selectList("event_ns.sel_excel_list");
		
		return stList;
		
	}
	// 이벤트 응모 데이터 입력
	public void ins_eventMsg(EventVO eventVO) throws Exception{
		session.insert("event_ns.ins_eventMsg", eventVO);
	}
	
	// 상품 차감 캠퍼스 별
	public void ups_product_sub(EventVO eventVO) throws Exception{
		session.update("event_ns.ups_product_sub", eventVO);
	}
	// 대상자 차감
	public void ups_targerCnt_sub(EventVO eventVO) throws Exception{
		session.update("event_ns.ups_targerCnt_sub", eventVO);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
