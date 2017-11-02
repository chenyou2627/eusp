package cn.mldn.eusplatform.dao;

import java.util.List;
import java.util.Set;

import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.eusplatform.vo.ScheduleEmp;
import cn.mldn.util.dao.IBaseDAO;

public interface IScheduleEmpDAO extends IBaseDAO<Long,ScheduleEmp> {
	
	/**
	 * 根据eid取得sid，以Set集合保存
	 * @param eid 雇员编号
	 * @return 全部的调度编号以Set集合保存
	 * @throws Exception JDBC
	 */
	public Set<Long> findSidByEid(String eid) throws Exception ;
	/**
	 * 根据sid取得所有参与此调度的雇员信息
	 * @param sid 调度表ID
	 * @return List集合
	 * @throws Exception
	 */
	public List<Emp> findEidByEmp(Long sid) throws Exception ;
	
	/**
	 * 取得所有已经调度的人员信息
	 * @return List集合
	 * @throws Exception
	 */
	public List<ScheduleEmp> findScheduleEmp() throws Exception;
	
	//public Map<String,> findScheduleMap() throws Exception;
}
