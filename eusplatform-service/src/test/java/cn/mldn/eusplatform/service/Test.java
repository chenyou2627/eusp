package cn.mldn.eusplatform.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hamcrest.core.Is;

import cn.mldn.eusplatform.dao.IReportDAO;
import cn.mldn.eusplatform.dao.IScheduleDAO;
import cn.mldn.eusplatform.dao.IScheduleEmpDAO;
import cn.mldn.eusplatform.isutil.ReportUtil;
import cn.mldn.eusplatform.service.back.IScheduleServiceBack;
import cn.mldn.eusplatform.vo.Report;
import cn.mldn.eusplatform.vo.Schedule;
import cn.mldn.util.factory.Factory;

public class Test {
	public static void main(String[] args) {
//		IScheduleServiceBack is = Factory.getServiceInstance("schedule.service.back");
//		
//		try {
//			System.out.println(is.get(1L));
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		IReportDAO ir = Factory.getDAOInstance("report.dao");
		try {
			System.out.println(ir.findAllSubdate(57L));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
