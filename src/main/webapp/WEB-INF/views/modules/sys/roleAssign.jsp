<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1183'/></title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/role/"><spring:message code='str71'/></a></li>
		<li class="active"><a href="${ctx}/sys/role/assign?id=${role.id}"><shiro:hasPermission name="sys:role:edit"><spring:message code='str1575'/></shiro:hasPermission><shiro:lacksPermission name="sys:role:edit"><spring:message code='str1634'/></shiro:lacksPermission></a></li>
	</ul>
	<div class="container-fluid breadcrumb">
		<div class="row-fluid span12">
			<span class="span4"><spring:message code='str653'/>: <b>${role.name}</b></span>
			<%--<span class="span4"><spring:message code='str1570'/>: ${role.office.name}</span>--%>
			<%--<c:set var="dictvalue" value="${role.dataScope}" scope="page" />--%>
			<%--<span class="span4"><spring:message code='str1573'/>: ${fns:getDictLabel(dictvalue, 'sys_data_scope', '')}</span>--%>
		</div>
	</div>
	<tags:message content="${message}"/>
	<div class="breadcrumb">
		<form id="assignRoleForm" action="" method="post" class="hide"></form>
		<a id="assignButton" href="javascript:" class="btn btn-primary"><spring:message code='str1576'/></a>
		<script type="text/javascript">
			$("#assignButton").click(function(){
				top.$.jBox.open("iframe:${ctx}/sys/role/usertorole?id=${role.id}", "<spring:message code='str1183'/>",810,$(top.document).height()-240,{
					buttons:{"<spring:message code='str1188'/>":"ok", "<spring:message code='str1189'/>":"clear", "<spring:message code='str876'/>":true}, bottomText:"<spring:message code='str1190'/>",submit:function(v, h, f){
						var pre_ids = h.find("iframe")[0].contentWindow.pre_ids;
						var ids = h.find("iframe")[0].contentWindow.ids;
						//nodes = selectedTree.getSelectedNodes();
						if (v=="ok"){
							// <spring:message code='str22'/>''<spring:message code='str1191'/>
							if(ids[0]==''){
								ids.shift();
								pre_ids.shift();
							}
							if(pre_ids.sort().toString() == ids.sort().toString()){
								top.$.jBox.tip("<spring:message code='str1192'/>${role.name}<spring:message code='str1193'/>", 'info');
								return false;
							};
					    	// <spring:message code='str1194'/>
					    	loading('<spring:message code='str1105'/>...');
					    	var idsArr = "";
					    	for (var i = 0; i<ids.length; i++) {
					    		idsArr = (idsArr + ids[i]) + (((i + 1)== ids.length) ? '':',');
					    	}
					    	$('#assignRoleForm').attr('action','${ctx}/sys/role/assignrole?id=${role.id}&idsArr='+idsArr).submit();
					    	return true;
						} else if (v=="clear"){
							h.find("iframe")[0].contentWindow.clearAssign();
							return false;
		                }
					}, loaded:function(h){
						$(".jbox-content", top.document).css("overflow-y","hidden");
					}
				});
			});
		</script>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th><spring:message code='str1427'/></th><th><spring:message code='str1428'/></th><th><spring:message code='str1429'/></th><th><spring:message code='str1430'/></th><shiro:hasPermission name="sys:user:edit"><th><spring:message code='str149'/></th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<%--<td>${user.company.name}</td>--%>
				<%--<td>${user.office.name}</td>--%>
				<td><a href="${ctx}/sys/user/form?id=${user.id}">${user.loginName}</a></td>
				<td>${user.name}</td>
				<td>${user.phone}</td>
				<td>${user.mobile}</td>
				<shiro:hasPermission name="sys:role:edit"><td>
					<a href="${ctx}/sys/role/outrole?userId=${user.id}&roleId=${role.id}" 
						onclick="return confirmx('<spring:message code='str1578'/><b>[${user.name}]</b><spring:message code='str1199'/><b>[${role.name}]</b><spring:message code='str1200'/>', this.href)"><spring:message code='str1577'/></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>
