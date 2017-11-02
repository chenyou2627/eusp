package cn.mldn.eusplatform.service.back;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.eusplatform.vo.Schedule;


public interface IScheduleServiceBack {
	public boolean addEmpIntoSchedule(String eid,Long sid)throws SQLException;
	/**
	 * 根据调度表编号追加审核信息
	 * @param vo 要追加的信息
	 * @return 
	 * @throws SQLException
	 */
	public boolean editApplySchedule(Schedule vo) throws SQLException;
	/**
	 * 根据给定的标记修改审核表标记
	 * @param sid 审核表ID 
	 * @param audit 标记
	 * @return boolean值
	 * @throws Exception
	 */
	public boolean editAudit(Long sid,int audit) throws Exception;
	public boolean editSchedule(Schedule vo)throws SQLException;
	/**
	 * 根据调度表编号，查询具体信息
	 * @param sid 调度表编号
	 * @return Map集合
	 * key = Schedule valu = Schedule对象
	 * key = allEmp valu = 所有的雇员
	 * @throws SQLException
	 */
	public Map<String,Object> get(Long sid) throws Exception;
	/**
	 * 进行调度申请表的列表显示，带分页
	 * @return Map集合
	 * @throws SQLException JDBC
	 */
	public Map<String,Object> list(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception;

	public Map<String,Object> listById(String column, String keyWord, Long currentPage, Integer lineSize,String eid) throws Exception;

	/**
	 * 增加调度申请
	 * @param vo vo
	 * @return 增加成功返回true
	 * @throws SQLException JDBC
 	 */

	public boolean add(Schedule vo) throws SQLException;

	/**
	 * 根据用户id 和 申请调度id 删除 调度申请；
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteBySidAndSeid(long sid,String seid)throws SQLException;
	/**
	 * 根据用户id 和 申请调度id 删除 调度申请
	 * @param sid
	 * @param seid
	 * @return
	 * @throws SQLException
	 */
	public boolean editAuditBySidAndSeid(long sid,String seid)throws SQLException;
	/**
	 * 根据用户id 和 申请调度id 获得 调度申请列表 
	 * @param sid
	 * @param seid
	 * @return
	 * @throws SQLException
	 */
	public Schedule getBySidAndSeid(long sid,String seid)throws SQLException;
	

	/**
	 * 根据审核标记模糊分页查询出调度表集合以及数量
	 * @param column 模糊查询列
	 * @param keyWord 模糊查询关键字
	 * @param currentPage 当前页
	 * @param lineSize 每页的行数
	 * @param ids 审核标记集合
	 * @return 调度申请表集合以及数量
	 * key="allSchedule",value表示所有的调度表集合
	 * key="allRecorders",value表示调度表的数量
	 * @throws Exception JDBC
	 */
	public Map<String,Object> listByAudit(String column, String keyWord, Long currentPage, Integer lineSize, Set<Integer> ids) throws Exception ;
	/**
	 * 根据SID  查询出所有的雇员信息，带分页
	 * @param sid 审核表ID
	 * @return list集合
	 * @throws Exception
	 */
	public Map<String,Object> listBySid(long sid) throws Exception ;
	/**
	 * 根据审核标记模糊分页查询出自己的调度表集合以及数量
	 * @param column 模糊查询列
	 * @param keyWord 模糊查询关键字
	 * @param currentPage 当前页
	 * @param lineSize 每页的行数
	 * @param ids 审核标记集合
	 * @return 调度申请表集合以及数量
	 * key="allSchedule",value表示所有的调度表集合
	 * key="allRecorders",value表示调度表的数量
	 * @throws Exception JDBC
	 */
	public Map<String,Object> listByAuditAndSid(String column, String keyWord, Long currentPage, Integer lineSize, Set<Integer> ids) throws Exception ;
}
