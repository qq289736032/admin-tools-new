<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str1297'/></title>
<meta name="decorator" content="default" />
<%@include file="/WEB-INF/views/include/highcharts.jsp"%>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tools/daoLiangConfig/daoLiangConfig"><spring:message code='str1297'/></a></li>
	</ul>
	<%--<form:form id="searchForm" action="${ctx}/tools/daoLiangConfig/addDaoLiangConfig" method="post"--%>
		<%--class="form-horizontal">--%>

	<%--</form:form>--%>
	<tags:message content="${message}" />
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th><spring:message code='str56'/>PID</th><th><spring:message code='str230'/></th><th>CPA</th><th><spring:message code='str149'/></th></tr>
		</thead>
		<tbody>
		<c:forEach items="${fns:getGamePlatformList()}" var="item">
			<tr>
				<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
				<td>${item.name}</td>
				<td>
				<input 
					<c:forEach items="${allDaoLiang}" var="daoLiang">
						<c:if test="${daoLiang.pid==item.pid}">value="${daoLiang.cpa}"</c:if>
					</c:forEach>
					id="${item.pid}"/>
				</td>
				<td><a href="javascript:;" onclick="submitUpdate('${item.pid}');"><spring:message code='str1216'/></a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
<script type="text/javascript">
	function submitUpdate(pid){
		var reg = new RegExp("^[0-9]*$");
		var capValue = document.getElementById(pid).value;
		if(capValue==''||!reg.test(capValue)){  
			tips("<spring:message code='str1299'/>!");
			return;
		}
		window.open("${ctx}/tools/daoLiangConfig/addDaoLiangConfig?pid="+pid+"&cpa="+capValue,"_self");
	}
</script>
</body>
</html>
