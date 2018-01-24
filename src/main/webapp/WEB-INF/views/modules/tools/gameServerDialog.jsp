<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title><spring:message code='str1375'/></title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
    <script type="text/javascript">

    </script>
    <style type="text/css">
        .modal-header{ border-bottom: none;}
        .modal-body{border-bottom: 1px solid #EEEEEE}
    </style>
</head>
<body>

<div id="serverDialog" class="">
    <div class="breadcrumb form-search">

        <div class="pop_layer_cont">
            <input type="checkbox" class="btn_select_16" onclick="allSelectOrNot(this)"/><spring:message code='str1376'/>
            <%--<input type="checkbox" class="btn_select_16 allAreaOption"/><spring:message code='str1367'/>--%>
        </div>

    </div>
    <c:forEach items="${gamePlatform}" var="platform" varStatus="status">
        <c:set var = "status_len" value="${status.count}"></c:set>
        <div class="modal-form modal-example select2-container-multi" style="background: #fff4e5">
            <div class="modal-header" >
                <input type="checkbox" onclick="" id="selectAll_${status.index}" class="game-server-sort-name" /><small class="label label-info">${platform.name}</small>
            </div>
            <div id="game-server-list-${status.index}" class="modal-body">
                <c:forEach items="${platform.gameServerList}" var="item" varStatus="serverIdStatus">
                    <input type="checkbox" class="ids_${status.index}" name="ids_${status.index}" value="${item.worldId}" id="${item.worldId}"/>${item.worldName}<spring:message code='str498'/>${item.worldId}<spring:message code='str1377'/>
                </c:forEach>
            </div>
        </div>
        <div style="padding-bottom: 3px"></div>
    </c:forEach>

</div>


<%--<script type="text/javascript" src="${ctxStatic}/modules/tools/gameServerDialog.js"/>--%>
<script type="text/javascript">
    //var topDocument = (top.frame_content == undefined) ? top.document : top.frame_content['mainFrame'].document;
    var selectedGameServerIds="";
    $(function() {

        <%--var len = ${status_len}--%>
        <%--for(var i = 0; i < ${status_len}; i ++){--%>
            <%--$("#selectAll_"+i).click(function () {--%>
                <%--alert( $("#selectAll_"+i).length);--%>
                <%--$('.ids_'+i).attr('checked', this.checked);--%>
            <%--});--%>
        <%--}--%>


        <%--for(var i = 0; i < ${status_len}; i ++){--%>
            <%--$('.ids_'+i).click(function(){--%>
                <%--alert($('.ids_'+i).length);--%>
                <%--if($('.ids_'+i).length == $('.ids_'+i+':checked').length){--%>
                    <%--$("#selectAll_"+i).attr("checked", "checked");--%>
                <%--}else{--%>
                    <%--$("#selectAll_"+i).removeAttr("checked");--%>
                <%--}--%>
            <%--})--%>
        <%--}--%>

        $(".game-server-sort-name").click(function(){
            var id =$(this).parent().next("div").attr("id");
            if(this.checked){
                $("#"+id+" :checkbox").each(function(){ $(this).attr("checked",true); });
            }else{
                $("#"+id+" :checkbox").each(function(){ $(this).attr("checked",false); });
            }
        });

        var ids = top.frames["mainFrame"].document.getElementById("serverIds").value;

        if(ids == null || ids == ""){
            return;
        }
        var idsArray = ids.split(",");
        for (var i = 0 ; i < idsArray.length ; i++ ){
            var id = jQuery.trim(idsArray[i]);
            $("#"+id).attr("checked",true);
        }



    });


    function banding(){
        var len = ${status_len}
        for(var i = 0; i < ${status_len}; i ++){
            $("#selectAll_"+i).click(function () {
                $('.ids_'+i).attr('checked', this.checked);
            });

            $('.ids_'+i).click(function(){
                alert(this.class.name);
                if($('.ids_'+i).length == $('.ids_'+i+':checked').length){
                    $("#selectAll_"+i).attr("checked", "checked");
                }else{
                    $("#selectAll_"+i).removeAttr("checked");
                }
            })
        }
    }

    function allSelectOrNot(a){
        if(a.checked){
            $(".modal-form :checkbox").each(function(){
                $(this).attr("checked",true);
            });
        }else{
            $(".modal-form :checkbox").each(function(){
                $(this).attr("checked",false);
            });
        }

    }

    function sure(){
        $(".modal-body :checkbox").each(function(){
            if(this.checked && selectedGameServerIds ==""){
                selectedGameServerIds = $(this).val();
            }else if(this.checked){
                selectedGameServerIds+=( ","+$(this).val() );
            }
        });
    }
</script>

</body>
</html>
