package cn.mldn.eusplatform.web.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.mldn.eusplatform.service.back.IEmpServiceBack;
import cn.mldn.eusplatform.vo.Dept;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.eusplatform.vo.EmpSchudel;
import cn.mldn.util.CommonConstantUtil;
import cn.mldn.util.JsonUtil;
import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.ServletObjectUtil;
import cn.mldn.util.web.SplitPageUtil;

public class EmpBackAction extends AbstractAction {

	public static final String ACTION_TITLE = "雇员" ;
	
	public ModelAndView addPre() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("emp.addPre.page"));
		IEmpServiceBack empService = Factory.getServiceInstance("emp.service.back");
		try {
			Map<String,Object> map = empService.addPre();
			mav.addObjectMap(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	public ModelAndView add(Emp vo) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("forward.page"));
		if(ServletObjectUtil.getParam().isUpload("pic")) {
			String fileName = ServletObjectUtil.getParam().createUploadFileName("pic").get(0);
			vo.setPhoto(fileName);
		}else {
			vo.setPhoto("nophoto.jpg"); 
		}
		IEmpServiceBack empService = Factory.getServiceInstance("emp.service.back");
		try {
			if(empService.add(vo)) {
				super.setUrlAndMsg(mav, "emp.add.action", "vo.add.success",ACTION_TITLE );
				if(ServletObjectUtil.getParam().isUpload("pic")) {
					String filePath = ServletObjectUtil.getRequest().getServletContext().getRealPath("/upload/emp/") + vo.getPhoto() ;
					ServletObjectUtil.getParam().saveUploadFile("pic", filePath);
				}
			}else {
				super.setUrlAndMsg(mav, "emp.add.action", "vo.add.failure",ACTION_TITLE );
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	public void checkEid(String eid) {
		IEmpServiceBack empService = Factory.getServiceInstance("emp.service.back");
		try {
			super.print(empService.checkEid(eid));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("emp.list.page"));
		SplitPageUtil spu = new SplitPageUtil("雇员名称:ename|电话:phone|工资:salary", "emp.list.action");
		IEmpServiceBack empService = Factory.getServiceInstance("emp.service.back");
		try {
			mav.addObjectMap(empService.list(spu.getColumn(), spu.getKeyWord(), 
					spu.getCurrentPage(), spu.getLineSize()));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	public ModelAndView editPre(String eid) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("emp.editPre.page"));
		IEmpServiceBack empService = Factory.getServiceInstance("emp.service.back");
		try {
			Map<String,Object> map = empService.editPre(eid);
			mav.addObjectMap(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	public ModelAndView edit(Emp vo) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("forward.page"));
		String oldpic = ServletObjectUtil.getParam().getParameter("oldpic");
		String eeid = ServletObjectUtil.getParam().getParameter("eeid");
		vo.setPhoto(oldpic); 
		vo.setEid(eeid);
		if (ServletObjectUtil.getParam().isUpload("pic")) {
			if ("nophoto.jpg".equals(oldpic)) {
				String fileName = ServletObjectUtil.getParam().createUploadFileName("pic").get(0) ;
				vo.setPhoto(fileName); 
			}
		} 
		IEmpServiceBack empService = Factory.getServiceInstance("emp.service.back");
		try {
			if (empService.edit(vo)) {
				super.setUrlAndMsg(mav, "emp.list.action", "vo.edit.success",ACTION_TITLE );
				if (ServletObjectUtil.getParam().isUpload("pic")) {
					String filePath = ServletObjectUtil.getApplication().getRealPath("/upload/emp/") + vo.getPhoto() ;
					ServletObjectUtil.getParam().saveUploadFile("pic", filePath);
				}
			} else {
				super.setUrlAndMsg(mav, "emp.list.action", "vo.edit.failure",ACTION_TITLE );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	
	
	@SuppressWarnings("unchecked")
	public void loadSchedule(Long did) {
		IEmpServiceBack empService = Factory.getServiceInstance("emp.service.back");
		try {
			ServletObjectUtil.getRequest().setCharacterEncoding("UTF-8");
			SplitPageUtil spu = new SplitPageUtil("", "emp.load.action");
			Map<String,Object> map = empService.listSchedule(did,spu.getColumn(), spu.getKeyWord(), 1l, 30);
			//Map<String,Object> map = empService.listSchedule(did,null,null, 1l,20);
			List<String> allJSON = new ArrayList<String>();
			List<Emp> allEmp = (List<Emp>) map.get("allEmps");
			Map<Long, String> allDeptMap = (Map<Long, String>) map.get("allDeptMap");
			Map<Long, String> allLevelMap = (Map<Long, String>) map.get("allLevelMap");
			Iterator<Emp> iteremp = allEmp.iterator();
			while(iteremp.hasNext()) {
				Emp emp = iteremp.next();
				EmpSchudel ems = new EmpSchudel();
				ems.setDeptTitle(allDeptMap.get(emp.getDid()));
				ems.setLevelTitle(allLevelMap.get(emp.getLid()));
				ems.setEid(emp.getEid());
				ems.setEname(emp.getEname());
				ems.setPhoto(emp.getPhoto());
				ems.setAllRecorders((Long)map.get("allRecorders"));
				allJSON.add(JsonUtil.toJson(ems));
			}
            ServletObjectUtil.getResponse().setCharacterEncoding("UTF-8");
            ServletObjectUtil.getResponse().setContentType("application/json");
            ServletObjectUtil.getResponse().getWriter().print(allJSON);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkSalary() throws Exception {
		IEmpServiceBack empService = Factory.getServiceInstance("emp.service.back");
		double salary =Double.parseDouble(ServletObjectUtil.getRequest().getParameter("salary"));
		Long lid = Long.parseLong(ServletObjectUtil.getRequest().getParameter("lid"));
		ServletObjectUtil.getRequest().setCharacterEncoding("UTF-8");
		ServletObjectUtil.getResponse().setCharacterEncoding("UTF-8");
		String flag = empService.checkLevSaly(salary, lid);
		if(flag ==CommonConstantUtil.FLAG1_LEVEL_0) {
			ServletObjectUtil.getResponse().getWriter().println(CommonConstantUtil.SALARY_LEVEL_0);
		}else if(flag ==CommonConstantUtil.FLAG1_LEVEL_1) {
			ServletObjectUtil.getResponse().getWriter().println(CommonConstantUtil.SALARY_LEVEL_1);
		}else if(flag ==CommonConstantUtil.FLAG1_LEVEL_2) {
			ServletObjectUtil.getResponse().getWriter().println(CommonConstantUtil.SALARY_LEVEL_2);
		}else if(flag ==CommonConstantUtil.FLAG1_LEVEL_3) {
			ServletObjectUtil.getResponse().getWriter().println(CommonConstantUtil.SALARY_LEVEL_3);
		}else {
			ServletObjectUtil.getResponse().getWriter().println(CommonConstantUtil.FLAG);
		}
	}
	public void loadDept() throws Exception {
		IEmpServiceBack empService = Factory.getServiceInstance("emp.service.back");
		List<String> allJSON = new ArrayList<String>();
		Iterator<Dept> iter = empService.list().iterator();
		while(iter.hasNext()) {
			allJSON.add(JsonUtil.toJson(iter.next()));
		}
		ServletObjectUtil.getRequest().setCharacterEncoding("UTF-8");
		ServletObjectUtil.getResponse().setCharacterEncoding("UTF-8");
		ServletObjectUtil.getResponse().getWriter().println(allJSON);
	}
}
