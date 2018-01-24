<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1265'/></title>
	<meta name="decorator" content="default"/>
    <script type="text/javascript">
//       $(function(){
//           $('.tooltip-demo').tooltip({
//               selector: "a[data-toggle=tooltip]"
//           })
//       });

    </script>
    <style type="text/css">
        table{table-layout: fixed;}
    </style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tools/gift/viewCodeList"><spring:message code='str1525'/></a></li>
		<li ><a href="${ctx}/tools/gift/viewCreateName"><spring:message code='str1526'/></a></li>
	</ul>
    <form:form id="searchForm" action="${ctx}/tools/gift/viewCodeList" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <label><spring:message code='str1527'/></label>
        <input type="text" name="code" value="${paramMap.code}"/>
		<label><spring:message code='str1528'/>(<spring:message code='str1529'/>)</label>
        <input type="text" name="gift_name"/>
		<label><spring:message code='str1530'/>(<spring:message code='str1529'/>)</label>
        <select id="pid" name="pid">
			<%--<form:option value="" label="<spring:message code='str627'/>"/>--%>
			<c:forEach var="platform" items="${fns:getGamePlatformList()}">
				<option id="" value="${platform.pid}" <c:if test="${paramMap.pid == platform.pid}">selected="selected" </c:if>>${platform.name}</option>
			</c:forEach>
		</select>
		<label><spring:message code='str1325'/>(<spring:message code='str1529'/>)</label>
		<select id="type" name="type">
					<option value=""><spring:message code='str1129'/></option>
					<option value="1"><spring:message code='str1531'/></option>
                    <option value="0"><spring:message code='str1532'/></option>
        </select>
        &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
    </form:form>
	<tags:message content="${message}"/>
    <form id="tableForm" action="">
	<table id="contentTable" class="table table-striped table-bordered table-condensed tooltip-demo">
		<tr><!--<th>ID</th>--><th><spring:message code='str1533'/></th><th><spring:message code='str1243'/></th><th><spring:message code='str1534'/></th><th><spring:message code='str1535'/></th><th><spring:message code='str1536'/></th><th><spring:message code='str1398'/></th><th><spring:message code='str1294'/></th></tr>
		<c:forEach items="${page.list}" var="item">
			<tr>
                <!--<td>${item.id}</td>-->
                <td>${item.code}</td>
                <td>${item.gift_name}</td>
				<td>${fns:getGamePlatformNameById(item.platform_id,item.platform_id)}</td>
                <td>${item.use_name}</td>
				<td><fmt:formatDate value="${item.use_time}" type="both"/></td>
				<td><fmt:formatDate value="${item.create_time}" type="both"/></td>
				<td><c:choose>
                    <c:when test="${item.status == 1}"><span class="label label-info"><spring:message code='str1531'/></span></c:when>
                    <c:when test="${item.status == 0}"><span class="label label-warning"><spring:message code='str1532'/></span></c:when>
                    <c:otherwise><span class="label label-important"><spring:message code='str1248'/></span></c:otherwise>
                   </c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
	</form>
	
    
    
</body>
</html>
