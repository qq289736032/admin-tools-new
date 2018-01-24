<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1139'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
    <script type="text/javascript">

        $(document).ready(function() {
            $("#content").focus();
            $("#inputForm").validate({
                submitHandler: function(form){

                    var isGlobal;
                    $('input[name="isGlobal"]:checked').each(function(){
                        isGlobal = $(this).val();
                    });
                    if(isGlobal == 0){
                        var serverIds = $("#serverIds").val();
                        if(serverIds == ''){
                            tips("<spring:message code='str1281'/>")
                            return;
                        }
                    }
                    var  contentEditor=CKEDITOR.instances.content;
                    var content= contentEditor.getData();
                    contentEditor.setData(encodeURI(content));
                    
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

          function submitForm(){
            var isGlobal = $(".isGlobal").attr('checked').val();
            alert(isGlobal);
            return;
            if(!isGlobal){
                var serverIds = $("#serverIds").val();
                if(serverIds == ''){
                    tips("<spring:message code='str1282'/>")
                    return;
                }
            }
            $("#inputForm").submit();
        }

    </script>
    <style type="text/css">
        td#cke_contents_content {
            height: 158px !important;
        }
        span#cke_content {
            width: 60% !important;
            /* height: 200px; */
        }
    </style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tools/gameNotice/"><spring:message code='str998'/></a></li>
		<shiro:hasPermission name="game.notice.edit">
			<li class="active"><a href="${ctx}/tools/gameNotice/form?id=${gameNotice.id}"><c:if test="${ empty gameEmail.id}"><spring:message code='operation.add'/></c:if><c:if test=" ${not empty gameEmail.id}"><spring:message code=' operation.modify'/></c:if><spring:message code='str1340'/></a></li>
		</shiro:hasPermission>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="gameNotice" action="${ctx}/tools/gameNotice/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>

        <div class="control-group">
            <label class="control-label"><spring:message code='str1341'/>:</label>
            <div class="controls">
            <select name="noticeType" id="noticeType">
                <c:forEach items="${fns:getDictList('notice_type')}" var="items">
                  <option value="${items.value }"><spring:message code='${items.internationalKey }'/> </option>
             </c:forEach>
          </select>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1342'/>:</label>
			<div class="controls">
                <form:textarea path="content" htmlEscape="false" rows="4" maxlength="200"  class="input-xxlarge required"/>
                <tags:ckeditor replace="content"/>
            <!--     
            	<textarea id="content_tmp"  name="content_tmp"  style="display:none" htmlEscape="false"   class="input-xxlarge required"></textarea>
			 -->
			 </div>
		</div>
        <%--<div class="control-group">--%>
            <%--<label class="control-label"><spring:message code='str1343'/>:</label>--%>
            <%--<div class="controls">--%>
                <%--<input id="content.href" name="content.href" class="input-small"/>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="control-group">
            <label class="control-label"><spring:message code='str1344'/>:</label>
            <div class="controls">
                <input name="startTime" id="startTime" maxlength="50"  class="input-small required" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>
                -
                <input name="finishTime"  id="finishTime" maxlength="50"  class="input-small required" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'startTime\')}'})"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><spring:message code='str1345'/>:</label>
            <div class="controls">
                <input name="stepTime" id="stepTime" htmlEscape="false" class="digits required"/>
                <span class="label badge-info help-inline"><spring:message code='str1346'/></span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label"><spring:message code='str768'/>:</label>
            <div class="controls">
                <shiro:hasGlobalRoleTag>
                    <input type="radio" name="isGlobal" value="1"  onclick="$('#gameServerTreeDiv').hide()"><spring:message code='str1347'/>
                </shiro:hasGlobalRoleTag>
                <input type="radio" name="isGlobal" value="0" checked="checked" onclick="$('#gameServerTreeDiv').show()"><spring:message code='str1348'/>

            </div>
            <div class="controls" id="gameServerTreeDiv">
                <input id="serverIds" name="serverIds" readonly/>
                <%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog()"></button>--%>
                <input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
            </div>
        </div>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"  value="<spring:message code='str1176'/> <spring:message code='str1177'/>"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>

    <div id="ServerContent"></div>
</body>
</html>
