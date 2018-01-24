<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str946'/></title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/log/pvp/pvpLogList"><spring:message code='str947'/></a></li>
		<li class="active"><a href="${ctx}/log/pvp/pvpLogFormList"><spring:message code='str948'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/pvp/pvpLogFormList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" /> 
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input-small" maxlength="20" readonly="readonly" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd" class="input-small" readonly="readonly" value="${paramMap.createDateEnd}" id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		&nbsp;<label><spring:message code='str55'/></label>
		<input type="text" id="serverIds" name="serverIds" value="${paramMap.serverIds}" readonly/>
        <input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();" />
	</form>
	<tags:message content="${message}" />
	<div class="row-fluid">
 			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
 			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th><spring:message code='str785'/></th><th colspan="10"></th></tr>
				<tr><th></th><th>1<spring:message code='str703'/></th><th>2<spring:message code='str703'/></th><th>3<spring:message code='str703'/></th><th>4<spring:message code='str703'/></th><th>5<spring:message code='str703'/></th><th>6<spring:message code='str703'/></th><th>7<spring:message code='str703'/></th><th>8<spring:message code='str703'/></th><th>9<spring:message code='str703'/></th><th>10<spring:message code='str703'/></th></tr>
				<tr>
					<td><spring:message code='str949'/></td>
					<td><c:if test="${oneServerPvpJoin[0].one ==null}"> 0</c:if><c:if test="${oneServerPvpJoin[0].one !=null}">${oneServerPvpJoin[0].one}</c:if></td>
					<td><c:if test="${oneServerPvpJoin[0].two ==null}"> 0</c:if><c:if test="${oneServerPvpJoin[0].two !=null}">${oneServerPvpJoin[0].two}</c:if></td>
					<td><c:if test="${oneServerPvpJoin[0].three ==null}"> 0</c:if><c:if test="${oneServerPvpJoin[0].three !=null}">${oneServerPvpJoin[0].three}</c:if></td>
					<td><c:if test="${oneServerPvpJoin[0].four ==null}"> 0</c:if><c:if test="${oneServerPvpJoin[0].four !=null}">${oneServerPvpJoin[0].four}</c:if></td>
					<td><c:if test="${oneServerPvpJoin[0].five ==null}"> 0</c:if><c:if test="${oneServerPvpJoin[0].five !=null}">${oneServerPvpJoin[0].five}</c:if></td>
					<td><c:if test="${oneServerPvpJoin[0].six ==null}"> 0</c:if><c:if test="${oneServerPvpJoin[0].six !=null}">${oneServerPvpJoin[0].six}</c:if></td>
					<td><c:if test="${oneServerPvpJoin[0].seven ==null}"> 0</c:if><c:if test="${oneServerPvpJoin[0].seven !=null}">${oneServerPvpJoin[0].seven}</c:if></td>
					<td><c:if test="${oneServerPvpJoin[0].enght ==null}"> 0</c:if><c:if test="${oneServerPvpJoin[0].enght !=null}">${oneServerPvpJoin[0].enght}</c:if></td>
					<td><c:if test="${oneServerPvpJoin[0].nine ==null}"> 0</c:if><c:if test="${oneServerPvpJoin[0].nine !=null}">${oneServerPvpJoin[0].nine}</c:if></td>
					<td><c:if test="${oneServerPvpJoin[0].ten ==null}"> 0</c:if><c:if test="${oneServerPvpJoin[0].ten !=null}">${oneServerPvpJoin[0].ten}</c:if></td>
				</tr>
			</table>
			</div>
		
 	</div>
 	<div class="row-fluid">
		<div class="span6 panel panel-primary" style="border:0px solid black;">
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th></th><th><spring:message code='str814'/></th><th><spring:message code='str206'/></th><th><spring:message code='str950'/></th></tr>
				<tr><td><spring:message code='str951'/></td>
					<td><c:if test="${oneServerBuy[0].cou ==null}"> 0</c:if><c:if test="${oneServerBuy[0].cou !=null}">${oneServerBuy[0].cou}</c:if></td>
					<td><c:if test="${oneServerBuy[0].num ==null}"> 0</c:if><c:if test="${oneServerBuy[0].num !=null}">${oneServerBuy[0].num}</c:if></td>
					<td><c:if test="${oneServerBuy[0].value ==null}"> 0</c:if><c:if test="${oneServerBuy[0].value !=null}">${oneServerBuy[0].value}</c:if></td>
				</tr>
				
			</table>
			</div>
 		</div>
 		
 		<div class="span6 panel panel-primary" style="border:0px solid black;">
 			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
 			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th></th><th><spring:message code='str814'/></th><th><spring:message code='str206'/></th><th><spring:message code='str950'/></th></tr>
				<tr><td><spring:message code='str952'/></td>
				<td><c:if test="${oneServerConsume[0].cou ==null}"> 0</c:if><c:if test="${oneServerConsume[0].cou !=null}">${oneServerConsume[0].cou}</c:if></td>
				<td><c:if test="${oneServerConsume[0].num ==null}"> 0</c:if><c:if test="${oneServerConsume[0].num !=null}">${oneServerConsume[0].num}</c:if></td>
				<td><c:if test="${oneServerConsume[0].value ==null}"> 0</c:if><c:if test="${oneServerConsume[0].value !=null}">${oneServerConsume[0].value}</c:if></td>
				</tr>
			</table>
			</div>
		</div>
 	</div>
 	
</body>
</html>
