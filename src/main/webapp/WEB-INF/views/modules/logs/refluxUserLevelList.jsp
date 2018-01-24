<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str978'/></title>
	<meta name="decorator" content="default"/>
    <script type="text/javascript">
//       $(function(){
//           $("#tipDiv").tooltip('show');
//       });
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);



            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/log/userStat/refluxUser"><spring:message code='str979'/></a></li>
        <li class="active"><a href="${ctx}/log/userStat/refluxUserLevel"><spring:message code='str980'/></a></li>
	</ul>
    <form id="searchForm" action="${ctx}/log/userStat/refluxUserLevel" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

        <%--<input type="text" id="startDate" name="startDate" readonly="readonly" value="${paramMap.startDate}" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'endDate\',{d:-7})}',maxDate:'#F{$dp.$D(\'endDate\')||\'%y-%M-%d\'}',onpicked:function(){endTime.focus();}})" />--%>
        <%--<input type="text" id="endDate" name="endDate" readonly="readonly" value="${paramMap.endDate}" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startDate\',{d:7})||\'%y-%M-%d\'}',minDate:'#F{$dp.$D(\'startDate\')}'})" />--%>

        <input type="text" id="startDate" name="startDate" readonly="readonly" value="${paramMap.startDate}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />

        &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>

    </form>
	<tags:message content="${message}"/>
    <form id="tableForm" action="">
        <%--<div class="container-fluid">--%>
            <%--<div class="row-fluid">--%>

            <%--</div>--%>
        <%--</div>--%>

        <div class="panel panel-primary">
            <div class="panel-heading"><spring:message code='str297'/></div>
            <table id="contentTable" class="table table-striped table-bordered table-condensed">
                <input type="text" style="display: none" hidden="hidden" name="msg" id="msg">
                <tr>
                    <th><spring:message code='str723'/></th><th><spring:message code='str981'/></th><th><spring:message code='str226'/></th><th><spring:message code='str989'/></th><th><spring:message code='str226'/></th><th><spring:message code='str983'/></th><th><spring:message code='str226'/></th>
                </tr>
                <c:forEach items="${map}" var="item">
                    <tr>
                        <td>${item.key} <spring:message code='str306'/></td>
                        <td>${item.value.weekUser}</td>
                        <td <c:if test="${item.value.weekUser/week > 1 }">style="color: red;font-weight:bold" </c:if>><c:if test="${week == 0}">0</c:if><c:if test="${week != 0}"><fmt:formatNumber value="${item.value.weekUser/week * 100}" pattern="#0.00"/></c:if> % </td>
                        <td>${item.value.doubleWeekUser}</td>
                        <td <c:if test="${item.value.doubleWeekUser/doubleWeek > 1 }">style="color: red;font-weight:bold" </c:if>><c:if test="${doubleWeek == 0}">0</c:if><c:if test="${doubleWeek != 0}"><fmt:formatNumber value="${item.value.doubleWeekUser/doubleWeek * 100}" pattern="#0.00"/></c:if> % </td>
                        <td>${item.value.monthUser}</td>
                        <td <c:if test="${item.value.monthUser/month > 1 }">style="color: red;font-weight:bold" </c:if>><c:if test="${month == 0}">0</c:if><c:if test="${month != 0}"><fmt:formatNumber value="${item.value.monthUser/month * 100}" pattern="#0.00"/></c:if> % </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <%--<div class="alert alert-error">--%>
            <%--<button type="button" class="close"  data-dismiss="alert">&times;</button>--%>
            <%--<strong>Warning!</strong><spring:message code='str727'/>7<spring:message code='str923'/>--%>
        <%--</div>--%>
        <%--<div class="alert alert-info">--%>
            <%--<button type="button" class="close"  data-dismiss="alert">&times;</button>--%>
            <%--<strong><spring:message code='str729'/>!</strong><br/>--%>
            <%--1. <spring:message code='str984'/>14<spring:message code='str731'/>7<spring:message code='str732'/>7<spring:message code='str733'/><br/>--%>
            <%--2. <spring:message code='str985'/>28<spring:message code='str731'/>14<spring:message code='str732'/>14<spring:message code='str733'/><br/>--%>
            <%--3. <spring:message code='str986'/>60<spring:message code='str731'/>30<spring:message code='str732'/>30<spring:message code='str733'/><br/>--%>
            <%--4. <spring:message code='str987'/>/<spring:message code='str737'/>14<spring:message code='str738'/>7<spring:message code='str744'/>--%>

        <%--</div>--%>
    </form>


</body>
</html>
