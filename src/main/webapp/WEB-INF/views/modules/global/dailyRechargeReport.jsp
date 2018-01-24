<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str296'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/rechargeConsume/dailyRechargeReport"><spring:message code='str296'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/rechargeConsume/dailyRechargeReport" method="post" class="breadcrumb form-search">
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
			<div class="span12 panel panel-primary">
				<div class="panel-heading"><spring:message code='str297'/></div><!-- 每日充值统计 -->
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
				<table id="dailyTable1" class="table table-striped table-bordered table-condensed">
					<tr>
						<th><spring:message code='str198'/></th>
						<th><spring:message code='str234'/></th>
						<th><spring:message code='str253'/></th>
						<th><spring:message code='str199'/></th>
						<th><spring:message code='str200'/></th>
						<th><spring:message code='str238'/></th>
						<th><spring:message code='str275'/></th>
						<th><spring:message code='str205'/></th>
						<th>ARPU</th>
						<th><spring:message code='str220'/>ARPU</th>
						<th><spring:message code='str298'/></th>
						<th><spring:message code='str299'/></th>
					</tr>
					<c:forEach items="${dailyList}" var="item">
						<tr>
							<td>${item.log_day}</td>
							<td>${item.kaifuNum}</td>
							<td><fmt:formatNumber value="${item.income/100}" pattern="0.00"/></td>
							<td>${item.dru}</td>
							<td>${item.dau}</td>
							<td>${item.dpa}</td>
							<td>${item.dP_times}</td>
							<td>	<span style="<c:if test="${item.pay_rate > 1  }">color: red;font-weight:bold</c:if>"> <fmt:formatNumber value="${item.pay_rate * 100}" pattern="0.00"/>%</span> </td>
							<td><fmt:formatNumber value="${item.arpu/100}" pattern="0.00"/></td>
							<td><fmt:formatNumber value="${item.active_arpu/100}" pattern="0.00"/></td>
							<td>${item.first_pay_user}</td>
							<td><fmt:formatNumber value="${item.first_pay_value/100}" pattern="0.00"/></td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12 panel panel-primary">
				<div class="panel-heading"><spring:message code='str300'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;ovoverflow:scrolld="dailyTable2" class="table table-striped table-bordered table-condensed">
				<table id="dailyTable2" class="table table-striped table-bordered table-condensed">
					<tr>
						<th></th>
						<th></th>
						<th colspan="4"><spring:message code='str268'/></th>
						<th colspan="4">3<spring:message code='str269'/></th>
						<th colspan="4">7<spring:message code='str269'/></th>
						<th colspan="4"><spring:message code='str244'/></th>
						<th colspan="4"><spring:message code='str248'/></th>
						<th colspan="4"><spring:message code='str251'/></th>
					</tr>
					<tr>
					<th><spring:message code='str198'/></th>
					<th><spring:message code='str270'/></th>
					<th><spring:message code='str238'/></th>
					<th><spring:message code='str253'/></th>
					<th><spring:message code='str205'/></th>
					<th>ARPU</th>
					<th><spring:message code='str238'/></th>
					<th><spring:message code='str253'/></th>
					<th><spring:message code='str205'/></th>
					<th>ARPU</th>
					<th><spring:message code='str238'/></th>
					<th><spring:message code='str253'/></th>
					<th><spring:message code='str205'/></th>
					<th>ARPU</th>
					<th><spring:message code='str238'/></th>
					<th><spring:message code='str253'/></th>
					<th><spring:message code='str205'/></th>
					<th>ARPU</th>
					<th><spring:message code='str238'/></th>
					<th><spring:message code='str253'/></th>
					<th><spring:message code='str205'/></th>
					<th>ARPU</th>
					<th><spring:message code='str238'/></th>
					<th><spring:message code='str253'/></th>
					<th><spring:message code='str205'/></th>
					<th>ARPU</th></tr>
					<c:forEach items="${dailyList}" var="item">
						<tr>
							<td>${item.log_day}</td>
							<!-- 当日收入 --><td><fmt:formatNumber value="${item.income/100}" pattern="0.00"/></td>
							
							<td>${item.first_day_dpa}</td>
							<td><fmt:formatNumber value="${item.first_day_income/100}" pattern="0.00"/></td>
							<td>	<span style="<c:if test="${item.first_day_pay_rate > 1  }">color: red;font-weight:bold</c:if>"> <fmt:formatNumber value="${item.first_day_pay_rate * 100}" pattern="0.00"/>%</span> </td>
							<td><fmt:formatNumber value="${item.first_day_arpu/100}" pattern="0.00"/></td>
							
							<td>${item.third_day_dpa}</td>
							<td><fmt:formatNumber value="${item.third_day_income/100}" pattern="0.00"/></td>
							<td>	<span style="<c:if test="${item.third_day_pay_rate > 1  }">color: red;font-weight:bold</c:if>"> <fmt:formatNumber value="${item.third_day_pay_rate * 100}" pattern="0.00"/>%</span> </td>
							<td><fmt:formatNumber value="${item.third_day_arpu/100}" pattern="0.00"/></td>
							
							<td>${item.seventh_day_dpa}</td>
							<td><fmt:formatNumber value="${item.seventh_day_income/100}" pattern="0.00"/></td>
							<td>	<span style="<c:if test="${item.seventh_day_pay_rate > 1  }">color: red;font-weight:bold</c:if>"> <fmt:formatNumber value="${item.seventh_day_pay_rate * 100}" pattern="0.00"/>%</span> </td>
							<td><fmt:formatNumber value="${item.seventh_day_arpu/100}" pattern="0.00"/></td>
							
							<td>${item.nn_pa}</td>
							<td><fmt:formatNumber value="${item.nn_pay_value/100}" pattern="0.00"/></td>
							<td>	<span style="<c:if test="${item.nn_pay_rate > 1  }">color: red;font-weight:bold</c:if>"> <fmt:formatNumber value="${item.nn_pay_rate * 100}" pattern="0.00"/>%</span> </td>
							<td><fmt:formatNumber value="${item.nn_arpu/100}" pattern="0.00"/></td>
							
							<td>${item.on_pa}</td>
							<td><fmt:formatNumber value="${item.on_pay_value/100}" pattern="0.00"/></td>
							<td>	<span style="<c:if test="${item.on_pay_rate > 1  }">color: red;font-weight:bold</c:if>"> <fmt:formatNumber value="${item.on_pay_rate * 100}" pattern="0.00"/>%</span> </td>
							<td><fmt:formatNumber value="${item.on_arpu/100}" pattern="0.00"/></td>
							
							<td>${item.oo_pa}</td>
							<td><fmt:formatNumber value="${item.oo_pay_value/100}" pattern="0.00"/></td>
							<td>	<span style="<c:if test="${item.oo_pay_rate > 1  }">color: red;font-weight:bold</c:if>"> <fmt:formatNumber value="${item.oo_pay_rate * 100}" pattern="0.00"/>%</span> </td>
							<td><fmt:formatNumber value="${item.oo_arpu/100}" pattern="0.00"/></td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str301'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
				<table id="dailyTable" class="table table-striped table-bordered table-condensed">
					<tr><th><spring:message code='str198'/></th><th><spring:message code='str56'/></th><th><spring:message code='str223'/></th><th><spring:message code='str238'/></th><th><spring:message code='str253'/></th><th><spring:message code='str205'/></th><th>APRU</th><th><spring:message code='str220'/>APRU</th></tr>
					<c:forEach items="${dailyPlatFormList}" var="item">
						<tr>
							<td>${item.log_day}</td>
							<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
							<td>${item.dau}</td>
							<td>${item.dpa}</td>
							<td><fmt:formatNumber value="${item.income/100}" pattern="0.00"/></td>
							<td>	<span style="<c:if test="${item.pay_rate > 1  }">color: red;font-weight:bold</c:if>"> <fmt:formatNumber value="${item.pay_rate * 100}" pattern="0.00"/>%</span></td>
							<td><fmt:formatNumber value="${item.arpu/100}" pattern="0.00"/></td>
							<td><fmt:formatNumber value="${item.active_arpu/100}" pattern="0.00"/></td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str302'/></div>
				<div style="height: 300px;line-height: 300px">
					<div class="highchart-income-pie" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-income-pie" data-graph-type="pie" data-graph-pie-show-in-legend="1" data-graph-datalabels-enabled="1" style="display: none">
						<caption><spring:message code='str225'/></caption>
						<thead>
						<tr>
							<th data-graph-type="pie"><spring:message code='str56'/></th>
							<th data-graph-type="pie"><spring:message code='str273'/></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${mapList}" var="item">
							<tr>
								<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
								<td data-graph-name="${fns:getGamePlatformNameById(item.pid,item.pid)} : ${item.income}">${item.income}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
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
