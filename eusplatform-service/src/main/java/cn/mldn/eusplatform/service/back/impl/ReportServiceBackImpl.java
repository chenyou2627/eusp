package cn.mldn.eusplatform.service.back.impl;

import java.util.Map;

import cn.mldn.eusplatform.dao.IReportDAO;
import cn.mldn.eusplatform.dao.IScheduleDAO;
import cn.mldn.eusplatform.isutil.ReportUtil;
import cn.mldn.eusplatform.service.back.IReportServiceBack;
import cn.mldn.eusplatform.vo.Report;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.util.web.ServletObjectUtil;

public class ReportServiceBackImpl extends AbstractService implements IReportServiceBack {

	@Override
	public boolean add(Report vo) throws Exception {
		IReportDAO reportDAO = Factory.getDAOInstance("report.dao") ;
		IScheduleDAO scheduleDAO = Factory.getDAOInstance("schedule.dao");
		
		String eid =(String) ServletObjectUtil.getRequest().getSession().getAttribute("eid") ;
		vo.setEid(eid);
		
		if(reportDAO.doEdit(vo)) {
			Map<String,Integer> map = ReportUtil.isReportNote(reportDAO.findAllBySid(vo.getSid(),null));
			if(map.get("NoReport") == 0 ){
				return scheduleDAO.editAuditBySid(vo.getSid(),5);
			}
			return true;
		} 
		return false;
	}
	
}
