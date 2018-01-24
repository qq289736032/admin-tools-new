<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>技能书吞噬<spring:message code='str1682'/><spring:message code='str1681'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<script type="text/javascript">
	function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").attr("action","${ctx}/log/phagocytosis/skillbookPhagocytosisList");
            $("#searchForm").submit();
            return false;
    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/phagocytosis/skillbookPhagocytosisList">技能书吞噬</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/phagocytosis/skillbookPhagocytosisList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<label><spring:message code='str653'/> <spring:message code='str4'/></label>
		<input id="roleName" name="roleName" type="text" value="${roleName}"/>
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input name="startDate" id="startDate" maxlength="50"  value="${paramMap.startDate}" class="input-small required" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
		&nbsp;<input id="btnExport" class="btn btn-primary" type="button" value="<spring:message code='str5'/>" onclick="doExport('${ctx}/log/phagocytosis/export')"/>
	</form>
 	<tags:message content="${message}" />
	<form id="tableForm" action="">
		<table id="contentTable" class="table table-striped table-bordered table-condensed ">
			<tr><th><spring:message code='str7'/></th><th><spring:message code='str1681'/>ID</th><th><spring:message code='str1686'/>ID</th><th><spring:message code='str1685'/>ID</th><th><spring:message code='str1683'/></th><th><spring:message code='str1684'/></th><th><spring:message code='str3'/></th></tr>
			<c:forEach items="${page.list}" var="item">
					<tr>
						<td>${item.role_name}</td>
						<td>${item.skill_book_id}</td>
               			<td>${item.item_template_id}</td>
               			<td>${item.target_skill_book_id}</td>
               			<td>${item.before_exp}</td>
               			<td>${item.after_exp}</td>
               			<td>${fns:parseYYYYMMDDHHMM(item.log_minute)}</td>
					</tr>
				</c:forEach>
		</table>
	</form>
	 <div class="pagination">${page}</div>

 	
</body>
</html>