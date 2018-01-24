<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title><spring:message code='str12'/></title>
    <meta name="decorator" content="default"/>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/game/chat/monitor"><spring:message code='str13'/></a></li>
</ul>
<form id="searchForm"  action="${ctx}/game/chat/monitor" method="post" class="breadcrumb form-search">

    <div>
        <label><spring:message code='str14'/>:</label>
        <input id="serverIds" name="serverIds" readonly/><input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();"/>
		<shiro:hasPermission name="game.chatmonitor.monitor">
        &nbsp;<input id="btnMonitor" class="btn btn-primary" type="button" value="<spring:message code='str16'/>" onclick="monitor();"/>		
        &nbsp;<input id="btnCancelMonitor" class="btn btn-danger" type="button" value="<spring:message code='str17'/>" onclick="cancelMonitor();"/>
		</shiro:hasPermission>
		<shiro:hasPermission name="game.chatmonitor.keyWordsEdit">
        &nbsp;<input id="addkeyWords" class="btn btn-primary" type="button" value="<spring:message code='str18'/>" onclick="addKeyWords();"/>
		</shiro:hasPermission>
     </div>
</form>
<div id="keyword" class="row-fluid span12">
    <div class="span4 panel panel-primary">
        <div class="panel-heading"><spring:message code='str19'/></div>
        <%--<div class="panel-body">--%>
        <table id="contentTable" class="table table-striped table-bordered table-condensed">

            <%--<tr>--%>
                <%--<th><spring:message code='str20'/></th><td>keyword_01</td><td><a href="${ctx}/game/chat/deleteKey?keyword=keyword_1" onclick="return confirmx('<spring:message code='str21'/>', this.href)"><spring:message code='str22'/></a></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<th><spring:message code='str20'/></th><td>keyword_01</td><td><a href="${ctx}/game/chat/deleteKey?keyword=keyword_1" onclick="return confirmx('<spring:message code='str21'/>', this.href)"><spring:message code='str22'/></a></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<th><spring:message code='str20'/></th><td>keyword_01</td><td><a href="${ctx}/game/chat/deleteKey?keyword=keyword_1" onclick="return confirmx('<spring:message code='str21'/>', this.href)"><spring:message code='str22'/></a></td>--%>
            <%--</tr>--%>
            <c:forEach var="keyword" items="${keywords}">
                <tr id="${keyword.key}">
                    <th><spring:message code='str20'/></th><td>${keyword.value}</td>
					<shiro:hasPermission name="game.chatmonitor.keyWordsEdit">
					<td><a href="#" onclick="deleteKey('<spring:message code='str21'/>', '${ctx}/game/chat/deleteKey?keyword=${keyword.key}', ${keyword.key})"><spring:message code='str22'/></a></td>
					</shiro:hasPermission>
                </tr>
            </c:forEach>

        </table>
        <%--</div>--%>
    </div>
    <div class="span8 alert alert-warning">
        1<spring:message code='str23'/>5<br/>
        2<spring:message code='str24'/>"<spring:message code='str16'/>"<spring:message code='str25'/>
        3<spring:message code='str26'/>2<spring:message code='str27'/><br/>
        4<spring:message code='str28'/><br/>
        5<spring:message code='str29'/> <spring:message code='str30'/>
    </div>
</div>

<div id="monitor" class="row-fluid span8">
    <div class="panel panel-primary span4">
        <div class="panel-heading"><spring:message code='str31'/>1</div>
        <div class="panel-body">
            <div id = "content_1" style = "overflow:scroll; height: 300px; width: 100%;"></div>
        </div>
    </div>

    <div class="panel panel-primary span4">
        <div class="panel-heading"><spring:message code='str31'/>2</div>
        <div class="panel-body">
            <div id = "content_2" style = "overflow:scroll; height: 300px; width: 100%; "></div>
        </div>
    </div>

</div>

<div class="pagination">${page}</div>

<script type="text/javascript">

    $(document).ready(function() {
        $('#btnCancelMonitor').hide();
    })

    function jinYan(serverId,roleId){
        ajaxRequest("<spring:message code='str32'/>?","${ctx}/game/role/jinYan",{serverId:serverId,roleId:roleId});
    }

    function getDate(serverIds){
        _remoteCall("${ctx}/game/chat/fetchData",{serverIds:serverIds},function(result){
            if(result.success){
            	
                var obj = result.data;
                var content1_his = $("#content_1").html();
                var content2_his = $("#content_2").html();

                $("#content_1").html(content1_his + obj.monitor1);
                $("#content_2").html(content2_his + obj.monitor2);

            }else{
                top.$.jBox.tip("<spring:message code='str33'/>", 'error');
            }
        })
    }

    function monitor(){
        $('#btnMonitor').val('<spring:message code='str34'/>...').attr({"disabled":"disabled"});
        $("#btnCancelMonitor").show();
        var serverIds = $('#serverIds').val();

        if(serverIds == null){
            top.$.jBox.tip("<spring:message code='str35'/>", 'error');
            return;
        }
        //<spring:message code='str36'/>
        startMonitor(serverIds);
        setInterval(startMonitor,6000*10*10,serverIds);    //10<spring:message code='str37'/>

        setInterval(getDate,5000,serverIds)       //5<spring:message code='str38'/>,<spring:message code='str39'/>
    }


    function startMonitor(serverIds){
        _remoteCall("${ctx}/game/chat/startMonitor",{serverIds:serverIds},function(result){
            if(result.success){
               top.$.jBox.tip("<spring:message code='str40'/>", 'success');
            }
        })
    }

    function cancelMonitor(){

        var serverIds = $('#serverIds').val();
        _remoteCall("${ctx}/game/chat/cancelMonitor",{serverIds:serverIds},function(result){
            if(result.success){
                top.$.jBox.tip('<spring:message code='str41'/>!', 'success');
            }else{

                top.$.jBox.tip(result.data, 'error');
                return false;
            }
        })

        $('#btnCancelMonitor').hide();
        $('#btnMonitor').val('<spring:message code='str16'/>').removeAttr("disabled");

    }


    function deleteKey(msg,href,id){

        var submit = function (v, h, f) {
            if (v == 'ok') {
                _remoteCall(href,null,function(result) {
                    if (result.success) {
                        top.$.jBox.tip('<spring:message code='str42'/>', 'success');
                        $("#"+id).remove();
                        //<spring:message code='str43'/>,<spring:message code='str44'/>
                        //cancelMonitor();
                    }
                });
            }
            else if (v == 'cancel') {
                top.jBox.tip(v, 'info');
            }
            return true; //close
        };

        top.$.jBox.confirm(msg, "<spring:message code='str45'/>", submit);
    }

    function addKeyWords(){
        var value = '';
        top.$.jBox.confirm("<spring:message code='str46'/>?","<spring:message code='str45'/>",function(v,h,f){
            if(v=="ok"){
                var html = "<div style='padding:10px;'><spring:message code='str47'/><input type='text' id='keyword_value' name='keyword_value' class='required' /></div>";
                var submit = function (v, h, f) {
                    if (f.keyword_value == '') {
                        top.$.jBox.tip("<spring:message code='str48'/>!!!", 'error', { focusId: "keyword_value" }); // <spring:message code='str49'/> reason <spring:message code='str50'/>
                        return false;
                    }
                    value = f.keyword_value;

                    var dateIndex = $('#contentTable tr').length + 1;
                    var keyword_key = ('keyword_'+dateIndex+Math.random()).replace('.','');

                    //$('#contentTable').append("<tr><th><spring:message code='str20'/></th><td>"+value+"</td><td><a href=\"${ctx}/game/chat/deleteKey?keyword="+keyword_key+"\" onclick=\"return confirmx('<spring:message code='str21'/>', this.href)\"><spring:message code='str22'/></a></td></tr>");

                    _remoteCall("${ctx}/game/chat/addKeyword",{key:keyword_key,keyword:value},function(result){
                        if(result.success){
                            $('#contentTable').append("<tr id="+keyword_key+"><th><spring:message code='str20'/></th><td>"+value+"</td><td><a href='#' onclick=\"deleteKey('<spring:message code='str21'/>', \'${ctx}/game/chat/deleteKey?keyword="+keyword_key+"\',\'"+keyword_key+"\')\"><spring:message code='str22'/></a></td></tr>");
                            top.$.jBox.tip('<spring:message code='str51'/>', 'success');
//                            if($('#btnMonitor').isDisabled) {
//                                cancelMonitor();
//                            }
                        }
                    })

                    return true;
                };

                top.$.jBox(html, { title: "<spring:message code='str18'/>", submit: submit });
            }
        },{buttonsFocus:1});
        top.$('.jbox-body .jbox-icon').css('top','55px');

    }
</script>

</body>
</html>
