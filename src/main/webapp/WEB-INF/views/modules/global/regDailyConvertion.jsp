<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str585'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/global/basicOperation/regConvertionTotal"><spring:message code='str585'/></a></li>
		<li class="active"><a href="${ctx}/global/basicOperation/regDailyConvertion"><spring:message code='str587'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/basicOperation/regDailyConvertion" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker">
		<label><spring:message code='str55'/></label>
	    <input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/>
		<%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();">--%>
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th><spring:message code='str198'/></th> <th><spring:message code='str580'/></th> <th><spring:message code='str581'/>Flash<spring:message code='str582'/></th> <th><spring:message code='str583'/></th><th><spring:message code='str584'/></th>  <th><spring:message code='str536'/></th>
			<th><spring:message code='str538'/></th><th><spring:message code='str539'/></th> <th><spring:message code='str540'/></th> <th><spring:message code='str541'/></th> <th><spring:message code='str542'/></th> <th><spring:message code='str543'/></th><th><spring:message code='str544'/></th> 
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list }" var="item">
			<tr>
			    <td>${date }</td>
				<td>${item.step1}</td>
				<td>${item.step2}</td>
				<td>${item.step3}</td>
				<td>${item.step4}</td>

				<td>${item.visit_times}</td>
				<td>${item.createButton}</td>
				<td>${item.createRole}</td>
				<td><fmt:formatNumber value="${item.create_role_rate *100}" pattern="#0.00" />% </td>
				<td>${item.firststage}</td>
				<td><fmt:formatNumber value="${item.login_rate *100}" pattern="#0.00" />% </td>
				<td>${item.nameBox}</td>
				<td>${item.nameBuggon}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>

	<script type="text/javascript">
		$(document).ready(function(){
			$('table.highchart').highchartTable();
		});
	</script>

</body>
</html>
