<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='menu.manager'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<style type="text/css">.table td i{margin:0 2px;}</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel : 3});
		});
    	function updateSort() {
			loading('<spring:message code='str1105'/>...');
	    	$("#listForm").attr("action", "${ctx}/sys/menu/updateSort");
	    	$("#listForm").submit();
    	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/menu/"><spring:message code='str1552'/></a></li>
		<shiro:hasPermission name="sys:menu:edit"><li><a href="${ctx}/sys/menu/form"><spring:message code='str1553'/></a></li></shiro:hasPermission>
	</ul>
	<tags:message content="${message}"/>
	<form id="listForm" method="post">
		<table id="treeTable" class="table table-striped table-bordered table-condensed table-hover">
			<tr><th><spring:message code='str1554'/></th><th><spring:message code='str1555'/></th><th style="text-align:center;"><spring:message code='str1556'/></th><th><spring:message code='str1562'/></th><th><spring:message code='str1557'/></th><shiro:hasPermission name="sys:menu:edit"><th><spring:message code='str149'/></th></shiro:hasPermission></tr>
			<c:forEach items="${list}" var="menu">
				<tr id="${menu.id}" pId="${menu.parent.id ne '1' ? menu.parent.id : '0'}">
					<td><i class="icon-${not empty menu.icon?menu.icon:' hide'}"></i><a href="${ctx}/sys/menu/form?id=${menu.id}">${menu.name}</a></td>
					<td>${menu.href}</td>
					<td style="text-align:center;">
						<shiro:hasPermission name="sys:menu:edit">
							<input type="hidden" name="ids" value="${menu.id}"/>
							<input name="sorts" type="text" value="${menu.sort}" style="width:50px;margin:0;padding:0;text-align:center;">
						</shiro:hasPermission><shiro:lacksPermission name="sys:menu:edit">
							${menu.sort}
						</shiro:lacksPermission>
					</td>
					<td>
					<c:if test="${menu.isShow eq '1'}"> <spring:message code='dict44'/></c:if>
					  <c:if test="${menu.isShow ne '1'}"> <spring:message code='dict57'/></c:if>
					  </td>
					<td>${menu.permission}</td>
					<shiro:hasPermission name="sys:menu:edit"><td>
						<a href="${ctx}/sys/menu/form?id=${menu.id}"><spring:message code='str1365'/></a>
						<a href="${ctx}/sys/menu/delete?id=${menu.id}" onclick="return confirmx('<spring:message code='str1561'/>', this.href)"><spring:message code='str22'/></a>
						<a href="${ctx}/sys/menu/form?parent.id=${menu.id}"><spring:message code='str1560'/></a> 
					</td></shiro:hasPermission>
				</tr>
			</c:forEach>
		</table>
		<shiro:hasPermission name="sys:menu:edit"><div class="form-actions pagination-left">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="<spring:message code='str1161'/>" onclick="updateSort();"/>
			<%--<div class="pull-right">--%>
				<%--<a class="btn btn-warning" href="${ctx}/sys/menu/synToActiviti" onclick="return confirmx('<spring:message code='str1162'/>', this.href)"><spring:message code='str1163'/></a>--%>
			<%--</div>--%>
		</div></shiro:hasPermission>
	 </form>
</body>
</html>
