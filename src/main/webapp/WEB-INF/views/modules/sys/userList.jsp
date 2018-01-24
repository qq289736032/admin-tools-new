<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1282'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<style type="text/css">.sort{color:#0663A2;cursor:pointer;}</style>
	<script type="text/javascript">
		$(document).ready(function() {
			// <spring:message code='str1297'/>
			var orderBy = $("#orderBy").val().split(" ");
			$("#contentTable th.sort").each(function(){
				if ($(this).hasClass(orderBy[0])){
					orderBy[1] = orderBy[1]&&orderBy[1].toUpperCase()=="DESC"?"down":"up";
					$(this).html($(this).html()+" <i class=\"icon icon-arrow-"+orderBy[1]+"\"></i>");
				}
			});
			$("#contentTable th.sort").click(function(){
				var order = $(this).attr("class").split(" ");
				var sort = $("#orderBy").val().split(" ");
				for(var i=0; i<order.length; i++){
					if (order[i] == "sort"){order = order[i+1]; break;}
				}
				if (order == sort[0]){
					sort = (sort[1]&&sort[1].toUpperCase()=="DESC"?"ASC":"DESC");
					$("#orderBy").val(order+" DESC"!=order+" "+sort?"":order+" "+sort);
				}else{
					$("#orderBy").val(order+" ASC");
				}
				page();
			});
			$("#btnExport").click(function(){
				top.$.jBox.confirm("<spring:message code='str1298'/>","<spring:message code='str45'/>",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/sys/user/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"<spring:message code='str1299'/>", buttons:{"<spring:message code='str876'/>":true}, 
					bottomText:"<spring:message code='str1300'/>5M<spring:message code='str1301'/>xls<spring:message code='str1302'/>xlsx<spring:message code='str1303'/>"});
			});
		});
		function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").attr("action","${ctx}/sys/user/");
            $("#searchForm").submit();
            return false;
        }
	</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/sys/user/import" method="post" enctype="multipart/form-data"
			style="padding-left:20px;text-align:center;" class="form-search" onsubmit="loading('<spring:message code='str1304'/>...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/><spring:message code='str1305'/>
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   <spring:message code='str1306'/>    <spring:message code='str1307'/>   "/>
			<a href="${ctx}/sys/user/import/template"><spring:message code='str1308'/></a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/user/"><spring:message code='str1548'/></a></li>
		<shiro:hasPermission name="sys:user:edit"><li><a href="${ctx}/sys/user/form"><spring:message code='str1549'/></a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${page.orderBy}"/> <div>
			<%--<label><spring:message code='str1310'/></label><tags:treeselect id="company" name="company.id" value="${user.company.id}" labelName="company.name" labelValue="${user.company.name}" --%>
				<%--title="<spring:message code='str1287'/>" url="/sys/office/treeData?type=1" cssClass="input-small" allowClear="true"/>--%>
			<label><spring:message code='str1427'/><spring:message code='str4'/></label><form:input path="loginName" htmlEscape="false" maxlength="50" class="input-small"/>

        <%--<div style="margin-top:8px;">--%>
			<%--<label><spring:message code='str1312'/></label><tags:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}" --%>
				<%--title="<spring:message code='str1289'/>" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true"/>--%>
			<%--<label><spring:message code='str1313'/>&nbsp;&nbsp;&nbsp;<spring:message code='str1314'/></label><form:input path="name" htmlEscape="false" maxlength="50" class="input-small"/>--%>
			&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str83'/>" onclick="return page();"/>
			<%--&nbsp;<input id="btnExport" class="btn btn-primary" type="button" value="<spring:message code='str5'/>"/>--%>
			<%--&nbsp;<input id="btnImport" class="btn btn-primary" type="button" value="<spring:message code='str1315'/>"/>--%>
		</div>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
		<thead><tr>
            <%--<th><spring:message code='str1286'/></th><th><spring:message code='str1288'/></th>--%>
            <th class="sort loginName"><spring:message code='str1427'/></th>
            <th><spring:message code='str138'/></th><th><spring:message code='str729'/></th><shiro:hasPermission name="sys:user:edit"><th><spring:message code='str149'/></th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<%--<td>${user.company.name}</td>--%>
				<%--<td>${user.office.name}</td>--%>
				<td><a href="${ctx}/sys/user/form?id=${user.id}">${user.loginName}</a></td>
				<td>${user.roleNames}</td>
                <td>${user.remarks}</td>
				<shiro:hasPermission name="sys:user:edit"><td>
    				<a href="${ctx}/sys/user/form?id=${user.id}"><spring:message code='str1365'/></a>
					<a href="${ctx}/sys/user/delete?id=${user.id}" onclick="return confirmx('<spring:message code='str1593'/>', this.href)"><spring:message code='str22'/></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
