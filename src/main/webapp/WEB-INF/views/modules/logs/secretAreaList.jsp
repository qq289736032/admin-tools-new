<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str1014'/></title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/area/secretAreaList"><spring:message code='str1014'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/area/secretAreaList" method="post" class="breadcrumb form-search">
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
		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str1015'/>10<spring:message code='str1016'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr>
					<td width='200'><spring:message code='str637'/></td><td width='200'>${active[0].num}</td>
				</tr>
				<tr>
					<td>10<spring:message code='str1017'/></td><td><c:if test="${tenParticipantNums[0].cou ==null}"> 0</c:if>
					<c:if test="${tenParticipantNums[0].cou !=null}">${tenParticipantNums[0].cou}</c:if></td>
				</tr>
				<tr>
					<td>PK<spring:message code='str1018'/></td><td><c:if test="${pkDieNum[0].num ==null}"> 0</c:if>
					<c:if test="${pkDieNum[0].num !=null}">${pkDieNum[0].num }</c:if></td>
				</tr>
				<tr>
					<td><spring:message code='str896'/>R<spring:message code='str1019'/></td><td><c:if test="${tenParticipantNum[0].cou ==null}"> 0</c:if>
					<c:if test="${tenParticipantNum[0].cou !=null}">${tenParticipantNum[0].cou}</c:if></td>
				</tr>
				<tr>
					<td><spring:message code='str896'/>R<spring:message code='str1020'/></td><td><c:if test="${tenParticipantNum[0].num ==null}"> 0</c:if>
					<c:if test="${tenParticipantNum[0].num !=null}">${tenParticipantNum[0].num}</c:if></td>
				</tr>
				<tr>
					<td><spring:message code='str1021'/></td><td><c:if test="${tenParticipantNum[0].sumVal ==null}"> 0</c:if>
					<c:if test="${tenParticipantNum[0].sumVal !=null}">${tenParticipantNum[0].sumVal}</c:if></td>
				</tr>
				<tr>
					<td><spring:message code='str1022'/>R<spring:message code='str1019'/></td><td><c:if test="${tenParticipantNum[0].cou ==null}"> 0</c:if>
					<c:if test="${tenParticipantNum[0].cou !=null}">${tenParticipantNum[1].cou}</c:if></td>
				</tr>
				<tr>
					<td><spring:message code='str1022'/>R<spring:message code='str1020'/></td><td><c:if test="${tenParticipantNum[0].num ==null}"> 0</c:if>
					<c:if test="${tenParticipantNum[0].num !=null}">${tenParticipantNum[1].num}</c:if></td>
				</tr>
				<tr>
					<td><spring:message code='str1023'/></td><td><c:if test="${tenParticipantNum[0].sumVal ==null}"> 0</c:if>
					<c:if test="${tenParticipantNum[0].sumVal !=null}">${tenParticipantNum[1].sumVal}</c:if></td>
				</tr>
			</table>
			</div>
 		</div>
 		
 		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str1015'/>16<spring:message code='str1016'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
 			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr>
					<td width='200'>16<spring:message code='str1017'/></td><td width='200'><c:if test="${sixteenParticipantNums[0].cou ==null}"> 0</c:if>
					<c:if test="${sixteenParticipantNums[0].cou !=null}">${sixteenParticipantNums[0].cou}</c:if></td>
				</tr>
				<tr>
					<td>PK<spring:message code='str1018'/></td><td><c:if test="${sixteenPkDieNum[0].num ==null}"> 0</c:if>
					<c:if test="${sixteenPkDieNum[0].num !=null}">${sixteenPkDieNum[0].num }</c:if></td>
				</tr>
				<tr>
					<td><spring:message code='str896'/>R<spring:message code='str1019'/></td><td><c:if test="${sixteenParticipantNum[0].cou ==null}"> 0</c:if>
					<c:if test="${sixteenParticipantNum[0].cou !=null}">${sixteenParticipantNum[0].cou}</c:if></td>
				</tr>
				<tr>
					<td><spring:message code='str896'/>R<spring:message code='str1020'/></td><td><c:if test="${sixteenParticipantNum[0].num ==null}"> 0</c:if>
					<c:if test="${sixteenParticipantNum[0].num !=null}">${sixteenParticipantNum[0].num}</c:if></td>
				</tr>
				<tr>
					<td><spring:message code='str1021'/></td><td><c:if test="${sixteenParticipantNum[0].sumVal ==null}"> 0</c:if>
					<c:if test="${sixteenParticipantNum[0].sumVal !=null}">${sixteenParticipantNum[0].sumVal}</c:if></td>
				</tr>
				<tr>
					<td><spring:message code='str1022'/>R<spring:message code='str1019'/></td><td><c:if test="${sixteenParticipantNum[0].cou ==null}"> 0</c:if>
					<c:if test="${sixteenParticipantNum[0].cou !=null}">${sixteenParticipantNum[1].cou}</c:if></td>
				</tr>
				<tr>
					<td><spring:message code='str1022'/>R<spring:message code='str1020'/></td><td><c:if test="${sixteenParticipantNum[0].num ==null}"> 0</c:if>
					<c:if test="${sixteenParticipantNum[0].num !=null}">${sixteenParticipantNum[1].num}</c:if></td>
				</tr>
				<tr>
					<td><spring:message code='str1023'/></td><td><c:if test="${sixteenParticipantNum[0].sumVal ==null}"> 0</c:if>
					<c:if test="${sixteenParticipantNum[0].sumVal !=null}">${sixteenParticipantNum[1].sumVal}</c:if></td>
				</tr>
			</table>
			</div>
		</div>
 	</div>
	<div class="row-fluid">
 		<div class="span12 panel panel-primary">
 		<div class="panel-heading">10<spring:message code='str1024'/>16<spring:message code='str1025'/>rool<spring:message code='str1026'/></div>
 		<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<tr>
						<th width='200'></th><th width='200'>10<spring:message code='str1025'/>roll<spring:message code='str814'/></th><th width='200'>16<spring:message code='str1025'/>roll<spring:message code='str814'/></th>
					</tr>
					<c:forEach items="${roolNum}" var="item">
					<tr>
						<td>${item.key}</td>
						<td>${item.value.tenNum}</td>
						<td>${item.value.sixteenNum}</td>
					</tr>
					</c:forEach>
			</table>
			</div>
		</div>
 	</div>
 	

 	
</body>
</html>
