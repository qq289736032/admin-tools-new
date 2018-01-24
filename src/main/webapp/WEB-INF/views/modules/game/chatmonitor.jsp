<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title><spring:message code='str12'/></title>
    <meta name="decorator" content="default"/>
</head>
<body>
<%--<div class="title_area">--%>
    <%--&lt;%&ndash;<h2><spring:message code='str12'/></h2>&ndash;%&gt;--%>
<%--</div>--%>
<div class="bd">
    <div id="flashContainer">
        <p>...</p>
    </div>
</div>
<script type="text/javascript">
    var swf = "${ctxStatic}/swf/Monitor.swf";
    var swfVersionStr = "10.0.0";
    var xiSwfUrlStr = "";

    var flashvars =
    {
        pid: '${platformId}',
        user_id:'${user.id}',
        user_name:'${user.loginName}',
        port:${port}
    }

    var params = {
        menu: "false",
        scale: "noScale",
        allowFullscreen: "true",
        allowScriptAccess: "always",
        wmode:"transparent",
        allowFullScreenInteractive:"true"
    };

    var attributes = {
        id:"MainFlash"
    };

    $(function(){
        calFlashContainerHeight();
        flashvars.ip = window.location.hostname;
        load();
    });

    function calFlashContainerHeight(){
        var height = $('#mainFrame').height();
        $('#mainFrame .bd').height(height);
    }

    function load()
    {
        swfobject.embedSWF(
                swf, "flashContainer",
                "100%", parent.$('#mainFrame').height(),
                swfVersionStr, xiSwfUrlStr,
                flashvars, params, attributes);
        if(window.ActiveXObject)
        {
            document.onkeydown=function(){
                if(event.keyCode==9){
                    event.keyCode=0;
                    event.returnValue=false;
                    return false;
                }
            };
        }

        setMainSwfHeight();

        $(window).resize(function() {
            calFlashContainerHeight();
            setMainSwfHeight();
        });

        function setMainSwfHeight(){
            $("#main").height( $(document.body).height());
        }
    }

</script>

<script type="text/javascript" src="${ctxStatic}/common/swfobject.js"></script>
</body>
</html>
