<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str52'/>R<spring:message code='str53'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/game/daZhongRMonitor/daZhongRMonitorReport"><spring:message code='str52'/>R<spring:message code='str53'/> </a></li>
	</ul>
	<form id="searchForm" action="${ctx}/game/daZhongRMonitor/daZhongRMonitorReport" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label><spring:message code='str54'/> <spring:message code='str4'/></label>
		<select name="amount">
			<option value="10" <c:if test="${paramMap.amount == 10}">selected="selected" </c:if>>10+</option>
			<option value="100" <c:if test="${paramMap.amount == 100}">selected="selected" </c:if>>100+</option>
			<option value="500" <c:if test="${paramMap.amount == 500}">selected="selected" </c:if>>500+</option>
			<option value="1000" <c:if test="${paramMap.amount == 1000}">selected="selected" </c:if>>1000+</option>
			<option value="5000" <c:if test="${paramMap.amount == 5000}">selected="selected" </c:if>>5000+</option>
			<option value="10000" <c:if test="${paramMap.amount == 10000}">selected="selected" </c:if>>10000+</option>
			<option value="20000" <c:if test="${paramMap.amount == 20000}">selected="selected" </c:if>>20000+</option>
			<option value="50000" <c:if test="${paramMap.amount == 50000}">selected="selected" </c:if>>50000+</option>
			<option value="100000" <c:if test="${paramMap.amount == 100000}">selected="selected" </c:if>>100000+</option>
		</select>
		<!--<label><spring:message code='str55'/></label>
	    <input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/>
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();">-->
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th><spring:message code='str56'/></th> <th><spring:message code='str14'/></th> <th><spring:message code='str57'/>ID</th> <th><spring:message code='str7'/></th> <th><spring:message code='str54'/></th>
			<th><spring:message code='str58'/></th> <th><spring:message code='str59'/></th> <th><spring:message code='str60'/></th> <th><spring:message code='str61'/></th> <th><spring:message code='str62'/></th>
			<th><spring:message code='str63'/></th> <th><spring:message code='str64'/></th> <th><spring:message code='str65'/></th> <th><spring:message code='str66'/></th> <th><spring:message code='str67'/></th></tr>
		</thead>
		<tbody>
		<c:forEach items="${daZhongRMonitor.list}" var="item">
			<tr>
				<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
				<td>${item.area_id}</td>
				<td>${item.user_id}</td>
				<td>${item.role_name}</td>
				<td><fmt:formatNumber value="${item.amount}" pattern="0.00"/></td>

				<td><fmt:formatDate value="${item.reg_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${item.first_pay_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${item.last_pay_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${item.sub_reg_time}</td>
				<td>${item.pay_times}</td>

				<td>${item.surplus_coin}</td>
				<td>${item.sub_last_login_time}</td>
				<td>
					<c:choose>
						<c:when test="${item.sub_last_login_time>warnLogin}"><span class="label label-important"><spring:message code='str68'/></span></c:when>
						<c:otherwise><span class="label label-info"><spring:message code='str69'/></span></c:otherwise>
					</c:choose>
				</td>
				<td>${item.sub_last_pay_time}</td>
				<td>
					<c:choose>
						<c:when test="${item.sub_last_pay_time>warnCharge}"><span class="label label-important"><spring:message code='str68'/></span></c:when>
						<c:otherwise><span class="label label-info"><spring:message code='str69'/></span></c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${daZhongRMonitor}</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('table.highchart').highchartTable();
		});
	</script>

</body>
</html>
