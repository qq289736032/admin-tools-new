<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1500'/></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tools/release"><spring:message code='str1500'/></a></li>
		<li><a href="${ctx}/tools/release/from"><spring:message code='str1501'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/tools/release" method="post" class="breadcrumb form-search">
	     <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        </form>
		<%----%>
		<%--&lt;%&ndash;<label><spring:message code='str2'/></label>&ndash;%&gt;--%>
		<%--&lt;%&ndash;<input type="text" id="roleName" name="roleName"  class="input-small" value="${paramMap.roleName}"/>&ndash;%&gt;--%>
		<%--&lt;%&ndash;<label><spring:message code='str6'/> <spring:message code='str4'/></label>&ndash;%&gt;--%>
		<%--&lt;%&ndash;<input type="text" id="roleId" name="roleId"  class="input-small" value="${paramMap.roleId}"/>&ndash;%&gt;--%>
		<%--&lt;%&ndash;<label><spring:message code='str3'/> <spring:message code='str4'/></label>&ndash;%&gt;--%>
		<%--&lt;%&ndash;<input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">&ndash;%&gt;--%>
		<%--&lt;%&ndash;-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">		&ndash;%&gt;--%>
		<%--&lt;%&ndash;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>&ndash;%&gt;--%>
	<%--&lt;%&ndash;&ndash;%&gt;--%>
	<%--</form>--%>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="table-layout:fixed;" >
		<tr><th><spring:message code='str1505'/></th><th><spring:message code='str1502'/></th><th><spring:message code='str1506'/></th><th width="18%"><spring:message code='str1504'/></th><th width="18%"><spring:message code='str1392'/></th><th><spring:message code='str1403'/></th><th><spring:message code='str1294'/></th></td><th><spring:message code='str149'/></th></tr>
		<c:forEach items="${page.list}" var="item">
			<tr>
			<td><fmt:formatDate value="${item.version_time}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
			<td style="overflow:hidden;white-space:nowrap;"><a title="${item.version_id}">${item.version_id}</a></td>
			<td style="overflow:hidden;white-space:nowrap;">${item.server_ids}</td>
			<td>${item.release_note}</td>
			<td>${item.release_content}</td>
			<td>${item.create_name}</td>
			<td>
				<c:choose>
				<c:when test="${item.status == 1}"><span class="label label-success"><spring:message code='str1507'/></span></c:when>
				<c:when test="${item.status == 0}"><span class="label label-info"><spring:message code='str1508'/></span></c:when>
			</c:choose>
			</td>
			<td>
				<c:choose>
					<c:when test="${item.status == 0}">
						<a href="${ctx}/tools/release/publish?id=${item.id}" onclick="return confirmx('<spring:message code='str1509'/>', this.href)"><spring:message code='str1356'/></a>
						<a href="${ctx}/tools/release/delete?id=${item.id}" onclick="return confirmx('<spring:message code='str1510'/>', this.href)"><spring:message code='str22'/></a>
					</c:when>
				</c:choose>
			</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
