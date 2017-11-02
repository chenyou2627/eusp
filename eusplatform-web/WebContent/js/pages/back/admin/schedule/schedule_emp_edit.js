did = 1 ;
var sid = $("tr[id^=1044316902-]").id;

function deleteEmpFun(eid,tid) {
	// console.log(eid + " = " + tid) ;
	$.post("pages/back/admin/travel/delete_emp.action",{"tid":tid,"eid":eid},function(data){
		$("#travel-" + eid).remove() ;
		operateAlert(data.status,"出差人员信息移除成功！","出差人员信息移除失败！") ;
	},"json") ;
}

$(function(){
	
	
	$("button[id^=remove-]").each(function(){
		sid = $("tr[id^=ccc1]").attr("id").split("-")[1];
		eid = this.id.split("-")[1];
		$(this).on("click",function(){
			$.post("pages/back/admin/schedule/ScheduleAddAction!deleteScheduleEmp.action",{"eid":eid,"sid":sid},function(data){
				operateAlert(data.trim(),"删除成功","删除失败");
				location.reload();
			},"text")
		})
	}) ;
	$("#did").on("change",function(){
		did = $(this).val() ;
		loadEmpSchedule(did);
		$("#empSchedule").each(function(){
			
		})
		//loadData() ;
	}) ;
	$("#empSchedule").on("click","button[id^=add]",function(){
		
		var eid = this.id.split(",")[1];
		var sid = $("tr[id^=ccc1]").attr("id").split("-")[1];
		$.post("pages/back/admin/schedule/ScheduleAddAction!addEmpIntoSchedule.action",{"eid":eid,"sid":sid},function(data){
			operateAlert(data.trim(),"添加成功","添加失败");
			
			if(data.trim()== "true"){
			//	$("#travel-"+eid+"").remove();
				//alert($("div[id=divEmp-"+eid+"]"));
			//$("#empSchedule").find("div").remove();
//				loadEmpSchedule(did);
				//alert("请勿重复添加");
				loadEmpSchedule(did);
			}else{
				alert("----------------------");
			}
		},"text")
	});
	
	
	 
	$(addBtn).on("click",function(){
		
		 $("#did option").remove();
		 $("#empSchedule").find("tr").remove();
		 $.post("EmpBackAction!loadDept.action",{ },function(data){
			 $("#did").append("<option value=>====== 请选择雇员所在部门 ======</option>");
			 for(var x=0 ;x<data.length;x++){
				 $("#did").append("<option value='"+data[x].did+"'>" + data[x].dname + "</option>") ;
			  }
		 },"json");
		// Ajax异步读取用户信息
		// 将异步加载信息填充到模态窗口的组件之中
		//loadData() ;
		$("#userInfo").modal("toggle") ;	// 显示模态窗口
	}) ;
})

function loadData() {	// 该函数名称一定要固定，不许修改
	// 如果要想进行分页的处理列表前首先查询出部门编号
	did = $("#did").val() ;	// 取得指定组件的value
	tid = $("#tid").val() ;
	// console.log("部门编号：" + did) ;
	$.post("pages/back/admin/travel/emp_dept.action", {
		"did" : did,
		"cp" : jsCommonCp, 
		"ls" : jsCommonLs,
		"tid" : tid
	}, function(data) {
		console.log(data);
		$("#empTable tr:gt(0)").remove() ;
		for (var x = 0 ; x < data.allEmps.length ; x ++) {
			addTableRow(data.allEmps[x].photo,data.allEmps[x].eid,data.allEmps[x].ename,data.allEmps[x].sal,data.allEmps[x].lid) ;
		}
		createSplitBar(data.allRecorders) ;	// 创建分页控制项
	}, "json");
}
function addTableRow(photo,eid,ename,sal,lid) {
	level = "普通员工" ;
	if (lid == "manager") {
		level = "部门经理" ;
	} else if (lid == "chief") {
		level = "总监" ;
	}
	row = 	" <div id='divEmp-"+eid+"'>" +
			"	<tr id='travelEmp-"+eid+"'>" + 
			"		<td class='text-center'>" +
			"			<img src='upload/member/"+photo+"' style='width:20px;'/> " +
			"		</td>" +
			"		<td class='text-center'>"+eid+"</td>" +
			"		<td class='text-center'>"+ename+"</td>" +
			"		<td class='text-center'>￥"+sal+"</td>" +
			"		<td class='text-center'>"+level+"</td>" +
			"		<td class='text-center'>" +
			"			<button class='btn btn-danger btn-xs' id='addEmp-"+eid+"'>" +
			"				<span class='glyphicon glyphicon-plus-sign'></span>&nbsp;增加</button>" +
			"		</td>" + 
			"	</tr> " +
			"</div>";
	$(empTable).append(row) ;
	$("#addEmp-" + eid).on("click",function(){
		tid = $("#tid").val() ;
		$.post("pages/back/admin/travel/add_emp.action",{"eid":eid,"tid":tid},function(data){
			if (data.status == true) {	// 待出发用户添加完成
				$("#travelEmp-" + eid).remove() ;
				rowInfo = 	"<tr id='travel-"+eid+"'>" + 
							"	<td class='text-center'>" +
							"		<img src='upload/member/"+data.emp.photo+"' style='width:20px;'/> " +
							"	</td>" +
							"	<td class='text-center'>"+data.emp.eid+"</td>" +
							"	<td class='text-center'>"+data.emp.ename+"</td>" +
							"	<td class='text-center'>￥"+data.emp.sal+"</td>" +
							"	<td class='text-center'>"+data.level.title+"</td>" +
							"	<td class='text-center'>"+data.dept.dname+"</td>" +
							"	<td class='text-center'>" +
							"		<button class='btn btn-danger btn-xs' id='remove-"+data.emp.eid+"-"+tid+"'>" +
							"			<span class='glyphicon glyphicon-remove'></span>&nbsp;移除</button>" +
							"	</td>" +
							"</tr> " ;
				$("#travelEmpTable").append(rowInfo) ;
				$("#remove-"+data.emp.eid+"-"+tid).on("click",function(){
					deleteEmpFun(eid,tid) ;
				}) ;
			} else {
				$("#userInfo").modal("toggle") ;
				operateAlert(false,"","该雇员已经有了项目安排无法添加到本次出差人员之内！") ;
			}
		},"json") ;
	}) ;
	
}

function loadEmpSchedule(did){
	  $("#empSchedule").find("tr").remove();
	  $.post("EmpBackAction!loadSchedule.action",{"did":did},function(data){
		  $("#empSchedule").find("tr").remove();
		  for(var x=0 ;x<data.length;x++){
			var str = "<tr id='travel-'"+data[x].eid+"'>" +
					"<td class='text-center'><img src='upload/emp/"+data[x].photo+"' style='width:20px';/></td>" +
					"<td class='text-center'>"+data[x].eid+"</td>" +
					"<td class='text-center'>"+data[x].ename+"</td>" +
					"<td class='text-center'>"+data[x].levelTitle+"</td>" +
					"<td class='text-center'>"+data[x].deptTitle+"</td>" +
					"<td class='text-center'>" +
					"<button class='btn btn-danger btn-xs' id='add,"+data[x].eid+"'>" +
					"<span id='spanDiv-"+data[x].eid+"' class='glyphicon glyphicon-plus-sign'></span>&nbsp;增加</button>" +
					"</td>" +
					"</tr>";
			$("#empSchedule").append($(str));
			 $("#remove-" + data[x].eid).on("click",function() {
			       var eid = this.id.split("-")[1];
			       alert(eid);
			    }) ;
			 $("#splitSchedule").append(createSplitBar(20));
			 $("#remove-" + data[x].eid).on("click",function() {
			       var eid = this.id.split("-")[1];
			       alert(eid);
			    }) ;
			 createSplitBar(10);
		  }
		  createSplitBar(10);
	    },"json") ;
	 
}