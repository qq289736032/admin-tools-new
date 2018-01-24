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
    </style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="${ctx}/tools/gift/viewCodeList"><spring:message code='str1525'/></a></li>
		<li class="active"><a href="${ctx}/tools/gift/viewCreateName"><spring:message code='str1526'/></a></li>
	</ul>
    <form:form id="searchForm" action="${ctx}/tools/gift/viewCreateName" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <label><spring:message code='str1528'/></label>
        <%-- <input type="text" name="name" value="${paramMap.name}"/> --%>
        <select id="giftId" name="giftId" >
					<%--<option value=""><spring:message code='str627'/></option>--%>
					<c:forEach items="${giftList}" var="gift">
						<option value="${gift.id}"   <c:if test="${gift.id == giftId}">selected="selected" </c:if>>${gift.name}</option>
					</c:forEach>
				</select>
        <label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'endDatePicker\',{d:-7})}',maxDate:'#F{$dp.$D(\'endDatePicker\')||\'%y-%M-%d\'}',onpicked:function(){endTime.focus();}})" />
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startDatePicker\',{d:7})||\'%y-%M-%d\'}',minDate:'#F{$dp.$D(\'startDatePicker\')}'})" />
        &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
    </form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed tooltip-demo">
		<tr><th><spring:message code='str1243'/></th><th><spring:message code='str1534'/></th><th><spring:message code='str1537'/></th><th><spring:message code='str1538'/></th><th><spring:message code='str1539'/></th></tr>
		<c:forEach items="${listCode}" var="item">
			<tr>
			<c:if test="${item.gift_create_id  >0  }">
                <td>${item.name}</td>
                <td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
                <td>${item.gift_create_id}</td>
                <td>${item.count } </td>
				<td>${item.num } </td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
