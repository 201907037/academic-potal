package com.example.cwp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cwp.domain.Board;
import com.example.cwp.domain.User;
import com.example.cwp.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService bs;
	
	@RequestMapping("/List")
	public String goBoardList(Model model,HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		
		model.addAttribute("boardList", bs.getAl());
		
		if(user.getAuthority().equals("교사")) {
			return "/boardForm/BoardListT";
		}
		else {
			return "/boardForm/BoardListS";
		}
		
	}
	
	@RequestMapping("/insertBoard")
	public String insertBoard(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		model.addAttribute("writer", user.getName());
		return "/boardForm/insertBoard";
	}
	
	@PostMapping("/insert")
	public String insert(Board board, Model model) {
		bs.insertBoard(board);
		return "redirect:/List";
	}
	
	@GetMapping("/getBoard")
	public String goBoard(@RequestParam(value="seq")int seq,Model model,HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		
		Board board = bs.getBoard(seq);
		
		if(board.getWriter().equals(user.getName())) {
			model.addAttribute("delete", "삭제하기");
			model.addAttribute("confirm", "수정하기");
		}
			
		else {
			model.addAttribute("delete", "본인 글만 삭제 가능합니다.");
			model.addAttribute("confirm", "본인 글만 수정 가능합니다.");
		}
			
		model.addAttribute("getBoard", board);
		model.addAttribute("title", board.getTitle());
		return "/boardForm/BoardView";
	}
	
	@GetMapping("/getBoardT")
	public String goBoardT(@RequestParam(value="seq")int seq,Model model) {
		
		Board board = bs.getBoard(seq);
		
			
		model.addAttribute("getBoard", board);
		model.addAttribute("title", board.getTitle());
		return "/boardForm/BoardViewT";
	}
	
	@GetMapping("/Correction")
	public String goCorrection(@RequestParam(value="seq")int seq,Model model) {
		Board board = bs.getBoard(seq);
		model.addAttribute("getBoard", board);
		return "/boardForm/BoardCorrection";
	}
	@GetMapping("/CorrectionT")
	public String goCorrectionT(@RequestParam(value="seq")int seq,Model model) {
		Board board = bs.getBoard(seq);
		model.addAttribute("getBoard", board);
		return "/boardForm/BoardCorrection";
	}
	
	@PostMapping("/CO")
	public String Correct(Board board,Model model) {
		bs.updateBoard(board);
		return "redirect:/List";
	}
	
	@GetMapping("/Delete")
	public String DeleteBoard(@RequestParam(value="seq")int seq,Model model,HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		
		Board board = bs.getBoard(seq);
		
		if(board.getWriter().equals(user.getName())) {
			bs.deleteBoard(board);
			return "redirect:/List";
		}
		
		
		
			return null;
		
	}
	@GetMapping("/DeleteT")
	public String DeleteBoardT(@RequestParam(value="seq")int seq,Model model) {
		
		
		
		Board board = bs.getBoard(seq);
		
			bs.deleteBoard(board);
			return "redirect:/List";
	}
}
