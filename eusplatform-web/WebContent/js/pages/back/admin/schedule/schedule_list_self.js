
$(function() {
	$("a[id^=show-]").on("click",function(){
		sid = this.id.split("-")[1];
		window.location ="pages/back/admin/schedule/ScheduleActionFront!show.action?sid=" + sid ;
	})
	change();
		$("span[id^=sp-]").each(function(){
			audit = parseInt(this.id.split("-")[1]);
			sid = parseInt(this.id.split("-")[2])
			auditUtil(audit,sid);
		});
	$("button[id^=removeBtn-]").on("click",function(){
		var r=confirm("确定要删除吗？");
		if(r==true){
			sid = this.id.split("-")[1];
			$.post("pages/back/admin/schedule/ScheduleAddAction!deleteSchedule.action",{sid:sid},function(data){
				
				operateAlert(true,"操作成功","") ;
				setTimeout(function(){
					location.reload();},800);
			});
		}else{
			
		}
		
	});
	$("#submit-0").on("click",function(){
		var r=confirm("确定要提交申请吗？");
		if(r==true){
			sid = this.value;
			$.post("pages/back/admin/schedule/ScheduleAddAction!submit.action",{sid:sid},function(data){
				setTimeout(function(){
					location.reload();},800);
			});
			
			
		}else{
			
		}
		
	});
	$("button[id!=submit-0]").on("click",function(){
		sid = this.value;
		window.location ="pages/back/admin/schedule/ScheduleActionFront!show.action?sid=" + sid ;
	})
	$("button[id^=editBtn-]").on("click",function(){
		var r=confirm("Press a button");
		if(r==true){
			sid = this.id.split("-")[1];
		}else{
			document.write("You pressed Cancel!");
		}
		
		
	});
	
})
function change(){
	$("button[id=submit-1]").text("查看申请");
	$("button[id=submit-2]").text("查看申请");
	$("button[id=submit-3]").text("查看申请");
	$("button[id=submit-4]").text("查看申请");
	$("button[id=submit-5]").text("查看申请");
	$("button[id=submit-6]").text("查看申请");
	
}

