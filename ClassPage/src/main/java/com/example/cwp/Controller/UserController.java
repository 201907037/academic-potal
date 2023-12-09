package com.example.cwp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cwp.domain.User;
import com.example.cwp.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class UserController {
	
	@Autowired
	private UserService us;
	
	
	@RequestMapping("/index")
	public String goLogin() {
		return "/index";
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
	
	@PostMapping("/login")
	public String login(User user, HttpSession session,Model model) {
		User findUser = us.getUser(user);
		if(findUser != null && findUser.getPw()==user.getPw()) {
			session.setAttribute("user", findUser);
			model.addAttribute("name", findUser.getName()+"("+findUser.getAuthority()+")");
			return "/main";
		}
		else {
			model.addAttribute("notice", "아이디, 비밀번호가 일치하지 않습니다.");
			return "/index";
		}
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/index";
	}
	@RequestMapping("/main")
	public String goMain(HttpServletRequest re,Model model) {
		HttpSession session = re.getSession(false);
		User findUser = (User) session.getAttribute("user");
		if(findUser==null) {
			return "redirect:/index";
		}
		else {
			session.setAttribute("user", findUser);
			model.addAttribute("name", findUser.getName()+"("+findUser.getAuthority()+")");
			return "/main";
		}
	}
}
