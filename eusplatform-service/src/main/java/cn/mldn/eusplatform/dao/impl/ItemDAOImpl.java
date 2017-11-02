package cn.mldn.eusplatform.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import cn.mldn.eusplatform.dao.IItemDAO;
import cn.mldn.eusplatform.vo.Item;
import cn.mldn.util.dao.abs.AbstractDAO;

public class ItemDAOImpl extends AbstractDAO implements IItemDAO {

	@Override
	public boolean doCreate(Item vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doEdit(Item vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Item findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> findAll() throws SQLException {
		List<Item> all = new ArrayList<>();
		String sql = "select iid ,title from item";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			Item vo = new Item();
			vo.setIid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Item> findAll(Long currentPage, Integer lineSize) throws Exception {
		return null;
	}

	@Override
	public List<Item> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
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
		String sql = "SELECT iid,title FROM item";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		Map<Long, String> map = new ConcurrentHashMap<Long, String>();
		while(rs.next()) {
			map.put(rs.getLong(1), rs.getString(2));
		}
		return map;
	}

}
