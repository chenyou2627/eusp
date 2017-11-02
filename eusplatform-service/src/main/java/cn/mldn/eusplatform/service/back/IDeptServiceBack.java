package cn.mldn.eusplatform.service.back;

import java.util.Map;

import cn.mldn.eusplatform.vo.Dept;

public interface IDeptServiceBack {
	/**
	 * 取得所有部门信息，并根据部门经理id取得经理姓名
	 * @return 所有部门信息以及经理姓名
	 * key="allDept", value表示所有的部门信息
	 * key="allEmpByDeptEid", value是一个map集合保存的是雇员id和雇员姓名
	 * @throws Exception JDBC
	 */
	public Map<String,Object> list() throws Exception ;
	/**
	 * 修改部门信息
	 * @param vo 要修改的部门信息
	 * @return 修改成功返回true,否则返回false
	 * @throws Exception JDBC
	 */
	public boolean edit(Dept vo) throws Exception ;
	/**
	 * 根据雇员编号取得雇员信息以及部门名称
	 * @param eid 雇员编号
	 * @return 雇员信息以及部门名称
	 * key="emp", value表示雇员信息
	 * key="dname", value表示部门名称
	 * key="levelTitle",value表示该雇员的权限等级名称
	 * @throws Exception JDBC
	 */
	public Map<String,Object> getEmpAndDeptAndLevelInfo(String eid) throws Exception ;
	/**
	 * 实现部门的增加
	 * @param dept 部门信息
	 * @return 增加成功返回true,否则返回false
	 * @throws Exception JDBC
	 */
	public boolean add(Dept dept) throws Exception ;
}
