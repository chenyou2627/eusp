package cn.mldn.eusplatform.web.action;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.mldn.eusplatform.service.back.IScheduleServiceBack;
import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.SplitPageUtil;

public class ScheduleTaskAction extends AbstractAction {
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("scheculetask.list")) ;
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		SplitPageUtil splitPage = new SplitPageUtil("申请标题:title", "scheculetask.list.action") ;
		Set<Integer> ids = new HashSet<Integer>() ;
		ids.add(4) ;
		try {
			Map<String,Object> map = scheduleService.listByAuditAndSid(splitPage.getColumn(), splitPage.getKeyWord(), splitPage.getCurrentPage(), splitPage.getLineSize(),ids) ;
			mav.addObjectMap(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
}
