<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1178'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<script type="text/javascript">
		function deleteCrossAreaList(url) {
		    var oldUrl = $("form:first").attr("action");
		    $("form:first").attr("action", url);
		    $("form:first").submit();
		    $("form:first").attr("action", oldUrl);
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
		<li class="active"  id="cross"><a href="${ctx}/tools/crossArea/battleArea"><spring:message code='str1179'/></a></li>
		<%--<li><a href="${ctx}/tools/crossArea/crossAreaAdd?crossAreaType=0"><spring:message code='str1180'/></a></li>--%>
		<li style="display: none" id="country" ><a><spring:message code='str1181'/></a></li>
		
	</ul>
	<div style="display: none" id="countryContent"  >
	</div>
	<form id="searchForm" action="${ctx}/tools/crossArea/crossAreaManage" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${battleArea.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${battleArea.pageSize}"/>
		
		<%--<label><spring:message code='str1182'/> <spring:message code='str4'/></label>--%>
		<%--<select id="crossAreaType" name="crossAreaType">--%>
			<%--<option value=""><spring:message code='str627'/></option>--%>
			<%--<c:forEach items="${fns:getDictList('crossType')}" var="item">--%>
				<%--<option value="${item.value}">${item.label}</option>--%>
			<%--</c:forEach>--%>
		<%--</select>--%>
		<label><spring:message code='str1183'/> <spring:message code='str4'/></label>
			<input type="text" name="crossAreaName" value="${crossAreaName}"/>
		<label>IP <spring:message code='str4'/></label>
			<input type="text" name="innerIp" value="${innerIp}"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
		&nbsp;<input id="btnExport" class="btn btn-primary" type="button" value="<spring:message code='str5'/>" onclick="exportXls('${ctx}/tools/crossArea/exportXls')"/>
		<%-- &nbsp;<input id="btnDelete" class="btn btn-primary" type="button" value="<spring:message code='str1184'/>" onclick="deleteCrossAreaList('${ctx}/tools/crossArea/deleteAllCrossArea')"/> --%>
			&nbsp;<input id="batchDelete" class="btn btn-primary" type="button" value="<spring:message code='str1184'/>" onclick="submitCheckedRecordIds('${ctx}/tools/crossArea/deleteAllCrossArea')"/>
	</form>
	<tags:message content="${message}"/>
	 <form id="tableForm" action="">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr><th width="30px"><input id="checkAllOrNot" name="checkAllOrNot" type="checkbox"></th><th><spring:message code='str1178'/>ID</th><th><spring:message code='str1185'/></th><th>IP</th><th><spring:message code='str1186'/></th><th><spring:message code='str1181'/></th><th style="display: none"><spring:message code='str1187'/></th><th><spring:message code='str149'/></th>
		</thead>
		<tbody>
		<c:forEach items="${battleArea.list}" var="item" varStatus="status">
			<tr>
				<td><input type="checkbox" name="recordIds" value="${item.crossId}"></td>
				<td>${item.crossId}</td>
				<td>${item.worldName}</td>
				<td>${item.ip}</td>
				<td>${item.port}</td>
				<td>
					<c:forEach items="${item.countryList }" var="country"
							   varStatus="status">
						<c:if test="${status.index gt 0 }">, </c:if>
						<a href="javascript:;">${country.name}</a>
					</c:forEach>
				</td>
				<td id="obj_${status.index+1}" style="display: none">
					${item.countryList }
				</td>
				<td>
				<%-- <a style="cursor: pointer" onclick="showDialog('${ctx}/tools/crossArea/showCountry?crossId=${item.crossId}')"><spring:message code='str1188'/></a> --%>
		 	<a style="cursor: pointer" href="${ctx}/tools/crossArea/showCountry?crossId=${item.crossId}"><spring:message code='str1188'/></a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</form>
	<div class="pagination">${battleArea}</div>

<script>
	function showContent(url) {
	    $.ajax({
	        type : "POST",
	        url: url,
	        dataType: 'text',
	        //	data:params,
	        before:function(){
	            loading("<spring:message code='str1189'/>...")
	        },
	        success:function(data){
	            var d = top.dialog({
	                title: '<spring:message code='str1190'/>',
	                width:'100%',
	                height:'97%',
	                content: data,
	                ok: function(){
	                   top.myCallback();
	                },
	                cancel: function(){}
	            });

	            d.showModal();
	        }
	    })
	}
	
	
</script>
</body>
</html>
