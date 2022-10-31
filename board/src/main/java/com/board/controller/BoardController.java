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
		service.readcount(board_no);// /view로 시작되는 주소로 이동시 board_no으로 된 조회소 1증가
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
	
	// 게시물 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String getDelete(@RequestParam("board_no") int board_no) throws Exception {
	  
	 service.delete(board_no);  

	 return "redirect:/board/list";
	}
	
	// 게시물 목록 + 페이징 추가
	@RequestMapping(value = "/listpage", method = RequestMethod.GET)
	public void getListPage(Model model) throws Exception {
	  
		 List list = null; 
		 list = service.list();
		 model.addAttribute("list", list);   
	}
	
	// 게시물 목록 + 페이징 추가
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void getListPage(Model model, @RequestParam("num") int num) throws Exception {
	 
	 // 게시물 총 갯수
	 int count = service.count();
	  
	 // 한 페이지에 출력할 게시물 갯수
	 int displayPost = 5;
	  
	 // 하단 페이징 번호 ([ 게시물 총 갯수 ÷ 한 페이지에 출력할 갯수 ]의 올림)
	 int pageNum = (int)Math.ceil((double)count/displayPost);
	  
	 // 출력할 게시물
	 int postNum = (num-1) * displayPost;
	    
	 List list = null; 
	 list = service.listPage(displayPost, postNum);
	 model.addAttribute("list", list);   
	 model.addAttribute("pageNum", pageNum);
	}
}