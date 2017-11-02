package cn.mldn.eusplatform.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import cn.mldn.eusplatform.dao.ILevelDAO;
import cn.mldn.eusplatform.vo.Level;
import cn.mldn.util.dao.abs.AbstractDAO;

public class LevelDAOImpl extends AbstractDAO implements ILevelDAO {

	@Override
	public boolean doCreate(Level vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doEdit(Level vo) throws SQLException {
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
	public Level findById(Long id) throws SQLException {
		Level level = null ;
		String sql = "select lid,title,losal,hisal from level where lid = ? " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1, id);
		ResultSet rs = super.pstmt.executeQuery() ;
		if(rs.next()) {
			level = new Level() ;
			level.setLid(rs.getLong(1));
			level.setTitle(rs.getString(2));
			level.setLosal(rs.getDouble(3));
			level.setHisal(rs.getDouble(4));
		}
		return level;
	}

	@Override
	public List<Level> findAll() throws SQLException {
		// TODO Auto-generated method stub
		List<Level> all = new ArrayList<Level>();
		String sql = "SELECT lid,title FROM level";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			Level vo = new Level();
			vo.setLid(rs.getLong(1));
			vo.setTitle(rs.getString(2));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Level> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Level> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
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
	public Map<Long, String> findAllLevel() throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT lid,title FROM level";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		Map<Long, String> map = new ConcurrentHashMap<Long, String>();
		while(rs.next()) {
			map.put(rs.getLong(1), rs.getString(2));
		}
		return map;
	}

}
