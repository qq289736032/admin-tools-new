<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str818'/></title>
	<meta name="decorator" content="default"/>

	<link href="${ctxStatic}/artDialog/css/ui-dialog.css" type="text/css" rel="stylesheet" />

	<script type="text/javascript">
//		$(document).ready(function(){
//			$('.btn').popover({
//				html:true
//			});
//		})


	</script>
	<style type="text/css">
		td.ui-dialog-body {height: auto;}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/mail/list"><spring:message code='str818'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/mail/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label><spring:message code='str862'/> <spring:message code='str4'/></label>
		<input id="roleId" name="roleId" type="text" value="${roleId}"/>
		<label><spring:message code='str7'/> <spring:message code='str4'/></label>
		<input id="roleName" name="roleName" type="text" value="${roleName}"/>
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input-small" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input-small" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">		
		&nbsp;
		<label><spring:message code='str20'/> <spring:message code='str4'/></label>
		<input id="contentKey" name="contentKey" type="text" value="${contentKey}"/>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed " >
		<thead>
		<tr><th><spring:message code='str863'/></th>	<th><spring:message code='str816'/></th>	<th><spring:message code='str138'/>ID</th> <th><spring:message code='str864'/></th>	<th><spring:message code='str865'/></th>	<th><spring:message code='str866'/></th>
			<th><spring:message code='str867'/></th>	<th style="display: none;"><spring:message code='str867'/></th><th><spring:message code='str868'/>(0:<spring:message code='str869'/> 1:<spring:message code='str870'/> 2:<spring:message code='str871'/>)</th></tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="status">
			<tr>
				<td>
					<c:if test="${not empty  item.admin_email_id}">
						<%--<a href="javascript:;" onclick="getGmMailInfo('${item.admin_email_id}');">${item.title}</a>--%>
						<a href="${ctx}/log/mail/gmMailInfo?gmMailId=${item.admin_email_id}">${item.title}</a>
					</c:if>
					<c:if test="${empty  item.admin_email_id}">
						${item.title}
					</c:if>
				</td>
				<td>${item.sender_name}</td>
				<td>${item.role_id}</td>
				<td>${fns:getRoleNameByRoleId(item.role_id, item.role_id)}</td>
				<td><fmt:formatDate value="${item.add_time}" type="both"/></td>
				<td>${item.content}</td>
				<td><c:if test="${item.attachment != ''}"><a type="button" href="#" class="btn btn-primary btn-small" id="btn_${status.index+1}"  onclick="viewAttach(${status.index+1})"><spring:message code='str872'/></a></c:if> </td>
                <td style="display: none" id="hide_${status.index + 1}"><c:if test="${item.attachment != ''}">${fns:transformationGoodNames(item.attachment)}</c:if></td>
				<td>${item.opened}</td>
				<td> <a href="${ctx}/log/mail/delete?id=${item.id}&roleId=${item.role_id}" onclick="return confirmx('<spring:message code='str873'/>', this.href)"><spring:message code='str874'/></a></td> 
				 
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	<script type="text/javascript">
//		$(document).ready(function(){
//			$('table.highchart').highchartTable();
//		});
		function getGmMailInfo(gmMailId){
			top.$.jBox.open("iframe:"+BASE+"/log/mail/getGmMailInfo?gmMailId="+gmMailId, "gm<spring:message code='str875'/>",1000,$(top.document).height()-240,{
				buttons:{"<spring:message code='str173'/>":"ok", "<spring:message code='str876'/>":true}, bottomText:"gm<spring:message code='str875'/>", loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}

	</script>
	<%--<script type="text/javascript" src="${ctxStatic}/artDialog/dist/dialog-min.js"></script>--%>
	<script type="text/javascript" src="${ctxStatic}/artDialog/dist/dialog-plus-min.js"></script>
</body>
</html>
