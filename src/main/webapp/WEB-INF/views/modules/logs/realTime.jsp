<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str961'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/log/realTimeService/realTimeServiceReport"><spring:message code='str961'/></a></li>
		<li class="active"><a href="${ctx}/log/realTimeService/realTime"><spring:message code='str962'/></a></li>
	    <li ><a href="${ctx}/log/realTimeService/realFenTime"><spring:message code='str963'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/realTimeService/realTime" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input name="logHour" id="logHour" maxlength="50"  value="${paramMap.logHour}" class="input-small required" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH'})"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th><spring:message code='str964'/></th> <th><spring:message code='str56'/></th> <th><spring:message code='str14'/></th> <th><spring:message code='str217'/></th> <th><spring:message code='str965'/></th>
			<th><spring:message code='str966'/></th> <th><spring:message code='str238'/></th><th><spring:message code='str275'/></th> <th><spring:message code='str253'/></th> <th><spring:message code='str205'/></th> <th>ARPU</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item">
			<tr>
				<td>${fns:parseYYYYMMDDHHMM(item.log_minute)}</td>
				<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
				<td>${fns:getGameServer(item.area_id).name}</td>
				<td>${item.ru}</td>
				<td>${item.au}</td>
				<td>${item.num}</td>
				<td>${item.pa}</td>
				<td>${item.pay_times}</td>
				<td>${item.income div 100}</td>
				<td><span style="<c:if test="${item.pay_rate > 1  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.pay_rate * 100}" pattern="#0.00"/>%</span></td>
				<td>${item.arpu div 100}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>

	
	
	<script type="text/javascript">
		$(document).ready(function(){
// 			$('table.highchart').highchartTable();
		});
	</script>

</body>
</html>
