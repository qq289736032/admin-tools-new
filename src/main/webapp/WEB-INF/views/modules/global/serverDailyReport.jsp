<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str595'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<script type="text/javascript">
	function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").attr("action","${ctx}/global/dashboard/serverDailyReport");
            $("#searchForm").submit();
            return false;
    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/dashboard/serverDailyReport"><spring:message code='str595'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/dashboard/serverDailyReport" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}',minDate:'#F{$dp.$D(\'endDatePicker\',{d:-6})}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}',maxDate:'#F{$dp.$D(\'startDatePicker\',{d:6})}'})">

		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
		&nbsp;<input id="btnExport" class="btn btn-primary" type="button" value="<spring:message code='str5'/>" onclick="doExport('${ctx}/global/dashboard/export')"/>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th><spring:message code='str198'/></th> 
		<th><spring:message code='str201'/></th> 
		<th><spring:message code='str199'/></th> 
		<th><spring:message code='str200'/></th> 
		<th><spring:message code='str235'/></th> 
		<th>ACCU</th> <th>PCCU</th> 
		<th>DT<spring:message code='str236'/>(<spring:message code='str237'/>)</th>
		<th><spring:message code='str238'/></th>
		<th><spring:message code='str275'/></th> 
		<th><spring:message code='str205'/></th> 
		<th>ARPU</th> 
		<th><spring:message code='str220'/>ARPU</th> 
		<th><spring:message code='str239'/></th>
		<th><spring:message code='str370'/></th> 
		<th><spring:message code='str240'/></th> 
		<th><spring:message code='str596'/></th>
		<th><spring:message code='str217'/>3<spring:message code='str597'/></th> 
		<th><spring:message code='str217'/>7<spring:message code='str597'/></th> 
		<th><spring:message code='str598'/></th> 
		<th><spring:message code='str599'/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item">
			<tr>
				<td>${item.log_day}</td>
	<!-- 收入(元)--><td><fmt:formatNumber value=" ${item.income/100}" pattern="#0.00"/></td>
				<td>${item.dru}</td>
				<td>${item.dau}</td>
				<td>${item.dau_sub_dru}</td>
				<td><fmt:formatNumber value="${item.acu}"  pattern="#,###,###,###"></fmt:formatNumber></td>
				<td>${item.pccu}</td>
	 <!-- 时长 --><td><fmt:formatNumber value="${item.dt/60000}"  pattern="#0.00" /></td>

				<td>${item.dpa}</td>
				<td>${item.dp_times}</td>
    <!--付费率 --><td <c:if test="${item.dpa_dau > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.dpa_dau * 100}" pattern="#0.00"/>%</td>
	<!-- ARPU --><td><fmt:formatNumber value="${item.income_pa/100}" pattern="#0.00"/></td> 
	<!-- 活跃ARPU --><td><fmt:formatNumber value="${item.income_dau_duplicate/100}" pattern="#0.00"/></td>
				<td>${item.first_pay_user}</td>
				<td>${item.first_pay_times}</td>
	<!-- 首充金额 --><td><fmt:formatNumber value="${item.first_pay_value/100}" pattern="#0.00"/></td>
				<td>${item.second_remainer}</td>

				<td>${item.third_remainer}</td>
				<td>${item.seventh_remainer}</td>
				<td>${item.doubleweek_remainer}</td>
				<td>${item.month_remainer}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str600'/></div>
				<div style="height: 300px;line-height: 300px">
					<div  class="highchart-daysIncome" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-daysIncome" data-graph-type="column" data-graph-xaxis-reversed="1"
						   data-graph-yaxis-1-title-text = '<spring:message code='str201'/>' data-graph-zoom-type="xy" style="display: none">
						<caption><spring:message code='str600'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str198'/></th>
							<th data-graph-type="column"><spring:message code='str201'/></th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.list}" var="item">
								<tr>
									<td>${item.log_day}</td>
						<!-- 收入--><td><fmt:formatNumber value=" ${item.income/100}" pattern="#0.00"/></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str601'/></div>
				<div style="height: 300px;line-height: 300px">
					<div class="highchart-daysActive" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-daysActive" data-graph-type="column" data-graph-xaxis-reversed="1"
						   data-graph-yaxis-1-title-text = '<spring:message code='str206'/>' data-graph-zoom-type="xy" data-graph-yaxis-2-opposite="1" style="display: none">
						<caption><spring:message code='str601'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str198'/></th>
							<th data-graph-type="column"><spring:message code='str223'/></th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.list}" var="item">
								<tr>
									<td>${item.log_day}</td>
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
				<div class="panel-heading"><spring:message code='str602'/>/<spring:message code='str603'/></div>
				<div style="height: 300px;line-height: 300px">
					<div  class="highchart-daysOnlines" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-daysOnlines" data-graph-type="column" data-graph-xaxis-reversed="1"
						   data-graph-yaxis-1-title-text = '<spring:message code='str604'/>' data-graph-yaxis-2-title-text = '<spring:message code='str605'/>'
						   data-graph-zoom-type="xy" data-graph-yaxis-2-opposite="1" style="display: none">
						<caption><spring:message code='str602'/>/<spring:message code='str603'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str198'/></th>
							<th data-graph-type="column"><spring:message code='str604'/></th>
							<th data-graph-type="line"><spring:message code='str605'/></th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.list}" var="item">
								<tr>
									<td>${item.log_day}</td>
									<td>${item.acu}</td>
									<td>${item.pccu}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str606'/></div>
				<div style="height: 300px;line-height: 300px">
					<div class="highchart-daysRecharger" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-daysRecharger" data-graph-type="column" data-graph-xaxis-reversed="1"
						   data-graph-yaxis-1-title-text = '<spring:message code='str206'/>1' data-graph-yaxis-2-title-text = '<spring:message code='str207'/>1'
						   data-graph-zoom-type="xy" data-graph-yaxis-2-opposite="1" style="display: none">
						<caption><spring:message code='str606'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str198'/></th>
							<th data-graph-type="column"><spring:message code='str238'/></th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.list}" var="item">
								<tr>
									<td>${item.log_day}</td>
									<td>${item.dpa}</td>
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
