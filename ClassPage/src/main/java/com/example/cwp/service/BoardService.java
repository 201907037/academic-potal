package com.example.cwp.service;

import java.util.List;

import com.example.cwp.domain.Board;

public interface BoardService {
	public void insertBoard(Board board);
	public void updateBoard(Board board);
	public void deleteBoard(Board board);
	public Board getBoard(int seq);
	public List<Board> getBoardList(Board board);
	public List<Board> getAl();
}
