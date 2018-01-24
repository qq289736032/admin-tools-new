<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>点击收藏游戏</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<style type="text/css">
		span.input-group-btn {
			display: none;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/collect/collectGameList">点击收藏游戏</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/collect/collectGameList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" /> 
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input name="startDate" id="startDate" maxlength="50"  value="${paramMap.startDate}" class="input-small required" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
		<label><spring:message code='str55'/></label>
		<input id="serverIds" name="serverIds" value="${paramMap.serverIds}" readonly/>
        <input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();" />
	</form>
	<tags:message content="${message}"/>

	<div class="row-fluid">
		<div class="span6 panel panel-primary">
			<div class="panel-heading">收藏游戏</div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th>等级</th><th>收藏游戏</th><th>收藏游戏后流失</th></tr>
				<c:forEach items="${collect}" var="item">
					<tr>
						<td>${item.role_level}</td>
               			<td>${item.num}</td>
               			<td>${item.num1}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
 		</div>
 		
 		<div class="span6 panel panel-primary">
			<div class="panel-heading">手机验证</div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
 			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th>等级</th><th>手机验证</th><th>手机验证后流失</th></tr>
				<c:forEach items="${phone}" var="item">
					<tr>
						<td>${item.role_level}</td>
               			<td>${item.num}</td>
               			<td>${item.num1}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
		</div>
 	</div>
 	<div class="row-fluid">
 		<div class="span6 panel panel-primary">
			<div class="panel-heading">收藏游戏&手机验证</div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
 			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th>等级</th><th>收藏游戏&手机验证</th><th>收藏游戏&手机验证后流失</th></tr>
				<c:forEach items="${collectPhone}" var="item">
					<tr>
						<td>${item.role_level}</td>
               			<td>${item.num}</td>
               			<td>${item.num1}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
		</div>
 	</div>

	
<script type="text/javascript">
	$(document).ready(function(){
		$('table.highchart').highchartTable();
		$("#s2id_pids").hide();
		$("#pids").multiselect({
			includeSelectAllOption:true,
			enableFiltering:true
		});
	});
</script>

</body>
</html>
