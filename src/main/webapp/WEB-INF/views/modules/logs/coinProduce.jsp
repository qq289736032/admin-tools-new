﻿<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str753'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#s2id_operatorType").hide();
			$("#operatorType").multiselect({
				includeSelectAllOption:true,
				enableFiltering:true
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/moneyFlow/coinProduce"><spring:message code='str754'/></a></li>
		<li><a href="${ctx}/log/moneyFlow/coinProduceConsume"><spring:message code='str755'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/moneyFlow/coinProduce" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<%--<input type="text" id="startDate" name="startDate" readonly="readonly" value="${paramMap.startDate}" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'endDate\',{d:-7})}',maxDate:'#F{$dp.$D(\'endDate\')||\'%y-%M-%d\'}',onpicked:function(){endTime.focus();}})" />--%>
		<%--<input type="text" id="endDate" name="endDate" readonly="readonly" value="${paramMap.endDate}" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startDate\',{d:7})||\'%y-%M-%d\'}',minDate:'#F{$dp.$D(\'startDate\')}'})" />--%>
		<input type="text" name="logDay" id="logDay" class="input-small" readonly="readonly" maxlength="20" value="${paramMap.logDay}" id="startDatePicker" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
		&nbsp;
		<label><spring:message code='str654'/> <spring:message code='str4'/></label>
		<select id="operatorType" name="operatorType" multiple="multiple">
			<c:forEach items="${fns:getOperaTypeMap()}" var="item">
				<c:choose>
					<c:when test="${fn:length(paramMap.operatorTypeList)==0}">
						<option value="${item.key }">${item.value.chDes }</option>
					</c:when>
					<c:otherwise>
						<option value="${item.key}"
								<c:forEach items="${paramMap.operatorTypeList}" var="operator">
									<c:if test="${operator==item.key}">selected="selected"</c:if>
								</c:forEach>>
								${item.value.chDes}
						</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>

		<label><spring:message code='str55'/></label>
		<input id="serverIds" name="serverIds" value="${paramMap.serverIds}" readonly="readonly"/>
		<%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();">--%>
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>

	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th><spring:message code='str654'/></th><th><spring:message code='str756'/></th>	<th><spring:message code='str679'/></th><th><spring:message code='str757'/></th><th><spring:message code='str681'/></th><th><spring:message code='str682'/></th>
			<th><spring:message code='str758'/></th><th><spring:message code='str684'/></th><th><spring:message code='str685'/></th></tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="item">
			<tr>
			<td>${fns:getOperationType(item.operate_type)}</td>
	        <td>${item.vip + item.vip0}</td>
			<td>${item.vip0times + item.viptimes}</td>
			<td>${item.vip}</td>
			<td>${item.vipnum}</td>
			<td>${item.viptimes}</td>
			<td>${item.vip0}</td>
			<td>${item.vip0num}</td>
			<td>${item.vip0times}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>

	<div class="alert alert-info">
		<button type="button" class="close"  data-dismiss="alert">&times;</button>
		<strong><spring:message code='str671'/>!</strong><br/>
		1. <spring:message code='str672'/><br/>
		2. <spring:message code='str686'/><br/>
		3. <spring:message code='str674'/>VIP<spring:message code='str675'/>VIP<spring:message code='str676'/>VIP<spring:message code='str759'/>
	</div>

</body>
</html>
