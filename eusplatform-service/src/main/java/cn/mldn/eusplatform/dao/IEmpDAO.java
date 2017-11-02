package cn.mldn.eusplatform.dao;

import java.sql.SQLException;
import java.util.List;
import cn.mldn.eusplatform.vo.Dept;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.util.dao.IBaseDAO;

public interface IEmpDAO extends IBaseDAO<String, Emp> {

	public Emp findByEidIsExist(String eid) throws SQLException;
	
	public List<Dept> findAllDept() throws SQLException;
	
	public boolean doUpdateByManager(Long did,Long lid) throws SQLException;
	
	public List<Emp> findAllByDid(Long lid,Long did,Long currentPage,Integer lineSize) throws Exception ;
	
	public Long getAllCount(Long lid,Long did) throws SQLException ;
	
}
