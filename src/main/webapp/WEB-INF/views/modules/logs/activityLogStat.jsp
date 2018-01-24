<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
	<title><spring:message code='act.data'/></title>
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
            $("#searchForm").attr("action","${ctx}/log/act/view");
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
		<li class="active"><a href="${ctx}/log/act/view"><spring:message code='act.data'/></a></li>
	 </ul>
	<form id="searchForm" action="${ctx}/log/act/view" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
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
		<tr>
			<th><spring:message code='str3'/></th>
			<th><spring:message code='str700'/></th>
			<th><spring:message code='str74'/></th>
			<th><spring:message code='str149'/></th>
			<th><spring:message code='str440'/></th>
			<th><spring:message code='str468'/></th>
		</tr>
		<c:forEach items="${goodsList.list }" var="item">
			<tr>
				<td>${item.log_day }</td>
				<td>${fns:getOperationType(item.operate_type)}</td>
				<td>${item.item_name }</td>
				<td><c:if test="${item.flow_type==1 }"><spring:message code='str921'/></c:if><c:if test="${item.flow_type==2 }"><spring:message code='str264'/></c:if></td>
				<td>${item.add_num }</td>
				<td>${item.role_num }</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${goodsList}</div>
    <table id="active-yb" class="table table-striped table-bordered table-condensed">
		<tr>
			<th><spring:message code='str3'/></th>
			<th><spring:message code='str700'/></th>
			<th><spring:message code='str1493'/></th>
			<th><spring:message code='str149'/></th>
			<th><spring:message code='str273'/></th>
			<th><spring:message code='str468'/></th>
		</tr>
		<c:forEach items="${moneyList.list }" var="item">
			<tr>
				<td>${item.log_day }</td>
				<td>${fns:getOperationType(item.operate_type)}</td>
				<td><spring:message code="${fns:getDictKeys(item.money_type,'money_type',item.money_type)}"  /></td>
				<td><c:if test="${item.flow_type==1 }"><spring:message code='str921'/></c:if><c:if test="${item.flow_type==2 }"><spring:message code='str264'/></c:if></td>
				<td>${item.add_num }</td>
				<td>${item.role_num }</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${moneyList}</div>
	 <div class="alert">
		 <button type="button" class="close"  data-dismiss="alert">&times;</button>
		 <strong>Warning!</strong> <spring:message code='str893'/>,<spring:message code='str727'/>15<spring:message code='str313'/>
	 </div>
</body>
</html>
