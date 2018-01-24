<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str252'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/coinProduceConsume/coinProduceConsumeReport"><spring:message code='str252'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/coinProduceConsume/coinProduceConsumeReport" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
	
		<label><spring:message code='str55'/></label>
	    <input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/>
		<%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();">--%>
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<th><spring:message code='str198'/></th> 
			<th><spring:message code='str253'/></th> 
			<th><spring:message code='str254'/></th> 
			<th><spring:message code='str255'/></th> 
			<th><spring:message code='str256'/></th> 
			<th><spring:message code='str257'/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${coinProduceConsume.list}" var="item">
			<tr>
				<td>${item.log_day}</td>
				<td><fmt:formatNumber value="${item.sum_pay_coin/100}" pattern="0.00"/></td>
				<td><fmt:formatNumber value="${item.sum_consume_coin/100}" pattern="0.00"/></td>
				<td>${item.all_surplus_coin}</td>
				<td <c:if test="${item.redundance_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.redundance_rate*100}" pattern="0.00"/>%</td>
				<td>${item.all_surplus_coin}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${coinProduceConsume}</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('table.highchart').highchartTable();
		});
	</script>

</body>
</html>
