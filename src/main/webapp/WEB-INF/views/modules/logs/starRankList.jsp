<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str1045'/></title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/star/starRankList"><spring:message code='str1045'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/star/starRankList" method="post" class="breadcrumb form-search">
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
 			<div class="" style="width:100%;overflow:scroll;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr>
					<th><spring:message code='str1046'/></th><th><spring:message code='str1047'/>+1</th><th><spring:message code='str1047'/>+2</th><th><spring:message code='str1047'/>+3</th><th><spring:message code='str1047'/>+4</th><th><spring:message code='str1047'/>+5</th><th><spring:message code='str1047'/>+6</th><th><spring:message code='str1047'/>+7</th><th><spring:message code='str1047'/>+8</th><th><spring:message code='str1047'/>+9</th><th><spring:message code='str1047'/>+10</th>
					<th><spring:message code='str1048'/>+1</th><th><spring:message code='str1048'/>+2</th><th><spring:message code='str1048'/>+3</th><th><spring:message code='str1048'/>+4</th><th><spring:message code='str1048'/>+5</th><th><spring:message code='str1048'/>+6</th><th><spring:message code='str1048'/>+7</th><th><spring:message code='str1048'/>+8</th><th><spring:message code='str1048'/>+9</th><th><spring:message code='str1048'/>+10</th>
					<th><spring:message code='str1049'/>+1</th><th><spring:message code='str1049'/>+2</th><th><spring:message code='str1049'/>+3</th><th><spring:message code='str1049'/>+4</th><th><spring:message code='str1049'/>+5</th><th><spring:message code='str1049'/>+6</th><th><spring:message code='str1049'/>+7</th><th><spring:message code='str1049'/>+8</th><th><spring:message code='str1049'/>+9</th><th><spring:message code='str1049'/>+10</th>
					<th><spring:message code='str1050'/>+1</th><th><spring:message code='str1050'/>+2</th><th><spring:message code='str1050'/>+3</th><th><spring:message code='str1050'/>+4</th><th><spring:message code='str1050'/>+5</th><th><spring:message code='str1050'/>+6</th><th><spring:message code='str1050'/>+7</th><th><spring:message code='str1050'/>+8</th><th><spring:message code='str1050'/>+9</th><th><spring:message code='str1050'/>+10</th>
				</tr>
				<c:forEach items="${star}" var="item">
					<tr>
						<td>${item.kong}</td>
						<td>${item.greenQuality1}</td>
						<td>${item.greenQuality2}</td>
						<td>${item.greenQuality3}</td>
						<td>${item.greenQuality4}</td>
						<td>${item.greenQuality5}</td>
						<td>${item.greenQuality6}</td>
						<td>${item.greenQuality7}</td>
						<td>${item.greenQuality8}</td>
						<td>${item.greenQuality9}</td>
						<td>${item.greenQuality10}</td>
						<td>${item.blueQuality1}</td>
						<td>${item.blueQuality2}</td>
						<td>${item.blueQuality3}</td>
						<td>${item.blueQuality4}</td>
						<td>${item.blueQuality5}</td>
						<td>${item.blueQuality6}</td>
						<td>${item.blueQuality7}</td>
						<td>${item.blueQuality8}</td>
						<td>${item.blueQuality9}</td>
						<td>${item.blueQuality10}</td>
						<td>${item.purpleQuality1}</td>
						<td>${item.purpleQuality2}</td>
						<td>${item.purpleQuality3}</td>
						<td>${item.purpleQuality4}</td>
						<td>${item.purpleQuality5}</td>
						<td>${item.purpleQuality6}</td>
						<td>${item.purpleQuality7}</td>
						<td>${item.purpleQuality8}</td>
						<td>${item.purpleQuality9}</td>
						<td>${item.purpleQuality10}</td>
						<td>${item.goldenQuality1}</td>
						<td>${item.goldenQuality2}</td>
						<td>${item.goldenQuality3}</td>
						<td>${item.goldenQuality4}</td>
						<td>${item.goldenQuality5}</td>
						<td>${item.goldenQuality6}</td>
						<td>${item.goldenQuality7}</td>
						<td>${item.goldenQuality8}</td>
						<td>${item.goldenQuality9}</td>
						<td>${item.goldenQuality10}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
 	</div>
</body>
</html>
