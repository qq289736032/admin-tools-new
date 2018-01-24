<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str792'/></title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/log/funnel/funnelStatisticsList"><spring:message code='str793'/></a></li>
		<li class="active"><a href="${ctx}/log/funnel/funnelStatisticsFormList"><spring:message code='str792'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/funnel/funnelStatisticsFormList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" /> 
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input-small" maxlength="20" readonly="readonly" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd" class="input-small" readonly="readonly" value="${paramMap.createDateEnd}" id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		<label><spring:message code='str55'/></label>
		<input id="serverIds" name="serverIds" value="${paramMap.serverIds}" readonly/>
        <input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();" />
	</form>
	<tags:message content="${message}" />
	
	<div class="row-fluid">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr>
					<th width='200'><spring:message code='str794'/></th><td>${active[0].num }</td>
				</tr>
				<tr>
					<th width='200'><spring:message code='str795'/></th><th width='200'><spring:message code='str787'/></th>
				</tr>
				<c:forEach items="${funnelAdvance}" var="item">
					<c:if test="${item.level==2 }"><tr><td>1<spring:message code='str796'/>2</td><td>${item.num }</td></tr></c:if>
					<c:if test="${item.level==3 }"><tr><td>2<spring:message code='str796'/>3</td><td>${item.num }</td></tr></c:if>
					<c:if test="${item.level==4 }"><tr><td>3<spring:message code='str796'/>4</td><td>${item.num }</td></tr></c:if>
					<c:if test="${item.level==5 }"><tr><td>4<spring:message code='str796'/>5</td><td>${item.num }</td></tr></c:if>
					<c:if test="${item.level==6 }"><tr><td>5<spring:message code='str796'/>6</td><td>${item.num }</td></tr></c:if>
					<c:if test="${item.level==7 }"><tr><td>6<spring:message code='str796'/>7</td><td>${item.num }</td></tr></c:if>
					<c:if test="${item.level==8 }"><tr><td>7<spring:message code='str796'/>8</td><td>${item.num }</td></tr></c:if>
					<c:if test="${item.level==9 }"><tr><td>8<spring:message code='str796'/>9</td><td>${item.num }</td></tr></c:if>
					<c:if test="${item.level==10 }"><tr><td>9<spring:message code='str796'/>10</td><td>${item.num }</td></tr></c:if>
					
				</c:forEach>
			</table>
 	</div>	
</body>
</html>
