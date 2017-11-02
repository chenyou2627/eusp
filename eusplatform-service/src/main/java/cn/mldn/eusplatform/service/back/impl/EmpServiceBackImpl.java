package cn.mldn.eusplatform.service.back.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.mldn.eusplatform.dao.IActionDAO;
import cn.mldn.eusplatform.dao.IDeptDAO;
import cn.mldn.eusplatform.dao.IEmpDAO;
import cn.mldn.eusplatform.dao.ILevelDAO;
import cn.mldn.eusplatform.dao.IRoleDAO;
import cn.mldn.eusplatform.dao.IScheduleEmpDAO;
import cn.mldn.eusplatform.service.back.IEmpServiceBack;
import cn.mldn.eusplatform.vo.Dept;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.eusplatform.vo.Level;
import cn.mldn.util.CommonConstantUtil;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.util.web.ServletObjectUtil;

public class EmpServiceBackImpl extends AbstractService implements IEmpServiceBack {

	@Override
	public Map<String, Object> login(Emp emp) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>() ;
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		Emp selectEmp = empDAO.findById(emp.getEid()) ;	
		result.put("emp", selectEmp) ;	
		if (selectEmp != null) {
			IRoleDAO roleDAO = Factory.getDAOInstance("role.dao") ;	
			IActionDAO actionDAO = Factory.getDAOInstance("action.dao") ;	
			result.put("allRoles", roleDAO.findAllByDept(selectEmp.getDid())) ;
			result.put("allActions", actionDAO.findAllByDept(selectEmp.getDid())) ;
		}
		return result ;
	}

	@Override
	public boolean add(Emp vo) throws Exception {
		
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		ILevelDAO levelDAO = Factory.getDAOInstance("level.dao") ;
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		String ineid = (String) ServletObjectUtil.getRequest().getSession().getAttribute("eid");
		vo.setIneid(ineid);
		vo.setHiredate(new Date());
		Dept dept = deptDAO.findById(vo.getDid());
		Level level= levelDAO.findById(vo.getLid());
		if(empDAO.findById(ineid).getDid() == 2) {
			if(level !=null) {
				if(level.getLosal()<vo.getSalary() && vo.getSalary()<level.getHisal()) {
					if(level.getLid() == 2) {
						if(empDAO.doUpdateByManager(vo.getDid(), 2l)) {
							if(dept.getCurrnum() <= dept.getMaxnum()) {
								if(empDAO.doCreate(vo)) {
									if(deptDAO.doUpdateNumBydid(vo.getDid())) {
										return deptDAO.doUpdateEidByDid(vo.getDid(), vo.getEid());
									}
									return false;
								}
							}
							return false;
						}
						return false;
					}else {
						if(dept.getCurrnum() <= dept.getMaxnum()) {
							if(empDAO.doCreate(vo)) {
								return deptDAO.doUpdateNumBydid(vo.getDid());
							}
						}
						return false;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean checkEid(String eid) throws Exception {
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		return empDAO.findByEidIsExist(eid) == null;
	}

	@Override
	public Map<String, Object> addPre() throws Exception {
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		ILevelDAO levelDAO = Factory.getDAOInstance("level.dao") ;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("allDepts", deptDAO.findAll());
		map.put("allLevel", levelDAO.findAll());
		return map;
	}

	@Override
	public Map<String, Object> list(String column, String keyWord, Long currentPage, Integer lineSize)
			throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		ILevelDAO levelDAO = Factory.getDAOInstance("level.dao") ;
		if(super.isLikeSearch(column) || super.isLikeSearch(keyWord)) {
			map.put("allEmps", empDAO.findSplit(column, keyWord, currentPage, lineSize));
			map.put("allRecorders", empDAO.getSplitCount(column, keyWord));
		}else {
			map.put("allEmps", empDAO.findAll(currentPage, lineSize));
			
			map.put("allRecorders", empDAO.getAllCount());
		}
		map.put("allDepts", deptDAO.findAllMap());
		map.put("allLevels", levelDAO.findAllLevel());
		return map;
	}

	@Override
	public Map<String, Object> editPre(String eid) throws Exception {
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		ILevelDAO levelDAO = Factory.getDAOInstance("level.dao") ;
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("allDepts", deptDAO.findAll());
		map.put("allLevel", levelDAO.findAll());
		map.put("emp", empDAO.findById(eid));
		map.put("allDeptMap", deptDAO.findAllMap());
		map.put("allLevelMap", levelDAO.findAllLevel());
		return map;
	}

	@Override
	public boolean edit(Emp vo) throws Exception {
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		ILevelDAO levelDAO = Factory.getDAOInstance("level.dao") ;
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		String ineid = (String) ServletObjectUtil.getRequest().getSession().getAttribute("eid");
		Emp emp = empDAO.findById(ineid);//
		Emp ems = empDAO.findById(vo.getEid());
		if(ineid != emp.getIneid()) {
			vo.setIneid(ineid);
		}
		Dept dept = deptDAO.findById(vo.getDid());
		vo.setHiredate(new Date());
		Level level= levelDAO.findById(vo.getLid());
		if(empDAO.findById(ineid).getDid() == 2) {
			if(level !=null) {
				if(level.getLosal()<vo.getSalary() && vo.getSalary()<level.getHisal()) {
					if(level.getLid() == 2) {
						if(ems.getDid() == vo.getDid()) {
							if(ems.getLid() == vo.getLid()) {
								return empDAO.doEdit(vo);
							}else {
								if(deptDAO.subNumBydid(ems.getDid())) {
									if(deptDAO.addNumBydid(vo.getDid())) {
										return empDAO.doEdit(vo);
									}
								}
							}
						}else {
							if(ems.getLid() == vo.getLid()) {
									if(deptDAO.subNumBydid(ems.getDid())) {
										if(deptDAO.addNumBydid(vo.getDid())) {
											return empDAO.doEdit(vo);
										}
									}
							  }else {
								  if(deptDAO.subNumBydid(ems.getDid())) {
										if(deptDAO.addNumBydid(vo.getDid())) {
											return empDAO.doEdit(vo);
										}
									}
							  }
						}
					}else {
						if(dept.getCurrnum() <= dept.getMaxnum()) {
							if(ems.getDid() == vo.getDid()) {
								return empDAO.doEdit(vo);
							}else {
								if(deptDAO.subNumBydid(ems.getDid())) {
									if(deptDAO.addNumBydid(vo.getDid())) {
										return empDAO.doEdit(vo);
									}
								}
							}
						}
						return false;
					}
				}
			}
		}
		return false;
	}

	@Override
	public Map<String, Object> listSchedule(Long did,String column, String keyWord, Long currentPage, Integer lineSize)
			throws Exception {
		String ineid = (String) ServletObjectUtil.getRequest().getSession().getAttribute("eid");
		Map<String,Object> map = new HashMap<String,Object>();
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		ILevelDAO levelDAO = Factory.getDAOInstance("level.dao") ;
		IScheduleEmpDAO scheduleEmpDAO = Factory.getDAOInstance("scheduleemp.dao");
		Emp emp = empDAO.findById(ineid);
		if(super.isLikeSearch(column) || super.isLikeSearch(keyWord)) {
			map.put("allEmps", empDAO.findSplit(column, keyWord, currentPage, lineSize));
			map.put("allRecorders", empDAO.getSplitCount(column, keyWord));
		}else {
			map.put("allEmps", empDAO.findAllByDid(emp.getLid(),did, currentPage, lineSize));
			map.put("allRecorders", empDAO.getAllCount(emp.getLid(),did));
		}
		
		map.put("allDeptMap", deptDAO.findAllMap());
		map.put("allLevelMap", levelDAO.findAllLevel());
		map.put("allDepts", deptDAO.findAll());
		map.put("allSchedules", scheduleEmpDAO.findScheduleEmp());
		return map;
	}

	@Override
	public List<Dept> list() throws Exception {
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		return deptDAO.findAll();
	}

	@Override
	public String checkLevSaly(double salary, Long lid) throws Exception {
		ILevelDAO levelDAO = Factory.getDAOInstance("level.dao") ;
		Level level= levelDAO.findById(lid);
		if(level.getLid() == 0) {
			if(!(level.getLosal()<salary && salary<level.getHisal())) {
				 return CommonConstantUtil.FLAG1_LEVEL_0;
			}
		}else if(level.getLid() == 1) {
			if(!(level.getLosal()<salary && salary<level.getHisal())) {
				return CommonConstantUtil.FLAG1_LEVEL_1;
			}
		}else if(level.getLid() == 2) {
			if(!(level.getLosal()<salary && salary<level.getHisal())) {
				return CommonConstantUtil.FLAG1_LEVEL_2;
			}
		}else if(level.getLid() == 3){
			if(!(level.getLosal()<salary && salary<level.getHisal())) {
				return CommonConstantUtil.FLAG1_LEVEL_3;
			}
		}
		return CommonConstantUtil.FLAG1_LEVEL;
	}

}
