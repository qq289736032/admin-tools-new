<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str383'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/dashboard/monthlyReport"><spring:message code='str383'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/dashboard/monthlyReport" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}',dateFmt:'yyyy-MM'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}',dateFmt:'yyyy-MM'})">
		<label><spring:message code='str55'/></label>
	    <input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/>
		<%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();">--%>
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	<div style="overflow: scroll">
		<table id="contentTable" class="datatable table table-striped table-bordered table-condensed" style="overflow: scroll">
			<thead>
			<tr><th><spring:message code='str369'/></th><th><spring:message code='str384'/></th><th><spring:message code='str385'/></th><th><spring:message code='str386'/></th><th><spring:message code='str387'/></th><th>ACCU</th><th>PCCU</th><th>DT<spring:message code='str236'/>(<spring:message code='str237'/>)</th><th><spring:message code='str388'/></th><th><spring:message code='str389'/></th>
				<th><spring:message code='str390'/></th><th><spring:message code='str392'/></th><th><spring:message code='str391'/></th><th><spring:message code='str393'/>ARPU</th><th><spring:message code='str387'/>ARPU</th><th><spring:message code='str394'/></th><th><spring:message code='str395'/></th><th><spring:message code='str396'/></th><th><spring:message code='str397'/></th><th><spring:message code='str398'/></th><th><spring:message code='str399'/></th><th><spring:message code='str400'/></th>
				<th><spring:message code='str401'/>ARPU</th><th><spring:message code='str402'/></th><th><spring:message code='str403'/></th><th><spring:message code='str404'/></th><th><spring:message code='str405'/></th><th><spring:message code='str406'/>ARPU</th></tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="item">
				<tr>
					<td>${item.log_month}</td>
					<td>${item.kaifuNum}</td>
					<td>${item.income}</td>
					<td>${item.dru}</td>
					<td>${item.mau}</td>
					<td><fmt:formatNumber value="${item.acu}" pattern="#0.00" /></td>
					<td>${item.pccu}</td>
					<td><fmt:formatNumber value="${item.dt/60000}" pattern="#0.00" /></td>
					<td>${item.mou}</td>
					<td>${item.month_remainer}</td>
					<td>${item.mpu}</td>
					<td>	<span style="<c:if test="${item.pay_rate > 1  }">color: red;font-weight:bold</c:if>"> <fmt:formatNumber value="${item.pay_rate * 100}" pattern="#0.00"/>%</span> </td>
					<td>${item.dp_times}</td>
					<td><fmt:formatNumber value="${item.arpu}" pattern="#0.00" /></td>
					<td><fmt:formatNumber value="${item.active_arpu}" pattern="#0.00" /></td>
					<td>${item.first_pay_user}</td>
					<td>${item.first_pay_times}</td>
					<td>${item.first_pay_value}</td>
					<td>${item.mnn_pa}</td>
					<td>${item.nn_pay_times}</td>
					<td>${item.nn_pay_value}</td>
					<td><span style="<c:if test="${item.nn_pay_rate > 1  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.nn_pay_rate *100}" pattern="#0.00" />% </span> </td>
					<td><fmt:formatNumber value="${item.nn_arpu}" pattern="#0.00" /></td>
					<td>${item.d_n_pa}</td>
					<td>${item.df_times}</td>
					<td>${item.i_f_pay_value}</td>
					<td><fmt:formatNumber value="${item.on_pay_rate *100}" pattern="#0.00" />% </td>
					<td><fmt:formatNumber value="${item.on_arpu}" pattern="#0.00" /></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<br/>
		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str407'/></div>
				<div style="height: 300px;line-height: 300px">
					<div  class="highchart-monthsIncome" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-monthsIncome" data-graph-type="column" data-graph-xaxis-reversed="1"
						   data-graph-yaxis-1-title-text = '<spring:message code='str201'/>' data-graph-zoom-type="xy" style="display: none">
						<caption><spring:message code='str407'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str198'/></th>
							<th data-graph-type="column"><spring:message code='str201'/></th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="item">
								<tr>
									<td>${item.log_month}</td>
									<td>${item.income}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str408'/></div>
				<div style="height: 300px;line-height: 300px">
					<div  class="highchart-monthsactive" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-monthsactive" data-graph-type="column" data-graph-xaxis-reversed="1"
						   data-graph-yaxis-1-title-text = '<spring:message code='str206'/>'  style="display: none">
						<caption><spring:message code='str408'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str198'/></th>
							<th data-graph-type="column"><spring:message code='str206'/></th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="item">
								<tr>
									<td>${item.log_month}</td>
									<td>${item.mau}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>


		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str409'/></div>
				<div style="height: 300px;line-height: 300px">
					<div class="highchart-newplayer" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-newplayer" data-graph-type="spline" data-graph-xaxis-reversed="1"
						   data-graph-yaxis-1-title-text = '<spring:message code='str206'/>'  style="display: none">
						<caption><spring:message code='str409'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str198'/></th>
							<th><spring:message code='str222'/></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${list}" var="item">
							<tr>
								<td>${item.log_month}</td>
								<td>${item.dru}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str410'/></div>
				<div style="height: 300px;line-height: 300px">
					<div class="highchart-monthremainer" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-monthremainer" data-graph-type="spline" data-graph-xaxis-reversed="1"
						   data-graph-yaxis-1-title-text = '<spring:message code='str411'/>'  style="display: none">
						<caption><spring:message code='str410'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str198'/></th>
							<th><spring:message code='str411'/></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${list}" var="item">
							<tr>
								<td>${item.log_month}</td>
								<td>${item.month_remainer}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>	
		</div>

		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str412'/></div>
				<div style="height: 300px;line-height: 300px">
					<div class="highchart-dt" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-dt" data-graph-type="spline" data-graph-xaxis-reversed="1"
						   data-graph-yaxis-1-title-text = 'dt'  style="display: none">
						<caption><spring:message code='str412'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str198'/></th>
							<th>DT<spring:message code='str236'/></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${list}" var="item">
							<tr>
								<td>${item.log_month}</td>
								<td>${item.dt}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str413'/></div>
				<div style="height: 300px;line-height: 300px">
					<div  class="highchart-recharger" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-recharger" data-graph-type="column" data-graph-xaxis-reversed="1"
						   data-graph-yaxis-1-title-text = '<spring:message code='str206'/>' data-graph-zoom-type="xy" style="display: none">
						<caption><spring:message code='str413'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str198'/></th>
							<th data-graph-type="column"><spring:message code='str206'/></th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="item">
								<tr>
									<td>${item.log_month}</td>
									<td>${item.mpu}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>



	<%--<table id="contentTable" class="table table-striped table-bordered table-condensed">--%>
		<%--<tr><th><spring:message code='str198'/></th><th><spring:message code='str234'/></th><th><spring:message code='str201'/></th><th><spring:message code='str199'/></th><th><spring:message code='str200'/></th><th><spring:message code='str235'/></th><th>ACCU</th><th>PCCU</th><th>DT<spring:message code='str236'/>(<spring:message code='str237'/>)</th><th><spring:message code='str238'/></th>--%>
			<%--<th><spring:message code='str205'/></th><th>ARPU</th><th><spring:message code='str220'/>ARPU</th><th><spring:message code='str239'/></th><th><spring:message code='str240'/></th><th><spring:message code='str241'/></th><th><spring:message code='str242'/></th><th><spring:message code='str244'/><spring:message code='str205'/></th><th><spring:message code='str244'/>ARPU</th>--%>
			<%--<th><spring:message code='str245'/></th><spring:message code='str246'/><th><spring:message code='str247'/></th><th><spring:message code='str248'/>ARPU</th><th><spring:message code='str245'/></th><th><spring:message code='str249'/></th><th><spring:message code='str250'/></th><th><spring:message code='str251'/>ARPU</th></tr>--%>
		<%--<c:forEach items="${page.list}" var="item">--%>
			<%--<tr>--%>

			<%--</tr>--%>
		<%--</c:forEach>--%>
	<%--</table>--%>
	<%--<div class="pagination">${page}</div>--%>

<script type="text/javascript">
	$(document).ready(function(){
		$('table.highchart').highchartTable();
	});
	
	function selectMonth() {  
       WdatePicker({ dateFmt: 'yyyy-MM', isShowToday: false, isShowClear: false });  
    }  
</script>

</body>
</html>
