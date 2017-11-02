package cn.mldn.eusplatform.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.eusplatform.dao.IReportDAO;
import cn.mldn.eusplatform.vo.Report;
import cn.mldn.util.dao.abs.AbstractDAO;

public class ReportDAOImpl extends AbstractDAO implements IReportDAO {
	
	@Override
	public boolean doReportBatch(Report vo, Set<String> eids) throws SQLException {
		String sql = "insert into report(sid,eid,subdate) values (?,?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		Iterator<String> iter = eids.iterator() ;
		while (iter.hasNext()) {
			super.pstmt.setLong(1, vo.getSid());
			super.pstmt.setString(2, iter.next());
			super.pstmt.setDate(3, new java.sql.Date(vo.getSubdate().getTime()));
			super.pstmt.addBatch();  
		}
		int result [] = super.pstmt.executeBatch() ;
		for (int x = 0 ; x < result.length; x ++) {
			if (result[x] == 0) {
				return false ;
			}
		}
		return true ; 
	}
	@Override
	public Map<Long, String> findAllMap(String eid) throws SQLException {
		Map<Long,String> map = new HashMap<>();
		String sql = "select sid,note from report where eid = ?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, eid);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()){
			map.put(rs.getLong(1), rs.getString(2));
		}
		return map;
	}
	@Override
	public boolean doCreate(Report vo) throws SQLException {
		String sql = "insert into report(sid,eid,subdate) values (?,?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1, vo.getSid());
		super.pstmt.setString(2, vo.getEid());
		super.pstmt.setDate(3, new java.sql.Date(vo.getSubdate().getTime()));
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doEdit(Report vo) throws SQLException {
		String sql = "update report set note = ? where sid = ? and eid = ?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getNote());
		super.pstmt.setLong(2, vo.getSid());
		super.pstmt.setString(3, vo.getEid());
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
	public Report findById(Long sid) throws SQLException {
		String sql = "select eid from report where sid = ?" ;
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, sid);
		ResultSet rs = super.pstmt.executeQuery();
		if(rs.next()){
			Report vo = new Report();
			vo.setEid(rs.getString(1));
			return vo ;
		}
		return null;
	}

	@Override
	public List<Report> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
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
	public List<Report> findAllBySid(Long sid,String eid) throws SQLException {
		List<Report> all = new ArrayList<>();
		if(eid == null || "".equals(eid)){
			String sql = "select srid,sid,eid,subdate,note from report where sid = ?";
			super.pstmt = super.conn.prepareStatement(sql);
			super.pstmt.setLong(1, sid);
		}else{
			String sql = "select srid,sid,eid,subdate,note from report where sid = ? and eid = ?";
			super.pstmt = super.conn.prepareStatement(sql);
			super.pstmt.setLong(1, sid);
			super.pstmt.setString(2, eid);
		}
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()){
			Report vo = new Report();
			vo.setSrid(rs.getLong(1));
			vo.setSid(rs.getLong(2));
			vo.setEid(rs.getString(3));
			vo.setSubdate(rs.getDate(4));
			vo.setNote(rs.getString(5));
			all.add(vo);
		}
		return all;
	}
	@Override
	public Map<String, String> findAllSubdate(Long sid) throws SQLException {
		Map<String,String> map = new HashMap<>();
		String sql = "select eid,subdate from report where sid = ?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, sid);
		ResultSet rs = super.pstmt.executeQuery();
		
		while(rs.next()){
			map.put(rs.getString(1),new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(2)));
		}
		return map;
	}
	@Override
	public Map<String, String> findAllNote(Long sid) throws SQLException {
		Map<String,String> map = new HashMap<>();
		String sql = "select eid,note from report where sid = ?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, sid);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()){
			map.put(rs.getString(1), rs.getString(2));
		}
		return map;
	}
}

