<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str183'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/medal/vipMedalList">VIP勋章</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/medal/vipMedalList" method="post" class="breadcrumb form-search">
		<%-- <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/> --%>
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input name="startDate" id="startDate" maxlength="50"  value="${paramMap.startDate}" class="input-small required" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
	  
	    <label><spring:message code='str626'/> <spring:message code='str4'/></label>
		<select name="viplevel">
			<option value=""><spring:message code='str627'/></option>
			<c:forEach items="${vipLevelMap}" var="item">
				<option value="${item.key}"
						<c:if test="${item.key==paramMap.viplevel}">selected="selected"</c:if>
						>${item.value}</option>
			</c:forEach>
		</select>
		
		<label><spring:message code='str55'/></label>
	    <input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/>
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	
	<br/>
	
	<div class="row-fluid">
 		<div class="span12 panel panel-primary">
 		<div class="panel-heading"><spring:message code='str184'/></div>
 		<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th><spring:message code='str85'/></th><th><spring:message code='str206'/></th></tr>
				<c:forEach items="${medalGrade}" var="item">
					<tr>
						<td>${item.level}</td>
               			<td>${item.num}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
		</div>
 	</div>
 	
 	<div class="row-fluid">
 		<div class="span12 panel panel-primary">
 		<div class="panel-heading"><spring:message code='str185'/></div>
 		<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th width="25%"><spring:message code='str85'/></th><th width="25%"><spring:message code='str748'/></th><%-- <th width="25%"><spring:message code='str749'/><spring:message code='str851'/></th><th width="25%"><spring:message code='str750'/><spring:message code='str851'/></th> --%></tr>
				<c:forEach items="${medalStatistics }" var="item">  
				<%-- <c:if test="${item.min_num != 0 || item.max_num != 0}" > --%>
				 <tr>
						<td>${item.level}</td>
              		  	<td>${item.id_num}</td>
              		  	<%-- <td>${item.min_num}</td>
              		  	<td>${item.max_num}</td> --%>
					</tr>
				<%-- </c:if> --%>
				</c:forEach>
			</table>
			</div>
		</div>
 	</div>
	
</body>
</html>