<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1440'/></title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tools/monitorConfig/monitorConfig"><spring:message code='str1441'/></a></li>
	</ul><br/>
	<form:form id="searchForm" action="${ctx}/tools/monitorConfig/addMonitorConfig" method="post" class="form-horizontal">
		<tags:message content="${message}"/>
		<input value="${monitorConfig.id}" name="currentId" type="hidden"/>
		<div class="control-group" >
			<label class="control-label"><spring:message code='str1442'/>:</label>
			<div class="controls">
                <input name="warnLogin" value="${monitorConfig.warn_login}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1443'/>:</label>
			<div class="controls">
                <input name="warnCharge" value="${monitorConfig.warn_charge}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"></label>
			<div class="controls">
                <input type="submit" value="<spring:message code='str1444'/>"/>
			</div>
		</div>
	</form:form>
</body>
</html>
