<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str414'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/global/monthRemainer/monthRemainerReport"><spring:message code='str415'/></a></li>
		<li class="active"><a href="${ctx}/global/monthRemainer/monthRemainerReport?isLineChart=1"><spring:message code='str414'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/monthRemainer/monthRemainerReport" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="hidden" id="isLineChart" name="isLineChart" value="1">
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
	<div  class="highchart-daysActive" style="height: 300px"></div>
	<table class="highchart table"
		   data-graph-container=".highchart-daysActive" data-graph-type="spline" data-graph-xaxis-reversed="1"
		   data-graph-yaxis-1-title-text = '<spring:message code='str219'/>' data-graph-zoom-type="xy" style="display: none">
		<caption><spring:message code='str219'/></caption>
		<thead>
		<tr>
			<th><spring:message code='str198'/></th>
			<th data-graph-type="line"><spring:message code='str219'/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${monthRemainer}" var="item">
			<tr>
				<td>${item.log_day}</td>
				<td>${item.sum_30_dru}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('table.highchart').highchartTable();
		});
	</script>

</body>
</html>
