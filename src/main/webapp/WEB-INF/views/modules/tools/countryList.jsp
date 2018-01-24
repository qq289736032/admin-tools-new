<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1181'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
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
		<li class="active"><a href="#"><spring:message code='str1179'/></a></li>
		<%--<li><a href="${ctx}/tools/crossArea/crossAreaAdd?crossAreaType=0"><spring:message code='str1180'/></a></li>--%>
	</ul>
	<form id="searchForm" action="${ctx}/tools/crossArea/countryList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${countries.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${countries.pageSize}"/>

		<label><spring:message code='str1237'/> <spring:message code='str4'/></label>
			<input type="text" name="crossAreaName" value="${paramMap.crossAreaName}"/>
		<label><spring:message code='str1240'/>ID <spring:message code='str4'/></label>
			<input type="text" name="crossId" value="${paramMap.crossId}"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>

	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr><th width="30px"><input id="checkAllOrNot" name="checkAllOrNot" type="checkbox"></th><th><spring:message code='str1237'/></th><th><spring:message code='str1181'/>ID</th><th><spring:message code='str1238'/></th><th><spring:message code='str1241'/></th><th style="display: none"><spring:message code='str149'/></th>
		</thead>
		<tbody>
		<c:forEach items="${countries.list}" var="item" varStatus="status">
			<tr>
				<td><input type="checkbox" name="recordIds" value="${item.id}"></td>
				<td>${item.name}</td>
				<td>${item.id}</td>
				<td>${item.kingName}</td>
				<td>${item.crossId}</td>
				<td style="display: none"><a style="cursor: pointer" onclick="showDialog('${ctx}/tools/crossArea/showCountry?crossId=${item.crossId}')"><spring:message code='str1188'/></a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${countries}</div>

<script>
	function showDialog(url){
		$.ajax({
			type : 'POST',
			url: url,
			dataType: 'json',
		//	data:params,
			before:function(){
				loading("<spring:message code='str1189'/>...")
			},
			success:function(data){

				var content = "<table id='dialogTable' class='table table-striped table-bordered table-condensed'>";
				content = content + "<thead> <tr><th><spring:message code='str1181'/>ID</th><th><spring:message code='str1237'/></th><th><spring:message code='str1238'/></th><th><spring:message code='str101'/></th></tr></thead><tbody>";
				for(var i = 0; i < data.length; i ++){

					content = content + "<tr><td>"+data[i].id+"</td><td>"+data[i].name+"</td><td>"+data[i].kingName+"</td><td>"+data[i].addTime+"</td></tr>";
				}
				content = content + "</tbody></table>";
				var d = top.dialog({
					title: '<spring:message code='str1181'/>',
					lock:true,
					width:'100%',
					height:'100%',
					content: content
				});

				d.showModal();
			}
		})

	}
</script>
</body>
</html>
