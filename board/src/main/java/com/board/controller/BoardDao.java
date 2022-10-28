package com.board.controller;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<BoardDto> sel(){
		return sqlSession.selectList("sql.sel");
	}
	// 게시물 목록
	public List<BoardDto> list(){
		return sqlSession.selectList("sql.list");
	}
	// 게시물 작성
	public void write(BoardDto vo) throws Exception{
		sqlSession.insert("sql.write", vo);

	}
	// 게시물 조회
	public BoardDto view(int board_no) throws Exception {
	 
	 return sqlSession.selectOne("sql.view", board_no);
	}
	
	// 게시물 수정
	public void modify(BoardDto vo) throws Exception {
		sqlSession.update("sql.modify", vo);
	}
	
	// 게시물 삭제
	public void delete(int board_no) throws Exception {
		sqlSession.delete("sql.delete", board_no);
	}
	
	// 게시물 총 갯수
	public int count() throws Exception {
	 return sqlSession.selectOne("sql.count"); 
	}
	
	// 게시물 목록 + 페이징
	public List listPage(int displayPost, int postNum) throws Exception {

	 HashMap data = new HashMap();
	  
	 data.put("displayPost", displayPost);
	 data.put("postNum", postNum);
	  
	 return sqlSession.selectList("sql.listPage", data);
	}
}
