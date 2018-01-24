<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str793'/></title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/funnel/funnelStatisticsList"><spring:message code='str793'/></a></li>
		<li><a href="${ctx}/log/funnel/funnelStatisticsFormList"><spring:message code='str792'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/funnel/funnelStatisticsList" method="post" class="breadcrumb form-search">
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
					<th width='200'><spring:message code='str797'/></th><th width='200'><spring:message code='str787'/></th>
				</tr>
				<c:forEach items="${funnelDistribution}" var="item">
					<c:if test="${item.level==1 }"><tr><td><spring:message code='str798'/></td><td>${item.num }</td></tr></c:if>
					<c:if test="${item.level==2 }"><tr><td><spring:message code='str799'/></td><td>${item.num }</td></tr></c:if>
					<c:if test="${item.level==3 }"><tr><td><spring:message code='str800'/></td><td>${item.num }</td></tr></c:if>
					<c:if test="${item.level==4 }"><tr><td><spring:message code='str801'/></td><td>${item.num }</td></tr></c:if>
					<c:if test="${item.level==5 }"><tr><td><spring:message code='str802'/></td><td>${item.num }</td></tr></c:if>
					<c:if test="${item.level==6 }"><tr><td><spring:message code='str803'/></td><td>${item.num }</td></tr></c:if>
					<c:if test="${item.level==7 }"><tr><td><spring:message code='str804'/></td><td>${item.num }</td></tr></c:if>
					<c:if test="${item.level==8 }"><tr><td><spring:message code='str805'/></td><td>${item.num }</td></tr></c:if>
					<c:if test="${item.level==9 }"><tr><td><spring:message code='str806'/></td><td>${item.num }</td></tr></c:if>
					<c:if test="${item.level==10 }"><tr><td><spring:message code='str807'/></td><td>${item.num }</td></tr></c:if>
				</c:forEach>
			</table>
 	</div>	
</body>
</html>
