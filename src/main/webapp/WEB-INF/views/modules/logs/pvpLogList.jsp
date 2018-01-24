<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str946'/></title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/pvp/pvpLogList"><spring:message code='str947'/></a></li>
		<li><a href="${ctx}/log/pvp/pvpLogFormList"><spring:message code='str948'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/pvp/pvpLogList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" /> 
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input-small" maxlength="20" readonly="readonly" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd" class="input-small" readonly="readonly" value="${paramMap.createDateEnd}" id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();" />
	</form>
	<tags:message content="${message}" />
 	<div class="row-fluid">
		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str953'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th><spring:message code='str198'/></th><th><spring:message code='str814'/></th></tr>
				<c:forEach items="${pvpOpen}" var="item">
					<tr>
						<td>${paramMap.startDate}</td>
               			<td>${item.cou}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
 		</div>
 		
 		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str954'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
 			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th><spring:message code='str8'/></th><th><spring:message code='str206'/></th></tr>
				<c:forEach items="${pvpEveryday}" var="item">
					<tr>
						<td>${item.po}</td>
               			<td>${item.cou}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
		</div>
 	</div>
 	<div class="row-fluid">
		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str955'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th><spring:message code='str3'/></th><th><spring:message code='str206'/></th></tr>
				<c:forEach items="${pvpSuccess}" var="item">
					<tr>
						<td>${item.pipeiTime}</td>
               			<td>${item.pipei}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
 		</div>
 		
 		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str956'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
 			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th><spring:message code='str3'/></th><th><spring:message code='str206'/></th></tr>
				<c:forEach items="${pvpLose}" var="item">
					<tr>
						<td>${item.pipeiTime}</td>
               			<td>${item.pipei}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
		</div>
 	</div>
 	
</body>
</html>
