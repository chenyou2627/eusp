package cn.mldn.eusplatform.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.eusplatform.vo.Report;
import cn.mldn.util.dao.IBaseDAO;

public interface IReportDAO extends IBaseDAO<Long,Report> {
	public boolean doReportBatch(Report vo,Set<String> eids) throws SQLException;
	/**
	 * 查询全部表数据以Map集合返回 暂时不可用
	 * 	key = 任务报告表中SID   value = 报告内容
	 * @param eid 用户ID
	 * @return Map集合
	 * @throws SQLException
	 */
	public Map<Long,String> findAllMap(String eid) throws SQLException;
	/**
	 * 根据sid获取全部的报告表内容
	 * @param sid 
	 * @return map
	 * key = eid value = subdate
	 * @throws SQLException
	 */
	public Map<String,String> findAllSubdate(Long sid) throws SQLException;
	
	/**
	 * 根据sid获取全部的报告表内容
	 * @param sid 
	 * @return map
	 * key = eid value = note
	 * @throws SQLException
	 */
	public Map<String,String> findAllNote(Long sid) throws SQLException;
	/**
	 * 根据用户ID 和 审核表ID 查找具体的报告详情
	 * @param sid 审核表ID
	 * @param eid 用户ID
	 * @return
	 * @throws SQLException
	 */
	public List<Report> findAllBySid(Long sid,String eid) throws SQLException;
}
