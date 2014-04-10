package com.koreapolyschool.util.event.vo;

public class ProgressDataVO { // 진행데이터 (확률 연산)

	private int in_college;
	private int enter_cnt;
	private long cs_time;
	private long es_time;
	
	public int getIn_college() {
		return in_college;
	}
	
	public void setIn_college(int in_college) {
		this.in_college = in_college;
	}
	
	public int getEnter_cnt() {
		return enter_cnt;
	}
	
	public void setEnter_cnt(int enter_cnt) {
		this.enter_cnt = enter_cnt;
	}
	
	public long getCs_time() {
		return cs_time;
	}
	
	public void setCs_time(long cs_time) {
		this.cs_time = cs_time;
	}
	
	public long getEs_time() {
		return es_time;
	}
	
	public void setEs_time(long es_time) {
		this.es_time = es_time;
	}
	
}