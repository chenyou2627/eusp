package cn.mldn.eusplatform.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.mldn.eusplatform.dao.IScheduleEmpDAO;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.eusplatform.vo.ScheduleEmp;
import cn.mldn.util.dao.abs.AbstractDAO;

public class ScheduleEmpDAOImpl extends AbstractDAO implements IScheduleEmpDAO {

	@Override
	public Set<Long> findSidByEid(String eid) throws Exception {
		Set<Long> sids = new HashSet<Long>() ;
		String sql = "select sid from schedule_emp where eid = ? " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, eid);
		ResultSet rs = super.pstmt.executeQuery() ;
		while(rs.next()) {
			sids.add(rs.getLong(1)) ;
		}
		return sids ;
	}
	
	@Override
	public boolean doCreate(ScheduleEmp vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doEdit(ScheduleEmp vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
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
	public ScheduleEmp findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScheduleEmp> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScheduleEmp> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScheduleEmp> findSplit(String column, String keyWord, Long currentPage, Integer lineSize)
			throws Exception {
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
	public List<Emp> findEidByEmp(Long sid) throws Exception {
		List<Emp> all = new ArrayList<Emp>() ;
		String sql = "select eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,locked from emp where eid in( select eid from schedule_emp where sid = ? ) and locked=0" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1, sid);
		ResultSet rs = super.pstmt.executeQuery() ;
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
	public List<ScheduleEmp> findScheduleEmp() throws Exception {
		// TODO Auto-generated method stub
		List<ScheduleEmp> all = new ArrayList<ScheduleEmp>() ;
		String sql = "SELECT seid,sid,eid FROM schedule_emp";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			ScheduleEmp vo = new ScheduleEmp();
			vo.setSeid(rs.getLong(1));
			vo.setSid(rs.getLong(2));
			vo.setEid(rs.getString(3));
			all.add(vo);
		}
		return all;
	}


	
}
