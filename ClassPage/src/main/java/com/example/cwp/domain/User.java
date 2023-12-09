package com.example.cwp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name ="USER_DATA")
public class User {
	@Id
	private int id;
	private int pw;
	private String name;
	private int age;
	private String authority;
	@Column(nullable = true)
	private int sub1;
	@Column(nullable = true)
	private int sub2;
	@Column(nullable = true)
	private int sub3;
	@Column(nullable = true)
	private int sub4;
	@Column(nullable = true)
	private int sub5;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPw() {
		return pw;
	}
	public void setPw(int pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public int getSub1() {
		return sub1;
	}
	public void setSub1(int sub1) {
		this.sub1 = sub1;
	}
	public int getSub2() {
		return sub2;
	}
	public void setSub2(int sub2) {
		this.sub2 = sub2;
	}
	public int getSub3() {
		return sub3;
	}
	public void setSub3(int sub3) {
		this.sub3 = sub3;
	}
	public int getSub4() {
		return sub4;
	}
	public void setSub4(int sub4) {
		this.sub4 = sub4;
	}
	public int getSub5() {
		return sub5;
	}
	public void setSub5(int sub5) {
		this.sub5 = sub5;
	}
	
	
	
	
}
