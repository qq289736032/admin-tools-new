<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str339'/></title>
	<meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<script src="${ctxStatic}/common/fixtable.js" type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/mapRoleOnline/list"><spring:message code='str339'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/mapRoleOnline/list" method="post" class="breadcrumb form-search" hidden="true">
		<label><spring:message code='str55'/></label>
	    <input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/>
		<%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();">--%>
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>

	<table id="active-yb" class="table table-striped table-bordered table-condensed">
		<tr><th><spring:message code='str1687'/></th><th colspan="2" style="color:red">${total}</th></tr>
		<tr><th><spring:message code='str340'/></th><th><spring:message code='str341'/></th><th><spring:message code='str226'/>(%)</th></tr>
		<c:forEach items="${list }" var="item">
			<tr>
				<td>${item.mapName }</td>
				<td>${item.num }</td>
				<td <c:if test="${item.num*100/total > 100 }">style="color: red;font-weight:bold" </c:if>><c:if test="${total >0 }"><fmt:formatNumber value="${item.num*100/total }" pattern="#0.00"/>%</c:if>
				    <c:if test="${total ==0 }">0</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
