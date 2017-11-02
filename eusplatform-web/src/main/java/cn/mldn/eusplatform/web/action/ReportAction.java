package cn.mldn.eusplatform.web.action;

import cn.mldn.eusplatform.service.back.IReportServiceBack;
import cn.mldn.eusplatform.vo.Report;
import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;

public class ReportAction extends AbstractAction{
	public ModelAndView add(Report vo) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("forward.page")) ;
		IReportServiceBack reportService = Factory.getServiceInstance("report.service.back") ;
		try {
			if(reportService.add(vo)) {
				super.setUrlAndMsg(mav, "scheculepersonaltask.listhistory.action", "vo.add.success", "报告");
			}else {
				super.setUrlAndMsg(mav, "scheculepersonaltask.listhistory.action", "vo.add.failure", "报告");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
}
