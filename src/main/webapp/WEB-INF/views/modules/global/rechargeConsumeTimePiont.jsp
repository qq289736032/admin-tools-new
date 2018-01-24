<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str566'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/rechargeConsume/rechargeConsumeTimePiont"><spring:message code='str566'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/rechargeConsume/rechargeConsumeTimePiont" method="post" class="breadcrumb form-search">
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
				<div class="panel-heading"><spring:message code='str567'/></div>
				<table id="contentTable1" class="datatable table table-striped table-bordered table-condensed" style="overflow: scroll">
					<thead>
					<tr><th><spring:message code='str198'/></th><th><spring:message code='str568'/></th><th><spring:message code='str238'/></th><th><spring:message code='str253'/></th><th><spring:message code='str205'/></th><th>ARPU</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${tableList1}" var="item">
						<tr>
							<td>${item.log_day}</td>
							<td>${item.log_hour}</td>
							<td>${item.pa}</td>
							<td><fmt:formatNumber value="${item.income/100}" pattern="0.00"/></td>
							<td <c:if test="${item.pay_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.pay_rate * 100}" pattern="0.00"/>%</td>
							<td><fmt:formatNumber value="${item.arpu/100}" pattern="0.00"/></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str569'/></div>
				<table id="contentTable2" class="datatable table table-striped table-bordered table-condensed" style="overflow: scroll">
					<thead>
					<tr><th><spring:message code='str198'/></th><th><spring:message code='str568'/></th><th><spring:message code='str191'/></th><th><spring:message code='str254'/></th><th><spring:message code='str205'/></th><th>ARPU</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${tableList2}" var="item">
						<tr>
							<td>${item.log_day}</td>
							<td>${item.log_hour}</td>
							<td>${item.consume_account}</td>
							<td><fmt:formatNumber value="${item.consume_amount/100}" pattern="0.00"/></td>
							<td <c:if test="${item.pay_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.pay_rate * 100}" pattern="0.00"/>%</td>
							<td><fmt:formatNumber value="${item.arpu/100}" pattern="0.00"/></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str570'/></div>
				<table id="contentTable" class="datatable table table-striped table-bordered table-condensed" style="overflow: scroll">
					<thead>
					<tr><th><spring:message code='str198'/></th><th><spring:message code='str568'/></th><th><spring:message code='str191'/></th><th><spring:message code='str254'/></th><th><spring:message code='str205'/></th><th>ARPU</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${tableList3}" var="item">
						<tr>
							<td>${item.log_day}</td>
							<td>${item.log_hour}</td>
							<td>${item.consume_account}</td>
							<td><fmt:formatNumber value="${item.consume_amount/100}" pattern="0.00"/></td>
							<td <c:if test="${item.pay_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.pay_rate * 100}" pattern="0.00"/>%</td>
							<td><fmt:formatNumber value="${item.arpu/100}" pattern="0.00"/></td>
						</tr>
					</c:forEach>
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
