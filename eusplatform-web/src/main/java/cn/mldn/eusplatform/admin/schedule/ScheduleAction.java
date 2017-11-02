package cn.mldn.eusplatform.admin.schedule;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import cn.mldn.eusplatform.service.back.IScheduleServiceBack;
import cn.mldn.eusplatform.vo.Schedule;
import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.ServletObjectUtil;
import cn.mldn.util.web.SplitPageUtil;

public class ScheduleAction extends AbstractAction{
	
	public static final String ACTION_TITLE = "审核" ;
	
	public ModelAndView list(){
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("audit.list"));
		SplitPageUtil spu = new SplitPageUtil("审核标题:title", "back.admin.audit.list.action");
		IScheduleServiceBack is = Factory.getServiceInstance("schedule.service.back");
		Set<Integer> set = new HashSet<>();
		set.add(1);
		set.add(2);
		set.add(4);
		set.add(5);
		
		try {
			mav.addObjectMap(
					is.listByAudit(spu.getColumn(), spu.getKeyWord(), spu.getCurrentPage(), spu.getLineSize(),set));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	public ModelAndView check(){
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("audit.propare"));
		SplitPageUtil spu = new SplitPageUtil("审核标题:title", "back.admin.audit.check.action");
		IScheduleServiceBack is = Factory.getServiceInstance("schedule.service.back");
		Set<Integer> set = new HashSet<>();
		set.add(3);
		try {
			mav.addObjectMap(
					is.listByAudit(spu.getColumn(), spu.getKeyWord(), spu.getCurrentPage(), spu.getLineSize(),set));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	public ModelAndView apply(String sid){
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("audis.apply"));
		IScheduleServiceBack is = Factory.getServiceInstance("schedule.service.back");
		try {
			mav.addObjectMap(is.get(Long.parseLong(sid)));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return mav;
	}
	public ModelAndView add(long sid,int audit,String anote) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("forward.page"));
		Schedule vo = new Schedule();
		vo.setAudit(audit);
		vo.setAnote(anote);
		vo.setAeid(super.getMid());
		vo.setAuddate(new Date());
		vo.setSid(sid);
		IScheduleServiceBack is = Factory.getServiceInstance("schedule.service.back");
		try {
			if(is.editApplySchedule(vo)){
				super.setUrlAndMsg(mav, "back.admin.audit.check.action", "schedule.check.success",ACTION_TITLE );
			}else{
				super.setUrlAndMsg(mav, "back.admin.audit.check.action", "schedule.check.failure",ACTION_TITLE );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	public ModelAndView show(String sid){
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("schedule.show"));
		IScheduleServiceBack is = Factory.getServiceInstance("schedule.service.back");
		try {
			mav.addObjectMap(is.get(Long.parseLong(sid)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	public ModelAndView report(String sid){
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("schedule.report"));
		IScheduleServiceBack is = Factory.getServiceInstance("schedule.service.back");
		try {
			mav.addObjectMap(is.get(Long.parseLong(sid)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
}
