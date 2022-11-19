package com.test.project.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.test.project.model.board.Board;
import com.test.project.model.board.dto.BoardSearchDto;
import com.test.project.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardResource {
	private final BoardService boardService;
	
	@GetMapping("/board/list/search")
	public PageInfo<Board> boardSearch(@ModelAttribute BoardSearchDto boardSearchDto) {
		return boardService.findAll(boardSearchDto);
	}
}
