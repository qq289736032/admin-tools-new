<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1413'/></title>
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
    </style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tools/virtual/"><spring:message code='str1413'/></a></li>
        <shiro:hasPermission name="apply.virtual.point">
            <li><a href="${ctx}/tools/virtual/form"><spring:message code='str1414'/></a></li>
        </shiro:hasPermission>
        <li><a href="${ctx}/tools/virtual/itemList"><spring:message code='str1413'/></a></li>
        <shiro:hasPermission name="add.virtual.goods">
            <li><a href="${ctx}/tools/virtual/itemForm"><spring:message code='str1415'/></a></li>
        </shiro:hasPermission>

	</ul>
    <form id="searchForm" action="${ctx}/tools/virtual/" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <%--<label><spring:message code='str1540'/></label>--%>
        <%--<input path="title" htmlEscape="false" maxlength="100" class="input-small"/>--%>
        <label><spring:message code='str1325'/></label>
        <select id="state">
            <option value="" label="<spring:message code='str627'/>"></option>
            <c:forEach items="${fns:getDictList('email_status')}" var="item">
                <option value="${item.value}" label="${item.label}"><spring:message code="${item.internationalKey}"/></option>
            </c:forEach>
        </select>
        <%--<label><spring:message code='str1326'/></label>--%>
         <%--<input type="text" name="createTimeStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createTimeStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">--%>
				<%---&nbsp;<input type="text" name="createTimeEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createTimeEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">--%>
        &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
        </div>
    </form>
	<tags:message content="${message}"/>
    <form id="tableForm" action="">
	<table id="contentTable" class="table table-striped table-bordered table-condensed tooltip-demo">
		<tr>
            <th><spring:message code='str1541'/></th><th><spring:message code='str1329'/></th><th><spring:message code='str1330'/></th><th><spring:message code='str1331'/>ID<spring:message code='str1332'/></th><th width="20%"><spring:message code='str1413'/></th><th><spring:message code='str1333'/></th><th><spring:message code='str1334'/></th><th width="10%"><spring:message code='str3'/></th><th><spring:message code='str1294'/></th><th><spring:message code='str149'/></th></tr>
		<c:forEach items="${page.list}" var="item" varStatus="status">
			<tr>
                <td>
                    <c:choose>
                        <c:when test="${item.global == 1}"><span class="label label-success"><spring:message code='str1164'/></span></c:when>
                        <c:when test="${item.global == 0}"><span class="label label-info"><spring:message code='str1542'/></span></c:when>
                    </c:choose>
                </td>
                <td style="overflow:hidden;white-space:nowrap;"><a title="${item.serverIds}">${item.serverIds}</a></td>
                <td style="overflow:hidden;white-space:nowrap;"><a title="${item.roleNames}">${item.roleNames}</a> </td>
                <td style="overflow:hidden;white-space:nowrap;"> <a title="${item.roleIds}">${item.roleIds}</a> </td>
                <td>${item.goods}</td>
                <td>${item.createName}</td>
                <td>${item.approveName}</td>
                <td><fmt:formatDate value="${item.createTime}" type="both"/></td>
                <td>
                    <c:choose>
                        <c:when test="${item.state == 2}"><span class="label label-warning"><spring:message code="${fns:getDictKeys(item.state, 'email_status', item.state)}"/></span></c:when>
                        <c:when test="${item.state == 1}"><span class="label label-success"><spring:message code="${fns:getDictKeys(item.state, 'email_status', item.state)}"/></span></c:when>
                        <c:when test="${item.state == 0}"><span class="label label-info"><spring:message code="${fns:getDictKeys(item.state, 'email_status', item.state)}"/></span></c:when>
                        <c:otherwise><spring:message code="${fns:getDictKeys(item.state, 'email_status', item.state)}"/></c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:if test="${item.state == 0}">
                        <shiro:hasPermission name="approve.virtual.goods">
                            <a href="${ctx}/tools/virtual/send?id=${item.id}" onclick="return confirmx('<spring:message code='str1543'/>', this.href)"><spring:message code='str1336'/></a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="cancel.apply.virtual">
                            <a href="${ctx}/tools/virtual/cancel?id=${item.id}" onclick="return confirmx('<spring:message code='str1544'/>', this.href)"><spring:message code='str175'/></a>
                        </shiro:hasPermission>
                    </c:if>
                    <c:if test="${item.state == 2}">
                        <a href="${ctx}/tools/virtual/recover?id=${item.id}" onclick="return confirmx('<spring:message code='str1545'/>', this.href)"><spring:message code='str1327'/></a>
                    </c:if>
                </td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
    </form>
    
</body>
</html>
