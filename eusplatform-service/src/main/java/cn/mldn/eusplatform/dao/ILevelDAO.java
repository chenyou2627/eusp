package cn.mldn.eusplatform.dao;

import java.sql.SQLException;
import java.util.Map;

import cn.mldn.eusplatform.vo.Level;
import cn.mldn.util.dao.IBaseDAO;

public interface ILevelDAO extends IBaseDAO<Long,Level>{

	public Map<Long,String> findAllLevel() throws SQLException;
}
