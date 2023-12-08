package com.example.cwp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cwp.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}
