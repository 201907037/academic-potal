package com.example.cwp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cwp.domain.Sub;
import com.example.cwp.persistence.SubRepository;

@Service
public class SubServiceImpl implements SubService{
	
	@Autowired
	private SubRepository sR;

	@Override
	public void insertSub(Sub sub) {
		
		sR.save(sub);
	}

	@Override
	public void updateSub(Sub sub) {
		Sub subfind = sR.findById(sub.getSeq()).get();
		subfind.setTitle(sub.getTitle());
		subfind.setContent(sub.getContent());
		sR.save(subfind);
	}

	@Override
	public void deleteSub(Sub sub) {
		sR.deleteById(sub.getSeq());
		
	}

	@Override
	public List<Sub> getSubList() {
		
		return (List<Sub>)sR.findAll();
	}

	@Override
	public Sub getSub(int seq) {
		
		return (Sub)sR.findById(seq).get();
	}

	@Override
	public void updatecnt(Sub sub,int cnt) {
		Sub subfind = sR.findById(sub.getSeq()).get();
		subfind.setNowcnt(cnt);
		sR.save(subfind);
		
	}
	
}
