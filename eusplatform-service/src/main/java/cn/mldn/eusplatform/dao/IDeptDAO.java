package cn.mldn.eusplatform.dao;

import java.sql.SQLException;
import java.util.Map;

import cn.mldn.eusplatform.vo.Dept;
import cn.mldn.util.dao.IBaseDAO;

public interface IDeptDAO extends IBaseDAO<Long, Dept> {
	/**
	 * 根据部门领导编号取得部门名称
	 * @param eid 部门领导的编号
	 * @return 部门名称
	 * @throws SQLException SQL
	 */
	public String findDnameByEid(String eid) throws SQLException ;
	/**
	 * 根据部门名称找出部门信息
	 * @param dname 部门名称
	 * @return 如果找到返回部门信息
	 * @throws SQLException SQL
	 */
	public Dept findByDname(String dname) throws SQLException ;
	/**
	 * 根据部门名称查找不含自己的部门名称
	 * @param vo 部门信息
	 * @return 查找到返回true,否则返回false
	 * @throws SQLException SQL 
	 */
	public boolean findDnameByOther(Dept vo) throws SQLException ;

	/**
	 * 查找部门编号与部门名称保存到map集合
	 * @return map
	 * @throws SQLException
	 */
	public Map<Long,String> findAllMap() throws SQLException;
	
	/**
	 * 根据部门编号更新部门当前人数
	 * @param did
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateNumBydid(Long did) throws SQLException;
	
	public boolean doUpdateEidByDid(Long did,String eid) throws SQLException;
	
	public boolean subNumBydid(Long did) throws SQLException;
	
	public boolean addNumBydid(Long did) throws SQLException;
	
}
