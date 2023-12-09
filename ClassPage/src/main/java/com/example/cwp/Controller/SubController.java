package com.example.cwp.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cwp.domain.Sub;
import com.example.cwp.domain.User;
import com.example.cwp.service.SubService;
import com.example.cwp.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class SubController {
	
	@Autowired
	private SubService ss;
	
	@Autowired
	private UserService us;
	
	@RequestMapping("/sub")
	public String goSub(Model model, HttpServletRequest re) {
		HttpSession session = re.getSession(false);
		User user = (User) session.getAttribute("user");
		
		List<Sub> sublist = ss.getSubList();
		
		int s1,s2,s3,s4,s5;
		
		
		s1 = user.getSub1();
		
		s2 = user.getSub2();
			 
		s3 = user.getSub3();
		
		s4 = user.getSub4();
		
		s5 = user.getSub5();
		
		sublist.removeIf(sub-> sub.getSeq()==s1);
		sublist.removeIf(sub-> sub.getSeq()==s2);
		sublist.removeIf(sub-> sub.getSeq()==s3);
		sublist.removeIf(sub-> sub.getSeq()==s4);
		sublist.removeIf(sub-> sub.getSeq()==s5);
		
		model.addAttribute("subList", sublist);
		if(user.getAuthority().equals("교사")) {
			return "/subForm/subListT";
		}
		else {
			return "/subForm/subList";
		}
		
	}
	
	@RequestMapping("/insertSub")
	public String insertSub(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		model.addAttribute("writer", user.getName());
		return "/subForm/subInsert";
	}
	
	@PostMapping("/plus")
	public String plusSub(Sub sub) {
		ss.insertSub(sub);
		return "redirect:/sub";
	}
	
	@RequestMapping("/getsub")
	public String goSub(@RequestParam(value="seq")int seq,Model model) {
		Sub sub = ss.getSub(seq);
		model.addAttribute("sub", sub);
		model.addAttribute("title", sub.getTitle());
		return "/subForm/SubView";
	}
	
	@GetMapping("/completeSub")
	public String completeSub(@RequestParam(value="seq")int seq, HttpServletRequest re) {
		
		HttpSession session = re.getSession(false);
		User user = (User) session.getAttribute("user");
		if(user.getSub1()==0)
			user.setSub1(seq);
		else if(user.getSub2()==0)
			user.setSub2(seq);
		else if(user.getSub3()==0)
			user.setSub3(seq);
		else if(user.getSub4()==0)
			user.setSub4(seq);
		else
			user.setSub5(seq);
		
		Sub sub = ss.getSub(seq);
		int subcnt = sub.getNowcnt()+1;
		ss.updatecnt(sub,subcnt);
		us.updateUser(user);
		
		return "redirect:/sub";
	}
	@RequestMapping("/mySub")
	public String mySub(HttpServletRequest re, Model model) {
		HttpSession session = re.getSession(false);
		User user = (User) session.getAttribute("user");
		List<Integer> subNo = new ArrayList<Integer>();
		List<Sub> myList= new ArrayList<Sub>();
		
		if(user.getSub1() != 0)
			subNo.add(user.getSub1());
		if(user.getSub2() != 0)
			subNo.add(user.getSub2());
		if(user.getSub3() != 0)
			subNo.add(user.getSub3());
		if(user.getSub4() != 0)
			subNo.add(user.getSub4());
		if(user.getSub5() != 0)
			subNo.add(user.getSub5());
		for (Integer integer : subNo) {
			myList.add(ss.getSub(integer));
		}
		model.addAttribute("myList", myList);
		return "/subForm/mySub";
	}
	
	@RequestMapping("/getmysub")
	public String detailSub(@RequestParam(value="seq")int seq,Model model) {
		Sub sub = ss.getSub(seq);
		model.addAttribute("sub", sub);
		model.addAttribute("title", sub.getTitle());
		return "/subForm/getmysub";
	}
	@GetMapping("/deleteSub")
	public String deleteSub(@RequestParam(value="seq")int seq,Model model, HttpServletRequest re) {
		HttpSession session = re.getSession(false);
		User user = (User) session.getAttribute("user");
		if(user.getSub1()==seq)
			user.setSub1(0);
		else if(user.getSub2()==seq)
			user.setSub2(0);
		else if(user.getSub3()==seq)
			user.setSub3(0);
		else if(user.getSub4()==seq)
			user.setSub4(0);
		else
			user.setSub5(0);
		Sub sub = ss.getSub(seq);
		int cntm = sub.getNowcnt()-1;
		ss.updatecnt(sub, cntm);
		us.updateUser(user);
		return "redirect:/sub";
	}
}
