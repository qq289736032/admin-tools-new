<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title><spring:message code='str1242'/></title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
    <script type="text/javascript">

    </script>
    <style type="text/css">
        .modal-header{ border-bottom: none;}
        .modal-body{border-bottom: 1px solid #EEEEEE}
    </style>
</head>
<body>

<div id="createCodeDetailDialog" class="">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th>ID</th><th><spring:message code='str1243'/></th><th><spring:message code='str439'/></th><th><spring:message code='str718'/></th><th style="display: none"><spring:message code='str718'/></th><th><spring:message code='str1244'/></th><th><spring:message code='str440'/></th><th><spring:message code='str700'/></th><th><spring:message code='str1245'/></th></tr>
		</thead>
		<tbody>
			<tr>
				<td>${id}</td>
				<td>${name}</td>
				<td><c:if test="${attachments != '' && attachments != '[]'}"><a type="button" href="#" class="btn btn-primary btn-small" id="btn_1"  onclick="viewAttach(1)"><spring:message code='str872'/></a></c:if> </td>
				<td id="hide_1" style="display: none">${fns:transformationGoodNames(attachments)}</td>
				<td>${content}</td>
				<td>${prefix}</td>
				<td>${count}</td>				
				<td><c:choose>
                    <c:when test="${type == 1}"><span class="label label-info"><spring:message code='str1246'/></span></c:when>
                    <c:when test="${type == 0}"><span class="label label-warning"><spring:message code='str1247'/></span></c:when>
                    <c:otherwise><span class="label label-important"><spring:message code='str1248'/></span></c:otherwise>
                   </c:choose>
				</td>
				<td><fmt:formatDate value="${effective_end_time}" type="both"/></td>				
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>
