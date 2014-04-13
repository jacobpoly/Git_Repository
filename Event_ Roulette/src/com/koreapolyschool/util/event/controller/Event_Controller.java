package com.koreapolyschool.util.event.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.koreapolyschool.util.event.service.Event_Service;
import com.koreapolyschool.util.event.view.GenericExcelView;
import com.koreapolyschool.util.event.vo.EventVO;
import com.koreapolyschool.util.event.vo.ExcelVO;
import com.koreapolyschool.util.event.vo.ProductVO;
import com.koreapolyschool.util.event.vo.ProgressDataVO;
import com.koreapolyschool.util.event.vo.StudentVO;

@Controller
public class Event_Controller {

	@Autowired
	private Event_Service event_Service;
	private StudentVO studentVO;
	private ProgressDataVO progressDataVO;
	
	private EventVO eventVO = new EventVO();
	
	private final Logger log = Logger.getLogger(this.getClass());   
	
	@RequestMapping("/e_gamestart.do")
	public ModelAndView gamestart(HttpServletRequest request) throws Exception{   
	//	log.info("================ Method Name : gamestart"); 
		
		ModelAndView start_mav = new ModelAndView("e_roulette");  
		String member_code = "09110400";  //  코드  학번 

		
		if (member_code != null || member_code !="") {
			
		studentVO = event_Service.mem2client(member_code);
		eventVO.setClient_code(studentVO.getClient_code());
		
		// 세션 추가 
		HttpSession session = request.getSession(true);
		session.setAttribute("client_code", studentVO.getClient_code());
		session.setAttribute("client_mem_code", studentVO.getClient_mem_code());
			    
			 	if (studentVO.getEnter_yn().equals("N") ) {
					// 미 응모자
					start_mav.addObject("event_yn", studentVO.getEnter_yn());
			 	}else if(studentVO.getEnter_yn().equals("Y")){
					// 응모자
					//start_mav.addObject("event_yn", studentVO.getEnter_yn());
			 		start_mav.addObject("event_yn", "N");
				}else{
					// 지원 되질 않는 학생
					start_mav.addObject("event_yn", "Y");
				}
			    
		} else {
			System.out.println("member_code is null");
			start_mav.addObject("event_yn", "Y");   // 미지원자 
		}
		return  start_mav;   
		
	}
	@RequestMapping("/e_participation.do")   // 참여하기  룰렛 기본 데이터 대입 
	public ModelAndView getJson(HttpServletRequest request) throws Exception{  
		  ModelAndView mv = new ModelAndView("jsonView1");  
		  HttpSession session =  request.getSession();
			session.setAttribute("memo",  request.getParameter("memo"));
		 
		  return mv;
		 } 
	
	@RequestMapping("/e_start_btn.do")
	public ModelAndView result(HttpServletRequest request) throws Exception {
		
		System.out.println("memo  ::"+  request.getSession().getAttribute("memo"));
		
	Map<String, Object> result_map = event_Service.op_Result((String) request.getSession().getAttribute("client_code"),
																						(String) request.getSession().getAttribute("memo"),
																						 (int) request.getSession().getAttribute("client_mem_code"));  // 캠퍼스의 확률을 연산
	
	System.out.println(result_map.get("result"));
	System.out.println(result_map.get("result_no"));
	
	ModelAndView result_mav = new ModelAndView("jsonView1");
	
	result_mav.addObject("result", result_map);
	
	return result_mav;
		
	}
	
	@RequestMapping(value = "/excel.do")
	public View selectExcel(@RequestParam Map<String, String> params,

			Map<String, Object> modelMap) throws Exception {

		List<String> colName = new ArrayList<String>();			// 컬럼
		
		List<ExcelVO> colValue =  event_Service.excel_list();

		colName.add("학번");
		colName.add("이름");
		colName.add("교육 상태");
		colName.add("캠퍼스명");
		colName.add("소속과정");
		colName.add("이벤트 상품");
		colName.add("로그인일시");
		colName.add("이벤트 응모 일시");
		colName.add("이벤트 메시지");
		colName.add("이벤트 응모여부");
		colName.add("주소");
		colName.add("연락처");

		modelMap.put("excelName", "event_excel");
		modelMap.put("colName", colName);
		modelMap.put("colValue", colValue);
		
		


		return new GenericExcelView();

	}

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
