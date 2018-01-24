<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title><spring:message code='str1375'/></title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
    <script type="text/javascript">
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").attr("action","${ctx}/tools/gameArea/selectSingleGameServer");
            $("#searchForm").submit();
            return false;
        }
    </script>
    <style>
        .selected{background-color:#ae0!important}
        td .ui-dialog-body {height: auto; }
    </style>
</head>
<body>
    <form id="searchForm" action="${ctx}/tools/gameArea/selectSingleGameServer" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <label><spring:message code='str1512'/></label>
        <select id="pid" name="pid">
            <%--<form:option value="" label="<spring:message code='str627'/>"/>--%>
            <c:forEach var="platform" items="${fns:getGamePlatformList()}">
                <option id="" value="${platform.pid}" <c:if test="${paramMap.pid == platform.pid}">selected="selected" </c:if>>${platform.name}</option>
            </c:forEach>
        </select>
        <label><spring:message code='str824'/></label>
        <input id="serverId" name="serverId" htmlEscape="false" value="${paramMap.serverId}" class="input-small"/>

        &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str83'/>" onclick="return page();"/>
    </div>
    </form>
    <table id="contentTable" class="table table-bordered table-condensed   td .ui-dialog-body">
        <thead><tr><th hidden="hidden"><input type="checkbox" id="selectall" name="selectall"/></th><th><spring:message code='str824'/></th><th><spring:message code='str14'/>ip</th><th><spring:message code='str561'/></th><th><spring:message code='str1294'/></th><th><spring:message code='str1309'/></th><th><spring:message code='str56'/></th></tr></thead>
        <tbody>
        <c:forEach items="${page.list}" var="gameServer" varStatus="status">
            <tr>
                <td align="center" hidden="hidden"><input type="checkbox" class="case" name="case" value="${status.index + 1}"/></td>
                <td>${gameServer.world_id}</td>
                <td>${gameServer.inner_ip}</td>
                <td>${gameServer.world_name}</td>
                <td>				  
				   <c:choose>
                    <c:when test="${gameServer.status == 0}"><span class="label label-info"><spring:message code="${fns:getDictKeys(item.status,'server_status',item.status)}"/></span></c:when>
                    <c:when test="${gameServer.status == 1}"><span class="label label-warning"><spring:message code="${fns:getDictKeys(item.status,'server_status',item.status)}"/></span></c:when>
                    <c:otherwise><span class="label label-important"><spring:message code="${fns:getDictKeys(item.status,'server_status',item.status)}"/></span></c:otherwise>
                   </c:choose></td>
                <td>${gameServer.open_time}</td>
                <td>${fns:getGamePlatformNameById(gameServer.pid,gameServer.pid )}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>

<script type="text/javascript">

    var selectedGameServerId;
    var selectedGameServerName;
    var selectedGamePlatformName;

    $(function(){

        $("#contentTable > tbody > tr").click(function(){
            removeAllSelected();
            $(this).addClass("selected");
            selectedGamePlatformName = $(this).children("td").get(6).innerHTML;
            selectedGameServerId = $(this).children("td").get(1).innerHTML;
            selectedGameServerName = $(this).children("td").get(3).innerHTML + '<spring:message code='str498'/>'+selectedGameServerId+'<spring:message code='str1377'/>';
        });


//        // add multiple select / deselect functionality
//        $("#selectall").click(function () {
//            $('.case').attr('checked', this.checked);
//        });
//
//        $(".case").click(function(){
//            if($(".case").length == $(".case:checked").length) {
//                $("#selectall").attr("checked", "checked");
//            } else {
//                $("#selectall").removeAttr("checked");
//            }
//
//        });
    });

    function removeAllSelected(){
        $("#contentTable > tbody > tr").each(function(){
            $(this).removeClass("selected");
        });
    }

</script>
</body>
</html>
