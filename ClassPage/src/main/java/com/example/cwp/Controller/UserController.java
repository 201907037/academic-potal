package com.example.cwp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cwp.domain.User;
import com.example.cwp.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService us;
	
	
	@RequestMapping("/loginForm")
	public String goLogin() {
		return "/userForm/login";
	}
	
	@RequestMapping("/asignForm")
	public String goAsign() {
		return "/userForm/asign";
	}
	
	@PostMapping("/end")
	public String endAsign(User user) {
		us.insertUser(user);
		
		return "/userForm/welcome";
	}
	@PostMapping("/asfinal")
	public String goFinal(@RequestParam(value="id")int id, Model model ) {
		String message = us.getUserId(id);
		if(message=="사용 가능한 아이디") {
		model.addAttribute("message", message);
		model.addAttribute("id", id);
		return "/userForm/asignfinal";
		}
		else {
			model.addAttribute("message", message);
			model.addAttribute("id", id);
			return "/userForm/asign2";
		}
	}
}
