package com.test.project.controller;

import java.net.URI;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.test.project.model.board.dto.BoardSearchDto;
import com.test.project.service.BoardService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class ViewController {
  private final BoardService boardService;

  @GetMapping("/")
  public String main() {
    return "home";
  }
  @GetMapping("/member/**")
  public void member() {}

  @GetMapping("/board/list")
  public String boardList(@ModelAttribute BoardSearchDto boardSearchDto, Model model) {
    
    model.addAttribute("pageInfo", boardService.findAll(boardSearchDto));
    return "board/list";
  }

  public URI getBoardResourceUri() {
    return URI.create(ServletUriComponentsBuilder
    .fromCurrentContextPath()
    .path("/api/board/list")
    .toUriString());
  }

}
