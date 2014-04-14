package com.koreapolyschool.util.event.vo;

public class StudentVO {		// 학생의 클라이언트 코드 
	
	private String enter_yn;		// 이벤트 응모여부
	private  int enter_cnt;			// 응모 횟수
	private String in_college_yn;	// 재학 여부
	private int member_code;	// 학번
	private String client_code;		// 캠퍼스 코드
	private int client_mem_code;	// 캠퍼스 학번
	private String student_stt_code;  // 학생 상태 코드
	
	
	public String getStudent_stt_code() {
		return student_stt_code;
	}

	public void setStudent_stt_code(String student_stt_code) {
		this.student_stt_code = student_stt_code;
	}

	public String getEnter_yn() {
		return enter_yn;
	}
	
	public void setEnter_yn(String enter_yn) {
		this.enter_yn = enter_yn;
	}
	
	public int getEnter_cnt() {
		return enter_cnt;
	}
	
	public void setEnter_cnt(int enter_cnt) {
		this.enter_cnt = enter_cnt;
	}
	
	public String getIn_college_yn() {
		return in_college_yn;
	}
	
	public void setIn_college_yn(String in_college_yn) {
		this.in_college_yn = in_college_yn;
	}
	
	public int getMember_code() {
		return member_code;
	}
	
	public void setMember_code(int member_code) {
		this.member_code = member_code;
	}
	
	public String getClient_code() {
		return client_code;
	}
	
	public void setClient_code(String client_code) {
		this.client_code = client_code;
	}
	
	public int getClient_mem_code() {
		return client_mem_code;
	}
	
	public void setClient_mem_code(int client_mem_code) {
		this.client_mem_code = client_mem_code;
	}
	
	
}
