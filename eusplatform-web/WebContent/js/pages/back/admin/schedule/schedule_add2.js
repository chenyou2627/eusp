$(function() {
	$("#1044316902").hide();
	$("#editButton").on("click",function(){
		title = $("input[id=title]").val();
		iid = $("select option:checked").val();
		sdate = $("input[id=sdate]").val();
		note = $("#note").val();
		sid = $("#sid").val();
		$.post("pages/back/admin/schedule/ScheduleAddAction!editSchedule.action",{"sid":sid,"title":title,"iid":iid,"sdate":sdate,"note":note},function(data){
			operateAlert("data.trim()","修改成功","修改失败") ;
		},"text")
	})
	$("#sdate").on("click",WdatePicker) ;
	$("#myform").validate({
		debug : true, // 取消表单的提交操作
		
		errorPlacement : function(error, element) {
			$("#" + $(element).attr("id").replace(".", "\\.") + "Msg").append(error);
		},
		highlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1, function() {
					$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-error");
				});

			})
		},
		unhighlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1,function() {
						$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-success");
				});
			})
		},
		errorClass : "text-danger",
		rules : {
			"title" : {
				required : true,
				//remote : {
//									url : "check.jsp", // 后台处理程序
//									type : "post", // 数据发送方式
//									dataType : "html", // 接受数据格式
//									data : { // 要传递的数据
//										code : function() {
//											return $("#code").val();
//										}
//									},
//									dataFilter : function(data, type) {
//										if (data.trim() == "true")
//											return true;
//										else
//											return false;
//									}
				//}
			},
			"iid" : {
				required : true
			} ,
			"sdate" : {
				required : true 
			},
			"note" : {
				required : true
			}
		}
	});
})

