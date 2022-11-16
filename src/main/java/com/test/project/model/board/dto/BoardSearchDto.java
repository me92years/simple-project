package com.test.project.model.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class BoardSearchDto {
  private int pageNum = 1;
  private int pageSize = 10;
  private String keyword;
  private String search; 
}