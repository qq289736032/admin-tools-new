<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str258'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/rechargeConsume/dailyBindConsumeReport"><spring:message code='str258'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/rechargeConsume/dailyBindConsumeReport" method="post" class="breadcrumb form-search">
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
				<div class="panel-heading"><spring:message code='str259'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
				<table id="dailyTable1" class="table table-striped table-bordered table-condensed">
					<tr><th><spring:message code='str198'/></th><th><spring:message code='str234'/></th><th><spring:message code='str260'/></th><th><spring:message code='str261'/></th><th><spring:message code='str254'/></th><th><spring:message code='str199'/></th><th><spring:message code='str200'/></th><th><spring:message code='str191'/></th><th><spring:message code='str262'/></th><th><spring:message code='str263'/></th><th><spring:message code='str264'/>ARPU</th><th><spring:message code='str220'/>ARPU</th><th><spring:message code='str265'/></th><th><spring:message code='str266'/></th></tr>
					<c:forEach items="${dailyList}" var="item">
						<tr>
							<td>${item.log_day}</td>
							<td>${item.kaifuNum}</td>
							<td>${item.total}</td>
							<td>${item.produce}</td>
							<td>${item.amount}</td>
							<td>${item.dru}</td>
							<td>${item.dau}</td>
							<td>${item.num}</td>
							<td>${item.times}</td>
							<td  style="<c:if test="${item.pay_rate > 1  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.pay_rate * 100}" pattern="0.00"/>%</td>
							<td><fmt:formatNumber value="${item.arpu}" pattern="0.00"/></td>
							<td><fmt:formatNumber value="${item.active_arpu}" pattern="0.00"/></td>
							<td>${item.first_num}</td>
							<td>${item.first_amount}</td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12 panel panel-primary">
				<div class="panel-heading"><spring:message code='str267'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
				<table id="dailyTable2" class="table table-striped table-bordered table-condensed">
					<tr><th></th><th></th><th colspan="4"><spring:message code='str268'/></th><th colspan="4">3<spring:message code='str269'/></th><th colspan="4">7<spring:message code='str269'/></th><th colspan="4"><spring:message code='str244'/></th><th colspan="4"><spring:message code='str248'/></th><th colspan="4"><spring:message code='str251'/></th></tr>
					<tr><th><spring:message code='str198'/></th><th><spring:message code='str270'/></th><th><spring:message code='str191'/></th><th><spring:message code='str254'/></th><th><spring:message code='str263'/></th><th><spring:message code='str264'/>ARPU</th><th><spring:message code='str191'/></th><th><spring:message code='str254'/></th><th><spring:message code='str263'/></th><th><spring:message code='str264'/>ARPU</th><th><spring:message code='str191'/></th><th><spring:message code='str254'/></th><th><spring:message code='str263'/></th><th><spring:message code='str264'/>ARPU</th><th><spring:message code='str191'/></th><th><spring:message code='str254'/></th><th><spring:message code='str263'/></th><th><spring:message code='str264'/>ARPU</th><th><spring:message code='str191'/></th><th><spring:message code='str254'/></th><th><spring:message code='str263'/></th><th><spring:message code='str264'/>ARPU</th><th><spring:message code='str191'/></th><th><spring:message code='str254'/></th><th><spring:message code='str263'/></th><th><spring:message code='str264'/>ARPU</th></tr>
					<c:forEach items="${dailyList}" var="item">
						<tr>
							<td>${item.log_day}</td>
							<td>${item.amount}</td>
							
							<td>${item.first_day_num}</td>
							<td>${item.first_day_num}</td>
							<td  style="<c:if test="${item.first_day_pay_rate > 1  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.first_day_pay_rate * 100}" pattern="0.00"/>%</td>
							<td><fmt:formatNumber value="${item.first_day_arpu}" pattern="0.00"/></td>
							
							<td>${item.third_day_num}</td>
							<td>${item.third_day_amount}</td>
							<td <c:if test="${item.third_day_pay_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.third_day_pay_rate * 100}" pattern="0.00"/>%</td>
							<td><fmt:formatNumber value="${item.third_day_arpu}" pattern="0.00"/></td>
							
							<td>${item.seventh_day_num}</td>
							<td>${item.seventh_day_amount}</td>
							<td <c:if test="${item.seventh_day_pay_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.seventh_day_pay_rate * 100}" pattern="0.00"/>%</td>
							<td><fmt:formatNumber value="${item.seventh_day_arpu}" pattern="0.00"/></td>
							
							<td>${item.nn_num}</td>
							<td>${item.nn_amount}</td>
							<td <c:if test="${item.nn_pay_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.nn_pay_rate * 100}" pattern="0.00"/>%</td>
							<td><fmt:formatNumber value="${item.nn_arpu}" pattern="0.00"/></td>
							
							<td>${item.on_num}</td>
							<td>${item.on_amount}</td>
							<td><fmt:formatNumber value="${item.on_pay_rate * 100}" pattern="0.00"/>%</td>
							<td><fmt:formatNumber value="${item.on_arpu}" pattern="0.00"/></td>
							
							<td>${item.oo_num}</td>
							<td>${item.oo_amount}</td>
							<td <c:if test="${item.oo_pay_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.oo_pay_rate * 100}" pattern="0.00"/>%</td>
							<td><fmt:formatNumber value="${item.oo_arpu}" pattern="0.00"/></td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str271'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
				<table id="dailyTable" class="table table-striped table-bordered table-condensed">
					<tr><th><spring:message code='str198'/></th><th><spring:message code='str56'/></th><th><spring:message code='str223'/></th><th><spring:message code='str260'/></th><th><spring:message code='str261'/></th><th><spring:message code='str191'/></th><th><spring:message code='str254'/></th><th><spring:message code='str263'/></th><th><spring:message code='str264'/>APRU</th><th><spring:message code='str220'/>APRU</th></tr>
					<c:forEach items="${dailyPlatFormList}" var="item">
						<tr>
							<td>${item.log_day}</td>
							<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
							<td>${item.dau}</td>
							<td>${item.total}</td>
							<td>${item.produce}</td>
							<td>${item.num}</td>
							<td>${item.amount}</td>
							<td <c:if test="${item.pay_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.pay_rate * 100}" pattern="0.00"/>%</td>
							<td><fmt:formatNumber value="${item.arpu}" pattern="0.00"/></td>
							<td><fmt:formatNumber value="${item.arpu}" pattern="0.00"/></td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str272'/></div>
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
								<td data-graph-name="${fns:getGamePlatformNameById(item.pid,item.pid)} : ${item.amount}">${item.amount}</td>
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
