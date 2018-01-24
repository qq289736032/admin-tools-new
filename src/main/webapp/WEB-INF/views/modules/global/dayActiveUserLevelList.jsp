<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str303'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/dayActiveLevel/dayActiveUserLevelList"><spring:message code='str303'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/dayActiveLevel/dayActiveUserLevelList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" /> 
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input-small" maxlength="20" readonly="readonly" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd" class="input-small" readonly="readonly" value="${paramMap.createDateEnd}" id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		<label><spring:message code='str55'/></label>
		<input id="serverIds" name="serverIds" value="${paramMap.serverIds}" readonly/>
        <input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();" />
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th><spring:message code='str304'/></th> <th><spring:message code='str305'/></th></tr>
		</thead>
		<tbody>
		<c:forEach items="${dayActiveUserLevel}" var="item">
			<c:if test="${item.level=='level01' }"><tr><td>1~10<spring:message code='str306'/></td><td>${item.cou}</td></tr></c:if>
			<c:if test="${item.level=='level02' }"><tr><td>11~20<spring:message code='str306'/></td><td>${item.cou}</td></tr></c:if>
			<c:if test="${item.level=='level03' }"><tr><td>21~30<spring:message code='str306'/></td><td>${item.cou}</td></tr></c:if>
			<c:if test="${item.level=='level04' }"><tr><td>31~40<spring:message code='str306'/></td><td>${item.cou}</td></tr></c:if>
			<c:if test="${item.level=='level05' }"><tr><td>41~50<spring:message code='str306'/></td><td>${item.cou}</td></tr></c:if>
			<c:if test="${item.level=='level06' }"><tr><td>51~60<spring:message code='str306'/></td><td>${item.cou}</td></tr></c:if>
			<c:if test="${item.level=='level07' }"><tr><td>61~70<spring:message code='str306'/></td><td>${item.cou}</td></tr></c:if>
			<c:if test="${item.level=='level08' }"><tr><td>71~80<spring:message code='str306'/></td><td>${item.cou}</td></tr></c:if>
			<c:if test="${item.level=='level09' }"><tr><td>81~90<spring:message code='str306'/></td><td>${item.cou}</td></tr></c:if>
			<c:if test="${item.level=='level10' }"><tr><td>91~100<spring:message code='str306'/></td><td>${item.cou}</td></tr></c:if>
			<c:if test="${item.level=='level11' }"><tr><td>101~110<spring:message code='str306'/></td><td>${item.cou}</td></tr></c:if>
			<c:if test="${item.level=='level12' }"><tr><td>111~120<spring:message code='str306'/></td><td>${item.cou}</td></tr></c:if>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>
