<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str307'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/monthRemainer/monthRemainerReport"><spring:message code='str415'/></a></li>
		<li><a href="${ctx}/global/monthRemainer/monthRemainerReport?isLineChart=1"><spring:message code='str414'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/monthRemainer/monthRemainerReport" method="post" class="breadcrumb form-search">
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
		<tr><th><spring:message code='str416'/></th> <th><spring:message code='str417'/></th> <th><spring:message code='str418'/></th> <th><spring:message code='str419'/></th> <th>7<spring:message code='str420'/></th>
			<th><spring:message code='str421'/></th> <th>14<spring:message code='str420'/></th> <th><spring:message code='str422'/></th> <th>30<spring:message code='str420'/></th> <th><spring:message code='str219'/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${monthRemainer}" var="item">
			<tr>
				<td>${item.log_day}</td>
				<td>${item.sum_dru}</td>
				<td>${item.sum_2}</td>
				<td>	<span style="<c:if test="${item.sum_2_dru >1  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_2_dru*100}" pattern="0.00"/>%</span></td>
				<td>${item.sum_7}</td>
				<td>	<span style="<c:if test="${item.sum_7_dru >1  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_7_dru*100}" pattern="0.00"/>%</span></td>
				<td>${item.sum_14}</td>
				<td>	<span style="<c:if test="${item.sum_14_dru >1  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_14_dru*100}" pattern="0.00"/>%</span></td>
				<td>${item.sum_30}</td>
				<td>	<span style="<c:if test="${item.sum_30_dru >1  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_30_dru*100}" pattern="0.00"/>%</span></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('table.highchart').highchartTable();
		});
	</script>

</body>
</html>
