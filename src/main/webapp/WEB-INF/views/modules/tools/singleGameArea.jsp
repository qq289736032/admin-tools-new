<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <style type="text/css">
        /*.modal-header{ border-bottom: none;}*/
        /*.modal-body{border-bottom: 1px solid #EEEEEE}*/
        /*{*/
        /*margin: 1px;*/
        /*font: inherit;*/
        /*color: inherit;*/
        /*}*/
        BUTTON
        {
            overflow: visible;
        }
        BUTTON
        {
            text-transform: none;
        }
        BUTTON
        {
            cursor: pointer;
            -webkit-appearance: button;
        }
        *
        {
            background: 0px 0px;
            /*color: #000 !important;*/
            text-shadow: none;
            -webkit-box-shadow: none;
            box-shadow: none;
        }
        :after
        {
            background: 0px 0px;
            color: #000 !important;
            text-shadow: none;
            -webkit-box-shadow: none;
            box-shadow: none;
        }
        :before
        {
            background: 0px 0px;
            color: #000 !important;
            text-shadow: none;
            -webkit-box-shadow: none;
            box-shadow: none;
        }
        *
        {
            box-sizing: border-box;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
        }
        :after
        {
            box-sizing: border-box;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
        }
        :before
        {
            box-sizing: border-box;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
        }
        HTML
        {
            font-size: 10px;
            -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
        }
        BODY
        {
            line-height: 1.4285;
            background-color: #fff;
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            color: #333;
            font-size: 14px;
        }
        BUTTON
        {
            line-height: inherit;
            font-family: inherit;
            font-size: inherit;
        }
        .container
        {
            padding-left: 15px;
            padding-right: 15px;
            margin-left: auto;
            margin-right: auto;
        }
        .row
        {
            margin-left: -15px;
            margin-right: -15px;
        }
        .col-md-9
        {
            position: relative;
            min-height: 1px;
            padding-left: 15px;
            padding-right: 15px;
        }
        .btn
        {
            background-image: none;
            border-bottom: transparent 1px solid;
            text-align: center;
            border-left: transparent 1px solid;
            padding-bottom: 6px;
            line-height: 1.4285;
            padding-left: 12px;
            padding-right: 12px;
            display: inline-block;
            white-space: nowrap;
            margin-bottom: 0px;
            font-size: 14px;
            vertical-align: middle;
            border-top: transparent 1px solid;
            cursor: pointer;
            font-weight: 400;
            border-right: transparent 1px solid;
            padding-top: 6px;
            border-radius: 4px;
            -ms-touch-action: manipulation;
            touch-action: manipulation;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }
        .btn-default
        {
            border-bottom-color: #ccc;
            background-color: #fff;
            border-top-color: #ccc;
            color: #333;
            border-right-color: #ccc;
            border-left-color: #ccc;
        }
        .btn-primary
        {
            border-bottom-color: #2e6da4;
            background-color: #337ab7;
            border-top-color: #2e6da4;
            color: #fff;
            border-right-color: #2e6da4;
            border-left-color: #2e6da4;
        }
        .btn-success
        {
            border-bottom-color: #4cae4c;
            background-color: #5cb85c;
            border-top-color: #4cae4c;
            color: #fff;
            border-right-color: #4cae4c;
            border-left-color: #4cae4c;
        }
        .btn-info
        {
            border-bottom-color: #46b8da;
            background-color: #5bc0de;
            border-top-color: #46b8da;
            color: #fff;
            border-right-color: #46b8da;
            border-left-color: #46b8da;
        }
        .btn-warning
        {
            border-bottom-color: #eea236;
            background-color: #f0ad4e;
            border-top-color: #eea236;
            color: #fff;
            border-right-color: #eea236;
            border-left-color: #eea236;
        }
        .btn-danger
        {
            border-bottom-color: #d43f3a;
            background-color: #d9534f;
            border-top-color: #d43f3a;
            color: #fff;
            border-right-color: #d43f3a;
            border-left-color: #d43f3a;
        }
        .btn-link
        {
            color: #337ab7;
            font-weight: 400;
            border-radius: 0;
        }
        .btn-link
        {
            background-color: transparent;
            -webkit-box-shadow: none;
            box-shadow: none;
        }
        .btn-link
        {
            border-bottom-color: transparent;
            border-top-color: transparent;
            border-right-color: transparent;
            border-left-color: transparent;
        }
        .container:after
        {
            display: table;
            content: " ";
        }
        .container:before
        {
            display: table;
            content: " ";
        }
        .row:after
        {
            display: table;
            content: " ";
        }
        .row:before
        {
            display: table;
            content: " ";
        }
        .container:after
        {
            clear: both;
        }
        .row:after
        {
            clear: both;
        }
        .active{
            background-color: #4DF989;
        }

        button {
            padding: 10px 10px 10px 10px;
            margin-bottom: 10px;
            margin: 4px;
            width: 120px;
        }
        	td.ui-dialog-body {
            height: auto;
     }

    </style>
</head>
<body>

<div id="game-server-list" class="modal-body">
    <c:forEach items="${fns:getCurrentGameServerList()}" var="item" varStatus="serverIdStatus">
        <%--<input type="checkbox" class="ids_${status.index}" name="ids_${status.index}" value="${item.worldId}" id="${item.worldId}"/>${item.worldName}<spring:message code='str498'/>${item.worldId}<spring:message code='str1377'/>--%>
        <BUTTON id="${item.worldId}" textValue="${item.name}" status="${item.status}" checkedStatus="0"  followerId="${item.followerId }"  onclick="isServerChecked(this)" class="btn <c:if test="${item.status == 0}">btn-success</c:if><c:if test="${item.status == 2}">btn-danger</c:if> <c:if test="${item.followerId > 0}">btn-warning</c:if> ">${item.name}</BUTTON>
    </c:forEach>
</div>

<script type="text/javascript">

    var selectedGameServerId;
    var selectedGameServerName;
    var selectedGamePlatformName;

    var textVal;

    $(function(){

        $("#contentTable > tbody > tr").click(function(){
            removeAllSelected();
            $(this).addClass("selected");
            selectedGamePlatformName = $(this).children("td").get(6).innerHTML;
            selectedGameServerId = $(this).children("td").get(1).innerHTML;
            selectedGameServerName = $(this).children("td").get(3).innerHTML + '<spring:message code='str498'/>'+selectedGameServerId+'<spring:message code='str1377'/>';
        });

    });

    function removeAllSelected(){
        $("#contentTable > tbody > tr").each(function(){
            $(this).removeClass("selected");
        });
    }

    function isServerChecked(a) {
        var check = a.attributes.checkedStatus.nodeValue;

        $("#game-server-list BUTTON").each(function(){
            var status = this.attributes.status.nodeValue;
            $(this).removeAttr("class");
            $(this).attr("checkedStatus", 0);
            if(status == 0){
                var followerId=this.attributes.followerId.nodeValue;
                if(followerId  > 0){
                    $(this).attr("class", "btn btn-warning");
                }else{
                	  $(this).attr("class", "btn btn-success");
                }
            }else{
                $(this).attr("class", "btn btn-danger");
            }
        });

        $(a).attr("class", "btn btn-default");
        $(a).attr("checkedStatus", 1);

    }



    function myCallback(){
        $("#game-server-list BUTTON").each(function(){
            var check = this.attributes.checkedStatus.nodeValue;
            var id = this.attributes.id.nodeValue;
            var textValue = this.attributes.textValue.nodeValue;
            if(check == 1) {
                selectedGameServerId = id;
                textVal = textValue;
            }
        });

        if(selectedGameServerId != null) {
            parent.$("#currentGameServer").text(textVal);
            _remoteCall("${ctx}/tools/gameArea/changeGameServer",{gameServerId : selectedGameServerId},function(result){
                if(result.success){
                    console.log("<spring:message code='str1511'/>" + selectedGameServerId);
                }
            });
        }
    }

</script>
</body>
</html>
