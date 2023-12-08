package com.example.cwp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cwp.domain.Board;
import com.example.cwp.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService bs;
	
	@RequestMapping("/List")
	public String goBoardList(Model model) {
		model.addAttribute("boardList", bs.getAl());
		return "/boardForm/BoardListS";
	}
	
	@RequestMapping("/insertBoard")
	public String insertBoard() {
		return "/boardForm/insertBoard";
	}
	
	@PostMapping("/insert")
	public String insert(Board board, Model model) {
		bs.insertBoard(board);
		model.addAttribute("boardList", bs.getAl());
		return "/boardForm/BoardListS";
	}
	
	@GetMapping("/getBoard")
	public String goBoard(@RequestParam(value="seq")int seq,Model model) {
		Board board = bs.getBoard(seq);
		model.addAttribute("getBoard", board);
		model.addAttribute("title", board.getTitle());
		return "/boardForm/BoardView";
	}
	
	@GetMapping("/Correction")
	public String goCorrection(@RequestParam(value="seq")int seq,Model model) {
		Board board = bs.getBoard(seq);
		model.addAttribute("getBoard", board);
		return "/boardForm/BoardCorrection";
	}
	
	@PostMapping("/CO")
	public String Correct(Board board,Model model) {
		bs.updateBoard(board);
		model.addAttribute("boardList", bs.getAl());
		return "/boardForm/BoardListS";
	}
}
