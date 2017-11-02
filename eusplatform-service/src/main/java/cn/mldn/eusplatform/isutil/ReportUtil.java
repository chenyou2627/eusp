package cn.mldn.eusplatform.isutil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.eusplatform.vo.Report;

public class ReportUtil {
	public static Map<String,Integer> isReportNote(List<Report> report){
		Map<String,Integer> map = new HashMap<>();
		Iterator<Report> it = report.iterator();
		int noReport = 0;
		int Report = 0;
		map.put("NoReport",noReport);
		map.put("Report",Report);
		while(it.hasNext()){
			Report vo = it.next();
			if(vo.getNote() == null || "".equals(vo.getNote())){
				map.put("NoReport",++ noReport);
			}else{
				map.put("Report",++ Report);
			}
		}
		return map;
	}
	public static Integer repCount(List<Report> report){
		Integer count = 0;
		Iterator<Report> it = report.iterator();
		while(it.hasNext()){
			Report vo = it.next();
			if(!(vo.getNote() == null || "".equals(vo.getNote()))){
				count ++ ;
			}
		}
		return count;
	}
	public static Set<String> convertListToSet(List<Emp> allEmp){
		Set<String> set = new HashSet<>();
		Iterator<Emp> it = allEmp.iterator(); 
		while(it.hasNext()){
			set.add(it.next().getEid());
		}
		return set;
	}
}	
