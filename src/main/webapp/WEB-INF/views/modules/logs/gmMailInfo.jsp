<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>gm<spring:message code='str815'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
    <script type="text/javascript">

    </script>
    <style type="text/css">
        .modal-header{ border-bottom: none;}
        .modal-body{border-bottom: 1px solid #EEEEEE}
    </style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/log/mail/list"><spring:message code='str818'/></a></li>
		<li class="active"><a href="${ctx}/log/mail/gmMailInfo">gm<spring:message code='str815'/></a></li>
	</ul><br/>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th>id</th>	<th><spring:message code='str816'/></th> <th><spring:message code='str819'/></th> <th><spring:message code='str817'/></th></tr>
		</thead>
		<tbody>
			<tr>
				<td>${gameEmail.id}</td>
				<td>${gameEmail.createName}</td>
				<td>${gameEmail.attachments}</td>
				<td>${gameEmail.approveName}</td>			
			</tr>
		</tbody>
	</table>

</body>
</html>
