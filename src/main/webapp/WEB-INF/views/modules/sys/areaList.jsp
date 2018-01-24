<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1104'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel : 5});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/area/"><spring:message code='str1601'/></a></li>
		<shiro:hasPermission name="sys:area:edit"><li><a href="${ctx}/sys/area/form"><spring:message code='str1602'/></a></li></shiro:hasPermission>
	</ul>
	<tags:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<tr><th><spring:message code='str1288'/></th><th><spring:message code='str1600'/></th><th><spring:message code='str1289'/></th><th><spring:message code='str729'/></th><shiro:hasPermission name="sys:area:edit"><th><spring:message code='str149'/></th></shiro:hasPermission></tr>
		<c:forEach items="${list}" var="area">
			<tr id="${area.id}" pId="${area.parent.id ne requestScope.area.id?area.parent.id:'0'}">
				<td><a href="${ctx}/sys/area/form?id=${area.id}">${area.name}</a></td>
				<td>${area.code}</td>
				<td><spring:message code="${fns:getDictKeys(area.type, 'sys_area_type', area.type)}" /></td>
				<td>${area.remarks}</td>
				<shiro:hasPermission name="sys:area:edit"><td>
					<a href="${ctx}/sys/area/form?id=${area.id}"><spring:message code='str1365'/></a>
					<a href="${ctx}/sys/area/delete?id=${area.id}" onclick="return confirmx('<spring:message code='str1598'/>', this.href)"><spring:message code='str22'/></a>
					<a href="${ctx}/sys/area/form?parent.id=${area.id}"><spring:message code='str1599'/></a> 
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
