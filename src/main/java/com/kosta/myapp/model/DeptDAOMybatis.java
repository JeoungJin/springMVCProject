package com.kosta.myapp.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.dto.DeptDTO;

@Repository
public class DeptDAOMybatis {
	
	Logger logger = LoggerFactory.getLogger(DeptDAOMybatis.class);
	
	
	@Autowired
	SqlSession sqlSession;
	
	static final String NAMESPACE="net.gasan.dept.";
	
	// 1.모두조회
	public List<DeptDTO> selectAll() {
		logger.info("mybatis:" + NAMESPACE + "selectAll");
		return sqlSession.selectList(NAMESPACE + "selectAll");
	}

	// 2.특정부서조회(부서코드로 조회)
	public DeptDTO selectById(int deptid) {
		logger.info("mybatis:" + NAMESPACE + "selectById");
		return sqlSession.selectOne(NAMESPACE+"selectById", deptid);
	}

	// 3.지역코드로 조회
	public List<DeptDTO> selectByLocation(int locid) {
		logger.info("mybatis:" + NAMESPACE + "selectByLocation");
		return sqlSession.selectList(NAMESPACE+"selectByLocation", locid);
	}
	// 4.신규부서입력
	public int deptInsert(DeptDTO dept) {
		logger.info("mybatis:" + NAMESPACE + "insert");
		return sqlSession.insert(NAMESPACE+"insert", dept);
	}
	// 5.특정부서 수정
	public int deptUpdate(DeptDTO dept) {
		logger.info("mybatis:" + NAMESPACE + "update");
		return sqlSession.update(NAMESPACE+"update", dept);
	}

	// 6.특정부서 삭제
	public int deptDelete(int deptid) {
		logger.info("mybatis:" + NAMESPACE + "delete");
		return sqlSession.delete(NAMESPACE+"delete", deptid);
	}
}
