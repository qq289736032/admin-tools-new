<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>IP<spring:message code='str999'/></title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/log/roleLogin/"><spring:message code='str1000'/></a></li>
		<li class="active"><a href="${ctx}/log/roleLogin/roleLoginIp/">IP<spring:message code='str999'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/roleLogin/roleLoginIp/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>IP<spring:message code='str1001'/> <spring:message code='str4'/></label>
		<input type="text" id="ip" name="ip"  class="input-small" value="${paramMap.ip}"/>
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input-small" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input-small" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">		
		
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr><th><spring:message code='str88'/></th><th><spring:message code='str6'/></th><th><spring:message code='str653'/></th><th><spring:message code='str14'/></th></tr>
		<c:forEach items="${page.list}" var="item">
			<tr>
				<td>${item.userId}</td>
				<td>${item.roleId}</td>
				<td>${item.roleName}</td>
				<td>${item.serverId}</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
