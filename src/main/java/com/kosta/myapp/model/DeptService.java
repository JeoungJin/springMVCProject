package com.kosta.myapp.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.dto.DeptDTO;

//Service : Business Logic작성한다. 
//DB작업은 DAO호출한다.

@Service
public class DeptService {
	
	@Autowired
	private DeptDAOMybatis deptDAO;
	//private DeptDAO deptDAO;
	
	// 1.모두조회
	public List<DeptDTO> selectAll() {
		return deptDAO.selectAll();
	}

	// 2.특정부서조회(부서코드로 조회)
	public DeptDTO selectById(int deptid) {
		return deptDAO.selectById(deptid);
	}

	// 3.지역코드로 조회
	public List<DeptDTO> selectByLocation(int locid) {
		return deptDAO.selectByLocation(locid);
	}

	// 4.신규부서입력
	public int deptInsert(DeptDTO dept) {
		return deptDAO.deptInsert(dept);
	}

	// 5.특정부서 수정
	public int deptUpdate(DeptDTO dept) {
		return deptDAO.deptUpdate(dept);
	}

	// 6.특정부서 삭제
	public int deptDelete(int deptid) {
		return deptDAO.deptDelete(deptid);
	}

}
