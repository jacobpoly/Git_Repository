package com.koreapolyschool.util.event.vo;

public class StudentVO {		// 학생 정보 (엑셀VO)
	
	private int event_no;			// 이벤트 번호
	private String enter_yn;		// 이벤트 응모여부
	private  int enter_cnt;			// 응모 횟수
	private String in_college_yn;	// 재학 여부
	private int member_code;	// 학번
	private String client_code;		// 캠퍼스 코드
	private int client_mem_code;	// 캠퍼스 학번
	private String name;				// 학생이름
	private String edu_state;			// 교육상태
	private String campus_name;	// 캠퍼스명
	private String class_name;		// 클래스명
	private String affiliate;			// 소속과정
	private String product;				// 이벤트 상품
	private String login_date;		// 로그인일시
	private String participation;		// 이벤트 응모 일시
	private String event_msg;		// 이벤트 메세지
	private String address;			// 주소
	private String phone;				// 연락처
	
	public int getMember_code() {
		return member_code;
	}

	public void setMember_code(int member_code) {
		this.member_code = member_code;
	}
	
	public int getEvent_no() {
		return event_no;
	}

	public void setEvent_no(int event_no) {
		this.event_no = event_no;
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEdu_state() {
		return edu_state;
	}
	
	public void setEdu_state(String edu_state) {
		this.edu_state = edu_state;
	}
	
	public String getCampus_name() {
		return campus_name;
	}
	
	public void setCampus_name(String campus_name) {
		this.campus_name = campus_name;
	}
	
	public String getClass_name() {
		return class_name;
	}
	
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	
	public String getAffiliate() {
		return affiliate;
	}
	
	public void setAffiliate(String affiliate) {
		this.affiliate = affiliate;
	}
	
	public String getProduct() {
		return product;
	}
	
	public void setProduct(String product) {
		this.product = product;
	}
	
	public String getLogin_date() {
		return login_date;
	}
	
	public void setLogin_date(String login_date) {
		this.login_date = login_date;
	}
	
	public String getParticipation() {
		return participation;
	}
	
	public void setParticipation(String participation) {
		this.participation = participation;
	}
	
	public String getEvent_msg() {
		return event_msg;
	}
	
	public void setEvent_msg(String event_msg) {
		this.event_msg = event_msg;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
