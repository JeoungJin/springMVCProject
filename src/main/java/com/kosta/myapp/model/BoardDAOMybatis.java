package com.kosta.myapp.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.dto.BoardDTO;
import com.kosta.dto.BoardEmpVO;

@Repository
public class BoardDAOMybatis {
	@Autowired
	SqlSession session;
	
	Logger logger = LoggerFactory.getLogger(BoardDAOMybatis.class);
	
	static final String NAMESPACE="net.gasan.board.";
	
	public List<BoardEmpVO> selectAllJoin() {
		logger.info("Mybatis:selectAllJoin()...");
		return session.selectList(NAMESPACE + "boardEmpInfo");
	}

	public List<BoardDTO> selectAll() {
		logger.info("Mybatis:selectAll()...");
		return session.selectList(NAMESPACE + "selectAll");
	}
	public BoardDTO selectById(int bno) {
		logger.info("Mybatis:selectById()...");
		return session.selectOne(NAMESPACE + "selectById", bno);
	}
	 
	
	public List<BoardDTO> selectByWriter(int writer) {
		logger.info("Mybatis:selectByWriter()...");
		return session.selectList(NAMESPACE + "selectByWriter", writer);
	}
	public List<BoardDTO> selectByTitle(String title) {
		logger.info("Mybatis:selectByTitle()...");
		return session.selectList(NAMESPACE + "selectByTitle", title);
	}
	public List<BoardDTO> selectByRegDate(Date sdate, Date edate) {
		 Map<String, Date> dateMap = new HashMap<>();
		 dateMap.put("sdate", sdate);
		 dateMap.put("edate", edate);
		 logger.info("Mybatis:selectByRegdate()..." + dateMap);
		 return session.selectList(NAMESPACE + "selectByRegdate", dateMap);
		 
	}
	public int insert(BoardDTO board) {
		logger.info("Mybatis:boardInsert()..." +board );
		return session.insert(NAMESPACE + "boardInsert", board);
	}
	public int update(BoardDTO board) {
		logger.info("Mybatis:boardUpdate()..." +board );
		return session.update(NAMESPACE + "boardUpdate", board);
	}
 
	public int delete(int bno) {
		logger.info("Mybatis:boardDelete()..." +bno );
		return session.delete(NAMESPACE + "boardDelete", bno);
	}
		 
 
}




