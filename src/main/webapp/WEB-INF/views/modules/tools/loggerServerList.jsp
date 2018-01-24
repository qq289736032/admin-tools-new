<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1438'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<style type="text/css">
		span.input-group-btn {
			display: none;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tools/LoggerServer/list"><spring:message code='str1438'/></a></li>
	</ul>

	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th><spring:message code='str1285'/>ID</th><th><spring:message code='str1290'/>IP</th><th><spring:message code='str1293'/>IP</th><th><spring:message code='str1294'/></th><th> <spring:message code='str1439'/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${loggerServerList}" var="item">
			<tr>
				<td>${item.worldId}</td>
				<td>${item.externalIp}</td>
				<td>${item.innerIp}</td>
				<td>
				<c:choose>
                    <c:when test="${item.status == 0}"><span class="label label-info"><spring:message code="${fns:getDictKeys(item.status,'server_status',item.status)}"/></span></c:when>
                    <c:when test="${item.status == 1}"><span class="label label-warning"><spring:message code="${fns:getDictKeys(item.status,'server_status',item.status)}"/></span></c:when>
                    <c:otherwise><span class="label label-important"><spring:message code="${fns:getDictKeys(item.status,'server_status',item.status)}"/></span></c:otherwise>
                   </c:choose>
				</td>
				<td>${item.updateTime}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>


</html>
