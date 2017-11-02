$(function(){
	$("span[id^=sp-]").each(function(){
		audit = parseInt(this.id.split("-")[1]);
		sid = parseInt(this.id.split("-")[2])
		auditUtil(audit,sid);
	});
	$("button[id^=lsBut-]").each(function(){
		var sid = parseInt(this.id.split("-")[1])
		var ins = "#lsBut-" + sid;
		$(ins).on("click",function(){
			window.location = "pages/back/admin/schedule/ScheduleActionFront!show.action?sid=" + sid ;
		});
	});
});

