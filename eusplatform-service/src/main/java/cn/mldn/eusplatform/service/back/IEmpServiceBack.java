package cn.mldn.eusplatform.service.back;

import java.util.List;
import java.util.Map;

import cn.mldn.eusplatform.vo.Dept;
import cn.mldn.eusplatform.vo.Emp;

public interface IEmpServiceBack {
	/**
	 * 实现用户的登录处理操作，该操作要执行如下的几步：
	 * 1、调用IMemberDAO.findById()方法根据用户名查找用户的信息，随后进行密码比对，如果密码正确则表示登录成功；
	 * 2、当登录成功之后应该根据用户编号获得用户对应的角色与权限数据信息；
	 * @param emp 包含有用户登录名（mid）与密码（password）两个重要的信息
	 * @return 返回的内容包含有如下数据：<br>
	 * 1、key = emp、value = 登录成功后的用户信息，如果没有返回雇员对象则表示登录失败；
	 * 2、key = allRoles、value = 用户对应的所有角色，Set集合；
	 * 3、key = allActions、value = 用户对应的所有权限，Set集合；
	 * @throws Exception 抛出状态的处理异常
	 */
	public Map<String, Object> login(Emp emp) throws Exception;
	
	public boolean add(Emp vo) throws Exception;
	
	public Map<String,Object> addPre() throws Exception;
	
	public boolean checkEid(String eid) throws Exception;

	
	public Map<String,Object> list(String column, String keyWord, 
			Long currentPage, Integer lineSize) throws Exception;
	
	public Map<String, Object> editPre(String eid) throws Exception;
	
	public boolean edit(Emp vo) throws Exception;
	
	public Map<String,Object> listSchedule(Long did,String column, String keyWord, 
			Long currentPage, Integer lineSize) throws Exception;
	
	public List<Dept> list()throws Exception;
	

	public String checkLevSaly(double salary,Long lid) throws Exception;
	

	
}
