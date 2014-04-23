package com.koreapolyschool.util.event.service;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koreapolyschool.util.event.dao.Event_Dao;
import com.koreapolyschool.util.event.vo.EventVO;
import com.koreapolyschool.util.event.vo.ExcelVO;
import com.koreapolyschool.util.event.vo.ProductVO;
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

	public int[] intConvert_Arr(List<ProductVO> op_pdlist) {

		//System.out.println(op_pdlist.size());

		int[] product_Arr = new int[op_pdlist.size()];

		for (int i = 0; i < op_pdlist.size(); i++) {
			product_Arr[i] = op_pdlist.get(i).getLeft_product_cnt();
		}

		return product_Arr;
	}

	public String[] strConvert_Name(List<ProductVO> op_pdlist) {

		String[] product_Name = new String[op_pdlist.size()];

		for (int i = 0; i < op_pdlist.size(); i++) {
			product_Name[i] = op_pdlist.get(i).getProduct_name();
		}

		return product_Name;
	}

	/*
	 * int int_arr_Total(int[] int_arr) {
	 * 
	 * int total = 0;
	 * 
	 * for (int i = 0; i < int_arr.length; i++) { total += int_arr[i]; }
	 * 
	 * return total; }
	 */
	// 확률 연산 메서드
	public static double randomRange(double n1, double n2) {
		return (Math.random() * (n2 - n1 + 1)) + n1;
	}

	// 당첨 상품에 회전 값 변경으로
	double changedTime(int product_num) {

		double resultTime;

		switch (product_num) {

		case 0:
			resultTime = randomRange(3680, 3810); // 6개월 기본
			break;
		case 1:
			resultTime = randomRange(3440, 3560); // 10만원
			break;
		case 2:
			resultTime = randomRange(3200, 3330); // 5만원
			break;
		case 3:
			resultTime = randomRange(2960, 3090); // 3만원
			break;
		case 4:
			resultTime = randomRange(2720, 2850); // 1만원
			break;
		case 5:
			resultTime = randomRange(2480, 2610); // 자석 책갈피
			break;
		case 6:
			resultTime = randomRange(2240, 2370); // 폴리 기념품
			break;
		default:
			resultTime = randomRange(3920, 4040); // 다음 기회에
			break;
		}

		return resultTime;
	}

	// 확률 연산 처리 // 상품 확률 // 상품 리스트
	Map<String, Object> win_process(double[] productPers, int[] product) {

		Map<String, Object> result = new HashMap<String, Object>();

		for (int i = 0; i < productPers.length; i++) {

			if (Math.random() * 100 < productPers[i]) { // 당첨 되는 부분 // 상품 확률이
														// 높으면 당첨~~

				// product[i]--; // 당첨이 되면 그 상품을 차감 한다.
				System.out.println("================================");
				System.out.println(i + "  인덱스 번호");
				System.out.println(i + 1 + " 번째 상품이  당첨 되었습니다.");
				System.out.println("================================");

				System.out.println("회전 시간 :: " + changedTime(i));

				System.out.println("==============");

				System.out.println("확률 (" + productPers[i] + ")  ");

				result.put("result", changedTime(i));
				result.put("result_no", i);

				// 데이터 점검~~

				return result;

			} else {
				// System.out.println(i + 1 + " 차 시도 ");
				if (i >= productPers.length - 1) { // 7이상이면 꽝처리 0~6 상품 7 꽝

					// System.out.println(productPers.length);

					System.out.println("다음 기회에~~!!!");

					result.put("result", changedTime(7));
					result.put("result_no", 7);
				}
				continue;
			}
		}

		return result;

	}

	// 확률 계산 (상품 리스트, 재학생 총, 응모자 수, 가중치)
	double[] productPer(int[] product_Arr, int student_total,
			int student_enter, double addTimePer, String client_code)
			throws Exception {

		int product_Total = 0;
		// System.out.println("응모자 수 :: " + student_enter);
		if (event_dao.sel_product_list(client_code).get(0).getLeft_product_total() != 0) {
			product_Total = event_dao.sel_product_list(client_code).get(0).getLeft_product_total();
		} else {
			product_Total = 1;
		}
		double[] result = new double[product_Arr.length];
		double student_per = 1; // 응모자수, 재학생 캠퍼스 수가 같을때
		if (student_total > student_enter) { // 응모자가 총 재학생 수 보다 적을때만 총 학생의 확률
												// 계산하고
	//		student_per = ((double) 1 / (student_total - student_enter)) * 100;
			student_per = ((double) 1 / (student_total - student_enter));
		} else { // 총 학생수 보다 응모자가 같거나 많다면, 미응모자가 없다는 것으로 판단하여, 100으로 한다.
	//		student_per = 1 * 100;
			student_per = 1;
			
		}

		// System.out.println("student_per :: " + student_per + " 남은 학생의 확률");
		// System.out.println(" (student_total - student_enter)  :: " +
		// (student_total - student_enter) + " 남은 학생 수");

		for (int y = 0; y < product_Arr.length; y++) {
			double product_per;

			if (product_Arr[y] == 0) {

			//	product_per = ((double) product_Arr[y] / 1) * 100; // 0개 일때 확률
																	// 연산
				product_per = ((double) product_Arr[y]); // 0개 일때 확률
				result[y] = (Double.parseDouble(String.format("%.2f", (double) product_per * student_per*100)));

				// System.out.println("product_Arr[" + y + "] 소진하거나 없는 상품 :: "+
				// product_Arr[y] + " 개수");
				// System.out.println("if  :: "+ product_Total);
				 System.out.println("	최종 확률 :: [" + y + "] :: " + result[y]);

			} else { // 한개 이상의 상품의 확률
				// System.out.println("product_Arr[" + y + "] 남은 상품  :: " +
				// product_Arr[y]);
				// System.out.println("else :: " + product_Total);

			//	product_per = ((double) product_Arr[y] / product_Total) * 100;

				product_per = ((double) product_Arr[y]);
				result[y] = (Double.parseDouble(String.format("%.2f", (double) product_per * student_per*100)));// + 0.16;// addTimePer; // 가중치
				// // 당첨률 낮추기

				// System.out.println("상품 확률 :: " + product_per);
				// System.out.println("재학생 (미응모자)확률 :: " + student_per);
				 System.out.println("	최종 확률 :: [" + y + "] :: " + result[y]);

			}
		}

		return result;

	}


	Map<String, Object> tran_chek(Map<String, Object> winPro, EventVO eventVO)
			throws Exception {

		if (eventVO.getStudent_stt_code().equals("31")
				|| eventVO.getStudent_stt_code().equals("32")
				|| eventVO.getStudent_stt_code().equals("33")
				|| eventVO.getStudent_stt_code().equals("34")) { // 재학생 기준 ~
			
			try {
				System.out.println("DB 결과");
				/*
				 * System.out.println("=============================");
				 * System.out
				 * .println("eventVO.getClient_code() ::  "+eventVO.getClient_code
				 * ());
				 * System.out.println("eventVO.getClient_code() ::  "+eventVO
				 * .getClient_mem_code());
				 * System.out.println("eventVO.getClient_code() ::  "
				 * +eventVO.getEvent_message());
				 * System.out.println("eventVO.getClient_code() ::  "
				 * +eventVO.getWin_yn());
				 * System.out.println("eventVO.getClient_code() ::  "
				 * +eventVO.getProduct_no());
				 * System.out.println("=============================");
				 */
				System.out.println("정상 상품 번호 :: " + eventVO.getProduct_no());
				
				event_dao.ups_targerCnt_sub(eventVO); // 응모자 차감 재학생일때 무조건 응모자 차감
				System.out.println("정상 응모자 차감");

				event_dao.ups_product_sub(eventVO); // 상품 을 차감 할때 예외 발생하면 꽝으로 처리
				System.out.println("상품 응모자 차감");

				System.out.println("정상 처리");

			} catch (Exception e) {
				// 예외 가 발생할 경우, 전달되는 값은 꽝으로 처리 한다.
				// 당첨 정보도 꽝으로 처리한다.

				System.out.println("예외 발생");
				/*
				 * System.out.println("=============================");
				 * System.out
				 * .println("eventVO.getClient_code() ::  "+eventVO.getClient_code
				 * ());
				 * System.out.println("eventVO.getClient_code() ::  "+eventVO
				 * .getClient_mem_code());
				 * System.out.println("eventVO.getClient_code() ::  "
				 * +eventVO.getEvent_message());
				 * System.out.println("eventVO.getClient_code() ::  "
				 * +eventVO.getWin_yn());
				 * System.out.println("eventVO.getClient_code() ::  "
				 * +eventVO.getProduct_no());
				 * System.out.println("=============================");
				 */
				eventVO.setWin_yn("N");
				eventVO.setProduct_no(8);
				winPro.put("result", changedTime(7));
				winPro.put("result_no", 7);
				winPro.put("result_txt", "다음 기회에");
				
			} finally { // 예외 발생하든 안하든, 재학의 이력을 등록

				event_dao.ins_eventMsg(eventVO);
				System.out.println("재학생 등록 완료");
			}

		} else { // 재학생 아닌 학생 이력등록
			event_dao.ins_eventMsg(eventVO); // 응모자 정보 입력
			System.out.println("미 재학생 등록 완료");
			/*
			 * System.out.println("=============================");
			 * System.out.println
			 * ("eventVO.getClient_code() ::  "+eventVO.getClient_code());
			 * System.out.println("eventVO.getClient_code() ::  "+eventVO.
			 * getClient_mem_code());
			 * System.out.println("eventVO.getClient_code() ::  "
			 * +eventVO.getEvent_message());
			 * System.out.println("eventVO.getClient_code() ::  "
			 * +eventVO.getWin_yn());
			 * System.out.println("eventVO.getClient_code() ::  "
			 * +eventVO.getProduct_no());
			 * System.out.println("=============================");
			 */
		}
		return winPro;
	}

	@Transactional
	public Map<String, Object> op_Result(String client_code, String msg,
			int client_mem_code, String student_stt_code, String ip)
			throws Exception {

		int[] product_Arr = null;
		double[] resultPer = null;
		String[] product_Name = null;
		EventVO eventVO = new EventVO();
		Map<String, Object> winPro = new HashMap<>();

		eventVO.setFirst_reg_ip(ip);
		eventVO.setFirst_reg_mem_code(client_mem_code);
		eventVO.setClient_code(client_code);
		eventVO.setClient_mem_code(client_mem_code);
		eventVO.setEvent_message(msg);
		eventVO.setStudent_stt_code(student_stt_code);

		if (student_stt_code.equals("31") || student_stt_code.equals("32")
				|| student_stt_code.equals("33")
				|| student_stt_code.equals("34")) { // 재학생 일 경우만

			if (client_code != null || client_code != "") {

				System.out.println("client_code :: " + client_code);

				progressDataVO = event_dao.sel_progressData(client_code);
				List<ProductVO> op_pdlist;
				op_pdlist = event_dao.sel_product_list(client_code);

				product_Arr = intConvert_Arr(op_pdlist);
				product_Name = strConvert_Name(op_pdlist);
				/*
				 * System.out.println("==============================="); for
				 * (int i = 0; i < product_Name.length; i++) {
				 * System.out.println(i + "번째의 문자 리스트 :: "+ product_Name[i] ); }
				 * System.out.println("===============================");
				 */
				int student_total = progressDataVO.getIn_college();
				int student_enter = progressDataVO.getEnter_cnt();
				double addTimePer = ((double) progressDataVO.getCs_time() / progressDataVO.getEs_time()) * 100 * 0.1;

				resultPer = productPer(product_Arr, student_total, student_enter, addTimePer, client_code);
																	// 상품들의 확률
																	// 연산

				winPro = win_process(resultPer, product_Arr); // 당첨 처리 후

				// insert 할 정보 셋

				// 재학생 기준
				if ((int) winPro.get("result_no") < 7) { // 당첨

					eventVO.setWin_yn("Y");
					eventVO.setProduct_no((int) winPro.get("result_no") + 1);
					winPro.put("result_txt", product_Name[(int) winPro.get("result_no")]);

				} else { // 꽝~~

					eventVO.setWin_yn("N");
					eventVO.setProduct_no(8);
					winPro.put("result_txt", "다음 기회에");
				}

				winPro = tran_chek(winPro, eventVO); // 한번 더 체크

			} // 끝
		} else { // 재학생 제외

			eventVO.setWin_yn("N");
			eventVO.setProduct_no(8);

			System.out.println("미 재학 ");
			// 새로 셋팅 // 꽝은 0으로 추가

			winPro.put("result", changedTime(7));
			winPro.put("result_no", 7);
			winPro.put("result_txt", "다음 기회에");

			winPro = tran_chek(winPro, eventVO);

		}
		return winPro;
	}

	public StudentVO mem2client(String client_mem_code) throws Exception {
		studentVO = (StudentVO) event_dao.sel_mem2client(client_mem_code);
		return studentVO;
	}

	// 확률 연산 데이터 추출
	public ProgressDataVO progressData(String client_code) throws Exception {

		progressDataVO = (ProgressDataVO) event_dao.sel_progressData(client_code);

		return progressDataVO;
	}

	// 상품 리스트 추출
	public List<ProductVO> product_list(String client_code) throws Exception {
		List<ProductVO> pdList;

		pdList = event_dao.sel_product_list(client_code);

		return pdList;
	}

	// 상품 추출
	public ProductVO product(HashMap<String, String> params) throws Exception {
		ProductVO pdVO;
		pdVO = event_dao.sel_product(params);

		return pdVO;
	}

	// 엑셀 데이터 추출
	public List<ExcelVO> excel_list() throws Exception {

		stList = event_dao.sel_excel_list();

		return stList;
	}

	// 이벤트 응모 데이터 입력
	public void eventMsg(EventVO eventVO) throws Exception {

		event_dao.ins_eventMsg(eventVO);

	}

	// 상품 차감 캠퍼스 별
	public void product_sub(EventVO eventVO) throws Exception {
		event_dao.ups_product_sub(eventVO);
	}

	// 대상자 차감
	public void targerCnt_sub(EventVO eventVO) throws Exception {
		event_dao.ups_targerCnt_sub(eventVO);
	}

}
