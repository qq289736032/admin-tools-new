<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str719'/></title>
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
		<li class="active"><a href="${ctx}/log/userStat/churnUser"><spring:message code='str720'/></a></li>
        <li><a href="${ctx}/log/userStat/churnUserLevel"><spring:message code='str721'/></a></li>
	</ul>
    <form id="searchForm" action="${ctx}/log/userStat/churnUser" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

        <input type="text" id="startDate" name="startDate" readonly="readonly" value="${paramMap.startDate}" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'endDate\',{d:-7})}',maxDate:'#F{$dp.$D(\'endDate\')||\'%y-%M-%d\'}',onpicked:function(){endTime.focus();}})" />
        <input type="text" id="endDate" name="endDate" readonly="readonly" value="${paramMap.endDate}" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startDate\',{d:7})||\'%y-%M-%d\'}',minDate:'#F{$dp.$D(\'startDate\')}'})" />

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
                    <th><spring:message code='str198'/></th><th><spring:message code='str724'/></th><th><spring:message code='str740'/></th><th><spring:message code='str725'/></th><th><spring:message code='str741'/></th><th><spring:message code='str726'/></th><th><spring:message code='str742'/></th>
                </tr>
                <c:forEach items="${list}" var="item">
                    <tr>
                        <td>${item.cdate}</td>
                        <td>${item.d1}</td>
                      <td <c:if test="${item.d1/item.dt1 > 1 }">style="color: red;font-weight:bold" </c:if>><c:if test="${item.dt1 == 0}">0</c:if><c:if test="${item.dt1 != 0}"><fmt:formatNumber value="${item.d1/item.dt1 * 100}" pattern="#0.00"/></c:if> % </td>
                        <td>${item.d2}</td>
                        <td <c:if test="${item.d2/item.dt2 > 1 }">style="color: red;font-weight:bold" </c:if>><c:if test="${item.dt2 == 0}">0</c:if><c:if test="${item.dt2 != 0}"><fmt:formatNumber value="${item.d2/item.dt2 * 100}" pattern="#0.00"/></c:if>%</td>
                        <td>${item.d3}</td>
                        <td <c:if test="${item.d3/item.dt3 > 1 }">style="color: red;font-weight:bold" </c:if>><c:if test="${item.dt3 == 0}">0</c:if><c:if test="${item.dt3 != 0}"><fmt:formatNumber value="${item.d3/item.dt3 * 100}" pattern="#0.00"/></c:if>%</td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="alert alert-error">
            <button type="button" class="close"  data-dismiss="alert">&times;</button>
            <strong>Warning!</strong><spring:message code='str727'/>7<spring:message code='str313'/>
        </div>
        <div class="alert alert-info">
            <button type="button" class="close"  data-dismiss="alert">&times;</button>
            <strong><spring:message code='str729'/>!</strong><br/>
            1. <spring:message code='str730'/>14<spring:message code='str731'/>7<spring:message code='str732'/>7<spring:message code='str733'/><br/>
            2. <spring:message code='str734'/>28<spring:message code='str731'/>14<spring:message code='str732'/>14<spring:message code='str733'/><br/>
            3. <spring:message code='str735'/>60<spring:message code='str731'/>30<spring:message code='str732'/>30<spring:message code='str733'/><br/>
            4. <spring:message code='str736'/>/<spring:message code='str737'/>14<spring:message code='str738'/>7<spring:message code='str744'/>

        </div>
    </form>


</body>
</html>
