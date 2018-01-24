<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title><spring:message code='str79'/></title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
    <script type="text/javascript">
        function page(){
            $("#searchForm").attr("action","${ctx}/game/role/gameRoleDialog");
            $("#searchForm").submit();
            return false;
        }


    </script>
    <style>
        .selected{background-color:#ae0!important}
    </style>
</head>
<body>

    <form:form id="searchForm" action="${ctx}/game/role/gameRoleDialog" class="breadcrumb form-search" method="post">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <label><spring:message code='str80'/></label>
        <input id="roleId" name="roleId" type="text"  class="input-small" value="${paramMap.roleId}"/>
        <label><spring:message code='str81'/></label>
        <input id="roleName" name="roleName" type="text"  class="input-small" value="${paramMap.roleName}"/>
        <label><spring:message code='str82'/></label>
        <input type="text" name="startLevel" class="input-small" value="${paramMap.startLevel}"> - <input type="text" class="input-small" name="endLevel" value="${paramMap.endLevel}">

        <input class="pull-right" type="hidden" name="serverId" id="serverId" value="${paramMap.serverId}">

        <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str83'/>" onclick="return page();"/>
    </form:form>

    <table id="contentTable" class="table table-bordered table-condensed">
        <thead><tr><th><input type="checkbox" id="checkAllOrNot" name="checkAllOrNot"/></th><th><spring:message code='str84'/></th><th><spring:message code='str7'/></th><th><spring:message code='str85'/></th><th>VIP<spring:message code='str85'/></th></tr></thead>
        <tbody>
        <c:forEach items="${page.list}" var="role" varStatus="status">
            <tr>
                <td align="center"><input type="checkbox" class="recordIds" name="recordIds" value="${status.index + 1}"/></td>
                <td>${role.id}</td>
                <td>${role.name}</td>
                <td>${role.level}</td>
                <td>${role.level}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>

<script type="text/javascript">

    var roleIds = "";
    var roleNames="";

    function sure(){

        $(":input[name='recordIds']").each(function(){
            if (this.checked){
                var roleName = $(this).parent().parent().children("td").get(2).innerHTML;
                var roleId = $(this).parent().parent().children("td").get(1).innerHTML;

                roleIds == ""? roleIds =roleIds+roleId : roleIds =roleIds+","+roleId;
                roleNames == ""? roleNames = roleNames+roleName : roleNames = roleNames+","+roleName;
            }
        });

    }

    $(function(){

        var serverId = top.frames["mainFrame"].document.getElementById("serverIds").value;
        $("#serverId").val(serverId);

    });
</script>
</body>
</html>
