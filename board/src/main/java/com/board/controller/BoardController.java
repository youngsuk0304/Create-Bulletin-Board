package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Inject
	BoardDao service;
	// �Խù� ���
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void getList(Model model) throws Exception {
		List list = null;
		list = service.list();
		model.addAttribute("list", list);
 }
	
	// �Խù� �ۼ�
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void getWirte() throws Exception {
	   
	}
	
	// �Խù� �ۼ�
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String posttWirte(BoardDto vo) throws Exception {
	  service.write(vo);
	  
	  return "redirect:/board/list";
	}
}