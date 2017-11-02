<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/pages/plugins/back/back_header.jsp"/>
<%!
	public static final String EMP_EDIT_URL = "EmpBackAction!edit.action" ;
%>
<script type="text/javascript" src="js/pages/back/admin/emp/emp_add.js"></script>
<body class="hold-transition skin-blue sidebar-mini"> 
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/pages/plugins/back/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/pages/plugins/back/include_menu_item.jsp">
			<jsp:param name="mi" value="2"/>
			<jsp:param name="msi" value="22"/>
		</jsp:include>
		<div class="content-wrapper text-left">
					<div class="panel panel-success">
				<div class="panel-heading">
					<strong><span class="glyphicon glyphicon-user"></span>&nbsp;雇员编辑</strong>
				</div>
				<div class="panel-body">
					<form class="form-horizontal" action="<%=EMP_EDIT_URL%>" id="myform" method="post" 
					enctype="multipart/form-data">
						<fieldset>
							<div class="form-group" id="passwordDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="password">登录密码：</label>
								<div class="col-md-5">
									<!-- 定义表单输入组件 -->
									<input type="text" id="password" name="password" value = "${emp.password}" class="form-control"
										placeholder="${emp.password}">
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="passwordMsg"></div>
							</div>
							<div class="form-group" id="enameDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="ename">雇员姓名：</label>
								<div class="col-md-5">
									<!-- 定义表单输入组件 -->
									<input type="text" id="ename" name="ename" value="${emp.ename}" class="form-control"
										placeholder="${emp.ename}">
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="enameMsg"></div>
							</div>
							<div class="form-group" id="phoneDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="phone">联系电话：</label>
								<div class="col-md-5">
									<!-- 定义表单输入组件 -->
									<input type="text" id="phone" name="phone" value="${emp.phone}" class="form-control"
										placeholder="${emp.phone}">
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="phoneMsg"></div>
							</div>
							<div class="form-group" id="didDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="jid">所属部门：</label>
								<div class="col-md-5">
									<select id="did" name="did" class="form-control">
									<c:forEach items="${allDepts}" var="dept">
									<c:if test="${dept.did==emp.did}">
										<option value="${dept.did}" selected>${allDeptMap[dept.did]}</option>
									</c:if>
									<c:if test="${dept.did!=emp.did}">
										<option value="${dept.did}" >${allDeptMap[dept.did]}</option>
									</c:if>
									</c:forEach>
									</select>
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="didMsg"></div>
							</div>
							<div class="form-group" id="lidDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="l">雇员职位：</label>
								<div class="col-md-5">
									<select id="lid" name="lid" class="form-control">
										<c:forEach items="${allLevel}" var="level">
										<c:if test="${level.lid==emp.lid}">
										<option value="${level.lid}" id="${level.lid}" selected>${allLevelMap[level.lid]}</option>
										</c:if>
										<c:if test="${level.lid!=emp.lid}">
										<option value="${level.lid}">${allLevelMap[level.lid]}</option>
										</c:if>
										</c:forEach>
									</select>
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="lidMsg"></div>
							</div>
							<div class="form-group" id="salaryDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="salary">基本工资：</label>
								<div class="col-md-5">
									<!-- 定义表单输入组件 -->
									<input type="text" id="salary" name="salary" value="${emp.salary}" class="form-control"
										placeholder="${emp.salary}">
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="salaryMsg"></div>
							</div>
							<div class="form-group" id="picDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="pic">雇员照片：</label>
								<div class="col-md-5">
									<img src="upload/emp/${emp.photo}" style="width:200px;"/>
									<!-- 定义表单输入组件 -->
									<input type="file" id="pic" name="pic" class="form-control"
										placeholder="请选择雇员照片">
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="picMsg"></div>
							</div>
							<!-- 定义输入表单样式，其中id主要用于设置颜色样式 -->
							<div class="form-group" id="noteDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="note">备注信息：</label>
								<div class="col-md-5">
									<!-- 定义表单输入组件 -->
									<textarea id="note" name="note"
										class="form-control" placeholder="${emp.note}" value="${emp.note}" rows="10">${emp.note}
									</textarea>
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="noteMsg"></div>
							</div> 
							<div class="form-group">
								<div class="col-md-5 col-md-offset-3">
									<input type="hidden" name="oldpic" id="oldpic" value="${emp.photo}">
									<input type="hidden" name ="eeid" id="eeid" value="${emp.eid}">
									<button type="submit" class="btn btn-primary">编辑</button>
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
