package com.board.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Handles requests for the application home page.
 */

@Controller
public class BoardController{
	@Autowired
	BoardDao boardDao;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		List<BoardDto> list = boardDao.sel();
		for(int i=0; i<list.size(); i++){
			model.addAttribute("board_no", list.get(i).getBoard_no());
			model.addAttribute("board_title", list.get(i).getBoard_title() );
			model.addAttribute("board_content", list.get(i).getBoard_content() );
			model.addAttribute("board_writer", list.get(i).getBoard_writer() );
		}
		return "home";
	}
	
}
