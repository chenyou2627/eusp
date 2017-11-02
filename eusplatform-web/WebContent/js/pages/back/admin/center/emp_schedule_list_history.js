$(function(){
	$("span[id^=sp]").each(function(){
		temp = this.id.split("-") ;
		auditUtil(parseInt(temp[1]),parseInt(temp[2])) ;
	}) ;
//	$("td[id^=hid-]").each(function(){
//		var hid = this.id.split("-")[1];
//		console.log(hid);
//		console.log($("#hid" + "-" + hid).text());
//		console.log($(this).text() == "" || $(this).text() == null );
//	});
	$("button[id^=btn]").each(function(){
		temp = this.id.split("-") ;
		var hid = this.id.split("-")[2];
		var flag = ($("#hid" + "-" + hid).text() != "") && ($("#hid" + "-" + hid).text() != null) ;
		if(!flag){
			$(this).html("<span class='fa fa-pencil-square-o'></span>&nbsp;任务报告") ;
			$(this).on("click",function(){
				id = this.id.split("-") ;
				window.location = "pages/back/admin/center/emp_schedule_report.jsp?sid=" + id[2] ;
			});
		}else {
			$(this).html("<span class='glyphicon glyphicon-th'></span>&nbsp;任务详情") ;
			$("#tdshow-" + temp[2]).text($("#showBtn-" + temp[2]).text()) ;
			$(this).on("click",function(){
				id = this.id.split("-") ;
				window.location = "pages/back/admin/schedule/ScheduleActionFront!show.action?sid=" + id[2] ;
			});
		}
//		if(temp[1] == 5){
//			$(this).html("<span class='glyphicon glyphicon-wrench'></span>&nbsp;未通过原因") ;
//			$("#showBtn-" + temp[2]).attr("href","pages/back/admin/schedule/ScheduleActionFront!show.action?sid=" + temp[2]) ;
//			$(this).on("click",function(){
//				id = this.id.split("-") ;
//				window.location = "pages/back/admin/schedule/ScheduleActionFront!apply.action?flag=0&sid=" + id[2] ;
//			});
//		}
//		if(temp[1] == 5 || temp[1] == 3){
//			$(this).html("<span class='glyphicon glyphicon-th'></span>&nbsp;任务详情") ;
//			$("#tdshow-" + temp[2]).text($("#showBtn-" + temp[2]).text()) ;
//			$(this).on("click",function(){
//				id = this.id.split("-") ;
//				window.location = "pages/back/admin/schedule/ScheduleActionFront!show.action?sid=" + id[2] ;
//			});
//		}else{
//			
//		}
	}) ;
	
})