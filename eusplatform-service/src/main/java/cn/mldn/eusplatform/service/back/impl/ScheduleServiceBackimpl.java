package cn.mldn.eusplatform.service.back.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.eusplatform.dao.IDeptDAO;
import cn.mldn.eusplatform.dao.IItemDAO;
import cn.mldn.eusplatform.dao.ILevelDAO;
import cn.mldn.eusplatform.dao.IReportDAO;
import cn.mldn.eusplatform.dao.IScheduleDAO;
import cn.mldn.eusplatform.dao.IScheduleEmpDAO;
import cn.mldn.eusplatform.isutil.ReportUtil;
import cn.mldn.eusplatform.service.back.IScheduleServiceBack;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.eusplatform.vo.Report;
import cn.mldn.eusplatform.vo.Schedule;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.util.web.ServletObjectUtil;  

public class ScheduleServiceBackimpl extends AbstractService implements IScheduleServiceBack {

	@Override
	public Map<String,Object> listBySid(long sid)
			throws Exception {
		IScheduleEmpDAO ise = Factory.getDAOInstance("scheduleemp.dao");
		IDeptDAO id = Factory.getDAOInstance("dept.dao");
		ILevelDAO il = Factory.getDAOInstance("level.dao");
		Map<String, Object> map = new HashMap<>();
		map.put("allEmp", ise.findEidByEmp(sid));
		map.put("allLevel", il.findAllLevel());
		System.out.println(il.findAllLevel().toString());
		map.put("allDept", id.findAllMap());
		System.out.println(id.findAllMap().toString());
		map.put("sid", sid);
		return map;
	}
	
	@Override
	public Map<String, Object> listByAuditAndSid(String column, String keyWord, Long currentPage, Integer lineSize,
			Set<Integer> ids) throws Exception {
		String eid =(String) ServletObjectUtil.getRequest().getSession().getAttribute("eid") ;
		Map<String, Object> map = new HashMap<>();
		IScheduleDAO scheduleDAO = Factory.getDAOInstance("schedule.dao");
		IScheduleEmpDAO scheduleEmpDAO = Factory.getDAOInstance("scheduleemp.dao");
		IReportDAO reportDAO = Factory.getDAOInstance("report.dao");
		Set<Long> sid = scheduleEmpDAO.findSidByEid(eid) ;
		map.put("allReport", reportDAO.findAllMap(eid));
		if(sid != null && sid.size() > 0) {
			if(!(super.isLikeSearch(keyWord) && super.isLikeSearch(column))){
				map.put("allSchedule", scheduleDAO.findAllByAuditAndSid(currentPage, lineSize, sid, ids)) ;
				map.put("allRecorders", scheduleDAO.getAllCount(sid,ids));
			}else{
				map.put("allSchedule", scheduleDAO.findSplitByAuditAndSid(column, keyWord, currentPage, lineSize, sid,ids));
				map.put("allRecorders", scheduleDAO.getSplitCount(column, keyWord, sid, ids));
			}
		}
		return map;
	}
	
	@Override
	public Map<String, Object> listByAudit(String column, String keyWord, Long currentPage, Integer lineSize,
			Set<Integer> ids ) throws Exception {
		Map<String, Object> map = new HashMap<>();
		IScheduleDAO is = Factory.getDAOInstance("schedule.dao");
		
		if(!(super.isLikeSearch(keyWord) && super.isLikeSearch(column))){
			map.put("allSchedule", is.findAllByAuditAndSid(currentPage, lineSize, null, ids)) ;
			map.put("allRecorders", is.getAllCount(null,ids));
		}else{
			map.put("allSchedule", is.findSplitByAuditAndSid(column, keyWord, currentPage, lineSize, null, ids));
			map.put("allRecorders", is.getSplitCount(column, keyWord,null,ids));
		}
		return map;
	}
	
	@Override
	public Map<String, Object> list(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception{
		Map<String, Object> map = new HashMap<>();
		IScheduleDAO is = Factory.getDAOInstance("schedule.dao");
		
		if(!(super.isLikeSearch(keyWord) && super.isLikeSearch(column))){
			map.put("allSchedule", is.findAll(currentPage, lineSize));
			map.put("allRecorders", is.getAllCount());
		}else{
			map.put("allSchedule", is.findSplit(column, keyWord, currentPage, lineSize));
			map.put("allRecorders", is.getSplitCount(column, keyWord));
		}
		return map;
	}
	@Override
	public boolean add(Schedule vo) throws SQLException {
		String seid = (String)ServletObjectUtil.getRequest().getSession().getAttribute("eid");
		vo.setSeid(seid);
		vo.setAudit(0);
		IScheduleDAO is = Factory.getDAOInstance("schedule.dao");
		boolean flag = is.doCreate(vo);
		return flag;
	}
	@Override
	public Map<String, Object> listById(String column, String keyWord, Long currentPage, Integer lineSize, String eid)
			throws Exception {
		Map<String, Object> map = new HashMap<>();
		IScheduleDAO is = Factory.getDAOInstance("schedule.dao");
		
		if(!(super.isLikeSearch(keyWord) && super.isLikeSearch(column))){
			map.put("allSchedule", is.findAllById(currentPage, lineSize,eid));
			map.put("allRecorders", is.getAllCountById(eid));
		}else{
			map.put("allSchedule", is.findSplitById(column, keyWord, currentPage, lineSize,eid));
			map.put("allRecorders", is.getSplitCountById(column, keyWord,eid));
		}
		return map;
	}
	@Override
	public boolean deleteBySidAndSeid(long sid, String seid) throws SQLException {
		IScheduleDAO is = Factory.getDAOInstance("schedule.dao");
		return is.doRemoveBySidAndSeid(sid, seid);
	}
	@Override
	public boolean editAuditBySidAndSeid(long sid,String seid) throws SQLException {
		// TODO Auto-generated method stub
		IScheduleDAO is = Factory.getDAOInstance("schedule.dao");
		return is.doEditAuditBySidAndSeid(sid, seid);
	}
	@Override
	public Schedule getBySidAndSeid(long sid, String seid) throws SQLException {
		IScheduleDAO is = Factory.getDAOInstance("schedule.dao");
		return is.findBySidAndSeid(sid, seid);
	}
	

	@Override
	public Map<String,Object> get(Long sid) throws Exception {
		Map<String, Object> map = new HashMap<>();
		IScheduleEmpDAO ise = Factory.getDAOInstance("scheduleemp.dao");
		IScheduleDAO isd = Factory.getDAOInstance("schedule.dao");
		IDeptDAO id = Factory.getDAOInstance("dept.dao");
		IItemDAO iit = Factory.getDAOInstance("item.dao");
		ILevelDAO ild = Factory.getDAOInstance("level.dao");
		IReportDAO ip = Factory.getDAOInstance("report.dao");
		Report vo = new Report();
		vo.setSid(sid);
		vo.setSubdate(new Date());
		/*
		 * 下一条语句的意思是：
		 * 		根据审核表SID 查询出所有属于此表的雇员信息，然后将此信息中的雇员编号EID取出 进行Report表的批量增加。
		 */
		if(ip.findById(sid) == null){
			map.put("flag", ip.doReportBatch(vo, ReportUtil.convertListToSet(ise.findEidByEmp(sid))));
		}
		map.put("repCount", ReportUtil.repCount(ip.findAllBySid(vo.getSid(),null)));
		map.put("allReport", ip.findAllBySid(sid, null));
		map.put("allTitle", iit.findAllMap());
		map.put("allDname", id.findAllMap());
		map.put("allEmp", ise.findEidByEmp(sid));
		map.put("Schedule", isd.findById(sid));
		map.put("allLevel", ild.findAllLevel());
		map.put("allNote", ip.findAllNote(sid));
		map.put("allsubdate", ip.findAllSubdate(sid));
		return map;
	}

	@Override
	public boolean editSchedule(Schedule vo) throws SQLException {
		IScheduleDAO is = Factory.getDAOInstance("schedule.dao");
		return is.doEditScheduleBySidAndSeid(vo);
	}

	@Override
	public boolean editApplySchedule(Schedule vo) throws SQLException {
		IScheduleDAO is = Factory.getDAOInstance("schedule.dao");
		String anote = vo.getAnote() +  is.findById(vo.getSid()).getAnote();
		vo.setAnote(anote);
		return is.doEdit(vo);
	}
	@Override
	public boolean editAudit(Long sid,int audit) throws Exception{
		IScheduleDAO is = Factory.getDAOInstance("schedule.dao");
		return is.editAuditBySid(sid, audit);
	}
	@Override
	public boolean addEmpIntoSchedule(String eid, Long sid) throws SQLException {
		// TODO Auto-generated method stub
		IScheduleDAO is = Factory.getDAOInstance("schedule.dao");
		return is.doCreateEmpIntoSchedule(eid, sid);
	}

}
