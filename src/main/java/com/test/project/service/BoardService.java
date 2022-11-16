package com.test.project.service;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.project.model.board.Board;
import com.test.project.model.board.BoardMapper;
import com.test.project.model.board.dto.BoardSearchDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
  private final BoardMapper boardMapper;

  public PageInfo<Board> findAll(BoardSearchDto boardSearchDto) {
    PageHelper.startPage(boardSearchDto.getPageNum(), boardSearchDto.getPageSize());
    return PageInfo.of(boardMapper.findAll());
  }
}
