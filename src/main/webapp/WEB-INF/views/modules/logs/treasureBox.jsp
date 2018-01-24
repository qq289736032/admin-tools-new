<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1081'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#s2id_pids").hide();
		$("#pids").multiselect({
			includeSelectAllOption:true,
			enableFiltering:true
		});
	});
	function setCommandType(type){
		$("#commandType").val(type);
		$("#searchForm").submit();
	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/treasure/treasureBox"><spring:message code='str1081'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/treasure/treasureBox" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			      <label><spring:message code='str1082'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input-small" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
	    <label><spring:message code='str1082'/> <spring:message code='str4'/></label>
			<select name="operateType">
					<option value=""><spring:message code='str627'/></option>
				<c:forEach items="${fns:getDuoxuanLiheMap()}" var="item">
					<option value="${item.key}"
						<c:if test="${item.key==paramMap.operateType}">selected="selected"</c:if>
					>${item.value}</option>
			 	</c:forEach>
			</select>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
		<input id="commandType" name="commandType" type="hidden"/>
		
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
	<!-- 	<tr>
		<th><spring:message code='str1083'/></th><th>88<spring:message code='str1022'/></th>	<th>688<spring:message code='str1022'/></th>	<th>1688<spring:message code='str1022'/></th></tr> -->
 
     <tr><th width="200px" align="center" ><spring:message code='str1084'/></th><th width="200px" align="center"><spring:message code='str206'/></th></tr>
		</thead>
		<tbody>
		<c:forEach items="${treasureBoxLog.list}" var="item">
			<tr>
				<td align="center">${item.value}</td>
				<td align="center">${item.num}</td>			
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${levelLog}</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('table.highchart').highchartTable();
		});
	</script>

</body>
</html>
