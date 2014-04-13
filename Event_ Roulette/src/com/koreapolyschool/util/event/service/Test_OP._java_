package operationTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Test_OP {
	int count = 0;		// 당첨 카운터
	int student_total = 511;		// 총 재학생 수
	int student_enter = 0;			// 응모자 수
	int[] product = { 0, 1, 0, 1, 2, 3, 6, 0 }; // 마지막은 꽝

	public static double randomRange(double n1, double n2) {
		return (Math.random() * (n2 - n1 + 1)) + n1;
	}

	// 당첨 상품에 회전 값 변경으로
	double changedTime(int product_num) {

		double resultTime;

		switch (product_num) {

		case 0:
			System.out.println("상품 번호  :: " + (1 + product_num));
			resultTime = randomRange(3200, 3330); // 5만원
			break;
		case 1:
			System.out.println("상품 번호  :: " + (1 + product_num));
			resultTime = randomRange(2960, 3090); // 3만원
			break;
		case 2:
			System.out.println("상품 번호  :: " + (1 + product_num));
			resultTime = randomRange(2720, 2850); // 1만원
			break;
		case 3:
			System.out.println("상품 번호  :: " + (1 + product_num));
			resultTime = randomRange(2480, 2610); // 자석 책갈피
			break;
		case 4:
			System.out.println("상품 번호  :: " + (1 + product_num));
			resultTime = randomRange(2240, 2370); // 폴리 기념품
			break;
		case 5:
			System.out.println("상품 번호  :: " + (1 + product_num));
			resultTime = randomRange(3920, 4040); // 다음 기회에
			break;
		case 6:
			System.out.println("상품 번호  :: " + (1 + product_num));
			resultTime = randomRange(3680, 3810); // 6개월 기본
			break;
		default:
			System.out.println("모두 소진 됨");
			resultTime = randomRange(3440, 3560); // 10만원
			break;
		}

		return resultTime;
	}

	int int_arr_Total(int[] int_arr) {

		int total = 0;

		for (int i = 0; i < int_arr.length; i++) {
			total += int_arr[i];
		}

		return total;
	}

	// 확률 계산 계산 (상품 리스트, 재학생 총, 응모자 수, 가중치)
	double[] productPer(int[] product_Arr, int student_total,
			int student_enter, double addTimePer) {

		System.out.println("=================시작!!!===============");

		System.out.println("응모자 수 :: " + student_enter);

		int product_Total = int_arr_Total(product_Arr);

		/*
		 * for (int i = 0; i < product_Arr.length; i++) {
		 * System.out.println(product_Arr[i]); }
		 */
		double[] result = new double[product_Arr.length];
		// 1/총 학생수 - 응모자 학생수
		double student_per = ((double) 1 / (student_total - student_enter)) * 100;

		// System.out.println("student_total :: "+student_total);
		// System.out.println("student_enter :: "+student_enter);

		System.out.println("student_per :: " + student_per + " 남은 학생의 확률");
		System.out.println(" (student_total - student_enter)  :: "
				+ (student_total - student_enter) + " 남은 학생 수");

		// 상품 확률
		// result =(( 상품의 갯수 / 총 상품) * 100 ) / 학생을 확률
		// 학생 확률
		// srudent_per = (1 / 총 재학생 - 응모자 수 ) * 100
		// (상품 확률 * 학생 확률) + 가중치  // 상품의 확률중 학생 확률일때 확률
		// ** 각각의 상품 확률일때, 캠퍼스 재학생 (미응모자) 어떠한 한명의 학생일때의 확률(즉, 각 상품이 일어날때, 캠퍼스 미응모자가 중 한명이 될 확률의 연산이다.)
		for (int y = 0; y < product_Arr.length; y++) {
			// System.out.println(y + " :: " + product_Arr[y]);

			double product_per;

			if (product_Arr[y] == 0) { 
				
				product_per = ((double) product_Arr[y] / 1) * 100; // 0개 일때 확률 연산  
				 System.out.println("product_Arr["+y+"] 소진하거나 없는 상품 :: "+product_Arr[y] +" 개수" );
			//	 System.out.println("if  :: "+ product_Total);

				result[y] = (Double.parseDouble(String.format("%.2f",
						(double) product_per * student_per)));

				System.out.println("	최종 확률 :: ["+y+"] :: " + result[y]);
				
			} else { // 한개 이상의 상품의 확률
				System.out.println("product_Arr["+y+"] 남은 상품  :: "+product_Arr[y]);
			//	System.out.println("else :: " + product_Total);

				product_per = ((double) product_Arr[y] / product_Total) * 100;
				
				result[y] = (Double.parseDouble(String.format("%.2f",
						(double) product_per * student_per)));///30; // +addTimePer; // 당첨률 낮추기

				System.out.println("상품 확률 :: " + product_per);
				System.out.println("재학생 (미응모자)확률 :: " + student_per);

				System.out.println("	최종 확률 :: ["+y+"] :: " + result[y]);

				// System.out.println("?? :: "+(Double.parseDouble(String.format("%.2f",
				// (double) product_per )))); // 상품의 확률
				// System.out.println("product_Arr[y]  :: "+ product_Arr[y] );
				// System.out.println("product_Total :: "+ product_Total);
				// System.out.println("student_per :: "+ student_per);
			}
		}
		// Double.parseDouble(String.format("%.3f", 1.4455555)); // 소수 점
		// 자리
		// 수 "%.3f" 소수점 3자리

		return result;
	}

	// 확률 연산 처리
	int[] probability_op(double[] productPers, int[] product) {

		for (int i = 0; i < productPers.length; i++) {

			if (Math.random() * 100 < productPers[i]) { // 당첨 되는 부분  // 상품 확률이 높으면 당첨~~

				// System.out.println("Before :: " + product[i]);
				product[i]--; // 당첨이 되면 그 상품을 차감 한다.
				// System.out.println("After ::" + product[i]);
				System.out.println(i + 1 + " 번째 상품이  당첨 되었습니다.");

				System.out.println("==============");

				System.out.println("회전 시간 :: " + changedTime(i));

				System.out.println("==============");

				System.out.println("확률 (" + productPers[i] + ")  ");

				count++;

				System.out.println("당첨 횟수 " + count);

				// 상품의 총수
				break;

			} else {
				// System.out.println(i + 1 + " 차 시도 ");

				if (i == productPers.length + 1) {

					// System.out.println(productPers.length);

					System.out.println("다음 기회에~~");

					// op.stopRotate(productPers.length + 1); // 룰렛 멈추는 연산 정보
					// 구함
				}

				continue;
			}

		}

		return product;
	}

	// 가중치 부여 날짜(초)
	public double func_addTimePer() throws ParseException {

		long es_time = 0;
		long cs_time = 0;
		double addTimePer = 0;

		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.KOREA);

		String currentTime = dateFormatter.format(new java.util.Date());
		String str_date = "2014-04-11 10:00:00";
		String end_date = "2014-04-28 10:00:00";

		Date current_Date = dateFormatter.parse(currentTime);
		Date str_Date = dateFormatter.parse(str_date);
		Date end_Date = dateFormatter.parse(end_date);
		cs_time = (current_Date.getTime() - str_Date.getTime()) / (1000);
		es_time = (end_Date.getTime() - str_Date.getTime()) / (1000); // 1000 ==
																		// 1초 ,
																		// //60
																		// 초,
																		// 60분,
																		// 24시

		// System.out.println("cs_time :: "+ cs_time);
		// System.out.println("es_time :: "+ es_time);

		addTimePer = (((double) cs_time / es_time) * 100);// * 0.1; // 가중치 마지막 날은
																	// 10% 가중치
																	// 더함

		return addTimePer;
	}

	public static void main(String[] args) throws ParseException {

		Test_OP op = new Test_OP();

		int[] final_result = null;
		double addTimePer = op.func_addTimePer();

		// System.out.println(" addTimePer :: 가중치 "+ addTimePer+" %");

		for (int j = 0; j < op.product.length; j++) {
			System.out.println(j + 1 + " 상품의  ::  " + op.product[j] + " 갯수");

		}

		for (op.student_enter = 0; op.student_enter < op.student_total; op.student_enter++) {

			double[] productPers = op.productPer(op.product, op.student_total,
					op.student_enter, addTimePer); // 순차적으로 상품을 연산 한다.

			System.out.println("가중치 :: " + addTimePer);
			/*
			 * for (int i = 0; i < productPers.length; i++) {
			 * 
			 * System.out.println(i + 1 + "상품의 확률  ::: " + productPers[i] +
			 * "%");
			 * 
			 * }
			 */
			final_result = op.probability_op(productPers, op.product);

		}

		for (int j = 0; j < final_result.length; j++) {
			System.out.println(j + 1 + " 상품의  :: 남을  " + final_result[j]
					+ " 갯수");

		}

	}

}
