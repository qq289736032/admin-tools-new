<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str456'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<script type="text/javascript" src="${ctxStatic}/highcharts/data.js"></script>
	<style type="text/css">
		span.input-group-btn {
			display: none;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/global/platformStatistics/platformActiveStatistics"><spring:message code='str456'/></a></li>
		<li class="active"><a href="${ctx}/global/platformStatistics/platformActiveStatisticsList">平台活跃统计（去新活跃）</a></li>
		
	</ul>
	<form id="searchForm" action="${ctx}/global/platformStatistics/platformActiveStatisticsList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		<label><spring:message code='str56'/> <spring:message code='str4'/></label>
		<!-- <spring:message code='str315'/> -->
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

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span4 panel panel-primary">
				<div class="panel-heading"><spring:message code='str462'/></div>
				<div style="height: 500px;line-height: 300px">
					<div class="highchart-oau-pie" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-oau-pie" data-graph-type="pie" data-graph-pie-show-in-legend="1" data-graph-datalabels-enabled="1" style="display: none">
						<caption><spring:message code='str463'/></caption>
						<thead>
						<tr>
							<th data-graph-type="pie"><spring:message code='str56'/></th>
							<th data-graph-type="pie"><spring:message code='str201'/></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${activeStatisticsList}" var="item">
							<tr>
								<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
								<td data-graph-name="${fns:getGamePlatformNameById(item.pid,item.pid)} : <fmt:formatNumber type="percent" value="${item.p_old_au}"/>"><fmt:formatNumber type="percent" value="${item.p_old_au}"/></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="span8 panel panel-primary">
				<div class="panel-heading"><spring:message code='str464'/>-<spring:message code='str458'/></div>
				<div class="" style="width:100%;height:500px;line-height:300px;overflow:auto;overflow-x:hidden;">
				<table id="dailyTable" class="table table-striped table-bordered table-condensed">
					<tr><th colspan="5"></th><th colspan="5" style="text-align: center"><spring:message code='str465'/></th>
					</tr>
					<tr><th><spring:message code='str162'/></th><th><spring:message code='str205'/></th><th><spring:message code='str175'/></th><th><spring:message code='str466'/></th><th><spring:message code='str467'/>(<spring:message code='str210'/>)</th><th><spring:message code='str468'/></th>
					<th><spring:message code='str469'/>0,100<spring:message code='str875'/></th><th><spring:message code='str469'/>100,1000<spring:message code='str875'/></th><th><spring:message code='str469'/>1000<spring:message code='str471'/>10000<spring:message code='str875'/></th><th><spring:message code='str469'/>10000+<spring:message code='str875'/></th>
					</tr>
					<c:forEach items="${activeStatisticsList}" var="item">
						<tr>
							<td>${fn:substring(paramMap.createDateStart,"5","10")}~${fn:substring(paramMap.createDateEnd,"5","10")}</td>
							<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
							<td>${item.au}</td>
							<td>${item.au-item.old_au}</td>
							<td>${item.old_au}</td>
							<td>${item.recharge0}</td>
							<td>${item.recharge100_less}</td>
							<td>${item.recharge1000_less}</td>
							<td>${item.recharge10000_less}</td>
							<td>${item.recharge10000_more}</td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div id="container" style="min-width:700px;height:400px"></div>
			<table id="datatable" style="display: none">
			  <tr>
				<td> </td>
				<th><spring:message code='str468'/></th>
				<th><spring:message code='str469'/>0,100<spring:message code='str875'/></th>
				<th><spring:message code='str469'/>100,1000<spring:message code='str875'/></th>
				<th><spring:message code='str469'/>1000<spring:message code='str471'/>10000<spring:message code='str875'/></th>
				<th><spring:message code='str469'/>10000+<spring:message code='str875'/></th>
			  </tr>
					<c:forEach items="${activeStatisticsList}" var="item">
						<tr>
							<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
							<td>${item.recharge0}</td>
							<td>${item.recharge100_less}</td>
							<td>${item.recharge1000_less}</td>
							<td>${item.recharge10000_less}</td>
							<td>${item.recharge10000_more}</td>
						</tr>
					</c:forEach>
			</table>
		</div>
		
	</div>



	<%--<table id="contentTable" class="table table-striped table-bordered table-condensed">--%>
		<%--<tr><th><spring:message code='str173'/></th><th><spring:message code='str209'/></th><th><spring:message code='str176'/></th><th><spring:message code='str174'/></th><th><spring:message code='str175'/></th><th><spring:message code='str210'/></th><th>ACCU</th><th>PCCU</th><th>DT<spring:message code='str211'/>(<spring:message code='str212'/>)</th><th><spring:message code='str213'/></th>--%>
			<%--<th><spring:message code='str180'/></th><th>ARPU</th><th><spring:message code='str195'/>ARPU</th><th><spring:message code='str214'/></th><th><spring:message code='str215'/></th><th><spring:message code='str216'/></th><th><spring:message code='str217'/></th><th><spring:message code='str219'/><spring:message code='str180'/></th><th><spring:message code='str219'/>ARPU</th>--%>
			<%--<th><spring:message code='str220'/></th><spring:message code='str221'/><th><spring:message code='str222'/></th><th><spring:message code='str223'/>ARPU</th><th><spring:message code='str220'/></th><th><spring:message code='str224'/></th><th><spring:message code='str225'/></th><th><spring:message code='str226'/>ARPU</th></tr>--%>
		<%--<c:forEach items="${page.list}" var="item">--%>
			<%--<tr>--%>

			<%--</tr>--%>
		<%--</c:forEach>--%>
	<%--</table>--%>
	<%--<div class="pagination">${page}</div>--%>

<script type="text/javascript">
	$(document).ready(function(){
		$('table.highchart').highchartTable();
		$("#s2id_pids").hide();
		$("#pids").multiselect({
			includeSelectAllOption:true,
			enableFiltering:true
		});
		$('#container').highcharts({
			data: {
				table: document.getElementById('datatable')
			},
			chart: {
				type: 'column'
			},
			title: {
				text: '<spring:message code='str464'/>(<spring:message code='str461'/>)'
			},
			yAxis: {
				allowDecimals: false,
				title: {
					text: '<spring:message code='str472'/>'
				}
			},
			tooltip: {
				formatter: function() {
					return '<b>'+ this.series.name +'</b><br/>'+
						this.y +' '+ this.x;
				}
			},
			plotOptions: {
				column: {
					stacking: 'percent'
				}
			}
		});
	});
</script>

</body>
</html>
