<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1217'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
    <script type="text/javascript">
//       $(function(){
//           $("#tipDiv").tooltip('show');
//       });

    </script>
</head>
<html>
<body>
<form id="searchForm"action="${ctx}/tools/gameArea/followerId" method="post" class="breadcrumb form-search"  style="table-layout:fixed;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="table-layout:fixed;">
		<tr><th style="width: 80px"><spring:message code='platform'/></th><th style="width: 80px"><spring:message code='str1676'/></th><th><spring:message code='str1677'/></th></tr>
		<c:forEach items="${FollowedList}" var="gameArea">
			<tr>
				<td>${gameArea.target.platformId}</td>
                <td>${gameArea.target.name}</td> 
               <td style="WORD-WRAP: break-word">${gameArea.followedAreaNames} </td> 
			</tr>
		</c:forEach>
	</table>
	</form>
    <div class="pagination">${page}</div>
    
</body>
</html>
