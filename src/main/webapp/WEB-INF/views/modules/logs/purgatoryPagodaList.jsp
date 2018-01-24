<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str933'/></title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/pagoda/purgatoryPagodaList"><spring:message code='str933'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/pagoda/purgatoryPagodaList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" /> 
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input-small" maxlength="20" readonly="readonly" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}',minDate:'#F{$dp.$D(\'endDatePicker\',{d:-6})}'})">
		-&nbsp;<input type="text" name="createDateEnd" class="input-small" readonly="readonly" value="${paramMap.createDateEnd}" id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}',maxDate:'#F{$dp.$D(\'startDatePicker\',{d:6})}'})">
		<label><spring:message code='str55'/></label>
		<input id="serverIds" name="serverIds" value="${paramMap.serverIds}" readonly/>
        <input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();" />
	</form>
	<tags:message content="${message}" />
	<div class="row-fluid">
 		<div class="span12 panel panel-primary">
		<div class="panel-heading"><spring:message code='str934'/></div>
 		<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<tr>
						<th width='200'></th><th width='200'><spring:message code='str206'/></th>
					</tr>
					<tr>
						<td><spring:message code='str935'/></td><td>${active[0].num}</td>
					</tr>
					<c:set var="flag" value ="1"/>
					<c:forEach items="${climbingTowerNum}" var="ex">
						<c:forEach begin="${flag}" end="${ex.floor_id-1}">
							<tr>
								<td><spring:message code='str936'/>${flag}<spring:message code='str937'/></td><td>0</td>
							</tr>
							<c:set var="flag" value="${flag+1}"/>
						</c:forEach>
						<tr><td><spring:message code='str936'/>${ex.floor_id}<spring:message code='str937'/></td><td>${ex.cou}</td></tr>
						<c:set var="flag" value="${flag+1}"/>
					</c:forEach>
			</table>
			</div>
		</div>
 	</div>
 	<div class="row-fluid">
 		<div class="span6 panel panel-primary">
 			<div class="panel-heading"><spring:message code='str933'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">

					<tr>
						<th width='200'><spring:message code='str637'/></th><td width='200'>${active[0].num}</td>
					</tr>
					<tr>
						<th><spring:message code='str938'/></th><td><c:if test="${integralOutput[0].num ==null}"> 0</c:if>
					<c:if test="${integralOutput[0].num !=null}">${integralOutput[0].num}</c:if></td>
					</tr>
					<tr>
						<th><spring:message code='str939'/></th><td>${climbingTower[0].num}</td>
					</tr>
					<tr>
						<th><spring:message code='str940'/></th><td><c:if test="${acquireAward[0].num ==null}"> 0</c:if>
					<c:if test="${acquireAward[0].num !=null}">${acquireAward[0].num}</c:if></td>
					</tr>
					<tr>
						<th><spring:message code='str941'/></th><td>${integralOutput[0].num-consumeAward[0].num}</td>
					</tr>
			</table>
			</div>
 		</div>
 		<div class="span6 panel panel-primary">
 			<div class="panel-heading"><spring:message code='str942'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">

					<tr>
						<th width='200'></th><th width='200'><spring:message code='str943'/></th><th width='200'><spring:message code='str814'/></th>
					</tr>
					<tr>
						<td>2<spring:message code='str944'/></td><td>${kbshengji[0].cou }</td><td><c:if test="${kbshengji[0].num == null}"> 0</c:if>
					<c:if test="${kbshengji[0].num != null}">${kbshengji[0].num}</c:if></td>
					</tr>
					<tr>
						<td>1<spring:message code='str944'/></td><td>${sbjinjie[0].cou }</td><td><c:if test="${sbjinjie[0].num == null}"> 0</c:if>
					<c:if test="${sbjinjie[0].num != null}">${sbjinjie[0].num}</c:if></td>
					</tr>
					<tr>
						<td><spring:message code='str945'/></td><td>${jingtong[0].cou }</td><td><c:if test="${jingtong[0].num == null}"> 0</c:if>
					<c:if test="${jingtong[0].num != null}">${jingtong[0].num}</c:if></td>
					</tr>
			</table>
			</div>
		</div>
 	</div>
 	
</body>
</html>
