package cn.mldn.eusplatform.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.mldn.eusplatform.dao.IScheduleDAO;
import cn.mldn.eusplatform.vo.Schedule;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.util.dbc.DatabaseConnection;

public class ScheduleDAOImpl extends AbstractDAO implements IScheduleDAO {
	@Override
	public boolean doCreateEmpIntoSchedule(String eid, Long sid) throws SQLException {
		String sql = "insert into schedule_emp (sid,eid) values(?,?)";
		Connection conn = DatabaseConnection.getConnection();
		super.pstmt = conn.prepareStatement(sql);
		super.pstmt.setString(2, eid);
		super.pstmt.setLong(1, sid);
		return super.pstmt.executeUpdate()>0;
	}
	public Long getLastId() throws SQLException {
		String sql = "SELECT last_insert_id()" ;
		this.pstmt = this.conn.prepareStatement(sql) ;
		ResultSet rs = this.pstmt.executeQuery() ;
		if (rs.next()) {
			return rs.getLong(1) ;
		}
		return 0L ;
	}
	public Long getMaxSid()throws SQLException {
		String sql = "select max(sid) from schedule ";
		Connection conn = DatabaseConnection.getConnection();
		super.pstmt = conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			return rs.getLong(1);
		}
		return 0L;
	}
	
	@Override
	public boolean doCreate(Schedule vo) throws SQLException {
		String sql = "insert into schedule (seid,iid,title,sdate,subdate,audit,note)value(?,?,?,?,?,?,?)";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getSeid());
		super.pstmt.setLong(2, vo.getIid());
		super.pstmt.setString(3, vo.getTitle());
		super.pstmt.setDate(4 , new java.sql.Date(vo.getSdate().getTime()));
		super.pstmt.setDate(5, new java.sql.Date(vo.getSubdate().getTime()));
		super.pstmt.setInt(6, vo.getAudit());
		super.pstmt.setString(7, vo.getNote());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doEdit(Schedule vo) throws SQLException {
		String sql = "update schedule set aeid = ?,auddate = ?,audit = ?,anote = ? where sid = ? ";
		super.pstmt = super.conn.prepareStatement(sql);
		
		super.pstmt.setString(1, vo.getAeid());
		super.pstmt.setDate(2, new java.sql.Date(vo.getAuddate().getTime()));
		super.pstmt.setInt(3, vo.getAudit());
		super.pstmt.setString(4, vo.getAnote());
		super.pstmt.setLong(5, vo.getSid());
		return super.pstmt.executeUpdate() > 0;
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
	public Schedule findById(Long id) throws SQLException {
		String sql = "select sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount from schedule where sid = ? ";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, id);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Schedule vo = new Schedule();
			vo.setSid(rs.getLong(1));
			vo.setSeid(rs.getString(2));
			vo.setAeid(rs.getString(3));
			vo.setIid(rs.getLong(4));
			vo.setTitle(rs.getString(5));
			vo.setSdate(rs.getDate(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAudit(rs.getInt(8));
			vo.setNote(rs.getString(9));
			vo.setAuddate(rs.getDate(10));
			vo.setAnote(rs.getString(11));
			vo.setEcount(rs.getInt(12));
			return vo;
		}
		return null;
	}

	@Override
	public List<Schedule> findAll() throws SQLException {
		List<Schedule> all = new ArrayList<>();
		String sql = "select sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount from schedule ";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Schedule vo = new Schedule();
			vo.setSid(rs.getLong(1));
			vo.setSeid(rs.getString(2));
			vo.setAeid(rs.getString(3));
			vo.setIid(rs.getLong(4));
			vo.setTitle(rs.getString(5));
			vo.setSdate(rs.getDate(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAudit(rs.getInt(8));
			vo.setNote(rs.getString(9));
			vo.setAuddate(rs.getDate(10));
			vo.setAnote(rs.getString(11));
			vo.setEcount(rs.getInt(12));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Schedule> findAll(Long currentPage, Integer lineSize) throws SQLException {
		List<Schedule> all = new ArrayList<>();
		String sql = "select sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount from schedule LIMIT ?,?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, (currentPage - 1) * lineSize);
		super.pstmt.setLong(2, lineSize);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Schedule vo = new Schedule();
			vo.setSid(rs.getLong(1));
			vo.setSeid(rs.getString(2));
			vo.setAeid(rs.getString(3));
			vo.setIid(rs.getLong(4));
			vo.setTitle(rs.getString(5));
			vo.setSdate(rs.getDate(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAudit(rs.getInt(8));
			vo.setNote(rs.getString(9));
			vo.setAuddate(rs.getDate(10));
			vo.setAnote(rs.getString(11));
			vo.setEcount(rs.getInt(12));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Schedule> findSplit(String column, String keyWord, Long currentPage, Integer lineSize)
			throws SQLException {
		List<Schedule> all = new ArrayList<>();
		String sql = "select sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount from schedule WHERE " + column + " LIKE ? LIMIT ?,?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%" + keyWord + "%");
		super.pstmt.setLong(2, (currentPage - 1) * lineSize);
		super.pstmt.setLong(3, lineSize);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Schedule vo = new Schedule();
			vo.setSid(rs.getLong(1));
			vo.setSeid(rs.getString(2));
			vo.setAeid(rs.getString(3));
			vo.setIid(rs.getLong(4));
			vo.setTitle(rs.getString(5));
			vo.setSdate(rs.getDate(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAudit(rs.getInt(8));
			vo.setNote(rs.getString(9));
			vo.setAuddate(rs.getDate(10));
			vo.setAnote(rs.getString(11));
			vo.setEcount(rs.getInt(12));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Long getAllCount() throws SQLException {
		String sql = "select count(*) from schedule";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		if(rs.next()){
			return rs.getLong(1);
		}
		return 0L;
	}

	@Override
	public Long getSplitCount(String column, String keyWord) throws SQLException {
		String sql = "select count(*) from schedule where ? like ?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, column);
		super.pstmt.setString(2, "%"+ keyWord +"%");
		ResultSet rs = super.pstmt.executeQuery();
		if(rs.next()){
			return rs.getLong(1);
		}
		return 0L;
	}


	@Override
	public List<Schedule> findAllById(String eid) throws SQLException {
		List<Schedule> all = new ArrayList<>();
		String sql = "select sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount from schedule where seid = ?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, eid);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Schedule vo = new Schedule();
			vo.setSid(rs.getLong(1));
			vo.setSeid(rs.getString(2));
			vo.setAeid(rs.getString(3));
			vo.setIid(rs.getLong(4));
			vo.setTitle(rs.getString(5));
			vo.setSdate(rs.getDate(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAudit(rs.getInt(8));
			vo.setNote(rs.getString(9));
			vo.setAuddate(rs.getDate(10));
			vo.setAnote(rs.getString(11));
			vo.setEcount(rs.getInt(12));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Schedule> findAllById(Long currentPage, Integer lineSize, String eid) throws SQLException {
		List<Schedule> all = new ArrayList<>();
		String sql = "select sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount from schedule where seid = ? LIMIT ?,?  ";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(2, (currentPage - 1) * lineSize);
		super.pstmt.setLong(3, lineSize);
		super.pstmt.setString(1,eid);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Schedule vo = new Schedule();
			vo.setSid(rs.getLong(1));
			vo.setSeid(rs.getString(2));
			vo.setAeid(rs.getString(3));
			vo.setIid(rs.getLong(4));
			vo.setTitle(rs.getString(5));
			vo.setSdate(rs.getDate(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAudit(rs.getInt(8));
			vo.setNote(rs.getString(9));
			vo.setAuddate(rs.getDate(10));
			vo.setAnote(rs.getString(11));
			vo.setEcount(rs.getInt(12));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Schedule> findSplitById(String column, String keyWord, Long currentPage, Integer lineSize, String eid)
			throws SQLException {
		List<Schedule> all = new ArrayList<>();
		String sql = "select sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount from schedule WHERE " + column + " LIKE ? LIMIT ?,? and seid = ? ";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%" + keyWord + "%");
		super.pstmt.setLong(2, (currentPage - 1) * lineSize);
		super.pstmt.setLong(3, lineSize);
		super.pstmt.setString(4, eid);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Schedule vo = new Schedule();
			vo.setSid(rs.getLong(1));
			vo.setSeid(rs.getString(2));
			vo.setAeid(rs.getString(3));
			vo.setIid(rs.getLong(4));
			vo.setTitle(rs.getString(5));
			vo.setSdate(rs.getDate(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAudit(rs.getInt(8));
			vo.setNote(rs.getString(9));
			vo.setAuddate(rs.getDate(10));
			vo.setAnote(rs.getString(11));
			vo.setEcount(rs.getInt(12));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Long getAllCountById(String eid) throws SQLException {
		String sql = "select count(*) from schedule where seid = ?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, eid);
		ResultSet rs = super.pstmt.executeQuery();
		if(rs.next()){
			return rs.getLong(1);
		}
		return 0L;
	}

	@Override
	public Long getSplitCountById(String column, String keyWord, String eid) throws SQLException {
		String sql = "select count(*) from schedule where ? like ? and seid = ?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, column);
		super.pstmt.setString(2, "%"+ keyWord +"%");
		super.pstmt.setString(3, eid);
		ResultSet rs = super.pstmt.executeQuery();
		if(rs.next()){
			return rs.getLong(1);
		}
		return 0L;
	}

	@Override
	public boolean doRemoveBySidAndSeid(long sid, String seid) throws SQLException {
		String sql = "delete from schedule where sid = ? and seid = ?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, sid);
		super.pstmt.setString(2, seid);
		return super.pstmt.executeUpdate() > 0;
	}
	
	@Override
	
	public boolean doEditAuditBySidAndSeid(long sid, String seid) throws SQLException {
		String sql = "update schedule set audit = 3 where sid =? and seid =?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, sid);
		super.pstmt.setString(2, seid);
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public Schedule findBySidAndSeid(long sid, String eid) throws SQLException {
		String sql = "select sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount from schedule where sid = ? and seid = ?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, sid);
		super.pstmt.setString(2, eid);
		ResultSet rs = super.pstmt.executeQuery();
		Schedule vo = null;
		while(rs.next()) {
			vo = new Schedule();
			vo.setSid(rs.getLong(1));
			vo.setSeid(rs.getString(2));
			vo.setAeid(rs.getString(3));
			vo.setIid(rs.getLong(4));
			vo.setTitle(rs.getString(5));
			vo.setSdate(rs.getDate(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAudit(rs.getInt(8));
			vo.setNote(rs.getString(9));
			vo.setAuddate(rs.getDate(10));
			vo.setAnote(rs.getString(11));
			vo.setEcount(rs.getInt(12));
		}
		return vo;
	}

	@Override
	public List<Schedule> findAllByAuditAndSid(Set<Long> sids, Set<Integer> ids) throws SQLException {
		List<Schedule> all = new ArrayList<>();
		StringBuffer sb = new StringBuffer("select sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount from schedule where 1=1 ") ;
		if(sids != null && sids.size() != 0) {
			Iterator<Long> iterSid = sids.iterator() ;
			sb.append(" and sid in (");
			while(iterSid.hasNext()) {
				sb.append(iterSid.next()).append(",") ;
			}
			sb.delete(sb.length()-1,sb.length()).append(")") ;
		}
		if(ids != null && ids.size() != 0) {
			Iterator<Integer> iterIds = ids.iterator() ;
			sb.append(" and audit in (");
			while(iterIds.hasNext()) {
				sb.append(iterIds.next()).append(",") ;
			}
			sb.delete(sb.length()-1, sb.length()).append(")") ;
		}
		sb.append(" order by audit asc") ;
		super.pstmt = super.conn.prepareStatement(sb.toString());
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Schedule vo = new Schedule();
			vo.setSid(rs.getLong(1));
			vo.setSeid(rs.getString(2));
			vo.setAeid(rs.getString(3));
			vo.setIid(rs.getLong(4));
			vo.setTitle(rs.getString(5));
			vo.setSdate(rs.getDate(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAudit(rs.getInt(8));
			vo.setNote(rs.getString(9));
			vo.setAuddate(rs.getDate(10));
			vo.setAnote(rs.getString(11));
			vo.setEcount(rs.getInt(12));
			all.add(vo);
		}
		return all ;
	}

	@Override
	public List<Schedule> findAllByAuditAndSid(Long currentPage, Integer lineSize, Set<Long> sids, Set<Integer> ids)
			throws SQLException {
		List<Schedule> all = new ArrayList<>();
		StringBuffer sb = new StringBuffer("select sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount from schedule where 1=1 ") ;
		if(sids != null && sids.size() != 0) {
			Iterator<Long> iterSid = sids.iterator() ;
			sb.append(" and sid in (");
			while(iterSid.hasNext()) {
				sb.append(iterSid.next()).append(",") ;
			}
			sb.delete(sb.length()-1,sb.length()).append(")") ;
		}
		if(ids != null && ids.size() != 0) {
			Iterator<Integer> iterIds = ids.iterator() ;
			sb.append(" and audit in (");
			while(iterIds.hasNext()) {
				sb.append(iterIds.next()).append(",") ;
			}
			sb.delete(sb.length()-1, sb.length()).append(")") ;
		}
		sb.append(" order by audit asc") ;
		sb.append(" limit ?,?") ;
		super.pstmt = super.conn.prepareStatement(sb.toString());
		super.pstmt.setLong(1, (currentPage - 1) * lineSize);
		super.pstmt.setLong(2, lineSize);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Schedule vo = new Schedule();
			vo.setSid(rs.getLong(1));
			vo.setSeid(rs.getString(2));
			vo.setAeid(rs.getString(3));
			vo.setIid(rs.getLong(4));
			vo.setTitle(rs.getString(5));
			vo.setSdate(rs.getDate(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAudit(rs.getInt(8));
			vo.setNote(rs.getString(9));
			vo.setAuddate(rs.getDate(10));
			vo.setAnote(rs.getString(11));
			vo.setEcount(rs.getInt(12));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Schedule> findSplitByAuditAndSid(String column, String keyWord, Long currentPage, Integer lineSize,
			Set<Long> sids, Set<Integer> ids) throws SQLException {
		List<Schedule> all = new ArrayList<>();
		StringBuffer sb = new StringBuffer("select sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount from schedule WHERE " + column + " LIKE ? ") ;
		if(sids != null && sids.size() != 0) {
			Iterator<Long> iterSid = sids.iterator() ;
			sb.append(" and sid in (");
			while(iterSid.hasNext()) {
				sb.append(iterSid.next()).append(",") ;
			}
			sb.delete(sb.length()-1,sb.length()).append(")") ;
		}
		if(ids != null && ids.size() != 0) {
			Iterator<Integer> iterIds = ids.iterator() ;
			sb.append(" and audit in (");
			while(iterIds.hasNext()) {
				sb.append(iterIds.next()).append(",") ;
			}
			sb.delete(sb.length()-1, sb.length()).append(")") ;
		}
		sb.append(" order by audit asc") ;
		sb.append(" limit ?,?") ;
		super.pstmt = super.conn.prepareStatement(sb.toString());
		super.pstmt.setString(1, "%" + keyWord + "%");
		super.pstmt.setLong(2, (currentPage - 1) * lineSize);
		super.pstmt.setLong(3, lineSize);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Schedule vo = new Schedule();
			vo.setSid(rs.getLong(1));
			vo.setSeid(rs.getString(2));
			vo.setAeid(rs.getString(3));
			vo.setIid(rs.getLong(4));
			vo.setTitle(rs.getString(5));
			vo.setSdate(rs.getDate(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAudit(rs.getInt(8));
			vo.setNote(rs.getString(9));
			vo.setAuddate(rs.getDate(10));
			vo.setAnote(rs.getString(11));
			vo.setEcount(rs.getInt(12));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Long getAllCount(Set<Long> sids, Set<Integer> ids) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) from schedule where 1=1 ") ;
		if(sids != null && sids.size() != 0) {
			Iterator<Long> iterSid = sids.iterator() ;
			sb.append(" and sid in (");
			while(iterSid.hasNext()) {
				sb.append(iterSid.next()).append(",") ;
			}
			sb.delete(sb.length()-1,sb.length()).append(")") ;
		}
		if(ids != null && ids.size() != 0) {
			Iterator<Integer> iterIds = ids.iterator() ;
			sb.append(" and audit in (");
			while(iterIds.hasNext()) {
				sb.append(iterIds.next()).append(",") ;
			}
			sb.delete(sb.length()-1, sb.length()).append(")") ;
		}
		sb.append(" order by audit asc") ;
		super.pstmt = super.conn.prepareStatement(sb.toString());
		ResultSet rs = super.pstmt.executeQuery();
		if(rs.next()){
			return rs.getLong(1);
		}
		return 0L;
	}

	@Override
	public Long getSplitCount(String column, String keyWord, Set<Long> sids, Set<Integer> ids) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) from schedule where ? like ? ") ;
		if(sids != null && sids.size() != 0) {
			Iterator<Long> iterSid = sids.iterator() ;
			sb.append(" and sid in (");
			while(iterSid.hasNext()) {
				sb.append(iterSid.next()).append(",") ;
			}
			sb.delete(sb.length()-1,sb.length()).append(")") ;
		}
		if(ids != null && ids.size() != 0) {
			Iterator<Integer> iterIds = ids.iterator() ;
			sb.append(" and audit in (");
			while(iterIds.hasNext()) {
				sb.append(iterIds.next()).append(",") ;
			}
			sb.delete(sb.length()-1, sb.length()).append(")") ;
		}
		sb.append(" order by audit asc") ;
		super.pstmt = super.conn.prepareStatement(sb.toString());
		super.pstmt.setString(1, column);
		super.pstmt.setString(2, "%"+ keyWord +"%");
		ResultSet rs = super.pstmt.executeQuery();
		if(rs.next()){
			return rs.getLong(1);
		}
		return 0L;
	}
	@Override
	public boolean doEditScheduleBySidAndSeid(Schedule vo) throws SQLException {
		String sql = "update schedule set sid = ?,seid = ?,iid = ?,title = ?,sdate = ?,subdate = ?,note = ? where sid =?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(8, vo.getSid());
		super.pstmt.setLong(1, vo.getSid());
		super.pstmt.setString(2, vo.getSeid());
		//super.pstmt.setString(3, vo.getAeid());
		super.pstmt.setLong(3, vo.getIid());
		super.pstmt.setString(4, vo.getTitle());
		super.pstmt.setDate(5, new java.sql.Date(vo.getSdate().getTime()));
		super.pstmt.setDate(6, new java.sql.Date(vo.getSubdate().getTime()));
		//super.pstmt.setInt(8, vo.getAudit());
		super.pstmt.setString(7, vo.getNote());
		//super.pstmt.setDate(9, new java.sql.Date(vo.getSdate().getTime()));
		//super.pstmt.setString(10, vo.getAnote());
		//super.pstmt.setInt(11, vo.getEcount());
		
		return super.pstmt.executeUpdate() >0;
	}

	@Override
	public boolean editAuditBySid(Long sid,Integer audit) throws Exception {
		String sql = "update schedule set audit = ? where sid =?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, audit);
		super.pstmt.setLong(2, sid);
		return super.pstmt.executeUpdate() > 0;
	}


	@Override
	public boolean increaseEcount(Long sid)throws SQLException{
		String sql = "update schedule set ecount = ecount+1 where sid = ?";
		super.conn = DatabaseConnection.getConnection();
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, sid);
		return super.pstmt.executeUpdate()>0;
		// TODO Auto-generated method stub
		
	}
	public int getEmpCount(Long sid)throws SQLException{
		String sql = "select count(*) from schedule_emp where sid=?";
		Connection conn = null ;
		conn = DatabaseConnection.getConnection();
		super.pstmt = conn.prepareStatement(sql);
		super.pstmt.setLong(1, sid);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}
	public boolean deleteByEid(String eid)throws SQLException {
		String sql = "delete from schedule_emp where eid =?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, eid);
		
		return super.pstmt.executeUpdate() > 0;
	}
	public void decreaseEcount(Long sid)throws SQLException{
		String sql = "update schedule set ecount = ecount-1 where sid = ?";
		super.conn = DatabaseConnection.getConnection();
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, sid);
		super.pstmt.executeUpdate();
	}

}
