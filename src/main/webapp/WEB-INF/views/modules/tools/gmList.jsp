<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1400'/></title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/game/role/gmList"><spring:message code='str821'/></a></li>
		<shiro:hasPermission name="game.gm.edit">
			<li><a href="${ctx}/game/role/gmForm"><spring:message code='str822'/></a></li>
		</shiro:hasPermission>
		<li><a href="${ctx}/game/role/gmPublish">GM<spring:message code='str823'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/game/role/gmList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label><spring:message code='str2'/></label>
		<input type="text" id="roleName" name="roleName"  class="input-small" value="${paramMap.roleName}"/>
		<label><spring:message code='str1401'/></label>
		<select id="roleType" name="roleType">
			<option value="" name="roleType"><spring:message code='str627'/></option>
			<c:forEach items="${fns:getDictList('role_type')}" var="roleType">
				<option value="${roleType.value}" name="roleType"><spring:message code="${roleType.internationalKey}"/></option>
			</c:forEach>
		</select>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input-small" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input-small" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">		
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr><th><spring:message code='str1363'/></th><th><spring:message code='str824'/></th><th><spring:message code='str14'/></th><th><spring:message code='str7'/></th><th><spring:message code='str1402'/></th><th><spring:message code='str1403'/></th><th><spring:message code='str101'/></th><th><spring:message code='str149'/></th></tr>
		<c:forEach items="${page.list}" var="item">
			<tr>
				<td>${item.platformId}</td>
				<td>${item.serverId}</td>
				<td>${fns:getGameServer(item.serverId).name}</td>
				<td>${item.roleName}</td>
				<td>
					<spring:message code='${fns:getDictKeys(item.roleType, "role_type",item.roleType)}'/>
				</td>
				<td>${item.createName}</td>
                <td><fmt:formatDate value="${item.createDate}" type="both"/></td>
				<td>
					<shiro:hasPermission name="game.gm.edit">
					<a href="${ctx}/game/role/updateGmForm?id=${item.id}"><spring:message code='str1365'/></a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
