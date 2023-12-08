package com.example.cwp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cwp.domain.Board;
import com.example.cwp.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardRepository bRep;
	
	public void insertBoard(Board board) {//저장기능
		bRep.save(board);
	}
	 public void updateBoard(Board board) {//수정기능
		 Board boardfind =bRep.findById(board.getSeq()).get();
		 boardfind.setTitle(board.getTitle());
		 boardfind.setContent(board.getContent());
		 boardfind.setWriter(board.getWriter());
		 bRep.save(boardfind);//modify한다.
	 }
	 public void deleteBoard(Board board) {
		 bRep.deleteById(board.getSeq());
	 }
	 public Board getBoard(int seq) {		 
	   return bRep.findById(seq).get();
	 }
	 public List<Board> getBoardList(Board board){		 
		 return  (List<Board>)bRep.findAll();
	 }
	
	public List<Board> getAl() {
		
		return (List<Board>)bRep.findAll();
	}
	 
}
