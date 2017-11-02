package cn.mldn.eusplatform.web.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import cn.mldn.eusplatform.dao.impl.ScheduleDAOImpl;
import cn.mldn.eusplatform.service.back.IItemServiceBack;
import cn.mldn.eusplatform.service.back.IScheduleServiceBack;
import cn.mldn.eusplatform.vo.Item;
import cn.mldn.eusplatform.vo.Schedule;
import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.ServletObjectUtil;
import cn.mldn.util.web.SplitPageUtil;

public class ScheduleAddAction extends AbstractAction{
//	public ModelAndView listEmp() {
//		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("emp1.list.page"));
//		SplitPageUtil spu = new SplitPageUtil("雇员名称:ename", "schedule1.list.action");
//		IEmpServiceBack empService = Factory.getServiceInstance("emp.service.back");
//		System.out.println(spu.getLineSize());
//		try {
//			mav.addObjectMap(empService.list(spu.getColumn(), spu.getKeyWord(), 
//					spu.getCurrentPage(), spu.getLineSize()));
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return mav;
//	}
	public void deleteScheduleEmp(String eid,Long sid) {
		ScheduleDAOImpl dao = new ScheduleDAOImpl();
		try {
			boolean flag = dao.deleteByEid(eid);
			if(flag== true) {
				dao.decreaseEcount(sid);
			}
			super.print(flag);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void addEmpIntoSchedule(String eid,Long sid) {
		IScheduleServiceBack is = Factory.getServiceInstance("schedule.service.back");
		ScheduleDAOImpl service = new ScheduleDAOImpl();
		boolean flag = false;
		try {
			flag = is.addEmpIntoSchedule(eid, sid);
			if(flag==true) {
				service.increaseEcount(sid);
			}
			super.print(flag);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 调度申请人将状态由 0 改变成 3；
	 */
	public void submit() {
		
		IScheduleServiceBack is = Factory.getServiceInstance("schedule.service.back");
		String seid = (String)ServletObjectUtil.getRequest().getSession().getAttribute("eid");
		long sid = Long.parseLong(ServletObjectUtil.getRequest().getParameter("sid"));
		try {
			boolean flag = is.editAuditBySidAndSeid(sid, seid);
			super.print(flag);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public void AddSchedule(Schedule vo) {
	IScheduleServiceBack is = Factory.getServiceInstance("schedule.service.back");
		vo.setSeid((String)ServletObjectUtil.getRequest().getSession().getAttribute("eid"));
		vo.setSubdate(new Date());
		IScheduleServiceBack service = Factory.getServiceInstance("schedule.service.back");
		ScheduleDAOImpl dao = new ScheduleDAOImpl();
		//vo.setEcount(dao.getEmpCount(sid));
		String seid = (String)ServletObjectUtil.getRequest().getSession().getAttribute("eid");
		this.ListItem();
		try {
			super.print(service.add(vo));
			boolean flag1= is.addEmpIntoSchedule(seid, dao.getMaxSid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void deleteSchedule() {
		IScheduleServiceBack is = Factory.getServiceInstance("schedule.service.back");
		String seid = (String)ServletObjectUtil.getRequest().getSession().getAttribute("eid");
		long sid = Long.parseLong(ServletObjectUtil.getRequest().getParameter("sid"));
		try {
			boolean flag = is.deleteBySidAndSeid(sid, seid);
			super.print(flag);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 进行Item的罗列
	 * @return
	 */
	public ModelAndView ListItem() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("front.orders.addpre.page")) ;
		IItemServiceBack service = Factory.getServiceInstance("item.service.back");
		try {
			List<Item> allItems = service.list();
			
			mav.addObject("allItems", allItems);
			return mav;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	public ModelAndView showEdit() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("front1.orders.addpre.page")) ;
		IScheduleServiceBack is = Factory.getServiceInstance("schedule.service.back");
		IItemServiceBack service = Factory.getServiceInstance("item.service.back");
		String seid = (String)ServletObjectUtil.getRequest().getSession().getAttribute("eid");
		long sid = Long.parseLong(ServletObjectUtil.getRequest().getParameter("id"));
		try {
			Schedule vo = is.getBySidAndSeid(sid, seid);
			List<Item> allItems = service.list();
			mav.addObject("allItems", allItems);
			mav.addObject("vo", vo);
			return mav;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	
	
	
	public void editSchedule(Schedule vo) {
			vo.setSubdate(new Date());
			vo.setSeid((String)ServletObjectUtil.getRequest().getSession().getAttribute("eid"));
			IScheduleServiceBack service = Factory.getServiceInstance("schedule.service.back");
			vo.setSeid((String)ServletObjectUtil.getRequest().getSession().getAttribute("eid"));
			this.ListItem();
			try {
				super.print(service.editSchedule(vo));
			} catch (Exception e) {
				e.printStackTrace();
			}
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
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("schedule.list.page")) ;
		SplitPageUtil spu = new SplitPageUtil("活动类型:title", "schedule.list.action");
		IItemServiceBack service = Factory.getServiceInstance("item.service.back");
		IScheduleServiceBack is = Factory.getServiceInstance("schedule.service.back");
		String eid = (String)ServletObjectUtil.getRequest().getSession().getAttribute("eid");
		
		try {
			List<Item> allItems = service.list();
			mav.addObject("allItems", allItems);
			mav.addObjectMap(
					is.listById(spu.getColumn(), spu.getKeyWord(), spu.getCurrentPage(), spu.getLineSize(), eid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	
	}
	public ModelAndView edit(long sid) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("schedule.edit")) ;
		IScheduleServiceBack is = Factory.getServiceInstance("schedule.service.back");
		try {
			mav.addObjectMap(is.listBySid(sid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	
	}
	

}
