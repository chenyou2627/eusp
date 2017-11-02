package cn.mldn.eusplatform.web.action;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.mldn.eusplatform.service.back.IScheduleServiceBack;
import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.SplitPageUtil;

public class SchedulePersonalTaskAction extends AbstractAction {
	
	public static final String ACTION_TITLE = "任务" ;
	
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("scheculepersonaltask.list")) ;
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		SplitPageUtil splitPage = new SplitPageUtil("申请标题:title", "scheculepersonaltask.list.action") ;
		Set<Integer> ids = new HashSet<Integer>() ;
		ids.add(1) ;
		try {
			Map<String,Object> map = scheduleService.listByAuditAndSid(splitPage.getColumn(), splitPage.getKeyWord(), splitPage.getCurrentPage(), splitPage.getLineSize(),ids) ;
			mav.addObjectMap(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	public ModelAndView listHistory() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("scheculepersonaltask.listhistory")) ;
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		SplitPageUtil splitPage = new SplitPageUtil("申请标题:title", "scheculepersonaltask.listhistory.action") ;
		Set<Integer> ids = new HashSet<Integer>() ;
		ids.add(5) ;
		ids.add(4) ;
		try {
			Map<String,Object> map = scheduleService.listByAuditAndSid(splitPage.getColumn(), splitPage.getKeyWord(), splitPage.getCurrentPage(), splitPage.getLineSize(),ids) ;
			mav.addObjectMap(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	public ModelAndView prepare(long sid) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("forward.page")) ;
		IScheduleServiceBack is = Factory.getServiceInstance("schedule.service.back");
		try {
			if(is.editAudit(sid, 4)){
				super.setUrlAndMsg(mav, "scheculepersonaltask.list.action", "schedule.prepare.success",ACTION_TITLE );
			}else{
				super.setUrlAndMsg(mav, "scheculepersonaltask.list.action", "schedule.prepare.failure",ACTION_TITLE );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	
}
