<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/pages/plugins/back/back_header.jsp"/>
<script type="text/javascript" src="js/pages/back/admin/audit/audit_schedule_show.js"></script>
<%!
	public static final String SCHEDULE_REPORT_LIST_URL = "pages/back/admin/schedule/ScheduleActionFront!report.action" ;
%>
<body class="hold-transition skin-blue sidebar-mini"> 
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/pages/plugins/back/include_title_head.jsp"/>
		<!-- 导入左边菜单项 -->
		<jsp:include page="/pages/plugins/back/include_menu_item.jsp">
			<jsp:param name="mi" value="4"/>
			<jsp:param name="msi" value="41"/>
		</jsp:include>
		<div id="audit">${param.audit }</div>
		<div class="content-wrapper text-left">
					<div class="panel panel-success">
				<div class="panel-heading">
					<strong><span class="glyphicon glyphicon-check"></span>&nbsp;查看调度安排</strong>
				</div>
				<div id="audit" style="display:none">${param.audit }</div>
				<div class="panel-body">
					<div>
						<table class="table table-striped table-bordered table-hover">
							<tr> 
								<td style="width:150px;"><strong>申请标题：</strong></td>
								<td>${Schedule.title}</td>
							</tr>
							<tr>
								<td><strong>任务类型：</strong></td>
								<td>${allTitle[Schedule.iid]}</td>
							</tr>
							<tr>
								<td><strong>总人数：</strong></td>
								<td>${Schedule.ecount}人</td>
							</tr>
							<tr>
								<td><strong>任务日期：</strong></td>
								<td>${Schedule.sdate}</td>
							</tr>
							<tr>
								<td><strong>任务描述：</strong></td>
								<td>${Schedule.note}</td>
							</tr>
							<c:if test="${(Schedule.audit == 4)or(Schedule.audit == 5)}">
							<tr id="taskReport">
								<td><strong>任务报告：</strong></td>
								<td><a type="button" class="btn btn-primary btn-xs" href="<%=SCHEDULE_REPORT_LIST_URL%>?sid=${Schedule.sid}">
										<span class="fa fa-file-text-o"></span>&nbsp;查看任务报告</a></td>
							</tr>
							</c:if>
						</table>
					</div>
					<div class="panel-group" id="news">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h4 class="panel-title"> 
									<a data-toggle="collapse" data-parent="news" href="#contentOne">
										<strong><span class="glyphicon glyphicon-user"></span>&nbsp;出差人员安排（总人数：${Schedule.ecount}人）</strong>
									</a>
								</h4>
							</div>
							<div id="contentOne" class="panel-collapse collapse in">
								<div class="panel-body">
									<table class="table table-condensed">
										<thead>
											<tr>
												<th class="text-center"><strong>照片</strong></th>
												<th class="text-center"><strong>姓名</strong></th>
												<th class="text-center"><strong>联系电话</strong></th>
												<th class="text-center"><strong>级别</strong></th>
												<th class="text-center"><strong>雇佣日期</strong></th>
												<th class="text-center"><strong>部门</strong></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${allEmp}" var="emp">
												<tr id="travel-${emp.eid }">
													<td class="text-center">
														<img src="upload/emp/${emp.photo }" style="width:20px;"/> 
													</td>
													<td class="text-center"><span id="eid-${emp.eid }" style="cursor:pointer;">${emp.ename }</span></td>
													<td class="text-center">${emp.phone}</td>
													<td class="text-center">${allLevel[emp.lid]}</td>
													<td class="text-center">${emp.hiredate}</td>
													<td class="text-center">${allDname[emp.did]}</td>
												</tr> 
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer" style="height:80px;">
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
	<jsp:include page="/pages/plugins/back/info/emp_info_modal.jsp"/>
	<jsp:include page="/pages/plugins/back/include_javascript_foot.jsp" />
<jsp:include page="/pages/plugins/back/back_footer.jsp"/>
