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
        <li><a href="${ctx}/tools/virtual/form"><spring:message code='str1414'/></a></li>
    </shiro:hasPermission>
    <li><a href="${ctx}/tools/virtual/itemList"><spring:message code='str1413'/></a></li>
    <shiro:hasPermission name="add.virtual.goods">
        <li class="active"><a href="${ctx}/tools/virtual/itemForm"><spring:message code='str1415'/></a></li>
    </shiro:hasPermission>
</ul><br/>
<form id="inputForm"  action="${ctx}/tools/virtual/saveItem" method="post" class="form-horizontal">

    <div class="control-group">
        <label class="control-label"><spring:message code='str1416'/>:</label>
        <div class="controls">
            <input id="itemId" name="itemId" class="input-normal"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label"><spring:message code='str1417'/>:</label>
        <div class="controls">
            <input id="name" name="name" class="input-normal"/>
        </div>
    </div>

    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1263'/> <spring:message code='str1264'/>"/>&nbsp;
        <input id="btnCancel" class="btn" type="button" value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)"/>
    </div>
</form>

</body>
</html>
