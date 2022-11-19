package com.test.project.model.board;

import java.util.List;

import com.test.project.model.board.dto.BoardSearchDto;

public interface BoardMapper {
  List<Board> findAll(BoardSearchDto boardSearchDto);
  Board findBoardById(int id);
  
}
