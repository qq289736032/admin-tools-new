<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1445'/></title>
	<meta name="decorator" content="default"/>

	<script src="${ctxStatic}/common/fixtable.js" type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tools/config/list"><spring:message code='str1445'/></a></li>
		<li><a href="${ctx}/tools/config/form"><spring:message code='str1446'/></a></li>
	</ul>
	
	<tags:message content="${message}"/>
	<table id="contentTable" class="datatable table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<th><spring:message code='str369'/></th>
			<th><spring:message code='str217'/></th>
			<th><spring:message code='str235'/></th>
			<th><spring:message code='str387'/></th>
			<th><spring:message code='str238'/></th>
			<th><spring:message code='str1447'/>%<spring:message code='str1434'/></th>
			<th>ARPU</th>
			<th><spring:message code='str201'/></th>
			<th><spring:message code='str149'/></th>
		</tr>
	</thead>
		<tbody>
		<c:forEach items="${list}" var="item">
			<tr>
				<td>${item.month}</td>
				<td>${item.newUser}</td>
				<td>${item.oldUser}</td>
				<td>${item.active}</td>
				<td>${item.payNum}</td>
				<td <c:if test="${item.payrate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.payrate * 100}" pattern="#0.00"/> %</td>
				<td><fmt:formatNumber value="${item.arpu/100}" pattern="#0.00"/></td>
				<td><fmt:formatNumber value="${item.income}" pattern="#0.00"/></td>
				<td><a href="${ctx}/tools/config/form?id=${item.id}">
					<ul class="the-icons clearfix">
						<li><i class="icon-edit"></i><spring:message code='str1365'/></li>
					</ul>
				</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<br/>
</body>
</html>
