<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1358'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>


</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tools/gamePlatform/"><spring:message code='str1359'/></a></li>
		<li class="active"><a href="${ctx}/tools/gamePlatform/form?id=${gamePlatform.id}"><spring:message code='str56'/><c:if test="${not empty gamePlatform.id }"> <spring:message code='str1365'/></c:if>
		  <c:if test="${ empty gamePlatform.id }"> <spring:message code='operation.add'/></c:if></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="gamePlatform" action="${ctx}/tools/gamePlatform/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1360'/>:</label>
			<div class="controls">
				<form:input path="pid" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"><spring:message code='str230'/>:</label>
            <div class="controls">
                <input id="oldName" name="oldName" type="hidden" value="${gamePlatform.name}">
                <form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
            </div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1361'/></label>
			<div class="controls">
				<form:input path="signKey" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1215'/>:</label>
			<div class="controls">
                <form:textarea path="description" htmlEscape="false" rows="3" maxlength="100" class="input-xlarge"/>
			</div>
		</div>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1176'/> <spring:message code='str1177'/>"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
