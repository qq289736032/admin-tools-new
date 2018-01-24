<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str558'/></title>
	<meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<script src="${ctxStatic}/common/fixtable.js" type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/tradeController/propSale?stype=0"><spring:message code='str558'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/tradeController/propSale?stype=0" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"value="${page.pageSize}" />
		
		<label><spring:message code='str185'/></label>
	    <input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		<label><spring:message code='str55'/></label>
	    <input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/>
		<%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();">--%>
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>

	<table id="active-yb" class="table table-striped table-bordered table-condensed">
		<tr><th><spring:message code='str187'/></th><th><spring:message code='str437'/></th><th><spring:message code='str438'/></th><th><spring:message code='str439'/>ID</th><th><spring:message code='str440'/></th><th><spring:message code='str441'/></th><th><spring:message code='str226'/>(%)</th>
			<th><spring:message code='str442'/></th><th><spring:message code='str191'/></th><th><spring:message code='str262'/></th><th><spring:message code='str443'/></th><th><spring:message code='str444'/></th><th><spring:message code='str445'/></th><th><spring:message code='str1695'/></th></tr>
		<c:forEach items="${page.list}" var="item">
			<tr>
				<td>${createDateStart } ~ ${createDateEnd }</td>
				<td>${item.type }</td>
				<td>${item.itemName }</td>
				<td>${item.itemId }</td>
				<td>${item.itemNum }</td>
				<td><fmt:formatNumber value="${item.amount/100}" pattern="#0.00"/></td>
				<td <c:if test="${item.amount*100/total > 100 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.amount*100/total }" pattern="#0.00"/> %</td>
				<td><fmt:formatNumber value="${item.consuYb/100}" pattern="#0.00"/></td>
				<td>${item.ybPopulation }</td>
				<td>${item.ybNum }</td>
				<td><fmt:formatNumber value="${item.bindYb}" pattern="#0.00"/></td>
				<td>${item.bindPopulation }</td>
				<td>${item.bindNum }</td>
				<td>${item.operateName }</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
