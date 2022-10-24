package com.board.controller;

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
	public List<BoardDto> list(){
		return sqlSession.selectList("sql.list");
	}
	public void write(BoardDto vo) throws Exception{
		sqlSession.insert("sql.write", vo);

	}
}
