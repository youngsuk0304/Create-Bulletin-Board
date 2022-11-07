package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reply/*")
public class BoardReController {
	@Inject
	BoardDao dao;

	@Inject
	BoardReDao redao;

	// 게시물 조회
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void getView(@RequestParam("board_no") int board_no, Model model) throws Exception {
		
		BoardDto vo = dao.view(board_no);
		model.addAttribute("view", vo);
		dao.readcount(board_no);// /view로 시작되는 주소로 이동시 board_no으로 된 조회소 1증가
		
		// 댓글 조회
		List<BoardReDto> reply = null;
		reply=redao.list(board_no);
		model.addAttribute("reply",reply);
	}

	
}
