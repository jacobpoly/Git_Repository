package com.koreapolyschool.util.event.service;

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

		int[] product_Arr = new int[op_pdlist.size()] ;
		for (int i = 0; i < op_pdlist.size(); i++) {
			product_Arr[i] = op_pdlist.get(i).getLeft_product_cnt();
		}
		return product_Arr;
	}
	
	int int_arr_Total(int[] int_arr) {

		int total = 0;

		for (int i = 0; i < int_arr.length; i++) {
			total += int_arr[i];
		}

		return total;
	}

	// 확률 연산 메서드
	public static double randomRange(double n1, double n2) {
		return (Math.random() * (n2 - n1 + 1)) + n1;
	}

	// 당첨 상품에 회전 값 변경으로
	double changedTime(int product_num) {

		double resultTime;

		switch (product_num) {

		case 0:
			System.out.println("상품 번호  :: " + (1 + product_num));
			resultTime = randomRange(3680, 3810); // 6개월 기본
			break;
		case 1:
			System.out.println("상품 번호  :: " + (1 + product_num));
			resultTime = randomRange(3440, 3560); // 10만원
			break;
		case 2:
			System.out.println("상품 번호  :: " + (1 + product_num));
			resultTime = randomRange(3200, 3330); // 5만원
			break;
		case 3:
			System.out.println("상품 번호  :: " + (1 + product_num));
			resultTime = randomRange(2960, 3090); // 3만원
			break;
		case 4:
			System.out.println("상품 번호  :: " + (1 + product_num));
			resultTime = randomRange(2720, 2850); // 1만원
			break;
		case 5:
			System.out.println("상품 번호  :: " + (1 + product_num));
			resultTime = randomRange(2480, 2610); // 자석 책갈피
			break;
		case 6:
			System.out.println("상품 번호  :: " + (1 + product_num));
			resultTime = randomRange(2240, 2370); // 폴리 기념품
			break;
		default:
			System.out.println("모두 소진 됨");
			resultTime = randomRange(3920, 4040); // 다음 기회에
			break;
		}

		return resultTime;
	}

	// 확률 계산 계산 (상품 리스트, 재학생 총, 응모자 수, 가중치)
	@Transactional
	double[] productPer(int[] product_Arr, int student_total,int student_enter, double addTimePer) {

		System.out.println("응모자 수 :: " + student_enter);

		int product_Total = int_arr_Total(product_Arr);

		double[] result = new double[product_Arr.length];
		double student_per = 1 * 100;
		if (student_total != student_enter ){
			student_per = ((double) 1 / (student_total - student_enter)) * 100;
		}
		System.out.println("student_per :: " + student_per + " 남은 학생의 확률");
		System.out.println(" (student_total - student_enter)  :: "	+ (student_total - student_enter) + " 남은 학생 수");

		for (int y = 0; y < product_Arr.length; y++) {
			double product_per;

			if (product_Arr[y] == 0) {

				product_per = ((double) product_Arr[y] / 1) * 100; // 0개 일때 확률
																	// 연산
				System.out.println("product_Arr[" + y + "] 소진하거나 없는 상품 :: "
						+ product_Arr[y] + " 개수");
				// System.out.println("if  :: "+ product_Total);

				result[y] = (Double.parseDouble(String.format("%.2f", (double) product_per * student_per)));

				System.out.println("	최종 확률 :: [" + y + "] :: " + result[y]);

			} else { // 한개 이상의 상품의 확률
				System.out.println("product_Arr[" + y + "] 남은 상품  :: "
						+ product_Arr[y]);
				// System.out.println("else :: " + product_Total);

				product_per = ((double) product_Arr[y] / product_Total) * 100;

				result[y] = (Double.parseDouble(String.format("%.2f", (double) product_per * student_per)));// /30; //// +addTimePer;// // 당첨률 낮추기

				System.out.println("상품 확률 :: " + product_per);
				System.out.println("재학생 (미응모자)확률 :: " + student_per);

				System.out.println("	최종 확률 :: [" + y + "] :: " + result[y]);

			}
		}
		
		
		
		return result;
	}

	// 확률 연산 처리 // 상품 확률 // 상품 리스트
	Map<String, Object> win_process(double[] productPers, int[] product) {

		Map<String, Object> result = new HashMap<String, Object>();
		
		for (int i = 0; i < productPers.length; i++) {

			if (Math.random() * 100 < productPers[i]) { // 당첨 되는 부분 // 상품 확률이
														// 높으면 당첨~~

				// product[i]--; // 당첨이 되면 그 상품을 차감 한다.
				System.out.println(i + 1 + " 번째 상품이  당첨 되었습니다.");

				System.out.println("==============");

				System.out.println("회전 시간 :: " + changedTime(i));

				System.out.println("==============");

				System.out.println("확률 (" + productPers[i] + ")  ");
				
				result.put("result", changedTime(i));
				result.put("result_no", i);
				
				return result;

			} else {
				// System.out.println(i + 1 + " 차 시도 ");

				if (i == productPers.length-1) {

					// System.out.println(productPers.length);

					System.out.println("다음 기회에~~");
					
					result.put("result", changedTime(productPers.length));
					result.put("result_no", productPers.length);
					
				}

				continue;
			}

		}
		
		
		return result;
		
	}
	@Transactional
	public Map<String, Object> op_Result(String client_code, String msg) throws Exception {

		int[] product_Arr = null;
		double[] resultPer = null;
		EventVO eventVO = null;
		Map<String, Object>  winPro  = new HashMap<>();
		
		if (client_code != null || client_code !="") {

			progressDataVO = event_dao.sel_progressData(client_code);
			List<ProductVO> op_pdlist;
				op_pdlist = event_dao.sel_product_list(client_code);
				product_Arr = intConvert_Arr(op_pdlist);

			int student_total = progressDataVO.getIn_college();
			int student_enter = progressDataVO.getEnter_cnt();
			double addTimePer = ((double) progressDataVO.getCs_time() / progressDataVO	.getEs_time()) * 100 * 0.1;

			resultPer =  productPer(product_Arr, student_total, student_enter, addTimePer);
			
			winPro =win_process(resultPer, product_Arr); // 확률 처리 후
		
		}
		return winPro;
	}

	// 맴버코드 클라이언트코드로 변환
	public StudentVO mem2client(String member_code) throws Exception {
		studentVO = (StudentVO) event_dao.sel_mem2client(member_code);
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
