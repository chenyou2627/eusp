package cn.mldn.eusplatform.dao;

import java.sql.SQLException;
import java.util.Map;

import cn.mldn.eusplatform.vo.Item;
import cn.mldn.util.dao.IBaseDAO;

public interface IItemDAO extends IBaseDAO<Integer, Item> {
	/**
	 * 查找任务类型编号与类型名称保存到map集合
	 * @return map
	 * @throws SQLException
	 */
	public Map<Long,String> findAllMap() throws SQLException;
}
