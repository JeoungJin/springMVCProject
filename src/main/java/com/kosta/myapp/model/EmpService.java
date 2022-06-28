package com.kosta.myapp.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.dto.EmpVO;
import com.kosta.dto.JobVO;

//사용자요청-->Controller-->Service-->DAO--->DB
//        <--         <--        <--   <--         

@Service
public class EmpService  {
	
	@Autowired
	EmpDAOMybatis empDAO  ;

	// 1.모든직원조회
	public List<EmpVO> selctAll() {
		return empDAO.selctAll();
	}

	
	public List<JobVO> selctJobAll() {
		return empDAO.selctJobAll();
	}
	
	public List<EmpVO> selctManagerAll() {
		return empDAO.selctManagerAll();
	}
	
	
	
	// 2.조건조회(특정부서)
	public List<EmpVO> selctByDept(int deptid) {
		return empDAO.selctByDept(deptid);
	}

	// 3.조건조회(특정메니저)
	public List<EmpVO> selctByManager(int mid) {
		return empDAO.selctByManager(mid);
	}

	// 4.조건조회(특정job)
	public List<EmpVO> selctByJob(String job_id) {
		return empDAO.selctByJob(job_id);
	}

	// 5.조건조회(특정 department_id=, job_id=, salary>=?, hire_date>=?)
	public List<EmpVO> selctByCondition(int deptid, String job_id, double sal, String hire_date) {
		return empDAO.selctByCondition(deptid, job_id, sal, hire_date);
	}

	// 6.특정직원 1건 조회
	public EmpVO selectById(int empid) {
		return empDAO.selectById(empid);
	}

	// 7.insert
	public int empInsert(EmpVO emp) {

		return empDAO.empInsert(emp);
	}

	// 8.update(특정직원 1건 employee_id=?)
	public int empUpdate(EmpVO emp) {

		return empDAO.empUpdate(emp);
	}
 

	// 10.delete(특정직원 1건 employee_id=?)
	public int empDelete(int empid) {

		return empDAO.empDelete(empid);
	}

	// 11.delete(조건 department_id=?)
	public int empDeleteByDept(int deptid) {

		return empDAO.empDeleteByDept(deptid);
	}
	//이메일 중복체크 
	public int selectByEmail(String email) {
		return empDAO.selectByEmail(email);
	}

	
}
