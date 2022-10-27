package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;


@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Inject
	BoardDao service;
	// 게시물 목록
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void getList(Model model) throws Exception {
		List list = null;
		list = service.list();
		model.addAttribute("list", list);
 }
	
	// 게시물 작성
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void getWirte() throws Exception {
	   
	}
	
    Date now = new Date();
    
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String posttWirte(BoardDto vo) throws Exception {
		vo.setBoard_regDate(now);
		service.write(vo);
		
		return "redirect:/board/list";
	}
	
	// 게시물 조회
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void getView(@RequestParam("board_no") int board_no, Model model) throws Exception {
		BoardDto vo = service.view(board_no);
		
		model.addAttribute("view",vo);
	}
	
	// 게시물 수정
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void getModify(@RequestParam("board_no") int board_no, Model model) throws Exception {

	 BoardDto vo = service.view(board_no);
	   
	 model.addAttribute("view", vo);
	}
	

	// 게시물 수정
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String postModify(BoardDto vo) throws Exception {

	 service.modify(vo);
	   
	 return "redirect:/board/view?board_no=" + vo.getBoard_no();
	}
}