<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1358'/></title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tools/gamePlatform/"><spring:message code='str1359'/></a></li>
		<li><a href="${ctx}/tools/gamePlatform/form"><spring:message code='str1362'/></a></li>
	</ul>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr><th><spring:message code='str1363'/></th><th><spring:message code='str1360'/></th><th><spring:message code='str1215'/></th><th><spring:message code='str1361'/></th><th><spring:message code='str1354'/></th><th><spring:message code='str1364'/></th><th><spring:message code='str149'/></th></tr>
		<c:forEach items="${list}" var="gamePlatform">
			<tr>
				<td><a href="form?id=${gamePlatform.id}">${gamePlatform.name}</a></td>
				<td>${gamePlatform.pid}</td>
				<td>${gamePlatform.description}</td>
				<td>${gamePlatform.signKey}</td>
				<td>${gamePlatform.createBy.loginName}</td>
                <td><fmt:formatDate value="${gamePlatform.createDate}" type="both"/></td>
				<td>
					<a href="${ctx}/tools/gamePlatform/form?id=${gamePlatform.id}">
						<spring:message code='str1365'/>
					</a>
					<a href="${ctx}/tools/gamePlatform/delete?id=${gamePlatform.id}" onclick="return confirmx('<spring:message code='str1366'/>', this.href)"><spring:message code='str22'/></a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
