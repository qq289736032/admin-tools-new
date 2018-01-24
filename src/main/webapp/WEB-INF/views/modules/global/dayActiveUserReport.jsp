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
		<li class="active"><a href="${ctx}/global/dashboard/dayActiveUserReport"><spring:message code='str307'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/dashboard/dayActiveUserReport" method="post" class="breadcrumb form-search">
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
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str307'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<thead>
					<tr><th><spring:message code='str198'/></th> <th><spring:message code='str308'/></th> <th><spring:message code='str309'/></th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${dayActive}" var="item">
						<tr>
							<td>${item.log_day}</td>
							<td>${item.sum_servernum}</td>
							<td>${item.sum_dau}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				</div>
				<table class="table table-striped table-bordered table-condensed">
					<tr>
						<td><spring:message code='str193'/>:</td>
						<td><spring:message code='str310'/>:${createDateStart}<spring:message code='str311'/>${createDateEnd}</td>
						<td><spring:message code='str312'/>:${betweenDays}<spring:message code='str313'/></td>
						<td><spring:message code='str308'/>:${serverNum}<spring:message code='str314'/></td>
						<td><spring:message code='str315'/>:${activeNum}</td>
					</tr>
				</table>
			</div>
			
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str316'/></div>
				<div class="" style="width:100%;height:325px;line-height:300px;overflow:auto;overflow-x:hidden;">
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<thead>
					<tr><th><spring:message code='str198'/></th> <th><spring:message code='str230'/> </th> <th><spring:message code='str14'/> </th><th><spring:message code='str317'/> </th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${dayServerActive}" var="item">
						<tr>
							<td>${item.log_day}</td>
							<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
							<td>${item.area_id}</td>
							<td>${item.dau}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str318'/></div>
				<div style="height: 300px;line-height: 300px">
					<div  class="highchart-daysActive" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-daysActive" data-graph-type="column" data-graph-xaxis-reversed="1"
						   data-graph-yaxis-1-title-text = '<spring:message code='str206'/>' data-graph-zoom-type="xy" style="display: none">
						<caption><spring:message code='str319'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str198'/></th>
							<th data-graph-type="line"><spring:message code='str206'/></th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${dayActive}" var="item">
								<tr>
									<td>${item.log_day}</td>
									<td>${item.sum_dau}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str320'/></div>
				<div style="height: 300px;line-height: 300px">
					<div class="highchart-weekComActive" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-weekComActive" data-graph-type="line" data-graph-xaxis-reversed="1"
						   data-graph-yaxis-1-title-text = '<spring:message code='str223'/>' style="display: none">
						<caption><spring:message code='str321'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str322'/></th>
							<th><spring:message code='str323'/></th>
							<th><spring:message code='str324'/></th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${weekComparison}" var="item">
								<tr>
									<td>${item.log_day}</td>
									<td>${item.now_sum_dau}</td>
									<td>${item.bef_sum_dau}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div> 
	
	<div class="container-fluid">
		<div class="row-fluid">
				<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str325'/></div>
				<div style="height: 300px;line-height: 300px">
					<div class="highchart-monthActive" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-monthActive" data-graph-type="line" data-graph-xaxis-reversed="1"
						   data-graph-yaxis-1-title-text = '<spring:message code='str223'/>' style="display: none">
						<caption><spring:message code='str326'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str198'/></th>
							<th><spring:message code='str327'/></th>
							<th><spring:message code='str328'/></th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${monthComparison}" var="item">
								<tr>
									<td>${item.log_day}</td>
									<td>${item.now_sum_dau}</td>
									<td>${item.bef_sum_dau}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
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
