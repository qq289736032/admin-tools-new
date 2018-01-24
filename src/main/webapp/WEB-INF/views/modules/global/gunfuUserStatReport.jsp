<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str329'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/dashboard/gunfuUserStatReport"><spring:message code='str329'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/dashboard/gunfuUserStatReport" method="post" class="breadcrumb form-search">
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
			<th></th>
			<th colspan="8" style="text-align: center;"><spring:message code='str330'/></th>
			<th colspan="8" align="center" style="text-align: center;"><spring:message code='str331'/></th>
		</tr>
		<tr>
		<th><spring:message code='str198'/></th> 
		<th><spring:message code='str201'/></th> 
		<th><spring:message code='str332'/></th> 
		<th><spring:message code='str333'/></th> 
		<th>ACCU</th> 
		<th>DT<spring:message code='str236'/>(<spring:message code='str237'/>)</th>
		<th><spring:message code='str238'/></th> 
		<th><spring:message code='str205'/></th> 
		<th>ARPU</th> 
		<th><spring:message code='str201'/></th> 
		<th><spring:message code='str332'/></th> 
		<th><spring:message code='str333'/></th>
		<th>ACCU</th> 
		<th>DT<spring:message code='str236'/>(<spring:message code='str237'/>)</th> 
		<th><spring:message code='str238'/></th> 
		<th><spring:message code='str205'/></th> 
		<th>ARPU</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="item">
			<tr>
				<td>${item.log_day}</td>
				<td><fmt:formatNumber value="${item.income/100}" pattern="0.00"/></td>
				<td>${item.dru}</td>
				<td>${item.dau}</td>
				<td><fmt:formatNumber value="${item.acu}" pattern="#,###,###,###"/></td>
				<td><fmt:formatNumber value="${item.dt/60000}" pattern="0.00"/></td>
				
				<td>${item.dpa}</td>
				<td><span style="<c:if test="${item.dpa_dau_duplicate > 1  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.dpa_dau_duplicate * 100}" pattern="0.00"/>%</span></td>
				<td><fmt:formatNumber value="${item.income_dpa/100}" pattern="0.00"/></td>
				<td><fmt:formatNumber value="${item.gunfu_income/100}" pattern="0.00"/></td>
				<td>${item.dru_gunfu}</td>
				<td>${item.rdau}</td>
				
				<td>${item.gunfu_acu}</td>
				<td><fmt:formatNumber value="${item.gunfu_dt/60000}" pattern="0.00"/></td>
				<td>${item.gunfu_pa}</td>
				<td><span style="<c:if test="${gunfu_dpa_rdau > 1  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.gunfu_dpa_rdau * 100}" pattern="0.00"/>%</span></td>
				<td><fmt:formatNumber value="${item.gunfu_income_gunfu_dpa/100}" pattern="0.00"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str201'/></div>
				<div style="height: 300px;line-height: 300px">
					<div  class="highchart-daysIncome" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-daysIncome" data-graph-type="column" data-graph-xaxis-reversed="1"
						   data-graph-yaxis-1-title-text = '<spring:message code='str330'/>' data-graph-yaxis-2-title-text = '<spring:message code='str334'/>' 
						   data-graph-zoom-type="xy" data-graph-yaxis-2-opposite="1" style="display: none">
						<caption><spring:message code='str330'/>vs<spring:message code='str334'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str198'/></th>
							<th data-graph-type="line"><spring:message code='str330'/></th>
							<th data-graph-type="line" data-graph-yaxis="1"><spring:message code='str334'/></th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="item">
								<tr>
									<td>${item.log_day}</td>
									<td>${item.income}</td>
									<td>${item.gunfu_income}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str217'/></div>
				<div style="height: 300px;line-height: 300px">
					<div class="highchart-daysActive" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-daysActive" data-graph-type="column" data-graph-xaxis-reversed="1"
						   data-graph-yaxis-1-title-text = '<spring:message code='str330'/>' data-graph-yaxis-2-title-text = '<spring:message code='str334'/>' 
						   data-graph-zoom-type="xy" data-graph-yaxis-2-opposite="1" style="display: none">
						<caption><spring:message code='str330'/>vs<spring:message code='str334'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str198'/></th>
							<th data-graph-type="line"><spring:message code='str330'/></th>
							<th data-graph-type="line" data-graph-yaxis="1"><spring:message code='str334'/></th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="item">
								<tr>
									<td>${item.log_day}</td>
									<td>${item.dru}</td>
									<td>${item.dru_gunfu}</td>
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
				<div class="panel-heading"><spring:message code='str220'/></div>
				<div style="height: 300px;line-height: 300px">
					<div  class="highchart-daysOnlines" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-daysOnlines" data-graph-type="column" data-graph-xaxis-reversed="1"
						   data-graph-yaxis-1-title-text = '<spring:message code='str330'/>' data-graph-yaxis-2-title-text = '<spring:message code='str334'/>' 
						   data-graph-zoom-type="xy" data-graph-yaxis-2-opposite="1" style="display: none">
						<caption><spring:message code='str330'/>vs<spring:message code='str334'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str198'/></th>
							<th data-graph-type="line"><spring:message code='str330'/></th>
							<th data-graph-type="line" data-graph-yaxis="1"><spring:message code='str334'/></th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="item">
								<tr>
									<td>${item.log_day}</td>
									<td>${item.dau}</td>
									<td>${item.rdau}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str238'/></div>
				<div style="height: 300px;line-height: 300px">
					<div class="highchart-daysRecharger" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-daysRecharger" data-graph-type="column" data-graph-xaxis-reversed="1"
						    data-graph-yaxis-1-title-text = '<spring:message code='str330'/>' data-graph-yaxis-2-title-text = '<spring:message code='str334'/>' 
						   data-graph-zoom-type="xy" data-graph-yaxis-2-opposite="1" style="display: none">
						<caption><spring:message code='str330'/>vs<spring:message code='str334'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str198'/></th>
							<th data-graph-type="line"><spring:message code='str330'/></th>
							<th data-graph-type="line" data-graph-yaxis="1"><spring:message code='str334'/></th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="item">
								<tr>
									<td>${item.log_day}</td>
									<td>${item.dpa}</td>
									<td>${item.gunfu_pa}</td>
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
