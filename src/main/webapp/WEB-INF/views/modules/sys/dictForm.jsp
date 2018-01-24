<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1122'/></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
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
		<li><a href="${ctx}/sys/dict/"><spring:message code='str1579'/></a></li>
		<li class="active"><a href="${ctx}/sys/dict/form?id=${dict.id}"><spring:message code='str1584'/><shiro:hasPermission name="sys:dict:edit"> <c:if test="${not empty dict.id }"> <spring:message code='str1365'/></c:if>
			  <c:if test="${ empty dict.id }"> <spring:message code='operation.add'/></c:if></shiro:hasPermission><shiro:lacksPermission name="sys:dict:edit"><spring:message code='str872'/></shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dict" action="${ctx}/sys/dict/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1581'/>:</label>
			<div class="controls">
				<form:input path="value" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1582'/>:</label>
			<div class="controls">
				<form:input path="label" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str700'/>:</label>
			<div class="controls">
				<form:input path="type" htmlEscape="false" maxlength="50" class="required abc"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1407'/>:</label>
			<div class="controls">
				<input name="description" htmlEscape="false" maxlength="50" class="required" value="${fns:urlDecode(dict.description)}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1556'/>:</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="11" class="required digits"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:dict:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1176'/> <spring:message code='str1177'/>"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
