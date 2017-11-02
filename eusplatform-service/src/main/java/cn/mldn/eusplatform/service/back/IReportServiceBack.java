package cn.mldn.eusplatform.service.back;

import cn.mldn.eusplatform.vo.Report;

public interface IReportServiceBack {
	/**
	 * 增加一个报告并修改调度的状态
	 * @param vo 报告
	 * @return 增加成功返回true
	 * @throws Exception JDBC
	 */
	public boolean add(Report vo) throws Exception ;
}
