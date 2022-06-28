package com.kosta.myapp.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.dto.BoardDTO;
import com.kosta.dto.BoardEmpVO;
import com.kosta.util.DBUtil;
import com.kosta.util.LogPreparedStatement;

@Repository
public class BoardDAO {

	@Autowired
	DataSource ds ;
	
	static final String SQL_SELECT_ALL = "select * from tbl_board order by 1 desc";
	static final String SQL_SELECT_BYID = "select * from tbl_board where bno = ?";
	static final String SQL_SELECT_BYWRITER = "select * from tbl_board where writer = ?";
	static final String SQL_SELECT_BYTITLE = "select * from tbl_board where title like ?";
	static final String SQL_SELECT_BYREGDATE = "select * from tbl_board where regdate between ? and ?";
	static final String SQL_INSERT = "INSERT INTO tbl_board values( seq_bno.nextval, ?, ?, ?, sysdate, sysdate, ?)";
	static final String SQL_UPDATE = "update tbl_board set title = ?, content=?, updatedate=sysdate  where bno = ?";
	static final String SQL_DELETE = "delete from tbl_board where bno = ? ";
	static final String SQL_BOARDEMP = "SELECT b.bno, b.title, b.content , e.first_name ||' '|| e.last_name fullname\r\n"
			                         + "FROM TBL_BOARD b JOIN EMPLOYEES e ON (b.writer = e.EMPLOYEE_ID )";
	
	Connection conn;
	LogPreparedStatement st;
	ResultSet rs;
	int result;
	
	
	public List<BoardEmpVO> selectAllJoin() {
		List<BoardEmpVO> blist = new ArrayList<>();
		BoardEmpVO board = null;
		
		try {
			conn = ds.getConnection(); //CP이용해서 Connection얻기 
			st = new LogPreparedStatement(conn, SQL_BOARDEMP);
			rs = st.executeQuery();
			while(rs.next()) {
				board = new BoardEmpVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				blist.add(board);
			}				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}		
		return blist;
	}
	
	
	public List<BoardDTO> selectAll() {
		List<BoardDTO> blist = new ArrayList<>();
		BoardDTO board = null;
		
		try {
			conn = ds.getConnection();
			st = new LogPreparedStatement(conn,SQL_SELECT_ALL);
			rs = st.executeQuery();
			while(rs.next()) {
				board = makeBoard(rs);
				blist.add(board);
			}				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}		
		return blist;
	}
	public BoardDTO selectById(int bno) {
		BoardDTO board = null;
		
		try {
			conn = ds.getConnection();
			st = new LogPreparedStatement(conn,SQL_SELECT_BYID);
			st.setInt(1, bno);
			System.out.println(st.toString());
			rs = st.executeQuery();
			while(rs.next()) {
				board = makeBoard(rs);
			}				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}		
		return board;
	}
	private BoardDTO makeBoard(ResultSet rs) throws SQLException {
		BoardDTO board = new BoardDTO();
		board.setBno(rs.getInt(1));
		board.setTitle(rs.getString(2));
		board.setContent(rs.getString(3));
		board.setWriter(rs.getInt(4));
		board.setRegdate(rs.getDate(5));
		board.setUpdatedate(rs.getDate(6));
		board.setPic(rs.getString("pic"));
		return board;
	}
	
	public List<BoardDTO> selectByWriter(int writer) {
		List<BoardDTO> blist = new ArrayList<>();
		BoardDTO board = null;
		
		try {
			conn = ds.getConnection();
			st = new LogPreparedStatement(conn,SQL_SELECT_BYWRITER);
			st.setInt(1, writer);
			rs = st.executeQuery();
			while(rs.next()) {
				board = makeBoard(rs);
				blist.add(board);
			}				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}		
		return blist;
	}
	public List<BoardDTO> selectByTitle(String title) {
		List<BoardDTO> blist = new ArrayList<>();
		BoardDTO board = null;
		
		try {
			conn = ds.getConnection();
			st = new LogPreparedStatement(conn,SQL_SELECT_BYTITLE);
			st.setString(1, "%"+title+"%");
			System.out.println(st);
			rs = st.executeQuery();
			while(rs.next()) {
				board = makeBoard(rs);
				blist.add(board);
			}				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}		
		return blist;
	}
	public List<BoardDTO> selectByRegDate(Date sdate, Date edate) {
		List<BoardDTO> blist = new ArrayList<>();
		BoardDTO board = null;
		
		try {
			conn = ds.getConnection();
			st = new LogPreparedStatement(conn,SQL_SELECT_BYREGDATE);
			st.setDate(1, sdate);
			st.setDate(2, edate);
			System.out.println(st);
			rs = st.executeQuery();
			while(rs.next()) {
				board = makeBoard(rs);
				blist.add(board);
			}				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}		
		return blist;
	}
	public int insert(BoardDTO board) {
		 
		
		try {
			conn = ds.getConnection();
			st = new LogPreparedStatement(conn,SQL_INSERT);
            st.setString(1, board.getTitle());
            st.setString(2, board.getContent());
            st.setInt(3, board.getWriter());
            st.setString(4, board.getPic());
			result = st.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}		
		return result;
	}
	public int update(BoardDTO board) {
		
		try {
			conn = ds.getConnection();
			st = new LogPreparedStatement(conn,SQL_UPDATE);
            st.setString(1, board.getTitle());
            st.setString(2, board.getContent());
            st.setInt(3, board.getBno());
			result = st.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}		
		return result;
	}
	public int delete(int bno) {
		
		try {
			conn = ds.getConnection();
			st = new LogPreparedStatement(conn,SQL_DELETE);
            st.setInt(1, bno);
			result = st.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}		
		return result;
	}
}




