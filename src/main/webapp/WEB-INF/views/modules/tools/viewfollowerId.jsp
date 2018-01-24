<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1217'/></title>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<style type="text/css">
	</style>
	
    <script type="text/javascript">
//       $(function(){
//           $("#tipDiv").tooltip('show');
//       });

    </script>
</head>
<html>
<body>
<c:if test="${followedDetail.size() > 0 }">
   <div style="width:500px; height:225px; overflow:scroll;">
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style=" table-layout:fixed;">
	
		<tr><th style="width: 80px"><spring:message code='platform'/></th><th style="width: 80px"><spring:message code='str1676'/></th><th style="width: 400px"><spring:message code='str1677'/></th></tr>
			<tr>
				<td>${paramMap.pids}</td>
                <td>${paramMap.name}</td> 
               <td style="WORD-WRAP: break-word">
                  <c:forEach items="${followedDetail}" var="gameArea"  varStatus="a">
                    ${gameArea.name},
                  </c:forEach>
                </td> 
			</tr>
	
	</table>
	</div>
	</c:if>
	<c:if test="${followedDetail.size() == 0 }">
	    <tr ><td><spring:message code='str1678'/></td></tr>
	</c:if>
	
</body>
</html>
