package com.kosta.myapp.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.dto.DeptDTO;
//DAO(Data Access Object):DB에 접근하는 Business Logic작성한다. 
import com.kosta.util.DBUtil;

@Repository
public class DeptDAO {

	@Autowired
	DataSource ds;

	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;

	// 1.모두조회
	public List<DeptDTO> selectAll() {
		List<DeptDTO> deptList = new ArrayList<>();

		String sql = "select * from departments order by 1";
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				DeptDTO dept = makeDept(rs);
				deptList.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return deptList;
	}

	private DeptDTO makeDept(ResultSet rs) throws SQLException {
		int deptid = rs.getInt(1);
		String deptname = rs.getString(2);
		int mid = rs.getInt(3);
		int loc = rs.getInt(4);
		DeptDTO dept = new DeptDTO(deptid, deptname, mid, loc);
		return dept;
	}

	// 2.특정부서조회(부서코드로 조회)
	public DeptDTO selectById(int deptid) {
		DeptDTO dept = null;

		String sql = "select * from departments  where DEPARTMENT_ID = " + deptid;
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				dept = makeDept(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return dept;
	}

	// 3.지역코드로 조회
	public List<DeptDTO> selectByLocation(int locid) {
		List<DeptDTO> deptList = new ArrayList<>();

		String sql = "select * from departments  where location_id = " + locid + "  order by 1";
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				DeptDTO dept = makeDept(rs);
				deptList.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return deptList;
	}

	// 4.신규부서입력
	public int deptInsert(DeptDTO dept) {

		int ret = 0;
		String sql = "insert into departments values(" + dept.getDepartment_id() + ",'" + dept.getDepartment_name()
				+ "'," + dept.getManager_id() + "," + dept.getLocation_id() + ") ";
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			ret = st.executeUpdate(sql); // -1(error), 1(insert1건) , 0(insert건수없음)
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return ret;
	}

	// 5.특정부서 수정
	public int deptUpdate(DeptDTO dept) {

		int ret = 0;
		String sql = "update departments " + "set DEPARTMENT_NAME='" + dept.getDepartment_name() + "',MANAGER_ID="
				+ dept.getManager_id() + ", LOCATION_ID=" + dept.getLocation_id() + " where DEPARTMENT_ID="
				+ dept.getDepartment_id();
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			ret = st.executeUpdate(sql); //
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return ret;
	}

	// 6.특정부서 삭제
	public int deptDelete(int deptid) {

		int ret = 0;
		String sql = "delete from departments where department_id = " + deptid;
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			ret = st.executeUpdate(sql); //
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return ret;
	}
}
