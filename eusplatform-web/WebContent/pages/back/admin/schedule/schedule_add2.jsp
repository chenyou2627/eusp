<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/pages/plugins/back/back_header.jsp"/>
<%!
	
%>
<script type="text/javascript" src="js/pages/back/admin/schedule/schedule_add2.js"></script>
<script type="text/javascript" src="${basePath}js/my97date/WdatePicker.js"></script> 
<body class="hold-transition skin-blue sidebar-mini"> 
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/pages/plugins/back/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/pages/plugins/back/include_menu_item.jsp">
			<jsp:param name="mi" value="3"/>
			<jsp:param name="msi" value="31"/>
		</jsp:include>
		<div class="content-wrapper text-left">
					<div class="panel panel-success">
				<div class="panel-heading">
					<strong><span class="glyphicon glyphicon-file"></span>&nbsp;人力资源调度申请单</strong>
				</div>
				<div class="panel-body">
					<form class="form-horizontal" action="" id="myform" method="post">
						<fieldset>
							<!-- 定义输入表单样式，其中id主要用于设置颜色样式 -->
							<div class="form-group" id="titleDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="title">申请标题：</label>
								<div class="col-md-5">
									<!-- 定义表单输入组件 -->
									<input type="text" id="title" name="title" class="form-control"
										placeholder="" value="${vo.title}" >
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="titleMsg"></div>
							</div>
							<div class="form-group" id="iidDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="iid">申请类型：</label>
								<div class="col-md-5">
									<select id="iid" name="iid" class="form-control">
										<c:forEach items="${allItems}" var="item">
											
											<c:choose>
													   		<c:when test="${item.iid!=vo.iid}">
													   			<option value="${item.iid}" id="${item.iid}" >${item.title}</option>
													   		</c:when>
													   		<c:otherwise>
													   			<option value="${item.iid}" id="${item.iid}" selected = "selected">${item.title}</option>
													   		</c:otherwise>
												  
											</c:choose>
										</c:forEach>
									</select>
									<p>${msg}</p>
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="iidMsg"></div>
							</div>
							<div class="form-group" id="sdateDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="sdate">任务日期：</label>
								<div class="col-md-5">
									<!-- 定义表单输入组件 -->
								<input type="text" id="sdate" name="sdate" class="form-control"
										placeholder="${vo.sdate}" value="${vo.sdate}" readonly>
								</div>
								<div class="form-group" id="sdateDiv">
								<!-- 定义表单提示文字 -->
								<div class="col-md-5" id="1044316902" style="dispay:none">
									<!-- 定义表单输入组件 -->
									<input type="text" id="sid" name="sid" class="form-control"
										placeholder="" value="${vo.sid}" readonly>
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="sdateMsg"></div>
							</div>
							</div>
							<!-- 定义输入表单样式，其中id主要用于设置颜色样式 -->
							<div class="form-group" id="noteDiv">
								<!-- 定义表单提示文字 -->	
								<label class="col-md-3 control-label" for="note">任务描述：</label>
								<div class="col-md-5">
									<!-- 定义表单输入组件 -->
									<textarea id="note" name="note"
										class="form-control" placeholder="${vo.note}" rows="10">${vo.note}</textarea>
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="noteMsg"></div>
							</div> 
							<div class="form-group">
								<div class="col-md-5 col-md-offset-3">
									<button id="editButton" class="btn btn-primary">修改</button>
									<button type="reset" class="btn btn-warning">重置</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
				<div class="panel-footer">
					<jsp:include page="/pages/plugins/include_alert.jsp"/>
				</div>
			</div>
		</div>
		<!-- 导入公司尾部认证信息 -->
		<jsp:include page="/pages/plugins/back/include_title_foot.jsp" />
		<!-- 导入右边工具设置栏 -->
		<jsp:include page="/pages/plugins/back/include_menu_sidebar.jsp" />
		<div class="control-sidebar-bg"></div>
	</div>
	<jsp:include page="/pages/plugins/back/include_javascript_foot.jsp" />
<jsp:include page="/pages/plugins/back/back_footer.jsp"/>
