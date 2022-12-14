package com.board.controller;

import java.util.Date;
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
	BoardDao dao;

	// 게시물 목록
	@RequestMapping(value = "/list", method = RequestMethod.GET) // value="/list"의 list는 list.jsp를 의미
	public void getList(Model model) throws Exception {
		List list = null;
		list = dao.list();
		model.addAttribute("list", list);
	}

	// 게시물 작성
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void getWirte() throws Exception {

	}

	Date now = new Date();

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String posttWirte(BoardDto vo) throws Exception {
		Date now=new Date();
		vo.setBoard_regDate(now);
		//vo.setBoard_regDate(now);
		dao.write(vo);

		return "redirect:/board/list";
	}

	// 게시물 조회
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void getView(@RequestParam("board_no") int board_no, Model model) throws Exception {
		BoardDto vo = dao.view(board_no);
		model.addAttribute("view", vo);
		dao.readcount(board_no);// /view로 시작되는 주소로 이동시 board_no으로 된 조회소 1증가
	}

	// 게시물 수정 페이지에 view 전달
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void getModify(@RequestParam("board_no") int board_no, Model model) throws Exception {

		BoardDto vo = dao.view(board_no);

		model.addAttribute("view", vo);
	}

	// 게시물 수정페이지에서 변경된 값을 수정하고 redirect를 이용하여 바로 /board/view?board_no=" +
	// vo.getBoard_no();로 페이지 이동
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String postModify(BoardDto vo) throws Exception {

		dao.modify(vo);

		return "redirect:/board/view?board_no=" + vo.getBoard_no();
	}

	// 게시물 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String getDelete(@RequestParam("board_no") int board_no) throws Exception {

		dao.delete(board_no);

		return "redirect:/board/list";
	}

	// 게시물 목록 + 페이징 추가
	@RequestMapping(value = "/listpage", method = RequestMethod.GET)
	public void getListPage(Model model) throws Exception {

		List list = null;
		list = dao.list();
		model.addAttribute("list", list);
	}

	// 게시물 목록 + 페이징 추가
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void getListPage(Model model, @RequestParam("num") int num) throws Exception {

		Page page = new Page();
		page.setNum(num);
		page.setCount(dao.count());

		List list = null;
		list = dao.listPage(page.getDisplayPost(), page.getPostNum());

		model.addAttribute("list", list);
		model.addAttribute("pageNum", page.getPageNum());

//		model.addAttribute("startPageNum", page.getStartPageNum());
//		model.addAttribute("endPageNum", page.getEndPageNum());
//		 
//		model.addAttribute("prev", page.getPrev());
//		model.addAttribute("next", page.getNext());  

		model.addAttribute("page", page);

		model.addAttribute("select", num);
//	 // 게시물 총 갯수
//	 int count = service.count();
//	  
//	 // 한 페이지에 출력할 게시물 갯수
//	 int displayPost = 5;
//	  
//	 // 하단 페이징 번호 ([ 게시물 총 갯수 ÷ 한 페이지에 출력할 갯수 ]의 올림)
//	 int pageNum = (int)Math.ceil((double)count/displayPost);
//	  
//	 // 출력할 게시물
//	 int postNum = (num-1) * displayPost;
//	    
//	// 한번에 표시할 페이징 번호의 갯수
//	 int pageNum_cnt = 3;
//
//	 // 표시되는 페이지 번호 중 마지막 번호
//	 int endPageNum = (int)(Math.ceil((double)num / (double)pageNum_cnt) * pageNum_cnt);
//
//	 // 표시되는 페이지 번호 중 첫번째 번호
//	 int startPageNum = endPageNum - (pageNum_cnt - 1);
//	 
//	// 마지막 번호 재계산
//	 int endPageNum_tmp = (int)(Math.ceil((double)count / (double)pageNum_cnt));
//	  
//	 if(endPageNum > endPageNum_tmp) {
//	  endPageNum = endPageNum_tmp;
//	 }
//	 
//	 boolean prev = startPageNum == 1 ? false : true;
//	 boolean next = endPageNum * pageNum_cnt >= count ? false : true;
//	 
//	 List list = null; 
//	 list = service.listPage(displayPost, postNum);
//	 model.addAttribute("list", list);   
//	 model.addAttribute("pageNum", pageNum);
//	 
//	// 시작 및 끝 번호
//	 model.addAttribute("startPageNum", startPageNum);
//	 model.addAttribute("endPageNum", endPageNum);
//
//	 // 이전 및 다음 
//	 model.addAttribute("prev", prev);
//	 model.addAttribute("next", next);
//	 
//	// 현재 페이지
//	 model.addAttribute("select", num);
	}
//	// 게시물 목록 + 페이징 추가 + 검색
//	@RequestMapping(value = "/listPageSearch", method = RequestMethod.GET)
//	public void getListPageSearch(Model model, @RequestParam("num") int num) throws Exception {
//	
//		Page page=new Page();
//		page.setNum(num);
//		page.setCount(service.count());  
//
//		List list = null; 
//		list = service.listPage(page.getDisplayPost(), page.getPostNum());
//
//		model.addAttribute("list", list);   
//		model.addAttribute("pageNum", page.getPageNum());
//
//		model.addAttribute("page", page);
//
//		model.addAttribute("select", num);
//	}

	// 게시물 목록 + 페이징 추가 + 검색
	@RequestMapping(value = "/listPageSearch", method = RequestMethod.GET)
	public void getListPageSearch(Model model, @RequestParam("num") int num,
			@RequestParam(value = "searchType", required = false, defaultValue = "title") String searchType,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) throws Exception {
		// value 받고자할 데이터의 키, required는 해당 데이터의 필수여부, defaultValue는 데이터가 들어오지 않을경우의 기본값
		Page page = new Page();
		page.setNum(num);
		// page.setCount(service.count());
		page.setCount(dao.searchCount(searchType, keyword));

		// 검색 타입과 검색어
		// page.setSearchTypeKeyword(searchType, keyword);
		page.setSearchType(searchType);
		page.setKeyword(keyword);

		List<BoardDto> list = null;
		// list = service.listPage(page.getDisplayPost(), page.getPostNum());
		list = dao.listPageSearch(page.getDisplayPost(), page.getPostNum(), searchType, keyword);

		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("select", num);

//		model.addAttribute("searchType", searchType);
//		model.addAttribute("keyword", keyword);
	}
}