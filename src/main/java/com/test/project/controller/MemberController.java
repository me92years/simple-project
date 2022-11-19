package com.test.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

	@PostMapping("/member/detail")
	public String member_detail() {
		return "member/detail";
	}
	
	@GetMapping("/member/**")
	public void member() {}
}
