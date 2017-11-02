$(function(){
	$("span[id^=sp]").each(function(){
		temp = this.id.split("-") ;
		auditUtil(parseInt(temp[1]),parseInt(temp[2])) ;
	}) ;
	$("button[id^=btn-]").each(function(){
		var sid = this.id.split("-")[2] ;
		console.log(this.id);
		$("#" + this.id).on("click",function(){
			console.log(sid);
			window.location = "pages/back/admin/schedule/SchedulePersonalTaskAction!prepare.action?sid=" + sid ;
		});
	});
})