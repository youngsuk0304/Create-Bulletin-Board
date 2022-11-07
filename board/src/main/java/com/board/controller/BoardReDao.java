package com.board.controller;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardReDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// 댓글 조회
	public List<BoardReDto> list(int brd_no) throws Exception {
	    return sqlSession.selectList("replySql.replyList", brd_no);
	}

	// 댓글 작성
	public void write(BoardReDto vo) throws Exception {
		sqlSession.insert("replySql.replyWrite", vo);
	}

	// 댓글 수정
	public void modify(BoardReDto vo) throws Exception {
		sqlSession.update("replySql.replyModify", vo);
	}

	// 댓글 삭제
	public void delete(BoardReDto vo) throws Exception {
		sqlSession.delete("replySql.replyDelete", vo);
	}
}
