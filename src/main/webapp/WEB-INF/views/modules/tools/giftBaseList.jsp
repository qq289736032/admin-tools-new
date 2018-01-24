<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1265'/></title>
	<meta name="decorator" content="default"/>
    <script type="text/javascript">
//       $(function(){
//           $('.tooltip-demo').tooltip({
//               selector: "a[data-toggle=tooltip]"
//           })
//       });

    </script>
    <style type="text/css">
        table{table-layout: fixed;}
        td.ui-dialog-body {
            height: auto;
       }
    </style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tools/gift/giftBaseList"><spring:message code='str1394'/></a></li>
		<li><a href="${ctx}/tools/gift/form"><spring:message code='str1395'/></a></li>
	</ul>
    <form:form id="searchForm" action="${ctx}/tools/gift/giftBaseList" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label><spring:message code='str1267'/></label>
		<input type="text" name="name" value="${paramMap.name}"/>
        <label><spring:message code='str1396'/></label>
		<input type="text" name="content" value="${paramMap.content}"/>
        &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
    </form:form>
	<tags:message content="${message}"/>
    <form id="tableForm" action="">
	<table id="contentTable" class="table table-striped table-bordered table-condensed tooltip-demo   td  .ui-dialog-body">
		<tr><th><spring:message code='str1252'/></th><th><spring:message code='str1397'/></th><th><spring:message code='str819'/></th><th style="display: none"><spring:message code='str819'/></th><th><spring:message code='str827'/></th><th><spring:message code='str1398'/></th></tr>
		<c:forEach items="${page.list}" var="gift" varStatus="status">
			<tr>
                <td>${gift.name}</td>
                <td>${gift.content}</td>
                <td><c:if test="${gift.attachments != '' && gift.attachments != '[]'}"><a type="button" href="#" class="btn btn-primary btn-small" id="btn_${status.index+1}"  onclick="viewAttach(${status.index+1})"><spring:message code='str872'/></a></c:if> </td>
                <td style="display: none" id="hide_${status.index + 1}">${fns:transformationGoodNames(gift.attachments)}</td>
                <td>${gift.createName}</td>
                <td><fmt:formatDate value="${gift.createDate}" type="both"/></td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
    </form>
    
</body>
</html>
