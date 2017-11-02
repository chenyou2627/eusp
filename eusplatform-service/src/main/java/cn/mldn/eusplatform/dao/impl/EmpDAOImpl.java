package cn.mldn.eusplatform.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.eusplatform.dao.IEmpDAO;
import cn.mldn.eusplatform.vo.Dept;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.util.dao.abs.AbstractDAO;

public class EmpDAOImpl extends AbstractDAO implements IEmpDAO {

	@Override
	public boolean doCreate(Emp vo) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO emp (eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,locked)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getEid());
		super.pstmt.setLong(2, vo.getLid());
		super.pstmt.setLong(3, vo.getDid());
		super.pstmt.setString(4, vo.getEname());
		super.pstmt.setDouble(5, vo.getSalary());
		super.pstmt.setString(6, vo.getPhone());
		super.pstmt.setString(7, vo.getPassword());
		super.pstmt.setString(8, vo.getPhoto());
		super.pstmt.setString(9, vo.getNote());
		super.pstmt.setDate(10, new java.sql.Date(vo.getHiredate().getTime()));
		super.pstmt.setString(11, vo.getIneid());
		super.pstmt.setInt(12, 0);
		return super.pstmt.executeUpdate() >0;
	}

	@Override
	public boolean doEdit(Emp vo) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "UPDATE emp SET lid=?,did=?,ename=?,salary=?,phone=?,password=?,photo=?,note=?,hiredate=?,ineid=? WHERE eid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, vo.getLid());
		super.pstmt.setLong(2, vo.getDid());
		super.pstmt.setString(3, vo.getEname());
		super.pstmt.setDouble(4, vo.getSalary());
		super.pstmt.setString(5, vo.getPhone());
		super.pstmt.setString(6, vo.getPassword());
		super.pstmt.setString(7, vo.getPhoto());
		super.pstmt.setString(8, vo.getNote());
		super.pstmt.setDate(9, new java.sql.Date(vo.getHiredate().getTime()));
		super.pstmt.setString(10, vo.getIneid());
		super.pstmt.setString(11, vo.getEid());
		return super.pstmt.executeUpdate() >0;
	}

	@Override
	public boolean doRemove(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<String> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Emp findById(String id) throws SQLException {
		String sql = "SELECT eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,locked "
				+ " FROM emp WHERE eid=? AND locked=0 " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, id);
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			Emp emp = new Emp() ;
			emp.setEid(rs.getString(1));
			emp.setLid(rs.getLong(2));
			emp.setDid(rs.getLong(3));
			emp.setEname(rs.getString(4));
			emp.setSalary(rs.getDouble(5));
			emp.setPhone(rs.getString(6));
			emp.setPassword(rs.getString(7));
			emp.setPhoto(rs.getString(8));
			emp.setNote(rs.getString(9));
			emp.setHiredate(rs.getDate(10));
			emp.setIneid(rs.getString(11));
			emp.setLocked(rs.getInt(12));
			return emp ;
		}
		return null;
	}

	@Override
	public List<Emp> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Emp> findAll(Long currentPage, Integer lineSize) throws Exception {
		String sql = "SELECT eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,locked FROM emp LIMIT ?,?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, (currentPage - 1) * lineSize);
		super.pstmt.setLong(2, lineSize);
		ResultSet rs = super.pstmt.executeQuery();
		List<Emp> all = new ArrayList<Emp>();
		while (rs.next()) {
			Emp vo = new Emp();
			vo.setEid(rs.getString(1));
			vo.setLid(rs.getLong(2));
			vo.setDid(rs.getLong(3));
			vo.setEname(rs.getString(4));
			vo.setSalary(rs.getDouble(5));
			vo.setPhone(rs.getString(6));
			vo.setPassword(rs.getString(7));
			vo.setPhoto(rs.getString(8));
			vo.setNote(rs.getString(9));
			vo.setHiredate(rs.getDate(10));
			vo.setIneid(rs.getString(11));
			vo.setLocked(rs.getInt(12));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Emp> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
		List<Emp> all = new ArrayList<Emp>();
		String sql = "SELECT eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,locked "
				+ "FROM emp WHERE " + column + " LIKE ? LIMIT ?,?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%" + keyWord + "%");
		super.pstmt.setLong(2, (currentPage - 1) * lineSize);
		super.pstmt.setLong(3, lineSize);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			Emp vo = new Emp();
			vo.setEid(rs.getString(1));
			vo.setLid(rs.getLong(2));
			vo.setDid(rs.getLong(3));
			vo.setEname(rs.getString(4));
			vo.setSalary(rs.getDouble(5));
			vo.setPhone(rs.getString(6));
			vo.setPassword(rs.getString(7));
			vo.setPhoto(rs.getString(8));
			vo.setNote(rs.getString(9));
			vo.setHiredate(rs.getDate(10));
			vo.setIneid(rs.getString(11));
			vo.setLocked(rs.getInt(12));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Long getAllCount() throws SQLException {
		String sql = "SELECT COUNT(*) FROM emp";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getLong(1);
		}
		return 0L;
	}

	@Override
	public Long getSplitCount(String column, String keyWord) throws SQLException {
		String sql = "SELECT COUNT(*) FROM emp WHERE " + column + " LIKE ?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%" + keyWord + "%");
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getLong(1);
		}
		return 0L;
	}

	@Override
	public Emp findByEidIsExist(String eid) throws SQLException {
		// TODO Auto-generated method stub
		Emp vo = null;
		String sql = "SELECT eid FROM emp WHERE eid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, eid);
		ResultSet rs = super.pstmt.executeQuery();
		if(rs.next()) {
			vo = new Emp();
			vo.setEid(rs.getString(1));
		}
		return vo;
	}

	@Override
	public List<Dept> findAllDept() throws SQLException {
		// TODO Auto-generated method stub
		List<Dept> all = new ArrayList<Dept>();
		String sql = "SELECT did,dname FROM dept";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			Dept vo = new Dept();
			vo.setDid(rs.getLong(1));
			vo.setDname(rs.getString(2));
			all.add(vo);
		}
		return all;
	}

	@Override
	public boolean doUpdateByManager(Long did, Long lid) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "UPDATE emp SET lid=? WHERE did=? AND lid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, 3);
		super.pstmt.setLong(2, did);
		super.pstmt.setLong(3, lid);
		return super.pstmt.executeUpdate() >0;
	}

	@Override
	public List<Emp> findAllByDid(Long lid,Long did, Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		String sql = "SELECT e.eid,e.lid,e.did,e.ename,e.salary,e.phone,e.password,e.photo,e.note,"
				+ "e.hiredate,e.ineid,e.locked"
				+ " FROM emp e "
				+ "WHERE e.eid not in (SELECT eid FROM schedule_emp)"
				+ "AND e.lid >= "+lid+" "
				+ "AND e.did=? LIMIT ?,?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, did);
		super.pstmt.setLong(2, (currentPage - 1) * lineSize);
		super.pstmt.setLong(3, lineSize);
		ResultSet rs = super.pstmt.executeQuery();
		List<Emp> all = new ArrayList<Emp>();
		while (rs.next()) {
			Emp vo = new Emp();
			vo.setEid(rs.getString(1));
			vo.setLid(rs.getLong(2));
			vo.setDid(rs.getLong(3));
			vo.setEname(rs.getString(4));
			vo.setSalary(rs.getDouble(5));
			vo.setPhone(rs.getString(6));
			vo.setPassword(rs.getString(7));
			vo.setPhoto(rs.getString(8));
			vo.setNote(rs.getString(9));
			vo.setHiredate(rs.getDate(10));
			vo.setIneid(rs.getString(11));
			vo.setLocked(rs.getInt(12));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Long getAllCount(Long lid,Long did) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT COUNT(*) FROM emp WHERE did=? AND lid >= "+lid+"";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, did);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getLong(1);
		}
		return 0L;
	}


}
