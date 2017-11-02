package cn.mldn.eusplatform.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import cn.mldn.eusplatform.dao.IDeptDAO;
import cn.mldn.eusplatform.vo.Dept;
import cn.mldn.util.dao.abs.AbstractDAO;

public class DeptDAOImpl extends AbstractDAO implements IDeptDAO {


	@Override
	public boolean findDnameByOther(Dept vo) throws SQLException {
		String sql = "select dname from dept where dname=? and did != ?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getDname());
		super.pstmt.setLong(2, vo.getDid());
		ResultSet rs = super.pstmt.executeQuery() ;
		while(rs.next()) {
			return true ;
		}
		return false;
	}
	
	@Override
	public Dept findByDname(String dname) throws SQLException {
		Dept dept = null ;
		String sql = "select did,dname,eid,maxnum,currnum from dept where dname = ?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, dname);
		ResultSet rs = super.pstmt.executeQuery() ;
		if(rs.next()) {
			dept = new Dept() ;
			dept.setDid(rs.getLong(1));
			dept.setDname(rs.getString(2));
			dept.setEid(rs.getString(3));
			dept.setMaxnum(rs.getInt(4));
			dept.setCurrnum(rs.getInt(5));
		}
		return dept;
	}
	
	@Override
	public String findDnameByEid(String eid) throws SQLException {
		String ename = "" ;
		String sql = "select dname from dept where eid = ? " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, eid);
		ResultSet rs = super.pstmt.executeQuery() ;
		if(rs.next()) {
			ename = rs.getString(1) ;
		}
		return ename;
	}
	
	@Override
	public boolean doCreate(Dept vo) throws SQLException {
		String sql = "insert into dept(dname,maxnum) values (?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getDname());
		super.pstmt.setInt(2, vo.getMaxnum());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doEdit(Dept vo) throws SQLException {
		String sql = "update dept set dname=?,maxnum=? where did = ? " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getDname()); 
		super.pstmt.setInt(2, vo.getMaxnum());
		super.pstmt.setLong(3, vo.getDid());
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doRemove(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Long> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Dept findById(Long id) throws SQLException {
		Dept vo = null;
		String sql = "SELECT did,dname,eid,maxnum,currnum FROM dept WHERE did=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, id);
		ResultSet rs = super.pstmt.executeQuery();
		if(rs.next()) {
			vo = new Dept();
			vo.setDid(rs.getLong(1));
			vo.setDname(rs.getString(2));
			vo.setEid(rs.getString(3));
			vo.setMaxnum(rs.getInt(4));
			vo.setCurrnum(rs.getInt(5));
		}
		return vo;
	}

	@Override
	public List<Dept> findAll() throws SQLException {
		List<Dept> list = new ArrayList<Dept>() ;
		String sql = "select did,dname,eid,maxnum,currnum from dept" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		ResultSet rs = super.pstmt.executeQuery() ; 
		while(rs.next()) {
			Dept dept = new Dept() ;
			dept.setDid(rs.getLong(1));
			dept.setDname(rs.getString(2));
			dept.setEid(rs.getString(3));
			dept.setMaxnum(rs.getInt(4));
			dept.setCurrnum(rs.getInt(5));
			list.add(dept) ;
		}
		return list;
	}

	@Override
	public List<Dept> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dept> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getAllCount() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getSplitCount(String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Long, String> findAllMap() throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT did,dname FROM dept";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		Map<Long, String> map = new ConcurrentHashMap<Long, String>();
		while(rs.next()) {
			map.put(rs.getLong(1), rs.getString(2));
		}
		return map;
	}

	@Override
	public boolean doUpdateNumBydid(Long did) throws SQLException {
		String sql = "UPDATE dept SET currnum=currnum+1 WHERE did=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, did);
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doUpdateEidByDid(Long did, String eid) throws SQLException {
		String sql = "UPDATE dept SET eid=? WHERE did=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, eid);
		super.pstmt.setLong(2, did);
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean subNumBydid(Long did) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "UPDATE dept SET currnum=currnum-1 WHERE did=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, did);
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean addNumBydid(Long did) throws SQLException {
		String sql = "UPDATE dept SET currnum=currnum+1 WHERE did=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, did);
		return super.pstmt.executeUpdate() > 0 ;
	}

}
