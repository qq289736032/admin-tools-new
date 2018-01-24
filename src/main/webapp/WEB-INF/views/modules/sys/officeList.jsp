<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1165'/></title>
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
		<li class="active"><a href="${ctx}/sys/office/"><spring:message code='str1604'/></a></li>
		<shiro:hasPermission name="sys:office:edit"><li><a href="${ctx}/sys/office/form"><spring:message code='str1616'/></a></li></shiro:hasPermission>
	</ul>
	<tags:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed table-hover">
		<tr><th><spring:message code='str1607'/></th><th><spring:message code='str1606'/></th><th><spring:message code='str1608'/></th><th><spring:message code='str1609'/></th><th><spring:message code='str729'/></th><shiro:hasPermission name="sys:office:edit"><th><spring:message code='str149'/></th></shiro:hasPermission></tr>
		<c:forEach items="${list}" var="office">
			<tr id="${office.id}" pId="${office.parent.id ne requestScope.office.id?office.parent.id:'0'}">
				<td><a href="${ctx}/sys/office/form?id=${office.id}">${office.name}</a></td>
				<td>${office.area.name}</td>
				<td>${office.code}</td>
				<td><spring:message code="${fns:getDictKeys(office.type, 'sys_office_type', office.type)}"/></td>
				<td>${office.remarks}</td>
				<shiro:hasPermission name="sys:office:edit"><td>
					<a href="${ctx}/sys/office/form?id=${office.id}"><spring:message code='str1365'/></a>
					<a href="${ctx}/sys/office/delete?id=${office.id}" onclick="return confirmx('<spring:message code='str1181'/>', this.href)"><spring:message code='str22'/></a>
					<a href="${ctx}/sys/office/form?parent.id=${office.id}"><spring:message code='str1182'/></a> 
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
