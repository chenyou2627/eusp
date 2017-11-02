$(function(){
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
})