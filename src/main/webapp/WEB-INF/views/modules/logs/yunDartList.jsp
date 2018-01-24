<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str1095'/></title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/dart/yunDartList"><spring:message code='str1095'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/dart/yunDartList" method="post" class="breadcrumb form-search">
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
			<div class="panel-heading"><spring:message code='str1095'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr>
					<td width='200'><spring:message code='str637'/></td><td width='200'>${active[0].num}</td>
				</tr>
				<tr>
					<td width='200'><spring:message code='str1096'/></td><td width='200'>${yunDartStatistics[0].cou }</td>
				</tr>
				<tr>
					<td width='200'><spring:message code='str1097'/></td><td width='200'><c:if test="${dartCarKill[0].cou == null }">0</c:if><c:if test="${dartCarKill[0].cou != null }">${dartCarKill[0].cou }</c:if></td>
				</tr>
				<tr>
					<td width='200'><spring:message code='str1098'/></td><td width='200'><c:if test="${yunDartStatistics[0].succe == null }">0</c:if><c:if test="${yunDartStatistics[0].succe != null }">${yunDartStatistics[0].succe }</c:if></td>
				</tr>
				<tr>
					<td width='200'><spring:message code='str1099'/></td><td width='200'><c:if test="${yunDartStatistics[0].defeate == null }">0</c:if><c:if test="${yunDartStatistics[0].defeate != null }">${yunDartStatistics[0].defeate }</c:if></td>
				</tr>
			</table>
			</div>
 		</div>
 		
 		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str1100'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
 			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr>
					<th width='200'></th><th width='200'><spring:message code='str206'/></th><th width='200'><spring:message code='str814'/></th>
				</tr>
				<tr>
					<td><spring:message code='str1101'/></td>
					<td><c:if test="${yunDartConsumesG[0].cou==null }">0</c:if><c:if test="${yunDartConsumesG[0].cou!=null }">${yunDartConsumesG[0].cou}</c:if></td>
					<td><c:if test="${yunDartConsumesG[0].num==null }">0</c:if><c:if test="${yunDartConsumesG[0].num!=null }">${yunDartConsumesG[0].num }</c:if></td>
				</tr>
					
				<tr>
					<td><spring:message code='str1102'/></td>
					<td><c:if test="${yunDartConsumeM[0].cou==null }">0</c:if><c:if test="${yunDartConsumeM[0].cou!=null }">${yunDartConsumeM[0].cou }</c:if></td>
					<td><c:if test="${yunDartConsumeM[0].num==null }">0</c:if><c:if test="${yunDartConsumeM[0].num!=null }">${yunDartConsumeM[0].num }</c:if></td>
				</tr>
					

			</table>
			</div>
		</div>
 	</div>
 	<div class="row-fluid">
 		<div class="span12 panel panel-primary">
 		<div class="panel-heading"><spring:message code='str1103'/></div>
 		<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr>
					<th width='200'></th><th width='200'><spring:message code='str1104'/></th><th width='200'><spring:message code='str1105'/></th><th width='200'><spring:message code='str765'/></th><th width='200'><spring:message code='str764'/></th>
				</tr>
				<c:forEach items="${dartCarStatistics}" var="item">
					<tr>
						<td><c:if test='${item.meiren_id==5 }'><spring:message code='str1106'/></c:if>
							<c:if test='${item.meiren_id==4 }'><spring:message code='str1635'/></c:if>
							<c:if test='${item.meiren_id==3 }'><spring:message code='str1108'/></c:if>
							<c:if test='${item.meiren_id==2 }'><spring:message code='str1109'/></c:if>
							<c:if test='${item.meiren_id==1 }'><spring:message code='str1110'/></c:if>
						
						</td><td>${item.cou}</td><td>${item.num}</td><td>${item.succe}</td><td>${item.defeate}</td>
					</tr>
				</c:forEach>	
			</table>
			</div>
		</div>
 	</div>
	
</body>
</html>
