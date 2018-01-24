<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='menu.manager'/></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
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
		<li><a href="${ctx}/sys/menu/"><spring:message code='str1552'/></a></li>
		<li class="active"><a href="${ctx}/sys/menu/form?id=${menu.id}&parent.id=${menu.parent.id}"><spring:message code='str1421'/><shiro:hasPermission name="sys:menu:edit"><c:if test="${not empty menu.id }"><spring:message code='str1365'/></c:if><c:if test="${ empty menu.id }"> <spring:message code='operation.add'/></c:if></shiro:hasPermission><shiro:lacksPermission name="sys:menu:edit"><spring:message code='str872'/></shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="menu" action="${ctx}/sys/menu/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1563'/>:</label>
			<div class="controls">
                <tags:treeselect id="menu" name="parent.id" value="${menu.parent.id}" labelName="parent.name" labelValue="${menu.parent.name}"
					title="<spring:message code='str1146'/>" url="/sys/menu/treeData" extId="${menu.id}" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1554'/>:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
        <div class="control-group">
            <label class="control-label"><spring:message code='str1564'/>Key:</label>
            <div class="controls">
                <form:input path="internationalKey" htmlEscape="false" maxlength="50" class="required"/>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1555'/>:</label>
			<div class="controls">
				<form:input path="href" htmlEscape="false" maxlength="200"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1565'/>:</label>
			<div class="controls">
				<form:input path="target" htmlEscape="false" maxlength="10"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1566'/>:</label>
			<div class="controls">
				<tags:iconselect id="icon" name="icon" value="${menu.icon}"></tags:iconselect>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1556'/>:</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="50" class="required digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1562'/>:</label>
			<div class="controls">
				<form:radiobuttons path="isShow" items="${fns:getDictList('show_hide')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label"><spring:message code='str1154'/>:</label>--%>
			<%--<div class="controls">--%>
				<%--<form:radiobuttons path="isActiviti" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1557'/>:</label>
			<div class="controls">
				<form:input path="permission" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:menu:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1176'/> <spring:message code='str1177'/>"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
