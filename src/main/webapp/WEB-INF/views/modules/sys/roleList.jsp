<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1202'/></title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/role/"><spring:message code='str1568'/></a></li>
		<shiro:hasPermission name="sys:role:edit"><li><a href="${ctx}/sys/role/form"><spring:message code='str1568'/></a></li></shiro:hasPermission>
	</ul>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr><th><spring:message code='str653'/></th><th><spring:message code='str729'/></th><th><spring:message code='str1426'/></th><shiro:hasPermission name="sys:role:edit"><th><spring:message code='str149'/></th></shiro:hasPermission></tr>
		<c:forEach items="${list}" var="role">
			<tr>
				<td><a href="form?id=${role.id}">${role.name}</a></td>
                <td>${role.remarks}</td>
                <td>
                    <c:if test="${role.isGlobal == 1}"><spring:message code='str98'/></c:if>
                    <c:if test="${role.isGlobal == 0}"><spring:message code='dict79'/></c:if>
                </td>
				<%--<td>${fns:getDictLabel(role.dataScope, 'sys_data_scope', '<spring:message code='str150'/>')}</td>--%>
				<shiro:hasPermission name="sys:role:edit"><td>
					<a href="${ctx}/sys/role/assign?id=${role.id}"><spring:message code='str1567'/></a>
					<a href="${ctx}/sys/role/form?id=${role.id}"><spring:message code='str1365'/></a>
					<a href="${ctx}/sys/role/delete?id=${role.id}" onclick="return confirmx('<spring:message code='str151'/>', this.href)"><spring:message code='str22'/></a>
				</td></shiro:hasPermission>	
			</tr>
		</c:forEach>
	</table>
</body>
</html>
