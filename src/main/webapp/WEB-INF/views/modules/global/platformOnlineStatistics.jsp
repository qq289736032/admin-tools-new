<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str523'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<style type="text/css">
		span.input-group-btn {
			display: none;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/platformStatistics/platformOnlineStatistics"><spring:message code='str523'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/platformStatistics/platformOnlineStatistics" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		<label><spring:message code='str56'/> <spring:message code='str4'/></label>
		<!-- <spring:message code='str344'/> -->
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
			<div class="panel panel-primary">
				<div class="panel-heading"><spring:message code='str524'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
				<table id="dailyTable" class="table table-striped table-bordered table-condensed">
					<tr><th><spring:message code='str198'/></th><th><spring:message code='str56'/></th><th>0:00</th><th>1:00</th><th>2:00</th><th>3:00</th><th>4:00</th><th>5:00</th><th>6:00</th>
					<th>7:00</th><th>8:00</th><th>9:00</th><th>10:00</th><th>11:00</th><th>12:00</th><th>13:00</th><th>14:00</th><th>15:00</th>
					<th>16:00</th><th>17:00</th><th>18:00</th><th>19:00</th><th>20:00</th><th>21:00</th><th>22:00</th><th>23:00</th>
					</tr>
					<c:forEach items="${onlineDistribution}" var="item">
						<tr>
							<td>${item.log_day}</td>
							<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
							<td>${item.section_1}</td>
							<td>${item.section_2}</td>
							<td>${item.section_3}</td>
							<td>${item.section_4}</td>
							<td>${item.section_5}</td>
							<td>${item.section_6}</td>
							<td>${item.section_7}</td>
							<td>${item.section_8}</td>
							<td>${item.section_9}</td>
							<td>${item.section_10}</td>
							<td>${item.section_11}</td>
							<td>${item.section_12}</td>
							<td>${item.section_13}</td>
							<td>${item.section_14}</td>
							<td>${item.section_15}</td>
							<td>${item.section_16}</td>
							<td>${item.section_17}</td>
							<td>${item.section_18}</td>
							<td>${item.section_19}</td>
							<td>${item.section_20}</td>
							<td>${item.section_21}</td>
							<td>${item.section_22}</td>
							<td>${item.section_23}</td>
							<td>${item.section_24}</td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		
		<div class="row-fluid">
			<div class="span6 panel panel-primary">
					<div class="panel-heading"><spring:message code='str525'/></div>
					<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;ovoverflow:scrollid="dailyTable" class="table table-striped table-bordered table-condensed">
					<table id="dailyTable" class="table table-striped table-bordered table-condensed">
						<tr><th><spring:message code='str198'/></th><th><spring:message code='str56'/></th><th>ACCU</th><th>PCCU</th>
						</tr>
						<c:forEach items="${onlineTotal}" var="item">
							<tr>
								<td>${item.log_day}</td>
								<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
								<td>${item.acu}</td>
								<td>${item.pccu}</td>				
							</tr>
						</c:forEach>
					</table>
					</div>
			</div>
			
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str56'/>ACCU<spring:message code='str193'/></div>
				<div style="height: 300px;line-height: 300px">
					<div class="highchart-income-pie" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-income-pie" data-graph-type="pie" data-graph-pie-show-in-legend="1" data-graph-datalabels-enabled="1" style="display: none">
						<caption><spring:message code='str526'/>ACCU<spring:message code='str527'/></caption>
						<thead>
						<tr>
							<th data-graph-type="pie"><spring:message code='str56'/></th>
							<th data-graph-type="pie"><spring:message code='str226'/></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${onlineTotalPieGraph}" var="item">
							<tr>
								<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
								<td data-graph-name="${fns:getGamePlatformNameById(item.pid,item.pid)} : <fmt:formatNumber type="percent" value="${item.p_acu_percent}"/>"><fmt:formatNumber type="percent" value="${item.p_acu_percent}"/></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
		<div class="panel panel-primary">
				<div class="panel-heading"><spring:message code='str528'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
				<table id="dailyTable" class="table table-striped table-bordered table-condensed">
					<tr><th><spring:message code='str198'/></th><th><spring:message code='str56'/></th><th><spring:message code='str308'/></th><th>ACCU<40</th><th><spring:message code='str226'/></th><th>ACCU[40,100]</th><th><spring:message code='str226'/></th><th>ACCU[100,200]</th><th><spring:message code='str226'/></th>
					<th>ACCU[200,500]</th><th><spring:message code='str226'/></th><th>ACCU[500+]</th><th><spring:message code='str226'/></th>
					</tr>
					<c:forEach items="${onlineMonitor}" var="item">
						<tr>
							<td>${item.log_day}</td>
							<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
							<td>${item.server_total}</td>
							<td>${item.accu_40less}</td>
						    <td <c:if test="${item.accu_40less_percent > 100 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.accu_40less_percent}" pattern="#0.00"/>%</td>
							<td>${item.accu_100less}</td>
							<td <c:if test="${item.accu_100less_percent  > 100 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.accu_100less_percent}" pattern="#0.00"/>%</td>
							<td>${item.accu_200less}</td>
							<td <c:if test="${item.accu_200less_percent  > 100 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.accu_200less_percent}" pattern="#0.00"/>%</td>
							<td>${item.accu_500less}</td>
							<td <c:if test="${item.accu_500less_percent > 100 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.accu_500less_percent}" pattern="#0.00"/>%</td>
							<td>${item.accu_500more}</td>
							<td <c:if test="${item.accu_500more_percent  > 100 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber   value="${item.accu_500more_percent}" pattern="#0.00"/>%</td>
						</tr>
					</c:forEach>
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
		$("#s2id_pids").hide();
		$("#pids").multiselect({
			includeSelectAllOption:true,
			enableFiltering:true
		});
	});
</script>

</body>
</html>
