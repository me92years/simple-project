package com.test.project.model.board;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Board {
  private Long id;
  private String title;
  private String content;
  private String author;
  private Date createDate;
  private Date modifiedDate;
}
