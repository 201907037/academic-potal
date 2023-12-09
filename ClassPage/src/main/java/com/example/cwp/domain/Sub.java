package com.example.cwp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="SUB_LIST")
public class Sub {
	@Id
	@GeneratedValue
	private int seq;
	private String title;
	private String teacher;
	private String content;
	private int maxcnt;
	private int nowcnt=0;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getMaxcnt() {
		return maxcnt;
	}
	public void setMaxcnt(int maxcnt) {
		this.maxcnt = maxcnt;
	}
	public int getNowcnt() {
		return nowcnt;
	}
	public void setNowcnt(int nowcnt) {
		this.nowcnt = nowcnt;
	}
	
	
}
