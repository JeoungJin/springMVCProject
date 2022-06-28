package com.kosta.myapp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.dto.EmpVO;
import com.kosta.dto.JobVO;

//CRUD작업(insert(C), select(R),update(U), Delete(D)==>DAO(Data Access Object)
@Repository
public class EmpDAOMybatis {
 
	@Autowired
	SqlSession session;
	
	static final String NAME_SPACE = "net.gasan.emp.";
	Logger logger = LoggerFactory.getLogger(EmpDAOMybatis.class);
	// 1.모든직원조회
	public List<EmpVO> selctAll() {
		List<EmpVO> emplist = session.selectList(NAME_SPACE + "selectAll");
		logger.info("selctAll결과:" + emplist.size());		
		return emplist;
	}

	// 모든jobs조회
	public List<JobVO> selctJobAll() {
		List<JobVO> joblist = session.selectList(NAME_SPACE + "selectJobAll");
		logger.info("selctJobAll결과:" + joblist.size());		
		return joblist;
	}

	// 모든 manager조회
	public List<EmpVO> selctManagerAll() {
		List<EmpVO> emplist = session.selectList(NAME_SPACE + "selectbyManager");
		logger.info("selctManagerAll결과:" + emplist.size());		
		return emplist;
	}

	 

	// 2.조건조회(특정부서)
	public List<EmpVO> selctByDept(int deptid) {
		List<EmpVO> emplist = session.selectList(NAME_SPACE + "selectByDept", deptid);
		logger.info("selctByDept결과:" + emplist.size());		
		return emplist;
	}

	// 3.조건조회(특정메니저)
	public List<EmpVO> selctByManager(int mid) {
		List<EmpVO> emplist = session.selectList(NAME_SPACE + "selectByManager2", mid);
		logger.info("selctByManager결과:" + emplist.size());		
		return emplist;
	}

	// 4.조건조회(특정job)
	public List<EmpVO> selctByJob(String job_id) {
		List<EmpVO> emplist = session.selectList(NAME_SPACE + "selectByJob", job_id);
		logger.info("selctByJob결과:" + emplist.size());		
		return emplist;
	}

	// 5.조건조회(특정 department_id=, job_id=, salary>=?, hire_date>=?)
	public List<EmpVO> selctByCondition(int deptid, String job_id, double sal, String hire_date) {
		Map<String,Object> map = new HashMap<>();
		map.put("salary", sal);
		map.put("deptid", deptid);
		map.put("jobid", job_id);
		map.put("hiredate", hire_date);
		List<EmpVO> emplist = session.selectList(NAME_SPACE + "selectByCondition", map);
		logger.info("selectByCondition결과:" + emplist.size());		
		return emplist;
	}

	// 6.특정직원 1건 조회
	public EmpVO selectById(int empid) {
		EmpVO emp = session.selectOne(NAME_SPACE + "selectById", empid);
		logger.info("selectById결과:" + emp.toString());		
		return emp;
	}

	// 7.insert
	public int empInsert(EmpVO emp) {
		int result = session.insert(NAME_SPACE + "insert", emp);
		logger.info("empInsert결과:" + result + "건 입력");	
		return result;
	}

	// 8.update(특정직원 1건 employee_id=?)
	public int empUpdate(EmpVO emp) {
		int result = session.update(NAME_SPACE + "update", emp);
		logger.info("empUpdate결과:" + result + "건 수정");		
		return result;
	}

 	// 10.delete(특정직원 1건 employee_id=?)
	public int empDelete(int empid) {
		int result = session.delete(NAME_SPACE + "delete", empid);
		logger.info("empUpdate결과:" + result + "건 삭제");		
		return result;
	}

	// 11.delete(조건 department_id=?)
	public int empDeleteByDept(int deptid) {
		int result = session.delete(NAME_SPACE + "deleteByDept", deptid);
		logger.info("empDeleteByDept결과:" + result + "건 삭제");		
		return result;
	}
	
	//이메일 중복체크하기 
	public int selectByEmail(String email) {
		int result = session.selectOne(NAME_SPACE + "selectByEmailDup", email);
		logger.info("selectByEmail결과:" + result + "건 조회");		
		return result;
	}
	

}
