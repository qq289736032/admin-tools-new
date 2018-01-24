<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str690'/></title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/beauty"><spring:message code='str691'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/beauty" method="post" class="breadcrumb form-search">
		<%-- <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/> --%>
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
 	    <input name="startDate" style="width: 216px" readonly="readonly" value="${paramMap.startDate}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	    <label><spring:message code='str55'/></label>
	    <input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/>
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	
	<div class="row-fluid">
		<div class="span12 panel panel-primary">
			<div class="panel-heading"><spring:message code='str692'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="beautyId" class="table table-striped table-bordered table-condensed">
				<tr><th width="200px"><spring:message code='str130'/>ID</th><th><spring:message code='str693'/></th></tr>
				<c:forEach items="${beauty}" var="item">
						<tr>
							<td><spring:message code='${fns:getDictKeys(item.beauty_id, "beauty_type",item.beauty_id)}'/></td>
							<td>${item.count}</td>
						</tr>
					</c:forEach>
				<tr>
				  <c:if test="${beauty.size()>0}">
				   <td><spring:message code='str694'/> </td><%-- <td>${sumCount}</td> --%>
				   <td></td>
				   </c:if>
				</tr>
			</table>
			</div>
 		</div>
 	</div>
 		
 	<div class="row-fluid">
 		<div class="span12 panel panel-primary">
			<div class="panel-heading"><spring:message code='str695'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
 			<table id="beautyActive" class="table table-striped table-bordered table-condensed">
				<tr><th width="200px"><spring:message code='str130'/>ID</th><th><spring:message code='str689'/></th></tr>
					<c:forEach items="${beautyActive}" var="item">
								<tr>
							        <td><spring:message code='${fns:getDictKeys(item.beauty_id, "beauty_type",item.beauty_id)}'/></td>
									<td>${item.count}</td>
								</tr>
							
					</c:forEach>
					<c:if test="${beauty.size()>0}">
					<tr>			
					<td><spring:message code='str696'/> </td><td>${sumCountOne}</td></tr>
					</c:if>
			</table>
			</div>
		</div>
 	</div>
 	 <script type="text/javascript">
			$(document).ready(function () {
			//<spring:message code='str697'/>
			 $("#beautyId  tr:last td").each(function(index,element){
				      if(index != 0) {
					   var num = 0;
					  $(this).parent().prevAll().find("td:eq("+index+")").each(function(index1,element){
					    num = num+parseInt($(this).text());
					  });
					   $(this).text(num);
					  }
				  });
			 $("#beautyActive  tr:last td").each(function(index,element){
			      if(index != 0) {
				   var num = 0;
				  $(this).parent().prevAll().find("td:eq("+index+")").each(function(index1,element){
				    num = num+parseInt($(this).text());
				  });
				   $(this).text(num);
				  }
			  });
			});
		
			
			
  
            </script> 
</body>
</html>
