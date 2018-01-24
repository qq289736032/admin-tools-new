<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str1054'/></title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/taskTeam/taskTeamList"><spring:message code='str1054'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/taskTeam/taskTeamList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" /> 
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input-small" maxlength="20" readonly="readonly" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd" class="input-small" readonly="readonly" value="${paramMap.createDateEnd}" id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		<label><spring:message code='str55'/></label>
		<input type="text" id="serverIds" name="serverIds" value="${paramMap.serverIds}" readonly/>
        <input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();" />
	</form>
	<tags:message content="${message}" />
	<div class="row-fluid">
		<div class="span6 panel panel-primary" style="border:0px solid black;">
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th><spring:message code='str1054'/></th><th><spring:message code='str790'/></th></tr>	
				
				<c:forEach items="${teamTask}" var="item">
					<c:if test="${item.guankaid==1}"><tr><td>40<spring:message code='str1055'/></td><td>${item.cou}</td></tr></c:if>
					<c:if test="${item.guankaid==2}"><tr><td>50<spring:message code='str1055'/></td><td>${item.cou}</td></tr></c:if>
					<c:if test="${item.guankaid==3}"><tr><td>60<spring:message code='str1055'/></td><td>${item.cou}</td></tr></c:if>
					<c:if test="${item.guankaid==4}"><tr><td>70<spring:message code='str1055'/></td><td>${item.cou}</td></tr></c:if>
					<c:if test="${item.guankaid==5}"><tr><td>80<spring:message code='str1055'/></td><td>${item.cou}</td></tr></c:if>
					<c:if test="${item.guankaid==6}"><tr><td>90<spring:message code='str1055'/></td><td>${item.cou}</td></tr></c:if>
					<c:if test="${item.guankaid==7}"><tr><td>100<spring:message code='str1055'/></td><td>${item.cou}</td></tr></c:if>
				</c:forEach>
				
			</table>
			</div>
 		</div>
 		
 		<div class="span6 panel panel-primary" style="border:0px solid black;">
 			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
 			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th></th><th><spring:message code='str1056'/>1<spring:message code='str1057'/></th><th><spring:message code='str1056'/>2<spring:message code='str1057'/></th><th><spring:message code='str1056'/>3<spring:message code='str1057'/></th><th><spring:message code='str1056'/>4<spring:message code='str1057'/></th><th><spring:message code='str1056'/>5<spring:message code='str1057'/></th><th><spring:message code='str1058'/></th></tr>	
				<c:forEach items="${taskTeamBrand }" var="item">
				<c:if test="${item.guanka_id==1 }">
					<tr><td>40<spring:message code='str1055'/></td><td>${item.one }</td><td>${item.two }</td><td>${item.three }</td><td>${item.four }</td><td>${item.five }</td><td>${item.one_key }</td></tr>
				</c:if>
				<c:if test="${item.guanka_id==2 }">
					<tr><td>50<spring:message code='str1055'/></td><td>${item.one }</td><td>${item.two }</td><td>${item.three }</td><td>${item.four }</td><td>${item.five }</td><td>${item.one_key }</td></tr>
				</c:if>
				<c:if test="${item.guanka_id==3 }">
					<tr><td>60<spring:message code='str1055'/></td><td>${item.one }</td><td>${item.two }</td><td>${item.three }</td><td>${item.four }</td><td>${item.five }</td><td>${item.one_key }</td></tr>
				</c:if>
				<c:if test="${item.guanka_id==4 }">
					<tr><td>70<spring:message code='str1055'/></td><td>${item.one }</td><td>${item.two }</td><td>${item.three }</td><td>${item.four }</td><td>${item.five }</td><td>${item.one_key }</td></tr>
				</c:if>
				<c:if test="${item.guanka_id==5 }">
					<tr><td>80<spring:message code='str1055'/></td><td>${item.one }</td><td>${item.two }</td><td>${item.three }</td><td>${item.four }</td><td>${item.five }</td><td>${item.one_key }</td></tr>
				</c:if>
				<c:if test="${item.guanka_id==6 }">
					<tr><td>90<spring:message code='str1055'/></td><td>${item.one }</td><td>${item.two }</td><td>${item.three }</td><td>${item.four }</td><td>${item.five }</td><td>${item.one_key }</td></tr>
				</c:if>
				<c:if test="${item.guanka_id==7 }">
					<tr><td>100<spring:message code='str1055'/></td><td>${item.one }</td><td>${item.two }</td><td>${item.three }</td><td>${item.four }</td><td>${item.five }</td><td>${item.one_key }</td></tr>
				</c:if>
				</c:forEach>
				
			</table>
			</div>
		</div>
 	</div>
 	<div class="row-fluid">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr>
					<th width="200px"><spring:message code='str700'/></th><th width="200px"><spring:message code='str440'/></th>
				</tr>
				<tr>
					<th width="200px"><spring:message code='str1059'/></th><th width="200px">${firstGetNumber[0].cou}</th>
				</tr>
				<tr>
					<th width="200px"><spring:message code='str1060'/></th><th width="200px">${teamNumber[0].cou}</th>
				</tr>
				<tr>
					<th width="200px"><spring:message code='str1061'/></th><th width="200px">${teamPayNumber[0].cou}</th>
				</tr>
			</table>
 	</div>
 	
</body>
</html>
