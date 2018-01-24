<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1217'/></title>
	<meta name="decorator" content="default"/>
    <script type="text/javascript">
//       $(function(){
//           $("#tipDiv").tooltip('show');
//       });

    </script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tools/gameConfig/"><spring:message code='str1218'/></a></li>
		<li><a href="${ctx}/tools/gameConfig/form"><spring:message code='str1219'/></a></li>
	</ul>

	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr><th><spring:message code='str1134'/></th><th><spring:message code='str1220'/></th><th><spring:message code='str1221'/></th><th><spring:message code='str1222'/></th><th><spring:message code='str101'/></th><th><spring:message code='str128'/></th><th><spring:message code='str149'/></th></tr>
		<c:forEach items="${page.list}" var="configFile">
			<tr>
				<td>${configFile.newName}</td>
                <td>${configFile.fileName}</td>
                <td>${configFile.fileDesc}</td>
                <td>${fns:getCommon(configFile.fileType)}</td>
                <td><fmt:formatDate value="${configFile.createDate}" type="both"/></td>
                <td><fmt:formatDate value="${configFile.updateDate}" type="both"/></td>

				<td>
					<a href="${ctx}/tools/gameConfig/form?id=${configFile.id}">
						<spring:message code='str1365'/>
					</a>
                    <a href="${ctx}/tools/gameConfig/delete?id=${configFile.id}" onclick="return confirmx('<spring:message code='str1224'/>', this.href)"><spring:message code='str22'/></a>
				</td>
			</tr>
		</c:forEach>
	</table>
    <div class="pagination">${page}</div>
</body>
</html>
