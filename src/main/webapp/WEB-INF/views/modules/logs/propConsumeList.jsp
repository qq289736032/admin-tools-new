<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
	<title><spring:message code='str914'/></title>
	<meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/highcharts.jsp" %>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<script src="${ctxStatic}/common/fixtable.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#s2id_operaType").hide();
		$("#operaType").multiselect({
			includeSelectAllOption:true,
			enableFiltering:true
		});
	});
	function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").attr("action","${ctx}/log/propController/propConsumeList");
            $("#searchForm").submit();
            return false;
    }
	</script>
	<style type="text/css">
		span.input-group-btn {
			display: none;
		}
	</style>
</head>
<body>
     <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/propController/propConsumeList"><spring:message code='str914'/></a></li>
	 </ul>
	<form id="searchForm" action="${ctx}/log/propController/propConsumeList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<label><spring:message code='str2'/></label>
		<input type="text" id="roleName" name="roleName" value="${paramMap.roleName}"/>
		
		<label><spring:message code='str915'/></label>
		<input type="text" id="itemName" name="itemName" value="${paramMap.itemName}"/>
		
		<label><spring:message code='str763'/></label>
		<select id="operaType" name="operaType" multiple="multiple">	
			<c:forEach items="${fns:getOperaTypeMap()}" var="item">
			<c:choose>
			 <c:when test="${fn:length(selectedOperas)==0}">
			   <option value="${item.key }">${item.value.chDes }</option>
			 </c:when>
			 <c:otherwise>
					<option value="${item.key}" 
						<c:forEach items="${selectedOperas}" var="opera">
							<c:if test="${opera==item.key}">
								selected="selected"
							</c:if>
						</c:forEach>
					>${item.value.chDes}</option>
					</c:otherwise>
			</c:choose>
			</c:forEach>	
		</select>
		
		<label><spring:message code='str185'/></label>
		<input type="text" name="createDateStart" class="input-small" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}',minDate:'#F{$dp.$D(\'endDatePicker\',{d:-15})}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input-small" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}',maxDate:'#F{$dp.$D(\'startDatePicker\',{d:15})}'})">
	
	    &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
		<shiro:hasPermission name="log.propConsume.export">
	    &nbsp;<input id="btnExport" class="btn btn-primary" type="button" value="<spring:message code='str5'/>" onclick="doExport('${ctx}/log/propController/export')"/>
		</shiro:hasPermission>
	</form>
	<tags:message content="${message}"/>
    <table id="active-yb" class="table table-striped table-bordered table-condensed">
		<tr><th><spring:message code='str7'/></th><th><spring:message code='str916'/></th><th><spring:message code='str188'/></th><th><spring:message code='str654'/></th><th><spring:message code='str74'/></th><th><spring:message code='str917'/></th><th><spring:message code='str918'/></th><th><spring:message code='str919'/></th>
			 <th><spring:message code='str920'/></th><th><spring:message code='str3'/></th></tr>
		<c:forEach items="${page.list }" var="item">
			<tr>
				<td>${item.roleName }</td>
				<td>${fns:getResourceType(item.resource)}</td>
				<td>${fns:getActivePanel(item.active)}</td>
				<td>${fns:getOperationType(item.opereateType)}</td>
				<td>${item.itemName }</td>
				<td><c:if test="${item.flowType==1 }"><spring:message code='str921'/></c:if><c:if test="${item.flowType==2 }"><spring:message code='str851'/></c:if></td>
				<td>${item.value }</td>
				<td>${item.beforeValue }</td>
				<td>${item.afterValue }</td>
				<td>${fns:parseLong(item.log_time )}</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
	 <div class="alert">
		 <button type="button" class="close"  data-dismiss="alert">&times;</button>
		 <strong>Warning!</strong> <spring:message code='str893'/>,<spring:message code='str727'/>15<spring:message code='str313'/>
	 </div>
</body>
</html>
