package com.test.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.test.project.model.board.Board;
import com.test.project.model.board.dto.BoardSearchDto;
import com.test.project.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	
	@GetMapping("/board/list")
	public String boardList(@ModelAttribute BoardSearchDto boardSearchDto, Model model) {
		model.addAttribute("pageInfo", boardService.findAll(boardSearchDto));
		return "board/list";
	}

	@GetMapping("/board/detail/{id}")
	public String boardDetail(@PathVariable("id") int id, Model model) {
		Board board = boardService.findBoardById(id);
		model.addAttribute("board", board);
		return "board/detail";
	}

}
