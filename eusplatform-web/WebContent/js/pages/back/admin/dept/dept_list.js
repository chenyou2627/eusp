$(function(){
	$("button[id^=edit-]").each(function(){
		$(this).on("click",function(){
			did = this.id.split("-")[1] ;
			dname = $("#dname-" + did).val() ;
			maxnum = parseInt($("#maxnum-" + did).val()) ;
			currnum = parseInt($("#currnum-" + did).text()) ;
			if (dname == "") { 
				operateAlert(false,"","部门名称不允许为空，请确认后再提交更新！") ;
			}else if (maxnum < currnum){
				operateAlert(false,"","部门最大人数必须大于当前员工数，请确认后再提交更新！") ;
			}else if (maxnum <= 0){
				operateAlert(false,"","部门最大人数必须大于0，请确认后再提交更新！") ;
			} else {
				$.post("pages/back/admin/dept/DeptAction!edit.action",{"did":did,"dname":dname,"maxnum":maxnum},function(data){
					if(data == "true"){
						operateAlert(true,"部门信息更新成功！","") ;
					}else{
						operateAlert(false,"","部门信息更新失败！") ;
					}
				},"text") ;
			}
		}) ;
	}) ;
	$("span[id^=eid-]").each(function(){
		$(this).on("click",function(){
			var eid = "" ;
			temp = this.id.split("-") ;
			for(i = 1 ; i < temp.length ; i ++){
				eid += temp[i] + "-" ;
			}
			eid = eid.substring(0,eid.length-1) ;			
			$.post("pages/back/admin/dept/DeptAction!empInfo.action",{"eid":eid},function(data){
				if(data != "false"){
					if(data.loginMemberDid == 2){
						$("#salary").text(data.emp.salary) ;
					}else{
						$("#salary").text("******") ;
					}
					$("#dname").text(data.dname) ;
					$("#ename").text(data.emp.ename) ;
					$("#phone").text(data.emp.phone) ;
					$("#note").text(data.emp.note) ;
					$("#photo").attr("src","upload/emp/" + data.emp.photo) ;
					$("#hiredate").text(data.emp.hiredate) ;
					$("#title").text(data.title) ;
					$("#userInfo").modal("toggle") ;
				}else{
					alert("该部门领导为空！！") ;
				}
			},"json") ;
			
		}) ;
	}) ;
}) ;