<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title><spring:message code='str1514'/></title>
    <link href="${ctxStatic}/bootstrap-switch/dist/css/bootstrap2/bootstrap-switch.css" type="text/css"
          rel="stylesheet"/>
    <script src="${ctxStatic}/bootstrap-switch/dist/js/bootstrap-switch.js" type="text/javascript"></script>
    <script src="${ctxStatic}/bootstrap-switch/dist/js/highlight.js" type="text/javascript"></script>
    <script src="${ctxStatic}/bootstrap-switch/dist/js/main.js" type="text/javascript"></script>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
    <style type="text/css">
        table {
            table-layout: fixed;
        }
        td {
            overflow-x: hidden;
        }
    </style>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/tools/super/list"><spring:message code='super.member'/></a></li>
    <li><a href="${ctx}/tools/super/form"><spring:message code='str1515'/></a></li>
</ul>
<form id="searchForm" action="${ctx}/tools/super/list " method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <label>qq<spring:message code='str4'/></label>
    <input name="qq" value="${qq }" maxlength="100" class="input-small"/>
    &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>"
                 onclick="return page();"/>
</form>
<tags:message content="${message}"/>
<form id="tableForm" action="">
    <table id="contentTable" class="table table-striped table-bordered table-condensed tooltip-demo">
        <tr>
            <th>qq</th>
            <th><spring:message code='str1520'/></th>
            <th><spring:message code='str1517'/></th>
            <th><spring:message code='str1518'/></th>
            <th><spring:message code='str1519'/></th>
            <th><spring:message code='str1136'/></th>
            <th><spring:message code='str1403'/></th>
            <th><spring:message code='str149'/></th>
        </tr>
        <c:forEach items="${page.list}" var="item" varStatus="status">
            <tr id="tr_${item.id}">
                <td id="qq_${item.id}">${item.qq}</td>
                <td id="recharge_${item.id}">${item.min_recharge}</td>
                <td id="mmrecharge_${item.id}">${item.month_min_recharge}</td>
                <td id="pic_${item.id}">${item.pic}</td>
                <td id="serverIds_${item.id}">${item.server_ids}</td>
                <td><fmt:formatDate value="${item.add_time}" type="both"/></td>
                <td>${item.create_name}</td>
                <td>
                    <input type="checkbox" id="${item.id}" operatorType="status" data-size="small" name="my-checkbox"
                           <c:if test="${item.status == 1}">checked</c:if>>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="pagination">${page}</div>
</form>
<script type="text/javascript">
    $(document).ready(function () {
        $('input[name="my-checkbox"]').on('switchChange.bootstrapSwitch', function (event, state) {
            var id = this.attributes.id.nodeValue;
            var flag = state == true ? 1 : 0;
            var type = this.attributes.operatorType.nodeValue;
            var qq = $("#qq_"+id).text();
            var recharge = $("#recharge_"+id).text();
            var mmrecharge = $("#mmrecharge_"+id).text();
            var pic = $("#pic_"+id).text();
            var serverIds = $("#serverIds_"+id).text();
            _remoteCall("${ctx}/tools/super/status", {id: id, status: type, flag: flag, qq: qq, recharge: recharge,mmrecharge:mmrecharge, pic : pic, serverIds : serverIds}, function (result) {
                if (result.success) {
                    top.$.jBox.tip("<spring:message code='str1200'/>!", 'success');
                }
            })
        });
    })
</script>
</body>
</html>
