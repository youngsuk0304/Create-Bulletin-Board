package com.board.controller;

import java.util.Date;

public class BoardReDto {
	private int re_no;
	private int brd_no;
	private String re_content;
	private String re_writer;
	private Date re_regDate;
	public int getRe_no() {
		return re_no;
	}
	public void setRe_no(int re_no) {
		this.re_no = re_no;
	}
	public int getBrd_no() {
		return brd_no;
	}
	public void setBrd_no(int brd_no) {
		this.brd_no = brd_no;
	}
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	public String getRe_writer() {
		return re_writer;
	}
	public void setRe_writer(String re_writer) {
		this.re_writer = re_writer;
	}
	public Date getRe_regDate() {
		return re_regDate;
	}
	public void setRe_regDate(Date re_regDate) {
		this.re_regDate = re_regDate;
	}
	
	
}
