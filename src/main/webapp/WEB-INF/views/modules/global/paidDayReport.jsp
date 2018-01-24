<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str307'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/paidDay/paidView"><spring:message code='str1694'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/paidDay/paidView" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		<spring:message code='str1692'/><input type="text" name="startAmount" class="input_search" size="10" maxlength="20" value="${paramMap.startAmount}" id="startAmount">
		~&nbsp;<input type="text" name="endAmount"   class="input_search" size="10" value="${paramMap.endAmount}"   id="endAmount">
		<%-- <label><spring:message code='str55'/></label> --%>
	    <%-- <input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/> --%>
		<%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();">--%>
		<%-- <input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/log/paidUserRetain/paidView')"> --%>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	<div class="panel panel-primary">
	<div class="panel-heading"><spring:message code='str1694'/></div>
	<table id="newRegisterRemained" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th><spring:message code='str198'/></th> <th><spring:message code='str1691'/></th> <th>1</th> <th>2</th> <th>3</th>
			<th>4</th> <th>5</th> <th>6</th> <th>7</th> <th>8</th>
			<th>9</th> <th>10</th> <th>11-15</th> <th>16-20</th> <th>21-30</th> <th>31+</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${apaDayActive}" var="item">
			<tr>
				<td>${item.order_time_format}</td>
				<td>${item.num}</td>
				<td>${item.loginDay_1 }</td>
				<td>${item.loginDay_2 }</td>
				<td>${item.loginDay_3 }</td>
				<td>${item.loginDay_4 }</td>
				<td>${item.loginDay_5 }</td>
				<td>${item.loginDay_6 }</td>
				<td>${item.loginDay_7 }</td>
				<td>${item.loginDay_8 }</td>
				<td>${item.loginDay_9 }</td>
				<td>${item.loginDay_10 }</td>
				<td>${item.loginDay_11_15 }</td>
				<td>${item.loginDay_16_20 }</td>
				<td>${item.loginDay_21_30 }</td>
				<td>${item.loginDay_31 }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>
