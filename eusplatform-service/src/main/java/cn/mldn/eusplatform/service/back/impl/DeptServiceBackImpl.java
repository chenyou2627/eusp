package cn.mldn.eusplatform.service.back.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.mldn.eusplatform.dao.IDeptDAO;
import cn.mldn.eusplatform.dao.IEmpDAO;
import cn.mldn.eusplatform.dao.ILevelDAO;
import cn.mldn.eusplatform.service.back.IDeptServiceBack;
import cn.mldn.eusplatform.vo.Dept;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.eusplatform.vo.Level;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.service.abs.AbstractService;

public class DeptServiceBackImpl extends AbstractService implements IDeptServiceBack {

	@Override
	public Map<String, Object> list() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		List<Dept> deptList = deptDAO.findAll() ;
		Map<String,String> empMap = new HashMap<String,String>() ;
		Iterator<Dept> iter = deptList.iterator() ;
		while(iter.hasNext()) {
			Dept dept = iter.next() ;
			if(dept.getEid() != null) {
				Emp emp = empDAO.findById(dept.getEid()) ;
				empMap.put(emp.getEid(), emp.getEname()) ;
			}
		}
		map.put("allDept", deptList) ;
		map.put("allEmpByDeptEid", empMap) ;
		return map ;
	}

	@Override
	public boolean edit(Dept vo) throws Exception {
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		if(deptDAO.findDnameByOther(vo) == false) {
			return deptDAO.doEdit(vo) ;
		}
		return false;
	}

	@Override
	public Map<String, Object> getEmpAndDeptAndLevelInfo(String eid) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		ILevelDAO levelDAO = Factory.getDAOInstance("level.dao") ;
		Emp emp = empDAO.findById(eid) ;
		String dname = deptDAO.findById(emp.getDid()).getDname() ;
		Level level = levelDAO.findById(emp.getLid()) ;
		map.put("emp", emp) ;
		map.put("dname",dname) ;
		map.put("levelTitle",level.getTitle()) ;
		return map;
	}

	@Override
	public boolean add(Dept dept) throws Exception {
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		if(deptDAO.findByDname(dept.getDname()) == null) {
			return deptDAO.doCreate(dept) ;
		}
		return false;
	}

}
