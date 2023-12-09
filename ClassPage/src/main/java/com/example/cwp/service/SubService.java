package com.example.cwp.service;

import java.util.List;

import com.example.cwp.domain.Sub;

public interface SubService {
	
	public void insertSub(Sub sub);
	public void updateSub(Sub sub);
	public void deleteSub(Sub sub);
	public Sub getSub(int seq);
	public List<Sub> getSubList();
	public void updatecnt(Sub sub,int cnt);
}
