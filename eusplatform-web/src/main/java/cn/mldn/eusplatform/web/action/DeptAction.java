package cn.mldn.eusplatform.web.action;

import java.util.Date;
import java.util.Map;

import cn.mldn.eusplatform.service.back.IDeptServiceBack;
import cn.mldn.eusplatform.vo.Dept;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.util.DateJsonValueProcessor;
import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.ServletObjectUtil;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class DeptAction extends AbstractAction {
	/**
	 * 部门的列表显示
	 * @return 跳转
	 */
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("dept.list")) ;
		IDeptServiceBack deptService = Factory.getServiceInstance("dept.service.back") ;
		try {
			mav.addObjectMap(deptService.list());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	/**
	 * 部门信息的修改
	 * @param vo 要修改的vo
	 */	
	public void edit(Dept vo) {
		IDeptServiceBack deptService = Factory.getServiceInstance("dept.service.back") ;
		try {
			super.print(deptService.edit(vo)) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 模态框数据的显示
	 * @param eid 雇员ID
	 */
	public void empInfo(String eid) {
		if(eid != null) {
			IDeptServiceBack deptService = Factory.getServiceInstance("dept.service.back") ;
			Long did = (Long)ServletObjectUtil.getRequest().getSession().getAttribute("did") ;
			JSONObject obj = new JSONObject() ;
			try {
				Map<String,Object> map = deptService.getEmpAndDeptAndLevelInfo(eid) ;
				Emp emp = (Emp)map.get("emp") ;
				String dname = (String)map.get("dname") ;
				String title = (String)map.get("levelTitle") ;
				//json对日期的处理
				JsonConfig config = new JsonConfig() ;
				DateJsonValueProcessor dataValue = new DateJsonValueProcessor();
				config.registerJsonValueProcessor(Date.class, dataValue);
				obj.put("emp", JSONObject.fromObject(emp,config)) ;
				obj.put("dname",dname) ;
				obj.put("title", title) ;
				obj.put("loginMemberDid", did) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
			super.print(obj);
		}else {
			super.print(false);
		}
	}
	public void add(Dept dept) {
		IDeptServiceBack deptService = Factory.getServiceInstance("dept.service.back") ;
		long did = (Long)ServletObjectUtil.getRequest().getSession().getAttribute("did") ;
		try {
			if(dept.getMaxnum()<=0 || did != 2l) {
				super.print(false);
			}else {
				super.print(deptService.add(dept));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
