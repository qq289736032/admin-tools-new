<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1282'/></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#loginName").focus();
			$("#inputForm").validate({
				rules: {
					loginName: {remote: "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')}
				},
				messages: {
					loginName: {remote: "<spring:message code='str1283'/>"},
					confirmNewPassword: {equalTo: "<spring:message code='str1284'/>"}
				},
				submitHandler: function(form){
					loading('<spring:message code='str1105'/>...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("<spring:message code='str1106'/>");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
			<li ><a href="${ctx}/sys/user/"><spring:message code='str1548'/></a></li>
		    <li class="active"><a href="${ctx}/sys/user/form"><shiro:hasPermission name="sys:user:edit"><spring:message code='str1549'/></a></li></shiro:hasPermission>
		
	</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group" hidden="hidden">
			<label class="control-label"><spring:message code='str1286'/>:</label>
			<div class="controls">
                <tags:treeselect id="company" name="company.id" value="${user.company.id}" labelName="company.name" labelValue="${user.company.name}"
					title="<spring:message code='str1287'/>" url="/sys/office/treeData?type=1" cssClass="required"/>
			</div>
		</div>
		<div class="control-group" hidden="hidden">
			<label class="control-label"><spring:message code='str1288'/>:</label>
			<div class="controls">
                <tags:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}"
					title="<spring:message code='str1289'/>" url="/sys/office/treeData?type=2" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1427'/>:</label>
			<div class="controls">
				<input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
				<form:input path="loginName" htmlEscape="false" maxlength="50" class="required userName"/>
			</div>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label"><spring:message code='str1290'/>:</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="no" htmlEscape="false" maxlength="50" class="required"/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label"><spring:message code='str1196'/>:</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1546'/>:</label>
			<div class="controls">
				<input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" class="${empty user.id?'required':''}"/>
				<c:if test="${not empty user.id}"><span class="help-inline"><spring:message code='str1550'/></span></c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1551'/>:</label>
			<div class="controls">
				<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" equalTo="#newPassword"/>
			</div>
		</div>
		<div class="control-group" hidden="hidden">
			<label class="control-label"><spring:message code='str1179'/>:</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="100" class="email"/>
			</div>
		</div>
		<div class="control-group" hidden="hidden">
			<label class="control-label"><spring:message code='str1177'/>:</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group" hidden="hidden">
			<label class="control-label"><spring:message code='str1197'/>:</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str729'/>:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<%--<div class="control-group" hidden="hidden">--%>
			<%--<label class="control-label"><spring:message code='str1293'/>:</label>--%>
			<%--<div class="controls">--%>
				<%--<form:select path="userType">--%>
					<%--<form:option value="" label="<spring:message code='str627'/>"/>--%>
					<%--<form:options items="${fns:getDictList('sys_user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
				<%--</form:select>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1594'/>:</label>
			<div class="controls">
				<form:checkboxes path="roleIdList" items="${allRoles}" itemLabel="name" itemValue="id" htmlEscape="false" class="required"/>
			</div>
		</div>
		<c:if test="${not empty user.id}">
			<div class="control-group">
				<label class="control-label"><spring:message code='str101'/>:</label>
				<div class="controls">
					<label class="lbl"><fmt:formatDate value="${user.createDate}" type="both" dateStyle="full"/></label>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><spring:message code='str1595'/>:</label>
				<div class="controls">
					<label class="lbl">IP: ${user.loginIp}&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code='str709'/><fmt:formatDate value="${user.loginDate}" type="both" dateStyle="full"/></label>
				</div>
			</div>
		</c:if>
		<div class="form-actions">
			<shiro:hasPermission name="sys:user:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1176'/> <spring:message code='str1177'/>"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
