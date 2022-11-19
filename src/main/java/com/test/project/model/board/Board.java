package com.test.project.model.board;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Board {
  private Long id;
  private String title;
  private String content;
  private String author;
  private String fileName;
  private Date createdDate;
  private Date modifiedDate;
}
