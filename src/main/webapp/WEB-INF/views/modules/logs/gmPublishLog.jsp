<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str820'/></title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/game/role/gmList/"><spring:message code='str821'/></a></li>
		<shiro:hasPermission name="game.gm.edit">
			<li><a href="${ctx}/game/role/gmForm"><spring:message code='str822'/></a></li>
		</shiro:hasPermission>
		<li class="active"><a href="${ctx}/game/role/gmPublish">GM<spring:message code='str823'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/game/role/gmPublish" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label><spring:message code='str2'/></label>
		<input type="text" id="roleName" name="roleName"  class="input-small" value="${paramMap.roleName}"/>
		<label><spring:message code='str6'/> <spring:message code='str4'/></label>
		<input type="text" id="roleId" name="roleId"  class="input-small" value="${paramMap.roleId}"/>
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input name="createDateStart" id="createDateStart" maxlength="50"  value="${paramMap.createDateStart}" class="input-small required" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>
		-
		<input name="createDateEnd" maxlength="50"  class="input-small required" value="${paramMap.createDateEnd}"  readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'createDateStart\')}'})"/>

		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr><th><spring:message code='str88'/></th><th><spring:message code='str6'/></th><th><spring:message code='str653'/></th><th><spring:message code='str147'/></th><th><spring:message code='str148'/></th><th><spring:message code='str824'/></th><th><spring:message code='str825'/></th><th><spring:message code='str826'/></th><th><spring:message code='str827'/></th><th><spring:message code='str3'/></th></tr>
		<c:forEach items="${page.list}" var="item">
			<tr>
				<td>${item.userId}</td>
				<td>${item.roleId}</td>
				<td>${item.roleName}</td>
				<td>
					<c:if test="${item.isjinyan eq 0 }"><span class="label label-info"><spring:message code='str828'/></span></c:if>
					<c:if test="${item.isjinyan eq 1 }"><span class="label label-success"><spring:message code='str829'/></span></c:if>
				</td>
				<td>
					<c:if test="${item.isfenhao eq 0 }"><span class="label label-info"><spring:message code='str830'/></span></c:if>
					<c:if test="${item.isfenhao eq 1 }"><span class="label label-success"><spring:message code='str831'/></span></c:if>
				</td>
				<td>${item.serverId}</td>
				<td>
					<c:if test="${item.operationType eq 0}"><spring:message code='str139'/></c:if>
					<c:if test="${item.operationType eq 1}"><spring:message code='str141'/></c:if>
					<c:if test="${item.operationType eq 2}"><spring:message code='str140'/></c:if>
					<c:if test="${item.operationType eq 3}"><spring:message code='str142'/></c:if>
				</td>
				<td>${item.msg}</td>
				<td>${item.createName}</td>
                <td>${fns:parseLong(item.createDate)}</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
