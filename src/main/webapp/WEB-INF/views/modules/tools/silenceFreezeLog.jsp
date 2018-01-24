<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str820'/></title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/game/role/silenceFreezeLog"><spring:message code='str823'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/game/role/silenceFreezeLog" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label><spring:message code='str2'/></label>
		<input type="text" id="roleName" name="roleName"  class="input-small" value="${paramMap.roleName}"/>
		<label><spring:message code='str6'/> <spring:message code='str4'/></label>
		<input type="text" id="roleId" name="roleId"  class="input-small" value="${paramMap.roleId}"/>
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">		
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr><th><spring:message code='str88'/></th><th><spring:message code='str6'/></th><th><spring:message code='str653'/></th><th style="display: none;"><spring:message code='str147'/></th><th style="display: none;"><spring:message code='str148'/></th><th><spring:message code='str824'/></th><th><spring:message code='str825'/></th><th><spring:message code='str826'/></th><th><spring:message code='str78'/></th><th><spring:message code='str827'/></th><th><spring:message code='str3'/></th></tr>
		<c:forEach items="${page.list}" var="item">
			<tr>
				<td>${item.userId}</td>
				<td>${item.roleId}</td>
				<td>${item.roleName}</td>
				<td style="display: none"><c:choose>
				      <c:when test="${item.isjinyan eq false}"><span class="label label-info"><spring:message code='str1379'/></span></c:when>
				      <c:otherwise><span class="label label-warning"><spring:message code='str98'/></span></c:otherwise>
				     </c:choose>
				 </td>
				<td style="display: none"><c:choose>
				      <c:when test="${item.isfenghao eq false}"><span class="label label-info"><spring:message code='str1379'/></span></c:when>
				      <c:otherwise><span class="label label-warning"><spring:message code='str98'/></span></c:otherwise>
				     </c:choose>
				</td>
				<td>${item.serverId}</td>
				<td>
					<c:if test="${item.operationType eq 0}"><span class="badge badge-success"><spring:message code='str139'/></span></c:if>
					<c:if test="${item.operationType eq 1}"><span class="badge badge-warning"><spring:message code='str141'/></span></c:if>
					<c:if test="${item.operationType eq 3}"><span class="label label-info"><spring:message code='str140'/></span></c:if>
					<c:if test="${item.operationType eq 4}"><span class="label label-success"><spring:message code='str142'/></span></c:if>
				</td>
				<td>${item.msg}</td>
				<td>${item.expireTime}</td>
				<td>${item.createName}</td>
                <td><fmt:formatDate value="${item.createDate}" type="both"/></td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
