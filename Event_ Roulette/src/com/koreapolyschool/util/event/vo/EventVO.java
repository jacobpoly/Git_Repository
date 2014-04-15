package  com.koreapolyschool.util.event.vo;

public class EventVO { // 이벤트 응모 데이터 insert

	private int evnet_no = 1;
	private int  event_schedule_seq = 1;	
	private int  client_mem_code;		
	private int product_no;			
	private String win_yn;				
	private String  event_message;			
	private String student_stt_code;	
	private String first_reg_dttm;		
	private int first_reg_mem_code;	
	private String  first_reg_ip;
	private String client_code;
//	private int enter_cnt;
//	private int student_stt_brn;
	
	
	public int getEvnet_no() {
		return evnet_no;
	}
	
	public void setEvnet_no(int evnet_no) {
		this.evnet_no = evnet_no;
	}
	
	public int getEvent_schedule_seq() {
		return event_schedule_seq;
	}
	
	public void setEvent_schedule_seq(int event_schedule_seq) {
		this.event_schedule_seq = event_schedule_seq;
	}
	
	public int getClient_mem_code() {
		return client_mem_code;
	}
	
	public void setClient_mem_code(int client_mem_code) {
		this.client_mem_code = client_mem_code;
	}
	
	public int getProduct_no() {
		return product_no;
	}
	
	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}
	
	public String getWin_yn() {
		return win_yn;
	}
	
	public void setWin_yn(String win_yn) {
		this.win_yn = win_yn;
	}
	
	public String getEvent_message() {
		return event_message;
	}
	
	public void setEvent_message(String event_message) {
		this.event_message = event_message;
	}
	
	public String getStudent_stt_code() {
		return student_stt_code;
	}
	
	public void setStudent_stt_code(String student_stt_code) {
		this.student_stt_code = student_stt_code;
	}
	
	public String getFirst_reg_dttm() {
		return first_reg_dttm;
	}
	
	public void setFirst_reg_dttm(String first_reg_dttm) {
		this.first_reg_dttm = first_reg_dttm;
	}
	
	public int getFirst_reg_mem_code() {
		return first_reg_mem_code;
	}
	
	public void setFirst_reg_mem_code(int first_reg_mem_code) {
		this.first_reg_mem_code = first_reg_mem_code;
	}
	
	public String getFirst_reg_ip() {
		return first_reg_ip;
	}
	
	public void setFirst_reg_ip(String first_reg_ip) {
		this.first_reg_ip = first_reg_ip;
	}

	public String getClient_code() {
		return client_code;
	}

	public void setClient_code(String client_code) {
		this.client_code = client_code;
	}			
	 
}
