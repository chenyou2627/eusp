package cn.mldn.eusplatform.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import cn.mldn.eusplatform.vo.Schedule;
import cn.mldn.util.dao.IBaseDAO;

public interface IScheduleDAO extends IBaseDAO<Long, Schedule> {
	
	/**
	 * 获得指定sid 的ecount
	 * @param sid
	 * @return
	 * @throws SQLException
	 */
	public boolean increaseEcount(Long sid)throws SQLException;
	/**
	 * 增加调度的雇员
	 * @param meid 调度申请的ID
	 * @param eid 需要调度的人
	 * @param sid 调度编号
	 * @return 增加成功返回true
	 * @throws SQLException
	 */
	public boolean doCreateEmpIntoSchedule(String eid,Long sid)throws SQLException;
	/**
	 * 修改调度申请
	 * @param vo
	 * @return
	 * @throws SQLException
	 */
	 
	public boolean doEditScheduleBySidAndSeid(Schedule vo)throws SQLException;
	/**
	 *  根据Sid和 seid 查找申请调度；
	 * @param sid
	 * @param eid
	 * @return
	 * @throws SQLException
	 */
	public Schedule findBySidAndSeid(long sid,String eid)throws SQLException;
	
	public List<Schedule> findAllById(String eid)throws SQLException;
	/**
	 * 根据申请人eid 获得所有的调度申请
	 * @param currentPage
	 * @param lineSize
	 * @param eid
	 * @return
	 * @throws SQLException
	 */
	public List<Schedule> findAllById(Long currentPage, Integer lineSize,String eid) throws SQLException;
	/**
	 * 根据申请人eid 查找  分页
	 * @param column
	 * @param keyWord
	 * @param currentPage
	 * @param lineSize
	 * @param eid
	 * @return
	 * @throws SQLException
	 */
	public List<Schedule> findSplitById(String column, String keyWord, Long currentPage, Integer lineSize,String eid)
			throws SQLException;
	/**
	 * 根据申请人eid 获得数据量
	 * @param eid
	 * @return
	 * @throws SQLException
	 */
	public Long getAllCountById(String eid) throws SQLException ;
	/**
	 * 根据申请人eid 获得分页数量
	 * @param column
	 * @param keyWord
	 * @param eid
	 * @return
	 * @throws SQLException
	 */
	public Long getSplitCountById(String column, String keyWord,String eid) throws SQLException;
	/**
	 * 根据Sid和 seid 删除 调度申请
	 * @param sid 调度申请编号
	 * @param seid 调度申请雇员编号
	 * @return 修改成功 返回true
	 * @throws SQLException
	 */
	public boolean doRemoveBySidAndSeid(long sid,String seid) throws SQLException;
	/**
	 * 进行调度申请的提交
	 * 状态由 0 （未提交）改变成3（待审核）
	 */
	public boolean doEditAuditBySidAndSeid(long sid,String seid) throws SQLException;
	


	public List<Schedule> findAllByAuditAndSid(Set<Long> sid,Set<Integer> ids) throws SQLException ;
	/**
	 * 根据审核标记和sid列出所有的调度安排并分页
	 * @param currentPage 当前页
	 * @param lineSize 每页的行数
	 * @param sid 以set集合保存的sid表示的是该雇员的调度安排
	 * @param ids 审核标记id，以set集合保存
	 * @return 调度安排集合
	 * @throws SQLException SQL
	 */
	public List<Schedule> findAllByAuditAndSid(Long currentPage, Integer lineSize,Set<Long> sid,Set<Integer> ids) throws SQLException ;
	/**
	 * 根据审核标记和sid列出所有的调度安排并模糊分页
	 * @param column 模糊查询的列
	 * @param keyWord 模糊查询的关键字
	 * @param currentPage 当前页
	 * @param lineSize 每页的行数
	 * @param sid 以set集合保存的sid表示的是该雇员的调度安排
	 * @param ids 审核标记id，以set集合保存
	 * @return 调度安排集合
	 * @throws SQLException SQL
	 */
	public List<Schedule> findSplitByAuditAndSid(String column, String keyWord, Long currentPage, Integer lineSize,Set<Long> sid,Set<Integer> ids) throws SQLException ;
	/**
	 * 根据审核标记和sid取得该标记下的所有记录数
	 * @param sid 以set集合保存的sid表示的是该雇员的调度安排
	 * @param ids 审核标记id，以set集合保存
	 * @return 记录数
	 * @throws Exception SQL
	 */
	public Long getAllCount(Set<Long> sid,Set<Integer> ids) throws Exception ;
	/**
	 * 根据审核标记、sid和模糊查询取得所有记录数
	 * @param column 模糊查询的行
	 * @param keyWord 模糊查询的关键字
	 * @param sid 以set集合保存的sid表示的是该雇员的调度安排
	 * @param ids 审核标记id，以set集合保存
	 * @return 记录数
	 * @throws Exception SQL
	 */
	public Long getSplitCount(String column,String keyWord,Set<Long> sid,Set<Integer> ids) throws Exception ;
	/**
	 * 根据编号修改状态，将本来是未报告的改为已完成
	 * 状态由 1 （任务进行中）改变成4（待提交报告）//rll  ReportServiceBackImpl   add()方法
	 * 状态由 4 （待提交报告）改变成5（任务已完成）//hh SchedulePersonalTaskAction prepare()方法
	 * @param sid 编号状态
	 * @return 修改成功返回true,否则返回false
	 * @throws Exception SQL
	 */
	public boolean editAuditBySid(Long sid,Integer audit) throws Exception ;
}
