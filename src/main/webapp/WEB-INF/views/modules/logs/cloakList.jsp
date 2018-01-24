<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str743'/></title>
<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/cloak/cloakList"><spring:message code='str743'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/cloak/cloakList" method="post" class="breadcrumb form-search">
		<%-- <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/> --%>
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input name="startDate" id="startDate" maxlength="50"  value="${paramMap.startDate}" class="input-small required" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
	    <label><spring:message code='str55'/></label>
	    <input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/>
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	<div class="row-fluid">
		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str744'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th><spring:message code='str85'/></th><th><spring:message code='str206'/></th></tr>
				<c:forEach items="${cloak}" var="item">
					<tr>
						<td>${item.level}</td>
               			<td>${item.num}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
 		</div>
 	
 	
 		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str745'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
 			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th><spring:message code='str746'/></th><th><spring:message code='str206'/></th></tr>
				<c:forEach items="${cloakType}" var="item">
					<tr>
						<td>${item.skin_type}</td>
               			<td>${item.skin}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
		</div>
	</div>
			
 	<div class="row-fluid">
		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str747'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th width="200px"><spring:message code='str85'/></th><th><spring:message code='str748'/></th><th><spring:message code='str749'/></th><th><spring:message code='str750'/></th></tr>
				<c:forEach items="${cloakActive}" var="item">
					<tr>
						<td>${item.level}</td>
                		<td>${item.id_num}</td>
                		<td>${item.min_num}</td>
              		  	<td>${item.max_num}</td>
					</tr>
				</c:forEach>
			</table>
			
			
			</div>
		</div>
		
 		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str751'/>0<spring:message code='str557'/></div>
 			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th width="200px"><spring:message code='str85'/></th><th><spring:message code='str752'/>0<spring:message code='str206'/></th><th><spring:message code='str749'/></th><th><spring:message code='str750'/></th></tr>
				<c:forEach items="${cloakNumber}" var="item">
					<tr>
						<td>${item.level}</td>
              		  	<td>${item.id_num}</td>
              		  	<td>${item.min_num}</td>
              		  	<td>${item.max_num}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
		</div>
 	</div>
 
			
	<%-- <div class="pagination">${page}</div> --%>
</body>
</html>
