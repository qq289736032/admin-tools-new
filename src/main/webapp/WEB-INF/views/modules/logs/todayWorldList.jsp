<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1075'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="${ctx}/log/rank/todayMax"><spring:message code='str1080'/></a></li>
		<li class="active"><a href="${ctx}/log/rank/pageWorld"><spring:message code='str1077'/>(<spring:message code='str1078'/>)</a></li>
	</ul>
	<form id="searchForm" method="post" class="breadcrumb form-search">

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input name="createDateStart" id="createTimeStart" maxlength="50"  value="${paramMap.createDateStart}" class="input-small required" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
		<label><spring:message code='str55'/></label>
	    <input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/>
		<%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();">--%>
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
				<th><spring:message code='str56'/></th>
				<th><spring:message code='str768'/></th>
				<th>30w-50w</th>
				<th>50w-80w</th>
				<th>80w-100w</th>
				<th>100w-200w</th>
				<th>200w-500w</th>
				<th>500w+</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page}" var="item">
			<tr>
					<td>${item.pid}</td>
					<td>${fns:getGameServer(item.world_id).getName()}</td>
					<td>${item.pageMaxOne}</td>
					<td>${item.pageMaxTwo}</td>
				    <td>${item.pageMaxThree}</td>
					<td>${item.pageMaxFour}</td>
					<td>${item.pageMaxFive}</td>
					<td>${item.maxFive}</td>
					  
					</tr>
		</c:forEach>
		</tbody>
		<script type="text/javascript">

	 function page(n,s){
         $("#pageNo").val(n);
         $("#pageSize").val(s);
         var   serverIds=$("#serverIds").val();
         if(serverIds==""){
        	 tips("<spring:message code='str1079'/>") ;
             return  false;
         }
	         $("#searchForm").attr("action","${ctx}/log/rank/pageWorld");
	         $("#searchForm").submit();
         }
         
     
	</script>
	</table>
<%-- 	<div class="pagination">${page}</div>
 --%>

</body>
</html>
