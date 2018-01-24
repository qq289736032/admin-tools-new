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
		<li class="active"><a href="${ctx}/global/onlineTime/onlineTimeReport"><spring:message code='str456'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/onlineTime/onlineTimeReport" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
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
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str456'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<thead>
					<tr><th><spring:message code='str198'/></th> <th><spring:message code='str85'/></th> <th><spring:message code='str341'/></th> <th><spring:message code='str457'/>(<spring:message code='str458'/>)</th> <th><spring:message code='str459'/></th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${onlineTime}" var="item">
						<tr>
							<td>${item.log_day}</td>
							<td>${item.level}</td>
							<td>${item.num}</td>
							<td><fmt:formatNumber value="${item.tot/60000}" pattern="0.00"/></td>
							<td><fmt:formatNumber value="${item.dt/60000}" pattern="0.00"/></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			</div>
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str460'/></div>
				<div style="height: 300px;line-height: 300px">
					<div  class="highchart-userRate" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-userRate" data-graph-type="pie" data-graph-pie-show-in-legend="1" data-graph-datalabels-enabled="1" style="display: none">
						<caption><spring:message code='str461'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str462'/></th>
							<th><spring:message code='str463'/></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${onlineTimeArea}" var="item">
							<tr>
								<td>${item.time}<spring:message code='str458'/></td>
								<td   data-graph-name='${item.time} <spring:message code='str458'/> : <fmt:formatNumber type="percent" value="${item.num/sumNum}"/>'><fmt:formatNumber type="percent" value="${item.num/sumNum}"/></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading"><spring:message code='str464'/></div>
			<div style="height: 300px;line-height: 300px">
				<div  class="highchart-onLineTimeLevel" style="height: 300px"></div>
				<table class="highchart table"
					   data-graph-container=".highchart-onLineTimeLevel" data-graph-type="line"
					   data-graph-yaxis-1-title-text = '<spring:message code='str236'/>(<spring:message code='str458'/>)' data-graph-zoom-type="xy" style="display: none">
					<caption><spring:message code='str464'/></caption>
					<thead>
					<tr>
						<th><spring:message code='str85'/></th>
						<th data-graph-type="column"><spring:message code='str465'/></th>
						<th data-graph-type="line"><spring:message code='str466'/></th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${onlineTimeLevel}" var="item">
						<tr>
							<td>${item.level}</td>
							<td>${item.sum_tot/60000}</td>
							<td><fmt:formatNumber value="${item.per_dt/60000}" pattern="0.00"/> <spring:message code='str458'/></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading"><spring:message code='str467'/></div>
			<div style="height: 300px;line-height: 300px">
				<div  class="highchart-onLineTimeUserNum" style="height: 300px"></div>
				<table class="highchart table"
					   data-graph-container=".highchart-onLineTimeUserNum" data-graph-type="column"
					   data-graph-yaxis-1-title-text = '<spring:message code='str462'/>' data-graph-zoom-type="xy" style="display: none">
					<caption><spring:message code='str467'/></caption>
					<thead>
					<tr>
						<th><spring:message code='str3'/></th>
						<th><spring:message code='str468'/></th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${onlineTimeArea}" var="item">
						<tr>
							<td>${item.time}<spring:message code='str458'/></td>
							<td>${item.num}<spring:message code='str469'/></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div> 
	

	
	<script type="text/javascript">
		$(document).ready(function(){
			$('table.highchart').highchartTable();
		});
	</script>

</body>
</html>
