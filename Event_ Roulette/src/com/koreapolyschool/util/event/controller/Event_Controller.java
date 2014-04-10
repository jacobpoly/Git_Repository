package com.koreapolyschool.util.event.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.koreapolyschool.util.event.vo.ProgressDataVO;
import com.koreapolyschool.util.event.vo.StudentVO;

@Controller
public class Event_Controller {

	@Autowired
	private Event_Service event_Service;
	private StudentVO studentVO;
	private ProgressDataVO progressDataVO;
	
	private final Logger log = Logger.getLogger(this.getClass());   
	
	@RequestMapping("/e_gamestart.do")
	public ModelAndView gamestart() throws Exception{   
	//	log.info("================ Method Name : gamestart"); 
		
		ModelAndView start_mav = new ModelAndView("e_roulette");  
		
		
		List<StudentVO> stlist = event_Service.excel_list();
		System.out.println(stlist.get(0).getEnter_yn());
		
		HashMap<String, String> params = new HashMap<>();

		params.put("client_code", "1234");
		params.put("product_no", "1");
		
		
		List<ProgressDataVO> pglist = event_Service.product_list(params);
		
	System.out.println(pglist);
		
		
		
	/*
		String member_code = "09110400";  //  코드  학번 

		if (member_code != null || member_code !="") {
			
			 	studentVO = event_Service.mem2client(member_code);
			    //progressDataVO = event_Service.progressData(studentVO.getClient_code());
			    
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
			start_mav.addObject("event_yn", "Y");
		}
	*/
		start_mav.addObject("event_yn", "N");
		return  start_mav;   
		
	}
	/*
	//@RequestMapping("/e_participation.do") // 참여 하기
	public ModelAndView participation(HttpServletRequest req){   // 참여하기를 클릭하면, 게임 스타트 버튼 활성화, 참여하기 버튼 비활성화, 메모 readOnly
		
		
		
		String memo = "";
		
		memo =  req.getParameter("memo");
		
		System.out.println("memo :: "+ memo);
		
		ModelAndView part_mav = new ModelAndView("e_roulette");
		
		EventVO event_vo = new EventVO(); 
		
			
	
	// 버튼 클릭 활성화
		part_mav.addObject("bt_start", "on");
		
		
		return  part_mav;   

	}

*/
	@RequestMapping("/e_result.do")
	public ModelAndView result() {
		
		
		
		
		
		
		
													// 정지 버튼이 클릭시, 현재 각도가 몫이 0일때 까지만 진행 하며, 몫이 0일때 발생하는 컨트롤러 이다. 
													// 확률을 연산 후, 
													// 당첨된 인덱스 번호로 정지 해야될 각도를 랜덤하게 추출한다.
													// 서서히 천천히 회전 할 회전 수를 랜덤으로 4~6바퀴 정도 연산을 하며, 
													// 각도의 변화 정도로 시간을 구한다.
													// 구해진 시간 동안 서서히 돌며, 멈춘다.
		
	ModelAndView result_mav = new ModelAndView();
	
		
	
	return result_mav;
		
	}
	//@RequestMapping("/jsonTest.do")
	@RequestMapping("/e_participation.do")
	public ModelAndView getJson(HttpServletRequest request) throws Exception{  
		  ModelAndView mv = new ModelAndView();  
		  
		  String memo = request.getParameter("memo");
		  System.out.println("memo :: "+ memo);
		  List<String> list = new ArrayList<String>();
		  list.add("객체1");
		  list.add("객체2");
		  list.add("객체3");
		  list.add("객체4");
		  list.add("객체5");
		  
		  Map<String, String> map = new HashMap<String, String>();
		  map.put("num", "10");
		  map.put("name", "hyunjo");
		  map.put("id", "systemddc");
		  map.put("age", "33");
		  map.put("job", "developer");
		  
		  mv.addObject("obj1", list); 
		  mv.addObject("obj2", map);
		  
		  // {"obj1":["객체1","객체2","객체3","객체4","객체5"],"obj2":{"id":"systemddc","num":"10","age":"33","name":"hyunjo","job":"developer"}}
		  
		  mv.setViewName("jsonView1"); // bean으로 정의 된 뷰를 사용한다.
		  return mv;
		 } 
	
	@RequestMapping(value = "/excel.do")

	public View selectCombo(@RequestParam Map<String, String> params,

			Map<String, Object> modelMap) throws Exception {

		List<String> colName = new ArrayList<String>();			// 컬럼

		colName.add("1번");
		colName.add("2번");
		colName.add("3번");
		colName.add("4번");
		colName.add("5번");


		List<String[]> colValue = new ArrayList<String[]>();


		String[] arr1 = { "11111", "22222", "33333", "44444", "55555" };		// 데이터
		String[] arr2 = { "aaaaa", "bbbbb", "ccccc", "ddddd", "eeeee" };
		String[] arr3 = { "가가가", "나나나", "다다다", "라라라", "마마마" };

		colValue.add(arr1);
		colValue.add(arr2);
		colValue.add(arr3);

		modelMap.put("excelName", "test");
		modelMap.put("colName", colName);
		modelMap.put("colValue", colValue);


		return new GenericExcelView();

	}

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
