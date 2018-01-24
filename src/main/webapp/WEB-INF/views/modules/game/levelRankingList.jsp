<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str179'/></title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">                                         
		<li class="active"><a href="${ctx}/game/role/levelRanking/"><spring:message code='str179'/></a></li>
	</ul>                
	<form id="searchForm" action="${ctx}/game/role/levelRanking" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label><spring:message code='str2'/></label>
		<input type="text" id="roleName" name="roleName"  class="input-small" value="${paramMap.roleName}"/>
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	    <input id="btnExport" class="btn btn-primary" type="button" value="<spring:message code='str5'/>" onclick="exportXls('${ctx}/game/role/export')"/>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr><th><spring:message code='str85'/></th><th><spring:message code='str6'/></th><th><spring:message code='str7'/></th><th><spring:message code='str11'/></th></tr>
		<c:forEach items="${page.list}" var="item">
			<tr>
				<td>${item.level}</td>
				<td>${item.id}</td>
				<td>${item.name}</td>
                <td>${item.lastUpgradeTime}</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
