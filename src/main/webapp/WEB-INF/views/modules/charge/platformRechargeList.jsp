<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str503'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#s2id_pids").hide();
		$("#pids").multiselect({
			includeSelectAllOption:true,
			enableFiltering:true
		});
	});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/charge/platform/platformRechargeList"><spring:message code='str503'/></a></li>

	</ul>
	<form id="searchForm" action="${ctx}/charge/platform/platformRechargeList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<label><spring:message code='str709'/></label>
		<input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		<label><spring:message code='platform'/><spring:message code='str4'/></label>

		<select id="pids" name="pids" multiple="multiple">
			<c:forEach items="${fns:getGamePlatformList()}" var="item">
				<c:choose>
					<c:when test="${fn:length(selectedPids)==0}">
						<option value="${item.pid}" >${item.name}</option>
					</c:when>
					<c:otherwise>
					<option value="${item.pid}" 
						<c:forEach items="${selectedPids}" var="pid">
							<c:if test="${pid==item.pid}">
								selected="selected"
							</c:if>
						</c:forEach>
					>${item.name}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
		
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th><spring:message code='platform'/></th><th><spring:message code='str273'/></th></tr>
		</thead>
		<tbody>
		<c:forEach items="${platformRecharge}" var="item">
			<tr>
				<td>${item.platform}</td>
				<td><fmt:formatNumber value="${item.amount}" pattern="0.00"></fmt:formatNumber></td>
			</tr>
		</c:forEach>
		</tbody>
		<tr>
			<th><spring:message code='str193'/></th>
			<th><c:if test="${platformRechargetotal[0].total==null }">0.00</c:if>
			<c:if test="${platformRechargetotal[0].total!=null }"><fmt:formatNumber value="${platformRechargetotal[0].total}" pattern="0.00"></fmt:formatNumber></c:if>
			</th>
		</tr>
	</table>
	<div class="panel panel-primary">
			<div class="panel-heading"><spring:message code='str1597'/></div>
			<div style="height: 300px;line-height: 300px">
				<div  class="highchart-onLineTimeUserNum" style="height: 300px"></div>
				<table class="highchart table"
					   data-graph-container=".highchart-onLineTimeUserNum" data-graph-type="column"
					   data-graph-yaxis-1-title-text = '<spring:message code='str273'/>' data-graph-zoom-type="xy" style="display: none">
					<caption><spring:message code='str1597'/></caption>
					<thead>
					<tr>
						<th></th>
						<th><spring:message code='str273'/></th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${platformRecharge}" var="item">
						<tr>
							<td>${item.platform}<spring:message code='platform'/></td>
							<td><fmt:formatNumber value="${item.amount}" pattern="0.00"></fmt:formatNumber><spring:message code='str273'/></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<script type="text/javascript">
		$(document).ready(function(){
			$('table.highchart').highchartTable();
		});
	</script>
</body>
</html>
