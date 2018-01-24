<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str165'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/game/guildLog/getGuildList"><spring:message code='str155'/></a></li>
		<li class="active"><a href="${ctx}/game/guildLog/membersList"><spring:message code='str165'/></a></li>
	</ul>
    <form id="searchForm" action="${ctx}/game/guildLog/membersList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label><spring:message code='str2'/></label>
		<input id="roleName" name="roleName" type="text" value="${paramMap.roleName}"/>
	    <label><spring:message code='str180'/></label>
	    <input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	    <input type="hidden" name="guildId" value="${guildId }"/>
	</form>
	<tags:message content="${message}"/>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th><spring:message code='str138'/>ID</th><th><spring:message code='str7'/></th><th><spring:message code='str181'/></th><th><spring:message code='str182'/></th>	<th><spring:message code='str128'/></th><th><spring:message code='str183'/></th></tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list }" var="item">
			<tr>
				<td>${item.roleId }</td>
				<td>${item.roleName }</td>
				<td>${item.position ==0?"族长":item.position ==1?"副族长":item.position ==2?"长老":"普通成员" }</td>
				<td>${item.addTime}</td>
				<td>${item.modifyTime}</td>
				<td>${item.gongxian }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
