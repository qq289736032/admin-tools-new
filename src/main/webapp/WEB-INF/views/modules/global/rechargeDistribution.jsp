<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str571'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/rechargeConsume/rechargeDistribution"><spring:message code='str571'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/rechargeConsume/rechargeDistribution" method="post" class="breadcrumb form-search">
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
			<div class="panel panel-primary">
				<div class="panel-heading"><spring:message code='str571'/></div>
				<div style="height: 300px;line-height: 300px">
				<div class="highchart-income" style="height: 300px"></div>
				<table class="highchart table"
					   data-graph-container=".highchart-income" data-graph-type="column"
					   data-graph-yaxis-1-title-text = '<spring:message code='str206'/>' data-graph-yaxis-2-title-text = '<spring:message code='str572'/>'
					   data-graph-zoom-type="xy" data-graph-yaxis-2-opposite="1" style="display: none">
					<caption><spring:message code='str571'/></caption>
					<thead>
					<tr>						
						<th><spring:message code='str573'/></th>
						<th data-graph-type="column"><spring:message code='str209'/></th>
						<th data-graph-type="line" data-graph-yaxis="1"><spring:message code='str574'/></th>
					</tr>
					</thead>
					<tbody>
						<c:forEach items="${resultList}" var="item">
							<tr>
								<td>${item.section}</td>
								<td>${item.num}</td>
								<td>${item.amount}</td>	
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
			</div>
		</div>
	
			
		<div class="panel panel-primary">
			<div class="panel-heading"><spring:message code='str575'/></div>
			<div class="table-scrollable">
				<table id="rechargedis" class="datatable table table-striped table-bordered table-condensed" style="overflow: scroll">
					<thead>
					<tr>
					<th><spring:message code='str198'/></th>
					<th><spring:message code='str498'/>0,9<spring:message code='str499'/></th>
					<th><spring:message code='str498'/>10,19<spring:message code='str499'/></th>
					<th><spring:message code='str498'/>20,29<spring:message code='str499'/></th>
					<th><spring:message code='str498'/>30,49<spring:message code='str499'/></th>
					<th><spring:message code='str498'/>50,69<spring:message code='str499'/></th>
					<th><spring:message code='str498'/>70,99<spring:message code='str499'/></th>
					<th><spring:message code='str498'/>100,199<spring:message code='str499'/></th>
					<th><spring:message code='str498'/>200,299<spring:message code='str499'/></th>
					<th><spring:message code='str498'/>300,399<spring:message code='str499'/></th>
						<th><spring:message code='str498'/>400,499<spring:message code='str499'/></th>
						<th><spring:message code='str498'/>500,699<spring:message code='str499'/></th>
						<th><spring:message code='str498'/>700,999<spring:message code='str499'/></th>
						<th><spring:message code='str498'/>1000,1499<spring:message code='str499'/></th>
						<th><spring:message code='str498'/>1500,1999<spring:message code='str499'/></th>
						<th><spring:message code='str498'/>2000,2499<spring:message code='str499'/></th>
						<th><spring:message code='str498'/>2500,2999<spring:message code='str499'/></th>
						<th><spring:message code='str498'/>3000,3999<spring:message code='str499'/></th>
						<th><spring:message code='str498'/>4000,4999<spring:message code='str499'/></th>
						<th><spring:message code='str498'/>5000,6999<spring:message code='str499'/></th>
						<th><spring:message code='str498'/>7000,9999<spring:message code='str499'/></th>
						<th><spring:message code='str498'/>10000,19999<spring:message code='str499'/></th>
						<th><spring:message code='str498'/>20000,49999<spring:message code='str499'/></th>
						<th><spring:message code='str498'/>50000,99999<spring:message code='str499'/></th>
						<th><spring:message code='str498'/>100000,199999<spring:message code='str499'/></th>
						<th><spring:message code='str498'/>200000,∞<spring:message code='str499'/></th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${tableList}" var="item">
						<tr>
							<td>${item.log_day}</td>
							<td>${item.amount1}</td>
							<td>${item.amount2}</td>
							<td>${item.amount3}</td>
							<td>${item.amount4}</td>
							<td>${item.amount5}</td>
							<td>${item.amount6}</td>
							<td>${item.amount7}</td>
							<td>${item.amount8}</td>
							<td>${item.amount9}</td>
							<td>${item.amount10}</td>
							<td>${item.amount11}</td>
							<td>${item.amount12}</td>
							<td>${item.amount13}</td>
							<td>${item.amount14}</td>
							<td>${item.amount15}</td>
							<td>${item.amount16}</td>
							<td>${item.amount17}</td>
							<td>${item.amount18}</td>
							<td>${item.amount19}</td>
							<td>${item.amount20}</td>
							<td>${item.amount21}</td>
							<td>${item.amount22}</td>
							<td>${item.amount23}</td>
							<td>${item.amount24}</td>
							<td>${item.amount25}</td>
						</tr>
					</c:forEach>
					<tr>
						<td><spring:message code='str193'/></td>
						<c:forEach items="${totalAmountList}" varStatus="status">
							<td>${totalAmountList[status.index]}</td>
						</c:forEach>
					</tr>
					</tbody>
				</table>

			</div>
		</div>
		
		<div class="panel panel-primary">
			<div class="panel-heading"><spring:message code='str576'/></div>
			<div class="table-scrollable">
				<table id="contentTable" class="datatable table table-striped table-bordered table-condensed" style="overflow: scroll">
					<thead>
					<tr><th><spring:message code='str198'/></th><th><spring:message code='str498'/>0,9<spring:message code='str499'/></th><th><spring:message code='str498'/>10,19<spring:message code='str499'/></th><th><spring:message code='str498'/>20,29<spring:message code='str499'/></th><th><spring:message code='str498'/>30,49<spring:message code='str499'/></th><th><spring:message code='str498'/>50,69<spring:message code='str499'/></th><th><spring:message code='str498'/>70,99<spring:message code='str499'/></th><th><spring:message code='str498'/>100,199<spring:message code='str499'/></th><th><spring:message code='str498'/>200,299<spring:message code='str499'/></th><th><spring:message code='str498'/>300,399<spring:message code='str499'/></th>
						<th><spring:message code='str498'/>400,499<spring:message code='str499'/></th><th><spring:message code='str498'/>500,699<spring:message code='str499'/></th><th><spring:message code='str498'/>700,999<spring:message code='str499'/></th><th><spring:message code='str498'/>1000,1499<spring:message code='str499'/></th><th><spring:message code='str498'/>1500,1999<spring:message code='str499'/></th><th><spring:message code='str498'/>2000,2499<spring:message code='str499'/></th><th><spring:message code='str498'/>2500,2999<spring:message code='str499'/></th><th><spring:message code='str498'/>3000,3999<spring:message code='str499'/></th><th><spring:message code='str498'/>4000,4999<spring:message code='str499'/></th><th><spring:message code='str498'/>5000,6999<spring:message code='str499'/></th><th><spring:message code='str498'/>7000,9999<spring:message code='str499'/></th><th><spring:message code='str498'/>10000,19999<spring:message code='str499'/></th>
						<th><spring:message code='str498'/>20000,49999<spring:message code='str499'/></th><th><spring:message code='str498'/>50000,99999<spring:message code='str499'/></th><th><spring:message code='str498'/>100000,199999<spring:message code='str499'/></th><th><spring:message code='str498'/>200000,∞<spring:message code='str499'/></th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${tableList}" var="item">
						<tr>
							<td>${item.log_day}</td>
							<td>${item.num1}</td>
							<td>${item.num2}</td>
							<td>${item.num3}</td>
							<td>${item.num4}</td>
							<td>${item.num5}</td>
							<td>${item.num6}</td>
							<td>${item.num7}</td>
							<td>${item.num8}</td>
							<td>${item.num9}</td>
							<td>${item.num10}</td>
							<td>${item.num11}</td>
							<td>${item.num12}</td>
							<td>${item.num13}</td>
							<td>${item.num14}</td>
							<td>${item.num15}</td>
							<td>${item.num16}</td>
							<td>${item.num17}</td>
							<td>${item.num18}</td>
							<td>${item.num19}</td>
							<td>${item.num20}</td>
							<td>${item.num21}</td>
							<td>${item.num22}</td>
							<td>${item.num23}</td>
							<td>${item.num24}</td>
							<td>${item.num25}</td>
						</tr>
					</c:forEach>
					<tr>
						<td><spring:message code='str193'/></td>
						<c:forEach items="${totalNumList}" varStatus="status">
							<td>${totalNumList[status.index]}</td>
						</c:forEach>
					</tr>
					</tbody>
				</table>

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
</script>

</body>
</html>
