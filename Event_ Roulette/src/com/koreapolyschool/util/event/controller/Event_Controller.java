package com.koreapolyschool.util.event.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import com.koreapolyschool.util.event.other.XOR_java;
import com.koreapolyschool.util.event.service.Event_Service;
import com.koreapolyschool.util.event.view.GenericExcelView;
import com.koreapolyschool.util.event.vo.EventVO;
import com.koreapolyschool.util.event.vo.ExcelVO;
import com.koreapolyschool.util.event.vo.ProgressDataVO;
import com.koreapolyschool.util.event.vo.StudentVO;

import java.net.*;
@Controller
public class Event_Controller {

	@Autowired
	private Event_Service event_Service;
	private StudentVO studentVO;
	private ProgressDataVO progressDataVO;

	private EventVO eventVO = new EventVO();

	private final Logger log = Logger.getLogger(this.getClass());

	@RequestMapping("/e_gamestart.do")
	public ModelAndView gamestart(HttpServletRequest request) throws Exception {
		// log.info("================ Method Name : gamestart");

	
		String xor_client_mem_code = request.getParameter("_client_mem_code");	
		System.out.println("Parameter :: "+xor_client_mem_code);
		ModelAndView start_mav = new ModelAndView("e_roulette");

		if (xor_client_mem_code != null) {
			System.out.println("e_gamestart.do  시작   ====================="+getNow());
			String client_mem_code = xor_client_mem_code;

			if (client_mem_code.equals("4812")) { // 관리자

				System.out.println("관리자 엑셀");

				return new ModelAndView("event_excel");

			} else { // if (client_mem_code != null || client_mem_code != "")

				System.out.println(client_mem_code+  ":: client_mem_code :: 조회 준비 ::"+ getNow());
				studentVO = event_Service.mem2client(client_mem_code);
				
				if (studentVO != null) { 
					// 응모 이력 정보를 못 가지고 오는 클라이언 코드 일 경우
					//eventVO.setClient_code(studentVO.getClient_code());
					System.out.println(studentVO.getClient_mem_code() +"::조회 완료 ::"+ getNow());
					// 세션 추가
					/*
					HttpSession session = request.getSession(true);
					session.setAttribute("client_code",				studentVO.getClient_code());
					session.setAttribute("client_mem_code",	studentVO.getClient_mem_code());
					session.setAttribute("student_stt_code",	studentVO.getStudent_stt_code());
					session.setAttribute("in_college_yn",			studentVO.getIn_college_yn());
					*/
					
					System.out.println("학생 상태 코드 :: " 	+ studentVO.getStudent_stt_code());
					System.out.println("응모 여부 :: " + studentVO.getEnter_yn());
					if (studentVO.getEnter_yn().equals("N")) {
						System.out.println("학생 : 미응모자 ");
						start_mav.addObject("event_yn", 		studentVO.getEnter_yn());
						
						start_mav.addObject	("client_code",		studentVO.getClient_code());
						start_mav.addObject	("client_mem_code",	studentVO.getClient_mem_code());
						start_mav.addObject	("student_stt_code",studentVO.getStudent_stt_code());
						start_mav.addObject	("in_college_yn",	studentVO.getIn_college_yn());
						
					} else if (studentVO.getEnter_yn().equals("Y")) {
						System.out.println("학생 : 응모자 ");
						// 응모자
						start_mav.addObject("event_yn", studentVO.getEnter_yn());
						// start_mav.addObject("event_yn", "N");
					} else {
						System.out.println("학생 : 해석 할수 없는 코드");
						// 지원 되질 않는 학생
						start_mav.addObject("event_yn", "Y");
					}

				} else { // 응모 이력 정보를 못 가지고 오는 클라이언 코드 일 경우, 응모 한 사람으로 체크한다.

					System.out.println("코드는 있지만 관리자가 아니며, 해석 할수 없는 코드");
					start_mav.addObject("event_yn", "Y"); // 미지원자
				}
				System.out.println("e_gamestart.do  끝   ====================="+getNow());
				
				return start_mav;

			}
			/*
			 * else { // 클라이트 코드가 존재하는 않는 사람 return new
			 * ModelAndView("e_roulette"); }
			 */

		} else {

			System.out.println("널~~");
			return new ModelAndView("empty");
		}

	}

	@RequestMapping("/e_participation.do")
	// 참여하기 룰렛 기본 데이터 대입
	public ModelAndView getJson(HttpServletRequest request) throws Exception {
	
		System.out.println("e_participation.do  시작   ====================="+getNow());
		ModelAndView mv = new ModelAndView("jsonView1");
		System.out.println("TEST memo ::"+ request.getParameter("memo"));

	//	System.out.println("접속 아이피 :: " + ip);

	//	mv.addObject("ip", ip);
		mv.addObject("e_start_btn", "e_start_btn");
		mv.addObject("on", "on");

		System.out.println("e_participation.do  끝   ====================="+getNow());
		return mv;
	}

	@RequestMapping("/e_start_btn.do")
	public ModelAndView result(HttpServletRequest request) throws Exception {

		String 	memo = request.getParameter("memo");
		String 	client_code = request.getParameter("client_code");
		String 	student_stt_code = request.getParameter("student_stt_code");
		int 	client_mem_code = Integer.parseInt(request.getParameter("client_mem_code"));
		
		System.out.println("e_start_btn.do  시작   ====================="+getNow());
		System.out.println("memo  ::" + memo);
		System.out.println("client_code  ::" +client_code);
		System.out.println("student_stt_code ::" + student_stt_code);
		System.out.println("client_mem_code ::" + client_mem_code);
	
		String ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	//	System.out.println("HTTP_X_FORWARDED_FOR :: " + ip);

		if (ip == null || ip.length() == 0
				|| ip.toLowerCase().equals("unknown")) {
			ip = request.getHeader("REMOTE_ADDR");
		//	System.out.println(" REMOTE_ADDR :: " + ip);
		}

		if (ip == null || ip.length() == 0 || ip.toLowerCase().equals("unknown")) {
			ip = request.getRemoteAddr();
		//	System.out.println(" :: " + ip);
		}
		
			System.out.println("ip ::" +ip);
			
			ModelAndView result_mav = new ModelAndView("jsonView1");
			Map<String, Object> result_map = new HashMap<>();
				
			
		result_map = event_Service.op_Result(
				client_code,
				memo,
				client_mem_code,
				student_stt_code,
				ip);
															
		int result_no = (int) result_map.get("result_no") + 1;

		result_map.put("result_no", result_no);
		System.out.println("전송 결과");
		System.out.println("===================================");
		System.out.println("1 ::" + result_map.get("result"));
		System.out.println("2 ::" + result_no);
		System.out.println("3 ::" + result_map.get("result_txt"));
		System.out.println("===================================");
		result_mav.addObject("result", result_map);

		System.out.println("e_start_btn.do  끝   ====================="+getNow());
		return result_mav;

	}

	@RequestMapping(value = "/excel.do")
	public View selectExcel(@RequestParam Map<String, String> params,

	Map<String, Object> modelMap) throws Exception {

		System.out.println("엑셀 시작" + getNow());

		List<String> colName = new ArrayList<String>(); // 컬럼

		List<ExcelVO> colValue = event_Service.excel_list();

		System.out.println("엑셀 DB 조회 완료" + getNow());
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

	public String getNow() {
		Date now = new Date(System.currentTimeMillis());

		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		return simpledateformat.format(now);
	}
}
