<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>ip<spring:message code='str1197'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>

	
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tools/config/client"><spring:message code='str1201'/>URL</a></li>
		<li><a href="${ctx}/tools/config/clientForm"><spring:message code='str1191'/></a></li>
	</ul>
	<form id="searchForm" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <input id="batchDelete" class="btn btn-primary" type="button" value="<spring:message code='str22'/>" onclick="submitCheckedRecordIds('${ctx}/tools/config/delCheckClient')"/>
    </form>
	<tags:message content="${message}"/>
	<form  id="tableForm"   action="">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>		
		   <th width="58px"><input id="checkAllOrNot" name="checkAllOrNot" type="checkbox"><spring:message code='str1133'/></th>
		  <th><spring:message code='str56'/></th><th><spring:message code='str1204'/></th><th><spring:message code='str14'/></th><th><spring:message code='str1201'/>URL</th><th><spring:message code='str149'/></th></tr>
		<c:forEach items="${list}" var="item" varStatus="status">
			<tr>
			<c:if test="${item.serverId !='_default' }">
			  <td><input type="checkbox" name="recordIds" value="${item.serverId}"></td>
			</c:if>
			<c:if test="${item.serverId =='_default' }">
			  <td><input type="checkbox" name="recordIds" value="${item.serverId}" disabled></td>
			</c:if>
			   	<td> ${item.platformId}</td>
			   	<td>${item.name }</td>
				<td>${item.serverId}</td>
				<td>${item.url}</td>
				<td>
				 <c:if test="${item.serverId !='_default' }">
					<a href="${ctx}/tools/config/deleteClient?serverId=${item.serverId}" onclick="return confirmx('<spring:message code='str1199'/>', this.href)"><spring:message code='str22'/></a>
				</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	</form>
	<script type="text/javascript">

		$(document).ready(function() {

			$('input[name="my-checkbox"]').on('switchChange.bootstrapSwitch', function(event, state) {
				var pid = this.attributes.pid.nodeValue;
				var flag = state == true? 1 : 0;
				_remoteCall("${ctx}/tools/config/updateClient",{pid:pid,flag:flag},function(result){
					if(result.success){
						top.$.jBox.tip("<spring:message code='str1200'/>!", 'success');
					}
				})
			});
		})

	</script>
</body>

</html>
