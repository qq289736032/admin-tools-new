<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title><spring:message code='str1249'/></title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>

    <script type="text/javascript">

        $(document).ready(function() {
            $("#title").focus();
            $("#inputForm").validate({
                submitHandler: function(form){

                    loading('<spring:message code='str1106'/>...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("<spring:message code='str1107'/>");
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });

        function openGoods(targetId){
            openGoodsDialog(targetId);
        }
        var trIndex = 0;
        function addItems(){
            $('#contentTable').append("<div id=\"row"+trIndex+"\"><select name=\"items["
                    +trIndex+"].itemId\"> "
                    + " <option value='' ><spring:message code='str627'/></option><c:forEach var='item' items='${item}'><option value='${item.item_id}' >${item.name}</option></c:forEach></select> "
                    + "<spring:message code='str440'/>:<input name=\"items["
                    +trIndex+"].itemNum\" id=\"itemNum" +trIndex+"\"/> "
                    + "<input id=\"deleteGoods\" class=\"btn btn-primary\" type=\"button\" value=\"<spring:message code='str22'/>\" onclick=\"deleteItems( " + trIndex+ ")\";/>"
            );
            trIndex ++;
        }



        function deleteItems(trIndex){
            //if(trIndex==1){
            // alert('<spring:message code='str1314'/>');
            // return
            //}
            $("#row"+(trIndex)).remove();
            trIndex --;
        }

        function toggleTrReceiverNames(){
            if ($("#global").val() == "1") {
                $('#trReceiverNames').hide();
                $('#receiverNames').val("");
            } else {
                $('#trReceiverNames').show();
            }
            $("#server_ids").val("");
        }

        function openServer(){
            if ($("#global").val() == "1") {
                $("#server_ids").val("");
                openSeverDialog();
            } else {
                selectSingleServer();
            }
        }

        function openGameUser(){
            var serverId =$("#server_ids").val();
            if (serverId == "") {
                alert("<spring:message code='str1140'/>");
                return;
            }

            openGameUserDialog(serverId);
        }
    </script>
</head>
<body>
<%--<div id="importBox">--%>
<%--<div id="importForm"--%>
<%--style="padding-left:20px;text-align:center;" class="form-search"><br/>--%>
<%--<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/><spring:message code='str1154'/>--%>
<%--<input id="uploadButton" type="submit" onclick="ajaxFileUpload();" class="btn btn-primary" value="<spring:message code='str1155'/>" />--%>
<%--&lt;%&ndash;<a href="${ctx}/tools/gameEmail/template"><spring:message code='str1156'/></a>&ndash;%&gt;--%>
<%--</div>--%>
<%--</div>--%>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/tools/virtual/"><spring:message code='str1413'/></a></li>
    <shiro:hasPermission name="apply.virtual.point">
        <li class="active"><a href="${ctx}/tools/virtual/form"><spring:message code='str1414'/></a></li>
    </shiro:hasPermission>
    <li><a href="${ctx}/tools/virtual/itemList"><spring:message code='str1413'/></a></li>
    <shiro:hasPermission name="add.virtual.goods">
        <li><a href="${ctx}/tools/virtual/itemForm"><spring:message code='str1415'/></a></li>
    </shiro:hasPermission>
</ul><br/>
<form id="inputForm"  action="${ctx}/tools/virtual/save" method="post" class="form-horizontal">

    <input type="hidden" id="id" name="id" style="display: none">

    <div class="control-group">
        <label class="control-label"><spring:message code='str1163'/>:</label>
        <div class="controls">
            <select id="global" name="global" onchange="toggleTrReceiverNames()">
                <option value="1"><spring:message code='str1164'/></option>
                <option value="0"><spring:message code='str1165'/></option>
            </select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"><spring:message code='str14'/>:</label>
        <div class="controls">
            <input id="serverIds" name="serverIds" readonly/>
            <%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openServer()"></button>--%>
            <input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
        </div>
    </div>

    <div class="control-group" style="display: none" id="trReceiverNames">
        <label class="control-label"><spring:message code='str1166'/>:</label>
        <div class="controls">
            <button type="button" onclick="openGameUser()"><spring:message code='str1166'/></button><br/>
            <textarea id="receiverNames" rows="3" name="receiverNames" class="input-xxlarge required"></textarea>
            <span style="color:#FF003A;"> * <spring:message code='str1167'/></span>

            <textarea id="receiverUserIds" style="display:none" name="receiverUserIds" class="required" readonly></textarea>
        </div>
    </div>


    <div class="control-group">

        <label class="control-label"><spring:message code='str1175'/>:</label>
        <div id="contentTable" class="controls">


        </div>
        <div class="controls">
            <input id="addGoods" class="btn btn-primary" type="button" value="<spring:message code='operation.add'/>" onclick="addItems();"/>
            <%--<input id="deleteGoods" class="btn btn-primary" type="button" value="<spring:message code='str22'/>" onclick="deleteItems();"/>--%>
        </div>
    </div>

    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1263'/> <spring:message code='str1264'/>"/>&nbsp;
        <input id="btnCancel" class="btn" type="button" value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)"/>
    </div>
</form>

</body>
</html>
