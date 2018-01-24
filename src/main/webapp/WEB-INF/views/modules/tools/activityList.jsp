<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1122'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
    <script type="text/javascript">
//       $(function(){
//           $('.tooltip-demo').tooltip({
//               selector: "a[data-toggle=tooltip]"
//           })
//       });

    </script>
    <style type="text/css">
        table{table-layout: fixed;}
		td {
			overflow: hidden;
		}
		td.ui-dialog-body {
            height: auto;
     }
    </style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tools/activity/activityList"><spring:message code='str1122'/></a></li>
		<li><a href="${ctx}/tools/activity/form?id=${activity.id}"> <c:if test="${not empty activity.id}"><spring:message code='str1109'/></c:if><c:if test="${empty activity.id}"><spring:message code='operation.add'/></c:if><spring:message code='str1123'/></a></li>
		
	 
	</ul>
    <form:form id="searchForm" action="${ctx}/tools/activity/activityList" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label><spring:message code='str1119'/></label>
		<select name="activityName">
				<option value=""><spring:message code='str1129'/></option>
			 	<c:forEach items="${activityConfigList}" var="item">
					<option value="${item.name}"
						<c:if test="${item.name==paramMap.activityName}">selected="selected"</c:if>
					>${item.name}</option>
			 	</c:forEach>
		</select>
        <label><spring:message code='str1130'/></label>
        <input type="text" name="createTimeStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createTimeStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
				-&nbsp;<input type="text" name="createTimeEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createTimeEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
        &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
		&nbsp;<input id="batchDelete" class="btn btn-primary" type="button" value="<spring:message code='str22'/>" onclick="submitCheckedRecordIds('${ctx}/tools/activity/batchDelete')"/>
		&nbsp;<input id="syncActivity" class="btn btn-primary" type="button" value="<spring:message code='str1131'/>" onclick="ajaxConfirm('<spring:message code='str1132'/>','${ctx}/tools/activity/syncActivity')"/>
    </form:form>
	<tags:message content="${message}"/>
    <form id="tableForm" action="">
	<table id="contentTable" class="table table-striped table-bordered table-condensed tooltip-demo ">
		<tr>
		<th width="58px"><input id="checkAllOrNot" name="checkAllOrNot" type="checkbox"><spring:message code='str1133'/></th>
		<th><spring:message code='str1134'/></th><th width="30%"><spring:message code='str1112'/></th><th><spring:message code='str1124'/></th><th><spring:message code='str1135'/></th><th><spring:message code='str1136'/></th><th><spring:message code='str128'/></th><th><spring:message code='str1137'/></th><th><spring:message code='str149'/></th></tr>
		<c:forEach items="${page.list}" var="item">
			<tr>
				<td><input type="checkbox" name="recordIds" value="${item.id}"></td>
                <td width="20%">${item.originalFile}</td>
                <td>${item.activityName}</td>
				<td>${item.activityDesc}</td>
                <td title="${item.serverIds}"><c:if test="${item.isGlobal == 1}"><spring:message code='str1347'/></c:if>
					<c:if test="${item.isGlobal == 0}">${item.serverIds}</c:if>				
				</td>
				<td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${item.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${item.createName}</td>
                <td>
					<a href="${ctx}/tools/activity/form?id=${item.id}">
						<ul class="the-icons clearfix">
							<li><i class="icon-edit"></i><spring:message code='str1365'/></li>
						</ul>
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
    </form>
    
</body>
</html>
