<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str70'/></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
	            $("#pageNo").val(n);
	            $("#pageSize").val(s);
	            $("#searchForm").attr("action","${ctx}/game/activityJoin/shiJueZhenList");
	            $("#searchForm").submit();
	            return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/game/activityJoin/activityJoinList"><spring:message code='activityjoin'/></a></li><!-- 活动参与度列表 -->
		<li><a href="${ctx}/game/activityJoin/caiLiaoFuBenList"><spring:message code='cailiaofuben'/></a></li><!-- 活动参与度列表 -->
		<li ><a href="${ctx}/game/activityJoin/xuanYuanFuBenList"><spring:message code='xuanyuanfenfuben'/></a></li><!-- 活动参与度列表 -->
		<li class="active"><a href="${ctx}/game/activityJoin/shiJueZhenList"><spring:message code='shijuezhen'/></a></li><!-- 活动参与度列表 -->
	</ul>
	<form id="searchForm" action="${ctx}/game/activityJoin/shiJueZhenList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<!-- 时间输入框 -->
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		
		<!-- 活动名称输入框 -->
		<label><spring:message code='activity.name'/> <spring:message code='str4'/></label></label>
	    <input id="activityName" name="activityName" value="${paramMap.activityName}" />
	    <!-- 点击查询 -->
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
					<table id="contentTable" class="table table-striped table-bordered table-condensed  ">
						<tr>
							<th><spring:message code='riqi'/></th>
							<th><spring:message code='activity.id'/></th>
							<th><spring:message code='activity.name'/></th>
							<th><spring:message code='tongguancishu'/></th>
						</tr>
						
						<c:forEach items="${page.list}" var="item">
							<tr>
								<td>${item.date}</td>
								<td>${item.activity_id}</td>
								<td>${item.activity_name}</td>
								<td>${item.tongguancishu}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
	<div class="pagination">${page}</div>


</body>
</html>
