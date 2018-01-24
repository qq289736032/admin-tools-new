<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
	<title><spring:message code='str901'/></title>
	<meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/highcharts.jsp" %>
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
            $("#searchForm").attr("action","${ctx}/log/powerChange/list");
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
		<li class="active"><a href="${ctx}/log/powerChange/list"><spring:message code='str901'/></a></li>
	 </ul>
	<form id="searchForm" action="${ctx}/log/powerChange/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<label><spring:message code='str138'/>ID<spring:message code='str4'/></label>
		<input type="text" id="roleId" name="roleId" value="${paramMap.roleId}"/>
		<label><spring:message code='str2'/></label>
		<input type="text" id="roleName" name="roleName" value="${paramMap.roleName}"/>
		
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
				<input type="text" name="createDateStart" class="input-small" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">	
	    &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
    <table id="active-yb" class="table table-striped table-bordered table-condensed">
		<tr><th><spring:message code='str56'/></th><th><spring:message code='str138'/>ID</th><th><spring:message code='str7'/></th><th><spring:message code='str825'/></th><th><spring:message code='str902'/></th><th><spring:message code='str903'/></th><th><spring:message code='str904'/></th><th><spring:message code='str905'/></th><th><spring:message code='str3'/></th></tr>
		<c:forEach items="${page.list }" var="item">
			<tr>
				<td>${item.pid }</td>
				<td>${item.role_id }</td>
				<td>${item.role_name }</td>
				<td>${fns:getOperationType(item.operate_type)}</td>
				<td>${item.before }</td>
				<td>${item.after }</td>
				<td>${item.value }</td>
			    <td><c:if test="${item.type==true }"><img src="${ctxStatic}/images/iconfont-icon124.png"/></c:if><c:if test="${item.type==false }"><img src="${ctxStatic}/images/iconfont-icon123.png"/></c:if></td>
				<td>${fns:parseYYYYMMDDHHMM(item.log_minute)}</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
	 <div class="alert">
		 <button type="button" class="close"  data-dismiss="alert">&times;</button>
		 <strong>Warning!</strong> <spring:message code='str906'/>ID<spring:message code='str907'/>,<spring:message code='str727'/>15<spring:message code='str908'/>
	 </div>
</body>
</html>
