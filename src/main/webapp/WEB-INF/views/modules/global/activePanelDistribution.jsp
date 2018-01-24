<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str184'/></title>
	<meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<script src="${ctxStatic}/common/fixtable.js" type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/tradeController/activePanelDistribution"><spring:message code='str184'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/tradeController/activePanelDistribution" method="post" class="breadcrumb form-search">
		
		<label><spring:message code='str185'/></label>
		<input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		<label><spring:message code='str55'/></label>
	    <input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/>
		<%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();">--%>
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	 
		
		<div class="row-fluid">
			<div class="span12 panel panel-primary">
				<div class="panel-heading"><spring:message code='str186'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll">
				<table id="active-yb" class="table table-striped table-bordered table-condensed">
					<tr><th><spring:message code='str187'/></th><th><spring:message code='str188'/></th><th><spring:message code='str189'/></th><th><spring:message code='str190'/>(%)</th><th><spring:message code='str191'/></th><th><spring:message code='str192'/>(%)</th></tr>
					<c:forEach items="${activeList}" var="item">
						<tr>
							<td>${createDateStart } ~ ${createDateEnd }</td>
							<td>${fns:getActivePanel(item.active_panel)}</td>
							<td>${item.yb }</td>
							<td <c:if test="${ totalYb > 0 && (item.yb*100/totalYb > 100) }">style="color: red;font-weight:bold" </c:if>><c:if test="${totalYb > 0 }"><fmt:formatNumber  value="${ item.yb >0 ? (item.yb*100/totalYb):0 }" pattern="#0.00"/> %</c:if></td>
							<td>${item.num }</td>
							<td <c:if test="${ ybNum > 0 && item.num*100/ybNum > 100 }">style="color: red;font-weight:bold" </c:if>><c:if test="${ybNum > 0 }"><fmt:formatNumber  value="${ ybNum >0 ? item.num*100/ybNum  :0}" pattern="#0.00"/> %</c:if> </td>
						</tr>
					</c:forEach>
					<tr>
					  <td style="font-weight: bold;"><spring:message code='str193'/></td>
					  <td></td>
					  <td>${totalYb }</td>
					  <td>100%</td>
					  <td>${ybNum }</td>
					  <td>100%</td>
					</tr>
				</table>
				</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span12 panel panel-primary">
				<div class="panel-heading"><spring:message code='str194'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
				<table id="active-bind-yb" class="table table-striped table-bordered table-condensed">
					<tr><th><spring:message code='str187'/></th><th><spring:message code='str188'/></th><th><spring:message code='str195'/></th><th><spring:message code='str190'/>(%)</th><th><spring:message code='str191'/></th><th><spring:message code='str192'/>(%)</th></tr>
					<c:forEach items="${activeList}" var="item">
						<tr>
							<tr>
							<td>${createDateStart } ~ ${createDateEnd }</td>
							<td>${fns:getActivePanel(item.active_panel)}</td>
							<td>${item.bind_yb }</td>
							<td <c:if test="${totalBind >0 && item.bind_yb*100/totalBind > 100 }">style="color: red;font-weight:bold" </c:if>><c:if test="${totalBind > 0 }"><fmt:formatNumber value="${ totalBind >0 ? item.bind_yb*100/totalBind :0 }" pattern="#0.00"/>%</c:if></td>
							<td>${item.bind_num }</td>
							<td <c:if test="${bindNum >0 && item.bind_num*100/bindNum > 100 }">style="color: red;font-weight:bold" </c:if>><c:if test="${bindNum > 0 }"><fmt:formatNumber value="${ bindNum >0 ? item.bind_num*100/bindNum : 0}" pattern="#0.00"/> %</c:if></td>
						</tr>
					</c:forEach>
					<tr>
					  <td style="font-weight: bold;"><spring:message code='str193'/></td>
					  <td></td>
					  <td>${totalBind }</td>
					  <td>100%</td>
					  <td>${bindNum }</td>
					  <td>100%</td>
					</tr>
				</table>
				</div>
			</div>
		</div>
</body>
</html>
