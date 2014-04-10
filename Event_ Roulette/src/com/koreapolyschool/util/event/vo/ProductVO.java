package com.koreapolyschool.util.event.vo;

public class ProductVO { // 상품 데이터

	private int product_no;
	private String product_name;
	private int left_product_cnt;
	private int product_total;

	public int getProduct_no() {
		return product_no;
	}

	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getLeft_product_cnt() {
		return left_product_cnt;
	}

	public void setLeft_product_cnt(int left_product_cnt) {
		this.left_product_cnt = left_product_cnt;
	}

	public int getProduct_total() {
		return product_total;
	}

	public void setProduct_total(int product_total) {
		this.product_total = product_total;
	}

}
